package org.bristolenergynetwork.retrofit.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.bristolenergynetwork.retrofit.model.Contractor;

public interface ContractorMapper {
  @Insert("insert into contractors values (null,#{name},#{phone},#{address},#{certs})")
  void save(Contractor contractor);

  @Select("select * from contractors where name=#{name} limit 1")
  Contractor findId(String name);
}
