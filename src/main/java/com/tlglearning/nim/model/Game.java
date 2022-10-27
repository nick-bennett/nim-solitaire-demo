package com.tlglearning.nim.model;

import java.util.LinkedList;
import java.util.List;

public class Game {

  private List<Pile> piles;
  private State state;

  public Game(State state, int[] pileSizes) throws IllegalArgumentException {
    if (state.isTerminal()) {
      throw new IllegalArgumentException("Game must start in a non-terminal state");
    }
    List<Pile> piles = new LinkedList<>();
    for (int size : pileSizes) {
      piles.add(new Pile(size));
    }
    this.piles = piles;
    if (isFinished()) {
      this.state = state.nextWinState(); // TODO: 2022-10-27 Explore whether this should be allowed.
    }
  }

  public void play(Pile pile, int quantity) throws IllegalArgumentException {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-25 Implement to remove the specified quantity from the specified pile, and to update the state accordingly (how?).
  }

  public boolean isFinished() {
    return piles.stream()
        .allMatch(Pile::isEmpty);
  }

  public List<Pile> getPiles() {
    return piles;
  }

  public State getState() {
    return state;
  }

  // DONE: 2022-10-25 Create getters for piles and state.

  // DONE: 2022-10-25 Create a test class for Game, with methods to test construction, play, and isFinished.

}
