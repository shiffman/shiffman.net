---
title: Week 1 A to Z
author: Daniel
layout: page
dsq_thread_id:
  - 249553138
pvc_views:
  - 5436
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#beyond">Beyond Processing and into Java</a></li>
<li class="arrow"><a href="#string">The String Class</a></li>
<li class="arrow"><a href="#file">File I/O</a></li>
<li class="arrow"><a href="#analysis">Simple Analysis</a></li>
</div>
<h2>Examples:</h2>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/HelloWorld.java">HelloWorld.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/BankAccountTest.java">BankAccountTest.java</a> <a href="http://www.shiffman.net/itp/classes/a2z/week01/BankAccount.java">BankAccount.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/SimpleFileIO.java"> SimpleFileIO.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/SimpleFileIO2.java"> SimpleFileIO2.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/EveryOtherWord.java"> EveryOtherWord.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/ReverseWords.java"> ReverseWords.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/ReverseCharacters.java"> ReverseCharacters.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/God.java"> God.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week01/FleschIndex.java"> FleschIndex.java</a></li>
<li class="arrow">Some sample input files: <a href="http://www.shiffman.net/itp/classes/a2z/week01/spam.txt">spam.txt</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week01/nytimes.txt">nytimes.txt</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week01/bible.txt">bible.txt</a></li>
<h2>Related:</h2>
<li class="arrow"><a href="http://bioportal.weizmann.ac.il/course/prog2/tutorial/java/data/strings.html">Characters and Strings tutorial</a></li>
<h2>Exercises (<i>optional and purposefully mundane</i>):</h2>
<li class="arrow">Write a program that opens multiple source text files and combines them together writing them out as one file.</li>
<li class="arrow">Write a program that counts the number of punctuation occurrences in a source text.</li>
<li class="arrow">Revise the file input / output program to have full error handling.</li>
<p>&nbsp;<br />
<a name ="beyond"></a></p>
<h2>Beyond <a href="http://www.processing.org">Processing</a> and into <a href="http://java.sun.com/">Java</a></h2>
<p>This course assumes one semester of programming experience in <a href="http://www.processing.org">Processing</a>.  If you&#8217;re already familiar with compiling and running your own Java programs without Processing feel free to skip this section.   </p>
<p>Pulling back the curtain of <a href="http://www.processing.org">Processing</a>, what weâ€™ll discover is it really is Java.  For example, in Java, you:</p>
<li class="arrow">Declare, initialize and use variables the same way</li>
<li class="arrow">Declare, initialize and use arrays the same way</li>
<li class="arrow">Employ conditional and loop statements the same way</li>
<li class="arrow">Define and call functions the same way</li>
<li class="arrow">Create classes the same way.</li>
<li class="arrow">Instantiate objects the same way.</li>
<p>&nbsp;<br />
Processing, of course, gives some extra stuff for free, and this is why itâ€™s a great tool for learning rich media programming.   Nevertheless, for the start of this semester, our programs will involve text / file processing and it will be simpler to compile and run programs from the command line.  However, the examples will also be provided via CVS as an Eclipse project and you should feel free to use <a href="http://www.eclipse.org">Eclipse</a> if you prefer (<a href="http://itp.nyu.edu/varwiki/A2Z/CVS">CVS Instructions</a>).   From time to time, I will also include <a href="http://www.perl.com/">Perl</a> and  <a href="http://www.php.net/">PHP</a> versions of the examples.  Using Java for the assignments will not be required if you prefer one of these languages.</p>
<p>Let&#8217;s look at a basic first example.</p>

{% highlight java %}
public class HelloWorld
{
  int x = 0;
  public static void main(String[] args)
  {
    int x = 0;
    while (x < 10) {
      System.out.println("Look, I can count to " + x + "!");
      x++;
    }
  }
}
{% endhighlight %}

