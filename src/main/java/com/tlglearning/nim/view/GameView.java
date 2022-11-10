package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.Pile;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameView {

  private static final String PILE_SEPARATOR = System.lineSeparator().repeat(2);

  private final ResourceBundle bundle;

  public GameView(ResourceBundle bundle) {
    this.bundle = bundle;
  }

  public String toString(Game game) {
    PileView pileView = new PileView();
    List<Pile> piles = game.getPiles();
    Iterator<Pile> iterator = piles.iterator();
    return Stream.concat(
            IntStream.rangeClosed(1, piles.size())
                .mapToObj((num) -> String.format("%d: %s", num, pileView.toString(iterator.next()))),
            Stream.of(bundle.getString(game.getState().toString().toLowerCase()))
        )
        .collect(Collectors.joining(PILE_SEPARATOR));
  }

  public static class PileView {

    private static final String REMOVED = "\u2542";
    private static final String REMAINING = "\u2503";

    public String toString(Pile pile) {
      return REMOVED.repeat(pile.getRemoved()) + " " + REMAINING.repeat(pile.getRemaining());
    }

  }

}
