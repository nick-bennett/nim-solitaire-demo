package com.tlglearning.nim.view;

import com.tlglearning.nim.model.Session;
import java.util.ResourceBundle;

public class SessionView {

  private static final String TALLY_FORMAT_KEY = "tally_format";

  private final String tallyFormat;

  public SessionView(ResourceBundle bundle) {
    tallyFormat = bundle.getString(TALLY_FORMAT_KEY);
  }

  public String toString(Session session) {
    return String.format(tallyFormat, session.getWins(), session.getLosses());
  }

}
