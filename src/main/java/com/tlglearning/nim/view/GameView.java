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

  private static final String PREFIX = System.lineSeparator();
  private static final String PILE_SEPARATOR = PREFIX.repeat(2);
  private static final String SUFFIX = "";

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
                .mapToObj((num) -> pileView.toString(num, iterator.next())),
            Stream.of(bundle.getString(game.getState().toString().toLowerCase()))
        )
        .collect(Collectors.joining(PILE_SEPARATOR, PREFIX, SUFFIX));
  }

  private class PileView {

    private static final String PILE_FORMAT_SINGLE_KEY = "pile_format_single";
    private static final String PILE_FORMAT_MULTIPLE_KEY = "pile_format_multiple";
    private static final String REMOVED_CHARACTER = "\u2542";
    private static final String REMAINING_CHARACTER = "\u2503";

    private final String pileFormatSingle = bundle.getString(PILE_FORMAT_SINGLE_KEY);
    private final String pileFormatMultiple = bundle.getString(PILE_FORMAT_MULTIPLE_KEY);

    public String toString(int pileNumber, Pile pile) {
      String representation;
      int removed = pile.getRemoved();
      int remaining = pile.getRemaining();
      if (removed != 0 && remaining != 0) {
        representation = String.format(pileFormatMultiple, pileNumber,
            REMOVED_CHARACTER.repeat(removed), REMAINING_CHARACTER.repeat(remaining), remaining);
      } else if (removed != 0) {
        representation = String.format(pileFormatSingle, pileNumber,
            REMOVED_CHARACTER.repeat(removed), remaining);
      } else {
        representation = String.format(pileFormatSingle, pileNumber,
            REMAINING_CHARACTER.repeat(remaining), remaining);
      }
      return representation;
    }

  }

}
