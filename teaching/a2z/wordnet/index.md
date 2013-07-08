---
title: WordNet
author: Daniel
layout: page
dsq_thread_id:
  - 249553829
  - 249553829
pvc_views:
  - 15423
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#wordnet">WordNet</a></li>
</div>
<h2>Examples:</h2>
<li class="arrow">All the code as two Eclipse projects: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/wordnet_week9.zip">wordnet_week9.zip</a> </li>
<li class="arrow">Updates on CVS: /home/dts204/a2z/examples/ &#8212; Projects: WordNetJWNL, WordNetRiTa</li>
<li class="arrow">RiTa code examples (adapted from Daniel Howe): <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Hyponyms.java">Hyponyms.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Synonyms.java">Synonyms.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Substitutionalized.java">Substitutionalized.java</a></li>
<li class="arrow">Additional RiTa examples: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/WordNetDemo.java">WordNetDemo.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/SensesLookup.java">SensesLookup.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Rewrite.java">Rewrite.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Replacer.java">Replacer.java</a>
</li>
<li class="arrow">JWNL examples: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/SynReplace.java">SynReplace.java</a>,  <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/WordNetDemo.java">WordNetDemo.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/WordNetHelper.java">WordNetHelper.java</a></li>
<h2>Related:</h2>
<li class="arrow"><a href="http://marimba.d.umn.edu/cgi-bin/similarity.cgi">WordNet Similarity</a></li>
<li class="arrow"><a href="http://pinker.wjh.harvard.edu/articles/papers/Edinburgh.pdf">Words and Rules</a> by Steven Pinker.  You might also be interested in watching the lecture <a href="http://mitworld.mit.edu/video/143/">Words and Rules</a> or reading the entire <a href="http://pinker.wjh.harvard.edu/books/wr/index.html">the book</a>.</li>
<li class="arrow"><a href="http://portal.acm.org/citation.cfm?id=219748&#038;dl=GUIDE">WordNet: a lexical database for English</a>, George A. Miller (Note you must visit this site on the NYU Network or via the <a href="http://www.nyu.edu/its/nyunet/proxy/">NYU proxy</a>).</li>
<h2>Exercises:</h2>
<li class="arrow">Write a function that lists all antonyms for any given word.</li>
<li class="arrow">Write a function that traverses the tree of hypernyms (or hyponyms) for any given word.</li>
<li class="arrow">A semantic concordance is a concordance where every word sense (as opposed to word string) is counted.  Revise <a href="http://www.shiffman.net/teaching/a2z/concordance/">our concordance example</a> to produce a semantic concordance via WordNet.</li>
<p><a name ="wordnet"></a></p>
<h2>WordNet</h2>
<p>Traditional dictionaries (in book format) are designed to be readable and searchable by a human being and the driving organizational principle behind such a dictionary is alphabetic order.    A dictionary (or <a href="http://en.wikipedia.org/wiki/Lexicon">lexicon</a>) designed to be machine-readable, however, does not have to live under such constraints.  <a href="http://wordnet.princeton.edu/">WordNet</a> is a lexical database of the English language where words (separated into the parts of speech: nouns, verbs, adjectives, and adverbs) are linked via semantic relationships.  It&#8217;s a social network, so to speak, for words.</p>
<blockquote><p>&#8220;WordNetÂ® is a large lexical database of English, developed under the direction of George A. Miller. Nouns, verbs, adjectives and adverbs are grouped into sets of cognitive synonyms (synsets), each expressing a distinct concept. Synsets are interlinked by means of conceptual-semantic and lexical relations.&#8221;</p></blockquote>
<p>This page will serve as a brief tutorial for using WordNet in Java.  We will use both <a href="http://www.rednoise.org/rita/wordnet/documentation/index.htm">RiTa Wordnet by Daniel Howe</a> and <a href="http://sourceforge.net/projects/jwordnet">JWNL</a>, a Java API for accessing the WordNet dictionary.</p>
<h3>How is WordNet organized?</h3>
<p>There are several online resources to get started learning about how the data in WordNet is organized.  The <a href="http://en.wikipedia.org/wiki/Wordnet">WordNet Wikipedia</a> page is a good place to start, not to mention the <a href="http://wordnet.princeton.edu/">WordNet</a> site itself.</p>
<p>Following is a brief summary of the key points:</p>
<p>The fundamental building block of WordNet is a <b>synset</b>.  A synset is not a word, but rather is a collection of words (and <a href="http://en.wikipedia.org/wiki/Collocation">collocations</a>) that are synonymous (i.e. set of synonyms).    In other words, the primary record in the WordNet database is a conceptual idea/meaning, which several words might be linked to.</p>
<p>A word can belong to multiple synsets.  For example, consider the word &#8220;eat.&#8221;  Here are its synsets:</p>
<li class="arrow">[IndexWord: [Lemma: eat] [POS: verb]]: take in solid food; &#8220;She was eating a banana&#8221;; &#8220;What did you eat for dinner last night?&#8221;</li>
<li class="arrow">[IndexWord: [Lemma: eat] [POS: verb]]: eat a meal; take a meal; &#8220;We did not eat until 10 P.M. because there were so many phone calls&#8221;; &#8220;I didn&#8217;t eat yet, so I gladly accept your invitation&#8221;</li>
<li class="arrow">[IndexWord: [Lemma: eat] [POS: verb]]: take in food; used of animals only; &#8220;This dog doesn&#8217;t eat certain kinds of meat&#8221;; &#8220;What do whales eat?&#8221;</li>
<li class="arrow">[IndexWord: [Lemma: eat] [POS: verb]]: worry or cause anxiety in a persistent way; &#8220;What&#8217;s eating you?&#8221;</li>
<li class="arrow">[IndexWord: [Lemma: eat] [POS: verb]]: use up (resources or materials); &#8220;this car consumes a lot of gas&#8221;; &#8220;We exhausted our savings&#8221;; &#8220;They run through 20 bottles of wine a week&#8221;
</li>
<li class="arrow">[IndexWord: [Lemma: eat] [POS: verb]]: cause to deteriorate due to the action of water, air, or an acid; &#8220;The acid corroded the metal&#8221;; &#8220;The steady dripping of water rusted the metal stopper in the sink&#8221;</li>
<p>All synsets are categorized into 4 parts of speech: nouns, verbs, adjectives, and adverbs.   While a traditional dictionary or thesaurus is focused on the meanings of words, WordNet is focused on the relationships between synsets.  In WordNet, only nouns can be related to nouns, verbs to verbs, etc.  </p>
<p>Following are the semantic relationships available in WordNet:</p>
<p><b>All parts of speech</b></p>
<li class="arrow"><b>Synonymy</b>.  This one is easy and links words that have similar meanings, e.g. happy and glad.</li>
<li class="arrow"><b>Antonymy</b>.  The opposite of synonymy, e.g. happy and sad.</li>
<p><b>Nouns only</b></p>
<li class="arrow"><b>Hypernymy</b>. Hypnernymy refers to a hierarchical relationship between words.  For example, furniture is a hypernym of chair since every chair is a piece of furniture (but not vice-versa). </li>
<li class="arrow"><b>Hyponymy</b>. Hypnonymy is the opposite of hypernymy.  Dog is a hyponym of canine since every dog is a canine.</li>
<li class = "arrow"><b>Meronymy</b>.  Meronymy refers to a part/whole relationship.  For example, paper is a meronym of book, since paper is a part of a book.</li>
<p><b>Verbs only</b></p>
<li class="arrow"><b>Troponymy</b>.  Troponymy is the semantic relationship of doing something <i>in the manner of </i> something else.  For example, &#8220;walk&#8221; is a troponym of &#8220;move&#8221; and &#8220;limp&#8221; is a troponym of &#8220;walk.&#8221;</li>
<li class="arrow"><b>Entailment</b>.  Entailment refers to the relationship between verbs where doing something requires doing something else.  If you are snoring, you must be sleeping so sleeping is entailed by snoring.</li>
<h3>Using RiTa</h3>
<p>We&#8217;re going to access Wordnet via <a href="http://www.rednoise.org/rita/wordnet/documentation/index.htm">RiTa Wordnet</a> by <a href="http://mrl.nyu.edu/~dhowe/">Daniel Howe</a>.   The RiTa Wordnet download comes with the Wordnet dictionary itself so all you need is to add two JAR files to your Eclipse build path: ritaWN.jar, supportWN.jar.  Both of these JARS are found in the <a href="http://www.rednoise.org/rita/wordnet/documentation/index.htm">download</a>.</p>
<p>To use RiTa wordnet, you must first declare and initialize the <a href="http://www.rednoise.org/rita/wordnet/documentation/riwordnet_class_riwordnet.htm">RiWordnet</a> object.  </p>

