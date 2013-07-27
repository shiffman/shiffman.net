---
title: Processing Sudden Motion Sensor Library
author: Daniel
layout: page
dsq_thread_id:
  - 249581975
pvc_views:
  - 12265
---
<p><img src="http://www.shiffman.net/p5/axes.jpg" alt="SMS Axes" class="right"/></p>
<p>Thanks to the open source <a href="http://members.optusnet.com.au/lbramsay/programs/unimotion.html">Unimotion</a> library by <a href="http://members.optusnet.com.au/a1291762/">Lincoln Ramsay</a>, I have developed a java native interface that allows access to the <a href="http://docs.info.apple.com/article.html?artnum=300781">Apple Sudden Motion Sensor</a> available in powerbooks (and macbooks) since 2005.</p>
<p>Download the library <a href="http://www.shiffman.net/p5/libraries/sms/sms.zip">sms.zip</a>.  Source code for unimotion.c and Unimotion.java is contained in the src directory.</p>
<p>Available functions:</p>
<pre lang="java">
// Return x, y, and z values as array
public static int[] getSMSArray();
    
// Return just the X
public static int getSMSX();

// Return just the Y
public static int getSMSY();

// Return just the Z
public static int getSMSZ();
</pre>
<p>Example code:</p>
<pre lang="java">
import sms.*;

void setup() {
  size(200,200);
}

void draw() {  
  int[] vals = Unimotion.getSMSArray();
  println(vals[0] + " " + vals[1] + " " + vals[2]);
}
</pre>
<p><b>History of Motion</b></p>
<li class="arrow">Written by Christian Klein</li>
<li class="arrow">JNI Implementation of motion by Dave Chatting</li>
<li class="arrow">Modified for iBook compatibility by Pall Thayer</li>
<li class="arrow">Modified for Hi Res Powerbook compatibility by Pall Thayer</li>
<li class="arrow">Modified for MacBook Pro compatibility by Randy Green</li>
<li class="arrow">Disparate forks unified into UniMotion by Lincoln Ramsay</li>
<li class="arrow">Made into a Java Native Interface by Daniel Shiffman</li>
