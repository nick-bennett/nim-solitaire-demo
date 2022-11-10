package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.State;
import com.tlglearning.nim.strategy.Strategy;
import com.tlglearning.nim.view.GameView;
import com.tlglearning.nim.view.MoveView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

  private static final Pattern MOVE_INPUT_PATTERN = Pattern.compile("(\\d+)\\s+(\\d+)");

  private final Game game;
  private final GameView gameView;
  private final MoveView moveView;
  private final Strategy strategy;
  private final ResourceBundle bundle;

  public GameController(
      State initialState, Strategy strategy, ResourceBundle bundle, int... pileSizes) {
    game = new Game(initialState, pileSizes);
    gameView = new GameView(bundle);
    moveView = new MoveView(bundle);
    this.strategy = strategy;
    this.bundle = bundle;
  }

  public State play() {
    State state = game.getState();
    while (!state.isTerminal()) {
      int[] move;
      if (state == State.PLAYER_1_MOVE) {
        move = strategy.selectMove(game);
      } else {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
          System.out.println(gameView.toString(game));
          String input = reader.readLine().trim();
          Matcher matcher = MOVE_INPUT_PATTERN.matcher(input);
          if (!matcher.matches()) {
            throw new IllegalArgumentException();
          }
        }
        move = new int[]{0, 0};
        // TODO: 2022-11-09 Get the user's move.
      }
      game.play(game.getPiles().get(move[0] - 1), move[1]);
      state = game.getState();
      // DONE: 2022-11-09 Display the move made, by whom, and the outcome (if terminal).
      System.out.println(moveView.toString(game, move));
    }
    return state;
  }

}
