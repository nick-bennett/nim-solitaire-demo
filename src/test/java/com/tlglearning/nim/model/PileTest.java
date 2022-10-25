package com.tlglearning.nim.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PileTest {

  private static final int PILE_SIZE = 10;

  @Test
  void constructor_invalid() {
    assertThrows(IllegalArgumentException.class, () -> new Pile(-1));
  }

  @Test
  void remove_valid() {
    Pile pile = new Pile(PILE_SIZE);
    int quantity = 3;
    int expectedRemaining = PILE_SIZE - quantity;
    assertEquals(expectedRemaining, pile.remove(quantity));
    assertEquals(quantity, pile.getRemoved());
    assertEquals(expectedRemaining, pile.getRemaining());
  }

  @Test
  void remove_invalid() {
    Pile pile = new Pile(PILE_SIZE);
    assertThrows(IllegalArgumentException.class, () -> pile.remove(PILE_SIZE + 1));
  }

  @Test
  void isEmpty_some() {
    Pile pile = new Pile(PILE_SIZE);
    pile.remove(PILE_SIZE - 1);
    assertFalse(pile.isEmpty());
  }

  @Test
  void isEmpty_none() {
    Pile pile = new Pile(PILE_SIZE);
    pile.remove(PILE_SIZE);
    assertTrue(pile.isEmpty());
  }

}
