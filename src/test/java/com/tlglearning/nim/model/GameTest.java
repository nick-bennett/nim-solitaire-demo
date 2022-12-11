package com.tlglearning.nim.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

  private int[] validPileSizes;
  private int[] invalidPileSizes;

  @BeforeEach
  void setUp() {
    validPileSizes = new int[]{3, 5, 7};
    invalidPileSizes = new int[]{0, 5, 7};
  }

  @Test
  void constructor_badState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Game(State.PLAYER_1_WIN, validPileSizes));
  }

  @Test
  void constructor_badPileSizes() {
    assertThrows(IllegalArgumentException.class,
        () -> new Game(State.PLAYER_1_MOVE, invalidPileSizes));
  }

  @Test
  void play_valid() {
    Game game = new Game(State.PLAYER_1_MOVE, validPileSizes);
    Pile pile = game.getPiles().get(0);
    game.play(pile, pile.getRemaining());
    assertTrue(pile.isEmpty());
  }

  @Test
  void play_invalid() {
    Game game = new Game(State.PLAYER_1_MOVE, validPileSizes);
    Pile pile = game.getPiles().get(0);
    assertThrows(IllegalArgumentException.class, () -> game.play(pile, pile.getRemaining() + 1));
  }

  @Test
  void play_alreadyFinished() {
    Game game = new Game(State.PLAYER_1_MOVE, validPileSizes);
    for (Pile pile : game.getPiles()) {
      game.play(pile, pile.getRemaining());
    }
    assertThrows(IllegalStateException.class, () -> game.play(game.getPiles().get(0), 1));
  }

  @Test
  void isFinished_some() {
    Game game = new Game(State.PLAYER_1_MOVE, validPileSizes);
    for (Pile pile : game.getPiles()) {
      game.play(pile, pile.getRemaining() - 1);
    }
    assertFalse(game.isFinished());
  }

  @Test
  void isFinished_none() {
    Game game = new Game(State.PLAYER_1_MOVE, validPileSizes);
    for (Pile pile : game.getPiles()) {
      game.play(pile, pile.getRemaining());
    }
    assertTrue(game.isFinished());
  }

  @DisplayName("GameTest.PileTest")
  static class PileTest {

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
}