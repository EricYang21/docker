package org.bristolenergynetwork.retrofit;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.bristolenergynetwork.retrofit.datasource.DataSource;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.DataSourceType;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Router {
  public Router() {}

  @PostMapping(path = "/query", produces = "application/json")
  public List<Contractor> query(@RequestBody QueryRequest req) {
    List<DataSource> sources = req.sources.stream().map(DataSourceType::create).toList();
    if (sources.isEmpty()) {
      // if no sources were specified, use the default (non-test) ones
      sources = DataSource.getDefaultSources();
    }
    List<Contractor> contractors = new ArrayList<>();
    for (DataSource source : sources) {
      for (Tasks task : req.tasks) {
        try {
          contractors.addAll(source.query(task, req.postCode));
        } catch (Exception e) {
          System.out.println("Failed to query datasource: " + e.getMessage());
        }
      }
    }

    return contractors;
  }
}

class QueryRequest {
  public EnumSet<DataSourceType> sources;
  public EnumSet<Tasks> tasks;
  public String postCode;
}
