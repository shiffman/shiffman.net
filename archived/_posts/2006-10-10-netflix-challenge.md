---
title: Netflix Challenge
author: Daniel
layout: post
dsq_thread_id:
  - 249553704
pvc_views:
  - 482
categories:
  - blog
  - netflix
  - programming
---
<p>Netflix recently released 100 million movie rating records as part of a <a href="http://netflixprize.com">contest</a> to improve its movie recommendation system.  </p>
<p><strong>The problem:</strong></p>
<p>I know how I rated a whole bunch of movies.  I know how everyone else has rated a whole bunch of movies.   For any given movie that I have not yet rated (but others have), predict how I would rate it based on my and everyone else&#8217;s rating history.  Netflix uses the root mean squared error (RMSE) to evaluate results.  In other words, let&#8217;s guess that I would give the movie <a href="http://www.imdb.com/title/tt0087957/">Purple Rain</a> a rating of 5, when in reality, I would only rate it a 4.  And let&#8217;s also guess that I would rate <a href="http://www.imdb.com/title/tt0045152/">Singin&#8217; in the Rain</a> a 3.5 when my true rating is a 5.  Here&#8217;s how we would calculate the RMSE:</p>

{% highlight java %}

Purple Rain Prediction Error:  5 - 4 = 1
Singin' in the Rain Prediction Error: 3.5 - 5 = -1.5

Squaring each error:  1*1 = 1, -1.5*-1.5 = 2.25
Add the squares of all errors together = 3.25

MSE = Sum of Squares divided by Total Guesses = 3.25 / 2 = 1.625

RMSE = square root of MSE = sqrt(1.625) = 1.275

{% endhighlight %}

<p>Let&#8217;s take a simple algorithm to solve the problem: for any user rating any movie, predict a future rating as the global average rating for that movie.  This algorithm produces an RMSE of 1.05, not too shabby.  The RSME for Netflix&#8217;s Cinematch system (which presumably employs <a href="http://en.wikipedia.org/wiki/Collaborative_filtering">collaborative filtering</a> techniques) is around 0.95, a mere 10% improvement.   The problem is indeed a difficult one.   Netflix will award a one million dollar prize to anyone who can improve the system by an additional 10%.</p>
<p>I submitted my first prediction file today, mostly as a test, nowhere near the <a href="http://www.netflixprize.com/leaderboard">leaderboard</a>, with the following algorithm:</p>
<p>A customer C will rating a movie M based on the following function:</p>
<p>rating(C,M) = 0.5 * (the global netflix average rating for movie M) + 0.5 * (the customer&#8217;s average rating)</p>
<p>My RMSE?  </p>
<blockquote><p>
Your prediction file submitted 2006-10-10 21:31:56 has been decompressed and processed.<br />
The computed RMSE for the quiz subset was 1.0147.
</p></blockquote>
<p>More to come. . . </p>
