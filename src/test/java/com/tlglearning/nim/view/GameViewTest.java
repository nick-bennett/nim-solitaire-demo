package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.State;
import java.util.Random;
import java.util.ResourceBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameViewTest {

  private static final int[] INITIAL_PILE_SIZES = {3, 5, 7};

  private ResourceBundle bundle;

  @BeforeEach
  void setUp() {
    bundle = ResourceBundle.getBundle("ui-strings");
  }

  @Test
  void toString_initial() {
    Game game = new Game(State.PLAYER_1_MOVE, INITIAL_PILE_SIZES);
    System.out.println(new GameView(bundle).toString(game));
  }

  @Test
  void toString_inProgress() {
    Game game = new Game(State.PLAYER_1_MOVE, INITIAL_PILE_SIZES);
    Random random = new Random();
    game
        .getPiles()
        .forEach((pile) -> game.play(pile, 1 + random.nextInt(pile.getRemaining())));
    System.out.println(new GameView(bundle).toString(game));
  }

}