---
title: Node.js Twitter Bots
layout: a2z-post
permalink: /a2z/twitter-bots/
---

## Twitter API and Twitter Bots

### Examples (twitter bots)
* [Twitter API with p5 sketch](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/01_twitter_api_oauth)
* [Basic Bot](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/02_basic_bot)
* [Liking and Retweeting Bot](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/03_liking_retweeter_bot)
* [Twitter Bot Queue](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/04_retweet_queue)
* [Bot Example with Tracery](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/05_tracery_bot)
* [Replier Bot](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/06_replier_bot)
* [Bot making API request](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/07_api_requests)
* [Image Bot](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/08_twitter_image_bot)
* [Image Processing Bot](https://github.com/shiffman/A2Z-F17/tree/master/week4-twitter/09_twitter_replier_image)

### Bot lists
* [Bot twitter list](https://twitter.com/shiffman/lists/bots)
* [More comprehensive twitter bot list](https://twitter.com/ckolderup/lists/the-fall-of-humanity/members)
* [Bot wiki](https://botwiki.org/bot/?networks=twitter-bots)
* [Another bot wiki](https://github.com/shiffman/A2Z-F15/wiki/Twiter-Bots)

### Read
* [Strategies on Bot Poetics](https://harrygiles.org/2016/04/06/some-strategies-of-bot-poetics/) by Harry Giles
* [New York Magazine article on bots](http://nymag.com/following/2015/11/12-weirdest-funniest-smartest-twitter-bots.html)
* [Rules for Automation and Best Practices](https://support.twitter.com/articles/76915) from Twitter, [Bot Etiquette](http://tinysubversions.com/2013/03/basic-twitter-bot-etiquette/) by Darius Kazemi

### Watch
* [Darius Kazemi Eyeo talk](https://vimeo.com/112289364)

### Bot resources / tutorials
* [A wiki with links to more bot resources](https://github.com/shiffman/A2Z-F16/wiki/Twitter-Bot-References)
* [Allison Parrish spreadsheet bot tutorial](http://air.decontextualize.com/twitterbot/)
* [Allison Parrish bot workshop notes](https://gist.github.com/aparrish/3ee64d07f0a00b08618a)
* [Allison Parrish node bot example](https://github.com/aparrish/example-twitter-bot-node)
* Twitter Bot node tutorial: [part 1](http://ursooperduper.github.io/2014/10/27/twitter-bot-with-node-js-part-1.html), [part 2](http://ursooperduper.github.io/2014/10/28/twitter-bot-with-node-js-part-2.html), [part 3](http://ursooperduper.github.io/2014/11/03/twitter-bot-with-node-js-part-3.html)
* [Twit Node API](https://github.com/ttezel/twit)
* [Another Node bot tutorial](http://kiafathi.azurewebsites.net/project-making-a-twitter-bot-with-node-js/)
* [Making a Twitter bot with node.js and Cloud9](https://botwiki.org/tutorials/making-what_capital/)

### The Twitter API

<iframe width="525" height="300" src="https://www.youtube.com/embed/GQC2lJIAyzM?list=PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV" frameborder="0" allowfullscreen></iframe>

<iframe width="525" height="300" src="https://www.youtube.com/embed/7-nX3YOC4OA?list=PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV" frameborder="0" allowfullscreen></iframe>

Some APIs require a level of authentication that can't be done from client-side JavaScript.  A prime example of this is the [Twitter API](https://dev.twitter.com/overview/documentation) which requires something known as [OAuth](https://dev.twitter.com/oauth/overview/faq). For basic API querying, you don't need to go to deep into the inner workings of authenticating.  With node, you can pick one of many node packages that help you connet and query the API. The one I'm using for these examples is called [Twit](https://github.com/ttezel/twit).

Before you can use Twit, you need to follow a few steps.

1. Create a twitter account. You might already have one, but depending on what you are doing, you may want to create a new account to associate with your app.  For example, I am using [this account](https://twitter.com/a2zitp) for all of these examples.

2. Create an "APP".  This can be done by on the [manage your apps](https://apps.twitter.com/) developer page.  One tricky thing here is that to do this you are required to associate a phone number with the account in order to use the API.  You can temporarily enter your own number or else sign up for a free one with [Twilio](https://www.twilio.com/) or [Google Voice](https://www.google.com/voice#inbox).

To create the app, you'll need to enter an app name, description, and associated website (can be anything for now).  You can leave the "callback" field blank.  

3. Once the app is created, navigate to "Keys and Access Tokens".  There you'll find your "Consumer Key (API Key)" and "Consumer Secret (API Secret)".

![tokens1](/a2z/images/tokens1.png)

Also, scroll down and click the "Generate Access Token" button.  Now you also have an "Access Token" and "Access Token Secret".

![tokens2](/a2z/images/tokens2.png)

Don't worry, if you ever post these keys somewhere by accident (like in an image file on a tutorial) you can always regenerate new ones.

That's it, you're done and ready to use Twit!

### Authenticating with Twit

To make any calls to the API you first need to install Twit and then include the package with `require()`.

{% highlight text %}
$ npm install Twit --save
{% endhighlight %}

{% highlight javascript %}
var Twit = require('twit');
{% endhighlight %}

And then you'll need to authenticate with all those secret keys.  There are a variety of ways to do this.  The easiest is just typing your keys into your code:

{% highlight javascript %}
var T = new Twit({
  consumer_key:         'YOURCONSUMERKEY',
  consumer_secret:      'YOURCONSUMERSECRET',
  access_token:         'YOURACCESSTOKEN',
  access_token_secret:  'YOURACCESSTOKENSECRET'
});
{% endhighlight %}

This may cause you some problems down the road, however, if you want to publish your code.  One way you can get around this is by putting your keys in a separate file called, say, `config.js`.

{% highlight javascript %}
module.exports = {
  consumer_key:         'YOURCONSUMERKEY',
  consumer_secret:      'YOURCONSUMERSECRET',
  access_token:         'YOURACCESSTOKEN',
  access_token_secret:  'YOURACCESSTOKENSECRET'
}
{% endhighlight %}

Now you can access this also with `require()`.

{% highlight javascript %}
var config = require('./config.js');
var T = new Twit(config);
{% endhighlight %}

This way if you want to share or publish your code, you can do so leaving out the `config.js` file.  This is what I've done in [this repo here](https://github.com/shiffman/A2Z-F17/tree/master/week9/07_twitter_api_oauth), though I've included a `config-empty.js` file with some comments on how to enter the tokens.

### Querying the Twitter API

[Full documentation of the various Twit methods is available on github](https://github.com/ttezel/twit), but I'll highlight what you need here for a basic p5.js sketch or bot.

There are three main calls you can make with Twit: `get()`, `post()`, and `stream()`.

`get()` is useful for looking at particular tweets.  For example, if you want to just search for tweets that match a given keyword, are from a particular user, etc. you can use `get()`.  The following asks for the 10 most recent tweets with the keyword JavaScript.  The callback for receiving the data is `gotData()`.

{% highlight javascript %}
T.get('search/tweets', { q: 'JavaScript', count: 10 }, gotData);
{% endhighlight %}

All of the [arguments you can pass to the search can be found here](https://dev.twitter.com/rest/reference/get/search/tweets).  In addition to the search text, you can search for tweets from a particular location or from a certain time period, and more.

What you get back is just [a lot of JSON as outlined here](https://dev.twitter.com/rest/reference/get/search/tweets).  So for example, if you look at the text of the tweets you would say:

{% highlight javascript %}
function gotData(err, data) {
  var tweets = data.statuses;
  for (var i = 0; i < tweets.length; i++) {
    console.log(tweets[i].text);
  }
}
{% endhighlight %}

The `post()` function is used to post an actual tweet itself.

{% highlight javascript %}
T.post('statuses/update', { status: 'I am tweeting via the API!' }, tweeted);
{% endhighlight %}

The `tweeted()` callback is where you can check whether the tweet was successfully posted or not.

Included in this week's examples is [an example that expands on the above](https://github.com/shiffman/A2Z-F17/tree/master/week9/07_twitter_api_oauth).  Features include (1) searching the Twitter API and returning the results as JSON for client-side JavaScript to parse and (2) receiving a tweet via a URL query string and passing it along via the API to be posted.

### Writing a bot

<iframe width="525" height="300" src="https://www.youtube.com/embed/ZvsqQjwrISQ?list=PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV" frameborder="0" allowfullscreen></iframe>

There's not much left to say about writing a bot. All of the pieces you need are in the above sections.  And none of the additional complexity of a web server via express is needed.  The bot is simply just a process that executes every so often and tweets something based on some sort of algorithm or data source.  

The easiest way to "schedule" a bot is to use JavaScript's `setInterval()` function.  For example, take the following:

{% highlight javascript %}
// Set up Twit
var Twit = require('twit');
var config = require('./config.js');
var T = new Twit(config);

// Once every hour
setInterval(tweeter, 60*60*1000);
{% endhighlight %}

With the above code, the function `tweeter()` is triggered once per hour (an hour is 60 minutes, a minute is 60 seconds, a second is 1,000 milliseconds).  All that is left to do is have `tweeter()` tweet something.  For example:

{% highlight javascript %}
// A function that tweets a random number!
function tweeter() {
  var num = Math.floor(Math.random()*100);
  var tweet = 'Here\'s a random number between 0 and 100: ' + num;

  T.post('statuses/update', { status: tweet }, tweeted);
}
{% endhighlight %}

That's it!  Your first twitter bot.  Of course you might want to do something more involved, like generate from a markov chain, or mix up text from some other API, etc.  But for any of these ideas the above structure will work.

[Here's an example of a twitter bot using a Context-Free Grammar](https://github.com/shiffman/A2Z-F15/tree/gh-pages/week9/09_twitter_bot_cfg) to generate tweets.

### Using the streaming API

The above bot scenario involved tweeting every N milliseconds.  This is what you think of when you think of some sort of autonomous robot tweeting.  It operates on its own, never gets tired, and tweets at a precise time.  But it is also possible to create a bot that participates in the social activity of twitter itself.  This can be accomplished using `stream()`.   [The Twitter streaming API](https://dev.twitter.com/streaming/overview) allows you to execute callbacks when certain events occur -- like when someone follows you.  

There are different kinds of streams -- a "public" stream, a "user" stream, and a "site" stream ([documentation](https://dev.twitter.com/streaming/overview)).  Here, I'll use the "user" stream to demonstrate a "follow event".

{% highlight javascript %}
// A user stream
var stream = T.stream('user');

// When someone follows the user
stream.on('follow', followed);

// In this callback we can see the name and screen name
function followed(event) {
  var name = event.source.name;
  var screenName = event.source.screen_name;
  console.log('I was followed by: ' + name + ' ' + screenName);
}
{% endhighlight %}

Now you could just add a `post()` to tweet back `"Thanks for following me!" + screenName`!

You can also trigger an events whenever the user is mentioned in a tweet.

{% highlight javascript %}
stream.on('tweet', tweetEvent);
{% endhighlight %}

Now this event is triggered both when the user is mentioned and when the user itself tweets.  So if you want to only get @replies you could add a check in the callback like:

{% highlight javascript %}
function tweetEvent(tweet) {
  var reply_to = tweet.in_reply_to_screen_name;
  // Check to see if this was, in fact, a reply to you
  if (reply_to === 'yourusername') {
    // Get the username and content of the tweet
    var name = tweet.user.screen_name;
    var txt = tweet.text;
    // Tweet back a reply?
  }
}
{% endhighlight %}

[Here's an example of a bot](https://github.com/shiffman/A2Z-F17/tree/gh-pages/week9/10_twitter_replier_bot) that replies to all @mentions with the same exact text in reverse.

The same search I used in the `get()` examples can also be accessed as a stream.  For example, to get a continuous stream of all tweets from a certain geolocation:

{% highlight javascript %}
// A geo location
var sanfran = [ '-122.75', '36.8', '-121.75', '37.8' ]
var stream = T.stream('statuses/filter', { locations: sanfran })
stream.on('tweet', tweetEvent);
{% endhighlight %}

<iframe width="525" height="300" src="https://www.youtube.com/embed/mUoIPmZ4KwA?list=PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV" frameborder="0" allowfullscreen></iframe>

<iframe width="525" height="300" src="https://www.youtube.com/embed/ovOtQxLwSzQ?list=PLRqwX-V7Uu6atTSxoRiVnSuOn6JHnq2yV" frameborder="0" allowfullscreen></iframe>
