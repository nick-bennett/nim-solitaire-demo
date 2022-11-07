package com.tlglearning.nim.strategy;

import com.tlglearning.nim.model.Game;

public interface Strategy {

  int[] selectMove(Game game);

}
