---
title: Calling Processing with Asterisk
author: Daniel
layout: page
dsq_thread_id:
  - 249554069
pvc_views:
  - 14205
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<ul>
<li><a href="#asterisk">Setting up Asterisk</a></li>
<li><a href="#thecall">Java Answers the Phone</a></li>
<li><a href="#middle">Simple Server in the Middle</a></li>
<li><a href="#processing">Processing Gets the Call</a></li>
<li><a href="#objects">A List of &#8220;Call&#8221; objects</a></li>
</ul>
</div>
<p><object type="application/x-shockwave-flash" width="300" height = "252" data="http://vimeo.com/moogaloop.swf?clip_id=327214&amp;server=vimeo.com&amp;fullscreen=1&amp;show_title=1&amp;show_byline=1&amp;show_portrait=0&amp;color=00ADEF"><param name="quality" value="best" /><param name="allowfullscreen" value="true" /><param name="scale" value="showAll" /><param name="movie" value="http://vimeo.com/moogaloop.swf?clip_id=327214&amp;server=vimeo.com&amp;fullscreen=1&amp;show_title=1&amp;show_byline=1&amp;show_portrait=0&amp;color=00ADEF" /></object><br /><a href="http://vimeo.com/327214/l:embed_327214">1-800-Processing</a> from <a href="http://vimeo.com/shiffman/l:embed_327214">shiffman</a> on <a href="http://vimeo.com/l:embed_327214">Vimeo</a>.</p>
<h2>Code you need:</h2>
<ul>
<li>Asterisk configuration: <a href="http://www.shiffman.net/itp/asterisk/extensions.conf">extensions.conf</a>, <a href="http://www.shiffman.net/itp/asterisk/runEAGI.sh">runEAGI.sh</a></li>
<li>JEAGI Client: <a href="http://www.shiffman.net/itp/asterisk/JEAGIClient.java">JEAGIClient.java</a>, <a href="http://www.shiffman.net/itp/asterisk/Client.java">Client.java</a></li>
<li>JEAGI Server: <a href="http://www.shiffman.net/itp/asterisk/JEAGIServer.java">JEAGIServer.java</a>, <a href="http://www.shiffman.net/itp/asterisk/ServerThread.java">ServerThread.java</a></li>
<li>Processing examples: <a href="http://www.shiffman.net/itp/asterisk/Asterisk.pde">Asterisk.pde</a>, <a href="http://www.shiffman.net/itp/asterisk/AsteriskMulti.zip">AsteriskMulti.zip</a></li>
</ul>
<h2>Helpful Links:</h2>
<ul>
<li><a href="http://www.asterisk.org/">The Asterisk Site</a></li>
<li><a href="http://www.voip-info.org/wiki/">A Reference Guide to all things VoIP</a></li>
<li><a href="http://itp.nyu.edu/~sve204/redial/">Shawn Van Every&#8217;s Redial Syllabus</a> (Special thanks to Shawn for all of his help in getting these examples working!!)</li>
</ul>
<p><a name ="asterisk"></a></p>
<h2>Getting Started: Asterisk</h2>
<p>This tutorial is designed to outline all the steps you&#8217;ll need to have your <a href="http://www.processing.org">Processing</a> sketch (or any TCP/IP client for that matter) receive a phone call.  None of this will work without a telephone line as well as <a href="http://www.asterisk.org/">Asterisk</a> (created by Mark Spencer in 1999), &#8220;the world&#8217;s leading open source telephony engine and tool kit.&#8221;</p>
<p>Asterisk is technically a <a href="http://en.wikipedia.org/wiki/PBX">PBX</a> (&#8220;Private Branch Exchange&#8221;), a connnection between (usually) a private business and the <a href="http://en.wikipedia.org/wiki/PSTN">PSTN</a> (&#8220;Public Switch Telephone Network&#8221;).  Asterisk, however, also supports  <a href="http://en.wikipedia.org/wiki/Voice_over_IP">VoIP</a> and is configurable/scriptable by a number of different programming languages.  In our case, getting the phone call into Processing will be a multi-step process.</p>
<ul>
<li>User makes a phone call.</li>
<li>Asterisk answers call.</li>
<li>Asterisk triggers shell script.</li>
<li>Shell script launches Java application (JEAGIClient.java)</li>
<li>JEAGIClient connects to local server (JEAGIServer.java)</li>
<li>JEAGIServer passes data from the phone call from JEAGIClient to Processing</li>
</ul>
<p>The remainder of this tutorial will assume that you have a telephone line and Asterisk running.   The <a href="http://www.asterisk.org/support">Asterisk</a> support site provides instructions on how to install Asterisk.</p>
<p><a name ="thecall"></a></p>
<h2>Java Answers the Phone</h2>
<p>Once you have Asterisk running, you will need to create a &#8220;Dialplan.&#8221;  The Dialplan is controlled by a configuration file named &#8220;extensions.conf&#8221; and will control how incoming and outgoing calls are handled and routed.  You can read more about extensions.conf  at <a href="http://www.voip-info.org/tiki-index.php?page=Asterisk%20config%20extensions.conf">voip-info</a> or on <a href="http://itp.nyu.edu/~sve204/redial_fall07/week2.html">Shawn Van Every&#8217;s syllabus</a>.  For us, however, we only need to do one thing when a call is received: trigger a shell script which will in turn allow us to launch a Java process.   Here is the example extensions.conf that I am using:</p>
<p>extensions.conf:</p>
<pre lang="java">
[dts204_default]
exten => _X,1,Goto(s,1);
exten => _X.,1,Goto(s,1);
exten => s,1,Answer();
exten => s,n,EAGI(/path/toyour/shellscript/runEAGI.sh);
</pre>
<p>The shell script itself launches the Java app:</p>
<p>runEAGI.sh:</p>
<pre lang="java">
#!/bin/sh
/usr/java/jdk1.5.0_10/bin/java -classpath /path/toyour/javaclass/ JEAGIClient $$
</pre>
<p>Incidentally, <a href="http://www.voip-info.org/wiki-Asterisk+AGI">EAGI</a> stands for &#8220;Enhanced Asterisk Gateway Interface&#8221;, which is what allows us to program for Asterisk in Java (the &#8220;E&#8221; is for controlling to the sound channel.)  </p>
<p>So, just what does JEAGIClient.java do?   The JEAGIClient reads input from and writes output to Asterisk.  It can execute commands  (such as &#8220;Wait for Digit&#8221;) and listen to activity on the phone call (such as &#8220;The digit 1 was pressed.&#8221;).  It&#8217;s a pretty  simple application that has a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/BufferedReader.html">BufferedReader</a> for input and an <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/OutputStream.html">OutputStream</a> for output.   Here is a snippet of code showing these elements declared and initialized.</p>
<pre lang="java">
public class JEAGIClient
{
  // EAGI Streams
  BufferedReader bin; // input stream from asterisk
  OutputStream out; // communication with asterisk
  OutputStream err; // communication with asterisk
	
  public JEAGIClient() {
    out = System.out;
    err = System.err;
    bin = new BufferedReader(new InputStreamReader(System.in));
  }
</pre>
<p>We can now issue commands to asterisk.    The following will wait 10 seconds for the caller to press a digit.</p>
<pre lang="java">
// Get started waiting for digits
String wait = "WAIT FOR DIGIT -1n";
out.write(wait.getBytes());
</pre>
<p>We can also read the input:</p>
<pre lang="java">
String line = null;
boolean loop = true;
while ((line = bin.readLine()) != null &#038;&#038; loop) {
  // whatever asterisk tells us will show up here as the String line
  // if we want to quit we can set loop = false (i.e. in the case of a hangup)
}
</pre>
<p>The phone number of the caller will show up as &#8220;agi_callerid:##########&#8221; and when a digit is pressed, a message will arrive in the form &#8220;200 result=49&#8243; with 49 being the ASCII code for &#8220;1&#8243;.  The JEAGIClient can therefore extract this information using some simple <a href="http://www.shiffman.net/teaching/a2z/mining/#html">String parsing</a> techniques:</p>
<pre lang="java">
String agi_callerid = "agi_callerid:";
int index = line.indexOf(agi_callerid);
if (index > -1) {
  String phoneNumber = line.substring(index + agi_callerid.length()+1,line.length());
}
</pre>
<p><a name ="middle"></a></p>
<h2>Simple Server in the Middle</h2>
<p>Once the information is read from Asterisk, it needs to be passed to a server running locally (that Processing will also connect to).  This is done by building a simple Java Client class that can be included inside JEAGIClient.java.  The Client will run as its own thread and connect to localhost on an arbitrarily chosen port.</p>
<pre lang="java">
public class JEAGIClient
{
  Client client;
	
  public JEAGIClient() {
    client = new Client("localhost", 9001);
    client.start();
  }
</pre>
<p>Later, when the phone number is parsed, the Client can send that number out to the server:</p>
<pre lang="java">
if (index > -1) {
  String id = line.substring(index + agi_callerid.length()+1,line.length());
  client.send(id);
}
</pre>
<p>The Client itself just needs three objects: <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/net/Socket.html">Socket</a> (to make a socket connection to the Server), a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/PrintWriter.html">PrintWriter</a> (to write out to the Server), and a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/BufferedReader.html">BufferedReader</a> (to read in from the Server).</p>
<p>Here is the full code for <a href="http://www.shiffman.net/itp/asterisk/JEAGIClient.java">JEAGIClient.java</a> and <a href="http://www.shiffman.net/itp/asterisk/Client.java">Client.java</a>.  </p>
<p>The Server itself is also quite simple and requires a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/net/ServerSocket.html">ServerSocket</a> (to listen for incoming connections).  Each connection is farmed out to another class, which manages reading and writing to that connection as its own thread and added to an ArrayList.  This is a very simple chat server.</p>
<pre lang="java">
ArrayList allConnections = new ArrayList();
ServerSocket server = new ServerSocket(9001);
while (true) {
  Socket connection = server.accept();
  ServerThread newConnection = new ServerThread(connection);
  newConnection.start();
  allConnections.add(newConnection);
}
</pre>
<p>The ServerThread class is quite similar to <a href="http://www.shiffman.net/itp/asterisk/Client.java">Client.java</a> and includes a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/net/Socket.html">Socket</a>, <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/PrintWriter.html">PrintWriter</a>, and <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/io/BufferedReader.html">BufferedReader</a>.</p>
<p>Here is the full code for <a href="http://www.shiffman.net/itp/asterisk/JEAGIServer.java">JEAGIServer.java</a> and <a href="http://www.shiffman.net/itp/asterisk/ServerThread.java">ServerThread.java</a>. </p>
<p>A quick note about putting this together.  You&#8217;ll need whatever machine you are running Asterisk on to have Java installed, of course.  You&#8217;ll need to compile and execute JEAGIServer.java on your own, however, for JEAGIClient, you only need to compile the classes since it will be executed by the shell script: runEAGI.sh.  You&#8217;ll have to make sure that your paths are set up correctly in runEAGI.sh and that you are using the same port number in both the JEAGIClient and JEAGIServer.</p>
<p><a name ="processing"></a></p>
<h2>Processing gets the Call</h2>
<p>The quickest way to make sure everything is working is to just telnet to your server and make a call.  You&#8217;ll see that you will receive the callerID along with digits pressed, etc.  This is not automatic, this is how we set up JEAGIClient.java to broadcast the information (so you should feel free to design your own communication protocol).  Once you&#8217;ve confirmed all the pieces are working, you are ready to connect to the JEAGIServer (on the machine running Asterisk) via Processing using the <a href="http://processing.org/reference/libraries/net/">net library</a>.</p>
<pre lang="java">
import processing.net.*; 

// Declare a client 
Client client; 

void setup() { 
  client = new Client(this, "SERVER ADDRESS", 9001); 
} 
</pre>
<p>In draw(), you can check and see if any messages are available:</p>
<pre lang="java">
// If there is information available to read from the Server 
if (client.available() > 0) { 
  String messageFromServer = client.readString();   // Read it as a String 
} 
</pre>
<p>And it&#8217;s as simple as that!</p>
<p>Here is a basic example that displays information from a call: <a href="http://www.shiffman.net/itp/asterisk/Asterisk.pde">Asterisk.pde</a>.</p>
<p><a name ="objects"></a></p>
<h2>A List of Call Objects</h2>
<p>One of the first things you will likely want to do when receiving phone calls in Processing is spawn an object for every new caller.  Most of the time when you want to have multiple objects, a simple <a href="http://processing.org/reference/Array.html">array</a> or even  <a href="http://www.shiffman.net/teaching/nature/particles/">ArrayList</a> will do.  Arrays allow you to store an ordered list of data with each element accessible via its index (the location in the array).  </p>
<p>In this case, however, an array or ArrayList is not a good data structure for storing multiple call objects.  Consider the following scenario (which will continually occur in our program):</p>
<ul>
<li>Processing receives: &#8220;5558675309,newcall&#8221; Create a new object and add it to our ArrayList.</li>
<li>Processing receives: &#8220;5558675309,5&#8243; Find the object associated with the phone # 555-867-5309 and pass it the command &#8220;5&#8243;</li>
<li>Processing receives: &#8220;5558675309,*&#8221; Find the object associated with the phone # 555-867-5309 and pass it the command &#8220;*&#8221;</li>
</ul>
<p>In other words, when we look up an object in our list of objects, we want to be able to find it via its unique phone number, not via its index in an array.  One solution would be to loop through the array and check each object&#8217;s phone number until we find a match, but this isn&#8217;t terribly efficient (although to be honest, with only a handful of callers, this really isn&#8217;t a big deal.)  </p>
<p>It would be preferable to write code that resembles:</p>
<pre lang="java">
if (listOfCalls.contains("5558675309")) {
   Call call = listOfCalls.get("5558675309");
   call.issueCommand("5");
}
</pre>
<p>One data structure that will allow us to do this is a <a href="http://en.wikipedia.org/wiki/Hash_table">Hash Table</a>.  In Java, we&#8217;ll use the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/HashMap.html">HashMap</a> class.   <a href="http://www.shiffman.net/teaching/a2z/bayesian/#hash">Here is a tutorial</a> I wrote up last year about Hash Tables.  Every object stored in a hash map must have both a key and a value.  The key is what we use to look up the object, and the value is the object itself.  We need the key to be unique and this is perfect since by definition every call has a unique key: the phone number itself!  So, when we receive a message from the server:</p>
<p>&#8220;5558675309,newcall&#8221;  or &#8220;5558675309,5&#8243; or &#8220;5558675309,*&#8221; or &#8220;5558675309,hangup&#8221; </p>
<p>We should first split the message into two String tokens:</p>
<pre lang="java">
String[] tokens = msg.trim().split(",");
</pre>
<p>We can then check and see if the phone number is already a key in the HashMap (making a new object if not):</p>
<pre lang="java">
if (!calls.containsKey(tokens[0])) {
  Call call = new Call(tokens[0],random(width),random(height));
  calls.put(call.id,call);
} 
</pre>
<p>We can also issue a command via the digit that was pressed:</p>
<pre lang="java">
Call call = (Call) calls.get(tokens[0]);
if (call != null) {
  call.command(tokens[1]);
}
</pre>
<p>Or remove it if the caller hangs up:</p>
<pre lang="java">
if (tokens[1].equals("hangup")) {
  calls.remove(tokens[0]);
} 
</pre>
<p>The full example is available here: </p>
<p><a href="http://www.shiffman.net/itp/asterisk/AsteriskMulti.zip">AsteriskMulti.zip</a></p>
