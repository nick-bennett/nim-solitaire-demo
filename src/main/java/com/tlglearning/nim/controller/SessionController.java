package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.State;
import com.tlglearning.nim.model.Session;
import com.tlglearning.nim.strategy.Strategy;
import com.tlglearning.nim.view.SessionView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SessionController {

  private static final String REPLAY_PROMPT_KEY = "replay_prompt";
  private static final String NEGATIVE_RESPONSE_KEY = "negative_response";
  private static final String SESSION_END_KEY = "session_end";

  private final Strategy strategy;
  private final ResourceBundle bundle;
  private final int[] pileSizes;
  private final String replayPrompt;
  private final String negativeResponse;
  private final String sessionEnd;

  private State initialState;

  public SessionController(
      State initialState, Strategy strategy, ResourceBundle bundle, int... pileSizes) {
    this.initialState = initialState;
    this.strategy = strategy;
    this.bundle = bundle;
    this.pileSizes = Arrays.copyOf(pileSizes, pileSizes.length);
    replayPrompt = bundle.getString(REPLAY_PROMPT_KEY);
    negativeResponse = bundle.getString(NEGATIVE_RESPONSE_KEY);
    sessionEnd = bundle.getString(SESSION_END_KEY);
  }

  public void play() throws IOException {
    Session session = new Session();
    SessionView view = new SessionView(bundle);
    do {
      State state = new GameController(initialState, strategy, bundle, pileSizes).play();
      if (state == State.PLAYER_1_WIN) {
        session.lose();
      } else {
        session.win();
      }
      System.out.print(view.toString(session));
      initialState = initialState.nextMoveState();
    } while (playAgain());
    System.out.print(sessionEnd);
  }

  private boolean playAgain() throws IOException {
    System.out.print(replayPrompt);
    String input = new BufferedReader(new InputStreamReader(System.in))
        .readLine()
        .trim()
        .toLowerCase();
    return !input.startsWith(negativeResponse);
  }

}
