---
title: Text Analysis
author: Daniel
layout: page
dsq_thread_id:
  - 249553164
pvc_views:
  - 25117
---

## This week&#8217;s topics:
* [Associative Arrays in JavaScript?](#dictionary)
* [Text Concordance](#concordance)
* [TF-IDF](#tfidf)
* [Naive Bayesian Classification](#bayes)

## P5 examples
* [Regex Basics](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/01_regexbasics)
* [Find double words](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/02_doublewords)
* [Double the vowels](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/03_voweldoubler)
* [Test a regex](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/04_regex_tester)
* [Find links](https://github.com/shiffman/Programming-from-A-to-Z-F14/tree/master/week2_regex/regex_p5/05_linkfinder)

## Related references

## Exercise ideas

<a name ="dictionary"></a>
## Associative Arrays in JavaScript?

You know that thing we call an array?  Ok, good.  Yes, an array is ordered list of data. Each element of the array is numbered and be accessed by its numeric index.

What if, however, instead of numbering the elements of an array we could name them?  This element is named “Sue”, this one “Bob”, this one “Jane”, and so on and so forth.  In programming, this kind of data structure is often referred to as an “associative array”, “map”, “hash” or “dictionary.”  It's a collection of <b><em>(key, value)</em></b> pairs.  The key is “Sue”, the value is 24.  It's just like having a dictionary of words and when you look up, say, “Sue” the definition is 24.</p>

Associative arrays can be incredibly convenient for various applications.  For example, you could keep a list of student IDs <b><em>(student name, id)</em></b> or a list of prices <b><em>(product name, price)</em></b> in a dictionary.  The fundamental building block of just about every text analysis application is a concordance, a list of all words in a document along with how many times each word occurred.  A dictionary is the perfect data structure to hold this information.  Each element of the dictionary is a word paired with its count.

Most programming languages and environemnts have specific classes or objects for advanced data structures.  JavaScript, however, does not.  But all is not lost.  Remember that thing called a JavaScript object?

{% highlight javascript %}
var obj = {
  Sue: 24,
  Jane: 991,
  Bob: 12
};
{% endhighlight %}

That's right.  A JavaScript object is a collection of name-value pairs.  And so while it might be more convenient to have a custom-tailored dictionary object, we're going to fairly easy be able to get all the functionality we need out of just a plain old object itself.

To start writing a concordance program then all we need is an empty object.

{% highlight javascript %}
var concordance = {};
{% endhighlight %}

A value (in this case a count) can be paired with its key by naming the key as a String.

{% highlight javascript %}
concordance['the'] = 100;
concordance['a'] = 10;
concordance['go'] = 50;
{% endhighlight %}

<a name ="concordance"></a>
## Text Concordance

In the case of our examples, we're going to take a document, split it into an array and increase the value associated with a particular key (i.e. word) one at a time.  Let's assume we have some text in a variable named `data`.  First, we'll split into word "tokens".

{% highlight javascript %}
var tokens = data.split(/\W+/);
{% endhighlight %}

Then we'll go through each one a a time.

{% highlight javascript %}
for (var i = 0; i < tokens.length; i++) {
{% endhighlight %}

The tricky thing here is we have to determine if this is a word we've encountered before or a new one.  If it's new, we need to set its initial count at 1.  If it's not new, then we just need to increase its count by one.

{% highlight javascript %}
for (var i = 0; i < tokens.length; i++) {
  var word = tokens[i];
  if (concordance[word] === undefined) {
    concordance[word] = 1;
  } else {
    concordance[word]++;
  }
}
{% endhighlight %}

There, we now have a concordance object that stores all the words and their counts!  The question, however, remains: what do we do with this thing?

The first thing you might want to do is simply examine the results.  For example, let's say we wanted to display the most frequent words (in sorted order).   Unfortunately, the fields of a JavaScript object have no order to them and cannot be easily sorted.  In this case, it can be advantageous to keep a separate array of all the keys.  This array can be sorted easily and used to iterate over the concordance.

{% highlight javascript %}
var keys = [];
for (var i = 0; i < tokens.length; i++) {
  var word = tokens[i];
  keys.push(word);
}
{% endhighlight %}

While we could write our own sorting algorithm in JavaScript to sort the array, we might as well make use of the `sort()` function available as part of the Array prototype.  The tricky thing here is that the sort function expects as an argument which a function itself!

{% highlight javascript %}
keys.sort(function(a, b) {
  // what goes here??
});
{% endhighlight %}

This is pretty typical of JavaScript and functional programming.  Here we have an anonymous function that we pass into the `sort()` function itself.  This function takes two arguments: `a` and `b`.  The function is a **comparison** function and should return true if a should appear before b in the sorted result.

{% highlight javascript %}
keys.sort(function(a, b) {
  if (concordance[a] > concordance[b]) {
    return true;
  } else {
    return false;
  }
});
{% endhighlight %}

This can be condensed since a positive number is evaluated as `true` and a negative one as `false`.

{% highlight javascript %}
keys.sort(function(a, b) {
  return (concordance[b] -  concordance[a]);
});
{% endhighlight %}

Now that we have sorted keys, we can iterate over the concordance.

{% highlight javascript %}
for (var i = 0; i < keys.length; i++) {
  console.log(keys[i] + ': ' concordance[keys[i]]);
}
{% endhighlight %}


