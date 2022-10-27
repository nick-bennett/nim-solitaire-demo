package com.tlglearning.nim.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class GameTest {

  @Test
  void constructor_badState() {
    assertThrows(IllegalArgumentException.class,
        () -> new Game(State.PLAYER_1_WIN, new int[]{3, 5, 7}));
  }

  @Test
  void constructor_badPileSizes() {
    assertThrows(IllegalArgumentException.class,
        () -> new Game(State.PLAYER_1_MOVE, new int[]{-3, 5, 7}));
  }

  @Test
  void play_valid() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    Pile pile = game.getPiles().get(0);
    game.play(pile, pile.getRemaining());
    assertTrue(pile.isEmpty());
  }

  @Test
  void play_invalid() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    Pile pile = game.getPiles().get(0);
    assertThrows(IllegalArgumentException.class, () -> game.play(pile, pile.getRemaining() + 1));
  }

  @Test
  void play_alreadyFinished() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    for (Pile pile : game.getPiles()) {
      game.play(pile, pile.getRemaining());
    }
    assertThrows(IllegalStateException.class, () -> game.play(game.getPiles().get(0), 1));
  }

  @Test
  void isFinished_some() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    for (Pile pile : game.getPiles()) {
      game.play(pile, pile.getRemaining() - 1);
    }
    assertFalse(game.isFinished());
  }

  @Test
  void isFinished_none() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    for (Pile pile : game.getPiles()) {
      game.play(pile, pile.getRemaining());
    }
    assertTrue(game.isFinished());
  }

}