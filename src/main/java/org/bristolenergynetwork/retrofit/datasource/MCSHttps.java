package org.bristolenergynetwork.retrofit.datasource;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONObject;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bristolenergynetwork.retrofit.controller.ContractorController;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MCSHttps implements DataSource {
  @Autowired ContractorController contractorController;
  // for mcs website
  @Override
  public List<Contractor> query(Tasks task, String postCode) throws Exception {
    // firstly, we need to deal with the task
    String selectedTechnologies = null;
    if (task == Tasks.HEAT_PUMP_REPAIR || task == Tasks.HEAT_PUMP_INSTALLATION) {
      selectedTechnologies = "ASHP,EAHP,GAHP,WSHP,SAHP";
      //
      // AIR_SOURCE_HEAT_PUMP,EXHAUST_AIR_HEAT_PUMP,GAS_ABSORBTION_HEAT_PUMP,GROUND_WATER_SOURCE_HEAT_PUMP,
      //    SOLAR_ASSISTED_HEAT_PUMP
    } else if (task == Tasks.BATTERY_STORAGE) {
      selectedTechnologies = "Battery";
    } else if (task == Tasks.MICRO_CHP) {
      selectedTechnologies = "MicroCHP";
    } else if (task == Tasks.SOLAR_PV) {
      selectedTechnologies = "SolarPV";
    } else if (task == Tasks.SOLAR_THERMAL) {
      selectedTechnologies = "SolarThermal";
    } else if (task == Tasks.WINDTURBINE) {
      selectedTechnologies = "WindTurbine";
    } else if (task == Tasks.BOILER_REPAIR || task == Tasks.BOILER_INSTALLATION) {
      selectedTechnologies = "Biomass";
    } else {
      return null;
    }

    String[] postCodeList = postCode.split(" ");
    // get request, so it doesn't need to Json Request.
    CloseableHttpClient httpclient = HttpClients.createDefault();
    String strResponseLA;
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet =
        new HttpGet(
            "https://findthatpostcode.uk/postcodes/"
                + postCodeList[0]
                + "%20"
                + postCodeList[1]
                + ".html");
    httpGet.setConfig(RequestConfig.DEFAULT);
    httpGet.addHeader(
        "User-Agent",
        "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)"
            + " Chrome/39.0.2171.95 Safari/537.36");
    CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
    HttpEntity httpEntity = httpResponse.getEntity();
    strResponseLA = EntityUtils.toString(httpEntity);
    httpResponse.close();

    ArrayList<String> select1 =
        ReUtil.findAll(
            "<a href='https://tools.wmflabs.org/geohack([\\s\\S]*)<h2 class=\"header-font mt0"
                + " mb1\">",
            strResponseLA,
            0,
            new ArrayList<String>());
    if (select1.size() == 0) {
      return null;
    }
    ArrayList<String> select2 =
        ReUtil.findAll(">([\\s\\S]*)</a", select1.get(0), 0, new ArrayList<String>());
    String longitude = null;
    String latitude = null;
    for (String value : select2) {
      String s = StringUtils.removeStart(value, ">");
      String s1 = StringUtils.removeEnd(s, "</a").trim();
      String[] split = s1.split(",");
      latitude = split[0].trim();
      longitude = split[1].trim();
    }

    URL url =
        new URL(
            "https://mcs-website-widget.solsticecloud.com//Search/Search_Installers_TypeAndLocation");
    BufferedReader br = null;
    StringBuffer response = new StringBuffer();

    JSONObject body = new JSONObject();
    body.put("nearest", "10");
    body.put(
        "selectedRegions",
        "Eastern,EastMidlands,London,NorthEast,NorthWest,SouthEast,SouthWest,WestMidlands,YorkshireHumberside");
    body.put("selectedTechnologies", selectedTechnologies);
    body.put("sortMode", 2);
    body.put("sourceLat", latitude);
    body.put("sourceLng", longitude);

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoInput(true);
    conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.connect();
    String stringBody = body.toJSONString();
    BufferedWriter writer =
        new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
    writer.write(stringBody);
    writer.close();

    int respCode = conn.getResponseCode();
    if (respCode == HttpURLConnection.HTTP_OK) {
      System.out.println("pass");
    } else {
      throw new Exception("API returned " + respCode);
    }

    br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
    String line = null;
    while (null != (line = br.readLine())) {
      response.append(line);
    }

    String[] allSplit = response.toString().split(",\\\\");

    List<String> name = new ArrayList<>();
    List<String> addressLine1 = new ArrayList<>();
    List<String> addressLine2 = new ArrayList<>();
    List<String> addressLine3 = new ArrayList<>();
    List<String> county = new ArrayList<>();
    List<String> country = new ArrayList<>();
    List<String> telephone = new ArrayList<>();

    List<Contractor> contractorList = new ArrayList<>();

    for (int i = 0; i < allSplit.length; i++) {

      if (allSplit[i].contains("Name") && !allSplit[i].contains(("ContactName"))) {
        String nameLine = allSplit[i];
        String[] firstSplitName = nameLine.split("\\\\");
        String firstName = firstSplitName[2];
        String[] SecondSplitName = firstName.split("\"");
        String finalName = SecondSplitName[1];
        name.add(finalName);
      }

      if (allSplit[i].contains("AddressLine1")) {
        String AddressLine1Line = allSplit[i];
        String[] firstSplitAddressLine1 = AddressLine1Line.split("\\\\");
        if (firstSplitAddressLine1.length <= 2) {
          addressLine1.add("null");
        } else {
          String firstAddress1 = firstSplitAddressLine1[2];
          String[] secondSplitAddress1 = firstAddress1.split("\"");
          String finalAddress1 = secondSplitAddress1[1];
          addressLine1.add(finalAddress1);
        }
      }

      if (allSplit[i].contains("AddressLine2")) {
        String AddressLine2Line = allSplit[i];
        String[] firstSplitAddressLine2 = AddressLine2Line.split("\\\\");
        if (firstSplitAddressLine2.length <= 2) {
          addressLine2.add("null");
        } else {
          String firstAddress2 = firstSplitAddressLine2[2];
          String[] secondSplitAddress2 = firstAddress2.split("\"");
          String finalAddress2 = secondSplitAddress2[1];
          addressLine2.add(finalAddress2);
        }
      }

      if (allSplit[i].contains("AddressLine3")) {
        String AddressLine3Line = allSplit[i];
        String[] firstSplitAddressLine3 = AddressLine3Line.split("\\\\");
        if (firstSplitAddressLine3.length <= 2) {
          addressLine3.add("null");
        } else {
          String firstAddress3 = firstSplitAddressLine3[2];
          String[] secondSplitAddress3 = firstAddress3.split("\"");
          String finalAddress3 = secondSplitAddress3[1];
          addressLine3.add(finalAddress3);
        }
      }

      if (allSplit[i].contains("County")) {
        String countyLine = allSplit[i];
        String[] firstSplitcounty = countyLine.split("\\\\");
        if (firstSplitcounty.length <= 2) {
          county.add("null");
        } else {
          String firstcounty = firstSplitcounty[2];
          String[] secondSplitcounty = firstcounty.split("\"");
          String finalcounty = secondSplitcounty[1];
          county.add(finalcounty);
        }
      }

      if (allSplit[i].contains("Country")) {
        String countryLine = allSplit[i];
        String[] firstSplitcountry = countryLine.split("\\\\");
        if (firstSplitcountry.length <= 2) {
          country.add("null");
        } else {
          String firstcountry = firstSplitcountry[2];
          String[] secondSplitcountry = firstcountry.split("\"");
          String finalcountry = secondSplitcountry[1];
          country.add(finalcountry);
        }
      }

      if (allSplit[i].contains("Telephone")) {
        String telephoneLine = allSplit[i];
        String[] firstSplittelephone = telephoneLine.split("\\\\");
        if (firstSplittelephone.length <= 2) {
          telephone.add("null");
        } else {
          String firsttelephone = firstSplittelephone[2];
          String[] secondSplittelephone = firsttelephone.split("\"");
          String finaltelephone = secondSplittelephone[1];
          telephone.add(finaltelephone);
        }
      }
    }

    for (int i = 0; i < name.size(); i++) {
      String address =
          addressLine1.get(i)
              + ","
              + addressLine2.get(i)
              + ","
              + addressLine3.get(i)
              + ","
              + county.get(i)
              + ","
              + country.get(i);
      String Conname = name.get(i);
      Integer id = contractorController.findId(Conname);
      Contractor contractor = new Contractor(id, name.get(i), telephone.get(i), "MCS", address);
      contractorList.add(contractor);
    }

    return contractorList;
  }
}
