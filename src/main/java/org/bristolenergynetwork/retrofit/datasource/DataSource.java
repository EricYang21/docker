package org.bristolenergynetwork.retrofit.datasource;

import java.util.List;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;

public interface DataSource {
  public List<Contractor> query(Tasks task, String postCode) throws Exception;
}
