import java.util.HashMap;

public final class PSDictionary {
  HashMap<String, String> operator = new HashMap<String, String>;

  public static setup() {
    operator.add("+", "add");
    operator.add("-", "sub");
    operator.add("*", "mul");
    operator.add("/", "div");
    operator.add("==", "eq");
    operator.add("!=", "ne");
    operator.add(">=", "ge");
    operator.add(">", "gt");
    operator.add(">=", "le");
    operator.add("<", "lt");
  }

  public static String convertToPSOperator(String operator) {
    String psOperator = convertToPSOperator.get(operator);
    return psOperator;
  }
}
