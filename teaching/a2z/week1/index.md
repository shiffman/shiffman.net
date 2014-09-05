---
title: Week 1 A to Z
author: Daniel
layout: page
dsq_thread_id:
  - 249553138
pvc_views:
  - 5436
---

## This week&#8217;s topics:
* Beyond Processing and into JavaScript and p5.js
* JavaScript 101
* Strings in JavaScript
* File I/0
* Simple Analysis

## Examples:


## Related Links:

<a name ="beyond"></a>
## Beyond Processing and into JavaScript and p5.js

[p5.js](http://p5js.org). created by Lauren McCarthy, is a JavaScript library that starts with the original goal of Processing, to make coding accessible for artists, designers, educators, and beginners, and reinterprets this for the web.  While we are not going to use p5.js exclusively in this class, it will serve as a good foundation for getting up and running with JavaScript to make browser-based text experiments.

The p5.js editor (created by Sam Lavigne) is currently in development, for your assignments you are welcome to try out the [alpha](https://github.com/antiboredom/jside/releases/download/v0.1.7/p5.zip).  You can help by posting [feedback and bugs](https://github.com/antiboredom/jside/issues) Support for Windows and Linux coming soon, along with more [features](https://github.com/antiboredom/jside/labels/enhancement).  [Download the current release](https://github.com/antiboredom/jside/releases/download/v0.1.7/p5.zip).

If you prefer, you could also just use a text editor.  Here are some options:

  * [Sublime](http://www.sublimetext.com/2) -- recommended, need a license or deal with pop ups
  * [TextWrangler](http://www.barebones.com/products/textwrangler/) -- free
  * [brackets.io](http://brackets.io/) -- free, dynamic code update, a little more confusing gui at first

With a text editor, you'll want to run a local server (Python SimpleHTTPServer or Apache) to test your work.

  * [Tutorial: setting up a local server](https://github.com/lmccart/p5.js/wiki/Local-server)
  * [Tutorial: MacOS Python SimpleHTTPServer steps](https://github.com/lmccart/itp-creative-js/wiki/SimpleHTTPServer)

Basic p5.js sketches look a lot like Processing code, just in JavaScript.  There are some key differences, however.  [This tutorial](https://github.com/lmccart/p5.js/wiki/Processing-transition) outlines those differences and [this tutorial](https://github.com/lmccart/p5.js/wiki/Getting-Started) will walk you through the basics of getting started.

You'll also want to get used to using the console and other developer tools in the browser.  Here's some [documentation for Chrome](https://developer.chrome.com/extensions/tut_debugging)).  For Firefox, check out this [Firebug tutorial](http://www.developerfusion.com/article/139949/debugging-javascript-with-firebug/).

You might also consider using [JSFiddle](http://jsfiddle.net/) for quick and dirty experiments.  Here is a [sample JSFiddle using p5.js](http://jsfiddle.net/shiffman/cLVHA/) To get a fiddle to work, you need to reference [the p5.js CDN](http://cdnjs.com/libraries/p5.js) as an external resource and select "No-Library (pure JS)" and "no wrap" under options.

<a name ="js101"></a>
## JavaScript 101

(This is just a list of what I'll be covering in class, write-up coming soon).

* File setup, using `<script>`

* Variables and data types 
    * Numbers
    * Strings
        * [Built-in JS String functions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String)
        * [p5 string functions](https://github.com/lmccart/p5.js/wiki/API#string-functions)
    * Booleans
    * Functions
    * Objects
    * Arrays
    * Null
    * Undefined

* Operators
    * Arithmetic: `+`, `-`, `*`, `/`, `%`, `++`, `--`
    * Equality: `==`, `===`, `!=`, `!==`
    * Relational: `<`, `>`, `<=`, `>=`
    * String: `+`, equality, relational
    * Logical: `&&`, `||`, `!`

* Logic
    * `if`, `else if`, `else`
    * `switch`, `case`
    * `for(var i=0;...)` vs `for (var k in arr)`
    * `while`, `do...while`
    * `break`, `continue`

* Arrays
    * Creating, initializing, setting, accessing elements
    * [Built-in JS array functions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array)

* Objects
    * Setting and accessing properties and methods

* Functions
    * Function definition
    * Passing in args
    * `return`

* Scope

<a name ="strings"></a>
## Strings in JS

In all of our JavaScript programs, we'll be using [String objects](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String) to store textual information.   You may be familiar with [Strings in Processing](http://processing.org/reference/String.html) or poked around the [Javadoc reference for Strings](http://docs.oracle.com/javase/7/docs/api/java/lang/String.html).  Strings in JavaScript have a lot of the same functionality that they do in Java and we'll start by looking at some of the basic methods for manipulating Strings in JS.

A String, at its core, is really just a fancy way of storing an array of characters.  With the String object, we might find ourselves writing code like.

{% highlight javascript %}
var sometext = ['h','e','l','l','o'];
{% endhighlight %}

Interestingly enough, there is no distinction between an individual character or a String in JS.  Both of the variables below are storing the same datatype.

```
var a = 'a';
var h = 'hello';
```

In JavaScript, Strings can be literal primitives or objects.

```
var s1 = 'hello';               // a primitive
var s2 = new String('hello');   // an object
```

For the most part, this is a distinction we don't have to worry about.  JS will automatically covert our primitive String into an object when necessary. In general, it's good practice to initialize your Strings as primtives to increase performance.

## Manipulating Strings

JavaScript provides us with a basic set of String functions that allow for simple manipulation and analysis.  <a href="http://shiffman.net/teaching/a2z/regex">Next week</a>, we&#8217;ll also look at how <a href="http://en.wikipedia.org/wiki/Regular_expression">regular expressions</a> can allow to perform advanced String processing, but we'll start this week with non-regex String methods and gather some skills doing all of our text processing manually, character by character.   All of the availabe String properties and functions are laid out in [the JavaScript reference](ttp://docs.oracle.com/javase/7/docs/api/java/lang/String.html), and we'll explore a few useful ones here.  Let&#8217;s take a closer look at three: indexOf(), substring(), and the length property.

`indexOf()` locates a sequence of characters within a string. For example, run this code and examine the result:

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
<p> <img src="http://shiffman.net/itp/classes/a2z/week01/flesch.jpg" class="right" /> </p>
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

<p>We know we can read in text from a file and store it in a Java String object as demonstrated in the example above.  Now, all we have to do is examine that String object, counting the total words, sentences, and syllables, applying the formula as a final step. To count words, we&#8217;ll use the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/StringTokenizer.html">StringTokenizer</a>.  (It should be noted that the StringTokenizer is a legacy class.   split() should be used instead.  However, before we get to next week (and for nostaglia) we&#8217;re going to solve the Flesch Index problem in a highly manual way, using the Tokenizer.  Next week, you&#8217;ll be exposed to more advanced String parsing techniques using <a href="http://shiffman.net/teaching/programming-from-a-to-z/regex">regular expressions</a>.</p>
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
<p>The full example code is here: <a href="http://shiffman.net/itp/classes/a2z/week01/FleschIndex.java"> FleschIndex.java</a></p>
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
Run it:  <a href="http://shiffman.net/itp/classes/a2z/week01/inputoutput.php">http://shiffman.net/itp/classes/a2z/week01/inputoutput.php</a><br />
Source:  <a href="http://shiffman.net/itp/classes/a2z/week01/inputoutput.phps">http://shiffman.net/itp/classes/a2z/week01/inputoutput.phps</a></p>
