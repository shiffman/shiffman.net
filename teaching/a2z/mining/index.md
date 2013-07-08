---
title: Mining
author: Daniel
layout: page
dsq_thread_id:
  - 249553346
pvc_views:
  - 4134
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#html">HTML</a></li>
<li class="arrow"><a href="#xml">XML</a></li>
<li class="arrow"><a href="#api">APIs</a></li>
</div>
<h2>Examples:</h2>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week06/A2ZUrlReader.java">A2ZUrlReader.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week06/WeatherGrabberHTML.java">WeatherGrabberHTML.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week06/A2ZXMLHelper.java">A2ZXMLHelper.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week06/XMLWeatherGrab.java">XMLWeatherGrab.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week06/XMLTraverse.java">XMLTraverse.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week06/YahooNameSearch.java">YahooNameSearch.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week06/DeliciousTest.java">DeliciousTest.java</a></li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week06/FlickrHelper.java">FlickrHelper.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week06/FlickrProcessing.java">FlickrProcessing.java</a></li>
<p>  Fore more on Processing in Eclipse: <a href="http://www.mostpixelsever.com/tutorial/eclipse/">http://www.mostpixelsever.com/tutorial/eclipse/</a></p>
<h2>Exercises (optional):</h2>
<li class="arrow">Combine these examples with one of the text analysis programs.  Try using RSS news feed to train a <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/bayesian/">bayesian filter</a>.</li>
<li class="arrow">Develop some visualization / analysis driven by yahoo searches.  Here&#8217;s <a href="http://osteele.com/archives/2005/12/aargh">an interesting example</a>.</li>
<p>&nbsp;<br />
<a href="http://www.shiffman.net/teaching/programming-from-a-to-z/">So far</a> we&#8217;ve looked at several techniques for analyzing and manipulating text, dealing mostly with locally stored text files.  <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/crawling/">Last week</a>, we tasted the delicious nectar of mining the web by developing <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/crawling/#url">an example that grabbed a the source from a URL</a>.  This week, we&#8217;ll look further at the possibilites of grabbing textual content from the network, exploring parsing <a href="#html">HTML</a> / <a href="#xml">XML</a>, as well as looking at three available APIs, <a href="http://developer.yahoo.com/">Yahoo</a>, <a href="http://sourceforge.net/projects/delicious-java/">del.icio.us</a>, and <a href="http://www.flickr.com/services/api/">flickr</a>.</p>
<p><a name ="html"></a></p>
<h2>Parsing HTML</h2>
<p>Grabbing data from an HTML page can be an uncomfortable experience.  All that HTML whoozeemawhatsit is related to the visual appearance of a page, making the data appear rather disorganized.  Nevertheless, there&#8217;s a lot of useful junk to be found out there in the world of HTML and with a little perserverence, it&#8217;s all there for free.   There are different ways we might approach the problem of pulling information from a webpage.  We can return to some of the basic <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html">String class</a> functionality of yesteryear or use regular expressions.</p>
<p>Letâ€™s say we want to pull out the number of apples from the text below. </p>

{% highlight java %}String stuff = â€œNumber of apples: 62.  Boy, do I like apples or what!â€;{% endhighlight %}

<p>Our algorithm would be as follows:  </p>
<p>1 &#8212; Find the end of the substring â€œapples: â€  Call it start.<br />
2 &#8212; Find the period after â€œapples: â€  Call it end.<br />
3 &#8212; Make a substring of the stuff between start and end.<br />
4 &#8212; Convert the string to a number (if we want to use it as such)</p>
<p>In code, this would look like this:</p>

{% highlight java %}
int start = stuff.indexOf(â€œapples: â€) + 8;  // STEP 1
int end   = stuff.indexOf(â€œ.â€,start);       // STEP 2
String apples = stuff.substring(start,end); // STEP 3
int apple_no = Integer.parseInt(apples);    // STEP 4
{% endhighlight %}

