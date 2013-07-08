---
title: Concordance
author: Daniel
layout: page
dsq_thread_id:
  - 249553198
pvc_views:
  - 8012
---
<div class="pullquote">
<b>This week&#8217;s topics:</b></p>
<li class="arrow"><a href="#tree">Binary Search Tree</a></li>
<li class="arrow"><a href="#package">Updated File I/O</a></li>
<li class="arrow"><a href="#concordance">Concordance</a></li>
</div>
<h2>Examples:</h2>
<li class="arrow">Note: updated and extended examples on the A2Z CVS!
</li>
<li class="arrow">Full example: <a href="http://www.shiffman.net/itp/classes/a2z/week03/concordance.zip">concordance.zip</a></li>
<li class="arrow">Individual source files: <a href="http://www.shiffman.net/itp/classes/a2z/week03/Concordance.java">Concordance.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week03/Tree.java">Tree.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week03/A2ZFileReader.java"> A2ZFileReader.java</a>.
</li>
<li class="arrow">Simplified example using TreeMap instead of custom binary search tree: <a href="http://www.shiffman.net/itp/classes/a2z/week03/concordanceTreeMap.zip">concordanceTreeMap.zip</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week03/treemap/Concordance.java">Concordance.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week03/treemap/Word.java">Word.java</a>, <a href="http://www.shiffman.net/itp/classes/a2z/week03/treemap/a2z/A2ZFileReader.java">A2ZFileReader.java</a></li>
<h2>Related:</h2>
<li class="arrow"><a href="http://www.cprogramming.com/tutorial/computersciencetheory/algorithmicefficiency1.html">Algorithmic Efficiency &#8212; Beating a Dead Horse Faster</a></li>
<p>&nbsp;<br />
Our goal for this week is to develop a very simple example of a <a href="http://en.wikipedia.org/wiki/Concordance_%28publishing%29">text concordance</a>.   This program will read in a text file and process a count for every word that appears in that file, producing a list of words with their corresponding counts in alphabetical order.   We might instinctively begin to develop this example by storing words in an array or ArrayList, however, we&#8217;re going to use a more advanced data structure to store our concordance information &#8212; a <a href="http://en.wikipedia.org/wiki/Binary_tree">Binary Tree</a>. </p>
<p><a name ="tree"></a></p>
<h2>Binary Tree</h2>
<div ="nohigh"><a href="http://www.shiffman.net/itp/classes/a2z/week03/treeapplet/"><img src="http://www.shiffman.net/itp/classes/a2z/week03/tree.jpg" class="right"/></a></div>
<p>A Binary Tree consists of a branching network of nodes, where each node has 0, 1, or 2 children.   In our tree (technically a <a href="http://en.wikipedia.org/wiki/Binary_search_tree">Binary Search Tree</a>, each node will represent a word, i.e. String).   Each node&#8217;s left subnode will contain words alphabetically preceding that node&#8217;s word, and each node&#8217;s right subnode will contain words that alphabetically follow that node&#8217;s word.  Confused?  The applet to the right demos a simple binary search tree of characters.  (My goodness, shocking, are we looking at graphics in this class?  Yowsa!)</p>
<p>Here&#8217;s a better example of an <a href="http://www.cs.jhu.edu/~goodrich/dsa/trees/btree.html">applet that visualizes a binary tree of integers</a>.  </p>
<p>Why is this better?  Consider a data structure to keep track of 1000 words.  If our task is to search and find any given word and our data structure is an array, the amount of cycles it might take to find any word would be equal to the amount of words, i.e. the efficiency of the algorithm would be linear.  For N words, N cycles are required.  This is known as <a href="http://en.wikipedia.org/wiki/Big_O_notation">Big O notation</a>.   Examples of Big O notation are:</p>

{% highlight java %}
c: constant
log N: logarithmic
log^2 N: log-squared
N: linear
N log N: n * log(n)
N^2: quadratic
N^3: cubic
2^N: exponential
{% endhighlight %}

