---
title: Crawling
author: Daniel
layout: page
dsq_thread_id:
  - 249553291
pvc_views:
  - 6735
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#url">URL grabbing</a></li>
<li class="arrow"><a href="#lists">Linked Lists</a></li>
<li class="arrow"><a href="#polite">Being Polite</a></li>
<li class="arrow"><a href="#find">Finding new URLs</a></li>
<li class="arrow"><a href="#crawler">A Crawler Class</a></li>
</div>
<h5>Examples:</h5>
<li class="arrow">simple URL retrieval: <a href="http://www.shiffman.net/itp/classes/a2z/week05/urlgrab/A2ZUrlReader.java">A2ZUrlReader.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week05/urlgrab/UrlGrab.java">UrlGrab.java</a></li>
<li class="arrow">Valentine&#8217;s day crawler: <a href="http://www.shiffman.net/itp/classes/a2z/week05/valentinecrawler.zip">valentinecrawler.zip</a>,  <a href="http://www.shiffman.net/itp/classes/a2z/week05/crawler/Crawler.java">Crawler.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week05/crawler/Crawling.java">Crawling.java</a></li>
<li class="arrow">Example that uses <a href="http://www.cs.cmu.edu/~rcm/websphinx/">WebSphinx</a> (a java open-source crawling library): <a href="http://www.shiffman.net/itp/classes/a2z/week05/sphinx/MyCrawler.java">MyCrawler.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week05/sphinx/WebSphinxTest.java">WebSphinxTest.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week05/sphinx/web_sphinx.zip">web_sphinx.zip</a>.</li>
<h5>Related:</h5>
<li class="arrow"><a href="http://www.robotstxt.org/wc/guidelines.html">Guidelines for Robot Writers</a> &#8212; examples above are terribly impolite!</li>
<li class="arrow"><a href="http://www-db.stanford.edu/~backrub/google.html">The Anatomy of a Large-Scale Hypertextual Web Search Engine</a></li>
<li class="arrow"><a href="http://www.chato.cl/534/article-9678.html">Web Dynamics, Web Search, Web Crawling, Web Characterization, Link Analysis, Search Engines and more</a> (suggested)</li>
<h5>Exercises (optional):</h5>
<li class="arrow">Rewrite the crawler to follow <a href="http://www.robotstxt.org/wc/exclusion.html">robots.txt</a>.  Investigate using <a href="http://www.osjava.org/norbert/HowTo.html">Norbert</a></li>
<li class="arrow">Rewrite the crawler to deal with relative paths, i.e. href=&#8221;relative/index.html&#8221; as well as href=&#8221;http://www.site.com/relative.html&#8221;</li>
<li class="arrow">Rewrite the crawler to place all the words it encounters (ignoring HTML?) into a concordance.</li>
<li class="arrow">Improve the crawler&#8217;s functionality to keep track of how many sites link to any given URL.</li>
<p><a name ="url"></a></p>
<h5>Grabbing a URL</h5>
<p>So far, we&#8217;ve only looked at how to retrieve textual information from a local file.   Of course, there&#8217;s also that wacky world wide web thing and it&#8217;s not terribly difficult for us to expand the functionality of our examples to include scraping information from a URL.   Here, we&#8217;ll need to introduce the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/net/package-summary.html">java.net</a> package, which will allow us to open an <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/InputStream.html">InputStream</a> from a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/net/URL.html">URL</a> object.</p>

{% highlight java %}
String urlpath = "http://www.mycoolwebsite.com";
URL url = new URL(urlpath);
InputStream stream = url.openStream();
{% endhighlight %}

<p>Once we have the InputStream, we can use a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/BufferedReader.html">BufferedReader</a> to read the stream (i.e. the source from the URL) line by line, appending it all to one big <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/StringBuffer.html">StringBuffer</a>.</p>

{% highlight java %}
StringBuffer stuff = new StringBuffer();
//Create a BufferedReader from the InputStream
BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

// Read the stream line by line and append to StringBuffer
String line;
while (( line = reader.readLine()) != null) {
  stuff.append(line + "\n");
}
// Close the reader
reader.close();
{% endhighlight %}

<p>Finally, we can simply convert the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/StringBuffer.html">StringBuffer</a> into a good old fashioned <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html">String</a> and have our way with it.</p>

{% highlight java %}
String urlContent = stuff.toString();
{% endhighlight %}