<p>The above code will do the trick, but we should be a bit more careful to make sure we donâ€™t run into any errors if we donâ€™t find the substrings.  We can add some error checking and generalize the code into a function:</p>

{% highlight java %}
// A function that returns a substring between two substrings
String giveMeTextBetween(String s, String startTag, String endTag) {
  String found = "";
  int startIndex = s.indexOf(startTag);         // Find the index of the beginning tag
  if (startIndex == -1) return "";              // If we don't find anything, return an empty String
  startIndex += startTag.length();              // Move to the end of the beginning tag
  int endIndex = s.indexOf(endTag, startIndex); // Find the index of the end tag
  if (endIndex == -1) return "";                // If we don't find the end tag, return a empty String
  return s.substring(startIndex,endIndex);      // Return the text in between
}
{% endhighlight %}

<p>We could also rewrite the function using regular expressions:</p>

{% highlight java %}
// A function to pull out the text between two regular expression Strings
public static String regexTextBetween(String s, String startRegex, String endRegex) {
  // Create on large regex from start and end with anything in between
  // Note how we use parenthese to capture the in between stuff
  String bigRegex = startRegex + "(.*?)" + endRegex;
  // Compile the pattern and create the matcher
  Pattern p = Pattern.compile(bigRegex);
  Matcher m = p.matcher(s);
  // If we find what we are looking for, return the captured group
  if (m.find()) {
    return m.group(1);
  } else {
    return "oops!";
  }
}
{% endhighlight %}

<p>Let&#8217;s say we wanted to grab the high and low temperatures off of <a href="http://weather.yahoo.com/forecast/USNY0996.html">this Yahoo weather page</a>.  If we visit that page and view source, we can see that the temperatures are embedded in the following html:</p>

{% highlight java %}
&lt;font size="2" face="Arial"&gt;High:&lt;/font&gt;&amp;nbsp;&lt;b&gt;&lt;font size="3" face="Arial"&gt;37&amp;deg;&lt;/font&gt;&lt;/b&gt;
&lt;font size="2" face="Arial"&gt;Low:&lt;/font&gt;&amp;nbsp;&lt;b&gt;&lt;font size="3" face="Arial"&gt;25&amp;deg;&lt;/font&gt;&lt;/b&gt;
{% endhighlight %}

<p>To grab the data we want, we simply call the above function with a regular expression matching that pattern that precedes and follows &#8220;37&#8243; (as well as &#8220;25&#8243;).</p>

{% highlight java %}
A2ZUrlReader urlreader = new A2ZUrlReader("http://weather.yahoo.com/forecast/USNY0996.html");
String html = urlreader.getContent();

// Grab high temperature
String high = regexTextBetween(html,"High:&lt;/font&gt;&amp;nbsp;&lt;b&gt;&lt;font size="3" face="Arial"&gt;","&amp;deg;");
System.out.println("The high for today is: " + high + " degrees.");

// Grab low temperature
String low = regexTextBetween(html,"Low:&lt;/font&gt;&amp;nbsp;&lt;b&gt;&lt;font size="3" face="Arial"&gt;","&amp;deg;");
System.out.println("The low for today is: " + low + " degrees.");
{% endhighlight %}

<p><a name ="xml"></a></p>
<h2>XML</h2>
<p>So, yes, HTML is an ugly, scary place with inconsistently formatted pages that are difficult to reverse engineer and parse effectively.  Fortunately for you, there is the world of <a href="http://en.wikipedia.org/wiki/XML">XML</a> (Extensible Markup Language) page.  XML is designed to facilitate the sharing of data across different systems and its format wonâ€™t cause your hair to go gray as fast.  Let&#8217;s examine how we might grab exactly the same weather information from <a href="http://xml.weather.yahoo.com/forecastrss?p=94089">Yahooâ€™s RSS feed</a>.</p>
<p>XML organizes information in a tree structure.  The code we&#8217;ll write to search and traverse an XML document is somewhat similar to the work we did in building a <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/concordance/#tree">binary search tree</a> and will involve <a href="http://en.wikipedia.org/wiki/Recursion">recursion</a>.  Let&#8217;s look at the XML for Yahoo weather&#8217;s RSS feed (this is only part of the source in order to simplify the discussion.)</p>

