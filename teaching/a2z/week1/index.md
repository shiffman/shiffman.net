---
title: Week 1 A to Z
author: Daniel
layout: page
dsq_thread_id:
  - 249553138
pvc_views:
  - 5436
---

<script language="javascript" type="text/javascript" src="//cdn.jsdelivr.net/p5.js/0.3.5/p5.min.js"></script>
<script language="javascript" type="text/javascript" src="//cdn.jsdelivr.net/p5.js/0.3.5/addons/p5.dom.js"></script>


## This week&#8217;s topics:
* [Beyond Processing and into JavaScript and p5.js](#beyond)
* [Installing Node](#node)
* [JavaScript 101](#js101)
* [Strings in JavaScript](#strings)
* [File I/0 with Node](#file)
* [Simple Text Analysis](#analysis)
* [Back to p5.js, processing text from a user](#p5analysis)

## Examples:
* [Processing to p5](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/00_Processing_to_p5.js)
* [OOP in JS](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/01_objects_in_JS)
* [DOM manipulation in p5](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/02_DOM_p5)
* [Strings in JS](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/03_Strings)
* [File I/O in Node](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/04_fileinput)
* [Process user text in p5](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week1/05_p5_text)

*(Some of these notes were adapted from the [Creative JavaScript](https://github.com/lmccart/itp-creative-js) ITP course.)*

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

<a name ="node"></a>
## JS with Node

We're also going to explore running some simple JavaScript programs via the command line in this class.  This will allow us to easily test out ideas as well as process text files.  This will also give us a head-start to building server-side programs that analyze text as well.  

The first thing we need to do to get up and running is to [install node](http://nodejs.org/download/).  Once you've installed node, to make sure it's working just open up terminal and type `node`.  If it's installed you'll see a prompt.  You can type any JavaScript here just like in the browser console to test (hit cntrl-c twice to exit.)

Now make any old file with JavaScript code in it.  For example, make a file called "hello.js" with:

{% highlight javascript %}
console.log('Hello!');
{% endhighlight %}

Now back in terminal type `node hello.js`.  You should see `Hello!` back in the console.

Congratulations, you are now executing server-side JS code via the command line!

<a name ="js101"></a>
## JavaScript 101

(This is just a list of what I'll be covering in class, hope to have write-up at some point).

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

{% highlight javascript %}
var a = 'a';
var h = 'hello';
{% endhighlight %}

In JavaScript, Strings can be literal primitives or objects.

{% highlight javascript %}
var s1 = 'hello';               // a primitive
var s2 = new String('hello');   // an object
{% endhighlight %}

For the most part, this is a distinction we don't have to worry about.  JS will automatically covert our primitive String into an object when necessary. In general, it's good practice to initialize your Strings as primtives to increase performance.

## Manipulating Strings

JavaScript provides us with a basic set of String functions that allow for simple manipulation and analysis.  <a href="http://shiffman.net/teaching/a2z/regex">Next week</a>, we&#8217;ll also look at how <a href="http://en.wikipedia.org/wiki/Regular_expression">regular expressions</a> can allow to perform advanced String processing, but we'll start this week with non-regex String methods and gather some skills doing all of our text processing manually, character by character.   All of the availabe String properties and functions are laid out in [the JavaScript reference](ttp://docs.oracle.com/javase/7/docs/api/java/lang/String.html), and we'll explore a few useful ones here.  Let&#8217;s take a closer look at three: indexOf(), substring(), and the length property.

`indexOf()` locates a sequence of characters within a string. For example, run this code and examine the result:

{% highlight javascript %}
var sentence = 'The quick brown fox jumps over the lazy dog.';
console.log(sentence.indexOf('quick'));
console.log(sentence.indexOf('fo'));
console.log(sentence.indexOf('The'));
console.log(sentence.indexOf('blah blah'));
{% endhighlight %}

Note that indexOf() returns a 0 for the first character, and a -1 if the search phrase is not part of the String.

After we find a certain search phrase within a String, we might want to pull out part of the String and save it in a different variable. This is what we call a &#8220;substring&#8221; and we can use java&#8217;s substring() function to take care of this task. Examine and run the following code:

{% highlight javascript %}
var sentence = 'The quick brown fox jumps over the lazy dog.';
var phrase = sentence.substring(4,9);
console.log(phrase);
{% endhighlight %}

Note that the substring begins at the specified beginIndex (the first argument) and extends to the character at endIndex (the second argument) minus one. Thus the length of the substring is endIndex minus beginIndex.

At any given point, we might also want to access the length of the String. We can accomplish this with the length property.

{% highlight javascript %}
var sentence = 'The quick brown fox jumps over the lazy dog.';
console.log(sentence.length);
{% endhighlight %}

It's also important to note that we can concatenate (i.e. join) a String together using the + operator.   With numbers plus means add, with Strings (or characters), it means concatenate, i.e.</p>

{% highlight javascript %}
var num = 5 + 6;                        // ADDING TWO NUMBERS!
var phrase = 'To be' + ' or not to be'; // JOINING TWO STRINGS!
{% endhighlight %}

## Splitting

One String-related function that will prove very useful in our text analysis programs is [split()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/split).  `split()` separates a group of strings embedded into a longer string into an array of strings.

Examine the following code:

{% highlight javascript %}
var spaceswords = 'The quick brown fox jumps over the lazy dog.';
var list1 = spaceswords.split(' ');
console.log(list1[0]);
console.log(list1[1]);

var commaswords = 'The,quick,brown,fox,jumps,over,the,lazy,dog.';
var list2 = commaswords.split(',');
for (var i = 0; i < list2.length; i++) {
  console.log(i + ': ' + list2[i]);
}

// Calculate sum of a list of numbers in a string
var numbers = '8,67,5,309';
var numlist = numbers.split(',');
var sum = 0;
for (var i = 0; i < numlist.length; i++) {
  sum = sum + Number(numlist[i]);  // Converting each String into an number!
}
console.log(sum);
{% endhighlight %}

To perform the reverse of split, we can write a quick function that joins together an array of Strings.

{% highlight javascript %}
var words = ['it','was','a','dark','and','stormy','night'];
{% endhighlight %}

<p>Knowing about loops and arrays we could join the above array of strings together as follows:</p>

{% highlight javascript %}
// Concatenating an array of Strings manually
function join(str, separator) {
  var stuff = '';
  for (var i = 0; i < str.length; i++) {
    if (i != 0) stuff += separator;
    stuff += str[i];
  }
  return stuff;
}
{% endhighlight %}

<p><a name="file"></a></p>

## Text Input and Output

To start, we are going to be working in the simple world of text in and text out.   We are going to do this a few ways.  First, we'll make a simple web page that processes text from an input field.  We'll also look at loading text from a file on a web page as well as process a text file using a simple node.js server-side program.

Let's start with a simple node.js program.  To load from a file, we'll use the [file system module](http://nodejs.org/api/fs.html). 

{% highlight javascript %}
var fs = require('fs');
{% endhighlight %}

One thing that is nice about working with the command line is that we can pass in filenames as arguments to a program.  For example, let's say we have a JS file `process.js`.  From the command line, we'll say:

{% highlight javascript %}
% node processing.js myfile.txt
{% endhighlight %}

The program will then read this text file as input.  To accomplish this, we can grab the filename as the third element (index 2) of the arugments array.

{% highlight javascript %}
var filename = process.argv[2];
{% endhighlight %}

We can even check to make sure the user entered a file name.

{% highlight javascript %}
if (process.argv.length < 3) {
  console.log('Oops, you forgot to pass in a text file.');
  process.exit(1);
}
{% endhighlight %}

We'll use the `readFile()` method to read the file.  `readFile()` takes three arguments -- the name of the file, the format of the file, and a function that will executed when the data from the file is ready (known as a *callback*). 

{% highlight javascript %}
fs.readFile(filename, 'utf8', analyze);
{% endhighlight %}

The use of a callback is very typical of JavaScript, and we'll be seeing many examples of this over the course of the semester.  It's also possible to write an "anonymous" function directly as an argument to `readFile()` but this will make the code a bit harder to follow.  Let's take a look at the `analyze()` function.

{% highlight javascript %}
function analyze(err, data) {
  if (err) {
    throw err;
  }
  console.log('OK: ' + filename);
  console.log(data);
}
{% endhighlight %}

The function takes two arguments: err and data.  err will be undefined (unless there's an error) and data will contain all of the text from the file in a String (unless there was an error).   If you're not familiar with `throw err`, take a look at the documentation for [throw](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/throw) and [try/catch](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/try...catch).

Once we&#8217;ve gotten the hang of reading and writing files, we can start to think about ways of creating output text based on an input text.  For example, we could do something as simple as make a new text with every other word from a source text.   To do this, we can split the text up into an array of Strings (with space as a delimiter) and create a new String by appended every other word to it.  StringBuffer is good to use in case we are dealing with really long texts.

{% highlight javascript %}
// Split text by wherever there is a space
var words = data.split(" ");
var everyotherword = '';
for (var i = 0; i < words.length; i+=2) {
  var word = words[i];
  everyotherword += word + ' ';
}
{% endhighlight %}

Using the Nigerian Spam as a source text, the result is something like:

```
On 12th, a contractor the co-orporation, Kingdom Olaf made time
Deposit  twelve months, at US$ (Seventeen Three Hundred fifty
Thousand only) my maturity,I a notification his address but no
After month, sent reminder finally from contract the Pertroleum
co-orporation Mr.Olaf died an accident further found that died
making WILL,and attempts his of was therefore further and
that Olaf
```

Another thing we might try is to search for every time a certain word appears.   The following code examines a text for every time the word &#8220;God&#8221; appears and keeps the word &#8220;God&#8221; along with what follows it:

{% highlight javascript %}
var words = data.split(' ');
for (var i = 0; i < words.length-1; i++) {
  if (words[i] == 'God') {
    console.log(words[i] + ' '  + words[i+1]);
  }
}
{% endhighlight %}

The result applied to Genesis from the Bible looks something like:


```
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
God will
```

<p>We could also reverse all the characters in a text, by walking through the String backwards.  Note how the for loop starts at the end of the String (`data.length - 1`).

{% highlight javascript %}
// Reverse all the characters in the text
var output = '';
for (var i = data.length-1; i >= 0; i--) {
  output += data.charAt(i);
}
{% endhighlight %}

The result applied to the Nigerian Spam looks something like:


```
rof %5 dna uoy rof %53 dna em rof %06 fo oitar eht ni erahs ot su
rof tnuocca ruoy otni diap eb lliw yenom ehT .refsnart eht rof rovaf
ruoy ni noitartsinimda/etaborp fo rettel dna stnemucod yrassecen eht
niatbo ot dna LLIW eht fo noitaziraton dna gnitfard rof yenrotta na
fo secivres eht yolpme llahs eW .nik fo txen eht sa ecalp ni uoy tup
lliw taht stivadiffa dna stnemucod yrassecen eht eraperp lliw yenrotta
```

<a name="analysis"></a>
## Analysis

We&#8217;ll end this week by looking at a basic example of text analysis.  We will read in a file, examine some of its statistical properties, and write a report.  Our example will compute the [Flesch Index](http://en.wikipedia.org/wiki/Flesch-Kincaid_Readability_Test)  (aka Flesch-Kincaid Reading Ease test), a numeric score that indicates the readability of a text.   The lower the score, the more difficult the text.  The higher, the easier.  For example, texts with a score of 90-100 are, say, around the 5th grade level, wheras 0-30 would be for &#8220;college graduates&#8221;.  The result of the test on a few sample texts (the Bible, spam, a New York Times article, and Processing tutorials I&#8217;m writing) are displayed to the right.

The Flesch Index is computed as a function of total words, total sentences, and total syllables.  It was developed by Dr. Rudolf Flesch and modified by J. P. Kincaid (thus the joint name).  Most word processing programs will compute the Flesch Index for you, which provides us with a nice method to check our results.


***Flesch Index = 206.835 &#8211; 1.015 * (words / sentences) + 84.6 * (syllables / words)***

Our pseudo-code will look something like this:

1. Read input file into String object
2. Count words
3. Count syllables
4. Count sentences
5. Apply formula
6. Write out report file

We know we can read in text from a file and store it in a String object as demonstrated in the example above.  Now, all we have to do is examine that String object, counting the total words, sentences, and syllables, applying the formula as a final step. To count words, we&#8217;ll use `split()`.

The first thing we&#8217;ll do is count the number of words in the text.  We&#8217;ve seen in some of the examples above that we can accomplish this by using `split()` to split a String up into an array wherever there is a space.  For this example, however, we are going to want to split by more than a space.  A new word occurs whenever there is a space or some sort of punctuation.

{% highlight javascript %}
var delimiters = /[.:;?! !@#$%^&*()]+/;
var words = data.split(delimiters);
{% endhighlight %}

You'll notice some new syntax here.  `/[.:;?! !@#$%^&*()]+/` is a regular expression.  We are going to cover regex in detail next week.  For now, we should just understand this as something that indicates a list of possible delimiters (any character than appears between `/[` and `]+/`).

Now we have split up the text, we can march through all the words (tokens) and count their syllables.

{% highlight javascript %}
for (var i = 0; i < words.length; i++) {
  var word = words[i];
  totalSyllables += countSyllables(word);
  totalWords++;
}
{% endhighlight %}

Ok, so `countSyllables()` isn&#8217;t a function that exists in JavaScript.  We&#8217;re going to have to write it ourselves.   The following method is not the most accurate way to count syllables, but it will do for now.

```
Syllables = total # of vowels in a word (not counting vowels that appear after another vowel and when &#8216;e&#8217; is found at the end of the word)
```

* &#8220;beach&#8221; &#8211;> one syllable
* &#8220;banana&#8221; &#8211;> three syllables
* &#8220;home&#8221; &#8211;> one syllable

Our code looks like this:

{% highlight javascript %}
// A method to count the number of syllables in a word
// Pretty basic, just based off of the number of vowels
// This could be improved
function countSyllables(word) {
  var syl    = 0;
  var vowel  = false;
  var length = word.length;

  // Check each word for vowels (don't count more than one vowel in a row)
  for (var i = 0; i < length; i++) {
    if (isVowel(word.charAt(i)) && (vowel == false)) {
      vowel = true;
      syl++;
    } else if (isVowel(word.charAt(i)) && (vowel == true)) {
      vowel = true;
    } else {
      vowel = false;
    }
  }

  var tempChar = word.charAt(word.length-1);
  // Check for 'e' at the end, as long as not a word w/ one syllable
  if (((tempChar == 'e') || (tempChar == 'E')) && (syl != 1)) {
    syl--;
  }
  return syl;
}

// Check if a char is a vowel (count y)
function isVowel(c) {
  if      ((c == 'a') || (c == 'A')) { return true;  }
  else if ((c == 'e') || (c == 'E')) { return true;  }
  else if ((c == 'i') || (c == 'I')) { return true;  }
  else if ((c == 'o') || (c == 'O')) { return true;  }
  else if ((c == 'u') || (c == 'U')) { return true;  }
  else if ((c == 'y') || (c == 'Y')) { return true;  }
  else                               { return false; }
}
{% endhighlight %}

As we will see next week, the above could be vastly improved using Regular Expressions, but it&#8217;s nice as an exercise to learn how to do all the String manipulation manually before we move on to more advanced techniques.

Counting sentences is a bit simpler.  We&#8217;ll just split the content using periods, question marks, exclamation points, etc. (&#8220;.:;?!&#8221;) as delimiters and count the total number of elements in the resulting array.  This isn&#8217;t terribly accurate; for example, &#8220;My e-mail address is daniel.shiffman@nyu.edu.&#8221;  will be counted as three sentences.  Nevertheless, as a first pass, this will do.

{% highlight javascript %}
// Look for sentence delimiters
var sentenceDelim = /[.:;?!]/;
var sentences = data.split(sentenceDelim);
totalSentences = sentences.length;
{% endhighlight %}

Now, all we need to do is apply the formula, generate a report.

{% highlight javascript %}
// Calculate flesch index
var f1 = 206.835;
var f2 = 84.6;
var f3 = 1.015;
var r1 = totalSyllables / totalWords;
var r2 = totalWords / totalSentences;
var flesch = f1 - (f2 * r1) - (f3 * r2);

// Write Report
var report = "";

report += "Total Syllables: " + totalSyllables + "\n";
report += "Total Words    : " + totalWords + "\n";
report += "Total Sentences: " + totalSentences + "\n";
report += "Flesch Index   : " + flesch + "\n";
{% endhighlight %}

The full example code is here.

<a name ="p5analysis"></a>
## Back to p5.js

Now that we've worked out some simple algorithms for manipulating and processing text, we can return to p5.js and process text from a user.  The simplest way will be to create a [textarea](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea) HTML element.  We can do this with [createElement()](http://p5js.org/reference/#/p5/createElement).

```javascript
var input = createElement("textarea","Enter some text.");
input.attribute("rows",10);  // specifying an attribute for the text area's size
input.attribute("cols",100);
```

We can then make a button that executes a function to process the text whenever the user clicks the button.

```javascript
var button = createButton("Compute the Flesch Index!");
button.mousePressed(flesch);  // flesch() is a callback that is triggered whenever the button is pressed
```

Writing the flesch function is just like we did with node only we are pulling text from the textarea, rather than a file.

```javascript
function flesch() {
  // What has the user entered?
  var data = input.value();
  
  // all the rest of the code from before
```

Try it out below! (and [take a look at the source](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week1/05_p5_text/flesch/sketch.js)).

<script language="javascript" type="text/javascript" src="flesch.js"></script>
<textarea rows="10" cols="100" id ="input">Enter some text.</textarea>
<button value="undefined" id="fleschit">Compute the Flesch Index!</button>
<p id="fleschreport"></p>

Also, here's a [full tutorial about the p5.dom library](https://github.com/lmccart/p5.js/wiki/Beyond-the-canvas).

