package com.tlglearning.nim.view;

import static org.junit.jupiter.api.Assertions.*;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.State;
import org.junit.jupiter.api.Test;

class GameViewTest {

  @Test
  void testToString() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    GameView view = new GameView();
    System.out.println(view.toString(game));
  }

}