{% highlight java %}
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes" ?&gt;
&lt;rss version="2.0" xmlns:yweather="http://xml.weather.yahoo.com/ns/rss/1.0" xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#"&gt;
 &lt;channel&gt;
   &lt;item&gt;
     &lt;title&gt;Conditions for New York, NY at 3:51 pm EST&lt;/title&gt;
     &lt;geo:lat&gt;40.67&lt;/geo:lat&gt;
     &lt;geo:long&gt;-73.94&lt;/geo:long&gt;
     &lt;link&gt;

http://us.rd.yahoo.com/dailynews/rss/weather/New_York__NY/*http://xml.weather.yahoo.com/forecast/USNY0996_f.html

     &lt;/link&gt;
     &lt;pubDate&gt;Mon, 20 Feb 2006 3:51 pm EST&lt;/pubDate&gt;
     &lt;yweather:condition text="Fair" code="34" temp="35" date="Mon, 20 Feb 2006 3:51 pm EST"/&gt;
     &lt;yweather:forecast day="Mon" date="20 Feb 2006" low="25" high="37" text="Clear" code="31"/&gt;
     &lt;guid isPermaLink="false"&gt;USNY0996_2006_02_20_15_51_EST&lt;/guid&gt;
   &lt;/item&gt;
&lt;/channel&gt;
&lt;/rss&gt;
{% endhighlight %}

<p>With the exception of the first line (which simply indicates that the document is XML formatted), this XML document constains a nested structure consisting of <i><b>elements</b></i>, each with a start tag, i.e. &lt;channel&gt; and an end tag, i.e. &lt;/channel&gt;.  Some of these elements have <strong><em>content</em></strong> between the tags, i.e:</p>

{% highlight java %}&lt;title&gt;Conditions for New York, NY at 3:51 pm EST&lt;/title&gt;{% endhighlight %}

<p>and some have <strong><em>attributes</em></strong>:</p>

{% highlight java %}&lt;yweather:forecast day="Mon" date="20 Feb 2006" low="25" high="37" text="Clear" code="31"/&gt;{% endhighlight %}

<p>It should be fairly obvious how searching for information, such as the title of the page or the high temperature, will be significantly less painful than with the tragically arbitrary process of parsing HTML.  Java provides us with a nice <a href="http://java.sun.com/webservices/jaxp/index.jsp">api for processing XML</a>.  The three packages we&#8217;ll need are:</p>
<li class="arrow"><a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/package-summary.html">org.w3c.dom</a>, which contains the <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Document.html">Document</a> class, representing the entire XML document.  Other noteworthy classes are <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Element.html">Element</a>, <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Attribute.html">Attribute</a>, <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Node.html">Node</a>, <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/NodeList.html">NodeList</a>.
</li>
<li class="arrow"><a href="http://java.sun.com/j2se/1.4.2/docs/api/javax/xml/parsers/package-summary.html">javax.xml.parsers</a>, which contains <a href="http://java.sun.com/j2se/1.4.2/docs/api/javax/xml/parsers/DocumentBuilder.html">DocumentBuilder</a>.  This does exactly what you might think, builds a <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Document.html">Document</a> instance out of an XML file.</li>
<li class="arrow"><a href="http://java.sun.com/j2se/1.4.2/docs/api/org/xml/sax/package-summary.html">org.xml.sax</a> &#8212; &#8220;SAX&#8221; stands for Simple Api for XML and this package contains other useful classes and interfaces.</li>
<p>Ok, we can begin to write some code.  Let&#8217;s just build a <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Document.html">Document</a> object from an XML page.  (Note this is code is incomplete, just demonstrates the first few steps of an XML reading program.)</p>