<div class = "pullquote">
Compile: <b>javac ClassName.java</b></p>
<p>Run: <b>java ClassName</b>
</div>
<p>Take the above code and make a text file called HelloWorld.java (using notepad, textpad, bbedit, textwrangler, etc.)  Congratulations, you've written your first java program.  However, unlike with processing, we don't have a "Run" or "Play" button.  You have to compile and run the program yourself.</p>
<p>On a Mac, you can accomplish this via the <a href="http://www.apple.com/macosx/features/unix/">Terminal</a>.</p>
<p><img src ="http://www.shiffman.net/itp/classes/a2z/week01/java0.jpg"/></p>
<p><b>Holy more than one file, batman</b></p>
<p>Let's take a look at an object oriented example where our class (Bank Account) is kept in its own file and a "driver" program accesses it.  Here are the two files you need:<br />
<b><a href="http://www.shiffman.net/itp/classes/a2z/week01/BankAccount.java">BankAccount.java</a><br />
<a href="http://www.shiffman.net/itp/classes/a2z/week01/BankAccountTest.java">BankAccountTest.java</a></b></p>
<p>As long as these files are both in the same directory, we can compile BankAccountTest.java, which, since it uses the BankAccount.java class, will instigate the compilation of that class.</p>
<p><img src ="http://www.shiffman.net/itp/classes/a2z/week01/java1.jpg"/></p>
<p>(examples from <a href="http://www.horstmann.com/bigjava.html">Big Java by Cay Horstmann</a>.)</p>
<p><b>What's new?</b></p>
<li class="arrow">The "main" method â€“ Every Java program must have a <b>main</b> method.  In Processing, we controlled the flow of the program via setup() and draw().   Under the covers, however, every PApplet has a main method that creates initializes the applet, creates the window, etc. etc.  Since we are writing Java programs <i>from scratch</i>, we'll need to write our own main method.   <a href="http://java.sun.com/docs/books/tutorial/getStarted/application/index.html#MAIN">A full explanation of the main method is available here.</a>  One thing that is important for us to note is that the main method takes an array of Strings as an argument, i.e. "main(String[] args)".  When the program is run via the command line, you can pass Strings into the program using the array.  This will prove incredibly useful for doing file processing (see below).</li>
<li class="arrow">Import Statements â€“  Although this particular HelloWorld program does not include any import statements, Java programs require that classes and libraries are explicitly imported.  Weâ€™ve experienced this before when using the video, serial, or opengl library in Processing, i.e. import processing.video.*; .</li>
<li class="arrow">public â€“ In Java, variables, functions, and classes can be â€œpublicâ€ or â€œprivate.â€  This designation indicates what level of access should be granted to a particular piece of code.  Itâ€™s not something we have to worry much about right now, but it becomes an important consideration when moving on to larger Java programs.</li>
<li class="arrow">class HelloWorld â€“ Sound somewhat familiar?  Java, it turns out, is a true object-oriented language.  There is nothing written in Java that is not part of a class!  Every program, even the main program is a class too! </li>
<p>&nbsp;<br />
<b>Exploring the Java API</b></p>
<p>The Processing reference quickly became our BFF while learning to program.  The Java API is going to be more of our rascally nemesis.  We can explore the full java documentation by visiting <a href="http://java.sun.com/">http://java.sun.com</a>.  There, we can click over to â€œAPIâ€ specifications: <a href="http://java.sun.com/reference/api/index.html">http://java.sun.com/reference/api/index.html</a> and find a selection of versions of Java.  We'll be using the JavaTM 2 Platform, Standard Edition, v 1.4.2: <a href="http://java.sun.com/j2se/1.4.2/docs/api/">http://java.sun.com/j2se/1.4.2/docs/api/</a>.</p>
<p>And so, very quickly, youâ€™ll find yourself completely lost.  And thatâ€™s ok.  The Java API is huge.  Humongous.  Itâ€™s not meant to be read or even perused.  Itâ€™s really more of a reference for looking up specific classes.  For example, you might be working on a program that requires sophisticated random number generation, and perhaps you overheard a conversation about the class â€œRandomâ€ and thought â€œHey, maybe I should check that out!â€  You can find the appropriate reference page by scrolling down the â€œAll Classesâ€ list or else by knowing that it lives in the java.util package (which you can select from the package list on the top left.)  Even better, if you type <a href="http://www.google.com/search?client=safari&#038;rls=en&#038;q=Java+Random&#038;ie=UTF-8&#038;oe=UTF-8">Java Random</a> into google, the Random documentation page will be the first to appear.  Much like Processing, youâ€™ll find a reference page with an explanation of what the class does, the Constructors for creating an object instance, and available methods (functions).  Since Random is part of the java.util package, we donâ€™t need to explicitly write an import statement to use it.  </p>
<p><a name="string"></a></p>
<h2>The â€œStringâ€ class</h2>
<p>The <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html">String</a> class is what we will use to store textual information in our Java programs (from time to time, we may also use the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/StringBuffer.html">StringBuffer</a> class, but <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html"> String</a> will do for now.)</p>
<p>You may be familiar with the <a href="http://processing.org/reference/String.html">Processing reference page for Strings</a>.   The complete reference for String is <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html">http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html </a>.  And again, just in case, link to full JavaDocs: <a href="http://java.sun.com/j2se/1.4.2/docs/api">http://java.sun.com/j2se/1.4.2/docs/api</a>. </p>
<p>A String, at its core, is really just a fancy way of storing an array of characters â€“ if we didnâ€™t have the String class, weâ€™d probably have to write some code like this:</p>

