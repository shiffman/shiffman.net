---
title: Single Parent Ecosystem
author: Daniel
layout: post
dsq_thread_id:
  - 249553888
  - 249553888
pvc_views:
  - 485
categories:
  - blog
  - evolution
  - ITP
  - p5
  - teaching_
---
<p><a href="http://shiffman.net/itp/classes/nature/week10_s07/eco"><img src="http://shiffman.net/itp/classes/nature/week10_s07/start.jpg"/>  <img src="http://shiffman.net/itp/classes/nature/week10_s07/later.jpg"/></a><br />
<a href="http://shiffman.net/itp/classes/nature/week10_s07/eco">Simple EcoSystem</a></p>
<p>This week, in <a href="http://shiffman.net/teaching/nature">The Nature of Code</a>, we&#8217;re talking about <a href="http://shiffman.net/teaching/nature/ga">genetic algorithms</a>.  A genetic algorithm is a search technique that involves a simulated population of candidate &#8220;solutions&#8221; (represented by virtual chromosomes) that evolve towards an optimal state.  The process is a computational model of principles from biological evolution, such as selection, inheritance, crossover, and mutation.</p>
<p>One of the more challenging aspects to developing a genetic algorithm is coming up with a good &#8220;fitness function&#8221; for your candidate solutions, i.e. how well does this candidate solve the problem?   Without a good fitness function, you won&#8217;t get anywhere since the function determines the likelihood of reproducing for the next generation.  This is <a href="http://en.wikipedia.org/wiki/Survival_of_the_Fittest">Survival of the Fittest</a> in code.</p>
<p>Nevertheless, as per <a href="http://mitpress.mit.edu/books/FLAOH/cbnhtml/">Flake</a>, a better natural selection catch-phrase might be &#8220;Survival of the Survivors.&#8221;  In the biological world, there is no fitness &#8220;function.&#8221;  The longer you survive, the more likely you are to reproduce.</p>
<p>With that in mind, the above example is a simple evolution simulation, where the simple ability to live longer affords creatures (called &#8220;bloops&#8221;) a greater chance of having a child (the example uses asexual reproduction for simplicity, but could be modified to incorporate two parents).   A bloop&#8217;s DNA determines its size and speed (the larger it is, the slower it moves.)   The bloops wither away and die unless they find food, in which case their strength increases, causing them to they survive longer and hopefully reproduce.  </p>
<p>There&#8217;s nothing inherently interesting about this example (other than as a demonstration of the technique itself) and it yields a fairly obvious result:  </p>
<p>Bloops that are too big can&#8217;t move, don&#8217;t find food, die, and aren&#8217;t very likely to have children.  Bloops that are tiny can move around very quickly, but aren&#8217;t terribly likely to find food either because of their small size.   After about 10 minutes or so of running, the bloops evolve towards a &#8220;midpoint&#8221; where a reasonably sized, reasonably fast family of bloops take over (see above screenshots for &#8220;before&#8221; and &#8220;after.)</p>
<p>Since color is encoded into the genes, you can follow bloops from generation to generation (though it should be noted that color plays no role whatsoever in a bloop&#8217;s ability to survive.)</p>