{% highlight java %}
// Import the necessary libraries
import java.io.*;
import java.net.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class XMLWeatherGrab {
  public static void main (String argv []){
    try {
      // Create a URL object and open an InputStream
      URL url = new URL("http://xml.weather.yahoo.com/forecastrss?p=10003");
      InputStream is = url.openStream();

      // Build a document from that InputStream
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(is);
{% endhighlight %}

<p>Next, we might choose to grab the root element from the XML tree:</p>

{% highlight java %}
Element root = doc.getDocumentElement();
System.out.println(root.toString());
{% endhighlight %}

<p>(Note how the above code displays the entire XML document.  Remember, XML is a nested structure and this root element contains all the subelements of the XML document!  So instead, we might begin to poke around the children of the root.)</p>

{% highlight java %}
NodeList children = root.getChildNodes();
for (int i = 0; i < children.getLength(); i++) {
  Node n = children.item(i);
  System.out.println(n.getNodeName());
}
{% endhighlight %}

<p>If we run the above code, which grabs a list of children and prints out their corresponding tag names, the result isn't terribly interesting.  All we get is "channel."  This is because the root element "RSS" has only one child, "channel."  So, what's next?  We'll need to look at the children of "channel"!  Oh, and then we might need to look at the children of the children of "channel" (if there are any)!   Indeed, this is where our recursive <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/concordance/#tree">tree traversal</a> code will come in handy.  The following function searches for specific element (in our case, we'll want to look for "yweather:forecast").   As long as it is still searching, it will traverse each node's child nodes.  It's a bit confusing as we have to deal with both <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Node.html">Node</a> objects and <a href="http://java.sun.com/j2se/1.4.2/docs/api/org/w3c/dom/Element.html">Element</a> objects.  The code checks each Node to see if it is of the type Element and if so, quickly converts it by casting the Node reference.</p>

{% highlight java %}
public Element findElement(Element currElement, String elementName)
{
  // So far we've found nothing, i.e. null
  Element found = null;
  // If the current element is what we want, hooray we are done!
  if (currElement.getTagName().equals(elementName)) {
    found = currElement;
  // Otherwise, if there are children, let's check those
  } else if (currElement.hasChildNodes()) {
    // Get the list of children
    NodeList children = currElement.getChildNodes();
    // As long as we haven't found it (i.e. found is still null)
    for (int i = 0; i < children.getLength() &#038;&#038; found == null; i++) {
      // Search each Element type node
      Node n = children.item(i);
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        Element e = (Element) n;
        found = findElement(e, elementName);
      }
    }
  }
  return found;
}
{% endhighlight %}

<p>We might also just want to traverse the XML document and display all the info, which we can do in a similar fashion:</p>

{% highlight java %}
// This method recursively traverses the XML Tree (starting from currElement)
public void traverseXML (Node currNode)
{
  // If it's an Element, spit out the Name
  if (currNode.getNodeType() == Node.ELEMENT_NODE) {
    System.out.print(currNode.getNodeName() + ": ");
  // If it's a "Text" node, take the value
  // These will come one after another and therefore appear in the right order
  } else if (currNode.getNodeType() == Node.TEXT_NODE) {
    System.out.println(currNode.getNodeValue().trim());
  }

  // Display any attributes
  if (currNode.hasAttributes()) {
    NamedNodeMap attributes = currNode.getAttributes();
    for (int i = 0; i < attributes.getLength(); i++) {
      Node attr = attributes.item(i);
      System.out.println("  " + attr.getNodeName() + ": " + attr.getNodeValue());
    }
  }

  // Check any children
  if (currNode.hasChildNodes()) {
    // Get the list of children
    NodeList children = currNode.getChildNodes();
    // Go through all the chilrden
    for (int i = 0; i < children.getLength(); i++) {
      // Search each Node
      Node n = children.item(i);
      traverseXML(n);
    }
  }
}
{% endhighlight %}

<p>Of course, the inevitable question now comes up.  Gosh, that seems awfully complicated.  Parsing the HTML was, well, simpler!  Indeed, getting up to speed with using XML can be a steep climb.  Nevertheless, the rewards are greater.  And since I've packaged the above code into its own class: <a href="http://www.shiffman.net/itp/classes/a2z/week06/A2ZXMLHelper.java">A2ZXMLHelper.java</a>, you should feel free to simply use its functionality.    This base class (<a href="http://www.shiffman.net/itp/classes/a2z/week06/A2ZXMLHelper.java">A2ZXMLHelper.java</a>)should provide you with a framework for getting started parsing XML documents.  See: <a href="http://www.shiffman.net/itp/classes/a2z/week06/XMLWeatherGrab.java">XMLWeatherGrab.java</a> for the example pulling weather information from Yahoo's RSS.</p>
<p>Another thing you might notice about XML is how "object-oriented" it seems.  If you take a look at an RSS feed, for example, you might notice that the tree structures contains a list of "item" elements (each item as a date, title, description, link, etc.).  Below is a simplification:</p>

{% highlight java %}
&lt;item&gt;
	&lt;title&gt;Article Title&lt;/title&gt;
	&lt;link>http://link&lt;/link&gt;
	&lt;pubDate>Mon, 25 Feb 2008 02:05:12 -0600&lt;/pubDate&gt;
	&lt;description&gt;
		Article Description blah blah blah
	&lt;/description&gt;
&lt;/item&gt;
{% endhighlight %}

<p>We could easily then design a Java class to store the information with a similar structure:</p>

{% highlight java %}
public class Post {

  private String title;
  private String content;
  private String link;
  private String date;
{% endhighlight %}

<p>Adding functions to set the values of each variable inside the class:</p>

{% highlight java %}
  void setTitle(String t) {
    title = t;
}

Full code: <a href="http://www.shiffman.net/itp/classes/a2z/week06/Post.java">Post.java</a>

Now that we have this Post class, we can read the XML document and create Post objects for each "item" element.  We first use the <a href="http://www.shiffman.net/itp/classes/a2z/week06/A2ZXMLHelper">A2ZXMLHelper.java</a> class which includes a function to fill an ArrayList with all XML Elements with a specific name.

{% endhighlight %}


{% highlight java %}
// Use the helper class to create an XML document
A2ZXMLHelper xml = new A2ZXMLHelper("http://feeds.boingboing.net/boingboing/iBag");

// Make an ArrayList to put all "item" elements from the XML doc
ArrayList items = new ArrayList();
xml.fillArrayList(xml.getRoot(), "item", items);
{% endhighlight %}

<p>Once we have all the XML Elements, we make a new ArrayList to fill with Post objects. . .</p>

{% highlight java %}
// Now, for every "item" in the XML doc, we will make a "Post" object
// and store in an ArrayList
ArrayList posts = new ArrayList();
{% endhighlight %}

<p>. . .and walk through each XML Element, filling the details of each Post object.</p>

{% highlight java %}
// Loop through all items
for (int i = 0; i < items.size(); i++) {
  // Make an empty Post object
  Post p = new Post();
  // Look at each "item" element
  Element e = (Element) items.get(i);
  // Get each item's children
  NodeList children = e.getChildNodes();

  for (int j = 0; j < children.getLength(); j++) {
    Node n = children.item(j);
    // Figure out which Node this is and set the node's text content in the object
    String tag = n.getNodeName();
    if (tag.equals("title")) {
      p.setTitle(n.getTextContent());
    }
    else if (tag.equals("pubDate")) {
      p.setDate(n.getTextContent());
    }
    else if (tag.equals("link")) {
      p.setLink(n.getTextContent());
    }
    else if (tag.equals("description")) {
      p.setContent(n.getTextContent());
    }
  }
  posts.add(p);
}
{% endhighlight %}

<p>Full code: <a href="http://www.shiffman.net/itp/classes/a2z/week06/BlogReader.java">BlogReader.java</a><br />
Full code: <a href="http://www.shiffman.net/itp/classes/a2z/week06/Post.java">Post.java</a></p>
<p><a name ="api"></a></p>
<h2>APIs</h2>
<p>Sure, we can parse HTML.  Sure, we can wade through nicely formatted XML.  But wouldn't it be nicer if we didn't have to do either and could simply access the data of a web site via some sort of API?  In fact, with many sites we can.  For starters: <a href="http://programmableweb.com/apis">The Programmable Web: APIs</a>.  Woohoo!</p>
<p>Let's look at three examples, <a href="http://developer.yahoo.com/">Yahoo search</a>, <a href="http://del.icio.us">del.icio.us</a>, and <a href="http://www.flickr.com">Flickr</a>.  To use any of these APIs in Eclipse, you first have add the appropriate library files (usually in the form of a <a href="http://java.sun.com/docs/books/tutorial/deployment/jar/">JAR</a>) to your Eclipse "Build Path".  This is done with the following steps:</p>
<p>1) Import the JAR file into your project.  There are many ways to do this, one way is to use: FILE â€“> IMPORT â€“> GENERAL â€“> FILE SYSTEM. (Technically, you don't need to import the file, you can point to it in another directory.  However, for the purpose of keeping everything together in one project, it's sometimes convenient to copy the JAR into your project.)  The following screenshot shows importing the Processing JAR "core.jar."</p>
<p><img src="http://www.mostpixelsever.com/images/import.jpg"/></p>
<p>2) Click â€œfinish.â€  You should now see â€œcore.jarâ€ listed under your project in the package explorer. </p>
<p><img src="http://mostpixelsever.com/images/core1.jpg"/></p>
<p>3) Right-click on the file and select BUILD PATH â€“> ADD TO BUILD PATH. The â€œcore.jarâ€ file should now appear with a jar icon.</p>
<p><img src="http://mostpixelsever.com/images/core2.jpg"/></p>
<p>For the Yahoo API, you'll need the JAR: "yahoo_search-2.0.1.jar" as well as an API Key (so Yahoo can track you in case your use of the API). </p>
<li class="arrow"><a href="http://developer.yahoo.com/search/">Yahoo Developer Site</a></li>
<li class="arrow"><a href="http://search.yahooapis.com/webservices/register_application">Get A Developer Key</a></li>
<li class="arrow"><a href="http://developer.yahoo.com/download/">Download the API</a> (which includes "yahoo_search-2.0.1.jar")</li>
<li class="arrow"><a href="http://www.shiffman.net/itp/classes/a2z/week06/YahooNameSearch.java">Try this example.</a></li>
<p>The code for dealing with the API is pretty simple.  Import the library:</p>

