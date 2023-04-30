package org.bristolenergynetwork.retrofit.datasource;

import java.util.List;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;

public class TestSource implements DataSource {
  public List<Contractor> query(Tasks task, String postCode) throws Exception {
    Contractor sally =
        new Contractor(3, "Sally Plumber", "01234 567890", "Heat Geek", "Too far away");
    Contractor tim =
        new Contractor(4, "Tim from down the road", "09876 543210", "", "Down the road");
    return List.of(sally, tim);
  }
}
