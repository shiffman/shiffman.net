---
title: Data, Libraries, and APIs
layout: a2z-post
permalink: /a2z/data-apis/
---

## Data, Libraries and APIs

### Simple JSON
* [JSON from Corpora](https://shiffman.github.io/A2Z-F17/week3-apis-data/00_corpora/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/00_corpora)

### JavaScript Language Processing Libraries
* [RiTa.js Demo](https://shiffman.github.io/A2Z-F17/week3-apis-data/01_rita_demo/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/01_rita_demo)
* [NLP Compromise Demo](https://shiffman.github.io/A2Z-F17/week3-apis-data/02_nlp_compromise/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/02_nlp_compromise)

### API examples
* [Wordnik -- get random words](https://shiffman.github.io/A2Z-F17/week3-apis-data/03_wordnik_randomwords/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/03_wordnik_randomwords)
* [Wordnik -- word info](https://shiffman.github.io/A2Z-F17/week3-apis-data/04_wordnik_word_info/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/04_wordnik_word_info)
* [Wordnik -- related words](https://shiffman.github.io/A2Z-F17/week3-apis-data/05_wordnik_related/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/05_wordnik_related)
* [NYTimes API article search](https://shiffman.github.io/A2Z-F17/week3-apis-data/06_nytimes_articles/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/06_nytimes_articles)
* [Wikipedia API article search](https://shiffman.github.io/A2Z-F17/week3-apis-data/07_wikipedia/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/07_wikipedia)
* [NYTimes word counts -- many API calls](https://shiffman.github.io/A2Z-F17/week3-apis-data/08_nytimes_word_counts/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/08_nytimes_word_counts)
* [Google Sheets with tabletop.js](https://shiffman.github.io/A2Z-F17/week3-apis-data/09_google_sheets/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/09_google_sheets)
* [MadLibs with Google Sheets](https://shiffman.github.io/A2Z-F17/week3-apis-data/10_google_sheets_madlibs/), [source](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week3-apis-data/10_google_sheets_madlibs)


### RiTaJS

<iframe width="525" height="300" src="https://www.youtube.com/embed/lIPEvh8HbGQ?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

When I think of programming libraries and frameworks for text-based data I think of python and libraries like the [Nature Language Toolkit (NLTK)](http://www.nltk.org/).  However, there are still lots of options for language and text-based code libraries for JavaScript.  I'll look at two on this page starting with RiTaJS.  [RiTaJS](https://github.com/dhowe/RiTaJS) is the JavaScript implementation of [RiTa](http://rednoise.org/rita/) by Daniel Howe.

<blockquote>Designed to support the creation of new works of computational literature, the RiTa library provides tools for artists and writers working with natural language in programmable media.</blockquote>

The RiTa library has numerous features around text analysis and generation.  For example, it has features built into it to generate text with algorithms and systems (Markov Chains, Context Free Grammer) I'll cover in later tutorials.

To use RiTa, you can grab the JavaScript library files from the [RiTa download page](http://rednoise.org/rita/download.php).  For my examples, I'm using the RiTa Lexicon which requires `rita-full.js`.

For now I want to look at two features, the `RiString` object and the `RiLexicon` object.  `RiString` allows you to analyze a piece of text.  You can tokenize it, count syllables, determine parts of speech, etc.  One particularly useful function is `features()` which returns an object with features for the sentence, including phonemes, syllables, stresses, etc.

{% highlight javascript %}
var s = "It was a dark and stormy night.";
var rs = new RiString(s);
// Look at the "features" of a string.
console.log(rs.features());
{% endhighlight %}

![rita1](/a2z/images/ristring.png)

It should be noted that the parts of speech tags are from the [Penn Treebank Project](https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html).

RiTaJS also has a lexicon built into it.  A lexicon is another word for "vocabulary" and operates like a machine readable dictionary.  [The RiTa lexicon](http://rednoise.org/rita/reference/RiLexicon.php) contains about 40,000 words along with associated spelling and phonemic data.  The library provides many hooks into the lexicon.  For example, you can ask it for random words of a given part of speech or with a certain syllable account.  It also can provide words that rhyming words and words that "sound similar." To use the lexicon, you simply need to make a `RiLexicon` object.

{% highlight javascript %}
var lexicon = new RiLexicon();
{% endhighlight %}

Once you have the object you can query it anywhere in your code.

{% highlight javascript %}
// Random noun
lexicon.randomWord('nn');

// List of words that rhyme with cat
lexicon.rhymes('cat');

// Alliterations
lexicon.alliterations('cat');
{% endhighlight %}

You can also customize the lexicon by editing the JS library files themselves or programmatically with [`addWord()`](http://rednoise.org/rita/reference/RiLexicon/addWord/index.php) and [`removeWord`]((http://rednoise.org/rita/reference/RiLexicon/removeWord/index.php)).  

### NLP-Compromise

<iframe width="525" height="300" src="https://www.youtube.com/embed/tk_JGu2AbJY?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

Another useful natural language processing library in JavaScript is [NLP-Compromise](http://nlp-compromise.github.io/website/), source on [github](https://github.com/nlp-compromise/nlp_compromise) by [Spencer Kelly](https://s3.amazonaws.com/spencermounta.in/index.html).

Just like with RiTaJS you can download the library files to use with your sketch.  However, most JavaScript libraries are also available via a "CDN" (Content Delivery Network) meaning you can reference them on a web server directly in `index.html`.

{% highlight html %}
<script src="https://unpkg.com/nlp_compromise@latest/builds/nlp_compromise.min.js"></script>
{% endhighlight %}

Once the library is loaded you can create a variable to call of its functions on.

{% highlight javascript %}
var nlp = window.nlp_compromise;
{% endhighlight %}

NLP-Compromise works by allowing you to chain together a series of functions that build and/or adjust a block of text.  For example, if you want to work with a noun you would say:

{% highlight javascript %}
var noun = nlp.noun('cat');
console.log(noun.pluralize()); // cats
{% endhighlight %}

But typically you'll see these actions chained together:

{% highlight javascript %}
var cats = nlp.noun('cat').pluralize());
{% endhighlight %}

NLP-Compromise can negate statements, conjugate verbs (and therby alter tense), provide articles and pronouns, and more.  

### Data

<iframe width="525" height="300" src="https://www.youtube.com/embed/rJaXOFfwGVw?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

Some parts of this page is excerpted (and adapted for JavaScript) from <a href="http://learningprocessing.com">Learning Processing</a>.

<p>Data can come from many different places: websites, news feeds, spreadsheets, databases, and so on. Let's say you've decided to make a map of the world's flowers. After searching online you might find a PDF version of a flower encyclopedia, or a spreadsheet of flower genera, or a JSON feed of flower data, or a REST API that provides geolocated lat/lon coordinates, or some web page someone put together with beautiful flower photos, and so on and so forth. The question inevitably arises: “I found all this data; which should I use, and how do I get it?”</p>

<p>If you are really lucky, you might find a JavaScript library that hands data to you directly with code. Maybe the answer is to just download this library and write some code like:</p>

{% highlight javascript %}
function setup() {
  var fdb = new FlowerDatabase();
  var sunflower = fdb.findFlower("sunflower");
  var h = sunflower.getAverageHeight();
}  
{% endhighlight %}

<p>In this case, someone else has done all the work for you. They've gathered data about flowers and built a library with a set of functions that hands you the data in an easy-to-understand format. This library, sadly, does not exist (not yet), but there are some that do.</p>

<p>Let's take another scenario. Say you’re looking to build a visualization of Major League Baseball statistics. You can't find a library to give you the data but you do see everything you’re looking for at mlb.com. If the data is online and your web browser can show it, shouldn't you be able to get the data? Passing data from one application (like a web application) to another is something that comes up again and again in software engineering. A means for doing this is an API or “application programming interface”: a means by which two computer programs can talk to each other. Now that you know this, you might decide to search online for “MLB API”. Unfortunately, mlb.com does not provide its data via an API. In this case you would have to load the raw source of the website itself and manually search for the data you’re looking for. While possible, this solution is much less desirable given the considerable time required to read through the HTML source as well as program algorithms for parsing it.</p>

<p>The goal of these notes is to give you an overview of techniques, ranging from the more difficult manual parsing of data, to the parsing of standardized formats, to the use of an API designed specifically for JavaScript itself. Each means of getting data comes with its own set of challenges. The ease of using a JavaScript library is dependent on the existence of clear documentation and examples. But in just about all cases, if you can find your data in a format designed for a computer (spreadsheets, XML, JSON, etc.), you'll be able to save some time in the day for a nice walk outside.</p>


<h1>JSON</h1>

<iframe width="312" height="175" src="https://www.youtube.com/embed/_NFkzw6oFtQ?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>
<iframe width="312" height="175" src="https://www.youtube.com/embed/118sDpLOClw?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

<p>The data exchange format that all of this week's examples focus on is called  JSON (pronounced like the name Jason), which stands for JavaScript Object Notation. Its design was based on the syntax for objects in the JavaScript programming language (and is most commonly used to pass data between web applications) but has become rather ubiquitous and language-agnostic.  Working with it in JavaScript is incredibly convenient.</p>

<p>All JSON data comes in the following two ways: an object or an array.  And the syntax for these is identical to the syntax you see in JavaScript itself.</p>

<p>Let's take a look at a JSON object first. A JSON object is identical to a JavaScript object (without functions). It’s a collection of variables with a name and a value (or "name/value pair").  Each name is encoded as a string enclosed in quotes, this is just about the only difference.  For example, following is JSON data describing a person:</p>

{% highlight javascript %}
{
  "name":"Olympia",
  "age":3,
  "height":96.5,
  "state":"giggling"
}
{% endhighlight %}

<p>This is how it might look if you typed it into your code directly (the quotes are no longer necessary.)</p>

{% highlight javascript %}
var person = {
  name: "Olympia",
  age: 3,
  height: 96.5,
  state: "giggling"
}
{% endhighlight %}

<p>An object can contain, as part of itself, another object.  Below, the value of “brother” is an object containing two name/value pairs.</p>

{% highlight javascript %}
{
  "name":"Olympia",
  "age":3,
  "height":96.5,
  "state":"giggling",
  "brother":{
    "name":"Elias",
    "age":6
  }
}
{% endhighlight %}


<p>To compare to data format like XML, the preceding JSON data would look like the following (for simplicity I'm avoiding the use of XML attributes).</p>

{% highlight xml %}
<xml version="1.0" encoding="UTF-8"?>
<person>
  <name>Olympia</name>
  <age>3</age>
  <height>96.5</height>
  <state>giggling</state>
  <brother>
    <name>Elias</name>
    <age>6</age>
  </brother>
</person>
{% endhighlight %}

<p>Multiple JSON objects can appear in the data as an array. A JSON array is simply a list of values (primitives or objects). The syntax is identical to JavaScript syntax. Here is a simple JSON array of integers:</p>

{% highlight javascript %}
[1, 7, 8, 9, 10, 13, 15]
{% endhighlight %}

You might find an array as part of an object. Below the value of “favorite colors” is an array of strings.

{% highlight javascript %}
{
  "name":"Olympia",
  "favorite colors":["purple","blue","pink"]
}
{% endhighlight %}

A great place to find a selection of JSON data sources to play with is <a href="https://github.com/dariusk/corpora">corpora</a>, a github repository maintained by <a href="http://tinysubversions.com/">Darius Kazemi</a>.  For example, here's <a href="https://github.com/dariusk/corpora/blob/master/data/animals/birds_antarctica.json">a JSON file containing information about birds in Antarctica</a>.

### Loading JSON into your code

<iframe width="525" height="300" src="https://www.youtube.com/embed/6mT3r8Qn1VY?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>


<p>Now that I've covered the syntax of JSON, I can look at using the data in JavaScript and p5.js.  The first step is simply loading the data <code>loadJSON()</code>. <code>loadJSON()</code> can be called in <code>preload</code> or used with a callback.  I'm using callbacks in just about all my examples so let's follow that syntax here.</p>

{% highlight javascript %}
function setup() {
  loadJSON('birds_antarctica.json', gotData);
}

function gotData(data) {
  // The JSON is now in data!
  console.log(data);
}
{% endhighlight %}

The data from the JSON file is passed into the argument <code>data</code> in the <code>gotData</code> callback.  Then it becomes a bit of detective work.  How is the data structured — a single object? an array of objects?  An object full of arrays of objects?  Let's look at a snippet from the birds of Antarctica.

{% highlight javascript %}
{
  "description": "Birds of Antarctica, grouped by family",
  "source": "https://en.wikipedia.org/wiki/List_of_birds_of_Antarctica",
  "birds": [
    {
      "family": "Albatrosses",
      "members": [
        "Wandering albatross",
        "Sooty albatross",
        "Light-mantled albatross"
      ]
    },
    {
      "family": "Cormorants",
      "members": [
        "Antarctic shag",
        "Imperial shag",
        "Crozet shag"
      ]
    }
  ]
}
{% endhighlight %}

If the JSON file is loaded into the variable <code>data</code>, the way you access that data is no different than if you had said:

{% highlight javascript %}
var data = {
  "description": "Birds of Antarctica, grouped by family",
  "source": "https://en.wikipedia.org/wiki/List_of_birds_of_Antarctica"
  // etc
}
{% endhighlight %}

For example, if you wanted to display the description and link it to the source you would say:

{% highlight javascript %}
createA(data.source, data.description);
{% endhighlight %}

And since <code>birds</code> is an array of objects, you can use a <code>for</code> loop just the way you always do with arrays.  Each element of the array is an object itself with properties that can be accessed like <code>family</code> and <code>members</code> (which is also an array!).

{% highlight javascript %}
for (var i = 0; i < data.birds.length; i++) {
  var family  = data.birds[i].family;
  createElement('h2', family);
  var members = data.birds[i].members;
  for (var j = 0; j < members.length; j++) {
    createDiv(members(i));
  }
}
{% endhighlight %}

Here's what this looks like:

<div style = "padding:24px;background-color:#f8f8f8;margin-bottom:24px">
<p><a href="https://en.wikipedia.org/wiki/List_of_birds_of_Antarctica">Birds of Antarctica, grouped by family</a></p>
<h2>Albatrosses</h2>
<div>Wandering albatross</div>
<div>Sooty albatross</div>
<div>Light-mantled albatross</div>
<p></p>
<h2>Cormorants</h2>
<div>Antarctic shag</div>
<div>Imperial shag</div>
<div>Crozet shag</div>
</div>


### APIs

<iframe width="525" height="300" src="https://www.youtube.com/embed/ecT42O6I_WI?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

<p>What makes something an API versus just some data you found, and what are some pitfalls you might run into when using an API?</p>

<p>An API (Application Programming Interface) is an interface through which one application can access the services of another. These can come in many forms. <a href="http://openweathermap.org/">Openweathermap.org</a> is an API that offers its data in JSON, XML, and HTML formats. The key element that makes this service an API is exactly that offer; openweathermap.org's sole purpose in life is to offer you its data. And not just offer it, but allow you to query it for specific data in a specific format. Let's look at a short list of sample queries.</p>

<ol>
  <li><a href="http://api.openweathermap.org/data/2.5/weather?lat=35&amp;lon=139">http://api.openweathermap.org/data/2.5/weather?lat=35&amp;lon=139</a><em><br/>(A request for current weather data for a specific latitude and longitude)</em></li>
  <li><a href="http://api.openweathermap.org/data/2.5/forecast/daily?q=London&amp;mode=xml&amp;units=metric&amp;cnt=7&amp;lang=zh_cn">http://api.openweathermap.org/data/2.5/forecast/daily?q=London&amp;mode=xml&amp;units=metric&amp;cnt=7&amp;lang=zh_cn</a><br/><em>(A request for a seven day London forecast in XML format with metric units and in Chinese.)</em></li>
  <li><a href="http://api.openweathermap.org/data/2.5/history/station?id=5091&amp;type=day">http://api.openweathermap.org/data/2.5/history/station?id=5091&amp;type=day</a><br/><em>(A request for a historical data for a given weather station.)</em></li>
</ol>

<p>One thing to note about openweathermap.org is that it does not require that you tell the API any information about yourself. You simply send a request to a URL and get the data back. Other APIs, however, require you to sign up and obtain an access token. <em>The New York Times</em> API is one such example. Before you can make a request, you'll need to visit <a href="http://developer.nytimes.com/"><em>The New York Times</em> Developer site</a> and request an API key. Once you have that key, you can store it in your code as a string.</p>

{% highlight javascript %}
// This is not a real key
var apiKey = "40e2es0b3ca44563f9c62aeded4431dc:12:51913116";
{% endhighlight %}

<p>You also need to know what the URL is for the API itself. This information is documented for you on the developer site, but here it is for simplicity:</p>

{% highlight javascript %}
var url = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
{% endhighlight %}

<p>Finally, you have to tell the API what it is you are looking for. This is done with a “query string,” a sequence of name value pairs describing the parameters of the query joined with an ampersand. This functions similarly to how you pass arguments to a function. If you wanted to search for the term "JavaScript" from a <code>search()</code> function you might say:</p>

{% highlight javascript %}
search("JavaScript");
{% endhighlight %}

<p>Here, the API acts as the function call, and you send it the arguments via the query string. Here is a simple example asking for a list of the oldest articles that contain the term "JavaScript" (the oldest of which turns out to be May 12th, 1852).</p>

{% highlight javascript %}
// The name/value pairs that configure the API query are: (q,JavaScript) and (sort,oldest)
var query = "?q=JavaScript&sort=oldest";
{% endhighlight %}

<p>This isn't just guesswork. Figuring out how to put together a query string requires reading through the API's documentation. For <em>The New York Times</em>, it’s all outlined on <a href="http://developer.nytimes.com/docs/read/article_search_api_v2">the <em>Times'</em> developer website</a>. Once you have your query you can join all the pieces together and pass it to <code>loadJSON()</code>. Here is a tiny example that simply displays the most recent headline.</p>

{% highlight javascript %}
function setup() {

  var apiKey = "sample-key";
  var url = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
  var query = "?q=JavaScript&sort=newest";

  // Here, I format the call to the API by joing the URL with the API key with the query string.
  loadJSON(url+query+"&api-key="+apiKey, gotData);

  function gotData(data) {
    // Grabbing a single headline from the results.
    var headline = data.response.docs[0].headline.main;
    createP(headline);
  }
}
{% endhighlight %}

<p>Some APIs require a deeper level of authentication beyond an API access key. Twitter, for example, uses an authentication protocol known as “OAuth” to provide access to its data. Writing an OAuth application requires more than just passing a string into a request.  There are some examples this week that use server-side programming in Node to perform the authentication.</p>

<iframe width="312" height="175" src="https://www.youtube.com/embed/4UoUqnjUC2c?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>
<iframe width="312" height="175" src="https://www.youtube.com/embed/UNtqhnhD-wo?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

### Encoding URLs

Certain characters and invalid in URLs.  For example, let's say you were querying wordnik for the words "bath towel".  You would have to say <code>bath%20towel</code>.  You could do this yourself with a regex or use URI encoding with <code>encodeURI()</code>.  [Here is more documentation](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/encodeURI) and an example below.

{% highlight javascript %}
var query = 'http://api.wordnik.com/v4/word.json/bath towel/api_key=apikeyblahblahblah';
// Encode the query before you ask for it
var encoded = encodeURI(query);
loadJSON(encoded, callback);
{% endhighlight %}

<code>encodeURI</code> does not encode the following characters: <code>, / ? : @ & = + $ #</code>. This is as it should be since these are used in URLs to mean certain things.  However, if you wanted to have a $ or / as part of some text you are passing into a key/value pair you *would* want to encode these characters.  For this <code>encodeURIcomponent()</code> can be used.

### Wordnik API

<iframe width="525" height="300" src="https://www.youtube.com/embed/YsgdUaOrFnQ?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>


### NYTimes API

<iframe width="525" height="300" src="https://www.youtube.com/embed/IMne3LY4bks?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>

### Wikipedia API

Coming soon.

### Google sheets

<iframe width="525" height="300" src="https://www.youtube.com/embed/ziBO-U2_t3k?list=PLRqwX-V7Uu6a-SQiI4RtIwuOrLJGnel0r" frameborder="0" allowfullscreen></iframe>