<p>Source for URL reading class: <a href="http://www.shiffman.net/itp/classes/a2z/week05/urlgrab/A2ZUrlReader.java">A2ZUrlReader.java</a></p>
<p><a name ="lists"></a></p>
<h5>Making a list and keeping it twice</h5>
<p>Sure, visiting one URL and grabbing data is super duper fun.   But there is more joy out there and it comes in the form of crawling the web via a <a href="http://en.wikipedia.org/wiki/Queue">queue</a> of URLs to visit.  To accomplish this, we&#8217;ll dive into the <a href="http://en.wikipedia.org/wiki/Linked_list">linked list</a> data structure.  Sure, we could use an <a href="http://java.sun.com/j2se/1.3/docs/api/java/util/ArrayList.html">ArrayList</a> here to keep track of multiple URLs in order, but a <a href="http://en.wikipedia.org/wiki/Linked_list">linked list</a> will be more efficient because we only need some very basic functionality.  We need to:</p>
<li class="arrow">Add URLs to the end of the list (i.e. queue)</li>
<li class="arrow">Get the first URL from the list</li>
<p>&nbsp;<br />
(Note how we do not need to traverse the list itself.  We only are required to access the first element, as well as place elements on the end.)</p>
<p>It&#8217;s pretty simple to use the java <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/LinkedList.html">LinkedList</a> class.</p>

{% highlight java %}
LinkedList urlsToVisit = new LinkedList();           // A queue of URLs to visit
urlsToVisit.add("http://www.yahoo.com");             // Add a URL (as a String) to the list
urlsToVisit.add("http://www.google.com");
urlsToVisit.add("http://itp.nyu.edu");
String urlpath = (String) urlsToVisit.removeFirst(); // Get the first URL (as a String)
{% endhighlight %}

