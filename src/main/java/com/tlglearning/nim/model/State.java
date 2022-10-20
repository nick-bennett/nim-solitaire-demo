package com.tlglearning.nim.model;

public enum State {

  PLAYER_1_MOVE,
  PLAYER_2_MOVE,
  PLAYER_1_WIN,
  PLAYER_2_WIN;

  public boolean isTerminal() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public State nextMoveState() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  public State nextWinState() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

}
