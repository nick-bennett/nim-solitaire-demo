package com.tlglearning.nim.model;

public class Pile {

  private static final String BAD_SIZE_FORMAT = "Invalid size: %d; must be non-negative.";
  private static final String BAD_REMOVE_QUANTITY_FORMAT =
      "Invalid quantity: %d; must not exceed number remaining (%d).";

  private int removed;
  private int remaining;

  public Pile(int size) {
    if (size < 0) {
      throw new IllegalArgumentException(String.format(BAD_SIZE_FORMAT, size));
    }
    removed = 0;
    remaining = size;
  }

  public int remove(int quantity) {
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

}