<p>We could do something fancier, such as remove all the elements from the list one by one (as long as the list isn&#8217;t empty):</p>

{% highlight java %}
while (!urlsToVisit.isEmpty()) {
  String urlpath = (String) urlsToVisit.removeFirst();
  // Presumably do something here!
}
{% endhighlight %}

<p>For our web crawler project, however, one list will not do.  For example, the following code would be somewhat tragic:</p>

{% highlight java %}
urlsToVisit.add("http://www.sadlittleurl.com");
urlsToVisit.add("http://www.sadlittleurl.com");
urlsToVisit.add("http://www.sadlittleurl.com");
urlsToVisit.add("http://www.sadlittleurl.com");
urlsToVisit.add("http://www.sadlittleurl.com");
urlsToVisit.add("http://www.sadlittleurl.com");
urlsToVisit.add("http://www.sadlittleurl.com");
{% endhighlight %}

<p>We shouldn&#8217;t be visiting the same URL over and over again.  This is why we&#8217;ll need a <i>second list</i>, one that keeps track of the URLs previously visited.   Ideally, a data structure that had constant time look-up &#8212; O(1) &#8212; would be great.  All we want to figure out is &#8212; here&#8217;s a URL, are you in the list or not?  Our friendly neighborhood <a href="http://en.wikipedia.org/wiki/Hash_table">hash table</a> from <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/bayesian/#hash">last week</a> will do just fine.</p>

{% highlight java %}
LinkedList urlsToVisit = new LinkedList();   // A queue of URLs to visit
HashMap urlsVisited = new HashMap();      // A hash table of visited URLs
String url = "http://www.abcdefg.com";
urlsVisited.put(url,url);
urlsToVisit.add("url");
{% endhighlight %}

<p>For each URL, we place it in both lists.  (Note that the HashMap requires a key and a value, whereas the LinkedList requires only a value.)  This way, when it comes time to look at a new URL, we can check and see if it&#8217;s been visited first before we waste our time and look at it again.</p>

{% highlight java %}
String url = "http://www.iamtiredofmakingupurls.com";
if (!urlsToVisit.containsKey(url)) {
  urlsVisited.put(url,url);
  urlsToVisit.add("url");
}
{% endhighlight %}

<p>We can now see how we are well on the way to writing a web crawler.  We start with one URL, grab it, look for other URLs, check if we&#8217;ve already visited them, if not add them to the list, and start all over with the next URL!</p>
<p><a name ="polite"></a></p>
<h5>Being Polite</h5>
<p>Ok, so we&#8217;re on our way to writing a web crawler.  Web crawlers, sadly, can be somewhat rude.  They can start poking their noses in places they shouldn&#8217;t.  They can hog a lot of resources.    As humble citizens of the web, we should do our best to follow these <a href="http://www.robotstxt.org/wc/guidelines.html">guidelines</a> as well as the <a href="http://www.robotstxt.org/wc/exclusion.html">the rules for robot exclusion</a>.  The examples included on this page, for the sake of simplicity, do not live up to these standards and may be construed as rather impolite.  If you plan on using them extensively, you will likely want to consider making some significant improvements.</p>
<p>Here is the pseudo-code for our simple crawler:</p>
<li class="arrow">1 &#8212; Set-up LinkedList and HashMap</li>
<li class="arrow">2 &#8212; Add one URL to both</li>
<li class="arrow">3 &#8212; Grab source from first URL in queue</li>
<li class="arrow">4 &#8212; Look for more URLs in source</li>
<li class="arrow">5 &#8212; For every found URL, if it is already in the HashMap, ignore, if not, add it to both the queue and hash</li>
<li class="arrow">6 &#8211; -Repeat steps 3 &#8211; 5</li>
<p>&nbsp;<br />
So far, we&#8217;ve covered the necessary code for every step except #4.</p>
<p><a name ="find"></a></p>
<h5>Finding URLs</h5>
<p><a href="http://www.shiffman.net/teaching/programming-from-a-to-z/regex">Remembering</a> <a href="http://en.wikipedia.org/wiki/Regular_expression">regular expressions</a>, we can create a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/regex/Pattern.html">Pattern</a> object to match any reference to a URL.  </p>
<p>URL references on web pages generally look like this:</p>

{% highlight java %}&lt;a href = "http://www.someurl.com"&gt;{% endhighlight %}

<p>For simplicity&#8217;s sake, we&#8217;ll ignore any references to links specified by a relative path and assume that the URL has to start with &#8220;http&#8221;.  We can begin to build a regular expression. . .</p>

{% highlight java %}
href            # match href
s*             # match optional infinite whitespace
=               # match equal sign
s*             # match optional infinite whitespace
"               # match quote
(http[^"s]+)   # capture the URL itself, http followed by one or more non whitespace/quote
"               # end with a quote
{% endhighlight %}

<p>Sure, this could be improved a number of ways, but it will do as a start.  In Java, it will look like this:</p>

{% highlight java %}
Pattern href;
// Match URLs
// Note using Pattern.COMMENTS flag which ignores white spaces and anything after '#' in a regex
href = Pattern.compile( "href            # match href \n" +
                        "\\s*=\\s*"     # 0 or more spaces, =, 0 ore more spaces, quote n" +
                        "(http[^\"\\s]*) # capture the URL itself, http followed by no spaces and no quotes n" +
                        "\"              # ending with a quote \n",
                        Pattern.CASE_INSENSITIVE | Pattern.COMMENTS);
{% endhighlight %}

<p>Now, as we grab some html source code, we can look for URLs and add them if appropriate:</p>

{% highlight java %}
// Grab the URL content
A2ZUrlReader urlr = new A2ZUrlReader(urlpath);
String stuff = urlr.getContent();

// Match the URL pattern to the content
Matcher m = href.matcher(stuff);
// While there are URLs
while (m.find()) {
  // Grab the captured part of the regex (the URLPath itself)
  String newurl = m.group(1);
  // If it hasn't already been visited
  if (!urlsVisited.containsKey(newurl)) {
    // Add it to both the LinkedList and the HashMap
    urlsToVisit.add(newurl);
    urlsVisited.put(newurl,newurl);
  }
}
{% endhighlight %}

<p>Now, not all URLs are treated equally.  If our goal, for example, is to analyze text on the web, we can certainly ignore references to media files, such as jpgs, movs, etc.  We&#8217;ll use a second regular expression for this:</p>

{% highlight java %}
// We will ignore URLs ending with certain extensions
String ignore = ".*(mov|jpg|gif|pdf)$";
{% endhighlight %}

<p>This allows us to not only check if the URL has already been visited, but confirm that it is not one of the listed excluded types.</p>

{% highlight java %}
if (!newurl.matches(ignore) &#038;&#038; !urlsVisited.containsKey(newurl)) {
  urlsToVisit.add(newurl);
  urlsVisited.put(newurl,newurl);
}
{% endhighlight %}

<p><a name ="crawler"></a></p>
<h5>A Crawler Class</h5>
<p>The following Crawler class encompasses all of the basic elements described above.  Its data is of a queue of URLs, a hash table of visited URLs, a regex for matching URL references, and a regex for URLs to ignore.  It has methods to add a new URL, crawl to the next URL, read source of a URL (called in the &#8216;crawl&#8217; method), and check if the queue is empty.  </p>

{% highlight java %}/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Simple example of a web crawler
// URL queue: linked list
// Sites already visited: hash table

// Needs to be updated to comply with ROBOTS.TXT!

package a2z;

import java.util.*;
import java.util.regex.*;

public class Crawler {

  private LinkedList urlsToVisit; // A queue of URLs to visit
  private HashMap urlsVisited;    // A table of already visited URLs
  private Pattern href;           // A Pattern to match an href tag
  private String ignore;          // To be used as a regex for ignoring media files (JPG,MOV, etc.)

  public Crawler() {
    urlsToVisit = new LinkedList();
    urlsVisited = new HashMap();

    // Match URLs
    // Note using Pattern.COMMENTS flag which ignores white spaces and anything after '#' in a regex
    href = Pattern.compile( "href             # match href n" +
                            "\s*=\s*"      # 0 or more spaces, =, 0 ore more spaces, quote n" +
                            "(http[^"\s]*)  # capture the URL itself, http followed by no spaces and no quotes n" +
                            ""               # ending with a quote n",
                            Pattern.CASE_INSENSITIVE | Pattern.COMMENTS);

    // We will ignore URLs ending with certain extensions
    ignore = ".*(mov|jpg|gif|pdf)$";
  }

  public void addUrl(String urlpath) {
    // Add it to both the LinkedList and the HashMap
    urlsToVisit.add(urlpath);
    urlsVisited.put(urlpath,urlpath);

  }

  public boolean queueEmpty() {
    return urlsToVisit.isEmpty();
  }

  public void crawl() {
      String urlpath = (String) urlsToVisit.removeFirst();
      read(urlpath);
  }

  private void read(String urlpath) {
    System.out.println(urlsVisited.size() + " " + urlsToVisit.size() + " " + urlpath);
    try {
      // Grab the URL content
      A2ZUrlReader urlr = new A2ZUrlReader(urlpath);
      String stuff = urlr.getContent();

      // OK I COULD DO SOMETHING WITH ALL OF THE CONTENT HERE!!

      // Match the URL pattern to the content
      Matcher m = href.matcher(stuff);
      // While there are URLs
      while (m.find()) {
        // Grab the captured part of the regex (the URLPath itself)
        String newurl = m.group(1);
        // If it hasn't already been visited (or if it matches the ignore pattern)
        if (!newurl.matches(ignore) &#038;&#038; !urlsVisited.containsKey(newurl)) {
          addUrl(newurl);
        }
      }
    } catch (Exception e) {
      // System.out.println("Problem reading from " + urlpath + " " + e);
      // e.printStackTrace();
    }
  }

}

{% endhighlight %}

<p>It is important to note that this crawler does not loop on its own.  The driver program must check and see if the queue has elements and call the &#8220;crawl&#8221; method in a loop, i.e.:</p>

{% highlight java %}
// Create a crawler object
Crawler crawler = new Crawler();
// Put the URL into the crawler object
crawler.addUrl("http://www.blahlbah.com");
while (!crawler.queueEmpty()) {
  crawler.crawl();
}
{% endhighlight %}

<p>In the <a href="http://www.shiffman.net/itp/classes/a2z/week05/valentinecrawler.zip">full example</a>, I&#8217;ve built in a little safety net so that the crawler doesn&#8217;t run rampant (although it might be fun to let it do so!)</p>

{% highlight java %}
// Since this crawler isn't particularly polite: http://www.robotstxt.org/wc/guidelines.html
// I'm limited it to viewing 100 url requests
int count = 0; int limit = 100;
while (!crawler.queueEmpty()) {
  crawler.crawl();
  count++;
  if (count > limit) break;
}
{% endhighlight %}

<p>Writing one&#8217;s own crawler is a useful and productive exercise, however, if all you need is basic crawling functionality, another option is to use an existing crawling library, such as <a href="http://www.cs.cmu.edu/~rcm/websphinx/">WebSphinx</a>.  To use WebSphinx, simply download <a href="http://www.cs.cmu.edu/~rcm/websphinx/#download">download websphinx.jar</a> and import it into you Eclipse project (make sure to add it to the &#8220;build path&#8221; as well.)  You can then create your own Crawler class that extends <a href="http://www.cs.cmu.edu/~rcm/websphinx/doc/websphinx/Crawler.html">Crawler</a>.  By overriding the <a href="http://www.cs.cmu.edu/~rcm/websphinx/doc/websphinx/Crawler.html#visit(websphinx.Page)">visit()</a> method, you can have customize what you want the Crawler to do each time it visits a page.  </p>

{% highlight java %}
// What to do when we visit the page
public void visit(Page page) {
    System.out.println("Visiting: " + page.getTitle());
    String content = page.getContent();
    // Do something with the content String!

    // Since we don't need to retain the pages
    // This code helps with memory management
    page.getOrigin().setPage(null);
    page.discardContent();
}
{% endhighlight %}

<p><a href="http://www.shiffman.net/itp/classes/a2z/week05/sphinx/web_sphinx.zip">Here is an example</a> of a basic Crawler implemented via WebSphinx.  For other possibilities, take a look at the <a href="http://www.cs.cmu.edu/~rcm/websphinx/doc/index.html">WebSphinx API</a>.</p>
