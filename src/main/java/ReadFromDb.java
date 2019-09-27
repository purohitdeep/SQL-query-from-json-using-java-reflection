

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.CimEventPayload;
import entities.Keys;
import entities.QueryData;
import java.io.IOException;
import java.util.List;

public class ReadFromDb {

  public static void main(String[] args) {

    String json =
        "{\n"
            + "  \"content\": {\n"
            + "    \"keys\": {\n"
            + "      \"sac_no\": 1024785,\n"
            + "      \"sac_unit\": \"FR023\",\n"
            + "      \"country_code\": \"FR\"\n"
            + "    },\n"
            + "    \"target\": [\n"
            + "      \"Metadata\",\n"
            + "      \"Event\",\n"
            + "      \"InvoiceInformation\",\n"
            + "      \"DeliveryInformation\",\n"
            + "      \"ReturnInformation\"\n"
            + "    ]\n"
            + "  }\n"
            + "}";
    ObjectMapper mapper = new ObjectMapper();
    try {
      // generate objects
      CimEventPayload cimEvent = mapper.readValue(json, CimEventPayload.class);

      // get keys
      Keys keys = cimEvent.getContent().getKeys();

      // get table names
      List<String> targetTables = cimEvent.getContent().getTarget();

      // make where clause - this would be exact one, as its going to be same for all tables
      List<QueryData> query = new QueryCreator(keys, targetTables).createQuery();

      System.out.println(query);

      // Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);

      // readFromDb(options);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}