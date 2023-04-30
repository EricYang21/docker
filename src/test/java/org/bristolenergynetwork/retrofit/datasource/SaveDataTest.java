package org.bristolenergynetwork.retrofit.datasource;

import java.util.List;
import org.bristolenergynetwork.retrofit.controller.ContractorController;
import org.bristolenergynetwork.retrofit.controller.SaveAllContractors;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaveDataTest {
  @Autowired ContractorController contractorController;

  @Test
  public void test() throws Exception {
    Tasks task = Tasks.SOLAR_PV;
    String postCode = "BS1 4TR";

    MCSHttps mcsHttps = new MCSHttps();
    List<Contractor> query = mcsHttps.query(task, postCode);
    for (Contractor value : query) {
      System.out.println("value = " + value);
    }

    for (Contractor value : query) {
      contractorController.save(value);
    }
  }

  @Test
  public void testForGreenRegisterResult() throws Exception {
    SaveAllContractors saveAllContractors = new SaveAllContractors();
    List<Contractor> contractorList = saveAllContractors.multipleThread();
    System.out.println("contractorList = " + contractorList);
  }

  @Test
  public void testForGreenRegisterSave() throws Exception {
    SaveAllContractors saveAllContractors = new SaveAllContractors();
    List<Contractor> contractorList = saveAllContractors.multipleThread();
    for (Contractor contractor : contractorList) {
      contractorController.save(contractor);
    }
  }

  @Test
  public void test3() throws Exception {
    SaveAllContractors saveAllContractors = new SaveAllContractors();
    List<Contractor> contractorList = saveAllContractors.multipleThread();
    for (Contractor c : contractorList) {
      contractorController.save(c);
    }
  }

  @Test
  public void test4() throws Exception {
    Tasks heatPumpInstallation = Tasks.CHIMNEY_CLEANING;
    TrustMarkHttps trustMarkHttps = new TrustMarkHttps();
    List<Contractor> query = trustMarkHttps.query(heatPumpInstallation, "BS99 1AA");
    System.out.println("query = " + query);
  }

  @Test
  public void test5() throws Exception {
    SaveAllContractors saveAllContractors = new SaveAllContractors();
    List<Contractor> contractorList = saveAllContractors.multipleThread();
    for (Contractor c : contractorList) {
      contractorController.save(c);
    }
  }
}
