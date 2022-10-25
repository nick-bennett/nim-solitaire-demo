package com.tlglearning.nim.model;

import java.util.List;

public class Game {

  private List<Pile> piles;
  private State state;

  public Game(State state, int[] pileSizes) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-25 Implement to create the list of piles, each with a size given by the pileSizes array.
  }

  public void play(Pile pile, int quantity) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-25 Implement to remove the specified quantity from the specified pile, and to update the state accordingly (how?).
  }

  public boolean isFinished() {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-25 Check all the piles and return true if all are empty.
  }

  // TODO: 2022-10-25 Create getters for piles and state.

  // TODO: 2022-10-25 Create a test class for Game, with methods to test construction, play, and isFinished.

}
