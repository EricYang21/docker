package org.bristolenergynetwork.retrofit.datasource;

import org.bristolenergynetwork.retrofit.controller.SaveAllContractors;
import org.junit.jupiter.api.Test;

public class ImportTest {
  // here is a test
  @Test
  public void test() throws Exception {
    SaveAllContractors saveAllContractors = new SaveAllContractors();
    saveAllContractors.multipleThread();
  }
}
