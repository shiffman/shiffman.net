---
title: Bayesian Filtering
author: Daniel
layout: page
dsq_thread_id:
  - 249806676
pvc_views:
  - 10092
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<ul>
<li><a href="#hash">Hash Tables</a></li>
<li><a href="#bayesian">Bayes&#8217; Rule</a></li>
<li><a href="#spam">Spam Filtering using Bayesian Analysis</a></li>
</ul>
</div>
<h2>Examples:</h2>
<ul>
<li><a href="http://www.shiffman.net/itp/classes/a2z/week04/HashMapTest.java">HashMapTest.java</a></li>
<li>simple text analysis example: <a href="http://www.shiffman.net/itp/classes/a2z/week04/simple/a2z.zip">a2z.zip</a></li>
<li>individual files: <a href="http://www.shiffman.net/itp/classes/a2z/week04/simple/TextAnalyzer.java"> TextAnalyzer.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/simple/A2ZFileReader.java">A2ZFileReader.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/simple/A2ZFileWriter.java">A2ZFileWriter.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/simple/WordMap.java">WordMap.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/simple/Word.java">Word.java</a></li>
<li>full bayesian spam filter example: <a href="http://www.shiffman.net/itp/classes/a2z/week04/bayesianspam.zip">bayesianspam.zip</a></li>
<li>individual files: <a href="http://www.shiffman.net/itp/classes/a2z/week04/bayes/Bayesian.java">Bayesian.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/a2z/A2ZFileReader.java">A2ZFileReader.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/bayes/SpamFilter.java">SpamFilter.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week04/bayes/Word.java">Word.java</a></li>
</ul>
<h2>Related:</h2>
<ul>
<li><a href="http://www.paulgraham.com/spam.html">A Plan for Spam</a> by <a href="http://www.paulgraham.com/">Paul Graham</a>.  This essay is also available in the book <a href="http://www.paulgraham.com/hackpaint.html">Hackers and Painters</a>.  Also: <a href="http://www.paulgraham.com/better.html">Better Bayesian Filtering</a> by Graham</li>
<li><a href="http://www.process.com/precisemail/bayesian_filtering.htm">Introduction to Bayesisan Filtering</a> (suggested)
</li>
<li><a href="http://yudkowsky.net/bayes/bayes.html">An Intuitive Explanation of Bayesian Reasoning</a> &#8212; quite long, but truly amazing if you&#8217;re interested in delving deeper into the math behind Bayesian analysis.
</li>
<li>For a Perl implementation: <a href="http://www.ddj.com/documents/s=9698/ddj0505a/0505a.html?temp=7vfW-Dp7KD">Naive Bayesian Text Classification</a> (sorry, you have to register).</li>
<li><a href="http://www.noraproject.org/">The Nora Project</a></li>
</ul>
<p>Our goal for this week is to develop a very simple example of applying <a href="http://en.wikipedia.org/wiki/Bayes%27_theorem">Bayes&#8217; Theorem</a> to text analysis.  The example we will use is modeled after <a href="http://www.paulgraham.com/">Paul Graham&#8217;s</a> &#8220;<a href="http://www.paulgraham.com/spam.html">A Plan for Spam</a>.&#8221;   This example will be demonstrated through storing Word objects in a <a href=http://en.wikipedia.org/wiki/Hash_table">Hash Table</a>, instead of a <a href="http://en.wikipedia.org/wiki/Binary_search_tree">Binary Search Tree</a> (as with <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/concordance">last week&#8217;s concordance example</a>).  We can use a Hash Table here because the order of the words is not relevant to this problem.</p>
<div class ="pullquote">
<b>Big O Notation for Searching Data Structures:</b></p>
<ul>
<li>Linked List: N</li>
<li>Binary Tree: logN</li>
<li>Hash Table: 1</li>
</ul>
</div>
<p><a name ="hash"></a></p>
<h2>Hash Tables</h2>
<p><a href="http://www.shiffman.net/teaching/programming-from-a-to-z/concordance">Last week</a>, we discovered that we could improve on the efficiency of searching through an ordered list of words via <a href="http://www.shiffman.net/teaching/programming-from-a-to-z/concordance/#tree">a binary tree data structure</a>.  This structure provided O(logN) look-up time, a great improvement over O(N) look-up that a simple <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/ArrayList.html">ArrayList</a> or <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/LinkedList.html">LinkedList</a> provided.  We wrote our own Tree class, however, we could have simplified our lives by using the Java <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/TreeMap.html">TreeMap</a>.</p>
<p>Wouldn&#8217;t it be nice if we could search for a word in a data structure and have constant time (i.e. O(1)) look-up for any word?  This is similar to the speed at which we can look-up any element in an array by accessing it via its index.  We only need one computation cycle!  In fact, a hash table does exactly this!  Although we could build our own as we did with the Tree, this time we&#8217;ll use the existing Java <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/HashMap.html">HashMap</a> class.</p>
<p>A hash table provides constant-time look-up of any value via its key.  In our case, the key and the value can essentially be the same (the word we are searching for).  The key is transformed into a numerical value via a hash function.  That number is then used as an index in an array to check if the key exists and/or retrieve the value associated with that key.   Consider a room full of approximately 50 ITP students that we want to put into a Hash Table.  We need to come up with a function that takes the person and converts them into a numerical value.   Let&#8217;s try a few different solutions:</p>
<ul>
<li>HashCode(person) = ASCII value of first letter of first name.</li>
<li>HashCode(person) = last two digits of NYU ID</li>
<li>HashCode(person) = birthday day</li>
<li>HashCode(person) = birthday year</li>
</ul>
<p>Which of the above functions are good hash functions and which are bad?    Birthday year, for example, is a terrible hash function.  If a large number of the students are born in the same year (which they likely will be) then we will end up with the same hashcode for many of the people.  This is what&#8217;s known as a &#8220;Collision.&#8221;   The HashTable can handle collisions by performing a linear search, however, the more collisions, the more our algorithm&#8217;s efficiency will deterioriate.  Imagine if everyone had the same hashcode, we&#8217;d be in no different shape then we would with a LinkedList!</p>
<p>The last two digits of NYU ID, however, would be a much better hash function since we would expect this to be fairly randomized and would give us 100 possibilities (00 &#8211; 99) with relatively few collisions.</p>
<p>Fortunately for us, we don&#8217;t have to worry about writing our own Hash Functions (though this would be an interesting avenue to pursue), we can simply use the HashMap class.  Here&#8217;s how we use a HashMap:</p>
<p>Create an empty one:</p>
<pre lang="java">
HashMap words = new HashMap();
</pre>
<p>Add some words to it (using a String as both the key and value)</p>
<pre lang="java">
String text = "This example demonstrates using a HashMap";  
// Add every word to the hashmap
String[] tokens = text.split("\W");
for (int i = 0; i < tokens.length; i++) {
    String word = tokens[i];
    // Note we are using the same String for both Key and Value
    words.put(word,word);
}
</pre>
<p>Now we can use the <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/HashMap.html#containsKey(java.lang.Object)">containsKey()</a> method to search for a word:</p>
<pre lang="java">    
// Search the hash table for various words
if (words.containsKey("example")) {
  System.out.println("I found the word example!");
} else {
   System.out.println("I did not find the word example!");
}
</pre>
<p>full code: <a href="http://www.shiffman.net/itp/classes/a2z/week04/HashMapTest.java">HashMapTest.java</a></p>
<div class ="pullquote">
<b>Bayes&#8217; Theorem</b><br />
p(A|B) = ( p(B|A)*p(A) ) / ( p(B|A)*p(A) + p(B|~A)*p(~A) )
</div>
<p><a name ="bayesian"></a></p>
<h2>Bayes&#8217; Rule</h2>
<p>Consider the following scenario:</p>
<ul>
<li>1% of all ITP students are afflicted with a rare disease known as ITPosis</li>
<li>There is a test you can take to determine if you have it, known as a TID (Test for Interactive Disease).</li>
<li>90% of all students with ITPosis will receive a positivie TID (i.e. 10% that have the disease will receive a false negative).</li>
<li>95% of students without ITPosis will receive a negative TID (i.e. 5% will receive false positives).</li>
</ul>
<p><b><i>You have received a positive TID, what is the likelihood you have ITPosis?</i></b></p>
<p>Strangely enough, there is a very precise answer to this question and it&#8217;s not what you would expect.  Bayesian reasoning is counter-intuitive and takes quite a bit of getting used to.  In fact, when <a href="http://yudkowsky.net/bayes/bayes.html">given a similar question related to breast cancer and mammograms</a>, only 15% of doctors get the answer correct.  </p>
<p>The answer &#8212; 15.3% &#8212; is calculated via Bayes&#8217; Theorem.  Let&#8217;s look at it again with this scenario:</p>
<ul>
<li>There are 1000 students.</li>
<li>10 of them have ITPosis.</li>
<li>9 of those 10 with the disease will receive a positive TID.</li>
<li>Out of the 990 w/o ITPosis, ~50 will receive positive TIDs.</li>
<li>Therefore, 59 total students receive positive TIDs, 9 of which actually have the disease, 50 do not.</li>
<li>The chance one has the disease if the test is positive is therefore 9 / 58 = 15.5% (off slightly from the exact result b/c of rounding)</li>
</ul>
<p>The problem our brains run into are those rascally 90% and 95% numbers.  90% of students who test positive have the disease and 95% who don&#8217;t test negative, if I test positive, I should probably have it, right?!!  The important thing to remember is that only 1% of students actually have the disease. Sure testing positive increases the likelihood, but because 5% of students without the disease receive a false positive, it only increases the chances to 15%.  All of this is explained in incredibly thorough and wonderful detail in <a href="http://yudkowsky.net/">Eliezer Yudkowsky&#8217;s</a> article <a href="http://yudkowsky.net/bayes/bayes.html">An Intuitive Explanation of Bayesian Reasoning</a>.  My explanation is simply adapated from his.</p>
<p>By the way, we could have calculated it as follows:</p>
<p>P (ITPosis | Positive TID) = (90%*1%) / (90%*1% + 5%*99%).</p>
<p>This reads as &#8220;the probability that a positive TID means you have ITPosis&#8221; equals. . . .</p>
<p>So why do we care?  This type of reasoning can be applied quite nicely to text analysis.   The example we&#8217;ll look at is Spam Filtering.  If we know the probability that a spam e-mail contains certain words and that non-spam e-mails contain certain words, we can calculate the likelihood that an e-mail is spam based on what words it contains.</p>
<p><a name ="spam"></a><br />
<img src="http://www.shiffman.net/itp/classes/a2z/week04/spam_small.jpg" class="right"/></p>
<h2>Spam Filtering using Bayesian Analysis</h2>
<p>Before we attempt any of this we should read Paul Graham&#8217;s <a href="http://www.paulgraham.com/spam.html">A Plan for Spam</a>.  And perhaps we might take a look at <a href="http://www.paulgraham.com/better.html">Better Bayesian Filtering</a>.  Now, we can start putting together some code.  Note that the code is not a perfect Spam filter by any means.  There are lots of issues with it (it only considers certain types of words, it doesn&#8217;t know anything about the number of e-mails received, it may run into memory problems if very large training files are used, etc.)  Nevertheless, the example outlines the steps one might take to apply <a href="http://en.wikipedia.org/wiki/Bayesian_filtering">Bayesian Filtering</a> to text.  We would also use similar strategies to perform <a href="http://en.wikipedia.org/wiki/Naive_Bayes_classifier">Naive Bayes Classification</a> of text, much like is done in the <a href="http://www.noraproject.org/">Nora Project</a>.</p>
<p><i><b>Fancy Word Class</b></i><br />
The first thing we need to do is develop a class to describe any individual word.  Not only do we want to know what the String is itself, but we want to know how many times that word appears in spam e-mails, how many times in good e-mails, and ultimately the probability that an e-mail is Spam based on the appearance of that word.</p>
<pre lang="java">
public class Word {
  private String word;    // The String itself
  private int countBad;   // The total times it appears in "bad" messages
  private int countGood;  // The total times it appears in "good" messages
  private float rBad;     // bad count / total bad words
  private float rGood;    // good count / total good words
  private float pSpam;    // probability this word is Spam
  
  // Create a word, initialize all vars to 0
  public Word(String s) {
    word = s;
    countBad = 0;
    countGood = 0;
    rBad = 0.0f;
    rGood = 0.0f;
    pSpam = 0.0f;
  }
}
</pre>
<p>That&#8217;s a good start and the above code encompasses all we need to store the necessary data for each Word object.  We&#8217;ll also need to give Word objects some functionality, such as increasing the the count for &#8220;bad&#8221; occurences, calculating the probability it occurs in spam messages, and more.  We&#8217;ll add some methods to the above class as follows (this is just a partial sample):</p>
<pre lang="java">
  // Increment bad counter
  public void countBad() {
    countBad++;
  }
  
  // Implement bayes rules to compute how likely this word is "spam"
  public void finalizeProb() {
    if (rGood + rBad > 0) pSpam = rBad / (rBad + rGood);
    if (pSpam < 0.01f) pSpam = 0.01f;
    else if (pSpam > 0.99f) pSpam = 0.99f;
  }
  
  // The â€œinterestingâ€ rating for a word is
  // How different from 0.5 it is
  public float interesting() {
    return Math.abs(0.5f - pSpam);
  }
</pre>
<p>Note that it will become important later to determine which words are &#8220;interesting.&#8221;  Our interesting rating is defined as how different the spam probability is from 0.5 (i.e. 50/50 is as boring as it gets).</p>
<p>Once we have the Word object taken care of, we can create a <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/HashMap.html">HashMap</a> and start dumping words in it.  Where do we get these words?  We&#8217;ll train the filter via known spam e-mails and known &#8220;good&#8221; e-mails.  Every time we encounter a word, we will check if it already exists in the Hash Table.  If it does not, we will add it, if it does, we will simply increase the counter for &#8220;bad&#8221; or &#8220;good&#8221; (depending on whether it&#8217;s found in a good or bad e-mail.)</p>
<pre lang="java">
HashMap words = new HashMap();
    
// Read the content and break up into words
A2ZFileReader fr = new A2ZFileReader(file);
String content = fr.getContent();

String[] tokens = content.split("\W");
int spamTotal = tokens.length; // How many words total
// For every word token
for (int i = 0; i < tokens.length; i++) {
  String word = tokens[i].toLowerCase();
  // If it exists in the HashMap already
  if (words.containsKey(word)) {
    Word w = (Word) words.get(word);
    w.countBad();
  // Otherwise it's a new word so add it
  } else {
    Word w = new Word(word);
    w.countBad();
    words.put(word,w);
  }
}
</pre>
<p>The above steps would then be repeated for any good e-mails (using the same HashMap, but calling countGood() instead of countBad().)  Once all the "training" e-mails are read, the probability of an e-mail containing a certain word is Spam, can be calculated for every word.  This is done by creating an <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/Iterator.html">Iterator</a> object from the HashMap.  The <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/Iterator.html">Iterator</a> allows us to visit every single value in the HashMap and call a method on it, i.e.:</p>
<pre lang="java">
Iterator iterator = words.values().iterator();
while (iterator.hasNext()) {
  Word word = (Word) iterator.next();
  word.finalizeProb();
 } 
</pre>
<p>Now, our spam filter is trained!  For every word in the HashMap, we know the probability that an e-mail containing it is spam.   Now, all that is left to do is take a new e-mail, find the 15 most interesting words, and compute the total probability for the e-mail according to the formula specified in <a href="http://www.paulgraham.com/spam.html">Graham&#8217;s essay</a>.</p>
<p>There are a number of different ways we can find the most interesting 15 words.  The following algorithm is an <a href="http://en.wikipedia.org/wiki/Insertion_sort">Insertion Sort</a> algorithm where an ArrayList is created one element at a time, inserting the elements the right spot to keep a descending sorted order.  If the list grows above 15, we just delete the last element to keep the top 15 words.</p>
<pre lang="java">
// Create an arraylist of 15 most "interesting" words
// Words are most interesting based on how different their Spam probability is from 0.5
ArrayList interesting = new ArrayList();

// For every word in the String to be analyzed
String[] tokens = stuff.split(splitregex);
        
for (int i = 0; i < tokens.length; i++) {
  String s = tokens[i].toLowerCase();
  Matcher m = wordregex.matcher(s);
  if (m.matches()) {
    
    Word w;
    
    // If the String is in our HashMap get the word out
    if (words.containsKey(s)) {
      w = (Word) words.get(s);
    // Otherwise, make a new word with a Spam probability of 0.4;
    } else {
      w = new Word(s);
      w.setPSpam(0.4f);
    }
    
    // We will limit ourselves to the 15 most interesting word
    int limit = 15;
    // If this list is empty, then add this word in!
    if (interesting.isEmpty()) {
      interesting.add(w);
    // Otherwise, add it in sorted order by interesting level
    } else {
      for (int i = 0; i < interesting.size(); i++) {
        // For every word in the list already
        Word nw = (Word) interesting.get(i);
        // If it's the same word, don't bother
        if (w.getWord().equals(nw.getWord())) {
          break;
        // If it's more interesting stick it in the list
        } else if (w.interesting() > nw.interesting()) {
          interesting.add(i,w);
          break;
        // If we get to the end, just tack it on there
        } else if (i == interesting.size()-1) {
          interesting.add(w);
        }
      }
    }
    
    // If the list is bigger than the limit, delete entries
    // at the end (the more "interesting" ones are at the 
    // start of the list
    while (interesting.size() > limit) interesting.remove(interesting.size()-1);
    
  }
}
</pre>
<p>We&#8217;re almost there. . . now that we&#8217;ve got the probabilities for each word, we can move on to the <a href="http://www.paulgraham.com/naivebayes.html">combined probability</a>.   (<a href="http://www.mathpages.com/home/kmath267.htm">more here</a>)</p>
<pre lang="java">
// Apply Bayes' rule (via Graham)
float pposproduct = 1.0f;
float pnegproduct = 1.0f;
// For every word, multiply Spam probabilities ("Pspam") together
// (As well as 1 - Pspam)
for (int i = 0; i < interesting.size(); i++) {
  Word w = (Word) interesting.get(i);
  //System.out.println(w.getWord() + " " + w.getPSpam());
  pposproduct *= w.getPSpam();
  pnegproduct *= (1.0f - w.getPSpam());
}

// Apply formula
float pSpam = pposproduct / (pposproduct + pnegproduct);
</pre>
<p>. . .and if pSpam is greater than 0.9, we've found a spam e-mail, if not, we're ok!!</p>