{% highlight java %}
char[] sometext = {â€˜Hâ€™, â€˜eâ€™, â€˜lâ€™, â€˜lâ€™, â€˜oâ€™, â€˜ â€˜, â€˜Wâ€™, â€˜oâ€™, â€˜râ€™, â€˜lâ€™, â€˜dâ€™};
{% endhighlight %}

<p>Clearly, this would be a royal pain in the programming behind.  Itâ€™s much simpler to do the following and make a String object:</p>

{% highlight java %}
String sometext = â€œHow to make a String? Characters between quotation marks!â€;
{% endhighlight %}

<p><b>Simple String Analysis</b></p>
<p>Java provides us with a basic set of String functions that allow for simple manipulation and analysis.  <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/regex">Next week</a>, we&#8217;ll also look at how <a href="http://en.wikipedia.org/wiki/Regular_expression">regular expressions</a> can allow to perform advanced String processing, but it&#8217;s good to pick up some of the basics first and gather some skills doing all of our text processing manually, character by character.   All of the availabe String methods are  functions are laid out on <a href="http://processing.org/reference/String.html">the JavaDoc page</a>, and weâ€™ll explore a few useful ones here.  Let&#8217;s take a closer look at three String class functions: indexOf(), substring(), and length(). </p>
<p>indexOf() locates a sequence of characters within a string. For example, run this code and examine the result: </p>

{% highlight java %}
String sentence = â€œThe quick brown fox jumps over the lazy dog.â€;
System.out.println(sentence.indexOf("quick"));
System.out.println(sentence.indexOf("fo"));
System.out.println(sentence.indexOf("The"));
System.out.println(sentence.indexOf("blah blah"));
{% endhighlight %}

<p>Note that indexOf() returns a 0 for the first character, and a -1 if the search phrase is not part of the String. </p>
<p>After we find a certain search phrase within a String, we might want to pull out part of the String and save it in a different variable. This is what we call a &#8220;substring&#8221; and we can use java&#8217;s substring() function to take care of this task. Examine and run the following code: </p>

{% highlight java %}
String sentence = "The quick brown fox jumps over the lazy dog.";
String phrase = sentence.substring(4,9);
System.out.println(phrase);
{% endhighlight %}

<p>Note that the substring begins at the specified beginIndex (the first argument) and extends to the character at endIndex (the second argument) minus one. Thus the length of the substring is endIndex minus beginIndex. </p>
<p>At any given point, we might also want to access the length of the String. We can accomplish this by calling the length() function. </p>

{% highlight java %}
String sentence = "The quick brown fox jumps over the lazy dog.";
System.out.println(sentence.length());
{% endhighlight %}

<p>Note this is different than accessing the length of an array. Here we are calling the length function available to us within the String class, and therefore must also have the open and close parentheses &#8212; length() &#8212; associated with calling a function.</p>
<p>Itâ€™s also important to note that we can concatenate (i.e. join) a String together using the â€œ+â€ operator.   With numbers plus means add, with Strings (or characters), it means concatenate, i.e.</p>

