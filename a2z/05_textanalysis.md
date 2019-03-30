---
title: Word Counting and Text Analysis
layout: a2z-post
permalink: /a2z/text-analysis/
---

## Word Counting and Text Analysis

<iframe width="525" height="300" src="https://www.youtube.com/embed/tE-ZYXU8A8U?list=PLRqwX-V7Uu6bZQkJcGM5S9fn9R9Yyd8iZ" frameborder="0" allowfullscreen></iframe>

### Examples
* [Word counting](https://shiffman.github.io/A2Z-F17/week5-analysis/01_concordance/) — [source code](https://github.com/shiffman/A2Z-F17/tree/master/week5-analysis/01_concordance)
* [Parts of Speech Concordance](https://shiffman.github.io/A2Z-F17/week5-analysis/02_pos_concordance/) — [source code](https://github.com/shiffman/A2Z-F17/tree/master/week5-analysis/02_pos_concordance)
* [Keyword extraction - TF-IDF](https://shiffman.github.io/A2Z-F17/week5-analysis/03_tf-idf/) — [source code](https://github.com/shiffman/A2Z-F17/tree/master/week5-analysis/03_tf-idf)
* [Text Classification - Naive Bayes](https://shiffman.github.io/A2Z-F17/week5-analysis/04_classification/) — [source code](https://github.com/shiffman/A2Z-F17/tree/master/week5-analysis/04_classification)
* [Node concordance](https://github.com/shiffman/A2Z-F17/tree/master/week5-analysis/05_node_concordance)

### Sample datasets:
* [Project Gutenberg](http://www.gutenberg.org/)
* [Enron e-mail corpus](http://www.aueb.gr/users/ion/data/enron-spam/)

### Related references
* [Secret Life of Pronouns](http://secretlifeofpronouns.com/), [Pennebaker Ted Talk](https://www.youtube.com/watch?v=PGsQwAu3PzU)
* [TF-IDF Single Page Tutorial](http://www.tfidf.com/)
* [Paul Graham's A Plan for Spam](http://www.paulgraham.com/spam.html) and [Better Bayesian Filtering](http://www.paulgraham.com/better.html)
* [Introduction to Bayesian Filtering](http://www.bcc.bilkent.edu.tr/BayesianFiltering.pdf)
* [Monty Hall and Bayes](http://sciencehouse.wordpress.com/2009/04/19/monty-hall-and-bayes/)
* [An Intuitive Explanation of Bayes' Theorem by Eliezer S. Yudkowsky](http://yudkowsky.net/rational/bayes)

### Related Projects
* [SPEECH COMPARISON](http://www.runemadsen.com/work/speech-comparison/) by Rune Madsen
* [Book-Book](http://www.sarahgp.com/index.html) by Sarah Groff-Palermo
* [Word Tree](https://www.jasondavies.com/wordtree/) by Jason Davies
* [Wordle](http://www.wordle.net/)
* [OK GO album covers](http://www.stefanieposavec.co.uk/-everything-in-between/#/ok-go-of-the-blue-colour-of-the-sky/) by Stefanie Posavec
* [Luke Dubois' Missed Connections](http://thecreatorsproject.vice.com/blog/craigslists-missed-connections-get-matched-by-an-algorithmic-cupid)
* [Luke Dubois' HindSight is always 20/20](http://hindsightisalways2020.net/)
* [Nicholas Felton's 2013 Annual Report](http://feltron.com/FAR13.html), [NY Times Article](http://bits.blogs.nytimes.com/2014/08/19/a-life-in-data-nicholas-feltons-self-surveillance/?_php=true&_type=blogs&_r=0)
* [Lyrical Indicators](http://style.org/lyrics/) and [Parsing the State of the Union](http://style.org/stateoftheunion/) by Jonathan Corum

### Exercise ideas
* Visualize the results of a concordance using canvas (or some other means).
* Expand the information the concordance holds so that it keeps track of word positions (i.e. not only how many times do the words appear in the source text, but where do they appear each time.)
* Implement some of the ideas specific to spam filtering to the bayesian classification example.
* In James W. Pennebaker's book [The Secret Life of Pronouns](http://secretlifeofpronouns.com/), Pennebaker describes his research into how the frequency of words that have little to no meaning on their own (I, you, they, a, an, the, etc.) are a window into the emotional state or personality of an author or speaker.  For example, heavy use of the pronoun “I” is an indicator of “depression, stress or insecurity”.  Create a page sketch that analyzes the use of pronouns.  For more, visit <a href="http://www.analyzewords.com/">analyzewords.com</a>.
* Use the ideas to find similarities between people. For example, if you look at all the e-mails on the ITP student list, can you determine who is similar? Consider using properties in addition to word count, such as time of e-mails, length of e-mails, who writes to whom, etc.

### Associative Arrays in JavaScript?

<iframe width="525" height="300" src="https://www.youtube.com/embed/_5jdE6RKxVk?list=PLRqwX-V7Uu6bZQkJcGM5S9fn9R9Yyd8iZ" frameborder="0" allowfullscreen></iframe>

You know that thing we call an array?  Yes, that's right, an ordered list of data.  Each element of an array is numbered and accessed by its numeric index.

{% highlight javascript %}
var nameList = ['Jane', 'Sue', 'Bob'];
// Is your name Sue?
console.log('Is your name ' + nameList[1] + '?');
{% endhighlight %}

What if, however, instead of numbering the elements of an array we could name them?  This element is named “Sue”, this one “Bob”, this one “Jane”, and so on and so forth.  In programming, this kind of data structure is often referred to as an “associative array”, “map”, “hash” or “dictionary.”  It's a collection of `key/value` pairs.  The key is `Sue`, the value is `24`.  It's just like having a dictionary of words and when you look up, say, `Sue` the definition is `24`.

Associative arrays can be incredibly convenient for various applications.  For example, you could keep a list of student IDs (`student name/id`) or a list of prices (`product name/price`) in a dictionary.  The fundamental building block of just about every text analysis application is a concordance, a list of all words in a document along with how many times each word occurred.  A dictionary is the perfect data structure to hold this information.  Each element of the dictionary consists of a String paired with a number.

Most programming languages and environments have specific classes or objects for a variety of data structures (a dictionary is just one example).  JavaScript, however, does not.  But all is not lost.  Remember that thing called a JavaScript object?

{% highlight javascript %}
var obj = {
  Sue: 24,
  Jane: 991,
  Bob: 12
};
{% endhighlight %}

That's right.  A JavaScript object is a collection of name-value pairs.  And so while it might be more convenient to have a custom-tailored dictionary object, we're going to be able to get all the functionality we need out of just a plain old object itself.

To start writing a concordance all we need is an empty object.

{% highlight javascript %}
var concordance = {};
{% endhighlight %}

A value (in this case a count) can be paired with a word by naming the key as a String.

{% highlight javascript %}
concordance['the'] = 100;
concordance['a'] = 10;
concordance['go'] = 50;
{% endhighlight %}

The above is just another way of writing:

{% highlight javascript %}
var concordance = {
  the: 100,
  a: 10,
  go: 50
};
{% endhighlight %}

We'll need this new way since we'll be pulling the names for the object as strings from a source text.

### Text Concordance

<iframe width="525" height="300" src="https://www.youtube.com/embed/unm0BLor8aE?list=PLRqwX-V7Uu6bZQkJcGM5S9fn9R9Yyd8iZ" frameborder="0" allowfullscreen></iframe>

In the case of our examples, we're going to take a text document, split it into an array of Strings and increase the value associated with a particular key (i.e. word) each time we encounter the same String.  Let's assume we have some text in a variable named `data`.  First, we'll split into word "tokens".

{% highlight javascript %}
// Using any non letter or number character as delimiter
var tokens = data.split(/\W+/);
{% endhighlight %}

Then we'll go through each one a a time.

{% highlight javascript %}
for (var i = 0; i < tokens.length; i++) {
{% endhighlight %}

The tricky thing here is we have to determine if each token (each element of the resulting array) is a new word or one we've already encountered.  If it's new, we need to set its initial count at 1.  If it's not, we need to increase its count by one.

{% highlight javascript %}
for (var i = 0; i < tokens.length; i++) {
  var word = tokens[i];
  // It's a new word!
  if (concordance[word] === undefined) {
    concordance[word] = 1;
  // We've seen this word before!
  } else {
    concordance[word]++;
  }
}
{% endhighlight %}

There, we now have a concordance object that stores all the words and their counts!  The question, however, remains: what do we do with this thing?

The first thing you might want to do is simply examine the results.  For example, let's say we wanted to display the most frequent words (in sorted order).   Unfortunately, the fields of a JavaScript object have no order to them and cannot be easily sorted.  One solution to this problem is to keep a separate array of all the keys.  This array can be sorted and used to iterate over all the name/value pairs in the concordance object.

{% highlight javascript %}
// Add another array to track keys
var keys = [];
for (var i = 0; i < tokens.length; i++) {
  var word = tokens[i];
  if (concordance[word] === undefined) {
    concordance[word] = 1;
    // When we have a new word, let's add to our keys array!
    keys.push(word);
  } else {
    concordance[word]++;
  }
}
{% endhighlight %}

While we could write our own sorting algorithm in JavaScript to sort the array, we might as well make use of the `sort()` function available as part of the Array prototype.  The tricky thing here is that the sort function expects as an argument which a function itself!

{% highlight javascript %}
keys.sort(function(a, b) {
  // what goes here??
});
{% endhighlight %}

This is pretty typical of JavaScript and functional programming.  Here we have an anonymous function that we pass into the `sort()` function itself.  This function takes two arguments: `a` and `b`.  The function is a **comparison** function and should return true if element `b` should appear before `a` in the sorted result.

{% highlight javascript %}
keys.sort(function(a, b) {
  if (concordance[b] > concordance[a]) {
    return true;
  } else {
    return false;
  }
});
{% endhighlight %}

This can be condensed since a positive number is evaluated as `true` and a negative one as `false`.

{% highlight javascript %}
keys.sort(function(a, b) {
  return (concordance[b] - concordance[a]);
});
{% endhighlight %}

Now that we have sorted keys, we can iterate over the concordance.

{% highlight javascript %}
for (var i = 0; i < keys.length; i++) {
  console.log(keys[i] + ': ' + concordance[keys[i]]);
}
{% endhighlight %}

Here is a [text concordance example](http://shiffman.net/teaching/a2z/analysis/01_concordance/) and its [source code](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week3_analysis/01_concordance).

### TF-IDF

<iframe width="525" height="300" src="https://www.youtube.com/embed/RPMYV-eb6lI?list=PLRqwX-V7Uu6bZQkJcGM5S9fn9R9Yyd8iZ" frameborder="0" allowfullscreen></iframe>


One common application of a text concordance is [TF-IDF](http://en.wikipedia.org/wiki/Tf%E2%80%93idf) or term frequency–inverse document frequency.  Let's consider a corpus of wikipedia articles.  Is there a way we could automatically generate keywords or tags for an article  based on its word counts?  

TF-IDF has two components.  Term frequency is one that we are already quite familiar with.  How frequent is a given term in a document?  This is exactly what we calculated in the concordance.  We could stop here and say that keyword generation is: "The words that appear most frequently are most important in a document."    While there is some merit to this idea, what we'll see is that the most frequent words are just the words that appear frequently in all text: junk words like 'to', 'a', 'and', 'you', 'me', etc.  [Ironically, these junk words may hold the key to unlocking a world of information about a particular text](http://secretlifeofpronouns.com/).  Nevertheless, these are clearly not related to a document's subject matter as keywords.  

TF-IDF takes a different approach. Yes, a word that appears frequently in a document (TF) is one key indicator.  But adding in another indicator such as inverse document frequency (is it a word that rarely appears in other documents?) takes the junk words out of the equation  Let's consider a wikipedia article about rainbows.  Here are some of the counts:

{% highlight text %}
the:      16
and:      6
rainbow:  5
droplets: 3
{% endhighlight %}

Using this as a keyword score alone is not enough since the most important word is 'the'.  Now let's say we looked at five other wikipedia articles.  Let's now count how many articles each of these words appear at least once in.

{% highlight text %}
the:      6
and:      6
rainbow:  1
droplets: 1
{% endhighlight %}

This is a somewhat obvious result: 'the' and 'and' appear in all the articles and 'rainbow' and 'droplet' appear in both.  We could therefore compute a score for each of these as:

{% highlight text %}
rainbow:   5 * (6/1)   30
droplets:  3 * (6/1)   18
the:      16 * (6/6)   16
and:       6 * (6/6)   6
{% endhighlight %}

Now we're getting somewhere!  

TF-IDF is meant to be run on a much larger corpus and in order to dampen the effect of the IDF value, a common solution is to use the logarithm of IDF.

{% highlight text %}
rainbow:   5 * log(6/1)   3.89
droplets:  3 * log(6/1)   2.33
the:      16 * log(6/6)   0.0
and:       6 * log(6/6)   0.0
{% endhighlight %}

If [logarithmic scale](http://en.wikipedia.org/wiki/Logarithmic_scale) is new to you, this Khan Academy video may help.  (Note how if a term appears in every single document the tf-idf score is always zero.)

<iframe width="525" height="300" src="//www.youtube.com/embed/sBhEi4L91Sg" frameborder="0" allowfullscreen></iframe>

We can improve this one more step by using not just the raw count of how many times a term (such as "rainbow") appears in a document, but the ratio of of its count to the total number of words in the document.  This normalizes the score by document length.  So if the total number of words in the article is 100, the score would now be:

{% highlight text %}
rainbow:  (5/100) * log(6/1)   0.0389
droplets: (3/100) * log(6/1)   0.0233
the:     (16/100) * log(6/6)   0.0
and:      (6/100) * log(6/6)   0.0
{% endhighlight %}

In the case of only examining this document it makes no difference, but if we were looking at the score for "rainbow" across multiple documents without this change the score would be biased towards longer documents.

For a wonderful example of TF-IDF out in the world, take a look at [Nicholas Felton's 2013 Annual Report](http://bits.blogs.nytimes.com/2014/08/19/a-life-in-data-nicholas-feltons-self-surveillance/).

### Naive Bayesian Text Classification

#### Bayes' Theorem:

{% highlight text %}
p(A|B) = (p(B|A) * p(A)) / (p(B|A) * p(A) + p(B|~A) * p(~A) )
{% endhighlight %}

Consider the following scenario:

* 1% of all ITP students are afflicted with a rare disease known as ITPosis
* There is a test you can take to determine if you have it, known as a TID (Test for Interactive Disease).
* 90% of all students with ITPosis will receive a positivie TID (i.e. 10% that have the disease will receive a false negative).
* 95% of students without ITPosis will receive a negative TID (i.e. 5% will receive false positives).

You have received a positive TID, what is the likelihood you have ITPosis?

As you might expect, there is a very precise answer to this question but it's probably not what you initially guess.  Bayesian reasoning is counter-intuitive and takes quite a bit of getting used to.  In fact, when [given a similar question related to breast cancer and mammograms](http://yudkowsky.net/rational/bayes), only 15% of doctors get the answer correct.

The answer — 15.3% — is calculated via Bayes' Theorem.  Let's look at it again with this scenario:

* There are 1000 students.
* 10 of them have ITPosis.
* 9 of those 10 with the disease will receive a positive TID.
* Out of the 990 w/o ITPosis, ~50 will receive positive TIDs.
* Therefore, 59 total students receive positive TIDs, 9 of which actually have the disease, 50 do not.
* The chance one has the disease if the test is positive is therefore 9 / 58 = 15.5% (off slightly from the exact result b/c of rounding).

This video illustrates the problem quite nicely.

<p><iframe width="525" height="300" src="//www.youtube.com/embed/D8VZqxcu0I0" frameborder="0" allowfullscreen></iframe></p>

The problem our brains run into are those rascally 90% and 95% numbers.  90% of students who test positive have the disease and 95% who don't test negative, if I test positive, I should probably have it, right?!!  The important thing to remember is that only 1% of students actually have the disease. Sure testing positive increases the likelihood, but because 5% of students without the disease receive a false positive, it only increases the chances to 15%.  All of this is explained in incredibly thorough and wonderful detail in [Eliezer Yudkowsky's](http://yudkowsky.net/) article [An Intuitive Explanation of Bayesian Reasoning](http://yudkowsky.net/rational/bayes).  My explanation is simply adapted from his.

By the way, we could have calculated it as follows:

<pre>
  P (ITPosis | Positive TID) = (90% * 1%) / (90% * 1% + 5% * 99%)
</pre>

This reads as "the probability that a positive TID means you have ITPosis" equals:

So why do we care?  This type of reasoning can be applied quite nicely to text analysis.   A common example is spam filtering.  If we know the probability that a spam e-mail contains a specific words, we can calculate the likelihood that an e-mail is spam based on its concordance.

A wonderful resource for this approach is [Paul Graham's A Plan for Spam](http://www.paulgraham.com/spam.html) as well as [Better Bayesian Filtering](http://www.paulgraham.com/better.html).

The example code that follows is not a perfect text classifier by any means.  It's a simple implementation of the idea that outlines the basic steps one might take to apply [Bayesian Filtering](http://en.wikipedia.org/wiki/Bayesian_filtering) to text.

#### A Word Object

The first thing we need to do is expand on the concordance example that stores a single number associated with each word.  For classification, we'll need to know things like how many times that word appears in spam e-mails versus good (aka 'ham') e-mails.  And then we'll need to use these values to calculate the probability that each word would appear in a spam or ham e-mail.

{% highlight javascript %}
var word = {};
word.countA = 0;   // category A count
word.countB = 0;   // category B count
word.probA = ???;  // probability word appears in category A doc
word.probB = ???;  // probability word appears in category B doc
// etc. etc.
{% endhighlight %}

Instead of storing a single number like `dictionary['the'] = 16;` we now need to associate an object with multiple data points with each key.The process of running the filter works as follows:

1. Train the filter with known category A (for example: spam) e-mails and known category B (ham) e-mails.  
2. For every word, check if it's new.  If it is add it, if not, simply increase the counter for "A" or "B" (depending on whether it's found in A or B).

Here's how this might look:

{% highlight javascript %}
for (var i = 0; i < tokens.length; i++) {
  var token = tokens[i].toLowerCase();
  if (dictionary[token] === undefined) {
    dictionary[token] = {};
    dictionary[token].countA = 0;
    dictionary[token].countB = 0;
    dictionary[token].word = token;
  } else {
    // Which category are we training for?
    if (category === 'A') {
      this.dict[token].countA++;
      this.tokenCountA++;
    } else if (category === 'B') {
      this.dict[token].countB++;
      this.tokenCountB++;
    }
  }
}
{% endhighlight %}

The above steps are repeated over and over again for all training documents.  Once all the "training" files are read, the probabilities can be calculated for every word.

Once we've gone through the process of counting the occurrences in each category ('A' or 'B', spam or ham, etc.), we can the calculate the probabilities according to Bayes rule.

{% highlight javascript %}
// Ok, assuming we have an array of keys
for (var i = 0; i < keys.length; i++) {
  var key = keys[i];
  var word = dictionary[key];

  // Average frequency per document
  // (this assumes we've counted total documents)
  word.freqA = word.countA / docCountA;      
  word.freqB = word.countB / docCountB;      

  // Probability via Bayes rule
  word.probA = word.freqA / (word.freqA + word.freqB);
  word.probB = 1 - probA;
}
{% endhighlight %}

The above formula might look a little bit simpler to you than the original Bayes rule.  This is because I am leaving out the "prior probability" and assuming that any document has a 50% chance of being category A or B.


Now, all that is left to do is take a new document, and compute the total probability for that document according to the formula specified in <a href="http://www.paulgraham.com/spam.html">Graham's essay</a>.  For this step, we need to calculate [combined probability](http://www.paulgraham.com/naivebayes.html) as outlined by Graham.  For more about combined probability, here's [another resource](http://www.mathpages.com/home/kmath267.htm).

{% highlight java %}
// Combined probabilities
// http://www.paulgraham.com/naivebayes.html
var productA = 1;
var productB = 1;

// Multiply probabilities together
for (var i = 0; i < words.length; i++) {
  var word = words[i];
  productA *= word.probA;
  productB *= word.probB;
}

// Apply formula
var pA = productA / (productA + productB);
{% endhighlight %}

Now we know the probability the document is in category A!

One important aspect of this analysis that I've left out is the "interesting-ness" of any given word.  An interesting rating is defined as how different, say, the spam probability is from 0.5 (i.e. 50/50 is as boring as it gets) or the absolute value of `probA - 0.5`. Graham's spam filter, for example, only uses the probability of the top 15 most interesting words. If you are looking for an exercise, you might try adding this feature to the Bayesian classifier example.

<iframe width="525" height="300" src="https://www.youtube.com/embed/6DoJob85jE0?list=PLRqwX-V7Uu6bZQkJcGM5S9fn9R9Yyd8iZ" frameborder="0" allowfullscreen></iframe>
