package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.State;
import com.tlglearning.nim.strategy.Strategy;
import com.tlglearning.nim.view.GameView;

public class GameController {

  private final Game game;
  private final GameView view;
  private final Strategy strategy;

  public GameController(State initialState, Strategy strategy, int... pileSizes) {
    game = new Game(initialState, pileSizes);
    view = new GameView();
    this.strategy = strategy;
  }

  public State play() {
    State state = game.getState();
    while (!state.isTerminal()) {
      int[] move;
      if (state == State.PLAYER_1_MOVE) {
        move = strategy.selectMove(game);
      } else {
        System.out.println(view.toString(game));
        // TODO: 2022-11-09 Get the user's move.
      }
      game.play(game.getPiles().get(move[0] - 1), move[1]);
      state = game.getState();
      // TODO: 2022-11-09 Display the move made, by whom, and the outcome (if terminal).
    }
    return state;
  }

}
