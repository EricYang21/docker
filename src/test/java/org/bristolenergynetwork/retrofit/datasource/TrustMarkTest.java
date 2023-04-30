package org.bristolenergynetwork.retrofit.datasource;

import java.util.List;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.junit.jupiter.api.Test;

public class TrustMarkTest {
  @Test
  public void test() throws Exception {
    Tasks task = Tasks.HOT_WATER_CYLINDER_INSTALLATION;
    String postCode = "BS1 4TR";
    TrustMarkHttps trustMarkHttps = new TrustMarkHttps();
    List<Contractor> query = trustMarkHttps.query(task, postCode);
    for (int i = 0; i < query.size(); i++) {
      System.out.println(query.get(i));
    }
  }
}
