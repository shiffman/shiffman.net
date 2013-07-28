import java.util.regex.*;

public class ReplaceDemo2 {
  public static void main(String[] args) {
    String input = "Replace every time the word \"the\" appears with the word ze.";
    String regex = "\\bthe\\b";  // Use any "non-word character" as a delimiter
    String output = input.replaceAll(regex,"ze");
    System.out.println(input);
    System.out.println("running replace regex. . . ");
    System.out.println(output);    
  } 
}