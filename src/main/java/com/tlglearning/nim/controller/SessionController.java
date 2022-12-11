package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Move;
import com.tlglearning.nim.model.State;
import com.tlglearning.nim.model.Tally;
import com.tlglearning.nim.strategy.Strategy;
import com.tlglearning.nim.view.GameView;
import com.tlglearning.nim.view.MoveView;
import com.tlglearning.nim.view.TallyView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionController {

  private static final String REPLAY_PROMPT_KEY = "replay_prompt";
  private static final String NEGATIVE_RESPONSE_KEY = "negative_response";
  private static final String SESSION_END_KEY = "session_end";
  private static final Pattern MOVE_INPUT_PATTERN = Pattern.compile("^(\\d+)\\s+(\\d+)$");
  private static final String INVALID_MOVE_FORMAT_KEY = "invalid_move_format";

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
      State state = new GameController().play();
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
    String input = reader
        .readLine()
        .trim()
        .toLowerCase();
    return !input.startsWith(negativeResponse);
  }

  private class GameController {

    private final Game game;
    private final GameView gameView;
    private final MoveView moveView;
    private final String invalidMoveFormat;

    public GameController() {
      game = new Game(initialState, pileSizes);
      gameView = new GameView(bundle);
      moveView = new MoveView(bundle);
      invalidMoveFormat = bundle.getString(INVALID_MOVE_FORMAT_KEY);
    }

    public State play() throws IOException {
      State state = game.getState();
      Move move;
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      while (!state.isTerminal()) {
        if (state == State.PLAYER_1_MOVE) {
          move = strategy.selectMove(game);
          game.play(move);
        } else {
          move = getAndApplyUserMove();
        }
        writer.print(moveView.toString(state, move));
        state = game.getState();
      }
      writer.print(gameView.toString(game));
      return state;
    }

    private Move getAndApplyUserMove() throws IOException {
      Move move = null;
      int numPiles = game.getPiles().size();
      int pileNumber = 0;
      int quantity = 0;
      while (true) {
        try {
          writer.print(gameView.toString(game));
          String input = reader.readLine().trim();
          Matcher matcher = MOVE_INPUT_PATTERN.matcher(input);
          if (!matcher.matches()) {
            throw new IllegalArgumentException();
          }
          pileNumber = Integer.parseInt(matcher.group(1));
          quantity = Integer.parseInt(matcher.group(2));
          if (pileNumber < 1 || pileNumber > numPiles || quantity < 1) {
            throw new IllegalArgumentException();
          }
          move = new Move(pileNumber, game.getPiles().get(pileNumber - 1), quantity);
          game.play(move);
          break;
        } catch (IllegalArgumentException e) {
          writer.printf(invalidMoveFormat, pileNumber, quantity, numPiles);
        }
      }
      return move;
    }

  }
}
