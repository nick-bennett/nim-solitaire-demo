package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.Pile;
import com.tlglearning.nim.model.Game.State;
import java.util.Random;
import org.junit.jupiter.api.Test;

class GameViewTest {

  @Test
  void toString_initial() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    System.out.println(new GameView().toString(game));
  }

  @Test
  void toString_inProgress() {
    Game game = new Game(State.PLAYER_1_MOVE, new int[]{3, 5, 7});
    Random random = new Random();
    game
        .getPiles()
        .forEach((pile) -> game.play(pile, 1 + random.nextInt(pile.getRemaining())));
    System.out.println(new GameView().toString(game));
  }

  static class PileViewTest {

    @Test
    void testToString() {
      Pile pile = new Pile(15);
      pile.remove(5);
      System.out.println(new GameView.PileView().toString(pile));
    }
  }
}