package com.tlglearning.nim.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PileTest {

  @Test
  void remove_valid() {
    Pile pile = new Pile(10);
    assertEquals(7, pile.remove(3));
    assertEquals(3, pile.getRemoved());
    assertEquals(7, pile.getRemaining());
  }

  @Test
  void remove_invalid() {
    Pile pile = new Pile(10);
    assertThrows(IllegalArgumentException.class, () -> pile.remove(11));
  }

  @Test
  void isEmpty() {
    fail("Test not yet implemented"); // TODO: 2022-10-20 Implement tests (with expected return values of true and false) for isEmpty method of Pile class.
  }

}