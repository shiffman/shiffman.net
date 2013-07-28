---
title: Generative Text
author: Daniel
layout: page
dsq_thread_id:
  - 249851006
pvc_views:
  - 9449
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#random">Basic Probability</a></li>
<li class="arrow"><a href="#markov">Markov Chains and N-Grams</a></li>
<li class="arrow"><a href="#grammar">Grammars</a></li>
<li class="arrow"><a href="#lsystem">LSystems</a></li>
<li class="arrow"><a href="#ga">Genetic Algorithms</a></li>
</div>
<h2>Examples:</h2>
<li class="arrow">All the code as an Eclipse project zip: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/a2z_week8.zip">a2z_week8.zip</a> </li>
<li class="arrow">Updates on CVS: /home/dts204/a2z/examples/</li>
<h2>Related</h2>
<li class="arrow">A lot of the code and discussion below is based on examples from <a href="http://mrl.nyu.edu/~dhowe/">Daniel Howe</a> Check out: <a href="http://www.rednoise.org/rita/">RiTa site</a> and <a href="http://www.rednoise.org/pdal/">Programming for Digital Art &#038; Literature</a>.</li>
<li class="arrow"><a href="http://googleresearch.blogspot.com/2006/08/all-our-n-gram-are-belong-to-you.html">Google N-Grams!!</a></li>
<li class="arrow"><a href="http://www.cs.princeton.edu/courses/archive/spr05/cos126/assignments/markov.html">Markov Models of Natural Language</a></li>
<li class="arrow"><a href="http://chomsky.info/articles/195609--.pdf">Three Models for the Description of Language (Chomsky)</a></li>
<p>&nbsp;<br />
Remember the ol&#8217; <a href="http://shiffman.net/projects/shakespearemonkeyhome/">monkey typing at a keyboard problem</a>? </p>

{% highlight java %}
 String monkey = "";
 for (int i = 0; i < 20; i++) {
     int n = (int) (Math.random()*127);
     char c = (char) n;
     monkey += c;
 }
 System.out.println(monkey);
{% endhighlight %}

<p>There, a monkey-ish program.   Although we sure do all love monkeys, this week is dedicated to creating text via generative methods ever so slightly more sophisticated than the randomly typing monkey.  </p>
<p><a name ="random"></a></p>
<h2>Probability and Chance Operations</h2>
<p>A true monkey types totally at random, meaning there is an equal probability that any key on the keyboard will be hit at any given time.  What if we could generate our own custom probability map for a keyboard (or perhaps a sequence of word tokens)?  What kind of results could we achieve?  Some interesting examples of text generated via this type of methodology are <a href="http://www.beardofbees.com/gnoetry.html">Gnoetry</a> and <a href="http://www.eskimo.com/~rstarr/poormfa/travesty.html">Travesty</a>.</p>
<p>Before we dive into code, it might be useful to review some probablility basics.   You might look at this <a href="http://shiffman.net/teaching/the-nature-of-code/week-1/">handout for the nature of code course</a>, as well as <a href="http://www.peterwebb.co.uk/probability.htm">The Laymanâ€™s Guide to Probability</a> and this page from <a href="http://library.thinkquest.org/20991/alg2/prob.html">Math for Morons like Us</a>. </p>
<p>Let's examine the following example, where letters are more likely to appear in alphabetical sequence than not.  This won't necessarily produce interesting results, but it will demonstrate the basics.  First we create a class to represent each letter.</p>

{% highlight java %}
public class Letter {

    char c;
    float[] probs;

    public Letter(char c_) {
        c = c_;
        probs = new float[127]; // We are allowing for 127 other possibilities
    }
}
{% endhighlight %}

<p>Note each letter object has a character as well as an array that holds values for the probability that any given character (ASCII code 0-126) will follow that letter.  An array is very convenient to use here because characters can be considered as integers and therefore function  in a dual role as a letter as well as an index in the array.</p>
<p>Now, for each letter, we simply want to fill that probability array.  We&#8217;re doing it in a highly arbitrary way as a demonstration in this example:</p>

