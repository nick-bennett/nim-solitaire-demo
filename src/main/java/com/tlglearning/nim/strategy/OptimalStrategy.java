package com.tlglearning.nim.strategy;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Game.Pile;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OptimalStrategy implements Strategy {

  private final Random rng = new Random();

  @Override
  public int[] selectMove(Game game) {
    int[] move;
    List<Pile> piles = game.getPiles();
    int nimSum = piles
        .stream()
        .mapToInt(Pile::getRemaining)
        .reduce(0, (a, b) -> a ^ b);
    if (nimSum != 0) {
      Iterator<Pile> iterator = piles.iterator();
      int[][] candidateMoves = IntStream.rangeClosed(1, piles.size())
          .mapToObj((pileNumber) -> {
            int[] candidate = {pileNumber, 0};
            Pile pile = iterator.next();
            int remaining = pile.getRemaining();
            int temp = nimSum ^ remaining;
            candidate[1] = (temp < remaining) ? (remaining - temp) : 0;
            return candidate;
          })
          .filter((candidate) -> candidate[1] > 0)
          .toArray(int[][]::new);
      // DONE: 2022-11-08 Pick a non-zero element in candidate moves, and return an array made up
      //  the position of the element, and the value in that position of the array.
      move = candidateMoves[rng.nextInt(candidateMoves.length)];
    } else {
      // DONE: 2022-11-08 Pick a move at random.
      Iterator<Pile> iterator = piles.iterator();
      int[][] candidateMoves = IntStream.rangeClosed(1, piles.size())
          .mapToObj((pileNumber) ->
              new int[]{pileNumber, Math.min(iterator.next().getRemaining(), 1)})
          .filter((candidate) -> candidate[1] > 0)
          .toArray(int[][]::new);
      move = candidateMoves[rng.nextInt(candidateMoves.length)];
    }
    return move;
  }

}
