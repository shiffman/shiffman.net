import java.util.regex.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
	

public class ReplaceDemo2 {
  public static void main(String[] args) throws IOException {
    String input = "Replace every time the word \"the\" appears with the word ze.";
    String regex = "\\bthe\\b";  // Use any "non-word character" as a delimiter
    String output = input.replaceAll(regex,"ze");
    System.out.println(input);
    System.out.println("running replace regex. . . ");
    System.out.println(output);    
  } 
}