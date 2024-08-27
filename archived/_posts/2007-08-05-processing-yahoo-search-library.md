---
title: Processing Yahoo Search Library
author: Daniel
layout: post
dsq_thread_id:
  - 249553981
pvc_views:
  - 4245
categories:
  - API
  - blog
  - book
  - java
  - library
  - p5
  - processing.org
  - yahoo
---
<p><strong>2011 Update: While this library may still work, I am deprecating it as Yahoo does not support the Java Search API any longer as far as I can tell</strong></p>
<p><img src="http://shiffman.net/p5/pyahoo_files/names.jpg"/></p>
<p>Now, I am rather overdue for an update on <a href="http://book.shiffman.net">my upcoming book</a>.  I&#8217;ll be posting details soon.   However, in the course of finishing up a chapter on String parsing, I discovered that my good friend, the <a href="http://code.google.com/apis/soapsearch/">Google SOAP API</a> is no longer being supported (obviously, I&#8217;m a little late on the ball here.) </p>
<p>So, I quickly whipped up a <a href="http://www.processing.org">Processing</a> library to make use of the Yahoo Search API.  Now, you can access the Yahoo! API directly in Processing.  There&#8217;s <a href="http://processing.org/learning/libraries/yahoosearch.html">even an example here</a>.  However, you would have to write your own thread if you wanted to search asynchronously.  In addition, if you&#8217;re not comfortable diving into outside Java APIs, you might struggle to figure out the syntax.   (<a href="http://www.realtimeart.com/switchboard/">Switchboard</a> also provides an interface to the Yahoo! API.)</p>
<p>So I set out (as an example for my book) to make a quick and easy bridge to the Yahoo API.    </p>
<li class="arrow"><a href="http://shiffman.net/p5/pyahoo_files/pyahoo.zip">Download the library here</a></li>
<li class="arrow"><a href="http://developer.yahoo.com">Go and get a developer ID</a></li>
<li class="arrow"><a href="http://developer.yahoo.com/download">Download the Yahoo! Search SDK</a>  Find the file: yahoo_search-2.X.X.jar and put it in the library folder (along with the above download).</li>
<p>Finally, take a peek at this example code. </p>

{% highlight java %}
// Import the library
import pyahoo.*;

YahooSearch yahoo;

void setup() {
  size(400,400);
  // Make a search object
  yahoo = new YahooSearch(this,"YOUR API KEY HERE");

}

void mousePressed() {
  yahoo.search("processing.org");
  // You can request more results like so (the default is 10):
  // yahoo.search("processing.org",30);
}

void draw() {
  noLoop();
}

// The searches will come in one at a time to here when finished
void searchEvent(YahooSearch yahoo) {
  // You can get the titles, URLs, or Summaries back as an array of Strings
  String[] titles = yahoo.getTitles();
  String[] urls = yahoo.getUrls();
  println("nI searched for " + yahoo.getSearchString());
  println("There are a total of " + yahoo.getTotalResultsAvailable() + " results available");
  println("Here are the first " + yahoo.getNumberRequested());
  for (int i = 0; i < titles.length; i++) {
    println("___________");
    println("Item # " + i);
    println(titles[i]);
    println(urls[i]);
  }
  
  // You can also access the Yahoo API Directly by asking for the WebSearchResult object:
  // WebSearchResults results = yahoo.getResults();
  // WebSearchResult[] results = yahoo.getResultsArray();
  // In this mode, make sure to import the Yahoo library up topl
  // import com.yahoo.search.WebSearchResults;
  // See Yahoo API documentation for more
}
{% endhighlight %}
<p>There's also a fancier example (mostly uncommented, sorry) that produced the image at the top of this post <a href="http://shiffman.net/p5/pyahoo_files/names.zip">here</a>.  </p>
<p>Thoughts?  Helpful?  Useful?  </p>
