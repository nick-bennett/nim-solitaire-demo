package com.tlglearning.nim.view;

import static org.junit.jupiter.api.Assertions.*;

import com.tlglearning.nim.model.Pile;
import org.junit.jupiter.api.Test;

class PileViewTest {

  @Test
  void testToString() {
    Pile pile = new Pile(15);
    pile.remove(5);
    System.out.println(new PileView().toString(pile));
  }
}