<p><img src="http://cis.stvincent.edu/carlsond/swdesign/bintrees/bst.gif"class="right"/> </p>
<p>It turns out that (balanced) binary search trees are logN in big O notation.  You can quickly see why they are more efficient.  Instead of traversing the list one word at a time, we can maneuver left and right between nodes based on the alphabetical position of the word we are searching for.  It turns out that to find any word, we only have to pass through each level once.  In a perfectly balanced tree this amounts approximately to the equation:  2^n = totalwords  (where &#8216;n&#8217; is the number of steps we have to take to find any given word.)  In <a href="http://mathworld.wolfram.com/Logarithm.html">logarithm</a>-speak, we can solve for n, i.e. n = log<sub>2</sub>(totalwords).</p>
<p>We really should create a <b><i>generic</i></b> binary tree, one that can hold any type of data that can be compared (i.e. implements Java&#8217;s <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Comparable.html">Comparable</a> interface.)  Nevertheless, to keep things simple we are going to create a binary search tree that holds onto Strings (which can be compared alphabetically).  (Diagram above thanks to: <a href="http://cis.stvincent.edu/swd/bintrees/bintrees.html">Br. David Carlson&#8217;s page</a>.)  The first thing we need to do is write a class to describe any given &#8220;Node&#8221; in the system.  </p>
<p>What type of data must a Node keep track of?</p>
<li class="arrow">Word (String)</li>
<li class="arrow">Left child (Node)</li>
<li class="arrow">Right child (Node)</li>
<p>&nbsp;<br />
Ok, so our Node class will look something like this to start:</p>

{% highlight java %}
private class Node
{
  public String word;
  public Node left;
  public Node right;

  public Node(String s) {
    word = s;
    left = null;
    right = null;
  }
}
{% endhighlight %}

<p>Each node starts with a word and empty slots for left and right &#8220;subnodes.&#8221;</p>
<p>Why is this class private?   Certainly, there are different ways to implement this Tree structure.  In our example, the Node class is private because it is an inner class that lives inside a Tree class.  The node data is only managed internally and thus does not need to be accessed by any other classes.  Here is how this works:</p>

{% highlight java %}
public class Tree
{
   private Node root;

   public Tree()
   {
      root = null;
   }

  private class Node
  {
    public String word;
    public Node left;
    public Node right;
    public int count;

    public Node(String s) {
      word = s;
      left = null;
      right = null;
    }
  }
}
{% endhighlight %}

<p>Each Tree object starts with a root Node, which contains references to two subnodes, which each contain references to two subnodes, etc.    When a Tree object is created, the root is set to null.  So what happens when we add our first word to the tree?   Well, we&#8217;ll have to implement an &#8220;insert&#8221; method inside the Tree object.</p>

{% highlight java %}
public void insert(String s)
{
  Node newNode = new Node(s);
  if (root == null) root = newNode;
  else root.insertNode(newNode);
}
{% endhighlight %}

<p>In other words, if the root is null (i.e. the tree is empty) this new node becomes the root.   If not, well then we call the &#8220;insertNode&#8221; method on the existing root node.   Oy, but what is this insertNode() method?   It turns out that we will need <i>two</i> insert methods.  The above method lives in the Tree object and deals with inserting a node into the first spot in our tree.  In all other cases, the Tree must be <a href="http://en.wikipedia.org/wiki/Recursion">recursively</a> traversed in order to find the right spot to insert the node.  This is accomplished via an insert method written into the node class itself:</p>

{% highlight java %}
// Inserts a new node as a descendant of this node
// If both spots are full, keep on going. . .
public void insertNode(Node newNode)
{
   // If new word is alphabetically before
   if (newNode.word.compareTo(word) < 0)
   {
      // if the spot is empty (null) insert here
      // otherwise keep going!
      if (left == null) left = newNode;
      else left.insertNode(newNode);
   }
   // if new word is alphabeticall after
   else if (newNode.word.compareTo(word) > 0)
   {
      // if the spot is empty (null) insert here
      // otherwise keep going!
      if (right == null) right = newNode;
      else right.insertNode(newNode);
   // if new word is the same
   } else {
      // We'd do something here if we wanted to when the Strings are equal
      // For example, increment a counter
   }
}
{% endhighlight %}

<p>This idea of recursively traversing the tree can be pretty confusing at first.  It&#8217;s important to try to get a handle on it since all other operations we might want to perform on the data (searching, printing, etc.) will require this same methodology.  </p>
<p>This <a href="http://www.cs.jhu.edu/~goodrich/dsa/trees/btree.html">nice friendly applet</a> might help.</p>
<p>Following is the code for printing the information in the tree.  Note how simple it is: only three lines!    However, conceptually it may take some time to get used to this idea.  We&#8217;ll be going over this in detail in class.</p>

{% highlight java %}
public void printNodes()
{
  if (left != null) left.printNodes();
  System.out.println(word + " " + count);
  if (right != null) right.printNodes();
}
{% endhighlight %}

<p>Thinking of it in pseudo-code might help:</p>

{% highlight java %}
1 -- Arrive at a node.
2 -- If the left child is not empty, start step 1 for the left child node.
3 -- Once we're back here, print the word.
4 -- If the right child is not empty, start step 1 for the right child node.
{% endhighlight %}

<p>What would happen if we did it this way?</p>

{% highlight java %}
1 -- Arrive at a node.
2 -- If the right child is not empty, start step 1 for the right child node.
3 -- Once we're back here, print the word.
4 -- If the left child is not empty, start step 1 for the left child node.
{% endhighlight %}

<p>The full source for our Tree class is as follows (partially based on the Horstmann example from <a href="http://www.horstmann.com/bigjava.html">Big Java</a>.</p>

{% highlight java %}
/* Daniel Shiffman               */
/* Binary Tree Class             */
/* Programming from A to Z       */
/* ITP, Spring 2006              */

// THIS TREE CLASS IS BASED ON THE HORSTMANN EXAMPLE
// SIMPLIFIED TO WORK WITH JUST STRINGS
// DOES NOT 'INSERT' A DUPLICATE WORD TO THE TREE
// INCLUDES A COUNTER FOR EACH WORD

public class Tree
{

   // We only need to keep track of the root
   // Each node will hold references to the other nodes
   private Node root;

   // Construct an empty tree
   public Tree()
   {
      root = null;
   }

   // Insert a new node
   public void insert(String s)
   {
      Node newNode = new Node(s);
      // If root is empty, then this node is the root
      if (root == null) root = newNode;
      // otherwise begin the recursive process
      else root.insertNode(newNode);
   }

   // Start the recursive traversing of the tree
   public void print()
   {
      if (root != null)
         root.printNodes();
   }


   // A node of a tree stores a String as well as references to
   // the child nodes to the left and to the right.
   // Note the use of an inner class
   private class Node
   {
      public String word;
      public Node left;
      public Node right;
      public int count;

      // Construct a node
      public Node(String s) {
         word = s;
         left = null;
         right = null;
         count = 1;
      }

      // Inserts a new node as a descendant of this node
      // If both spots are full, keep on going. . .
      public void insertNode(Node newNode)
      {

         // If new word is alphabetically before
         if (newNode.word.compareTo(word) < 0)
         {
            // if the spot is empty (null) insert here
            // otherwise keep going!
            if (left == null) left = newNode;
            else left.insertNode(newNode);
         }
         // if new word is alphabeticall after
         else if (newNode.word.compareTo(word) > 0)
         {
            // if the spot is empty (null) insert here
            // otherwise keep going!
            if (right == null) right = newNode;
            else right.insertNode(newNode);
         // if new word is the same
         } else {
            count++;
         }
      }

      // Recursively print words (with counts) in sorted order
      public void printNodes()
      {
         if (left != null) left.printNodes();
         System.out.println(word + " " + count);
         if (right != null) right.printNodes();
      }
   }
}
{% endhighlight %}

<p><a name ="package"></a></p>
<h2>Breaking out File I/O operations into a separate class</h2>
<p>Now that we&#8217;ve completed the Tree class, it&#8217;s fairly simple to implement a main &#8220;driver&#8221; program to read an input file, create an empty tree, use the StringTokenizer to split it up into &#8220;words&#8221;, and insert all the words into the Tree.  We&#8217;ll examine these pieces step-by-step in a moment, but first let&#8217;s look at cleaning up our code a bit for reading from a text file.</p>
<p>In earlier examples, all of the file input code could be found in the main program and it&#8217;s probably a good idea to break it out into a separate class that manages this work for us.  The following class stores two Strings, &#8220;filename&#8221; and &#8220;content.&#8221;  The constructor is called with a filename, and a process is then set in motion that reads the content from the file into the &#8220;content&#8221; String.  The content can then be accessed via the &#8220;getContent()&#8221; method.   It also should be noticed that I&#8217;ve set this example up to be part of an &#8220;a2z&#8221; <a href="http://java.sun.com/docs/books/jls/third_edition/html/packages.html">package</a>.</p>

{% highlight java %}
/* Daniel Shiffman               */
/* Class to read an input file   */
/* and return a String           */

package a2z;

import java.nio.*;
import java.io.*;
import java.nio.channels.*;

public class A2ZFileReader
{
  private String filename;
  private String content;

  public A2ZFileReader(String name) throws IOException {
    filename = name;
    readContent();
  }

  public void readContent() throws IOException {
    // Create an input stream and file channel
    // Using first arguemnt as file name to read in
    FileInputStream fis = new FileInputStream(filename);
    FileChannel fc = fis.getChannel();

    // Read the contents of a file into a ByteBuffer
    ByteBuffer bb = ByteBuffer.allocate((int)fc.size());
    fc.read(bb);
    fc.close();

    // Convert ByteBuffer to one long String
    content = new String(bb.array());
  }

  public String getContent() {
    return content;
  }
}
{% endhighlight %}

<p><a name ="concordance"></a></p>
<h2>The Concordance</h2>
<p>Once this is complete, the code for our main program is fairly simple and just needs to implement several steps.</p>
<p>STEP 1: READ IN THE DATA FILE</p>

{% highlight java %}
String path   = args[0];
A2ZFileReader fr = new A2ZFileReader(path);
String content = fr.getContent();
{% endhighlight %}

<p>STEP 2: CREATE AN EMPTY TREE:</p>

{% highlight java %}
Tree tree = new Tree();
{% endhighlight %}

<p>STEP 3:  SPLIT CONTENT UP INTO &#8220;WORDS&#8221; AND INSERT INTO TREE</p>

{% highlight java %}
String regex = "\b";  // We could come up with a better regex
String words[] = content.split(regex);
// For every word
for (int i = 0; i < words.length; i++)
{
  tree.insert(words[i]);
}
{% endhighlight %}

<p>STEP 4:  DISPLAY TREE CONTENTS</p>

{% highlight java %}
System.out.println("Here are the contents of your tree:");
System.out.println();
tree.print();
{% endhighlight %}

<p>There are plenty of improvements that could be made (error handling, eliminating tokens that aren&#8217;t really words, etc.) and some of these are incorporated into the full source for the example this week, available at the top of this page.  (Or gee, I guess I might as well put a link to it <a href="http://www.shiffman.net/itp/classes/a2z/week03/concordance.zip">here</a> too.)  Others are suggested as exercisess for this week&#8217;s programming assignment.</p>
<p>It should also be noted that we could have implemented a concordance using one of Java&#8217;s built-in data structures, such as <a href="http://java.sun.com/j2se/1.4.2/docs/api/java/util/TreeMap.html">TreeMap</a>.  My example:  <a href="http://www.shiffman.net/itp/classes/a2z/week03/concordanceTreeMapzip">concordanceTreeMap.zip</a> or also: <a href="http://www.faqs.org/docs/javap/c12/ex-12-5-answer.html">Chapter 12.5 of this online Java textbook</a>.</p>
