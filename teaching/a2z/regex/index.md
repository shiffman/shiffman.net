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
* [Splitting with Regex](#splitting)
* [Search and Replace](#searchreplace)

## Plain JS Examples:
* [Basic Regex: exec()](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week2_regex/regex_node/regex_helloworld1.js)
* [Basic Regex: match()](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week2_regex/regex_node/regex_helloworld2.js)
* [VowelCounter](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week2_regex/regex_node/vowelcounter.js)
* [WordSplitterRegex](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week2_regex/regex_node/split.js)
* [Simple replace](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week2_regex/regex_node/replace1.js)
* [Replace back reference](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week2_regex/regex_node/replace2.js)

## P5 examples
* [Find double words](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/doublewords)
* [Double the vowels](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/voweldoubler)

## Related references
* [Chapter 1, Mastering Regular Expressions](http://safari.oreilly.com/0596002890/mastregex2-CHP-1)
* [Guide to regex in JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions)
* [Eloquent JavaScript Regular Expressions](http://eloquentjavascript.net/09_regexp.html)

## Exercise ideas
* Write a regular expression that matches any e-mail address.
* Take that regular expression and do a search and replace so that any e-mail address is made into a &#8220;mailto:&#8221; link.
* Create an example that reads an HTML page and removes any markup and leaves only the raw content.

<a name ="regex"></a>
## Regular Expressions 

<p><div style="background: #DDDDDD; padding: 18px"><em>WARNING: This is a woefully incomplete overview of regular expressions.  It would be absurd to try to fully cover the topic in a short handout like this.  Hopefully, this will provide some of the basics to get you started, but to really understand regular expressions, I suggest you to read as much of <a href="http://safari.oreilly.com/0596002890/">Mastering Regular Expressions</a> by Jeffrey E.F. Friedl as you have time for.  In addition, the <a href="http://eloquentjavascript.net/09_regexp.html">regular expressions chapter in Eloquent JavaScript</a> is more comprehensive than below.</em></div></p>

A **[regular expression](http://en.wikipedia.org/wiki/Regular_expression)** is a sequence of characters that describes or matches a given amount of text.  For example, the sequence <span class="regex">bob</span>, considered as a regular expression, would match any occurance of the word &#8220;bob&#8221; inside of another text. The following is a rather rudimentary introduction to the basics of regular expressions.  We could spend the entire semester studying regular expressions if we put our mind to it. Nevertheless, we&#8217;ll just have a basic introduction to them this week and learn more advanced technique as we explore different text processing applications over the course of the semester.

A truly wonderful book written on the subject is: <a href="http://regex.info/">Mastering Regular Expressions</a> by Jeffrey Friedl.  I would recommend at least reading [Chapter 1](http://safari.oreilly.com/0596002890/mastregex2-CHP-1).

Regular expressions (referred to as &#8216;regex&#8217; for short) have both literal characters and meta characters.  In <span class="regex">bob</span>, all three characters are literal, i.e. the &#8216;b&#8217; wants to match a &#8216;b&#8217;, the &#8216;o&#8217; an &#8216;o&#8217;, etc.    We might also have the regular expression:

<span class="regex">^bob</span>

In this case, the &#8216;^&#8217; is a meta character, i.e. it does not want to match the character &#8216;^&#8217;, but instead indicates the &#8220;beginning of a line.&#8221;  In other words the regex above would find a match in:

<i>bob goes to the park.</i>

but would not find a match in:

<i>jill and bob go to the park.</i>

Here are a few common meta-characters (I&#8217;m listing them below as they would appear in a Java regular expression, which may differ slightly from perl, php, .net, etc.) used to get us started:

### Position Metacharacters:

```
^     beginning of line
$     end of line
\b    word boundary
\B    a non word boundary
```

### Single Character Metacharacters:

```
.     any one character
\d    any digit from 0 to 9
\w    any word character (a-z,A-Z,0-9)
\W    any non-word character
\s    any whitespace character (tab, new line, form feed, end of line, carriage return)
\S    any non whitespace character
```

### Quantifiers (refer to the character that precedes it):

```
?         appearing once or not at all
*         appearing zero or more times
+         appearing one or more times
{min,max} appearing within the specified range
```

Using the above, we could come up with some quick examples:

* <span class="regex">^$</span> &#8211;> matches beginning of line followed by end of line, i.e. match any blank line!
* <span class="regex">ingb</span> &#8211;> matches &#8216;ing&#8217; followed by a word boundary, i.e. any time &#8216;ing&#8217; appears at the end of a word!

**Character Classes** allow one to do an &#8220;or&#8221; statement amongst individual characters and are denoted by characters enclosed in brackets, i.e. <span class="regex">[aeiou]</span> means match any vowel.  Using a &#8220;^&#8221; negates the character class, i.e. <span class="regex">[^aeiou]</span> means match any character not a vowel (note this isn&#8217;t just limited to letters, it really means <i>anything at all</i> that is not an a, e, i, o, or u.)  A hyphen indicates a range of characters, such as <span class="regex">[0-9]</span> or <span class="regex">[a-z]</span>.

Another key metacharacter is |, meaning or.  This is known as the concept of **Alternation**.

<span class="regex">John | Jon</span> -> match &#8220;John&#8221; or Jon&#8221;

Note: this regex could also be written as <span class="regex">Joh?n</span>, meaning match &#8220;Jon&#8221; with an option &#8220;h&#8221; between the &#8220;o&#8221; and &#8220;n.&#8221;

Parentheses can also be used to constrain the alternation, i.e.:

<span class="regex"> (212|646|917)\d*</span>  matches any sequence of zero or more digits preceded by 212, 646, or 917 (presumably to retrieve phone #&#8217;s with NYC area codes).  Note this regular expression would need to be improved to take into consideration white spaces and/or punctuation.

Parentheses also serve the purpose of capturing groups for back-references.  For example, examine the following regular expression: <span class="regex">b([0-9A-Za-z]+)s+1b</span>.

The first part of the expression without parentheses would read: <span class="regex">b([0-9A-Za-z]+)</span> meaning match any &#8220;word&#8221; containing at least one or more letters/digits.  The next part <span class="regex">s+</span> means any sequence of at least one white space.  The third part <span class="regex">1</span> says match whatever you matched that was enclosed inside the first set of parentheses, i.e. <span class="regex">([0-9A-Za-z]+)</span>.   So, thinking this over, what will this regular expression match in the following line:

*This is really really super super duper duper fun.  Fun!*

<a name ="egrep"></a>
## Testing regex with egrep

[grep](http://en.wikipedia.org/wiki/Grep) is a unix command line utility that takes an input file, a regular expression and outputs the lines that contain matches for that regular expression.  It&#8217;s a quick way for us to test some regexes.  As a point of history, the name comes from the form &#8220;g/re/p&#8221; which stands for &#8220;Global Regular Expression Print.&#8221;  We&#8217;ll be used egrep, which allows for more sophisticated regular expression searches.  (Note: the examples below use a slightly different regex &#8220;flavor&#8221; than what we will see in JavaScript.  This is something we&#8217;ll have to get used to, and will likely cause a bit of confusion.  Not to worry, confusion over regular expression flavors is extremely normal.  No need to seek professional help.)</p>

The syntax is simple:

```
egrep  -flags 'regexpattern' filename
```

If we want to output a file:

```
egrep  -flags 'regexpattern' filename >> outputfilename
```

```
%  egrep -i 'four' bible.txt
%  egrep -i 'five' bible.txt
```

![regex four](http://shiffman.net/itp/classes/a2z/week02/four.jpg) ![regex four](http://shiffman.net/itp/classes/a2z/week02/five.jpg)

The `-i` flag indicates that the match should be case-insensitive.  You can find  documentation for [the &#8220;egrep&#8221; command here](http://www.unet.univie.ac.at/aix/cmds/aixcmds2/egrep.htm) (with a full list of flags).

Let&#8217;s look at some other examples (special thanks to [Friedl&#8217;s Mastering Regular Expressions](http://regex.info)).

### Match URL&#8217;s:

```
egrep -i 'http://\S*' a2z.txt
```
(run this with the following sample file: [a2z.txt](http://shiffman.net/itp/classes/a2z/week02/a2z.txt).

### Match double words:

```
egrep -i '\<(\w+)\s+\1\>' doubletext.txt
```
(run this with the following sample file: [doubletext.txt](http://shiffman.net/itp/classes/a2z/week02/doubletext.txt)).

(Note, in the above example, the metacharacter <span class="regex">\<</span> means &#8220;start of word boundary&#8221; and </span><span class="regex">\></span> means &#8220;end of word boundary.&#8221;  This is different than the <span class="regex">\b</span> we&#8217;ll find in JavaScript which is the metacharacter for the beginning *or* end of a word (also known as a ‘word boundary’).

<a name ="jsregex"></a>
## Regular Expressions in JavaScript

In JavaScript, regular expressions like Strings are objects.  For example, a regex object can be created like so:

{% highlight javascript %}
var regex = new RegExp('aregex');
{% endhighlight %}

While the above is technically correct (and sometimes necessary, we'll get to this later), a more common way to create a regex in JavaScript is with forward slashes.  Whereas a String is an array of characters between quotes, a regex is an array of characters between forward slashes.  For example:

{% highlight javascript %}
var regex = /aregex/;
{% endhighlight %}

The `RegExp` object has two methods.  The key method to examine is `exec()` which executes a search in a given String for matches of the regular expression.  It returns an array of information including the matched String, the index where the String appears, and the input String (in case you forgot.)

For example:

{% highlight javascript %}
var text = "This is a test.";   // The String the search in
var regex = /test/;             // The regex  
var results = regex.exec(text); // Execute the search
{% endhighlight %}

`results` now contains the following array:

{% highlight javascript %}
[ 'test',
  index: 10,
  input: 'This is a test.' ]
{% endhighlight %}

If the regular expression included capturing parenthese, the groups would also appear in the array.  For example, let's say you needed a regex to match any phone numbers a String.

{% highlight javascript %}
var text = 'Phone numbers: 212-555-1234 and 917-555-4321 and 646.555.9876.';  
var regex = /(\d+)[-.]\d+[-.]\d+/;               
var results = regex.exec(text);
{% endhighlight %}

The above isn't necessarily the greatest phone number matching regex, but it'll do for this example.  One or more numbers followed by a dash or period followed by one or more numbers, a dash or period again, and one or more numbers.  Let's look at the resulting array.

{% highlight javascript %}
[ '212-555-1234',
  '212',
  index: 42,
  input: 'Phone numbers: 212-555-1234 and 917-555-4321 and 646.555.9876.' ]
{% endhighlight %}

Notice how the full phone number match appears as the first (index 0) element and the captured group (the area code) follows.  You might notice, however, that there are threep phone numbers in the original input String and yet `exec()` only matched the first one.  In order to find all the matches, we'll need to add two more steps.

1. Add the global flag: `g`.

Regular expressions can include flags that modify how the search operates.  For example the flag `i` is for case-insensitivity so that the regular expression <span class="regex">hello</span> with the flag `i` would match “hello”, “Hello”, “HELLO”, and “hElLO” (and other permutations).  A flag is added after the second forward slash like so: `/hello/i`.  The global flag `g` tells the regular expression that we want to search for *all* of the matches and not just the first one.

{% highlight javascript %}
var regex = /(\d+)[-.]\d+[-.]\d+/g;  // Now includes the global flag 
{% endhighlight %}

2. Add a `while` loop to continue calling `exec()`

The `exec()` function, even with the global flag, will still return only the first match.  However, if we call `exec()` a second time with the same regex and input String, it will move on and return the results for the second match (or `null` if there is no match.)  We can therefore write a `while` loop to keep checking until the result is null.

{% highlight javascript %}
var text = 'Phone numbers: 212-555-1234 and 917-555-4321 and 646.555.9876.';  
var regex = /(\d+)[-.]\d+[-.]\d+/g;               
var results = regex.exec(text);

while (results != null) {
  // do something with the matched results and then

  // Check again
  results = regex.exec(text);
}
{% endhighlight %}

This could also be written with the following shorthand (The examples linked from here, however, use the longer-winded code for clarity.)

{% highlight javascript %}
var results;
while ((results = regex.exec(text)) != null) {
  // do something with the matched results and then
}
{% endhighlight %}

The `RegExp` object also includes another method `test()` which simply returns `true` or `false` depending on whether or not at least one match was found.  

{% highlight javascript %}
var text = 'This is a regex example.';
var regex = /example/;
var found = regex.test(text);  // Results in TRUE
{% endhighlight %}

The String object also includes methods that receive regular expression objects as arguments.  For example, `match()` works almost identically as `exec()`.   There are only two differences.  One, the method is called on a String with a RegExp as an argument.  And second, it works differently in the case of global matches.  Let's look at a simple example first.

{% highlight javascript %}
var text = "This is a test of regular expressions.";  
var regex = /test/;               
var results = text.match(regex);
{% endhighlight %}

The above produces the identical result as we saw with `exec()` with results containing the following array.

{% highlight javascript %}
[ 'test',
  index: 10,
  input: 'This is a test of regular expressions.' ]
{% endhighlight %}

If we try to global match of phone numbers, however, we'll get different results.

{% highlight javascript %}
var text = 'Phone numbers: 212-555-1234 and 917-555-4321 and 646.555.9876.';  
var regex = /(\d+)[-.]\d+[-.]\d+/g;               
var results = text.match(regex);
{% endhighlight %}

Here we do not need to employ a loop and instead get an array of all the matches.

{% highlight javascript %}
[ '212-555-1234', '917-555-4321', '646.555.9876' ]
{% endhighlight %}

This is quite a bit more convenient in many cases, however, we've lost some information.  If we require the capturing group matches or the index locations of the matches, we'll need to go back to using `exec()` in RegExp.

Another method of the String object is `search()` which works just like `indexOf()` returning the index of the match or a `-1` if there is no match.  

<a name="splitting"></a>
## Splitting with Regular Expressions

We can now revisit the [split function we examined previously](http://shiffman.net/teaching/a2z/#string) and understand how regular expressions work as a delimiter.  An input String is split into an array of substrings beginning at each match of that regular expression. Here's a simple example that quickyl counts the # of words (not perfect by any means).

{% highlight javascript %}
var text = "This text has characters, spaces, and some punctuation.";
var regex = /\W+/; // one or more non-word chars (anything not a-z0-9)

var words = text.split(regex);
console.log('Total words: ' + words.length);
{% endhighlight %}

The `words` array now contains:

{% highlight javascript %}
[ 'This',
  'text',
  'has',
  'characters',
  'spaces',
  'and',
  'some',
  'punctuation'
]
{% endhighlight %}

What if you, however, would like to include all the delimiters?  To accomplish this, simply enclose your delimiters in capturing parentheses.With `var regex = /(\W+)/;` therefore you'll get the following result.

{% highlight javascript %}
[ 'This',
  ' ',
  'text',
  ' ',
  'has',
  ' ',
  'characters',
  ', ',
  'spaces',
  ', ',
  'and',
  ' ',
  'some',
  ' ',
  'punctuation',
  '.',
  '' ]
{% endhighlight %}

<a name="searchreplace"></a>
## Search and Replace

Running a search and replace is one of the more powerful things one can do with regular expressions.  This can be accomplished with the String's `replace()` method.  The method receives two arguments, a regex and a replacement String.  Wherever there is a regex match, it is replaced with the String provided.


{% highlight javascript %}
var text = 'Replace every time the word "the" appears with the word ze.'; 

// \b is a word boundary
// You can think of this as an invisible boundary 
// between a non-word character and a word character.
var regex = /\bthe\b/g;  

var replaced = text.replace(regex,'ze');
{% endhighlight %}

The result is:

```
Replace every time ze word "ze" appears with ze word ze.
```

We can also reference the matched text using a backreference to a captured group in the substitution string.  A backreference to the first group is indicated as <span class="regex">$1</span>, <span class="regex">$2</span> is the second, and so on and so forth.
{% highlight javascript %}
var text = "Double the vowels.";
var regex = /([aeiou]+)/g;
var replaced = text.replace(regex, '$1$1');
{% endhighlight %}

The result is:

```
Dououblee thee vooweels
```

