package com.tlglearning.nim.controller;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.State;
import com.tlglearning.nim.view.GameView;

public class GameController {

  private final Game game;
  private final GameView view;

  public GameController(State initialState, int... pileSizes) {
    game = new Game(initialState, pileSizes);
    view = new GameView();
  }

}
