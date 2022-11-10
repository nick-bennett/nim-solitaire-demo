package com.tlglearning.nim;

import com.tlglearning.nim.controller.GameController;
import com.tlglearning.nim.model.Game.State;
import com.tlglearning.nim.strategy.OptimalStrategy;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main {

  private static final String BUNDLE_NAME = "ui-strings";

  public static void main(String[] args) throws IOException {
    new GameController(
        State.PLAYER_1_MOVE, new OptimalStrategy(), ResourceBundle.getBundle(BUNDLE_NAME), 1, 4, 5
    ).play();
  }

}