{% highlight java %}
int num = 5 + 6; // ADDING TWO NUMBERS!
String phrase = "To be" + " or not to be"; // JOINING TWO STRINGS!
String anotherphrase = "Hell" + 'o'; //JOING A STRING WITH A CHAR!
{% endhighlight %}

<p><b>Splitting</b></p>
<p>One String-related function that will prove very useful in our text analysis programs is <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html#split(java.lang.String)">split</a>. <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html#split(java.lang.String)">split</a> separates a group of strings embedded into a longer string into an array of strings.</p>
<p>Examine the following code: </p>

{% highlight java %}
String spaceswords = "The quick brown fox jumps over the lazy dog.";
String list1[] = spaceswords.split(" ");
System.out.println(list1[0]);
System.out.println(list1[1]);

String commaswords = "The,quick,brown,fox,jumps,over,the,lazy,dog.";
String list2[] = commaswords.split(",");
for (int i = 0; i < list2.length; i++) {
  System.out.println(list2[i] + " " + i);
}

//calculate sum of a list of numbers in a string
String numbers = "8,67,5,309";
String numlist[] = numbers.split( ',');
int sum = 0;
for (int i = 0; i < list.length; i++) {
  sum = sum + Integer.parseInt(list[i]);  // Converting each String into an int
}
System.out.println(sum);
{% endhighlight %}

<p>To perform the reverse of split, we can write a quick function that joins together an array of Strings.</p>

{% highlight java %}
String[] lines = {â€œItâ€, â€œwasâ€, â€œaâ€, â€œdarkâ€, â€œandâ€, â€œstormyâ€, â€œnight.â€};
{% endhighlight %}

<p>Knowing about loops and arrays we could join the above array of strings together as follows:</p>

{% highlight java %}
// Concatenating an array of Strings using the String class
public String join(String str[], String separator) {
    String stuff = ""
    for (int i = 0; i < str.length; i++) {
      if (i != 0) stuff += separator;
      stuff += str[i];
    }
    return stuff;
}

// Concatenating an array of Strings using the StringBuffer class (thank you Ben Fry and the Processing source!)
// Using a StringBuffer is better with really long Strings / arrays
public String join(String str[], String separator) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < str.length; i++) {
      if (i != 0) buffer.append(separator);
      buffer.append(str[i]);
    }
    return buffer.toString();
}
{% endhighlight %}

<p><a name="file"></a></p>
<h2>File Input and Output</h2>
<p>To start, we are going to be working in the simple world of text in and text out.  We'll load some text from a file, analyze it, mess with it, etc. and then write some text back out to a file.   Most of the examples you might find online read text from a file line by line.  This is very useful for certain types of operations and you may want to investigate how to do this on your own (see: <a href="http://www.cafeaulait.org/slides/intljava/2001ny/javaio/57.html">http://www.cafeaulait.org/slides/intljava/2001ny/javaio/57.html</a> for an example).  For our purposes, however, we're just going to read the text in character by character (or byte by byte).  This can be accomplished using <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/package-summary.html">java.io</a> or <a href="http://java.sun.com/j2se/1.4.2/docs/guide/nio/">java.nio</a> .   java.nio is a java's "new and improved" input/output package that supposedly improves performance in  buffer management, network and file I/O, regular-expression support, etc. </p>
<p><b>Simple File I/O with the old java.io package</b></p>

{% highlight java %}
// Simple File Input and Output using "old" I/O
// Daniel Shiffman
// Programming from A to Z, Spring 2006

// Input file is the first argument passed from the command line
// Output file is the second
// This could be improved with some basic error handling (what if an invalid filename is entered, etc.?)

import java.io.*;

public class SimpleFileIO2 {
  public static void main (String[] args) throws IOException {

    // Read the file into a String, character by character
    // (We could read it line by line with BufferedReader)
    // This could also be greatly improved using StringBuffer
    FileReader in = new FileReader(new File(args[0]));
    String content = "";
    int c;
    while ((c = in.read()) != -1)  {
      content += (char) c;
    }
    in.close();


    // Do our fancy string editing stuff here

    // Write out a file with the content, character by character
    FileWriter out = new FileWriter(new File(args[1]));
    for (int i = 0; i < content.length(); i++) {
      out.write(content.charAt(i));
    }
    out.close();
  }
}
{% endhighlight %}