{% highlight java %}
for (int i = 0; i < probs.length; i++) {
    // only considering lower case letters
    if (i > 96 &#038;&#038; i < 123) {
        // if it's  before in the alphabet
        if (i <= c) probs[i] = 0.01f;
        // if it's within 4 letters after
        else if (i-c < 4) probs[i] = (float) (4-(c-i))*10f;
        // anything else
        else probs[i] = 0.1f;
    }
    else {
        probs[i] = 0.0f;
    }
}
{% endhighlight %}

<p>This just fills the array with numbers, it's best to then normalize these values (i.e. divide each one by the sum of all values) so that the array of probabilities adds up to 100%.</p>

{% highlight java %}
// Divide each value by the sum of all values
public void normalize(float[] arr) {
  float total = 0;
  for (int i = 0; i < arr.length; i++) {
      total += arr[i];
  }
  for (int i = 0; i < arr.length; i++) {
      arr[i] /= total;
  }
}
{% endhighlight %}

<p>Finally, we can build a sentence  letter at a time according to these probabilities.   One way we can accomplish this result is via a process of picking two random numbers:</p>
<li class="arrow">1 -- pick a random character (0 - 126)</li>
<li class="arrow">2 -- pick a random floating point value (0 - 1.0)</li>
<li class="arrow">3 -- look up the probability of the character picked in step #1 in the probability table (array)</li>
<li class="arrow">4 -- if the random number picked in step #2 is less than the probability found in step #3, then we have found our next letter and we are all set</li>
<li class="arrow">5 -- if it is not, go back to step 1 and start over again.</li>
<p>&nbsp;<br />
(If this seems confusing, consider a situation with only two possible letters, 'a' with a probability of 0.9 and 'b' with a probability of 0.1, we can see that the above method would yield us picking 'a' 9 times as often.)</p>

{% highlight java %}
// An algorithm for picking the next character based on probability map
public char pickNext() {
    // Have we found one yet
    boolean foundone = false;
    int hack = 0;  // let's count just so we don't get stuck in an infinite loop by accident
    while (!foundone &#038;&#038; hack < 10000) {
        // Pick two random numbers
        float r1 = (float) Math.random();
        float r2 = (float) Math.random();
        int index = (int) (r1*probs.length); // random spot in the array
        // If the second random number is less than the probability in the array
        // Then we've found an OK answer
        if (r2 < probs[index]) {
            foundone = true;
            return (char) index;
        }
        hack++;
    }
    // Hack in case we run into a problem (need to improve this)
    return ' ';
}
{% endhighlight %}

<p>Code: <a href="http://shiffman.net/itp/classes/a2z/week09/Letter.java">Letter.java</a>, <a href="http://shiffman.net/itp/classes/a2z/week09/ProbDriver.java">ProbDriver.java</a>, full source: <a href="http://shiffman.net/itp/classes/a2z/week09/prob.zip">prob.zip</a>.</p>
<p><a name ="markov"></a></p>
<h2>Markov Chains and N-Grams</h2>
<p>A <a href="http://en.wikipedia.org/wiki/Markov_chain">Markov Chain</a> can be described as a sequence of random "states" where each new state is conditional only on the previous state.   An example of a Markov Chain is monopoly.   The "next" state of the monopoly board depends on the current state and the roll of the dice.  It doesn't matter how we got to that current state, only what it is at the moment.  A game like blackjack, for example, is different in that the deal of the cards is dependent on the history of many previous deals (assuming a single-deck not continuously shuffled.)    <a href="http://www.bewersdorff-online.de/amonopoly/">Monopoly as Markov Chain</a>.</p>
<p>We can use a markov chain to generate text where each new word or character is dependent on the previous word (or character) or sequence of words (or characters).   This is known as an N-gram model.  An N-gram model for language predicts a word (or character) W[i] based on the previous sequence W[i-2] W[i-1], etc.    Given the phrase "I have to" we might say the next word is 50% likely to be "go", 30% likely to be "run" and 20% likely to be "pee."    We can construct these word sequence probabilities based on a large corpus of source texts.   The following function, for example, displays all n-grams for a given String (of order N):</p>

{% highlight java %}
public static ArrayList nGrams (String sentence,int n) {
  // Split sentence up into words
  String[] words = sentence.split("\W+");
  // Total number of n-grams we will have
  int total  = words.length - n;
  ArrayList grams = new ArrayList();
  // Loop through and create all sequences
  for(int i=0;i< =total;i++) {
    String seq = "";
    for (int j = i; j < i+n; j++) {
      seq += words[j] + " ";
    }
    // Add to ArrayList
    grams.add(seq);
  }
  return grams;
}
{% endhighlight %}

