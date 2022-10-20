package com.tlglearning.nim.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PileTest {

  @Test
  void constructor_invalid() {
    assertThrows(IllegalArgumentException.class, () -> new Pile(-1));
  }

  @Test
  void remove_valid() {
    Pile pile = new Pile(10);
    assertAll(
        () -> assertEquals(7, pile.remove(3)),
        () -> assertEquals(3, pile.getRemoved()),
        () -> assertEquals(7, pile.getRemaining())
    );
  }

  @Test
  void remove_invalid() {
    Pile pile = new Pile(10);
    assertThrows(IllegalArgumentException.class, () -> pile.remove(11));
  }

  @Test
  void isEmpty_some() {
    Pile pile = new Pile(10);
    pile.remove(9);
    assertFalse(pile.isEmpty());
  }

  @Test
  void isEmpty_none() {
    Pile pile = new Pile(10);
    pile.remove(10);
    assertTrue(pile.isEmpty());
  }

}