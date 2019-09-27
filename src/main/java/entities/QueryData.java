package entities;

import lombok.Data;

@Data
public class QueryData {
  QueryCondition selectQueryCondition;
  QueryCondition whereQueryCondition;

  public String toString(){
    return selectQueryCondition.getQuery() + whereQueryCondition.getQuery() + whereQueryCondition.getParameters() + "\n";
  }

}

