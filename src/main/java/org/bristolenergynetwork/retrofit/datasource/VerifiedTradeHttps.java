package org.bristolenergynetwork.retrofit.datasource;

import cn.hutool.core.util.ReUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.bristolenergynetwork.retrofit.controller.ContractorController;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifiedTradeHttps implements DataSource {
  @Autowired ContractorController contractorController;

  @Override
  public List<Contractor> query(Tasks task, String postCode) throws Exception {
    String process1 = task.name().toLowerCase();
    String choice = process1.replaceAll("_", "-");
    for (int i = 0; i < 10; i++) {
      postCode = postCode.replaceAll(" ", "");
    }
    if (task == Tasks.LOFT_INSULATION) {
      choice = "loft-conversion-expert";
    } else if (task == Tasks.BALCONY_ROOF_INSULATION
        || task == Tasks.BALCONY_ROOF_REPAIR
        || task == Tasks.BALCONY_ROOF_REPLACEMENT) {
      choice = "roofer";
    } else if (task == Tasks.WINDOW_INSTALLATION || task == Tasks.WINDOW_REPAIR) {
      choice = "window-conservatory-expert";
    } else if (task == Tasks.HEAT_PUMP_INSTALLATION || task == Tasks.HEAT_PUMP_REPAIR) {
      choice = "heating-engineer";
    } else if (task == Tasks.DAMP_PROOFING) {
      choice = "damp-expert";
    } else if (task == Tasks.AIR_CONDITION_INSTALLATION || task == Tasks.AIR_CONDITION_REPAIR) {
      choice = "air-conditioning-expert";
    } else if (task == Tasks.BOILER_INSTALLATION || task == Tasks.BOILER_REPAIR) {
      choice = "Biomass";
    } else {
      return null;
    }

    URL url = new URL("https://verifiedtrades.co.uk/search/" + choice + "/" + postCode);
    // get request, so it doesn't need to Json Request.
    BufferedReader br = null;
    StringBuffer response = new StringBuffer();

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    // start connection
    conn.setDoInput(true);
    conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
    conn.setRequestMethod("GET");
    conn.setDoOutput(true);
    conn.connect();

    // get respCode and get response
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

    String strResponse = response.toString();
    // this website return the whole js, should distinguish what we need and return
    // for example:<h3 class="h1 trade trade-name  has-fm " >
    //                                                        	Pete Mayne Services</h3>

    // if no response <div class='col-md-12 align-self-center grey-dark text-center'><h3>Sorry, we
    // couldn't find anybody to help you in BS1. Try another location near you?</h3></div>

    Boolean flag = false;
    ArrayList<String> emptyResponse =
        ReUtil.findAll(
            "<div class='col-md-12 align-self-center grey-dark"
                + " text-center'><h3>([\\s\\S]*)</h3></div>",
            strResponse,
            0,
            new ArrayList<>());
    for (String empty : emptyResponse) {
      ArrayList<String> emptyString =
          ReUtil.findAll("3>([\\s\\S]*)</h", empty, 0, new ArrayList<>());
      for (String value : emptyString) {
        String s = StringUtils.removeStart(value, "3>");
        String s1 = StringUtils.removeEnd(s, "</h").trim();
        flag = true;
      }
    }

    if (flag == true) {
      return null;
    }
    String name = null;
    String address = null;
    String phone = null;
    ArrayList<String> names = new ArrayList<>();
    List<String> resultFindAll =
        ReUtil.findAll(
            "trade trade-name([\\s\\S]*?)<?/h3>?", strResponse, 0, new ArrayList<String>());
    for (String eachName : resultFindAll) {

      List<String> nameList = ReUtil.findAll(">([\\s\\S]*)<", eachName, 0, new ArrayList<String>());
      for (String value : nameList) {

        String s =
            StringUtils.removeStart(
                value, ">                                                       ");
        for (int i = 0; i < 10; i++) {
          value = value.replace("  ", " ");
        }
        String s1 = StringUtils.removeEnd(s, "<").trim();

        if (s1.contains("amp;")) {
          String[] split = s1.split("amp;");
          s1 = split[0] + split[1];
        }
        name = s1;
        names.add(name);
      }
    }

    ArrayList<String> addresses = new ArrayList<>();
    ArrayList<String> resultAddress =
        ReUtil.findAll(
            "aria-hidden=\"true\"></i>([\\s\\S]*?)<?p class=\"trade trade-description\">",
            strResponse,
            0,
            new ArrayList<String>());
    for (String eachAddress : resultAddress) {
      ArrayList<String> addressList =
          ReUtil.findAll("i>([\\s\\S]*)</", eachAddress, 0, new ArrayList<>());
      for (String value : addressList) {
        for (int i = 0; i < 10; i++) {
          value = value.replace("  ", " ");
        }

        String s = StringUtils.removeStart(value, "i>");
        String s1 = StringUtils.removeEnd(s, "</").trim();
        address = s1;
        addresses.add(address);
      }
    }

    // we need to search another link again to get the phone.
    if (names.size() != 0) {
      ArrayList<String> phones = new ArrayList<>();
      for (String na : names) {

        String finalName = na.toLowerCase().trim();
        if (finalName.contains(",")) {
          finalName = finalName.replace(", ", "-");
        }
        if (finalName.contains("&")) {
          finalName = finalName.replace(" & ", "--");
        }
        if (finalName.contains("(")) {
          finalName = finalName.replace(" (", "-");
        }
        if (finalName.contains(")")) {
          finalName = finalName.replace(") ", "-");
        }
        if (finalName.contains(")")) {
          finalName = finalName.replace(")", "");
        }
        finalName = finalName.replace(" ", "-");

        if (finalName.equals("c-brookes-plumbing--heating")) {
          finalName = "c-brookes-plumbing--heating-1";
        }
        if (finalName.equals("bristol-boiler-man-ltd")) {
          finalName = "bristol-boiler-man-ltd-1";
        }
        if (finalName.equals("cp-\uFEFFdomestic-heating-ltd")) {
          finalName = "cp-domestic-heating-ltd";
        }
        if (finalName.equals("harrysblinds")) {
          finalName = "harrysblinds-1";
        }
        System.out.println("postCode = " + postCode + "  " + task.name() + finalName);

        URL phoneUrl = new URL("https://verifiedtrades.co.uk/" + finalName);
        // get request, so it doesn't need to Json Request.
        BufferedReader bufferedReader = null;
        StringBuffer responsePhone = new StringBuffer();

        HttpURLConnection connection = (HttpURLConnection) phoneUrl.openConnection();
        // start connection
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.connect();

        // get respCode and get response
        int respCodePhone = connection.getResponseCode();

        if (respCodePhone == HttpURLConnection.HTTP_OK) {
          System.out.println("phone url pass");
        } else {
          // throw new Exception("API returned " + respCode);
        }

        bufferedReader =
            new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String linePhone = null;
        while (null != (linePhone = bufferedReader.readLine())) {
          responsePhone.append(linePhone);
        }

        String strResponsePhone = responsePhone.toString();

        // example: <p>Phone: 01242693723</p>
        ArrayList<String> phoneLists =
            ReUtil.findAll(
                "<p>Mobile Phone:([\\s\\S]*)Website", strResponsePhone, 0, new ArrayList<String>());
        if (finalName.equals("swade-boiler--gas-services")) {
          phoneLists =
              ReUtil.findAll(
                  "<p>Mobile Phone:([\\s\\S]*)col-md-5",
                  strResponsePhone,
                  0,
                  new ArrayList<String>());
        }
        if (phoneLists.size() == 0) {
          phoneLists =
              ReUtil.findAll(
                  "<p>Phone:([\\s\\S]*)col-md-5", strResponsePhone, 0, new ArrayList<String>());
        }
        for (String eachphone : phoneLists) {
          ArrayList<String> phoneList =
              ReUtil.findAll(":([\\s\\S]*?)</?", eachphone, 0, new ArrayList<>());
          for (String value : phoneList) {
            String s = StringUtils.removeStart(value, ":");
            String s1 = StringUtils.removeEnd(s, "</").trim();
            phone = s1;
            phones.add(phone);
          }
        }
      }
      Contractor contractor = null;
      for (int i = 0; i < names.size(); i++) {
        Integer id = contractorController.findId(names.get(i));
        contractor =
            new Contractor(id, names.get(i), phones.get(i), "Verified-Trade", addresses.get(i));
      }

      List<Contractor> contractorList = new ArrayList<>();
      contractorList.add(contractor);

      return contractorList;
    }
    return null;
  }
}
