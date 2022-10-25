package com.tlglearning.nim.model;

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