{% highlight java %}
RiWordnet wordnet = new RiWordnet(null);
{% endhighlight %}

<p>Just as with <a href="http://www.shiffman.net/teaching/a2z/generative/">regular RiTa</a> from last week, RiTa wordnet expects a <a href="http://www.processing.org">Processing</a> PApplet.  Since our examples are java console apps, we can just pass in &#8220;null&#8221; to the constructor (but if you use RiTa with Processing you should pass in a reference to the PApplet).</p>
<p>The wordnet object can then query the Wordnet dictionary for you.  There are many useful functions, such as: exists(), getAllAntonyms(), getAllHypernyms(), getAllSynonyms(), getPos(), getSenseIds(), getSoundsLike(), etc.  Let&#8217;s walk through a few examples.   </p>
<p>Let&#8217;s say we are starting with a word: &#8220;run&#8221;.   Now word tokens are not the fundamental building blocks of Wordnet, everything in Wordnet is organized into synsets (senses) and a word can be a member of several synsets.  Run can mean a lot of different things.  As a noun, it can be a run scored in a baseball game or a jog around central park.  Run can also be a verb, of course, to run around the park or to run for office, etc.  Wordnet can tell us all of this.  We first start with a String.</p>

{% highlight java %}
String word = "run";
{% endhighlight %}

