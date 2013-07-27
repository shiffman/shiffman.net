---
title: 'Night #2: Fade Sound In and Out Using Minim'
author: Daniel
layout: post
pvc_views:
  - 4326
dsq_thread_id:
  - 512355451
dsq_needs_sync:
  - 1
categories:
  - General
tags:
  - minim
  - pcomp
  - processing.org
  - sound
---
<p>I made this example earlier in the semester after seeing countless projects with the following functionality: turning a sound on when a sensor reading reaches a given level, turning the sound off when a sensor reading falls below a certain level.  Most the projects used Minim for sound playback and pause() and play() to start and stop a sound, along with rewind() to send the sound back to the beginning.  While this does work, I find a more effective strategy is to fade a sound in and out using shiftGain().</p>
<p>Now this is a fairly simple problem if you can pinpoint the moment a sound should fade in.  For example, let&#8217;s say you want it to happen when the mouse is clicked.</p>
<pre lang="java">
void mousePressed() {
  player.shiftGain(-80, 13, 1000); 
}
</pre>
<p>The above line of code will fade the sound over 1,000 milliseconds (i.e. 1 second) from a decibel level of -80 (inaudible) to 13 (some vaguely loud level.)</p>
<p>If you put this code in draw(), however, you&#8217;ve got a problem.</p>
<pre lang="java">
void draw() {
  if (sensorVal > threshold) {
    player.shiftGain(-80, 13, 1000); 
  }
}
</pre>
<p>You can&#8217;t call shiftGain() over and over again!  You want this to happen just once, the moment the sensor value reaches that threshold.  Introducing a boolean is a quick way to solve this problem.  If you set the boolean to true when the sound is fading and only call shiftGain() when the boolean is set to false, you&#8217;ve now got only one call to the function.</p>
<pre lang="java">
boolean fade = false;

void draw() {
  if (sensorVal > threshold &#038;&#038; !fade) {
    player.shiftGain(-80, 13, 1000); 
    fade = true;
  }
}
</pre>
<p>The question remains: when does fade get set back to false?  One likely solution is to reset the boolean when the sensor value falls below the threshold, i.e.</p>
<pre lang="java">
  if (sensorVal > threshold &#038;&#038; !fade) {
    player.shiftGain(-80, 13, 1000); 
    fade = true;
  } else if (sensorVal < threshold) {
    fade = false;
  }
</pre>
<p>Following is an example that does this with a double threshold (fading up above a value and fading down below a value).  In addition, it offers some other improvements (such as having the sound fade from its current gain level). The mouseX location is the stand-in for a sensor value.</p>
<p><a href="http://www.shiffman.net/wp/wp-content/uploads/2011/12/SoundFade.zip"><img src="http://www.shiffman.net/wp/wp-content/uploads/2011/12/soundfade.png" alt="" title="soundfade" width="400" height="239" class="alignnone size-full wp-image-979" /></a></p>
<p>Source: <a href="http://www.shiffman.net/wp/wp-content/uploads/2011/12/SoundFade.zip">SoundFade.zip</a></p>
