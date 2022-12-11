package com.tlglearning.nim.model;

public final class Move {

  private final int pileNumber; // 1-based pile number, used by MoveView.
  private final Pile pile;
  private final int quantity;

  public Move(int pileNumber, Pile pile, int quantity) {
    this.pileNumber = pileNumber;
    this.pile = pile;
    this.quantity = quantity;
  }

  public int getPileNumber() {
    return pileNumber;
  }

  public Pile getPile() {
    return pile;
  }

  public int getQuantity() {
    return quantity;
  }

}