<p>And then ask RiTa wordnet for all the parts of speech available for that String.</p>

{% highlight java %}
String[] pos = wordnet.getPos(word);
{% endhighlight %}

<p>RiTa will give you the following Strings indicating parts of speech:</p>
<p><b>n &#8211;> noun</b><br />
<b>v &#8211;> verb</b><br />
<b>a &#8211;> adjective</b><br />
<b>r &#8211;> adverb</b></p>
<p>Armed with a part of speech, you can then ask RiTa for all of the &#8220;senses&#8221; associated with that word.</p>

{% highlight java %}
int[] ids = wordnet.getSenseIds(word, pos[0]);
{% endhighlight %}

<p>In wordnet a &#8220;sense&#8221; is unique, and therefore as a unique ID #.  Looping through the ID #&#8217;s, we can get more information about the &#8220;sense&#8221;, such as a description, and the list of words in the synset.</p>

{% highlight java %}
for (int i = 0; i < ids.length; i++) {
  // Sense ID #
  System.out.println("Sense: " + ids[i]);
  String description = wordnet.getDescription(ids[i]);
  // Sense Description (definition)
  System.out.println("Description: " + description);
  // All words that belong to this synset
  String[] words = wordnet.getSynset(ids[i]);
  if (words != null) {
    System.out.print("Synset: ");
    for (int j = 0; j < words.length; j++) System.out.print(words[j] + " ");
  }
  System.out.println("n-------------------------");
}
{% endhighlight %}

<p>Wordnet is a massive network of pointers.  Every synset points to other synsets, and each pointer is of a certain type: antonynm, synonym, hypernym, etc.   RiTa wordnet simplifies these relationships by providing a series of functions that return (as an array of Strings) a list of related words for any given word (and part of speech).  For example, if you want the list of all hyponyms for a given word, you would say:</p>

{% highlight java %}
// Hyponyms for all senses
String word = "cat";
String pos = wordnet.getBestPos(word);
String[] result = wordnet.getAllHyponyms(word, pos);
for (int i = 0; i < result.length; i++) {
  System.out.println(result[i]);
}
{% endhighlight %}

<p>Note that this returns <b><i>all</i></b> hyponyms for <b><i>all</i></b> of the synsets that include the word cat.  If you wanted hyponyms for a specific sense, you would have to use a sense ID, in combination with the function: <a href="http://www.rednoise.org/rita/wordnet/documentation/riwordnet_method_gethyponyms.htm">getHyponyms()</a>.   You can get lists of other types of related words in exactly the same manner as above with the functions offered <a href="http://www.rednoise.org/rita/wordnet/documentation/">here</a>: getAllAntonyms(), getAllDerivedTerms(), getAllHolonyms(), getAllHypernyms(), getAllHyponyms(), getAllMeronyms(), getAllNominalizations(), getAllSynonyms(), etc.</p>
<p>Here are some other examples:</p>
<p>Listing Hyponyms: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Hyponyms.java">Hyponyms.java</a><br />
Listing Synonyms: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Synonyms.java">Synonyms.java</a><br />
Listing Synsets: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/SensesLookup.java">SensesLookup.java</a>,<br />
Rewriting a text using antonyms, synonyms, and hyponyms: <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Rewrite.java">Rewrite.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/wordnet/rita/Replacer.java">Replacer.java</a></p>
<h3>Using JWNL</h3>
<p><a href="http://sourceforge.net/projects/jwordnet">JWNL</a> (Java WordNet Library) is a Java API for accessing the WordNet dictionary.  I recommend that you use the much friendlier RiTa Wordnet (as described above), however if you want to delve deeper into the code and walk through the "pointer" relationships in wordnet more manually, JWNL is the place to be.  Here's how you can get started.</p>
<h2>Step 1.  Download WordNet.</h2>
<p><a href="http://wordnet.princeton.edu/obtain">Select and download the WordNet files for your OS</a>.  The folder we care about is "dict," which stores the dictionary files. </p>
<h2>Step 2.  Download JWNL</h2>
<p>JWNL is available for download <a href="http://sourceforge.net/projects/jwordnet">here</a>.  You can read a bit more about it <a href="http://jwordnet.sourceforge.net/">here</a> as well as peruse the <a href="http://jwordnet.sourceforge.net/">JavaDocs</a>.</p>
<h2>Step 3. Grab jars and configure the properties file</h2>
<p>The download includes two JAR files: jwnl.jar and commons-logging.jar.  You must add both of these to your build path in Eclipse to run the examples provided on this page.  In addition, you should make sure to copy the sample "file_properties.xml" file.  Edit the line in this file that includes the path to the dictionary. i.e.:</p>
<p><b>&lt;param name="dictionary_path" value="/Users/daniel/Desktop/WordNet-3.0/dict"/&gt;</b></p>
<p>Once you have JWNL installed, you can access the WordNet database from your Java application and search for these semantic relationships.  </p>
<p>First, you must initialize JWNL with the properties file.</p>

