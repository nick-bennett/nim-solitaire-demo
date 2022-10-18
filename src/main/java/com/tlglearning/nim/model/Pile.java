package com.tlglearning.nim.model;

public class Pile {

  private int removed;
  private int remaining;

  // DONE: 2022-10-18 Define constructor that initializes removed to 0 and sets remaining to the value of a parameter.

  public Pile(int size) {
    removed = 0;
    remaining = size;
  }

  // DONE: 2022-10-18 Define methods shown in nim-classes.svg as stubs.

  public int remove(int quantity) {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-18 Implement remove method.
  }

  public boolean isEmpty() {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: 2022-10-18 Implement isEmpty method.
  }

  // DONE: 2022-10-18 Generate "getters" (accessors) for removed and remaining.

  public int getRemoved() {
    return removed;
  }

  public int getRemaining() {
    return remaining;
  }

  // TODO: 2022-10-18 Create a test class for Pile.

}
