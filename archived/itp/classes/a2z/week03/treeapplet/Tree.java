/* Daniel Shiffman               */
/* SID: 042-60-2005              */
/* C-PAC II                      */
/* Prof. Michael Lewis           */
/* Java Problem #3               */
/* Binary Tree Problem           */

/** THIS TREE CLASS IS FROM THE HORSTMANN EXAMPLE **/
/** REVISED TO NOT 'INSERT' A DUPLICATE WORD TO   **/
/** THE TREE                                      **/


import processing.core.*;

/**
 * This class implements a binary search tree whose
 * nodes hold objects that implement the Comparable
 * interface.
 */
public class Tree
{
  /**
   * Constructs an empty tree.
   */
  public Tree()
  {
    root = null;
  }

  /**
   * Inserts a new node into the tree.
   * @param obj the object to insert
   */
  public void insert(Comparable obj)
  {
    Node newNode = new Node();
    newNode.data = obj;
    newNode.left = null;
    newNode.right = null;
    if (root == null) root = newNode;
    else root.insertNode(newNode);
  }

  /**
   * Prints the contents of the tree in sorted order.
   */
  public void print()
  {
    if (root != null)
      root.printNodes();
  }

  public void render(PApplet parent)
  {
    if (root != null)
      root.render(parent,parent.radians(80),200);
  }

  private Node root;

  /**
   * A node of a tree stores a data item and references
   * of the child nodes to the left and to the right.
   */
  private class Node
  {
    /**
     * Inserts a new node as a descendant of this node.
     * @param newNode the node to insert
     */
    public void insertNode(Node newNode)
    {
      if (newNode.data.compareTo(data) < 0)
      {
        if (left == null) left = newNode;
        else left.insertNode(newNode);
      }
      else if (newNode.data.compareTo(data) > 0)
      {
        if (right == null) right = newNode;
        else right.insertNode(newNode);
      } 
      else {
        count++;
        //If I wanted to do something with a duplicate word
        //System.out.println("Duplicate Word!!");
      }
    }

    /**
     * Prints this node and all of its descendants
     * in sorted order.
     */
    public void printNodes()
    {
      if (left != null)
        left.printNodes();
      System.out.println(data);
      if (right != null)
        right.printNodes();
    }


    public void render(PApplet parent, float theta, float len)
    {
      
      theta = parent.constrain(theta*0.7f,parent.radians(20),parent.radians(80));
      len = parent.constrain(len*0.7f,24,200);
      
      if (left != null) {
        parent.pushMatrix();
        parent.rotate( theta);
        parent.stroke(255,100);
        parent.smooth();
        parent.line(0,5,0,len-5);
        parent.translate(0,len);
        parent.rotate( -theta);
        left.render(parent,theta,len);
        parent.popMatrix();
      }
      //EACH NODE
      parent.fill(255,50);
      parent.noStroke();
      parent.smooth();
      parent.ellipse(0,0,16+count,16+count);
      parent.fill(255);
      parent.text((String)data,0,3);


      if (right != null) {
        parent.pushMatrix();
        parent.rotate( -theta);
        parent.stroke(255,100);
        parent.smooth();
        parent.line(0,5,0,len-5);
        parent.translate(0,len);
        parent.rotate( theta);
        right.render(parent,theta,len);
        parent.popMatrix();
      }
      
      


    }

    public Comparable data;
    public int count;
    public Node left;
    public Node right;
  }
}



