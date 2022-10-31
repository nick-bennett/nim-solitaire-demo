package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Game;
import java.util.stream.Collectors;

public class GameView {

  public String toString(Game game) {
    PileView pileView = new PileView();
    // TODO: 2022-10-31 Include state and pile # information.
    return game.getPiles()
        .stream()
        .map(pileView::toString)
        .collect(Collectors.joining(System.lineSeparator()));
  }

}
