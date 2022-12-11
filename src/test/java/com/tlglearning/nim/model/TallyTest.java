package com.tlglearning.nim.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TallyTest {

  Tally tally;

  @BeforeEach
  void setUp() {
    tally = new Tally();
  }

  @Test
  void win() {
    assertEquals(0, tally.getWins());
    tally.win();
    assertEquals(1, tally.getWins());
    assertEquals(0, tally.getLosses());
  }

  @Test
  void lose() {
    assertEquals(0, tally.getLosses());
    tally.lose();
    assertEquals(1, tally.getLosses());
    assertEquals(0, tally.getWins());
  }

}