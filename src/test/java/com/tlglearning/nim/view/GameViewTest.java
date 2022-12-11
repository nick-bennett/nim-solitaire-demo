package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Move;
import com.tlglearning.nim.model.Pile;
import com.tlglearning.nim.model.State;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;
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
    // This method doesn't assert anything about the result of the toString(Game) method;
    // it is only being used to display output for the programmer to check visually.
    Game game = new Game(State.PLAYER_1_MOVE, INITIAL_PILE_SIZES);
    System.out.println(new GameView(bundle).toString(game));
  }

  @Test
  void toString_inProgress() {
    // This method doesn't assert anything about the result of the toString(Game) method;
    // it is only being used to display output for the programmer to check visually.
    Game game = new Game(State.PLAYER_1_MOVE, INITIAL_PILE_SIZES);
    Random random = new Random();
    List<Pile> piles = game.getPiles();
    Iterator<Pile> iterator = piles.iterator();
    IntStream.rangeClosed(1, piles.size())
            .forEach((pileNumber) -> {
              Pile pile = iterator.next();
              Move move = new Move(pileNumber, pile, 1 + random.nextInt(pile.getRemaining()));
              game.play(move);
            });
    System.out.println(new GameView(bundle).toString(game));
  }

}