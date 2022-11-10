package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.State;
import com.tlglearning.nim.strategy.Strategy;
import com.tlglearning.nim.view.GameView;
import com.tlglearning.nim.view.MoveView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

  private static final Pattern MOVE_INPUT_PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)");
  private static final String INVALID_MOVE_FORMAT_KEY = "invalid_move_format";

  private final Game game;
  private final GameView gameView;
  private final MoveView moveView;
  private final Strategy strategy;
  private final ResourceBundle bundle;
  private final String invalidMoveFormat;

  public GameController(
      State initialState, Strategy strategy, ResourceBundle bundle, int... pileSizes) {
    game = new Game(initialState, pileSizes);
    gameView = new GameView(bundle);
    moveView = new MoveView(bundle);
    this.strategy = strategy;
    this.bundle = bundle;
    invalidMoveFormat = bundle.getString(INVALID_MOVE_FORMAT_KEY);
  }

  public State play() throws IOException {
    State state = game.getState();
    int[] move;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (!state.isTerminal()) {
      if (state == State.PLAYER_1_MOVE) {
        move = strategy.selectMove(game);
        game.play(game.getPiles().get(move[0] - 1), move[1]);
      } else {
        move = getAndApplyUserMove(reader);
      }
      System.out.println(moveView.toString(state, move));
      state = game.getState();
    }
    System.out.print(gameView.toString(game));
    return state;
  }

  private int[] getAndApplyUserMove(BufferedReader reader) throws IOException {
    int[] move = null;
    int numPiles = game.getPiles().size();
    int pileNumber = 0;
    int quantity = 0;
    while (move == null) {
      try {
        System.out.print(gameView.toString(game));
        String input = reader.readLine();
        input = input.trim();
        Matcher matcher = MOVE_INPUT_PATTERN.matcher(input);
        if (!matcher.matches()) {
          throw new IllegalArgumentException();
        }
        pileNumber = Integer.parseInt(matcher.group(1));
        quantity = Integer.parseInt(matcher.group(2));
        if (pileNumber < 1 || pileNumber > numPiles || quantity < 1) {
          throw new IllegalArgumentException();
        }
        game.play(game.getPiles().get(pileNumber - 1), quantity);
        move = new int[]{pileNumber, quantity};
      } catch (IllegalArgumentException e) {
        System.out.printf(invalidMoveFormat, pileNumber, quantity, numPiles);
      }
    }
    return move;
  }

}
