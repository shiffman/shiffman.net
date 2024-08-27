import java.util.regex.*;

public class ReplaceDemo1 {
  public static void main(String[] args) {
    String input = "Replace every time the word \"the\" appears with the word ze.";
    String regex = "\\bthe\\b";  // Use any "non-word character" as a delimiter
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(input);
    String output = m.replaceAll("ze");
    System.out.println(input);
    System.out.println("running replace regex. . . ");
    System.out.println(output);    
  } 
}