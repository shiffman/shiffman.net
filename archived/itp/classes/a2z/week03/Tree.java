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



