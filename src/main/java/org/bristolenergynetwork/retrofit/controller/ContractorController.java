package org.bristolenergynetwork.retrofit.controller;

import org.bristolenergynetwork.retrofit.model.Contractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractorController {

  @Autowired private ContractorService contractorService;

  public void save(Contractor contractor) {

    contractorService.save(contractor);
  }

  public Integer findId(String name) {
    Integer id = contractorService.findId(name);
    return id;
  }
}
