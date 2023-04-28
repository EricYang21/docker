package org.bristolenergynetwork.retrofit.controller;

import java.util.List;
import java.util.concurrent.Callable;
import org.bristolenergynetwork.retrofit.datasource.DataSource;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;

public class ThreadTask implements Callable {
  String postcode;
  Tasks task;
  DataSource dataSource;

  public ThreadTask(String postcode, Tasks task, DataSource dataSource) {
    this.postcode = postcode;
    this.task = task;
    this.dataSource = dataSource;
  }

  @Override
  public Object call() throws Exception {
    List<Contractor> query = null;
    System.out.println(Thread.currentThread().getName());

    query = dataSource.query(task, postcode);
    if (query != null) {
      for (Contractor contractor : query) {
        if (contractor == null) {
          query.remove(contractor);
        }
      }
    }

    return query;
  }
}
