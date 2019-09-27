package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content implements Serializable {

  @JsonProperty("keys")
  private Keys keys;

  @JsonProperty("target")
  private List<String> target = null;
}
