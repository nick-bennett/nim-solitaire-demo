package com.tlglearning.nim.model;

public class Pile {

  private int removed;
  private int remaining;

  public Pile(int remaining) {
    removed = 0;
    this.remaining = remaining;
  }

  public int remove(int quantity) {
    if (quantity > remaining) {
      throw new IllegalArgumentException("Quantity to remove must not exceed quantity remaining.");
    }
    removed += quantity;
    return (remaining -= quantity);
  }

  public boolean isEmpty() {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-20 Implement isEmpty method.
  }

  public int getRemoved() {
    return removed;
  }

  public int getRemaining() {
    return remaining;
  }

}
