---
title: 'Night #3: Regular Expressions in Processing'
author: Daniel
layout: post
pvc_views:
  - 4301
dsq_thread_id:
  - 513414509
categories:
  - General
tags:
  - processing.org
  - regex
---
<p>Several years ago I became somewhat obsessed with regular expressions while reading Jeffrey Friedl&#8217;s <a href="http://regex.info">Mastering Regular Expressions</a>.  At the time, I wrote <a href="http://shiffman.net/teaching/a2z/regex/">a short tutorial about regular expressions</a> for my course Programming from A to Z.  The sad truth is that if you&#8217;ve ever done regular expressions in Java, it&#8217;s pretty darn awkward compared to, say, python or perl.  The good news is there are some nice regex helper functions in Processing that can make it a bit easier.  Before we get to that let&#8217;s start with the Java API: </p>
<ul>
<li><a href="http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html">Pattern</a> &#8212; a compiled representation of a regular expression.</li>
<li><a href="http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html">Matcher</a> &#8212; an engine that performs match operations on a character sequence (or String) by interpreting a Pattern.</li>
</ul>
<p>An example of Pattern and Matcher in Java (which you can write directly into Processing) looks like the following:</p>

{% highlight java %}
String inputtext = "This is a test of regular expressions.";  // Input text
String regex = "test";              // The regular expression to match
Pattern p = Pattern.compile(regex); // Making a Pattern object with the regex 
Matcher m = p.matcher(inputtext);   // Matching that regex in the input string
if (m.find()) {
  System.out.println(m.group());     // Here's the match!
} else {
  System.out.println("No match!");   // No match!
}
{% endhighlight %}

<p>Of course, in most cases, you want to do something more sophisticated where you iterate over many matches.</p>

{% highlight java %}
// Regex that matches double words
// Ugh, look at all these double back slashes!!
String regex = "\\b(\\w+)\\b\\W+\\1";   

Pattern p = Pattern.compile(regex);     // Compile Regex
Matcher m = p.matcher(content);         // Create Matcher
while (m.find()) {
  System.out.println(m.group());
}
{% endhighlight %}

<p>Processing provides some regex helper functions that wrap all of this Java Pattern/Matcher stuff.  They are <a href="http://processing.org/reference/match_.html">match()</a> and <a href="http://processing.org/reference/matchAll_.html">matchAll()</a>.  </p>
<p>The match() function is used to apply a regular expression to a piece of text, and return matching groups (elements found inside parentheses) as a String array. If there is no match, the function will return null. If no groups are specified in the regular expression, but the sequence matches, an array of length one (with the matched text as the first element of the array) will be returned. </p>
<p>Here&#8217;s an example (this is straight from the reference page).</p>

{% highlight java %}
String s = "Inside a tag, you will find <tag>content</tag>.";
String[] m = match(s, "<tag>(.*?)</tag>");
println("Found '" + m[1] + "' inside the tag.");
// Prints to the console:
// "Found 'content' inside the tag."
{% endhighlight %}

<p>The matchAll() function is at first a bit confusing because it returns a two dimensional array.  But if you look right back to how match() works, it&#8217;s pretty simple.  match() assumes you want just one match, and gives you an array, a list of all the groups for that single match.  matchAll() assumes you want all the matches, so it gives you a bunch of those arrays, one for every match.   What&#8217;s an array of an array?  A two dimensional array!  The first dimension is the match itself, and the second dimension is the group for that match, i.e.</p>

{% highlight java %}
String s = "Some tags! <tag>one</tag> <tag>two</tag> <tag>three</tag>.";

// Match this regular expression
String[][] m = matchAll(s, "<tag>(.*?)</tag>");
// Loop through all the matches     
for (int i = 0; i < m.length; i++) {
  // Print out group 1 for each match                
  println("Found '" + m[i][1] + "' inside a tag."); 
}
{% endhighlight %}

<p>This new example uses a regex that matches anything inside an HTML href tag and draws it the screen.</p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/Regex.zip'><img src="http://shiffman.net/wp/wp-content/uploads/2011/12/regex.png" alt="" title="regex" width="500" height="298" class="alignnone size-full wp-image-994" /></a></p>
<p><a href='http://shiffman.net/wp/wp-content/uploads/2011/12/Regex.zip'>Regex.zip</a></p>