{% highlight java %}
JWNL.initialize(new FileInputStream(propsFile));
{% endhighlight %}

<p>Once the database is initialized, you can create a Dictionary object (that can be queried).</p>

{% highlight java %}
wordnet = Dictionary.getInstance();
{% endhighlight %}

<p>Once you have the Dictionary object, you can begin to look up words and search for relationships.  To do this, we need to get familiar with the following classes:</p>
<p><b><a href="http://nlp.stanford.edu/nlp/javadoc/jwnl-docs/net/didion/jwnl/data/IndexWord.html">IndexWord</a></b>. An IndexWord is a single word and part of speech.  An IndexWord can be used to lookup a Synset object.</p>

{% highlight java %}
IndexWord word = wordnet.getIndexWord(POS.VERB,"run");
{% endhighlight %}

<p>Once you have an IndexWord, you can lookup all the <b><a href="http://nlp.stanford.edu/nlp/javadoc/jwnl-docs/net/didion/jwnl/data/Synset.html">Synset</a></b> objects associated with that word.   A Synset represents a concept, and contains the set of words whose meanings are synonymous.</p>

{% highlight java %}
Synset[] senses = word.getSenses();
for (int i = 0; i < senses.length; i++) {
   System.out.println(word + ": " + senses[i].getGloss());
}
{% endhighlight %}

<p>For simplicity, you might also just ask for the first one.</p>

{% highlight java %}
Synset sense = word.getSense(1);
{% endhighlight %}

<p>For any given Synset, you can search for all related Synsets (for any given type of relationship).  All the Synsets in WordNet that are related to each other <i>point</i> to each other.  The <a href="http://www-nlp.stanford.edu/nlp/javadoc/jwnl-docs/net/didion/jwnl/data/PointerUtils.html">PointerUtils </a> class provides a selection of methods that returns a list of Synsets that a given Synset points to.  For example:</p>

{% highlight java %}
PointerTargetNodeList relatedList = PointerUtils.getInstance().getSynonyms(sense);
{% endhighlight %}

<p>Once you have that list, you can iterate through it:</p>

{% highlight java %}
Iterator i = relatedList.iterator();
while (i.hasNext()) {
  PointerTargetNode related = (PointerTargetNode) i.next();
  Synset s = related.getSynset();
  System.out.println(s);
}
{% endhighlight %}

<p>With two Synsets, you can search for a relationship between them using the <a href="http://nlp.stanford.edu/nlp/javadoc/jwnl-docs/net/didion/jwnl/data/relationship/RelationshipFinder.html">RelationshipFinder</a>.<br />
The RelationshipFinder requires that you specify a <b><a href="http://nlp.stanford.edu/nlp/javadoc/jwnl-docs/net/didion/jwnl/data/PointerType.html">PointerType</a></b>.  There is a PointerType for each of the semantic relationships described above, i.e.:</p>
<p>PointerType.SYNONYM<br />
PointerType.HYPERNYM<br />
etc.</p>

{% highlight java %}
RelationshipList list = RelationshipFinder.getInstance().findRelationships(synset1, synset2, PointerType.SYNONYM);
if (!list.isEmpty())  {
  Relationship rel = (Relationship) list.get(0);
  System.out.println(rel);
}
{% endhighlight %}

<p>In the above example, a list of <a href="http://nlp.stanford.edu/nlp/javadoc/jwnl-docs/net/didion/jwnl/data/relationship/Relationship.html">Relationship</a> objects is returned.  However, for simplicity, only the first relationship is displayed.</p>
<p>Once you have that Relationship object, you can call various methods to learn about that relationship.  For example, you can get the depth of that relationship (how many degrees of separation) via getDepth() as well as traverse the links between the Synsets by calling getNodeList().</p>

{% highlight java %}
System.out.println("The depth of this relationship is: " + rel.getDepth());

PointerTargetNodeList nodelist = rel.getNodeList();
Iterator i = nodelist.iterator();
while (i.hasNext()) {
  PointerTargetNode related = (PointerTargetNode) i.next();
  System.out.println(related.getSynset());
}
{% endhighlight %}