{% highlight java %}import com.yahoo.search.*;{% endhighlight %}

<p>Second, create a SearchClient object and pass it your license key.</p>

{% highlight java %}
SearchClient client = new SearchClient("YOUR KEY HERE!");
{% endhighlight %}

<p>Afterwards, you can ask Yahoo to perform a search and retrieve the resulting metadata (total results, time search took, URLs, titles, descriptions, etc.)  The example below displays the total number of results for a google search for &#8220;itp.&#8221;  Full documentation is available in the <a href="http://developer.yahoo.com/download/download.html">API download</a>. </p>

{% highlight java %}
  WebSearchRequest request = new WebSearchRequest("itp");
  WebSearchResults results = client.webSearch(request);
  int count = results.getTotalResultsAvailable().intValue();
{% endhighlight %}

<p>There&#8217;s also a nice <a href="http://sourceforge.net/projects/delicious-java">Java del.icio.us API</a> available on <a href="http://sourceforge.net/">sourceforge</a>.   It works in a similar way as the Yahoo API, and is available as <a href="http://sourceforge.net/project/showfiles.php?group_id=121225">a JAR download</a>.  The <a href="http://delicious-java.sourceforge.net/">JavaDocs are available online</a> as well.</p>
<p>It&#8217;s very simple to use.  All you need to do is create a <a href="http://delicious-java.sourceforge.net/del/icio/us/Delicious.html">Delicious</a> object instance by passing in your username and password.  (I would suggest pulling these from the args array or properties file so that they don&#8217;t appear in your code like below.)  You can then retrieve <a href="http://delicious-java.sourceforge.net/del/icio/us/beans/Tag.html">Tags</a>, <a href="http://delicious-java.sourceforge.net/del/icio/us/beans/Post.html">Posts</a>, etc.  The example below grabs all of the tags associated with an account into a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/List.html">List</a> and prints them.</p>