<p><b>Simple File I/O with the new java.nio package</b></p>

{% highlight java %}
// Simple File Input and Output using "new" I/O
// Daniel Shiffman
// Programming from A to Z, Spring 2006
// Based off of code from Java Regular Expressions by Mehran Habibi

// Input file is the first argument passed from the command line
// Output file is the second
// This could be improved with some basic error handling (what if an invalid filename is entered, etc.?)

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class SimpleFileIO {
  public static void main (String[] args) throws IOException {

    // Create an input stream and file channel
    // Using first arguemnt as file name to read in
    FileInputStream fis = new FileInputStream(args[0]);
    FileChannel fc = fis.getChannel();

    // Read the contents of a file into a ByteBuffer
    ByteBuffer bb = ByteBuffer.allocate((int)fc.size());
    fc.read(bb);
    fc.close();

    // Convert ByteBuffer to one long String
    String content = new String(bb.array());

    // Conceivably we would now mess with the string here
    // Doing all sorts of fun stuff

    // Create an output stream and file channel
    // Using second argument as file name to write out
    FileOutputStream fos = new FileOutputStream(args[1]);
    FileChannel outfc = fos.getChannel();

    // Convert content String into ByteBuffer and write out to file
    bb = ByteBuffer.wrap(content.getBytes());
    outfc.write(bb);
    outfc.close();
  }
}{% endhighlight %}

<p>Once we&#8217;ve gotten the hang of reading and writing files, we can start to think about ways of creating output text based on an input text.  For example, we could do something as simple as make a new text with every other word from a source text.   To do this, we can split the text up into an array of Strings (with space as a delimiter) and create a new String by appended every other word to it.  StringBuffer is good to use in case we are dealing with really long texts.</p>

{% highlight java %}
 //Split text by wherever there is a space
String[] words = content.split(" ");
StringBuffer everyotherword = new StringBuffer();
for (int i = 0; i < words.length; i+=2) {
   String word = words[i];
   everyotherword.append(word + " ");
}
{% endhighlight %}

<p>Using the Nigerian Spam as a source text, the result is something like:</p>

{% highlight java %}
On 12th, a contractor the co-orporation, Kingdom Olaf made time
Deposit  twelve months, at US$ (Seventeen Three Hundred fifty
Thousand only) my maturity,I a notification his address but no
After month, sent reminder finally from contract the Pertroleum
co-orporation Mr.Olaf died an accident further found that died
making WILL,and attempts his of was therefore further and
that Olaf
{% endhighlight %}

<p>Another thing we might try is to search for every time a certain word appears.   The following code examines a text for every time the word &#8220;God&#8221; appears and keeps the word &#8220;God&#8221; along with what follows it:</p>

{% highlight java %}
for (int i = 0; i < words.length; i+=2) {
   if (words[i].equals("God")) {
      gods.append(words[i] + " " + words[i+1] + "n");
   }
 }
{% endhighlight %}

<p>The result applied to Genesis from the Bible looks something like:</p>

{% highlight java %}
God Almighty
God forbid
God hath
God did
God hath
God of
God Almighty
God make
God of
God of
God meant
God will{% endhighlight %}

<p>We could also reverse all the characters in a text, by walking through the String backwards.  Note how the for loop starts at the end of the String (content.length() -1).</p>

{% highlight java %}
StringBuffer reverse = new StringBuffer();
for (int i = content.length()-1; i >= 0; i--) {
   char c = content.charAt(i);
   reverse.append(c);
}
{% endhighlight %}

<p>The result applied to the Nigerian Spam looks something like:</p>

{% highlight java %}
rof %5 dna uoy rof %53 dna em rof %06 fo oitar eht ni erahs ot su
rof tnuocca ruoy otni diap eb lliw yenom ehT .refsnart eht rof rovaf
ruoy ni noitartsinimda/etaborp fo rettel dna stnemucod yrassecen eht
niatbo ot dna LLIW eht fo noitaziraton dna gnitfard rof yenrotta na
fo secivres eht yolpme llahs eW .nik fo txen eht sa ecalp ni uoy tup
lliw taht stivadiffa dna stnemucod yrassecen eht eraperp lliw yenrotta{% endhighlight %}

