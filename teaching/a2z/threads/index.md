---
title: Threads
author: Daniel
layout: page
dsq_thread_id:
  - 249553416
pvc_views:
  - 142928
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<ul>
<li><a href="#thread">independent threads</a></li>
<li><a href="#synchronize">synchronized threads</a></li>
<li><a href="#library">making p5 libraries</a></li>
</ul>
</div>
<p><b>If you are looking for a tutorial about using threads in Processing, I&#8217;ve adapted this page on the Processing wiki: </p>
<p><i><a href="http://wiki.processing.org/w/Threading">http://wiki.processing.org/w/Threading</a></i></b></p>
<h2>Examples:</h2>
<ul>
<li><a href="http://www.shiffman.net/itp/classes/a2z/week10/simplethreads.zip">simplethreads.zip</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week10/SimpleThread.java">SimpleThread.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week10/ThreadDriver.java">ThreadDriver.java</a></li>
<li><a href="http://www.shiffman.net/itp/classes/a2z/week10/newsreader.zip">newsreader.zip</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week10/XMLNewsChecker.java">XMLNewsChecker.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week10/CheckThread.java">CheckThread.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week10/a2z.zip">a2z package</a></li>
<li>Applet source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/threads_news.zip">threads_news.zip</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week10/nothreads_news.zip">nothreads_news.zip</a></li>
<li>a2z Processing library: <a href="http://www.shiffman.net/itp/classes/a2z/week10/a2z.jar">a2z.jar</a> (required for above applets)</li>
<li>Simple Processing Event library: <a href="http://www.shiffman.net/itp/classes/a2z/week10/pevent.jar">pevent.jar</a>, Library source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/pevent.zip">pevent.zip</a>, Applet source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/simple_eventlib.zip">simple_eventlib.zip</a></li>
<li>Processing NewsReader Event library: <a href="http://www.shiffman.net/itp/classes/a2z/week10/news.jar">news.jar</a>, Library source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/news.zip">news.zip</a>, Applet source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/news_event.zip">news_event.zip</a></li>
</ul>
<h2>Related:</h2>
<ul>
<li><a href="http://java.sun.com/docs/books/tutorial/essential/threads/">Java Threads tutorial</a></li>
</ul>
<h2>Exercises (optional):</h2>
<ul>
<li>Rewrite the <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/crawling/">spider</a> so that each URL request is its own thread.</li>
<li>Recreate one of your previous exercises as a Processing library.</li>
</ul>
<p><a name ="thread"></a></p>
<h2>Threading</h2>
<p>We&#8217;re quite familiar with the idea of writing a program that follows a specific sequence of steps as outlined in, say, a main() function.   A Thread is also a series of steps with a beginning, a middle, and an end.   A thread&#8217;s sequence, however, can run independently of the main program.  In fact, we can launch any number of threads at one time and they will all run concurrently.  Visit the Java site for a <a href="http://java.sun.com/docs/books/tutorial/essential/threads/definition.html">more involved explanation</a>.</p>
<p>This is incredibly useful when it comes to data mining, as we can have separate threads retrieving different pieces of information from the network.  If one gets stuck or has an error, the entire program won&#8217;t grind to a halt, since the error only stops that individual thread.   To create independent, asynchronous threads, we simply extend the <a href="http://java.sun.com/j2se/1.5.0/docs/api/java/lang/Thread.html">Thread</a> class.</p>

{% highlight java %}
public class SimpleThread extends Thread {
{% endhighlight %}

<p>It&#8217;s useful to include a few fields describing the thread&#8217;s properties.   In this example, we&#8217;ll use a boolean variable to indicate whether the thread is running or not, a integer to count how many times the thread has executed its loop, a String to give the thread an ID, and an integer to hold the number of milliseconds in between each execution.  The constructor will initialize these values:</p>

{% highlight java %}
    private boolean running;           // Is the thread running?  Yes or no?
    private int wait;                  // How many milliseconds should we wait in between executions?
    private String id;                 // Thread name
    private int count;                 // counter

    // Constructor, create the thread
    // It is not running by default
    public SimpleThread (int w, String s){
        wait = w;
        running = false;
        id = s;
        count = 0;
    }
{% endhighlight %}

<p>We are then going to override two functions from the parent Thread class:</p>
<ul>
<li><b>start()</b> &#8212; Start causes the thread to begin its execution.   The Java Virtual Machine will call the run method for you, so make sure to include super.start().  We aren&#8217;t required to override this method, but it&#8217;s useful since we may want our own custom code to execute when the thread begins.</li>
<li><b>run()</b> &#8212; Nothing happens in the parent version of this function.  It&#8217;s up to us to write code for whatever we want the thread to do when it <i>runs</i>.  When the end of this function is reached, the thread ends.  To keep it running over a period of time, a while loop (testing the running variable) is used.</li>
</ul>

{% highlight java %}
    // Overriding "start()"
    public void start ()
    {
        // Set running equal to true
        running = true;
        // Print messages
        System.out.println("Starting thread (will execute every " + wait + " milliseconds.)");
        // Do whatever start does in Thread, don't forget this!
        super.start();
    }


