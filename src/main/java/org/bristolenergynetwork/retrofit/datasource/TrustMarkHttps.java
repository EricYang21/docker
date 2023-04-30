package org.bristolenergynetwork.retrofit.datasource;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSONArray;
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
public class TrustMarkHttps implements DataSource {
  @Autowired ContractorController contractorController;

  @Override
  public List<Contractor> query(Tasks task, String postCode) throws Exception {
    String[] postCodeList = postCode.split(" ");
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

    // this above is for find longitude and latitude

    for (int i = 0; i < 10; i++) {
      postCode = postCode.replaceAll(" ", "");
    }

    JSONArray TradeSkillIds = new JSONArray();
    JSONArray tradeIds = new JSONArray();
    List<Contractor> allContractorList = new ArrayList<>();

    if (task == Tasks.BOILER_INSTALLATION || task == Tasks.BOILER_REPAIR) {
      TradeSkillIds.add("352");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }
      TradeSkillIds.remove(0);
      TradeSkillIds.add("201");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }
      TradeSkillIds.remove(0);
      TradeSkillIds.add("180");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }

      if (tradeIds.size() != 0) {
        tradeIds.remove(0);
      }
      tradeIds.add("179");
      List<Contractor> contractorList4 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList4 != null) {
        for (Contractor value : contractorList4) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }

    if (task == Tasks.HEAT_PUMP_INSTALLATION || task == Tasks.HEAT_PUMP_REPAIR) {
      TradeSkillIds.add("353");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }
      TradeSkillIds.remove(0);
      TradeSkillIds.add("139");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }
      TradeSkillIds.remove(0);
      tradeIds.add("141");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("198");
      List<Contractor> contractorList4 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList4 != null) {
        for (Contractor value : contractorList4) {
          allContractorList.add(value);
        }
      }
      tradeIds.remove(0);
      tradeIds.add("140");
      List<Contractor> contractorList5 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList5 != null) {
        for (Contractor value : contractorList5) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }
    if (task == Tasks.SOLAR_PV) {
      TradeSkillIds.add("184");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      TradeSkillIds.remove(0);
      TradeSkillIds.add("4");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }
      return allContractorList;
    }

    if (task == Tasks.SOLAR_THERMAL) {
      TradeSkillIds.add("178");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      TradeSkillIds.remove(0);
      TradeSkillIds.add("5");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      TradeSkillIds.remove(0);
      TradeSkillIds.add("199");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }
    }

    if (task == Tasks.HOT_WATER_CYLINDER_INSTALLATION || task == Tasks.HOT_WATER_CYLINDER_REPAIR) {
      tradeIds.add("96|155|95");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("123");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("122");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }

    if (task == Tasks.LOFT_INSULATION || task == Tasks.LOFT_REPAIR) {
      tradeIds.add("57");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("159|74|75|186");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("49");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }

    if (task == Tasks.MICRO_CHP) {
      tradeIds.add("182");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("136");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("181");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("98");
      List<Contractor> contractorList4 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList4 != null) {
        for (Contractor value : contractorList4) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }

    if (task == Tasks.AIR_HEATING) {
      tradeIds.add("128");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("129");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("130");
      List<Contractor> contractorList3 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList3 != null) {
        for (Contractor value : contractorList3) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("131");
      List<Contractor> contractorList4 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList4 != null) {
        for (Contractor value : contractorList4) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }
    if (task == Tasks.WINDTURBINE) {
      tradeIds.add("180");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("50");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }
    if (task == Tasks.WINDOW_INSTALLATION || task == Tasks.WINDOW_REPAIR) {
      tradeIds.add("92");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("48");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }

    if (task == Tasks.FLAT_ROOF_INSULATION
        || task == Tasks.FLAT_ROOF_REPAIR
        || task == Tasks.FLAT_ROOF_REPLACEMENT) {
      tradeIds.add("153|76|77");
      List<Contractor> contractorList1 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList1 != null) {
        for (Contractor value : contractorList1) {
          allContractorList.add(value);
        }
      }

      tradeIds.remove(0);
      tradeIds.add("38");
      List<Contractor> contractorList2 =
          queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);
      if (contractorList2 != null) {
        for (Contractor value : contractorList2) {
          allContractorList.add(value);
        }
      }

      return allContractorList;
    }

    if (task == Tasks.AIR_CONDITION_INSTALLATION) {
      TradeSkillIds.add("1");
    } else if (task == Tasks.AIR_CONDITION_REPAIR) {
      TradeSkillIds.add("358");
    } else if (task == Tasks.BATTERY_STORAGE) {
      TradeSkillIds.add("351");
    } else if (task == Tasks.BUILDER) {
      TradeSkillIds.add("9");
    } else if (task == Tasks.DOOR_REPAIR || task == Tasks.DOOR_INSTALLATION) {
      TradeSkillIds.add("41");
    } else if (task == Tasks.DAMP_PROOFING) {
      TradeSkillIds.add("14");
    } else if (task == Tasks.ELECTRIC_STORAGE_HEATERS) {
      TradeSkillIds.add("202");
    } else if (task == Tasks.ELECTRIC_VEHICLE_CHARGING) {
      TradeSkillIds.add("61");
    } else if (task == Tasks.ELECTRICIAN) {
      TradeSkillIds.add("18");
    } else if (task == Tasks.FLOORING_EXPERT) {
      tradeIds.add("22");
    } else if (task == Tasks.AIR_CONDITION_INSTALLATION || task == Tasks.AIR_CONDITION_REPAIR) {
      TradeSkillIds.add("179");
    } else if (task == Tasks.PLUMBER) {
      tradeIds.add("36");
    }

    List<Contractor> contractorList =
        queryMain(postCode, longitude, latitude, TradeSkillIds, tradeIds, task);

    return contractorList;
  }

  public List<Contractor> queryMain(
      String postCode,
      String longitude,
      String latitude,
      JSONArray TradeSkillIds,
      JSONArray tradeIds,
      Tasks task)
      throws Exception {
    URL url = new URL("https://www.trustmark.org.uk/org-search");
    BufferedReader br = null;
    StringBuffer response = new StringBuffer();

    JSONArray SchemeIds = new JSONArray();
    JSONArray PaymentOptions = new JSONArray();
    JSONArray AdvancedTradeSkillIds = new JSONArray();
    JSONArray AdvancedTradeIds = new JSONArray();
    JSONArray Regions = new JSONArray();

    JSONObject body = new JSONObject();
    body.put("Address", postCode); // BS1 4TR
    body.put("AdvancedTradeIds", AdvancedTradeIds);
    body.put("AdvancedTradeSkillIds", AdvancedTradeSkillIds);
    body.put("ExcludeNationalBusinesses", false);
    body.put("FromAdvanceSearch", false);
    body.put("ItemsPerPage", 10);
    body.put("Latitude", Double.parseDouble(latitude)); // need t0 change 51.4558891
    body.put("LicenseNumber", "");
    body.put("LocationText", "");
    body.put("Longitude", longitude);
    body.put("MatchOption", ""); // need t0 change 51.4558891
    body.put("MaxItems", 200);
    body.put("NumberOfReview", 0);
    body.put("OrganisationName", "");
    body.put("PASAnnex", "");
    body.put("PageNo", 1);
    body.put("PaymentOptions", PaymentOptions);
    body.put("Postcode", "BS1 4TR");
    body.put("RatingScore", "");
    body.put("Regions", Regions);
    body.put("SchemeIds", SchemeIds);
    body.put("Standard", "");
    body.put("TradeCodesFromUrl", "");
    if (tradeIds.size() != 0) {
      body.put("TradeIds", tradeIds.get(0));
    }
    if (!TradeSkillIds.isEmpty()) {
      body.put("TradeSkillIds", TradeSkillIds.get(0));
    }

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

    JSONObject allResult = JSONObject.parseObject(String.valueOf(response));
    JSONArray items = allResult.getJSONArray("Items");

    List<Contractor> contractorList = new ArrayList<>();

    for (int i = 0; i < items.size(); i++) {
      JSONObject contrator = (JSONObject) items.get(i);
      String name = contrator.getString("Name");
      String phone = contrator.getString("PhoneForDisplay");
      String address1 = contrator.getString("Address1");
      String address2 = contrator.getString("Address2");
      if (address2 != null && address2.contains("https://netzerocollective.co.uk/")) {
        address2 = "Garden works, Charley wood road";
      }
      String town = contrator.getString("Town");
      String county = contrator.getString("County");
      String country = contrator.getString("Country");
      String address = address1 + "," + address2 + "," + town + "," + county + "," + country;
      Integer id = contractorController.findId(name);
      Contractor newContrator = new Contractor(id, name, phone, "TrustMark", address);
      contractorList.add(newContrator);
    }

    for (Contractor contractor : contractorList) {
      System.out.println(
          "postCode = " + postCode + " task = " + task + " name = " + contractor.getName());
    }

    return contractorList;
  }
}
