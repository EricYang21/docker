package org.bristolenergynetwork.retrofit.controller;

import cn.hutool.core.util.ReUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.StringUtils;
import org.bristolenergynetwork.retrofit.datasource.DataSource;
import org.bristolenergynetwork.retrofit.datasource.VerifiedTradeHttps;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;

public class SaveAllContractors {

  @Autowired ContractorController contractorController;

  public ArrayList<String> searchAllPostcode() throws IOException {
    ArrayList<String> allPostcode = new ArrayList<>();
    ArrayList<Integer> number = new ArrayList<>();
    for (int i = 1; i < 12; i++) {
      number.add(i);
    }
    for (int i = 13; i < 17; i++) {
      number.add(i);
    }
    for (int i = 20; i < 33; i++) {
      number.add(i);
    }
    for (int i = 34; i < 38; i++) {
      number.add(i);
    }
    for (int i = 39; i < 42; i++) {
      number.add(i);
    }
    for (int i = 48; i <= 49; i++) {
      number.add(i);
    }
    number.add(99);

    for (Integer i : number) {

      URL url = new URL("https://www.postcodearea.co.uk/postaltowns/bristol/bs" + i + "/");
      BufferedReader bufferedReader = null;
      StringBuffer response = new StringBuffer();

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      // start connection
      connection.setDoInput(true);
      connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
      connection.setRequestMethod("GET");
      connection.setDoOutput(true);
      connection.connect();

      // get respCode and get response
      int respCode = connection.getResponseCode();
      if (respCode == HttpURLConnection.HTTP_OK) {}
      bufferedReader =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
      String linePhone = null;
      while (null != (linePhone = bufferedReader.readLine())) {
        response.append(linePhone);
      }

      String strResponse = response.toString();

      ArrayList<String> select1 =
          ReUtil.findAll(
              "<a class=\"btn btn-primary btn-block btn-sm\" href=([\\s\\S]*)</a></div>",
              strResponse,
              0,
              new ArrayList<String>());
      String regex = "BS\\w+ \\w\\w\\w<?";
      ArrayList<String> select2 = ReUtil.findAll(regex, select1.get(0), 0, new ArrayList<>());
      ArrayList<String> select3 = new ArrayList<>();
      for (String value : select2) {
        String s = StringUtils.removeEnd(value, "<");
        select3.add(s); // have all postcode
      }
      allPostcode.addAll(select3);
    }

    return allPostcode;
  }

  //  public void searchAllCon() throws Exception {
  //    DataSource mcsHttps = new MCSHttps();
  //    ArrayList<DataSource> dataSourceArrayList = new ArrayList<>();
  //    dataSourceArrayList.add(mcsHttps);
  //    List<Contractor> contractorList = new ArrayList<>();ArrayList<String> postcode =
  // searchAllPostcode();
  //    ArrayList<String> selectPostcode = new ArrayList<>();
  //    selectPostcode.add("BS1 1AA");
  //    selectPostcode.add("BS2 0AF");
  //    selectPostcode.add("BS3 1AA");
  //    selectPostcode.add("BS4 1AA");
  //    selectPostcode.add("BS5 0AA");
  //    selectPostcode.add("BS6 5AA");
  //    selectPostcode.add("BS7 0AA");
  //    selectPostcode.add("BS8 1AA");
  //    selectPostcode.add("BS9 0AA");
  //    selectPostcode.add("BS10 5AA");
  //    selectPostcode.add("BS11 0AA");
  //    selectPostcode.add("BS13 0AA");
  //    selectPostcode.add("BS14 0AA");
  //    selectPostcode.add("BS15 0AA");
  //    selectPostcode.add("BS16 0AA");
  //    selectPostcode.add("BS20 0AA");
  //    selectPostcode.add("BS21 5AB");
  //    selectPostcode.add("BS22 0AA");
  //    selectPostcode.add("BS23 1AA");
  //    selectPostcode.add("BS24 0AA");
  //    selectPostcode.add("BS25 1AA");
  //    selectPostcode.add("BS26 2AA");
  //    selectPostcode.add("BS27 3AA");
  //    selectPostcode.add("BS28 4AA");
  //    selectPostcode.add("BS29 6AA");
  //    selectPostcode.add("BS30 5AA");
  //    selectPostcode.add("BS31 1AA");
  //    selectPostcode.add("BS32 0AF");
  //    selectPostcode.add("BS34 5AA");
  //    selectPostcode.add("BS35 1AB");
  //    selectPostcode.add("BS36 1AA");
  //    selectPostcode.add("BS37 0AA");
  //    selectPostcode.add("BS39 4AA");
  //    selectPostcode.add("BS40 5AA");
  //    selectPostcode.add("BS41 8BA");
  //    selectPostcode.add("BS48 1AB");
  //    selectPostcode.add("BS49 4AA");
  //    selectPostcode.add("BS99 1AA");
  //    for (String post : selectPostcode) {
  //
  //      for (DataSource data : dataSourceArrayList) {
  //        for (Tasks task : Tasks.values()) {
  //          if (task == Tasks.HEAT_PUMP_REPAIR
  //              || task == Tasks.HEAT_PUMP_INSTALLATION
  //              || task == Tasks.BATTERY_STORAGE){
  //          List<Contractor> query = data.query(task, post);
  //            if (query !=null) {
  //              for (Contractor con : query) {
  //                if (con != null) {
  //                  contractorList.add(con);
  //                }
  //              }
  //          }
  //        }
  // }     }
  //    }
  //    System.out.println("contractorList = " + contractorList);
  //  }