{% highlight java %}
import del.icio.us.*;
import del.icio.us.beans.*;
import java.util.*;

public class DeliciousTest {
    public static void main(String[] args) {
        System.out.println("Testing del.icio.us API");
        Delicious del = new Delicious("itpa2z","interact");
        List tags = del.getTags();
        Iterator i = tags.iterator();
        while (i.hasNext()) {
            Tag tag = (Tag) i.next();
            int count = tag.getCount();
            String word = tag.getTag();
            System.out.println(word + " " + count);
        }
    }
}
{% endhighlight %}

<p>Of course, you don&#8217;t need this API to access <a href="http://del.icio.us">del.icio.us</a> since you can just as easily grab data via <a href="http://del.icio.us/rss/">RSS</a>.</p>
<p>Finally, here&#8217;s some quick information to get you started using the Flickr API (which again could also be accessed via <a href="http://www.flickr.com/services/feeds/">RSS feeds</a>.</p>
<p>Flickr Services API: <a href="http://www.flickr.com/services/api/">http://www.flickr.com/services/api/</a><br />
FlickrJ: <a href="http://sourceforge.net/projects/flickrj/">http://sourceforge.net/projects/flickrj/</a><br />
FlickrJ docs: <a href="http://flickrj.sourceforge.net/api/index.html">http://flickrj.sourceforge.net/api/index.html</a></p>
<p>To get started using the Flickr API, you must first get an API key: <a href="http://www.flickr.com/services/api/keys/">http://www.flickr.com/services/api/keys/</a>.   To use the Flickr API, you simply create a Flickr object with they key.</p>

