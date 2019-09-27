package entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class QueryCondition {
  String query;
  List<Object> parameters = new ArrayList<>();
}
