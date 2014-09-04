---
title: Interactive Fiction
author: Daniel
layout: page
dsq_thread_id:
  - 249761763
pvc_views:
  - 2329
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#if">why Interactive Fiction</a></li>
<li class="arrow"><a href="#howto">how to play</a></li>
<li class="arrow"><a href="#compiling">Inform: compiling</a></li>
<li class="arrow"><a href="#coding">Inform: coding</a></li>
</div>
<h2>Examples:</h2>
<li class="arrow"><a href="http://shiffman.net/itp/classes/a2z/week08/HelloWorld.inf">HelloWorld.inf</a></li>
<li class="arrow"><a href="http://shiffman.net/itp/classes/a2z/week08/ITP.inf">ITP.inf</a></li>
<li class="arrow"><a href="http://www.nickm.com/if/if/kits.tgz">Two starter kits</a> by <a href="http://www.nickm.com/">Nick Montfort</a></li>
<li class="arrow"><a href="http://www.firthworks.com/roger/downloads/IBGgames.zip">IBG examples</a> by Roger Firth and Sonja Kesserich.  Use along with the <a href="http://www.inform-fiction.org/manual/download_ibg.html">Inform Beginnner&#8217;s Guide</a>.</li>
<h2>Reading:</h2>
<li class="arrow"><a href="http://nickm.com/if/toward.html">Toward a Theory of Interactive Fiction</a>, Nick Montfort</li>
<li class="arrow"><a href="http://nickm.com/if/rec.html">Interactive Fiction Recommendations</a>, Nick Montfort (pick one to play and write a short response on the <a href="http://shiffman.net/a2z/">course blog.</a>)
</li>
<li class="arrow"><a href="#">The Inform Beginner&#8217;s Guide</a>, Chapters 1-5</li>
<li class="arrow"><a href="http://nickm.com/twisty/">Twisty Little Passages</a>, Nick Montfort (suggested)</li>
<li class="arrow"><a href="#">Inform User&#8217;s Manual</a> (suggested) </li>
<h2>Assignment:</h2>
<li class="arrow">Develop your own interactive fiction work using <a href="http://www.inform-fiction.org/">Inform</a>.  Feel free to work in groups and base your story off of the provided examples.  Keep it simple.   Do not feel obligated to make your work a game or puzzle.  Publish your work along with the source to the <a href="http://shiffman.net/a2z/">course blog</a>.</li>
<h2>Exercises (optional):</h2>
<li class="arrow">Examine the simple ITP text adventure above.  What is missing?  What doesn&#8217;t really make sense / work?  Can you fix / improve it?</li>
<p><a name ="if"></a></p>
<h2>Why Interactive Fiction</h2>
<p><a href="http://en.wikipedia.org/wiki/Interactive_fiction">Interactive Fiction</a> (IF), sometimes referred to as &#8220;text adventure&#8221; and not to be confused with <a href="http://en.wikipedia.org/wiki/Hypertext_fiction">Hypertext Fiction</a>, describes an experience where a user inputs text and receives a reply in the form of text output.   Interactive fiction walks back and forth across the line between literary narrative and computer game where a player character, or &#8220;interactor,&#8221; explores a simulated world.   </p>
<p>To date, we&#8217;ve focused our energies on strategies and techinques for programming algorithms that analyze and generate text, mostly in Java, with some discussion of perl / php along the way.  Why take a break for interactive fiction?  First and foremost, playing interactive fiction is good for you.  The skills you pick up problem-solving your way through a simulated world are the same skills you need to write good code.  Logic, creativity, ingenuity, organization.</p>
<p>Part of our goal this semester is to look at ways a computer can understand and process text, whether in the form of natural language or simply as abstract data.  Interactive fiction deals with this problem head on and has been since 1975 when Will Crowther, a programmer and cave enthusiast, developed <a href="http://en.wikipedia.org/wiki/Colossal_Cave_Adventure">Adventure</a> in <a href="http://en.wikipedia.org/wiki/Fortran">Fortran</a>.   Learning the basics of this stripped-down-to-the-bare-text-essentials medium (no hiding behind fancy visualizations) should provide us with some insight into our own process for developing engaging and interesting text-based work.  And if you&#8217;re feeling particularly saucy, you might run with this and consider entering your work in the annual <a href="http://www.ifcomp.org/">Interactive Fiction competition</a>.</p>
<p><a name ="howto"></a></p>
<h2>How to play: Interpreters and basic commands</h2>
<p>Just as <a href="http://java.sun.com">java</a> programs run on a <a href="http://en.wikipedia.org/wiki/Java_virtual_machine">Java vitual machine</a> (the same java bytecode can be run on different platforms as long as a virtual machine exists for that platform) most interactive fiction programs run via a virtual machine, also known as an &#8220;interpreter.&#8221;   For the Mac OS X, I suggest you download and install <a href="http://www.logicalshift.demon.co.uk/mac/zoom.html">Zoom</a> as your interpreter, for Windows: <a href="http://ifarchive.giga.or.at/if-archive/interpreters-infocom-zcode/frotz/">Windows Frotz 2002</a>.   Note that not all interactive fiction works exist as Z-code files that run on Z-machine interpreters.   <a href="http://www.tads.org/">TADS</a> (Text Adventures Development System) is also a popular authoring tool for IF and for these works, you&#8217;ll need a <a href="http://www.tads.org/download.htm">TADS interpreter</a>.</p>
<p>If you want to incorporate IF into your java programs, you might be interested in checking out <a href="http://sourceforge.net/projects/zplet/">zplet</a>, an open-source java interpreter.  <a href="http://www.eblong.com/zarf/zplet/shade.html">Here&#8217;s an example</a> of the IF work <a href="http://www.eblong.com/zarf/if.html#shade">Shade</a> by Andrew Plotkin running as a zplet.</p>
<p><a href="http://shiffman.net/itp/classes/a2z/week08/zork1.jpg"><img src="http://shiffman.net/itp/classes/a2z/week08/zork1small.jpg" alt = "zork" class = "right"/></a></p>
<p>A comprehensive resource for tools to play IF can be found on the <a href="http://www.ifwiki.org/index.php/Websites_for_downloading_or_playing_IF">Interactive Fiction wiki</a>.  To find works, you might look through the <a href="http://www.ifarchive.org/">Interactive Fiction Archive</a> or check out <a href="http://www.nickm.com/if/rec.html">this list of recommendations</a> by <a href="http://www.nickm.com/">Nick Montfort</a>.  <a href="http://www.wurb.com/if/index">Baf&#8217;s Guide to the Interactive Fiction Archive</a> is another good resource.</p>
<p>Exploring interactive fiction worlds is often a relatively intuitive process (certainly, there are exceptions).  However, if you&#8217;re new to it, there are some useful conventions and familiarizing yourself with a few basic commands (see: <a href="http://jerz.setonhill.edu/if/gallery/help.html#Basics">this article</a>) will help you get started.  </p>
<p>Some examples of common IF commands:</p>
{% highlight java %}
>look
>examine something
>take something
>go west
>north
>put something in something
>open something
>listen to something (or someone)
>up
>exit
>inventory
>give something to someone
{% endhighlight %}
<p><a name ="compiling"></a></p>
<h2>Inform: compiling</h2>
<p>To develop your own interactive fiction works, you&#8217;re probably going to want to use an existing authoring environment, such as <a href="http://www.inform-fiction.org/">Inform</a> or <a href="http://www.tads.org/">TADs</a>.  The following instructions document how to compile interactive fiction works using Inform on a Mac (<a href="http://www.firthworks.com/roger/informfaq/pp.html#6">PC instructions</a>).</p>
<p>First, <a href="http://www.inform-fiction.org/software/current.html">download the appropriate compiler for your system</a>.  Extract the files to your hard drive.  With the Mac OS X compiler, you&#8217;ll have two crucial elements: &#8220;inform630_macosx&#8221; &#8212; the executable compiler &#8212; and &#8220;lib&#8221; &#8212; a directory with all the required library files.  (Note you may find it interesting to look through the library files, as they will provide insight into the guts of the Inform parser.) </p>
<p>Ok, you&#8217;re ready to compile.  Download <a href="http://shiffman.net/itp/classes/a2z/week08/HelloWorld.inf">HelloWorld.inf</a> and use the syntax below (note I placed the inform compiler in the applications directory, but you may have placed it somewhere else).</p>
{% highlight java %}
$ /Applications/inform/inform630_macosx +include_path=/Applications/inform/lib/ HelloWorld.inf
{% endhighlight %}
<p>Some basic unix knowledge can make life a little easier, you probably want to consider making an alias (syntax below is for bash):</p>
{% highlight java %}
$ alias inform='/Applications/inform/inform630_macosx +include_path=/Applications/inform/lib'
{% endhighlight %}
<p>You might also consider adding the alias to your profile (.profile or .bash_profile depending on your shell) to make it permanent.  With the alias compiling is less wordy:</p>
{% highlight java %}
$ inform HelloWorld.inf
{% endhighlight %}
<p>How it might look:<br />
<img src="http://shiffman.net/itp/classes/a2z/week08/compiling.jpg" alt="compiling"/></p>
<p>Compiling should yield the z-code file <a href="http://shiffman.net/itp/classes/a2z/week08/HelloWorld.z5">HelloWorld.z5</a> which you can then run with Zoom.</p>
<p><a name ="coding"></a></p>
<h2>Inform: coding</h2>
<p>For a full guide to programming interactive fiction with <a href="http://www.inform-fiction.org/">Inform</a> I suggest you read the <a href="http://www.inform-fiction.org/manual/about_dm4.html">Designer&#8217;s Manual</a> or the <a href="http://www.inform-fiction.org/manual/about_ibg.html">Inform beginner&#8217;s guide</a>.  <a href="http://chicagodave.wordpress.com/">David Cornelson&#8217;s</a> <a href="http://www.ifwiki.org/index.php/House_1_Inform_Tutorial">House Tutorial</a> is also an excellent resource.  In addition, <a href="http://www.nickm.com/">Nick Montfort</a> provides two introductory <a href="http://www.nickm.com/if/#advice">starter kits</a> for Inform.</p>
<p>Fortunately, we have a head start here since we already understand basic computer programming concepts such as conditional statments, variables, arrays, functions, and objects (an understanding of object-oriented design is fundamental to IF development).   The following is not, by any means, a substitute for the above resources.   This brief discussion barely scratches the surface of IF development and glosses over some of the finer points. </p>
<p><b>Comments</b></p>
<p>Any text that appears after an exclamation point is ignored by the compiler as a comment:</p>
{% highlight java %}
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!  Hello World
!  HelloWorld.inf                       
!  by Daniel Shiffman          
!  Example for Programming from A to Z, Spring 2006
{% endhighlight %}
<p><b>Variables</b></p>
<p>Global constants/variables are defined at the top.</p>
{% highlight java %}
Constant Story "ITP";
Constant Headline
            "^A simple Inform example
             ^A story about ITP
	     ^by Daniel Shiffman.^
	     ^Programming from A to Z, Spring 2006^
	     ^Special thanks to the examples of Nick Monfort, Roger Firth, and Sonja Kesserich^";
{% endhighlight %}
<p>The &#8220;Story&#8221; and &#8220;Headline&#8221; are displayed to the user when the game begins (if you didn&#8217;t declare and define them, nothing would appear.)   A ^ inside a string is a metacharacter that indicates new line.</p>
<p><b>Including Libraries</b><br />
It&#8217;s also necessary to import the appropriate libraries, using an &#8220;include&#8221; statement.  &#8220;Parser&#8221; and &#8220;Verblib&#8221; are the fundamental components of the Inform parser library.  Nothing will work without including them!</p>
{% highlight java %}
Include "Parser";
Include "VerbLib";
{% endhighlight %}
<p><b>Objects</b><br />
Everything in your interactive fiction world is an Object.  Objects can be defined with the following elements:</p>
<li class="arrow"><strong>object_id </strong>&#8211; this is essentially your &#8220;variable name&#8221; for the object, i.e. how you refer to it in your source code.</li>
<li class="arrow"><strong>interpreter_name</strong> &#8212; this is the name that the interpreter will display when talking about the object</li>
<li class="arrow"><strong>parent_object</strong> &#8212; if the object has a parent.  For example, the parent of a &#8220;sheep&#8221; object might be a &#8220;meadow&#8221; object, assuming the sheep in your story lives in a meadow.  Generally speaking, &#8220;room&#8221; objects do not have parents and things that are found in a room are &#8220;children&#8221; of room objects.</li>
<li class="arrow"><strong>properties</strong> &#8212; objects can have one or more properties.   For example, an object can have a description property.  This is what would appear when the user types &#8220;look.&#8221;  In the case of a &#8220;room&#8221;, it might have a property like &#8220;w_to&#8221;.  This property would indicate what object is west of a given object.   Properties can deal with events and actions associated with a given object.  What happens when you take an object or listen to an object, etc.?</li>
<li class="arrow"><strong>attributes</strong> &#8212; Attributes are boolean values that describe the state of an object.  A room might have &#8220;light&#8221; or &#8220;~light&#8221; (no light). </li>
<li class="arrow">There&#8217;s more things you can do with objects, but these basic elements will get you started!</li>
<p>&nbsp;<br />
Let&#8217;s look at the basic syntax for describing an object:</p>
{% highlight java %}
Object   object_id "interpreter name" parent_object_id
with     property value,
         property value,
         .
         .
         .
         property value,
