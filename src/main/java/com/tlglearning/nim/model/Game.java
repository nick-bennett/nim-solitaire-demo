package com.tlglearning.nim.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

  private static final String BAD_INITIAL_STATE_FORMAT =
      "Invalid state: %s. Game must start in a non-terminal state";
  private static final String NO_MOVES_ALLOWED_FORMAT =
      "No moves allowed; game is in a terminal state: %s";
  private static final String TO_STRING_FORMAT = "%s{state=%s, piles=%s}";

  private State state;
  private final List<Pile> piles;

  public Game(State state, int... pileSizes) throws IllegalArgumentException {
    if (state.isTerminal()) {
      throw new IllegalArgumentException(String.format(BAD_INITIAL_STATE_FORMAT, state));
    }
    this.state = state;
    piles = Arrays.stream(pileSizes)
        .mapToObj(Pile::new)
        .collect(Collectors.toList());
  }

  public void play(Pile pile, int quantity) throws IllegalArgumentException {
    if (state.isTerminal()) {
      throw new IllegalStateException(String.format(NO_MOVES_ALLOWED_FORMAT, state));
    }
    pile.remove(quantity);
    state = isFinished() ? state.nextWinState() : state.nextMoveState();
  }

  public boolean isFinished() {
    return piles
        .stream()
        .allMatch(Pile::isEmpty);
  }

  public State getState() {
    return state;
  }

  public List<Pile> getPiles() {
    // Read-only view, but child objects are mutable. Discuss creating and returning a deep copy.
    return Collections.unmodifiableList(piles);
  }

  @Override
  public String toString() {
    return String.format(TO_STRING_FORMAT, getClass().getSimpleName(), state, piles);
  }

}
