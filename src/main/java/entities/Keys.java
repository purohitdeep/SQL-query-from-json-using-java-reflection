package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Keys implements Serializable {

  @JsonProperty("sac_no")
  private Integer sacNo;

  @JsonProperty("sac_unit")
  private String sacUnit;

  @JsonProperty("country_code")
  private String countryCode;
}