has      attribute attribute . . . attribute
{% endhighlight %}
<p>For example, a computer lab in the example <a href="http://shiffman.net/itp/classes/a2z/week08/ITP.inf">ITP.inf</a> is described as follows:</p>
{% highlight java %}
Object  lab "The Computer Lab."
  with  description
          "A mysterious figure in the corner types madly at a keyboard. 
	   To the west is a classroom. You see a printer.",
        e_to lobby,
        w_to four0four,
  has   light;
{% endhighlight %}
<p>Translated the above code reads as follows:   Define an object called &#8220;lab&#8221; that will be displayed as &#8220;The Computer Lab&#8221; to the user.  The object has no parent (it is a room.)  Display this description when the user enters the lab.  The object (room) &#8220;lobby&#8221; is to the east, and &#8220;four0four&#8221; is to the west.  There is light in the lab so the user can see things upon entering.</p>
<p>We could also place an object in the lab.   For example:</p>
{% highlight java %}
Object  student "mysterious figure" lab
  with  description 
           "The mysterious figure appears to be a stressed out student, quietly muttering.",
        name 'student' 'person' 'figure' 'man' 'woman' 'mysterious figure',
        before [;
          Listen: "Paper.  It needs paper.  Find the paper.";
        ],
  has   scenery;
{% endhighlight %}
<p>Note the additional properties.  &#8220;Name&#8221; indicates all the possible valid words that can be used to refer to a given object.  The &#8220;before&#8221; block handles events and actions that occur in association with that object.  If the user types &#8220;Listen to person,&#8221; the interpreter will reply with the above message.    <i>Before</i> indicates we want our code to be executed before the default action taken by the interpreter.  If we wanted something to happen afterwards, we would use <i>after</i>.  This object is also &#8220;scenery&#8221;, meaning it cannot be removed from the parent, nor will the user be made aware of its existence upon entering the room (note the description of the computer lab explicitly mentions the so-called &#8220;mysterious figure.&#8221;)</p>
<p>In some examples, you will see an alternative syntax for creating a parent-child relationship between objects.  The code below is equivalent to above (assuming the &#8220;student&#8221; object appears directly after the &#8220;lab&#8221;).</p>
{% highlight java %}
Object -> student "mysterious figure"
{% endhighlight %}
<p>We  can also use conditional statements inside our objects to handle different events depending on what has occured previously, what is currently in the user&#8217;s inventory, etc.  Here&#8217;s a slightly more advanced object, a &#8220;printer&#8221; which also resides in the computer lab.</p>
{% highlight java %}
Object  printer "printer" lab
  with  description
           "Your standard ol' HP printer.",
 	name 'printer',
        after [; 
	  SwitchOn:
	  if (paper in printer) {
	    Achieved(0);
	    move thesis to printer;
	    "The printer begins to hum.  Beautiful pages emerge.  It's your thesis!";
	  } else {
	    "The printer begins to hum.  
	    A red light blinks and a message reads 'PC Load Letter.'
	    It beeps loudly.  This is rather annoying.";
	  }
	  Receive:
	  if (noun == paper) {
	    if (self has on) {
              Achieved(0);
	      move thesis to printer;
	      "The printer begins to hum.  Beautiful pages emerge.  It's your thesis!";
	    } else {
              "You should probably turn the printer on.";
	    }
	  }
        ],	   
  has	scenery container openable switchable;
{% endhighlight %}
<p>There&#8217;s a lot more going on in this object.  For example, the printer has many attributes.  It is a container, meaning it can hold objects.  It is openable, i.e. it can be opened or closed.  It is switchable, meaning it can be turned on and off.  We are also handling certain types of actions using &#8220;after.&#8221;   When the printer is switched on, if it has paper in it, it prints out a thesis.  If it does not have paper it beeps.  We are also handling the &#8220;Receive&#8221; action.  Here, the keyword <i>noun</i> works like an argument to &#8220;receive,&#8221; holding on to a reference to the object that was actually placed in or on an object.  (We place objects <i>in</i> a &#8220;container&#8221; and <i>on</i> a &#8220;supporter&#8221;).  So, in the case of the printer, if it receives paper, assuming it is on, it prints out the thesis.  If not, it displays a message indicating that the user should consider turning on the printer.  </p>
<p>This example also includes specific tasks that when achieved by the user increase his/her score.   When the thesis is printed, &#8220;Achieved(0)&#8221; calls a function to indicate the first task has been completed.  At the top of the code, we would include the following variables to describe the total number of tasks and respective scores for each task.</p>
{% highlight java %}
Constant NUMBER_TASKS = 3;
Constant MAX_SCORE = 3;
Array task_scores -> 1 1 1;
{% endhighlight %}
<p><b>Routines</b><br />
As with any programming language, routines (i.e. functions) will prove to be very useful.  The syntax for writing a function is as follows:</p>
{% highlight java %}
[ function_name argument argument;
  ! whatever code you want
  ! whatever code you want
];
{% endhighlight %}
<p>Some functions are required.  For example, the &#8220;Initialise&#8221; function defines what happens when the work starts up.  </p>
{% highlight java %}
[ Initialise;
   location = lab;   ! the interactor will begin in the lab
];
{% endhighlight %}
<p><b>Grammar and Vocabulary</b></p>
<p>Finally, we can extend the grammar / vocabulary of our world by &#8220;extending&#8221; existing commands or creating our own new ones.  For example, this creates a new command &#8220;about&#8221;:</p>
{% highlight java %}
Verb "bloop" * noun                                    -> Bloop;

[ BloopSub; 
  print "Way to bloop!";
];
{% endhighlight %}
<p>A verb &#8220;bloop&#8221; is defined.  &#8220;noun&#8221; indicates that you need to bloop <i>something</i>.  &#8220;-> Bloop&#8221; indicates that the verb should call the subroutine &#8220;BloopSub&#8221; which simply prints out a message. . </p>