<p><a name="analysis"></a></p>
<h2>Analysis</h2>
<p> <img src="http://www.shiffman.net/itp/classes/a2z/week01/flesch.jpg" class="right" /> </p>
<p>We&#8217;ll end this week by looking at a basic example of text analysis.  We will read in a file, examine some of its statistical properties, and write out a new file that will contain our report.   Our example will compute the <a href="http://en.wikipedia.org/wiki/Flesch-Kincaid_Readability_Test">Flesch Index</a>  (aka Flesch-Kincaid Reading Ease test), a numeric score that indicates the readability of a text.   The lower the score, the more difficult the text.  The higher, the easier.  For example, texts with a score of 90-100 are, say, around the 5th grade level, wheras 0-30 would be for &#8220;college graduates&#8221;.  The result of the test on a few sample texts (the Bible, spam, a New York Times article, and Processing tutorials I&#8217;m writing) are displayed to the right.  </p>
<p>The Flesch Index is computed as a function of total words, total sentences, and total syllables.  It was developed by Dr. Rudolf Flesch and modified by J. P. Kincaid (thus the joint name).  Most word processing programs (MS Word, Corel Wordperfect, etc.) will compute the Flesch Index for you, which provides us with a nice method to check our results.</p>
<p><b><i>Flesch Index = 206.835 &#8211; 1.015 * (total words / total sentences) + 84.6 * (total syllables / total words)</i></b></p>
<p>Our pseudo-code will look something like this:</p>

{% highlight java %}
1) Read input file into String object
2) Count words
3) Count syllables
4) Count sentences
5) Apply formula
6) Write out report file
{% endhighlight %}

<p>We know we can read in text from a file and store it in a Java String object as demonstrated in the example above.  Now, all we have to do is examine that String object, counting the total words, sentences, and syllables, applying the formula as a final step. To count words, we&#8217;ll use the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/StringTokenizer.html">StringTokenizer</a>.  (It should be noted that the StringTokenizer is a legacy class.   split() should be used instead.  However, before we get to next week (and for nostaglia) we&#8217;re going to solve the Flesch Index problem in a highly manual way, using the Tokenizer.  Next week, you&#8217;ll be exposed to more advanced String parsing techniques using <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/regex">regular expressions</a>.</p>
<p>The first thing we&#8217;ll do is count the number of words in the text.  We&#8217;ve seen in some of the examples above that we could accomplish this by using the &#8220;split&#8221; function, the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/StringTokenizer.html">StringTokenizer</a> works in a similar way.  To create a StringTokenizer, the constructor receives the String you want to tokenize as well as a set of delimiters (the characters that indicate where a token ends, and a new token begins.)  You may be asking, what the heck is a token??   In our case, we want to split the String up into words, so each word is one &#8220;token.&#8221;  Ok, so step one (creating the Tokenizer) looks like this:</p>

{% highlight java %}
String delimiters = ".,':;?{}[]=-+_!@#$%^&#038;*() ";
StringTokenizer tokenizer = new StringTokenizer(content,delimiters);
{% endhighlight %}

