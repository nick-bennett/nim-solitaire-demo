package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Move;
import com.tlglearning.nim.model.State;
import java.util.ResourceBundle;

public class MoveView {

  private static final String MOVE_FORMAT_KEY = "move_format";
  private static final String PLAYER_KEY_SUFFIX = "_name";

  private final ResourceBundle bundle;
  private final String moveFormat;

  public MoveView(ResourceBundle bundle) {
    this.bundle = bundle;
    moveFormat = bundle.getString(MOVE_FORMAT_KEY);
  }

  public String toString(State state, Move move) {
    return String.format(
        moveFormat, bundle.getString(state.toString().toLowerCase() + PLAYER_KEY_SUFFIX),
        move.getPileNumber(), move.getQuantity());
  }

}
