package org.bristolenergynetwork.retrofit.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.bristolenergynetwork.retrofit.model.BrowseHistory;

public interface BrowseHistoryMapper {
  @Select("select * from history where userId=#{id} limit 1")
  List<BrowseHistory> findHistoryById(Integer id);

  @Insert("insert into history values (#{userId},#{link})")
  Boolean save(BrowseHistory browseHistory);
}
