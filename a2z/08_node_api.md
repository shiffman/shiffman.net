---
title: Building an API with Node and Express
layout: a2z-post
permalink: /a2z/node-api/
---

## Building an API with Node and Express

<iframe width="560" height="315" src="https://www.youtube.com/embed/P-Upi9TMrBk?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe>


### Examples
* [basic routes](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week4-node/04_routes_rest_express)
* [concordance API example](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-node-api/01_concordance_API_express) -- demonstrates how a server can pre-process a large dataset in advance and hand results to clients.
* [Spellcheck API](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-node-api/02_spellcheck_API) -- demonstrates how to pass results from node package ([NodeNatural](https://github.com/NaturalNode/natural)) to client, also processes large dataset.
* [Sentiment API](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-node-api/03_sentiment_API) -- demonstrates how to pass results from node package and also save data for persistence to a JSON file standing in as a basic database.
* [Classification API](https://github.com/shiffman/A2Z-F16/tree/gh-pages/week9-node-api/04_classification_API) -- An API that can be trained to classify text into any number of categories.  Picks up the thread of week 5's word counting examples.

### Node packages
* [Natural](https://github.com/NaturalNode/natural) -- includes modules for string operations (tokenizing, differencing), classification, and other misc tools like spell checking.
* [Sentiment](https://github.com/thisandagain/sentiment) -- AFINN-based sentiment analysis with an easy means of adding/updating your own words and positive/negative scores.
* [RiTa](https://github.com/dhowe/RiTaJS) -- same RiTa library we looked at before but also available in node.
* [nlp-compromise](https://github.com/spencermountain/nlp_compromise) -- client and server-side misc natual language processing tools.

### Other relevant resources
* [AFINN-111](http://www2.imm.dtu.dk/pubdb/views/publication_details.php?id=6010) -- AFINN is a list of English words rated for valence with an integer between minus five (negative) and plus five (positive). The words have been manually labeled by Finn Årup Nielsen in 2009-2011.
* [How to write a spelling checker](http://norvig.com/spell-correct.html) by [Peter Norvig](http://norvig.com/)
* [Text file containing 355k English words](https://github.com/dwyl/english-words)

<iframe width="250" height="140" src="https://www.youtube.com/embed/6oiabY1xpBo?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe> <iframe width="250" height="140" src="https://www.youtube.com/embed/e4qKBkwwkNg?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe> <iframe width="250" height="140" src="https://www.youtube.com/embed/oMhAd864bBc?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe>

### Why use server-side programming?

In [session four's notes](/a2z/server-node/) on node and twitter bots I covered the basics of working with [node](https://nodejs.org/en/), [npm](https://www.npmjs.com/), and building an API with "RESTian" routes.

This page picks up on that thread and looks at a few scenarios where running server side code to work with text has advantages over running everything on the client.

One of the main reasons you might go down this road is if you have a large dataset that takes a significant amount of time to process.  For example, let's say you want to build a word counting app and you have one million documents to process.  This would be unrealistic to do on the client-side, but reasonable on the server.  

#### module.exports

To build this example the first thing I'll do is go and grab [`concordance.js`](https://github.com/shiffman/A2Z-F16/blob/gh-pages/week5-analysis/01_concordance/concordance.js) from the [text analysis examples](/a2z/text-analysis).  Functions and objects from separate JS files can be used in node just like in an HTML page.  However, this must be done via [node modules](https://nodejs.org/api/modules.html) and the `require()` function.

For example, if you have the following constructor function in a file called `concordance.js`:

{% highlight javascript %}
function Concordance() {
  this.dict = {};
  this.keys = [];
}
{% endhighlight %}

You can have access to this constructor function in your main app (`server.js`) with two additional steps.  First, you must add `Concordance` to `module.exports` in `concordance.js`.  `module.exports` is [the object that's actually returned as when you call require](http://stackoverflow.com/questions/5311334/what-is-the-purpose-of-node-js-module-exports-and-how-do-you-use-it).

{% highlight javascript %}
module.exports = Concordance;
{% endhighlight %}

Once you've done this, you can now get access to `Concordance` with a call to `require()` like so:

{% highlight javascript %}
// Require it
var Concordance = require('./concordance');
// Now make an object with it
var wordcounts = new Concordance();
{% endhighlight %}

#### Reading files

<iframe width="560" height="315" src="https://www.youtube.com/embed/6iZiqQZBQJY?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe>

Now that I have a `Concordance` object I can start filling it with data on the server.  Let's say I had a sequence of numbered files sitting on the server that I want to process.  I can read those files and pass the contents to the concordance with node's [file system module](https://nodejs.org/api/fs.html) (aka `fs`).  The `fs` module has functions for grabbing a list of files in a directory as well as reading specific files.

{% highlight javascript %}
var fs = require('fs');

// What are all the files in the directory "data"?
var files = fs.readdirSync('data');
// Read all the files one by one
for (var i = 0; i  < files.length; i++) {
  var txt = fs.readFileSync('data/'+files[i], 'utf8');
  wordcounts.process(txt);
}
{% endhighlight %}

One thing you might notice about the above is the use of `readdirSync()` and `readFileSync()` as opposed to `readdir()` and `readFile()`.   The "sync" refers to "synchronous" meaning these lines of code are "blocking".  The data has to be read before moving onto the next line.  This is unusual in JavaScript in that typically a callback is required to be executed when the data is read.  This is a case where I am happy for the program to stop and wait because I want to process all of the data before the server starts listening for connections. It's ok if it takes a long time because this only happens once when the server starts.  (This, however, would not be advisable at other points in the code like handling a client request to the server.)

#### Routes for results

Now that the data is read, I can create routes that send the data to a client making a `loadJSON()` request with p5 (or pick your function using any JS library that can make HTTP requests.)  Here's one that sends everything in the concordance object.

{% highlight javascript %}
// Route for sending all the concordance data
app.get('/all', showAll);

function showAll(req, res) {
  // Send the entire concordance
  res.send(wordcounts);
}
{% endhighlight %}

I can also get fancier and make up my own protocol for sending back pieces of data from the concordance.  For example, here is some code that returns the count for a specific word or "word not found" if it is not present in the concordance.  The point is to send back a JavaScript object — it's up to you to put in the object what you think makes the most sense.

{% highlight javascript %}
// Now a route for data about one word
app.get('/search/:word', showOne);

function showOne(req, res) {
  var word = req.params['word'];

  var reply = { };
  var count = wordcounts.getCount(word);

  // If it's not part of concordance send back a message
  if (count === undefined) {
    reply.status = 'word not found';
  // Otherwise send back the word and count
  } else {
    reply.status = 'success';
    reply.count = count;
  }
  // It's useful to send back the word to
  // so the client can match the count with a given API call
  reply.word = word;

  res.send(reply);
}
{% endhighlight %}

#### What does the client say?

<iframe width="560" height="315" src="https://www.youtube.com/embed/4zr8j-jeU_M?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe>

The client can then access this data with `loadJSON()`.

{% highlight javascript %}
  // Getting all the data
  loadJSON('/all', gotAll);

  // Getting data for one word -- 'apple'
  loadJSON('/search/apple', gotOne);

  function gotAll(data) {
    // deal with all the data
  }

  function gotOne(data) {
    // deal with the results of the search for a single word
  }
{% endhighlight %}

### CORS

One thing you might notice about the above `loadJSON()` calls is that they do not reference the domain itself, simply the route "all" or "search".  This is because I am assuming that the p5 sketch will be hosted by the same node app that is running the API code.  In fact, in my example I'm doing exactly this by placing the p5 sketch in a "public" folder and serving those files statically using node and express:

{% highlight javascript %}
app.use(express.static('public'));
{% endhighlight %}

However, let's say you want others to be able to access your API from their code.  In order for this to be possible you must enable something called [CORS (Cross-origin resource sharing)](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).  This prevents others from getting that nasty error: *XMLHttpRequest cannot load. No 'Access-Control-Allow-Origin' header is present on the requested resource.*  ([More about Cross Domain Requests in JS](https://jvaneyck.wordpress.com/2014/01/07/cross-domain-requests-in-javascript/).)

This is easy enough to do with the [Node CORS package](https://github.com/expressjs/cors).

{% highlight text %}
$ npm install cors --save
{% endhighlight %}

{% highlight javascript %}
var cors = require('cors');
app.use(cors());
{% endhighlight %}

### Persistance

Another topic relevant to server-side programming is "persistance".  In other words, let's say you want to build a text classifier.  [NaturalNode](https://github.com/NaturalNode/natural) includes as one of its features [Bayesian Text Classification](https://web.stanford.edu/class/cs124/lec/naivebayes.pdf) which I briefly covered in [text analysis](/a2z/text-analysis).  

Let's assume your application classifies text as "happy" or "sad".  The system is "trained" by users submitting text tagged with the appropriate category (happy or sad).  The server passes all this text to a `Classifier` object which stores all the relevant counts and probabilities for the submitted text.  After running your application for a week, you have hundreds of submissions.  What would happen if you have to close and restart the server?

If all of the data is just stored in memory in the `Classifier` object, it will all be lost as soon as the server quits.  A solution is to save the data somewhere permanent that persists even when the server stops running.   One option is to use a database (like [mongodb](https://docs.mongodb.org/ecosystem/drivers/node-js/) or the simpler [nedb](https://github.com/louischatriot/nedb)) but for some basic scenarios these solutions can be overkill.

One option is to just write out a text file filled with JSON.  The `fs` module can handle the reading and writing and the [JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON) object has functions `parse()` and `stringify()` to convert back and forth from JS object to raw text.

Here is the skeleton of code used in the examples here.  Step 1 for the server is to check and see if the file exists.  (The code below is from a sentiment analysis scenario where words and their positive/negative score are stored in a file called `additional.json`.)

{% highlight javascript %}
var exists = fs.existsSync('additional.json');
{% endhighlight %}

If it does in fact exist, the data can be read using `fs` and stored in a variable using `JSON.parse()`.

{% highlight javascript %}
var additional;
if (exists) {
  var txt = fs.readFileSync('additional.json', 'utf8');
  additional = JSON.parse(txt);
}
{% endhighlight %}

In the case of it not existing, the code simply makes an empty JavaScript object.

{% highlight javascript %}
} else {
  additional = {};
}
{% endhighlight %}

The example includes an API call to add a word and score to the sentiment analysis.  The server can then just write out a new JSON file using `JSON.stringify()` on the variable `additional`.  Here, the writing of a file must be done asynchronously since the action is associated with a web request (rather than just when the server starts up) and you don't want the server to get stuck on an operation while other clients could be trying to connect.

{% highlight javascript %}
// Assuming some new word and score is added
additional['new word'] = 5;

// Turn additional back into text
var json = JSON.stringify(additional);

// Write out the file
fs.writeFile('additional.json', json, 'utf8', finished);

// Callback for when file is finished
function finished(err) {
  console.log('Finished writing additional.json');
}
{% endhighlight %}

Writing out a text file is a nice quick and dirty solution that can work for many small-scale creative projects in a prototyping stage.  Often, however, a more involved database is required. In addition to creating a database on your own server, there is also the option of using an API service like [Firebase](https://firebase.google.com) to store your data.  I have [notes and examples on firebase here](/a2z/firebase/).

### GET vs POST

<iframe width="560" height="315" src="https://www.youtube.com/embed/GZ2nwxhQUTU?list=PLRqwX-V7Uu6Yyn-fBtGHfN0_xCtBwUkBp" frameborder="0" allowfullscreen></iframe>

The [HTTP (Hypertext Transfer Protocol)](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol) includes several different kinds of requests.  The one you are likely most commonly familiar with is a `GET` request.  This is the request that happens when you type a URL into an address bar.  You are asking the server if you can "get" something, and what is sent back is some sort of data, often in the form of HTML, but in can be anything.

In fact, you are also making GET requests in code all the time.  If you pass a url into the `loadJSON()` function, a GET request is made.

{% highlight javascript %}
loadJSON('http://api.com/some/api/call', gotData);

function gotData(data) {
  // the response is in data
}
{% endhighlight %}

In this week's examples, I am handling GET requests in node as well, by using the `get()` function on the `app` object, i.e.

{% highlight javascript %}
loadJSON('http://api.com/some/api/call', gotData);

function gotData(data) {
  // the response is in data
}
{% endhighlight %}

There are some scenarios, however, where a POST request is preferable to a GET.  POST requests are designed for instances where the data sent would be stored on the server (or affect some sort of change of state by the server like deleting a database record.)  They are also useful in the context of sending sensitive data like passwords since the data of a POST is not visible via the URL address.

In the case of creative ITP projects we can be a little loosey goosey about these distinctions.  I'm using a post here because my examples might send a large paragraph (or even more) of text (GET requests have a data length limit).

To send a POST from p5 the `httpPost()` method is available.  Simply pass a JavaScript object with the data for the post to the appropriate url.  You can then also define success and error callbacks to track the request.

{% highlight javascript %}
// Data to be posted
var params = {
  name: "daniel",
  password: "rainb0w"
}

// Making the post
httpPost('/path/to/post', params, finished);

// Handling the result
function finished(reply) {
  console.log(reply);
}
{% endhighlight %}

On the server side, receiving the data looks almost identical to a `GET`.

{% highlight javascript %}
// "post" instead of "get"
app.post('/path/to/post', gotData);

function gotData(req, res) {
  // Look at the "body" of a POST to get
  // data, not the "params" like with a GET
  var name = req.body.name;
  var password = req.body.password;
  var reply = {
    message: "thank you"
  }
  res.send(reply);
}
{% endhighlight %}

In the above code, you'll notice that the data is pulled from `req.body` rather than `req.params` as with a `GET` request.  This only works because earlier in my code I am including the express [body-parser](https://github.com/expressjs/body-parser) package.  

{% highlight javascript %}
var bodyParser = require('body-parser');
app.use(bodyParser.json()); // support json encoded bodies
{% endhighlight %}

This package handles the parsing of a `POST` body and gives you easy access in `request.body`.
