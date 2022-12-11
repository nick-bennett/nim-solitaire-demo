package com.tlglearning.nim.model;

public class Pile {

  private static final String BAD_SIZE_FORMAT = "Invalid size: %d. Initial size must be positive.";
  private static final String EXCESS_REMOVAL_FORMAT =
      "Invalid quantity: %d. Quantity to remove must not exceed number remaining (%d).";
  private static final String TO_STRING_FORMAT = "%s{removed=%d, remaining=%d}";

  private int removed;
  private int remaining;

  public Pile(int size) throws IllegalArgumentException {
    if (size <= 0) {
      throw new IllegalArgumentException(String.format(BAD_SIZE_FORMAT, size));
    }
    removed = 0;
    remaining = size;
  }

  int remove(int quantity) throws IllegalArgumentException {
    if (quantity > remaining) {
      throw new IllegalArgumentException(String.format(EXCESS_REMOVAL_FORMAT, quantity, remaining));
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
    return String.format(TO_STRING_FORMAT, getClass().getSimpleName(), removed, remaining);
  }

}
