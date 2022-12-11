package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Tally;
import java.util.ResourceBundle;

public class TallyView {

  private static final String TALLY_FORMAT_KEY = "tally_format";

  private final String tallyFormat;

  public TallyView(ResourceBundle bundle) {
    tallyFormat = bundle.getString(TALLY_FORMAT_KEY);
  }

  public String toString(Tally tally) {
    return String.format(tallyFormat, tally.getWins(), tally.getLosses());
  }

}