{% highlight java %}
Flickr f = new Flickr("YOUR API KEY!");
{% endhighlight %}

<p>Then, you can call various functions to get information related to people, photos, comments, groups, photosets, geocoding info, etc.  The API is quite large and we won&#8217;t cover it in great detail here.  However, here is a quick example to show you how you might grab an array of images tagged with &#8220;itp.&#8221;</p>

{% highlight java %}
// Interface with Flickr photos
PhotosInterface photos = f.getPhotosInterface();
// Create a search parameters object to control the search
SearchParameters sp = new SearchParameters();
// Simple example, just looking for a single tag
sp.setTags(new String[] {"itp"});
// We're looking for n images, starting at "page" 0
PhotoList list = photos.search(sp, n,0);
// Grab all the image paths and store in String array
String[] smallURLs = new String[list.size()];
for (int i = 0; i < list.size(); i++) {
  Photo p = (Photo) list.get(i);
  smallURLs[i] = p.getSmallUrl();
}
{% endhighlight %}

<p>The full example takes these photos and loads the as PImage objects in a <a href"http://www.processing.org">Processing</a> sketch.  </p>
<p><a href="http://www.shiffman.net/itp/classes/a2z/week06/FlickrHelper.java">FlickrHelper.java</a><br />
<a href="http://www.shiffman.net/itp/classes/a2z/week06/FlickrProcessing.java">FlickrProcessing.java</a></p>
