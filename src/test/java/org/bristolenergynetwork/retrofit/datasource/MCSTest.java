package org.bristolenergynetwork.retrofit.datasource;

import java.util.List;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.junit.jupiter.api.Test;

public class MCSTest {

  @Test
  public void test() throws Exception {
    Tasks task = Tasks.BOILER_REPAIR;
    String postCode = "BS1 4TR";

    MCSHttps mcsHttps = new MCSHttps();
    List<Contractor> query = mcsHttps.query(task, postCode);
    for (Contractor value : query) {
      System.out.println("value = " + value);
    }
  }
}
