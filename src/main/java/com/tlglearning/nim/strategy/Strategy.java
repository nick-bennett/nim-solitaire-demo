package com.tlglearning.nim.strategy;

import com.tlglearning.nim.model.Game;
import com.tlglearning.nim.model.Move;

public interface Strategy {

  Move selectMove(Game game);

}
