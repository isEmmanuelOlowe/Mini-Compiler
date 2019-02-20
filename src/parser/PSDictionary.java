package logoCompiler.parser;

import java.util.HashMap;

/**
* Converts Logo operators to posts script operators
*/
public final class PSDictionary {
  private static HashMap<String, String> operator = new HashMap<String, String>();

  /**
  * setups HashMap with corresponding values
  */
  public static void setup() {
    operator.put("+", "add");
    operator.put("-", "sub");
    operator.put("*", "mul");
    operator.put("/", "div");
    operator.put("==", "eq");
    operator.put("!=", "ne");
    operator.put(">=", "ge");
    operator.put(">", "gt");
    operator.put(">=", "le");
    operator.put("<", "lt");
  }

  /**
  * Converts logo operator to post script operator.
  *
  * @param logoOperator the value of the logo operator
  * @return the corresponding operator in post script
  */
  public static String convertToPSOperator(String logoOperator) {
    String psOperator = operator.get(logoOperator);
    return psOperator;
  }
}
