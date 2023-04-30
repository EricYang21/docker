package org.bristolenergynetwork.retrofit.datasource;

import java.util.List;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.junit.jupiter.api.Test;

public class VerifiedTest {
  @Test
  public void test() throws Exception {
    Tasks task = Tasks.LOFT_INSULATION;
    String postCode = "BS1 4TR";
    VerifiedTradeHttps verifiedTradeHttps = new VerifiedTradeHttps();
    List<Contractor> query = verifiedTradeHttps.query(task, postCode);
    for (int i = 0; i < query.size(); i++) {
      System.out.println(query.get(i));
    }
  }
}
