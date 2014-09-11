---
title: Regular Expressions
author: Daniel
layout: page
dsq_thread_id:
  - 249553164
pvc_views:
  - 25117
---

## This week&#8217;s topics:
* [Regular Expressions](#regex)
* [Testing regex with egrep](#egrep)
* [Regex in JavaScript](#jsregex)
* [Strings in JavaScript](#strings)
* [Splitting with Regex](#splitting)
* [Simple Text Analysis](#analysis)
* [Search and Replace](#searchreplace)

## Examples:
* [RegexHelloWorld](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/00_Processing_to_p5.js)
* [Doublewords](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/01_objects_in_JS)
* [VowelCounter](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/02_DOM_p5)
* [WordSplitterRegex](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/03_Strings)
* [ReplaceDemo1](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/04_fileinput)
* [ReplaceDemo2](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/05_p5_text)
* [ReplaceBackReference]
* [HTMLTagRemover]
* sample input files

## Related references
* [Chapter 1, Mastering Regular Expressions](http://safari.oreilly.com/0596002890/mastregex2-CHP-1)
* . . .
* regex game http://www.javaregex.com/agame.html

## Exercise ideas
* Write a regular expression that matches any e-mail address.
* Take that regular expression and do a search and replace so that any e-mail address is made into a &#8220;mailto:&#8221; link.</li>
<li>Challenging:  Examine the HTML tag remover example.  Can you write a regular expression that searches for text in an HTML page, but ignores any text inside HTML tags, while leaving them intact?
* Challenging:  Examine the HTML tag remover example.  Can you write a regular expression that searches for text in an HTML page, but ignores any text inside HTML tags, while leaving them intact?

<p><a name ="regex"></a></p>

<a name ="beyond"></a>
## Regular Expressions

***WARNING: This is a woefully incomplete overview of regular expressions.  It would be absurd to try to fully cover the topic in a short handout like this.  Hopefully, this will provide some of the basics to get you started, but to really understand regular expressions, I implore you to read as much of [Mastering Regular Expressions](http://safari.oreilly.com/0596002890/) by Jeffrey E.F. Friedl as you have time for.***

A **[regular expression](http://en.wikipedia.org/wiki/Regular_expression)** is a sequence of characters that describes or matches a given amount of text.  For example, the sequence <span class="regex">bob</span>, considered as a regular expression, would match any occurance of the word &#8220;bob&#8221; inside of another text.    The following is a rather rudimentary introduction to the basics of regular expressions.  We could spend the entire semester studying regular expressions if we put our mind to it. . . Nevertheless, we&#8217;ll just have a basic introduction to them this week and learn more advanced technique as we explore different text processing applications over the course of the semester.</p>
<p>A truly wonderful book written on the subject is: <a href="http://regex.info/">Mastering Regular Expressions</a> by Jeffrey Friedl.   Chapter 1, available via the Safari Network (through NYU) can be found here:</p>
<p><a href=" http://safari.oreilly.com/0596002890/mastregex2-CHP-1"> http://safari.oreilly.com/0596002890/mastregex2-CHP-1</a></p>
<p>Regular expressions (sometimes referred to as &#8216;regex&#8217; for short) have both literal characters and meta characters.  In <span class="regex">bob</span>, all three characters are literal, i.e. the &#8216;b&#8217; wants to match a &#8216;b&#8217;, the &#8216;o&#8217; an &#8216;o&#8217;, etc.    We might also have the regular expression:</p>
<p><span class="regex">^bob</span></p>
<p>In this case, the &#8216;^&#8217; is a meta character, i.e. it does not want to match the character &#8216;^&#8217;, but instead indicates the &#8220;beginning of a line.&#8221;  In other words the regex above would find a match in:</p>
<p><i>bob goes to the park.</i></p>
<p>but would not find a match in:</p>
<p><i>jill and bob go to the park.</i></p>
<p>Here are a few common meta-characters (I&#8217;m listing them below as they would appear in a Java regular expression, which may differ slightly from perl, php, .net, etc.) used to get us started:  </p>
<p><b>Position Metacharacters:</b></p>

{% highlight java %}
^     beginning of line
$     end of line
\b    word boundary
\B    a non word boundary
{% endhighlight %}

<p><b>Single Character Metacharacters:</b></p>

{% highlight java %}
.     any one character
\d    any digit from 0 to 9
\w    any word character (a-z,A-Z,0-9)
\W    any non-word character
\s    any whitespace character (tab, new line, form feed, end of line, carriage return)
\S    any non whitespace character
{% endhighlight %}

<p><b>Quantifiers (refer to the character that precedes it):</b></p>

{% highlight java %}
?     appearing once or not at all
*     appearing zero or more times
+     appearing one or more times
{min,max} appearing within the specified range
{% endhighlight %}

<p>Using the above, we could come up with some quick examples:</p>
<p><span class="regex">^$</span> &#8211;> matches beginning of line followed by end of line, i.e. match any blank line!</p>
<p><span class="regex">ingb</span> &#8211;> matches &#8216;ing&#8217; followed by a word boundary, i.e. any time &#8216;ing&#8217; appears at the end of a word!</p>
<p><b>Character Classes</b> allow one to do an &#8220;or&#8221; statement amongst individual characters and are denoted by characters enclosed in brackets, i.e. <span class="regex">[aeiou]</span> means match any vowel.  Using a &#8220;^&#8221; negates the character class, i.e. <span class="regex">[^aeiou]</span> means match any character not a vowel (note this isn&#8217;t just limited to letters, it really means <i>anything at all</i> that is not an a, e, i, o, or u.)  A hyphen indicates a range of characters, such as <span class="regex">[0-9]</span> or <span class="regex">[a-z]</span>.</p>
<p>Another key metacharacter is |, meaning or.  This is known as the concept of <b>Alternation</b>.  </p>
<p> <span class="regex">John | Jon</span> -> match &#8220;John&#8221; or Jon&#8221;</p>
<p>note: this regex could also be written as <span class="regex">Joh?n</span>, meaning match &#8220;Jon&#8221; with an option &#8220;h&#8221; between the &#8220;o&#8221; and &#8220;n.&#8221;</p>
<p>Parentheses can also be used to constrain the alternation, i.e.:</p>
<p><span class="regex"> (212|646|917)d*</span>  matches any sequence of zero or more digits preceded by 212, 646, or 917 (presumably to retrieve phone #&#8217;s with NYC area codes).  Note this regular expression would need to be improved to take into consideration white spaces and/or punctuation.</p>
<p>Parentheses also serve the purpose of capturing groups for back-references.  For example, examine the following regular expression:</p>
<p><span class="regex">b([0-9A-Za-z]+)s+1b</span></p>
<p>The first part of the expression without parentheses would read: <span class="regex">b([0-9A-Za-z]+)</span> meaning match any &#8220;word&#8221; containing at least one or more letters/digits.  The next part <span class="regex">s+</span> means any sequence of at least one white space.  The third part <span class="regex">1</span> says match whatever you matched that was enclosed inside the first set of parentheses, i.e. <span class="regex">([0-9A-Za-z]+)</span>.   So, thinking this over, what will this regular expression match in the following line:</p>

{% highlight java %}
This is really really super super duper duper fun.  Fun!
{% endhighlight %}

<p><a name ="egrep"></a></p>
<h2>egrep</h2>
<p><a href="http://en.wikipedia.org/wiki/Grep">grep</a> is a unix command line utility that takes an input file, a regular expression and outputs the lines that contain matches for that regular expression.  It&#8217;s a quick way for us to test some regexes (and we can use it on ITP&#8217;s server or on any Mac OS X machine.)  As a point of history, the name comes from the form &#8220;g/re/p&#8221; which stands for &#8220;Global Regular Expression Print.&#8221;  We&#8217;ll be used egrep, which allows for more sophisticated regular expression searches.  (Note: the examples below use a slightly different regex &#8220;flavor&#8221; than what we will see in Java.  This is something we&#8217;ll have to get used to, and will likely cause a bit of confusion.  Not to worry, confusion over regular expression flavors is extremely normal.  No need to seek professional help.)</p>
<p>The syntax is simple:</p>
<p><b><i>egrep  -flags &#8216;regexpattern&#8217; filename</i></b></p>
<p>If we want to output a file:</p>
<p><b><i>egrep  -flags &#8216;regexpattern&#8217; filename >> outputfilename</i></b></p>

{% highlight java %}
%  egrep -i 'four' bible.txt
%  egrep -i 'five' bible.txt
{% endhighlight %}

<p><img src="http://shiffman.net/itp/classes/a2z/week02/four.jpg"/> <img src="http://shiffman.net/itp/classes/a2z/week02/five.jpg"/></p>
<p>The -i flag indicates that the match should be case-insensitive.  You can find full documentation for the &#8220;egrep&#8221; command here (with full flags): <a href="http://www.unet.univie.ac.at/aix/cmds/aixcmds2/egrep.htm">http://www.unet.univie.ac.at/aix/cmds/aixcmds2/egrep.htm</a>.</p>
<p>Let&#8217;s look at some other examples (special thanks to <a href="http://regex.info">Friedl&#8217;s Mastering Regular Expressions</a>).</p>
<p>Match URL&#8217;s:</p>

{% highlight java %}%  egrep -i 'http://[^ ]*' a2z.txt{% endhighlight %}

<p>(run this with the following sample file: <a href="http://shiffman.net/itp/classes/a2z/week02/a2z.txt">a2z.txt</a>)</p>
<p>Match double words:</p>

{% highlight java %}%  egrep -i '\< (w+) +\1\>' doubletext.txt{% endhighlight %}

<p>(run this with the following sample file: <a href="http://shiffman.net/itp/classes/a2z/week02/a2z.txt">doubletext.txt</a>)</p>
<p>(Note, in the above example, the metacharacter <span class="regex">< </span> means &#8220;start of word boundary&#8221; and </span><span class="regex">></span> means &#8220;end of word boundary.&#8221;  This is different than the <span class="regex">b</span> we&#8217;ll find in Java.</p>
<p><a name ="java"></a></p>
<h2>Regular Expressions in Java</h2>
<p>With Java 1.4, Sun introduced the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/package-summary.html">java.util.regex</a> package.  Having regex support come standard with Java is a great thing, and there are many advantages to working with regexes in a robust object-oriented environment.  Nevertheless, unlike with Perl (where regexes are a low-level component of the language), using regexes in Java can prove to be a bit awkward.  The following will offer a brief overview of using regexes in Java, for more information I would suggest reading Chapter 8 of <a href="http://regex.info">Mastering Regular Expressions</a>, the book <a href="http://www.amazon.com/gp/product/1590591070/">Java Regular Expressions</a>, and the <a href="http://java.sun.com/docs/books/tutorial/extra/regex/">online Sun tutorial</a>.</p>
<h2>Making a String into a Regular Expression</h2>
<p>Perl accepts normal strings as regular expressions, which makes life lovely.  With Java, however, a regular expression is a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html">Pattern</a> object that is made with a String.  We have to deal with Java&#8217;s own String metacharacters when putting together a String that will be used as a Regular Expression.   In other words, in Java if you use a backslash in a String, it will be considered as a metacharacter, i.e.: </p>

{% highlight java %}String newline = "\n";{% endhighlight %}

<p>To actually have a backslash in a regular expression, we need to escape it with another backslash, i.e.:</p>

{% highlight java %}String newlineregex = "\\n";{% endhighlight %}

<p>Conceptually, it might take us a moment to wrap our heads around this distinction, nevertheless, functionally, in Java, the solution is simple:  whenever you want to have backslash in your regex, use 2!</p>
<p>Ok, moving on to using a regex in Java, our program must impor the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/package-summary.html">java.util.regex</a> package:</p>

{% highlight java %}import java.util.regex.*{% endhighlight %}

<p>The classes we will use are as follows:</p>
<ul>
<li><a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html">Pattern</a> &#8212; a compiled representation of a regular expression.</li>
<li><a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Matcher.html">Matcher</a> &#8212; an engine that performs match operations on a character sequence (or String) by interpreting a Pattern.</li>
</ul>
<p>Our first regex program will follow this pseudo-code:</p>
<ul>
<li> 1 &#8212; Get input text that we want to match (presumably we would use the <a href="http://shiffman.net/teaching/programming-from-a-to-z/week-1-a-to-z/#file">File I/O</a> samples demonstrated last week.)</li>
<li>2 &#8212; Create a String representation of a regex</li>
<li>3 &#8212; Compile regex into a Pattern object</li>
<li>4 &#8212; Create a matcher object from Pattern by handing it input text</li>
<li>5 &#8212; Test if matcher has a match, if it does &#8211;> display match</li>
<li>6 &#8212; If matcher has no match &#8211;> display &#8220;no match&#8221;</li>
</ul>
<p>Ok, let&#8217;s take a look at the actual code:</p>

{% highlight java %}
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
{% endhighlight %}

<p>Note the use of the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Matcher.html#find()">find()</a> method, which attempts to find the next subsequence of the input sequence that matches the pattern (returns true or false based on whether it finds something) and <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Matcher.html#group()">group()</a> which returns the input subsequence captured by the given group during the previous match operation.</p>
<p>If we want to look for multiple matches, we can simply use a &#8220;while&#8221; loop instead of an &#8220;if&#8221;:</p>

{% highlight java %}
String regex = "\\b(\\w+)\\b\\W+\\1";   // Regex that matches double words
Pattern p = Pattern.compile(regex);     // Compile Regex
Matcher m = p.matcher(content);         // Create Matcher
while (m.find()) {
  System.out.println(m.group());
}
{% endhighlight %}

<p>We can also add flags when compiling the regex.  For example, if we want to have a case insensitive regex:</p>

{% highlight java %}Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);{% endhighlight %}

<p>Two flags can be added using the bitwise OR, i.e. | </p>

{% highlight java %}Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.COMMENTS);{% endhighlight %}

<p>It&#8217;s easy to notice, how easy it would be to improve the <a href="http://shiffman.net/teaching/programming-from-a-to-z/week-1-a-to-z/#analysis">Flesch Index</a> example from last week.  For example, we could use a regular expression to very quickly count vowels:</p>

{% highlight java %}
String regex = "[aeiou]";
Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
int vowelcount = 0;
Matcher m = p.matcher(content);         // Create Matcher
while (m.find()) {
  vowelcount++;
}
System.out.println("Total vowels: " + vowelcount);
{% endhighlight %}

<p><a name="split"></a></p>
<h2>Splitting with Regular Expressions</h2>
<p>It should briefly be noted that the <a href="http://shiffman.net/teaching/programming-from-a-to-z/week-1-a-to-z/#string">split function we examined last week</a> actually takes a regular expression as an argument.  An input String is split into an array wherever any part of that input String that matches that regular expression.  For example. . . </p>

{% highlight java %}
String regex = "\\W";  // Use any "non-word character" as a delimiter
String[] words = content.split(regex);
System.out.println("Total words: " + words.length);
{% endhighlight %}

<p>. . .is a very quick way to use regular expressions to count the # of words (This method is not perfect by any means.)</p>
<p><a name="searchreplace"></a></p>
<h2>Search and Replace</h2>
<p>Running a search and replace is one of the more powerful things one can do with regular expressions.  In Java, it&#8217;s simple.  The String function itself has a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html#replaceAll(java.lang.String,%20java.lang.String)">replaceAll()</a> method built-in.  The method takes two arguments, a regex and a replacement String.  Wherever there is a regex match, it is replaced with the String provided, i.e.:</p>

{% highlight java %}
String input = "Replace every time the word "the" appears with the word ze.";
String regex = "\\bthe\\b";  // Use any "non-word character" as a delimiter
String output = input.replaceAll(regex,"ze");
{% endhighlight %}

<p>Output yields: <i>Replace every time ze word &#8220;ze&#8221; appears with ze word ze.</i></p>
<p>The <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Matcher.html#replaceAll(java.lang.String)">replaceAll()</a> method is also available in the Matcher class, i.e.:</p>

{% highlight java %}
String input = "Replace every time the word "the" appears with the word ze.";
String regex = "\\bthe\\b";  // Use any "non-word character" as a delimiter
Pattern p = Pattern.compile(regex);
Matcher m = p.matcher(input);
String output = m.replaceAll("ze");
{% endhighlight %}

<p>We can also reference the matched text using a backreference in the substitution string.  A backreference to the entire match is indicated as <span class="regex">$0</span>.  If there are capturing parentheses, you can reference specifics groups as <span class="regex">$1</span>, <span class="regex">$2</span>, etc. . . </p>

{% highlight java %}
String input = "Anytime a sequence of one or more vowels appears, n" +
               "we're going to double the vowels.";
String regex = "[aeiou]+";  //
String output = input.replaceAll(regex, "$0$0");
{% endhighlight %}

<p>Output yields:<br />
<i>Anytiimee aa seequeuencee oof oonee oor mooree vooweels aappeaears, wee&#8217;ree goioing too dououblee thee vooweels.</i></p>
<p>The closing example from this week using a regular expression to remove all HTML tags from a source file.  A nice way to write regular expressions is to start with an exact text and then slowly generalize it, i.e.:</p>
<p>Let&#8217;s start with the regular expression:</p>
<p><span class="regex">&lt;table&gt;</span></p>
<p>Ok, now let&#8217;s generalize it to be:</p>
<p><span class="regex">< wwwww></span><br />
(less than followed by 5 word characters followed by a greater than)</p>
<p>Well, this can be further generalized to:</p>
<p><span class="regex">< w*></span></p>
<p>But really we should allow for white spaces, punctuation, and other characters inside the opening and closing brackets.  Basically, we want to allow for any character that is not &#8220;>&#8221;!</p>
<p><span class="regex">< [^>]*></span></p>
<p>The code to replace this match with nothing is then:</p>

{% highlight java %}
// A Regex to match anything in between <>
// Reads as: Match a "< "
// Match one or more characters that are not ">"
// Match "< ";
String tagregex = "<[^>]*>";
Pattern p2 = Pattern.compile(tagregex);
Matcher m2 = p2.matcher(content);
count = 0;
// Just counting all the tags first
while (m2.find()) {
  //System.out.println(m.group());
  count++;
}
// Replace any matches with nothing
content = m2.replaceAll("");
System.out.println("Removed " + count + " other tags."); {% endhighlight %}

<h2>Related Perl / PHP Examples</h2>
<p><b>Perl version of the vowel doubler:</b></p>

{% highlight java %}
#!/usr/bin/perl

undef $/;    # File "slurp" mode
$stuff = <>; # read in the first file

# double any vowel occurences
# g -- global
# i -- case insensitive
$stuff =~ s/([aeiou]+)/$1$1/g;

print $stuff;
{% endhighlight %}

<p><b>PHP</b>:<br />
Run it:  <a href="http://shiffman.net/itp/classes/a2z/week02/voweldoubler.php">http://shiffman.net/itp/classes/a2z/week02/voweldoubler.php</a><br />
Source:  <a href="http://shiffman.net/itp/classes/a2z/week02/voweldoubler.phps">http://shiffman.net/itp/classes/a2z/week02/voweldoubler.phps</a></p>
