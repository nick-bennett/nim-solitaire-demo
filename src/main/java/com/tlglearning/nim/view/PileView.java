package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Pile;

public class PileView {

  private static final String REMOVED = "\u2542";
  private static final String REMAINING = "\u2503";

  public String toString(Pile pile) {
    return REMOVED.repeat(pile.getRemoved()) + " " + REMAINING.repeat(pile.getRemaining());
  }

}
