package org.bristolenergynetwork.retrofit.controller;

import org.bristolenergynetwork.retrofit.mappers.ContractorMapper;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {

  @Autowired ContractorMapper contractorMapper;

  public void save(Contractor contractor) {
    contractorMapper.save(contractor);
  }

  public void checkEqual(Contractor contractor) {}

  public Integer findId(String name) {
    Contractor contractor = contractorMapper.findId(name);
    return contractor.getId();
  }
}