  public List<Contractor> multipleThread() throws Exception {
    ExecutorService threadPool = Executors.newFixedThreadPool(120);

    // DataSource mcsHttps = new MCSHttps();
    // DataSource trustMarkHttps = new TrustMarkHttps();
    DataSource verifiedTradeHttps = new VerifiedTradeHttps();
    // DataSource greenRegister = new GreenRegister();
    ArrayList<DataSource> dataSourceArrayList = new ArrayList<>();

    //    dataSourceArrayList.add(mcsHttps);
    //  dataSourceArrayList.add(trustMarkHttps);
    dataSourceArrayList.add(verifiedTradeHttps);
    //  dataSourceArrayList.add(greenRegister);
    List<Contractor> contractors = new ArrayList<>();
    ArrayList<String> postcodes = searchAllPostcode();
    ArrayList<String> selectPostcode = new ArrayList<>();
    selectPostcode.add("BS1 1AA");
    selectPostcode.add("BS2 0AF");
    selectPostcode.add("BS3 1AA");
    selectPostcode.add("BS4 1AA");
    selectPostcode.add("BS5 0AA");
    selectPostcode.add("BS6 5AA");
    selectPostcode.add("BS7 0AA");
    selectPostcode.add("BS8 1AA");
    selectPostcode.add("BS9 0AA");
    selectPostcode.add("BS10 5AA");
    selectPostcode.add("BS11 0AA");
    selectPostcode.add("BS13 0AA");
    selectPostcode.add("BS14 0AA");
    selectPostcode.add("BS15 0AA");
    selectPostcode.add("BS16 0AA");
    selectPostcode.add("BS20 0AA");
    selectPostcode.add("BS21 5AB");
    selectPostcode.add("BS22 0AA");
    selectPostcode.add("BS23 1AA");
    selectPostcode.add("BS24 0AA");
    selectPostcode.add("BS25 1AA");
    selectPostcode.add("BS26 2AA");
    selectPostcode.add("BS27 3AA");
    selectPostcode.add("BS28 4AA");
    selectPostcode.add("BS29 6AA");
    selectPostcode.add("BS30 5AA");
    selectPostcode.add("BS31 1AA");
    selectPostcode.add("BS32 0AF");
    selectPostcode.add("BS34 5AA");
    selectPostcode.add("BS35 1AB");
    selectPostcode.add("BS36 1AA");
    selectPostcode.add("BS37 0AA");
    selectPostcode.add("BS39 4AA");
    selectPostcode.add("BS40 5AA");
    selectPostcode.add("BS41 8BA");
    selectPostcode.add("BS48 1AB");
    selectPostcode.add("BS49 4AA");
    selectPostcode.add("BS99 1AA");
    for (DataSource data : dataSourceArrayList) {
      for (String postcode : selectPostcode) {
        for (Tasks task : Tasks.values()) {
          ThreadTask threadTask = new ThreadTask(postcode, task, data);
          Future<List<Contractor>> future = threadPool.submit(threadTask);
          List<Contractor> contractorList = future.get();
          if (contractorList != null) {
            for (Contractor contractor : contractorList) {
              if (contractor == null) {
                contractorList.remove(contractor);
              } else {
                contractors.add(contractor);
              }
            }
          }
        }
      }
    }
    Set set = new HashSet();
    List<Contractor> contractors1 = new ArrayList<>();
    set.addAll(contractors);
    contractors1.addAll(set);
    return contractors1;
  }
}
