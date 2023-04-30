package org.bristolenergynetwork.retrofit.controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bristolenergynetwork.retrofit.datasource.GreenRegister;
import org.bristolenergynetwork.retrofit.datasource.MCSHttps;
import org.bristolenergynetwork.retrofit.datasource.TrustMarkHttps;
import org.bristolenergynetwork.retrofit.datasource.VerifiedTradeHttps;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Request;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

  @Autowired MCSHttps mcsHttps;

  @Autowired TrustMarkHttps trustMarkHttps;

  @Autowired VerifiedTradeHttps verifiedTradeHttps;

  @PostMapping(path = "/query", produces = "application/json")
  @CrossOrigin
  public List<Contractor> query(@RequestBody Request req) throws Exception {

    String s = StringUtils.upperCase(req.task);
    String s1 = s.toUpperCase();
    String replace = s1.replace(" ", "_");
    Tasks taskNow = null;
    for (Tasks task : Tasks.values()) {
      if (replace.equals(task.name())) {
        taskNow = task;
      }
    }
    List<Contractor> query = null;
    if (req.source.equals("MCS")) {
      query = mcsHttps.query(taskNow, req.postcode);
    } else if (req.source.equals("GreenRegister")) {
      GreenRegister greenRegister = new GreenRegister();
      query = greenRegister.query(taskNow, req.postcode);
    } else if (req.source.equals("TrustMark")) {
      query = trustMarkHttps.query(taskNow, req.postcode);
    } else if (req.source.equals("VerifiedTrade")) {
      query = verifiedTradeHttps.query(taskNow, req.postcode);
    }
    return query;
  }
}