<p>Developing the full statistical model involves a node-based data structure (see: <a href="http://www.rednoise.org/pdal/index.php?n=Main.N-Grams">Daniel Howe's Markov page</a>).  For us, since this functionality is built into the <a href="http://www.rednoise.org/rita/documentation/index.htm">RiTa</a> Library, we can get it for free.   </p>
<p>A quick note about using <a href="http://www.rednoise.org/rita/documentation/index.htm">RiTa</a> in the context of our class.   RiTa is designed as a <a href="http://www.processing.org">Processing</a> library and thus assumes the existence of a PApplet when generating objects.  This is because RiTa also includes functionality to draw text on the screen.   Although you may ultimately use RiTa in Processing for projects that require visuals, these examples will remain simple Java programs with console output only.  This is why you will see the use of "null" in this code instead of "this".  Since we are not a PApplet, we cannot pass ourselves into the RiTa objects and so we just say null.   "core.jar" will still have to be part of the build path and you must put certain files (such as "rita_addenda.txt") in a folder called "data."   Other bugs may arise with RiTa outside of Processing and we'll discover these as we go. . .</p>
<p>Ok, now onto building a Markov model and generating sentences based on that model in RiTa.  First we must declare a <a href="http://www.rednoise.org/rita/documentation/rimarkov_class_rimarkov.htm">RMarkov</a> object.</p>

{% highlight java %}
// Create a new markov model with n=3
RiMarkov markov = new RiMarkov(null, 3);
{% endhighlight %}

<p>Once we have the object, we can pass it text from which to build its model:</p>

{% highlight java %}
A2ZFileReader fr = new A2ZFileReader("plays/hamlet.txt");
markov.loadString(fr.getContent());
{% endhighlight %}

<p>We can even pass it multiple texts with weights for those texts.</p>

{% highlight java %}
fr = new A2ZFileReader("plays/seagull.txt");
markov.loadString(fr.getContent(),3);
{% endhighlight %}

<p>The above code is the equivalent of adding Chekov&#8217;s <i>The Seagull</i> three times into the model.   Once the model is built we can generate sentences:</p>

{% highlight java %}
// Generate 8 lines
String[] lines = markov.generate(8);
System.out.println("Here are 8 generated lines: + n");
for (int i = 0; i < lines.length; i++) {
  System.out.println(lines[i]);
}
{% endhighlight %}

<p>And here's a sample result:</p>

{% highlight java %}
Nonsense, Matriona will feed it.
It is always either vodka or brandy.
Yet I am sorry to leave.
You should not handle youthful egoism so roughly, sister.
What did I hurt my poor boy?
No, indeed, are ambition; for the first day.
Yes, they are singing across the water.
It is like a beggar beneath your window.
{% endhighlight %}

<p>Here&#8217;s another great example, direct from our class: <a href="http://itp.nyu.edu/~ap1607/cgi-bin/thesis_title">Thesis Title Generator</a> by Adam Parrish.</p>
<p>Examples: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/markov/Markov.java">Markov.java</a>, <a href="http://shiffman.net/itp/classes/a2z/week09/2008/markov/Ngram.java">Ngram.java</a></p>
<h2>Context Free Grammars (CFGs)</h2>
<p><a name="grammars"></a><br />
From <a href="http://en.wikipedia.org/wiki/Grammar">Wikipedia</a>:  &#8220;Grammar is the study of the rules governing the use of a given natural language, and, as such, is a field of linguistics. Traditionally, grammar included morphology and syntax; in modern linguistics these subfields are complemented by phonetics, phonology, semantics, and pragmatics.&#8221;  </p>
<p>A <a href="http://en.wikipedia.org/wiki/Context-free_grammar">Context-Free Grammar</a> is a set of rules that define the syntax of a language &#8212; what is and is not a valid sequence of &#8220;tokens&#8221;.   Programming languages, for example, are context-free grammars &#8212; a compiler reads your code to make sure it conforms to specific rules and informs you of any errors.   (These rules aren&#8217;t limited to the production of text, and can be used for music, images, etc.)     A context-free grammar, however, isn&#8217;t robust enough to describe the complexities of natural language.   After all, they have no context!   Natural languages can be described using <a href="http://en.wikipedia.org/wiki/Context-sensitive_grammar">Context-sensitive grammars</a>, a concept introduced by <a href="http://en.wikipedia.org/wiki/Noam_Chomsky">Chomsky</a> in the 50s.</p>
<p>See <a href="http://www.rednoise.org/pdal/index.php?n=Main.Grammars">Daniel Howe&#8217;s</a> page on grammars for a fuller explanation (from which this section is adapted).</p>
<p>For our purposes here, we want to learn how to define our own context free grammars and generate text from them.  I&#8217;m going to give a short explanation here, followed by code examples.  However, again, <a href="http://www.rednoise.org/pdal/index.php?n=Main.Grammars">Daniel Howe&#8217;s</a> page contains more detailed explanation.</p>
<p>All &#8220;production rules&#8221; in a context-free grammar are of the form:</p>
<p><b>V &#8211;> w</b></p>
<p>where</p>
<p><b>V</b> is a single &#8220;non-terminal&#8221;<br />
and<br />
<b>w</b> is a sequence of &#8220;terminals&#8221; and &#8220;non-terminals&#8221;</p>
<p>A non-terminal symbol is simply another symbol which needs to be defined in another production rule.  A terminal symbol does not need to be defined because that&#8217;s it, you&#8217;ve reached the end, what is here is what should be in the sentence itself.  Here&#8217;s an incredibly simple CFG.</p>

{% highlight java %}
s -> a b
a -> 'the'
b -> c 'cat'
c -> 'happy'
c -> 'sad'
{% endhighlight %}

<p>Anything in single quotes is a &#8220;terminal&#8221; element, meaning this is the end, this is the word/string we can use.  In the above &#8220;cat,&#8221; &#8220;happy,&#8221; and &#8220;sad&#8221; are all terminals.     Anything that is not in quotes is non-terminal, or a &#8220;symbol.&#8221;  The abve example has 4 symbols &#8212; s,a,b,c.    The symbol &#8220;s&#8221; is commonly used to indicate &#8220;start&#8221; or &#8220;sentence.&#8221;     Notice how in the above set of rules the symbol c can be &#8220;happy&#8221; or &#8220;sad.&#8221;   We can use an OR (pipe character) to combine these two rules:</p>

{% highlight java %}
c -> 'happy' | 'sad'
{% endhighlight %}

<p>Again, this grammar is incredibly basic, and is just for demonstrating how the elements work.  The only two valid &#8220;sentences&#8221; that conform to this grammar are:</p>

{% highlight java %}
the happy cat
the sad cat
{% endhighlight %}

<p>The process of generating the above two sentences goes something like:</p>
<p><b>s</b> becomes <b>a b</b> which becomes <b>the <i>c</i> cat</b> which then becomes <b>the happy cat</b> or <b>the sad cat</b>.     Here, either &#8220;happy&#8221; or &#8220;sad&#8221; is picked randomly (with a 50% chance for each one.)</p>
<p>The RiTa library supports CFGs through the use of a grammar file.  The format is different, instead of writing:</p>
<p><b>V &#8211;> w</b></p>
<p>you write:</p>

{% highlight java %}
&lt;b&gt;
{
  &lt;V&gt;
  &lt;w&gt;
}

or
{
  &lt;V&gt;
   w
}
{% endhighlight %}

<p>depending on whether w is a symbol &#8212; <w> &#8212; or word &#8212; w.</p>
<p>Our grammar is therefore:</p>

{% highlight java %}
{
 &lt;start&gt;
 &lt;a&gt; &lt;b&gt;
}

{
  &lt;a&gt;
  the
}

{
  &lt;b&gt;
  &lt;c&gt; cat
}

{
  &lt;c&gt;
  happy | sad
}
{% endhighlight %}

<p>Here are links to two examples from Daniel Howe&#8217;s site:  <a href="http://www.rednoise.org/pdal/uploads/grammar.g">Another simple grammar</a>, <a href="http://www.rednoise.org/rita/examples/haiku.g">Haiku grammar</a>.</p>
<p>Once you have a grammar file, you can use RiTa to generate text.  Here&#8217;s how it works.</p>
<p>First you create the <a href="http://www.rednoise.org/rita/documentation/rigrammar_class_rigrammar.htm">RiGrammer</a> object, referencing a PApplet (or in our case: null) and the grammar file (which should be placed in a folder named &#8220;data&#8221;):</p>

{% highlight java %}
RiGrammar grammar = new RiGrammar(null, "simple.g");
{% endhighlight %}

<p>The <a href="http://www.rednoise.org/rita/documentation/rigrammar_class_rigrammar.htm">expand()</a> method is used to generate the text:</p>

{% highlight java %}
String text = grammar.expand();
System.out.println(text);
{% endhighlight %}

<p>RiTa uses &#8220;start&#8221; as the default first non-terminal symbol.   However, the methods expandFrom() and expandWith(), described <a href="http://www.rednoise.org/rita/documentation/rigrammar_class_rigrammar.htm">here</a> allow for more advanced functionality. </p>
<p>In addition, RiTa will assign equal probabilities to all choices in a given sequence, however, you can assign different weights to different choices using the following syntax:</p>

{% highlight java %}
   {
     &lt;a&gt;
     [2] b | c
   }
{% endhighlight %}

<p>In the above grammer, &#8220;b&#8221; is twice as likely to be selected as &#8220;c&#8221;.</p>
<p>Code: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/grammar/CFG.java">CFG.java</a>.  Grammar file: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/data/simple.g">simple.g</a>.</p>
<p>Finally, a grammar file is something we can generate ourselves as well based on some algorithm.  For example, using <a href="http://www.rednoise.org/rita/examples/haiku.g">this haiku grammar file from Daniel Howe</a>, we could read in a source text, analyze the syllable counts of all the words, and rewrite the grammar file to reflect these new words.  Here is a version of this grammar file using words from an Obama speech: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/data/generated_grammar.g">generated_grammar.g</a>.</p>
<p>Writing the grammar file is easy using our A2ZFileWriter class:</p>

{% highlight java %}
A2ZFileWriter fw = new A2ZFileWriter("data/generated_grammar.g");
fw.appendLine("{");
fw.appendLine("&lt;start&#038;t;");
fw.appendLine("&lt;5-line> % &lt;7-line&gt; % &lt;5-line&gt;");
fw.appendLine("}n");
{% endhighlight %}

<p>Here is the full source: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/grammar/GrammarMaker.java">GrammarMaker.java</a>.  Note the use of a Concordance to get all the words from a source file as well as RiTa&#8217;s Analyzer object to count syllables.</p>
<p><a name ="lsystem"></a></p>
<h2>L-Systems</h2>
<p><a href="http://en.wikipedia.org/wiki/Lindenmayer_system">L-Systems</a> were developed by <a href="http://en.wikipedia.org/wiki/Aristid_Lindenmayer">Aristid Lindenmayer</a> in 1968 as a means for modeling plant cell structure.  L-Systems, although used primarily to generate graphics (often resulting in <a href="http://en.wikipedia.org/wiki/Fractal">fractals</a>), are based on a formal grammar, consisting of an alphabet, a set of rules, and an axiom (the starting sentence).    An L-System can be generated with a CFG, as demonstrated in <a href="http://www.rednoise.org/rita/examples/LSysCFGEx1/">example</a> by Daniel Howe.  Here, we&#8217;ll demonstrate how to generate an L-System without CFGs and the RiTa library.</p>
<li class="arrow">Alphabet: A,B,C</li>
<li class="arrow">Rules: A &rarr; AB, B &rarr;  AA, C &rarr;  BC</li>
<li class="arrow">Axiom: ABC</li>
<p>&nbsp;<br />
We can then generate the L-System by applying the rules iteratively, beginning with the axiom, i.e.:</p>

{% highlight java %}
Generation #0: ABC
Generation #1: ABAABC
Generation #2: ABAAABABAABC
Generation #3: ABAAABABABAAABAAABABAABC
Generation #4: ABAAABABABAAABAAABAAABABABAAABABABAAABAAABABAABC
Generation #5: ABAAABABABAAABAAABAAABABABAAABABABAAABABABAAABAAABAAABABABAAABAAABAAABABABAAABABABAAABAAABABAABC
{% endhighlight %}

<div class="nohigh"><a href="http://shiffman.net/itp/classes/a2z/week09/lsystem/"><img src="http://shiffman.net/itp/classes/a2z/week09/lsystem.jpg" class="right"/></a></div>
<p>To generate imagery from an L-System, the sentences are deciphered as directions for a <a href="http://en.wikipedia.org/wiki/Logo_programming_language">turtle graphics</a> engine.  For example:</p>
<li class="arrow">Alphabet: F (go forward), + (turn right), &#8211; (turn left), [ (save current location), ] (return to last saved location)</li>
<li class="arrow">Rules: F &rarr; F[F]-F+F[--F]+F-F</li>
<li class="arrow">Axiom: F-F-F-F</li>
<p>&nbsp;<br />
The <a href="http://shiffman.net/itp/classes/a2z/week09/lsystem/">applet</a> to the right visualizes the result.</p>
<p>Let&#8217;s look a bit at how the code is implemented.  Our program will involve two classes, <a href="http://shiffman.net/itp/classes/a2z/week09/LSystem.java">LSystem.java</a> and <a href="http://shiffman.net/itp/classes/a2z/week09/Rule.java">Rule.java</a>.   </p>
<p>The LSystem class includes a String for the current sentence, an int to keep track of the total # of generations, and an array of Rule objects to describe the rules of the system.</p>

{% highlight java %}
public class LSystem {

    private String sentence;     // The sentence (a String)
    private Rule[] ruleset;      // The ruleset (an array of Rule objects)
    private int generation;      // Keeping track of the generation #

    // Construct an LSystem with a startin sentence and a ruleset
    public LSystem(String axiom, Rule[] r) {
        sentence = axiom;
        ruleset = r;
        generation = 0;
    }
{% endhighlight %}

<p>The rather simple Rule class includes a character that is matched and a replacement String, i.e.</p>

{% highlight java %}
public class Rule {
    private char match;
    private String replace;

    public Rule(char a_, String b_) {
        match = a_;
        replace = b_;
    }

    public char getMatch() {
        return match;
    }

    public String getReplace() {
        return replace;
    }
}
{% endhighlight %}

<p>The main work is found in the function that generates a new String based on the existing String by matching characters in the ruleset and replacing them accordingly.  The pseudo-code goes something like:</p>
<p>1. Create an empty StringBuffer (A <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/StringBuffer.html">StringBuffer</a> is faster than a old fashioned String when it comes to appending.)<br />
2. For every character in the current String:</p>
<li class="arrow">Loop through all rules and see if it matches one</li>
<li class="arrow">If it does, append the replacement String to the StringBuffer</li>
<li class="arrow">If it does not, append itself to the StringBuffer</li>

{% highlight java %}
some weird bug is preventing me from pasting the code here, visit: <a href="http://shiffman.net/itp/classes/a2z/week09/LSystem.java">LSystem.java</a> and look for the generate() method.
{% endhighlight %}

<p>Code: <a href="http://shiffman.net/itp/classes/a2z/week09/LSys.java">LSys.java</a>, <a href="http://shiffman.net/itp/classes/a2z/week09/LSystem.java">LSystem.java</a>, <a href="http://shiffman.net/itp/classes/a2z/week09/Rule.java">Rule.java</a>,full source: <a href="http://shiffman.net/itp/classes/a2z/week09/lsystem.zip">lsystem.zip</a>.</p>
<p><a name ="ga"></a></p>
<h2>Genetic Algorithms</h2>
<p>We can also accomplish some interesting results generating text via genetic algorithms.  For example, what if a String of characters is considered DNA?  For examples and explanation, visit the <a href="http://shiffman.net/teaching/the-nature-of-code/ga/">nature of code GA handout</a>.</p>
<p>Code: <a href="http://shiffman.net/itp/classes/a2z/week09/2008/genetic/DNA.java">DNA.java</a>, <a href="http://shiffman.net/itp/classes/a2z/week09/2008/genetic/Population.java</a>Population.java</a>, <a href="http://shiffman.net/itp/classes/a2z/week09/2008/genetic/GAShakespeare.java">GAShakespeare.java</a>.<br />
</w></p>
