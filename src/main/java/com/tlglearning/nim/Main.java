package com.tlglearning.nim;

import com.tlglearning.nim.controller.SessionController;
import com.tlglearning.nim.model.State;
import com.tlglearning.nim.strategy.OptimalStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class Main {

  private static final String BUNDLE_NAME = "ui-strings";
  private static final int[] pileSizes = {3, 5, 7};

  public static void main(String[] args) throws IOException {
    new SessionController(
        new BufferedReader(new InputStreamReader(System.in)),
        System.out,
        ResourceBundle.getBundle(BUNDLE_NAME),
        State.PLAYER_1_MOVE,
        new OptimalStrategy(),
        pileSizes
    ).play();
  }

}
