package com.tlglearning.nim.strategy;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Pile;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OptimalStrategy implements Strategy {

  private final Random rng = new Random();

  @Override
  public int[] selectMove(Game game) {
    List<Pile> piles = game.getPiles();
    int nimSum = getNimSum(piles);
    int[][] candidateMoves =
        (nimSum != 0) ? getOptimalCandidates(piles, nimSum) : getNonOptimalCandidates(piles);
    return candidateMoves[rng.nextInt(candidateMoves.length)];
  }

  private static int getNimSum(List<Pile> piles) {
    return piles
        .stream()
        .mapToInt(Pile::getRemaining)
        .reduce(0, (a, b) -> a ^ b);
  }

  private static int[][] getOptimalCandidates(List<Pile> piles, int nimSum) {
    Iterator<Pile> iterator = piles.iterator();
    return IntStream.rangeClosed(1, piles.size())
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
  }

  private static int[][] getNonOptimalCandidates(List<Pile> piles) {
    Iterator<Pile> iterator = piles.iterator();
    return IntStream.rangeClosed(1, piles.size())
        .mapToObj((pileNumber) ->
            new int[]{pileNumber, Math.min(iterator.next().getRemaining(), 1)})
        .filter((candidate) -> candidate[1] > 0)
        .toArray(int[][]::new);
  }

}