    // We must implement run, this gets triggered by start()
    public void run ()
    {
        while (running &#038;&#038; count < 10){
            System.out.println(id + ": " + count);
            count++;
            // Ok, let's wait for however long we should wait
            try {
                sleep((long)(wait));
            }
            catch (Exception e) {
            }
        }
        System.out.println(id + " thread is done!");  // The thread is done when we get to the end of run()
    }
{% endhighlight %}

<p>Finally, it's useful to create a <b>quit()</b> method, in case we want to interrupt the thread and stop it.  (Note that <a href="http://java.sun.com/j2se/1.5.0/docs/guide/misc/threadPrimitiveDeprecation.html">stop() is now deprecated</a>).</p>

{% highlight java %}
    // Our method that quits the thread
    public void quit()
    {
        System.out.println("Quitting.");
        running = false;  // Setting running to false ends the loop in run()
        interrupt(); // in case the thread is waiting. . .
    }
}
{% endhighlight %}

<p>Once we&#8217;ve completed our Thread class, creating and running threads is easy!</p>

{% highlight java %}
SimpleThread thread1 = new SimpleThread(1000,"cat");
SimpleThread thread2 = new SimpleThread(1500,"dog");
thread1.start();
thread2.start();
{% endhighlight %}

<p><a href="http://www.shiffman.net/itp/classes/a2z/week10/newsreader.zip">Here&#8217;s a more sophisticated example that involves reading the Google news RSS feed in a thread.</a> </p>
<p><a name="synchronize"></a></p>
<h2>Synchronized Threads</h2>
<p>Writing an independent thread is easy, nevertheless, there are often times where one needs to access and manipulate information inside a thread externally (perhaps in the &#8220;main&#8221; program, or another thread, etc.)   This problem occurs in data visualization programs, where we might require that an animation driven by data from the network runs smoothly, without having to pause and wait each time that data reloads.   Let&#8217;s look at this applet:</p>
<p><img src="http://itp.nyu.edu/~dts204/a2z/newsapplet.jpg" alt="newsapplet"/></p>
<p><a href="http://itp.nyu.edu/~dts204/a2z/nothread/">Non threaded version</a></p>
<p><a href="http://itp.nyu.edu/~dts204/a2z/thread/">Threaded version</a></p>
<p>A few things have changed.  For example, we have added a boolean variable &#8220;available&#8221; to indicate to the main program when the thread has completed loading a new set of headlines.</p>

{% highlight java %}
  private boolean available;         // Is new news available?
{% endhighlight %}

<p>At the end of the &#8220;check()&#8221; method, which reads the news feed, available is set to true:</p>

{% highlight java %}
  private synchronized void check() {
    headlines = new ArrayList();
    // Create a URL object and open an InputStream
    A2ZXmlReader xmlreader = null;
    try {
      xmlreader = new A2ZXmlReader("http://itp.nyu.edu/icm/proxy/proxy.php?url=http://news.google.com/?output=rss");
      // Call our recursive search function to locate the element we want

      ArrayList elements = new ArrayList();
      xmlreader.fillArrayList(xmlreader.getRoot(),"title",elements);
      for (int i = 0; i < elements.size(); i++) {
        Element e = (Element) elements.get(i);
        // As long as we find the element
        if (e != null) {
          Node n = e.getFirstChild();
          String headline = n.getNodeValue();
          headline = headline.replaceAll("&#39;","'");
          if ((!headline.matches("Google News")) &#038;&#038; (headlines.size() < maxheadlines))  {
            headlines.add(headline);
          }
        }
      }
      available = true;
      notifyAll();  // let's notify everyone that the headlines have been updated
    }
    catch (Exception e) {
      System.out.println("Something went wrong. " + e);
    }
  }
{% endhighlight %}

<p>Note also the use of <a href="http://java.sun.com/docs/books/tutorial/essential/threads/monitors.html">synchronized</a> keyword.  This indicates that the thread should be locked down while this method is executed, i.e. other threads cannot have access to its internal data.   We're also using <a href="http://java.sun.com/docs/books/tutorial/essential/threads/waitAndNotify.html">notifyAll()</a>, which alerts any threads that are waiting for data that it is ready.  Now, whenever the updated ArrayList of headlines is retrieved by an external source, available is reset to false while we wait for the next reload time:</p>

{% highlight java %}
  public synchronized ArrayList getHeadlines() {
    // We should put a while (!available) loop here
    // but since we are explicitly only calling this function if available is true, we're ok
    available = false;
    notifyAll(); // let's notify everyone that available has changed
    return headlines;
  }
{% endhighlight %}

<p>Finally, we include a method <b>available()</b> to return true or false based on whether or not new information has arrived:</p>

{% highlight java %}
  public boolean available() {
    return available;
  }
{% endhighlight %}

<p>Our main program checks if new information is available, and acts accordingly.  It never has to pause and wait for the data to be loaded since all that work is taken care of in the thread.  We check every time through Processing&#8217;s <a href="http://www.processing.org/reference/draw_.html">draw()</a> loop.</p>

{% highlight java %}
void draw() {
  // If there is new news available, get it!
  if (news.available()) {
    headlines = news.getHeadlines();
  }
}
{% endhighlight %}

<p><a name="library"></a></p>
<h2>Making your own Processing library</h2>
<p>Looking closely at the above applets, you&#8217;ll notice the following line of code:</p>

{% highlight java %}
import a2z.*;                  // Lookie, our code is a Processing library!
{% endhighlight %}

<p>Instead of having to include all of our java classes in a &#8220;code&#8221; folder for each sketch, or create each one as a separate .java tab, we can package up these classes into a JAR file and include it as a Processing library.  Here are the steps:</p>
<li>Create a set of classes as a java package &#8212; say it&#8217;s called: mylib.jar</li>
<li>Export the package as a jar.  This can be accomplished in <a href="http://www.eclipse.org/">Eclipse</a> via &#8220;Export&#8221; or command line like so: &#8220;jar cvf mylib.jar Foo.class Bar.class&#8221;</li>
<li>Take the jar file and place it in: <i>/Applications/Processing 0109/libraries/mylib/library/</i></li>
<li>Import the library, i.e.: &#8220;import mylib.*;&#8221;</li>
<p>&nbsp;<br />
It&#8217;s also possible to write a library that registers itself with a parent applet and knows when certain events occur.  We can also provide a <a href="http://java.sun.com/j2se/1.3/docs/api/java/lang/reflect/Method.html">Method</a> instance (from: <a href="http://java.sun.com/j2se/1.3/docs/api/java/lang/reflect/package-summary.html">java.lang.reflect</a>) that can be invoked in the parent applet when a given event occurs.</p>
<p>To implement these features, you&#8217;ll want your class to contain a PApplet reference:</p>

{% highlight java %}
package mylib;

import processing.core.*;

public class MyLib {
  PApplet parent;

  public MyLib(PApplet p) {
    parent = p;
    parent.registerDraw(this);
    parent.registerDispose(this);
  }

  public void draw() {
     // Code here will be executed at the end of draw() in the parent applet
  }

  public void dispose() {
    // Code in here will be executed when the parent applet shuts down
    // (note: http://dev.processing.org/bugs/show_bug.cgi?id=183)
  }

}
{% endhighlight %}

<p>Other methods you can register are: keyEvent, mouseEvent, pre, endFrame, stop, post.  Full documentation is available in howto.txt (in the Processing libraries folder), and you may also find <a href="http://processing.org/discourse/yabb_beta/YaBB.cgi?board=os_libraries_tools">the forums</a> helpful.</p>
<p>To create a callback method that the user writes in the main applet, create a <a href="http://java.sun.com/j2se/1.3/docs/api/java/lang/reflect/Method.html">Method</a> instance in your library class:</p>

{% highlight java %}
Method eventMethod;
{% endhighlight %}

<p>Then, in the constructor, you can check and see if that method exists in the parent:</p>

{% highlight java %}
    try {
      // Looking for a method called "myEvent", with one argument of PEvent type
      eventMethod = parent.getClass().getMethod("myEvent", new Class[] { PEvent.class });
    }
    catch (Exception e) {
      System.out.println("Method not in parent class? " + e);
    }
{% endhighlight %}

<p>Later, whenever you feel like it, you can invoke that method:</p>

{% highlight java %}
// As long as the method exists
if (eventMethod != null) {
  try {
    // Call the method with this object as the argument!
    eventMethod.invoke(parent, new Object[] {this} );
  } catch (Exception e) {
    // Error handling
    System.err.println("I couldn't invoke that method for some reason.");
    e.printStackTrace();
    eventMethod = null;
  }
}
{% endhighlight %}

<p>Your processing applet would then include the method automatically called.  Note this is exactly how <a href="http://processing.org/reference/libraries/serial/serialEvent_.html">serialEvent()</a>, <a href="http://processing.org/reference/libraries/video/captureEvent_.html">captureEvent()</a>, etc. work!</p>

{% highlight java %}
import pevent.*;

PEvent event;

void setup() {
  event = new PEvent(this);
}

void myEvent(PEvent e) {
  println(e.read());
}


void draw() {
}
{% endhighlight %}

<p>Full source:</p>
<ul>
<li>Simple Processing Event library: <a href="http://www.shiffman.net/itp/classes/a2z/week10/pevent.jar">pevent.jar</a>, Library source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/pevent.zip">pevent.zip</a>, Applet source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/simple_eventlib.zip">simple_eventlib.zip</a></li>
<li>Processing NewsReader Event library: <a href="http://www.shiffman.net/itp/classes/a2z/week10/news.jar">news.jar</a>, Library source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/news.zip">news.zip</a>, Applet source: <a href="http://www.shiffman.net/itp/classes/a2z/week10/news_event.zip">news_event.zip</a></li>
</ul>
