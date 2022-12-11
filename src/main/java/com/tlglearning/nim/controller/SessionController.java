package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.State;
import com.tlglearning.nim.model.Tally;
import com.tlglearning.nim.strategy.Strategy;
import com.tlglearning.nim.view.TallyView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SessionController {

  private static final String REPLAY_PROMPT_KEY = "replay_prompt";
  private static final String NEGATIVE_RESPONSE_KEY = "negative_response";
  private static final String SESSION_END_KEY = "session_end";

  private final BufferedReader reader;
  private final PrintStream writer;
  private final ResourceBundle bundle;
  private final Strategy strategy;
  private final int[] pileSizes;
  private final String replayPrompt;
  private final String negativeResponse;
  private final String sessionEnd;

  private State initialState;

  public SessionController(BufferedReader reader, PrintStream writer, ResourceBundle bundle,
      State initialState, Strategy strategy, int... pileSizes) {
    this.reader = reader;
    this.writer = writer;
    this.bundle = bundle;
    this.initialState = initialState;
    this.strategy = strategy;
    this.pileSizes = Arrays.copyOf(pileSizes, pileSizes.length);
    replayPrompt = bundle.getString(REPLAY_PROMPT_KEY);
    negativeResponse = bundle.getString(NEGATIVE_RESPONSE_KEY);
    sessionEnd = bundle.getString(SESSION_END_KEY);
  }

  public void play() throws IOException {
    Tally tally = new Tally();
    TallyView view = new TallyView(bundle);
    do {
      State state =
          new GameController(reader, writer, bundle, initialState, strategy, pileSizes).play();
      if (state == State.PLAYER_1_WIN) {
        tally.lose();
      } else {
        tally.win();
      }
      writer.print(view.toString(tally));
      initialState = initialState.nextMoveState();
    } while (playAgain());
    writer.print(sessionEnd);
  }

  private boolean playAgain() throws IOException {
    writer.print(replayPrompt);
    String input = this.reader
        .readLine()
        .trim()
        .toLowerCase();
    return !input.startsWith(negativeResponse);
  }

}
