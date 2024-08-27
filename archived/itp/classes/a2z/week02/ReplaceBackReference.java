import java.util.regex.*;

public class ReplaceBackReference {
  public static void main(String[] args) throws IOException {
    String input = "Anytime a sequence of one or more vowels appears, \n" + 
                   "we're going to double the vowels.";
    String regex = "[aeiou]+";  //
    String output = input.replaceAll(regex, "$0$0");
    System.out.println(input);
    System.out.println("running replace regex. . . ");
    System.out.println(output);    
  } 
}