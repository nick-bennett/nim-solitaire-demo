package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.Game.State;
import com.tlglearning.nim.model.Session;
import com.tlglearning.nim.strategy.OptimalStrategy;
import com.tlglearning.nim.strategy.Strategy;
import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SessionController {

  private static final String REPLAY_PROMPT_KEY = "replay_prompt";
  private static final String NEGATIVE_RESPONSE_KEY = "negative_response";

  private final Strategy strategy;
  private final ResourceBundle bundle;
  private final int[] pileSizes;
  private final Session session;
  private final String replayPrompt;
  private final String negativeResponse;

  private State initialState;

  public SessionController(
      State initialState, Strategy strategy, ResourceBundle bundle, int... pileSizes) {
    this.initialState = initialState;
    this.strategy = strategy;
    this.bundle = bundle;
    this.pileSizes = Arrays.copyOf(pileSizes, pileSizes.length);
    session = new Session();
    replayPrompt = bundle.getString(REPLAY_PROMPT_KEY);
    negativeResponse = bundle.getString(NEGATIVE_RESPONSE_KEY);
  }

  public void play() throws IOException {
    do {
      State state = new GameController(initialState, strategy, bundle, 1, 4, 5).play();
      if (state == State.PLAYER_1_WIN) {
        session.lose();
      } else {
        session.win();
      }
      initialState = initialState.nextMoveState();
    } while (playAgain());
  }

  private boolean playAgain() {

  }

}