<p>We could have simplified our lives by just using space (&#8221; &#8220;) as the delimiter, but here we&#8217;re saying that any of the punctuation characters listed indicates the end of one word and the start of another.  Now we just need to march through all the words (tokens) and count their syllables.</p>

{% highlight java %}
while (tokenizer.hasMoreTokens())
{
  String word = tokenizer.nextToken();
  syllables += countSyllables(word);
  words++;
}
{% endhighlight %}

<p>Ok, so &#8220;countSyllables&#8221; isn&#8217;t a function that exists anywhere in Java.  We&#8217;re going to have to write it ourselves.   The following method is not the most accurate way to count syllables, but it will do for now.  </p>
<p><i>Syllables = total # of vowels in a word (not counting vowels that appear after another vowel and when &#8216;e&#8217; is found at the end of the word), i.e.:</i></p>
<li class="arrow">&#8220;beach&#8221; &#8211;> one syllable</li>
<li class="arrow">&#8220;banana&#8221; &#8211;> three syllables</li>
<li class="arrow">&#8220;home&#8221; &#8211;> one syllable</li>
<p>&nbsp;<br />
Our code looks like this:</p>

{% highlight java %}
// A method to count the number of syllables in a word
// Pretty basic, just based off of the number of vowels
// This could be improved
public static int countSyllables(String word) {
    int      syl    = 0;
    boolean  vowel  = false;
    int      length = word.length();

    //check each word for vowels (don't count more than one vowel in a row)
    for(int i=0; i &lt; length ; i++) {
      if        (isVowel(word.charAt(i)) &#038;&#038; (vowel==false)) {
        vowel = true;
        syl++;
      } else if (isVowel(word.charAt(i)) &#038;&#038; (vowel==true)) {
        vowel = true;
      } else {
        vowel = false;
      }
    }

    char tempChar = word.charAt(word.length()-1);
    //check for 'e' at the end, as long as not a word w/ one syllable
    if (((tempChar == 'e') || (tempChar == 'E')) &#038;&#038; (syl != 1)) {
      syl--;
    }
    return syl;
}

//check if a char is a vowel (count y)
public static boolean isVowel(char c) {
    if      ((c == 'a') || (c == 'A')) { return true;  }
    else if ((c == 'e') || (c == 'E')) { return true;  }
    else if ((c == 'i') || (c == 'I')) { return true;  }
    else if ((c == 'o') || (c == 'O')) { return true;  }
    else if ((c == 'u') || (c == 'U')) { return true;  }
    else if ((c == 'y') || (c == 'Y')) { return true;  }
    else                               { return false; }
  }
}
{% endhighlight %}

<p>Again, this could be vastly improved using Regular Expressions, but it&#8217;s nice as an exercise to learn how to do all the String manipulation manually before we move on to more advanced techniques.</p>
<p>Counting sentences is simple.  We&#8217;ll just tokenize the content using periods, question marks, exclamation points, etc. (&#8220;.:;?!&#8221;) as delimiters and count the total number of tokens.  This isn&#8217;t terribly accurate; for example, &#8220;My e-mail address is daniel.shiffman@nyu.edu.&#8221;  will be counted as three sentences.  Nevertheless, as a first pass, this will do. . .</p>

{% highlight java %}
 //look for sentence delimiters
String sentenceDelim = ".:;?!";
StringTokenizer sentenceTokenizer = new StringTokenizer(content,sentenceDelim);
sentences = sentenceTokenizer.countTokens();
{% endhighlight %}

<p>Now, all we need to do is apply the formula, generate a report. . .</p>

{% highlight java %}
//calculate flesch index
final float f1 = (float) 206.835;
final float f2 = (float) 84.6;
final float f3 = (float) 1.015;
float r1 = (float) syllables / (float) words;
float r2 = (float) words / (float) sentences;
float flesch = f1 - (f2*r1) - (f3*r2);

//Write Report
String report = "";

report += "Total Syllables: " + syllables + "n";
report += "Total Words    : " + words + "n";
report += "Total Sentences: " + sentences + "n";
report += "Flesch Index   : " + flesch + "n";
System.out.println(report);
{% endhighlight %}

<p>. . . and we&#8217;re done!</p>
<p>The full example code is here: <a href="http://www.shiffman.net/itp/classes/a2z/week01/FleschIndex.java"> FleschIndex.java</a></p>
<h2> Related Perl and PHP examples </h2>
<p><b>Perl</b>:</p>

{% highlight java %}
#!/usr/bin/perl

# Reads a text and prints out wherever "I" appears along with the word following it
# call it from the command line, like so:
# perl I.pl input.txt >> output.txt
# uses regular expressions, which we will cover next week

while (<>) {
  if ($_ =~ m/ (IsS+)/) {
    print("$1 n");
  }
}
{% endhighlight %}

<p><b>PHP</b>:<br />
Run it:  <a href="http://www.shiffman.net/itp/classes/a2z/week01/inputoutput.php">http://www.shiffman.net/itp/classes/a2z/week01/inputoutput.php</a><br />
Source:  <a href="http://www.shiffman.net/itp/classes/a2z/week01/inputoutput.phps">http://www.shiffman.net/itp/classes/a2z/week01/inputoutput.phps</a></p>
