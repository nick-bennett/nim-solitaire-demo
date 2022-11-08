package com.tlglearning.nim.model;

import java.util.LinkedList;
import java.util.List;

public class Game {

  private final List<Pile> piles;
  private State state;

  public Game(State state, int... pileSizes) throws IllegalArgumentException {
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

  public static class Pile {

    private static final String BAD_SIZE_FORMAT = "Invalid size: %d; must be non-negative.";
    private static final String BAD_REMOVE_QUANTITY_FORMAT =
        "Invalid quantity: %d; must not exceed number remaining (%d).";
    private static final String TO_STRING_FORMAT = "Pile{removed=%d, remaining=%d}";

    private int removed;
    private int remaining;

    public Pile(int size) throws IllegalArgumentException {
      if (size < 0) {
        throw new IllegalArgumentException(String.format(BAD_SIZE_FORMAT, size));
      }
      removed = 0;
      remaining = size;
    }

    public int remove(int quantity) throws IllegalArgumentException {
      if (quantity > remaining) {
        throw new IllegalArgumentException(
            String.format(BAD_REMOVE_QUANTITY_FORMAT, quantity, remaining));
      }
      removed += quantity;
      return (remaining -= quantity);
    }

    public boolean isEmpty() {
      return remaining == 0;
    }

    public int getRemoved() {
      return removed;
    }

    public int getRemaining() {
      return remaining;
    }

    @Override
    public String toString() {
      return String.format(TO_STRING_FORMAT, removed, remaining);
    }
  }

  public enum State {

    PLAYER_1_MOVE {
      @Override
      public boolean isTerminal() {
        return false;
      }

      @Override
      public State nextMoveState() {
        return PLAYER_2_MOVE;
      }

      @Override
      public State nextWinState() {
        return PLAYER_1_WIN;
      }
    },
    PLAYER_2_MOVE {
      @Override
      public boolean isTerminal() {
        return false;
      }

      @Override
      public State nextMoveState() {
        return PLAYER_1_MOVE;
      }

      @Override
      public State nextWinState() {
        return PLAYER_2_WIN;
      }
    },
    PLAYER_1_WIN,
    PLAYER_2_WIN;

    public boolean isTerminal() {
      return true;
    }

    public State nextMoveState() {
      return this;
    }

    public State nextWinState() {
      return this;
    }

  }
}
