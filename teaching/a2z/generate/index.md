---
title: Generative Text
author: Daniel
layout: page
---

<script language="javascript" type="text/javascript" src="//cdn.jsdelivr.net/p5.js/0.3.6/p5.min.js"></script>
<script language="javascript" type="text/javascript" src="//cdn.jsdelivr.net/p5.js/0.3.6/addons/p5.dom.js"></script>
<script language="javascript" type="text/javascript" src="generate.js"></script>

## This week&#8217;s topics:
* [N-Grams and Markov Chains](#ngrams)
* [Grammars](#grammar)
* [Wordnik](#wordnik)
* [TwitterBot](#twitter)

## Example Code
* [Markov chains](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week4_generate/01_markov)
* [Context-free Grammars](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week4_generate/02_cfg)
* [Wordnik API](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week4_generate/03_wordnik/01_wordnik)
* [TwitterBot](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/04_twitter_bot/bot.js)

## Related references
* [Animated Markov Chain explanation](http://setosa.io/blog/2014/07/26/markov-chains/) 
* [N-Grams and Markov Chains by Allison Parrish](http://www.decontextualize.com/teaching/rwet/n-grams-and-markov-chains/)
* [Context-Free Grammars by Allison Parrish](http://www.decontextualize.com/teaching/rwet/recursion-and-context-free-grammars/)
* [N-Grams and Markov Chains by Daniel Howe](http://www.rednoise.org/pdal/index.php?n=Main.N-Grams)
* [Context-Free Grammars by Daniel Howe](http://www.rednoise.org/pdal/index.php?n=Main.Grammars)
* [Google N-Gram Viewer](https://books.google.com/ngrams), [google blog post about n-grams](http://googleresearch.blogspot.com/2006/08/all-our-n-gram-are-belong-to-you.html)
* [Markov Models of Natural Language](http://www.cs.princeton.edu/courses/archive/spr05/cos126/assignments/markov.html)
* [Three Models for the Description of Language (Chomsky)](http://chomsky.info/articles/195609--.pdf)

## Exercise ideas
* Create page that generates its content by feeding an existing text into the Markov chain algorithm.  What effect does the value of n (the “order” of the n-gram) have on the result?  [Allison Parish's ITP Course generator](http://static.decontextualize.com/toys/next_semester) is an excellent example.
* Visualize N-gram frequencies.  See [WebTrigrams by Chris Harrison](http://www.chrisharrison.net/index.php/Visualizations/WebTrigrams) for an example.
* What happens if you mash-up two texts? For example, feed Shakespeare plays and ITP physical computing blog post content into the generator.  Can you modify the MarkovGenerator object to weight the input text (i.e. make shakespeare N-grams have higher probabilities?)  [The Gnoetry Project](http://www.beardofbees.com/gnoetry.html) is a useful reference.  
* Rework any of the example programs to use something other than text (or, at least, text that represents language) as its basic unit. For example: musical notes, songs in playlists, pixels in an image, etc.
* Invent your own grammar. Consider using one that generates something other English sentences: music, images, code, etc.
* Build a grammar that pulls its terminal words from [Wordnik](https://www.wordnik.com/).
* Build a grammar based on a source text [as demonstrated here](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/04_grammar_maker/grammarmaker.js).  

<a name ="ngrams"></a>
## N-Grams and Markov Chains

The [infinite monkey theorem](http://en.wikipedia.org/wiki/Infinite_monkey_theorem) suggests that a monkey randomly typing at a keyboard for an infinite amount of time would eventually type the complete works of William Shakespeare.  [Working out the math to this problem](http://natureofcode.com/book/chapter-9-the-evolution-of-code/#chapter09_section2) reveals just how insanely long this would actually take.  Even for a monkey to randomly type “to be or not to be” requires eons upon eons of time.  We can demonstrate the idea with JavaScript by generating random Strings.

<p id="randomit"></p>

{% highlight javascript %}
// All of our possible characters
var possible = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOP .';
// The random String
var tobe = '';
// Pick 18 random characters
for (var i = 0; i < 18; i++) {
 var r = possible.charAt(floor(random(0,possible.length)));
 tobe += r;
}
console.log(tobe);
{% endhighlight %}

Have you seen “to be or not to be” yet?  Probably not.  After all, a true monkey types totally at random, meaning there is an equal probability that any key will be pressed at any given time.  The chances are incredibly slim we'll get an exact sequence.  But  what if we could generate our own custom probability map for a keyboard (or a sequence of word tokens)?  What kind of results could we achieve?  Let's consider the following scenario.  What if we picked letters according to their [actual frequency in the English language](http://en.wikipedia.org/wiki/Letter_frequency#Relative_frequencies_of_letters_in_the_English_language)?  Here's a chart to get us started.

<p style="font-size:8pt;"><a href="http://commons.wikimedia.org/wiki/File:English_letter_frequency_(alphabetic).svg#mediaviewer/File:English_letter_frequency_(alphabetic).svg"><img src="http://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/English_letter_frequency_%28alphabetic%29.svg/400px-English_letter_frequency_%28alphabetic%29.svg.png" alt="English letter frequency (alphabetic).svg"></a><br><a href="http://commons.wikimedia.org/wiki/File:English_letter_frequency_(alphabetic).svg#mediaviewer/File:English_letter_frequency_(alphabetic).svg">English letter frequency (alphabetic)</a> by <a href="//commons.wikimedia.org/wiki/User:Nandhp" title="User:Nandhp">Nandhp</a> Licensed under Public domain via <a href="//commons.wikimedia.org/wiki/">Wikimedia Commons</a>.</p>

```

Letter Probability    
------ ----------- 
a      8.167%         
b      1.492%         
c      2.782%         
d      4.253%        
```

How might we pick ‘a’ 8% of the time and ‘d’ 4% of the time?  Another way of stating this question might be: “How would be pick ‘a’ twice as often as ‘d’”?  While there are a variety of solutions to this sort of problem a simple one that we will employ in our examples is the following.  Imagine we had an array.

{% highlight javascript %}
// An array of two choices
var possibilities = ['a','d'];
{% endhighlight %}

We could then pick one element from that array randomly as follows:

{% highlight javascript %}
// A random integer from 0 to the end of the array
var i = floor(random(possibilities.length));
// Get the element in that index
var pick = possibilities[i];
{% endhighlight %}

Each element in the case of an array with two spots has a 50% chance of being picked.  But what if created the array as follows instead?

{% highlight javascript %}
// Two choices, but one appears twice
var possibilities = ['a','a', 'd'];
{% endhighlight %}

Now when picking a random element, there is a two out of three chance of picking ‘a’.  In fact, ‘a’ is twice as likely to be picked than ‘d’.
Using [all the letter frequencies found in this JSON file](letterfreq.json), we could rewrite our random generator to build an array of letters, adding each letter to the array a number of times according to its frequency.

<p id "randomfreq"></p>

{% highlight javascript %}
// All the possible letters
var letters = 'abcdefghijklmnopqrstuvwxyz';
for (var i = 0; i < letters.length; i++) {
    // Let's assume we've loaded a JSON object 
    // that has the probabilities for each one
    var prob = json[letters[i]];
    // Arbitrarily multiplying the probability by 1,000
    // If the probability is 1.067% then it would be 
    // placed in the array 1,067 times.
    var n = floor(prob*1000);
    for (var j = 0; j < n; j++) {
      possibilities.push(letters[i]);
    }
  }
}
{% endhighlight %}

While we've probably increased the likelihood of randomly typing “to be or not to be” only slightly, we can nevertheless see how the quality of the results have changed.  e’s appear a great deal more than z’s and so on and so forth.  

Next, let's take this idea of custom letter probabilities another step forward and examine the frequencies of letters neighboring other letters commonly in English.  For example, how often does ‘h’ occur after ‘t’ versus ‘a’ or ‘r’ and so on and so forth?  

This is exactly the approach of N-grams.  An n-gram is a contiguous sequence of N elements.  In the case of text, this might be N letters or N words or N phonemes or N syllables.  For an example, here's a function that returns all word N-grams for a given String (using a regex to split the text up into tokens):

{% highlight java %}
function nGrams(sentence, n) {
  // Split sentence up into words
  var words = sentence.split(/\W+/);
  // Total number of n-grams we will have
  var total = words.length - n;
  var grams = [];
  // Loop through and create all sequences
  for(var i = 0; i <= total; i++) {
    var seq = '';
    for (var j = i; j < i + n; j++) {
      seq += words[j] + ' ';
    }
    // Add to array
    grams.push(seq);
  }
  return grams;
}
{% endhighlight %}

Try it out below.

<textarea id = 'ngramarea'>Enter some text here</textarea><br/>
order: <select id = "order">
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
    </select>
<button id = 'ngrambutton'>show me ngrams</button>

<pre><code id="ngramsresult"></code></pre>

Looking at all the N-grams of a given text provides a strategy for generating text.  Let’s go back to the phrase “to_be_or_not_to_be” (using an underscore instead of space for clarity).   Let's start with the simplest possible scenario and look at all N-grams where N=1 (which is admittedly a bit silly, but will be a useful demonstration.)

```
t o _ b e _ o r _ n o t _ t o _ b e
```

Now, let's look at all the possible characters that appear after t:

```
t -> o
t -> _
t -> o
```

From the above we can calculate that in this (very small) piece of text, an ‘o’ follows a ‘t’ 67% of the time and a space 33%. 

```
o -> _
o -> r
o -> t
o -> _
```

With ‘o’, we next see a space 50% of the time, ‘r’ 25%, and ‘t’ 25%.


We could express this result as a JavaScript object:

{% highlight javascript %}
// All ngrams and next characters
var ngrams = {
  't': ['o', '_', 'o'],
  'o': ['_', 'r', 't', '_']
};
{% endhighlight %}

Now, imagine we next decided to generate new text based on these probabilities.  We might start with ‘t’:

{% highlight javascript %}
var txt = 't';
{% endhighlight %}

And then pick a new character based on the array associated in the `ngrams` object.

{% highlight javascript %}
var txt = 't';

var possible = ngrams['t'];
var r = floor(random(possible.length));
var next = possible[r];

txt = txt + next;
{% endhighlight %}

Now repeat for the next N-gram.  And so and and so forth.  Here are some sample outcomes (and the full code for producing these):

<p id="ngramtest"></p>

{% highlight javascript %}
// Our simple ngrams model
var ngrams = {
  't': ['o', '_', 'o'],
  'o': ['_', 'r', 't', '_']
};

// Starting text
var txt = 't';
// Let's do the following ten times
for (var i = 0; i < 10; i++) {
  var possible = ngrams[txt.charAt(i)];
  // If there is nothing in the ngrams object for this character
  // it's a "terminal" character and we stop
  if (!possible) {
    break;
  }
  // Otherwise pick a random number
  var r = floor(random(possible.length));
  // And we've got the next chararacter
  var next = possible[r];
}
{% endhighlight %}

This technique is known as a Markov Chain.  A [Markov Chain](http://en.wikipedia.org/wiki/Markov_chain) can be described as a sequence of random “states” where each new state is conditional only on the previous state.   An example of a Markov Chain is [monopoly](http://www.bewersdorff-online.de/amonopoly/).   The next state of the monopoly board depends on the current state and the roll of the dice.  It doesn’t matter how we got to that current state, only what it is at the moment.  A game like blackjack, for example, is different in that the deal of the cards is dependent on the history of many previous deals (assuming a single-deck not continuously shuffled.)

Using an N-gram model, can use a markov chain to generate text where each new word or character is dependent on the previous word (or character) or sequence of words (or characters).   For example. given the phrase “I have to” we might say the next word is 50% likely to be “go”, 30% likely to be “run” and 20% likely to be “pee.”  We can construct these word sequence probabilities based on a large corpus of source texts.  Here, for example, is the full set of ngrams of order 2 and their possible outcomes in the phrase: “to be or not to be, that is the question.”

{% highlight javascript %}
var ngrams = {
   "to": [ " ", " " ],
   "o ": [ "b", "b" ],
   " b": [ "e", "e" ],
   "be": [ " ", "," ],
   "e ": [ "o", "q" ],
   " o": [ "r" ],
   "or": [ " " ],
   "r ": [ "n" ],
   " n": [ "o" ],
   "no": [ "t" ],
   "ot": [ " " ],
   "t ": [ "t", "i" ],
   " t": [ "o", "h", "h" ],
   "e,": [ " " ],
   ", ": [ "t" ],
   "th": [ "a", "e" ],
   "ha": [ "t" ],
   "at": [ " " ],
   " i": [ "s" ],
   "is": [ " " ],
   "s ": [ "t" ],
   "he": [ " " ],
   " q": [ "u" ],
   "qu": [ "e" ],
   "ue": [ "s" ],
   "es": [ "t" ],
   "st": [ "i" ],
   "ti": [ "o" ],
   "io": [ "n" ],
   "on": [ "." ]
};
{% endhighlight %}

The process to generate the above `ngrams` object is quite similar to the [concordance](http://shiffman.net/teaching/a2z/analysis/) we previously developed.  Only this time, instead of pairing each token with a count, we are pairing each N-gram with an array of possible next characters.  Let's look at how this is built.

{% highlight javascript %}
var ngrams = {};
var text = 'to be or not to be, that is the question';
var n = 2;
// Look at all characters of the String
for (var i = 0; i < text.length - n; i++) {
  // Look at an ngram
  var gram = text.substring(i, i + n);
  // Look at the next charaacter
  var next = text.charAt(i + n);
  // Is this a new one, make an empty array
  if (!ngrams.hasOwnProperty(gram)) {
    ngrams[gram] = [];
  }
  // Add the next character as a possible outcome
  ngrams[gram].push(next);
}
{% endhighlight %}

A small difference from the concordance you might notice above is the use of `hasOwnProperty()` which is a universal object method in JavaScript that allows you to test if a variable is a property of the object or not.  Last week, we said `if (ngrams[gram] === undefined)`.  Both are valid strategies.

Once we've got the `ngrams` object with all possibities mapped out we can start to generate text.

{% highlight javascript %}
// Start with an arbitrary ngram
var current = 'to'; 
// The final text
var output = current;
  
// Generate a new character some number of times
for (var i = 0; i < 20; i++) {
  // If this is a valid ngram
  if (ngrams.hasOwnProperty(current)) {
    // What are all the possible next tokens
    var possible = ngrams[current];
    // Pick one randomly
    var next = choice(possible);
    // Add to the output
    output += next;
    // Get the last N entries of the output; we'll use this to look up
    // an ngram in the next iteration of the loop
    current = output.substring(output.length-n, output.length);
  } else {
    break;
  }
}
{% endhighlight %}

You might notice the `choice()` function above.  Just about all of our examples require the ability to pick a random element from an array over and over.  To simplify this process, I'm emulating the [python choice()](https://docs.python.org/2/library/random.html?highlight=choice#random.choice) function.

{% highlight javascript %}
function choice(somelist) {
  var i = floor(random(somelist.length));
  return somelist[i];
}
{% endhighlight %}

In the examples, you'll find [all of the above packaged into a MarkovGenerator object](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/01_markov/01_markov_bychar_short/markov.js).  Special thanks to [Allison Parish's excellent RWET examples](https://github.com/aparrish/rwet-examples) which this is modeled after.  

<a name ="ngrams"></a>
## Grammars

From <a href="http://en.wikipedia.org/wiki/Grammar">Wikipedia</a>:  &#8220;Grammar is the study of the rules governing the use of a given natural language, and, as such, is a field of linguistics. Traditionally, grammar included morphology and syntax; in modern linguistics these subfields are complemented by phonetics, phonology, semantics, and pragmatics.&#8221;

A [Context-Free Grammar](http://en.wikipedia.org/wiki/Context-free_grammar) is a set of rules that define the syntax of a language &#8212; what is and is not a valid sequence of &#8220;tokens&#8221;.   Programming languages, for example, are context-free grammars &#8212; a compiler reads your code to make sure it conforms to specific rules and informs you of any errors.   (These rules aren&#8217;t limited to the production of text, and can be used for music, images, etc.) A context-free grammar, however, isn&#8217;t robust enough to describe the complexities of natural language.  After all, they have no context!  Natural languages can be described using <a href="http://en.wikipedia.org/wiki/Context-sensitive_grammar">Context-sensitive grammars</a>, a concept introduced by <a href="http://en.wikipedia.org/wiki/Noam_Chomsky">Chomsky</a> in the 50s.

For our purposes here, we want to learn how to define our own context free grammars and generate text from them.  I&#8217;m going to give a short explanation here, followed by code examples.  For more detailed discussions, I would recommend [Daniel Howe&#8217;s (creator of RiTa)explanation](http://www.rednoise.org/pdal/index.php?n=Main.Grammars) and [Context-Free Grammars by Allison Parrish](http://www.decontextualize.com/teaching/rwet/recursion-and-context-free-grammars/).

All &#8220;production rules&#8221; in a context-free grammar are of the form:

```
V -> w
```

where `V` is a single &#8220;non-terminal&#8221; and `w` is a sequence of &#8220;terminals&#8221; and &#8220;non-terminals&#8221;

A non-terminal symbol is simply another symbol which needs to be defined in another production rule.  A terminal symbol does not need to be defined because that&#8217;s it, you&#8217;ve reached the end, what is here is what should be in the sentence itself.  Here&#8217;s an incredibly simple CFG.

```
s -> a b
a -> 'the'
b -> c 'cat'
c -> 'happy'
c -> 'sad'
```

Anything in single quotes is a &#8220;terminal&#8221; element, meaning this is the end and no more substitutions are necessary.  In the above &#8220;cat,&#8221; &#8220;happy,&#8221; and &#8220;sad&#8221; are all terminals. Anything that is not in quotes is non-terminal, or a &#8220;symbol.&#8221;  The abve example has 4 symbols &#8212; s, a, b, c. The symbol &#8220;s&#8221; is commonly used to indicate &#8220;start&#8221; or &#8220;sentence.&#8221; 

Notice how in the above set of rules the symbol c can be &#8220;happy&#8221; or &#8220;sad.&#8221;   This is often express with an OR (pipe character) to combine these two rules:

```
c -> 'happy' | 'sad'
```

Again, this grammar is incredibly basic, and is just for demonstrating how the elements work.  The only two valid &#8220;sentences&#8221; that conform to this grammar are:

```
the happy cat
the sad cat
```

The process of generating the above two sentences goes something like:

`s` becomes `a b` which becomes `the c cat` which then becomes `the happy cat` or `the sad cat`.  Here, either &#8220;happy&#8221; or &#8220;sad&#8221; is picked randomly (with a 50% chance for each one.)

How do we build a data structure to track a context-free grammar?  Yup, again, we're going to use a JavaScript object just like with the concordance or N-gram analysis.  Here we'll take a production rule and map non-terminal characters to keys and the possible outcomes as an array of values.

{% highlight javascript %}
var cfg = {
  's': [['a', 'b']],
  'a': [['the']],
  'b': [['c', 'cat']],
  'c': [['happy'], ['sad']]
};
{% endhighlight %}

The above may look a little strange to you.  Notice how the value for each key is an array of arrays.  Each element of the larger array is one possible outcome which is an array itself: the list of elements in that outcome.  I should also note that the is no distinction between a “symbol” and a “terminal.”  Everything is just a String, if there is a rule associated with that String then it is a symbol, if not, it's terminal.

A generated sentence from the CFG is commonly referred to as an “expansion” and is a bit tricky to implement.  We could try to just use a loop, iterating over a sentence and executing the production rules.  The nested nature of the rules, however, introduces a great deal of complexity.  An elegant strategy, however, is to call a function recursively expanding the same sentence over and over again until there are no non-terminal elements left.  Let's assume we have an empty array and some seed (often called an "axiom").

{% highlight javascript %}
var expansion = [];
var seed = 's';
{% endhighlight %}

We can write a function that receives both the array and the seed and calls itself recursively if a rule that matches that seed String is present.

{% highlight javascript %}
function expand(start, expansion) {
  if (cfg.hasOwnProperty(start)) {
    // Grab one possible expansion
    var possible = rules[start];
    var random_expansion = choice(possible);
    // Call this method again with the current element of the expansion
    for (var i = 0; i < random_expansion.length; i++) {
      expand(random_expansion[i], expansion);
    }
  // if the rule wasn't found, then it's a terminal: simply append the
  // string to the expansion
  } else {
    expansion.push(start);
  }
}
{% endhighlight %}

The [full implementation of the CFG object can be found here](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/01_cfg_raw/cf.js).  In addition, here are some sample grammars: [creating a grammar in code](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/01_cfg_raw/sketch.js), [a grammar file](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/02_cfg_reader/data/test.grammar), [a grammar files as JSON](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/03_cfg_reader_json/data/grammar.json), [haiku grammar file as JSON](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/03_cfg_reader_json/data/haiku.json).  These grammars come from [Allison Parrish](https://github.com/aparrish/rwet-examples) and [Daniel Howe](http://rednoise.org/rita/).

Finally, a grammar file is something we can generate ourselves as well based on some algorithm.  For example, using [this haiku grammar file from Daniel Howe](http://www.rednoise.org/rita/examples/haiku.g), we could read in a source text, analyze the syllable counts of all the words, and rewrite the grammar file to reflect these new words.  Here is a version of this grammar file using words from an Obama speech: [generated_grammar.g](http://shiffman.net/itp/classes/a2z/week09/2008/data/generated_grammar.g).

Here is a [node example that uses a concordance to get al the words from a source file and the RiTa library to count syllables](https://github.com/shiffman/Programming-from-A-to-Z-F14/blob/master/week4_generate/02_cfg/04_grammar_maker/grammarmaker.js).

<a name ="wordnik"></a>
## Wordnik

coming soon

<a name ="twitter"></a>
## Twitter Bots

coming soon



