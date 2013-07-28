---
title: E-mail Processing
author: Daniel
layout: post
dsq_thread_id:
  - 249554100
pvc_views:
  - 11187
categories:
  - blog
  - email
  - java
  - p5
  - processing.org
---
<p>While it may not be nearly as kooky as <a href="http://shiffman.net/p5/asterisk">calling Processing on the phone</a>, I&#8217;ve been asked about checking e-mail from Processing several times this semester.  So rather than try to dig up example code on the internets, I&#8217;ve quickly thrown together one that checks a <a href="http://en.wikipedia.org/wiki/Post_Office_Protocol">POP account</a> and/or sends mail via <a href="http://en.wikipedia.org/wiki/Smtp">SMTP</a>.  It&#8217;s all done with <a href="http://en.wikipedia.org/wiki/Smtp">Javamail</a>.</p>
<p><a href="http://shiffman.net/p5/Email.zip">Download the example sketch.</a></p>
<p>Code snippets after the jump. . .</p>
<p><!--more--></p>
{% highlight java %}
    Properties props = System.getProperties();

    props.put("mail.pop3.host", "pop.gmail.com");
    
    // These are security settings required for gmail
    // May need different code depending on the account
    props.put("mail.pop3.port", "995");
    props.put("mail.pop3.starttls.enable", "true");
    props.setProperty("mail.pop3.socketFactory.fallback", "false");
    props.setProperty("mail.pop3.socketFactory.class","javax.net.ssl.SSLSocketFactory");

    // Create authentication object
    Auth auth = new Auth();
    
    // Make a session
    Session session = Session.getDefaultInstance(props, auth);
    Store store = session.getStore("pop3");
    store.connect();
    
    // Get inbox
    Folder folder = store.getFolder("INBOX");
    folder.open(Folder.READ_ONLY);
    System.out.println(folder.getMessageCount() + " total messages.");
    
    // Get array of messages and display them
    Message message[] = folder.getMessages();
    for (int i=0; i < message.length; i++) {
      System.out.println("---------------------");
      System.out.println("Message # " + (i+1));
      System.out.println("From: " + message[i].getFrom()[0]);
      System.out.println("Subject: " + message[i].getSubject());
      System.out.println("Message:");
      String content = message[i].getContent().toString(); 
      System.out.println(content);
    }
    
    // Close the session
    folder.close(false);
    store.close();
{% endhighlight %}
<p>and sending:</p>
{% highlight java %}
  // Create a session
  String host="smtp.gmail.com";
  Properties props=new Properties();

  // SMTP Session
  props.put("mail.transport.protocol", "smtp");
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.port", "25");
  props.put("mail.smtp.auth", "true");
  // We need TTLS, which gmail requires
  props.put("mail.smtp.starttls.enable","true");

  // Create a session
  Session session = Session.getDefaultInstance(props, new Auth());

  try
  {
    // Make a new message
    MimeMessage message = new MimeMessage(session);

    // Who is this message from
    message.setFrom(new InternetAddress("name@gmail.com", "Name"));

    // Who is this message to (we could do fancier things like make a list or add CC's)
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("address@email.com", false));

    // Subject and body
    message.setSubject("Hello World!");
    message.setText("It's great to be here. . .");

    // We can do more here, set the date, the headers, etc.
    Transport.send(message);
    println("Mail sent!");
  }
  catch(Exception e)
  {
    e.printStackTrace();
  }
{% endhighlight %}
