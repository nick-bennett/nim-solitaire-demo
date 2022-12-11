package com.tlglearning.nim.model;

public enum State {

  PLAYER_1_MOVE(false) {
    @Override
    public State nextMoveState() {
      return PLAYER_2_MOVE;
    }

    @Override
    public State nextWinState() {
      return PLAYER_1_WIN;
    }
  },
  PLAYER_2_MOVE(false) {
    @Override
    public State nextMoveState() {
      return PLAYER_1_MOVE;
    }

    @Override
    public State nextWinState() {
      return PLAYER_2_WIN;
    }
  },
  PLAYER_1_WIN(true),
  PLAYER_2_WIN(true);

  private final boolean terminal;

  State(boolean terminal) {
    this.terminal = terminal;
  }


  public boolean isTerminal() {
    return terminal;
  }

  public State nextMoveState() {
    return this;
  }

  public State nextWinState() {
    return this;
  }

}
