---
title: JavaScript Closures
layout: a2z-post
permalink: /a2z/closures/
---
<head>
<script language="javascript" type="text/javascript" src="/js/p5.js"></script>
<script language="javascript" type="text/javascript" src="/js/p5.dom.js"></script>
<script language="javascript" type="text/javascript" src="/a2z/js/closure.js"></script>
</head>

## Closures

<iframe width="560" height="315" src="https://www.youtube.com/embed/-jysK0nlz7A" frameborder="0" allowfullscreen></iframe>

### Examples
* Closure Basics — [source code](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-closures/00_closure_basics)
* [Closure for Animation 1](https://shiffman.github.io/A2Z-F17/week3-closures/00_closure_basics/) — [source code](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-closures/01_closure_for_animation)
* [Closure for Animation 2](https://shiffman.github.io/A2Z-F17/week3-closures/01_closure_for_animation/) — [source code](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-closures/02_closure_for_animation_part2)
* [Closure with API call and animation while loading](https://shiffman.github.io/A2Z-F17/week3-closures/03_closure_animation_while_API_loading/) — [source code](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-closures/03_closure_animation_while_API_loading)
* [Closure with loop through many delayed API calls](https://shiffman.github.io/A2Z-F17/week3-closures/04_closure_delay_API_calls/) — [source code](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-closures/04_closure_delay_API_calls)

### Related references
* [Mozilla Closure page](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures)
* [Eloquent JavaScript section](http://eloquentjavascript.net/03_functions.html#h_hOd+yVxaku)

### Closures

Closures are a key aspect of the JavaScript programming language.  

>Closures are functions that refer to independent (free) variables (variables that are used locally, but defined in an enclosing scope). In other words, these functions 'remember' the environment in which they were created.—[Mozilla Developer Network]( href="]https://developer.mozilla.org/en-US/docs/Web/JavaScript/Closures)

This page offers a cursory overview of the idea of a closure along with a couple scenarios particularly relevant to the examples for this course.

Variable scoping is key to the idea of closures.  Lets briefly look at how variables are scoped in a JavaScript function.

{% highlight javascript %}
// Global variable! Available everywhere!
var x = 100;  

function logXY() {
  // Local only to the logXY() function!
  var y = 50;

  // Both variables are available here!
  console.log(x + ',' + y);
}

// Executing the function logXY()
logXY();

// Result: 100,50
{% endhighlight %}

`x` is a global variable so its viewable both outside of and inside `logXY()`.  `y` is local only to `logXY()` and is therefore only available inside `logXY()`.

Now let's adjust the scenario above as follows:

{% highlight javascript %}

// Global variable
var logAgain;

function setup() {

  // Local variable! Only available in setup.
  var x = 100;  

  // logXY() local to setup() only!
  function logXY() {
    // Local only to the logXY() function!
    var y = 50;
    // Both variables are available here!
    console.log(x + ',' + y);
  }

  // Assign logXY to logAgain
  logAgain = logXY;
}

function mousePressed() {
  logAgain();
  // Result: 100,50
}
{% endhighlight %}

If you are used to programming languages like Java or C++, the variable `x`, for example, is local only to `setup()`.  So once `setup()` finishes executing its no longer needed and goes away!  So when `logAgain()` is called later in `mousePressed()`, there should be nothing storing a reference to `x` and the result should be `undefined`.

But it's not!

This is the magic of JavaScript.  Whenever a function is declared, a "closure" which stores both a reference to the function as well as all variables currently in scope for that function is maintained.  One way to think about this is that scope isn't just about where in the code things are declared, but it's also about when things actually happen.  So while `x` and `y`'s scope are limited to `setup()`, they also extend beyond `setup()` to anytime that `logXY()` function is called.

This idea of keeping scope for "later" is crucial for callbacks and becomes relevant for scenarios like animation and API callbacks.  Let's look at animation first.

### Closure for Animation

Let's say you have a DOM element.

{% highlight javascript %}
var count = 0;
var p = createP(count);
{% endhighlight %}

And let's say you want to use `setInterval()` to change the element's count every N millseconds.

{% highlight javascript %}

// Call countIt every 100 milliseconds
setInterval(countIt, 100);

// Increase the count and set the new content
function countIt() {
  count++;
  p.html(count);
}
{% endhighlight %}

<div id="count1" class="box"></div>

Now let's say you wanted to do the same for more than one paragraph, and have the second paragraph keep its own "count".  Sure you could create `count1` and `count2` and call `setInterval()` twice.  But better yet, you could use a closure!

{% highlight javascript %}
var p1 = createP('0');
var p2 = createP('0');
animate(p1, 100);
animate(p2, 50);

function animate(elt, howlong) {
  // A closure!
  // Even though count has local scope
  // increment() is called later by setInterval()
  // and so count is maintained by increment()'s "closure"
  // oh and "elt" and "howlong" too!
  var count = 0;

  function increment() {
    count = count + 1;
    elt.html(count);
  }

  setInterval(increment, howlong);
}
{% endhighlight %}

<div id="count2" class="box"></div>
<div id="count3" class="box"></div>


### Closure for API callback

Closures are also helpful when querying APIs.  Let's say for example, you have several DOM elements, each associated with a particular term.  And whenever you click on any of the elements, you want to make an API call with the associated term.

{% highlight javascript %}
var words = ['rainbow', 'heart', 'sparkle', 'canteen', 'ridiculous'];

for (var i = 0; i < words.length; i++) {
  var a = createA('#', words[i]);
  // When you click the link, call queryAPI
  a.mousePressed(queryAPI);
}
{% endhighlight %}

Now the idea here is that the `queryAPI` function would connect to the API itself.


{% highlight javascript %}
function queryAPI() {
  var url = 'http://api.madeup.com/?q=' + ___________;
  loadJSON(url, gotData);
}
{% endhighlight %}

Ooops!  There's no reference to the particular word associated with the element that was clicked on. (While yes, there are ways to get access to that word from the DOM element itself for the purpose of demonstrating the closure I'll skip that as an option.)

Here what you may be thinking the following: "I want to pass an argument to `queryAPI`. In other words, you want to execute the callback `queryAPI` with the value stored in `words[i]`.  But you can't.  That is, unless you use a closure!

Here, the technique is to instead call a function where the callback is defined as a closure that maintains a reference to the particular term associated with the callback.  In other words;

{% highlight javascript %}
var words = ['rainbow', 'heart', 'sparkle', 'canteen', 'ridiculous'];

for (var i = 0; i < words.length; i++) {
  var a = createA('#', words[i]);
  assignQuery(a, words[i]);
}
{% endhighlight %}

And then the `assignQuery` pairs the value in `words[i]` with the `mousePressed` event for the DOM element.

{% highlight javascript %}
function assignQuery(elt, word) {

  // This hasn't changed!
  elt.mousePressed(queryAPI);

  // Neither has this!  But because of the closure
  // we now have a reference to the word
  function queryAPI() {
    var url = 'http://api.madeup.com/?q=' + word;
    loadJSON(url, gotData);
  }
}
{% endhighlight %}

If the `gotData()` function is also defined inside `assignQuery()` then it can also make use of the closure maintaining a reference to the DOM element and associated word.

Again, the key here is that while the scope of `elt` and `word` is defined locally to `assignQuery`, event though `queryAPI` may happen later whenever the user clicks the mouse, long after `assignQuery` was executed, that scope is maintained by the `queryAPI`'s closure which maintains a reference to all variables within its own scope.

Here is the a [full example that queries wordnik](13_closure_animation_while_API_loading/) and returns substitutes a "related" in the DOM element.  And the [source code](https://github.com/shiffman/A2Z-F15/tree/gh-pages/week8/13_closure_animation_while_API_loading).  The example also uses the "animation" closure to count while waiting for data back from the API.

### Exercise ideas

#### 1: Closure with setTimeout()

Use a closure to create 100 DOM elements with `setTimeout()`.  Here is code that doesn't make use of a closure and does not work properly.

{% highlight javascript %}
for (var i = 0; i < 100; i++) {
  setTimeout(makeElt, i * 100);
}

function makeElt() {
  createDiv('Number: ' + i);
}
{% endhighlight %}


#### 2: Closure to animate a DOM element.

Use a closure to animate a DOM element in some way with the `style()` function.  (Fill in the blanks).

{% highlight javascript %}
function animate(_______) {

  ______________________________;

  setInterval(________, ________);

  function _______() {
    ______.style(________, ________);
  }
}
{% endhighlight %}

Then assign the function to a single element:

{% highlight javascript %}
animate(elt);
{% endhighlight %}

Then start animating elements only once you click on them:

{% highlight javascript %}
elt.mousePressed(_________);

function(__________) {

}
{% endhighlight %}

Now make many elements, each that start animating when you click on them.  Do you need a closure now that you are looping through the elements? (Hint: yes, you do except for the fact the p5.js will assign the `this` keyword to the element in a `mousePressed` callback.)

Finally, can you get your `animate()` function to return a reference to the interval so that you can start and stop the animation when you click on it?  This one is hard!

#### 3: Closure for an API call.

Use a closure to make an API call to openweathermap.org.  Send openweathermap a zip code and when the weather is returned, create a DOM element with that zip code and the weather data.  Here is some code to help you get started.

{% highlight javascript %}

var zip = '10003';

assignQuery(zip);

function assignQuery(_________) {

  loadJSON(_________, _________);

  function _________(_________) {
    _____________________________________________
    _____________________________________________
    // etc.
  }

}
{% endhighlight %}

Now can you make this work with multiple zip codes?  Can you make buttons for each zip code that when you click on each button an API call is made for a particular zip code?
