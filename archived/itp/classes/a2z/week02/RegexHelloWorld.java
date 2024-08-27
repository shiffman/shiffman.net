import java.util.regex.*;

public class RegexHelloWorld {
  public static void main(String[] args) {
    String inputtext = "This is a test of regular expressions.";  // Step #1
    String regex = "test";               // Step #2
    Pattern p = Pattern.compile(regex);  // Step #3
    Matcher m = p.matcher(inputtext);    // Step #4
    if (m.find()) {
      System.out.println(m.group());     // Step #5
    } else {
      System.out.println("No match!");   // Step #6
    }
  } 
}