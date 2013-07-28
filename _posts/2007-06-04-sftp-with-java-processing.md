---
title: Sftp with Java / Processing
author: Daniel
layout: post
dsq_thread_id:
  - 249554029
pvc_views:
  - 4141
categories:
  - java
  - library
  - p5
  - processing.org
  - programming
  - sftp
---
<p>I know, I know, you&#8217;ve been waiting your whole life for this.</p>
<p><a href="http://www.shiffman.net/p5/libraries/sftp/sftp.zip">Download beta SFTP Processing library</a> (source and example included in zip).</p>
<p>The library uses <a href="http://www.jcraft.com/jsch/">JSch (Java Secure Channel)</a>.<br />
Copyright (c) 2002,2003,2004,2005,2006,2007 Atsuhiko Yamanaka, JCraft, Inc.</p>
{% highlight java %}
import sftp.*;

Sftp sftp;

void setup() {
  size(200,200);
  background(0);
  // Create the SFTP object
  // if 3rd arg = false, you must set the password in your code
  // if 3rd arg = true, you will be prompted to enter your password
  sftp = new Sftp("www.hostname.com","login", true);
  // sftp.setPassword("XXXXXX");
  sftp.start(); // start the thread
  noLoop();
}

void mousePressed() {
  // At any point you can execute an SFTP command 
  // Not all commands are currently implemented
  // but you do have "ls" and "get"
  // Gosh, I should implement "put", sorry!
  sftp.executeCommand("ls");
  sftp.executeCommand("get file.txt");
}
{% endhighlight %}
