
import entities.Keys;
import entities.QueryCondition;
import entities.QueryData;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class QueryCreator {
  Keys keys;
  List<String> targetTables;

  public QueryCreator(Keys keys, List<String> targetTables) {
    this.keys = keys;
    this.targetTables = targetTables;
  }

  public List<QueryData> createQuery() {
    List<QueryData> queryData = null;
    try {
      QueryCondition queryWhereCondition = makeWhereCondition();
      queryData = makeSelectCondition(queryWhereCondition);

    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return queryData;
  }

  public List<QueryData> makeSelectCondition(QueryCondition queryWhereCondition)
      throws IllegalAccessException {

    List<QueryData> queryDataList = new ArrayList<>();

    for (String table : targetTables) {
      StringBuilder selectClause = new StringBuilder();

      // TODO: Add column based fetch mechanism
      selectClause.append("Select * from ").append(table);

      QueryCondition queryCondition = new QueryCondition();
      queryCondition.setQuery(selectClause.toString());

      QueryData queryData = new QueryData();
      queryData.setWhereQueryCondition(queryWhereCondition);
      queryData.setSelectQueryCondition(queryCondition);

      queryDataList.add(queryData);
    }
    return queryDataList;
  }

  public QueryCondition makeWhereCondition() throws IllegalAccessException {
    QueryCondition queryCondition = new QueryCondition();

    StringBuilder whereClause = new StringBuilder(" where ");

    Class aClass = keys.getClass();
    Field[] declaredFields = aClass.getDeclaredFields();
    boolean singleConditionCreated = false;

    for (Field declaredField : declaredFields) {
      declaredField.setAccessible(true);
      if (singleConditionCreated) {
        whereClause.append("and ");
      }
      whereClause.append(declaredField.getName());
      whereClause.append(" = ? ");
      singleConditionCreated = true;
      queryCondition.getParameters().add(declaredField.get(keys));
    }

    queryCondition.setQuery(whereClause.toString());
    return queryCondition;
  }
}
