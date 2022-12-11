package com.tlglearning.nim.model;

public class Session {

  private int wins;
  private int losses;

  public void win() {
    wins++;
  }

  public void lose() {
    losses++;
  }

  public int getWins() {
    return wins;
  }

  public int getLosses() {
    return losses;
  }

  public int getTotal() {
    return wins + losses;
  }

}
