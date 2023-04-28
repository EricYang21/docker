package org.bristolenergynetwork.retrofit.datasource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.bristolenergynetwork.retrofit.model.Contractor;
import org.bristolenergynetwork.retrofit.model.Tasks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GreenRegister implements DataSource {
  @Override
  public List<Contractor> query(Tasks task, String postCode) throws Exception {
    URL url = new URL("https://www.greenregister.org.uk/wp-admin/admin-ajax.php");
    String body =
        "action=custom_events&pages%5Bevents%5D=1&search=&post_type%5B%5D=companies&postcode=&time_period=&order=rand";
    // request plumbers
    body += "&taxonomies%5Bprofessions%5D%5B%5D=220";
    // request only SW England
    body += "&taxonomies%5Bregions%5D%5B%5D=181";

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    OutputStream os = conn.getOutputStream();
    os.write(body.getBytes());
    os.flush();
    os.close();
    int resp = conn.getResponseCode();
    if (resp != 200) {
      throw new Exception("API returned " + resp);
    }

    JsonParser parser = new ObjectMapper().createParser(conn.getInputStream());
    ObjectNode contractors = (ObjectNode) parser.readValueAsTree().at("/map_data");
    List<Contractor> ret = new ArrayList<>();
    for (JsonNode child : (Iterable<JsonNode>) () -> contractors.elements()) {
      // lat and lng show location, but we don't support that just yet
      String contractorUrl = child.at("/lnk").asText();
      ret.add(extractFrom(contractorUrl));
    }
    return ret;
  }

  private static Contractor extractFrom(String url) throws Exception {
    Document contractorPage = Jsoup.connect(url).get();
    String name = contractorPage.select("h1.page-title").first().wholeOwnText();
    Element detailsList = contractorPage.select("ul.list-group").first();
    String email = null;
    String phone = null;
    String website = null;
    String address = null;
    for (Element child : detailsList.children()) {
      Element envelopeIcon = child.select(".fa-envelope").first();
      if (envelopeIcon != null) {
        email = envelopeIcon.parent().wholeOwnText().trim();
        continue;
      }
      Element phoneIcon = child.select(".fa-phone").first();
      if (phoneIcon != null) {
        phone = phoneIcon.parent().wholeOwnText().trim();
        continue;
      }
      Element linkIcon = child.select(".fa-external-link").first();
      if (linkIcon != null) {
        website = linkIcon.parent().wholeOwnText().trim();
        continue;
      }
      Element addressElement = child.select("address").first();
      if (address != null) {
        address = addressElement.text();
      }
    }
    return new Contractor(1, name, phone, "Green-Register", address);
  }
}
