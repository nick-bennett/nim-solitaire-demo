package com.tlglearning.nim.model;

import java.util.LinkedList;
import java.util.List;

public class Game {

  private final List<Pile> piles;
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
    this.state = isFinished() ? state.nextMoveState().nextWinState() : state;
  }

  public void play(Pile pile, int quantity) throws IllegalArgumentException {
    if (state.isTerminal()) {
      throw new IllegalStateException("Game already finished.");
    }
    pile.remove(quantity);
    state = isFinished() ? state.nextWinState() : state.nextMoveState();
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

}
