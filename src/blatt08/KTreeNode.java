package blatt08;

import aud.util.Graphvizable;
import aud.util.GraphvizDecorable;
import aud.util.GraphvizDecorator;
import aud.util.Sys;
import aud.Vector;

/** Node in a k-ary search tree.<p>
   
    The implementation supports an arbitrary number of children/keys for 
    each node, which are stored as entries ({@link Entry}) in a 
    {@link Vector}.<p>
    
    Nodes support searching for an entry in the node and its subtrees,
    as inserting an entry into a node/subtree as well as splitting and 
    merging nodes.<p>
    
    <ul>
    
    <li>{@link #split} splits a node in two halves and creates a new tree 
    rooted by a 2-node, which stores the partitioning key.</li>
    
    <li>{@link #mergeChild} merges keys from a child into the parent node, i.e.,
    "pulls up" the child.</li>
     
    </ul>
    
    Note that there are {@code k-1} keys for {@code k=}{@link #getK} children.
    The node always stores {@code getK()} entries where the leftmost (0-th)
    key is {@code null} by definition.<p>
    
    Different types of trees would use different strategies for splitting and
    merging. This way, {@code KTreeNode} serves as a basis for implementation 
    of, e.g., 2-3-4 trees or B-trees. (The implementation is not efficient,
    similar notes as for {@link BinarySearchTree} apply)<p>
    
    In contrast to {@link BinarySearchTree}, the current implementation of
    {@code KTreeNode} stores only keys, there is no direct key-value mapping
    available.
    
    Many method rely on correct input arguments. The implementation uses a lot 
    of assertions but hardly explicit error checking (for the correct-use 
    cases)! Make sure to run it with {@code "-ea"} when testing your code!
 */
public class KTreeNode<Key extends Comparable<Key> > 
  implements Graphvizable, GraphvizDecorable {
  
  /** Entry in a {@code KTreeNode} with reference to key and left child.<p>
      As for {@link BinarySearchTree}, {@code null} represents the smallest
      possible ("leftmost") key, see {@link KTreeNode#compareKeys}.
   */
  protected class Entry {
    Entry(Key key,KTreeNode<Key> child) {
      this.key=key;
      this.child=child;
    }
    Entry(Entry other) {
      this.key=other.key;
      this.child=other.child;
    }
    final Key      key;
    KTreeNode<Key> child;

    @Override public String toString() { return key!=null ? key.toString() : "null"; }
  }

  KTreeNode<Key>  parent_ = null;
  Vector<Entry>   entries_;

  /**  Compares keys where {@code null} keys refer to smallest key.<p>
   */
  protected int compareKeys(Key a,Key b) {
    if (a==null) return -1;
    if (b==null) return +1;
    else         return a.compareTo(b);
  }
  
  /** Create new 1-node without key or children. 
      This is intended mainly trooto construct a root of an empty tree.
   */
  public KTreeNode() {
    entries_=new Vector<Entry>();
    entries_.push_back(new Entry(null,null)); // left child with key null
  }  
  
  /** create new 2-node with key and no children */
  public KTreeNode(Key key) {
    entries_=new Vector<Entry>();
    entries_.push_back(new Entry(null,null)); // left child with key null
    entries_.push_back(new Entry(key,null));
  }  
  /** get the number of subtrees/children (including {@code null} subtrees) */
  public int getK() { return entries_.size(); }

  /** Get the k-th key.<p>
      The 0-th key always equals {@code null} by definition.
      @param k in {@code [0,getKey()-1}
   */
  public Key getKey(int k) {
    assert(0<k && k<getK());
    return entries_.at(k).key;
  }

  /** get k-th child node
      @param k in {@code [0,getKey()-1}
      @return subtree or {@code null}
   */
  public KTreeNode<Key> getChild(int k) {
    assert(0<=k && k<getK());
    return entries_.at(k).child;
  } 

  /** find key
      @param key
      @return key instance found in tree (reference may differ from 
      {@code key}!) or {@code null}
   */
  public Key find(Key key) {
    for (int i=1;i<entries_.size();++i) {
      int cmp=compareKeys(key,entries_.at(i).key);
      if (cmp==0)
        return entries_.at(i).key;
      else if (cmp<0) {
        if (entries_.at(i-1).child==null)
          return null;
        else
          return entries_.at(i-1).child.find(key);
      }
    }
    return entries_.back().child!=null ? entries_.back().child.find(key) : null;
  }
  
  /** determine index of a child
      @param child search for this node in the list of childs
      @return index (or -1 if no such child)
    */
  int getIndexOfChild(KTreeNode<Key> child) {
    assert(child!=null);
    for (int i=0;i<entries_.size();++i)
      if (entries_.at(i).child==child)
        return i;
    return -1;
  }
  
  /** Insert key in this node.<p>
      @param key to be inserted
      @param k insert position, <b>must be correct</b> such that the order
      of entries_ is not violated (checked by assertions only)
      @return inserted entry root
   */
  Entry insert(Key key,int k) { 
    assert(key!=null);
    assert(0<=k && k<=getK()); // unchecked

    Entry entry=new Entry(key,null);
    entries_.insert(k+1,entry);

    assert(entries_.at(k+1)==entry);
    assert(k==0 || compareKeys(entries_.at(k-1).key,key)<0);
    assert(k+2>=getK() || compareKeys(key,entries_.at(k+2).key)<0);

    return entry;
  }

  /** Split this node at position k.
      After splitting, the node represents the new tree with 2-node as 
      root storing key at insertion position, the left and right children 
      store the range of keys left and right of k     
      @param k split position     
    */
  void split(int k) {
    assert(1<k && k<getK()-1);
    KTreeNode<Key> tmpRoot=new KTreeNode<Key>(getKey(k));
    // note: tmpRoot is never used as a node, we "steal" its entries below

    KTreeNode<Key> left=new KTreeNode<Key>(getKey(1));
    left.entries_.at(0).child=entries_.at(0).child;
    left.entries_.at(1).child=entries_.at(1).child; // left
    for (int i=2;i<k;++i)
      left.entries_.push_back(entries_.at(i));
    tmpRoot.entries_.front().child=left;
    left.updateChildrensParent();

    KTreeNode<Key> right=new KTreeNode<Key>(getKey(k+1));
    right.entries_.at(0).child=entries_.at(k).child;
    right.entries_.at(1).child=entries_.at(k+1).child; // right
    for (int i=k+2;i<getK();++i)
      right.entries_.push_back(entries_.at(i));
    tmpRoot.entries_.back().child=right;
    right.updateChildrensParent();
    
    // steal entries_ from tmpRoot, parent remains same
    this.entries_=tmpRoot.entries_; 
    left.parent_=right.parent_=this;
  }

  /** Merge child k into this node (pull child up).<p>
      <b>Note:<b> This call invalidates the child node and all references to
      the child! 
   */
  void mergeChild(int k) {
    KTreeNode<Key> child=getChild(k);
    assert(child!=null);
    
    // insert entries of child
    for (int i=child.getK()-1;i>0;--i)  
      entries_.insert(k+1,child.entries_.at(i));      
 
    // update their parent reference
    for (int i=0;i<child.getK();++i)
      if (child.entries_.at(i).child!=null) {
        assert(child.entries_.at(i).child.parent_==child);
        child.entries_.at(i).child.parent_=this;
      }
    
    entries_.at(k).child=child.entries_.at(0).child;
  }
  
  /** update parent {@code reference} of all children to {@code this} */
  private void updateChildrensParent() {
    for (Entry e : entries_) {
      if (e.child!=null)
        e.child.parent_=this;
    }
  }
  
  /** few consistency checks
      @throws RuntimeException on error (and/or assertion fails)
   */
  public void checkConsistency() {
    if (parent_!=null) {
      int i=parent_.getIndexOfChild(this);
      if (i<0)
        throw new RuntimeException("parent-child mismatch");
    }
    for (int i=0; i<entries_.size(); ++i) {
      if (entries_.at(i).child!=null) {
        if (entries_.at(i).child.parent_!=this)
          throw new RuntimeException("invalid parent");
      }
      if (i>1) {
        if (compareKeys(entries_.at(i-1).key,entries_.at(i).key)>=0) {
          throw new RuntimeException("invalid order of entries");
        }
      }
      // missing: check if children's keys fit in
      
      if (entries_.at(i).child!=null)
        entries_.at(i).child.checkConsistency();
    }
  }

  @Override public String toString() {
    java.util.Iterator<Entry> ii=entries_.iterator();
    assert(ii.hasNext());
    Entry entry=ii.next();
    assert(entry.key==null);
    String rv="";
    while (ii.hasNext()) {      
      rv+=ii.next().toString();
      if (ii.hasNext())
        rv+=" | ";
    }
    return rv;
  }

  @Override public GraphvizDecorator getDecorator() {
    return null;
  }

  @Override public String toDot() {
    String dot="graph BinaryTree {\n";

    GraphvizDecorator decorator=getDecorator();
    if (decorator!=null) {
      String d=decorator.getGlobalStyle();
      if (d!=null) dot+=" "+d+";\n";
      d=decorator.getGraphDecoration(this);
      if (d!=null) dot+=" graph ["+d+"];\n";      
    }
    dot+=treeToDot(null);

    dot+="\n}\n";
    //System.out.println(dot);
    return dot;
  }
  
  private String dotRef() { 
    return toString()+"-"+hashCode();
  }

  protected String dotLabel() { return toString(); }

  private String treeToDot(KTreeNode<Key> parent) {
    // Currently no "root" or "same rank" tags. Do we require them?

    GraphvizDecorator decorator=getDecorator();
    String dot =" \""+dotRef()+"\" [label=\""+dotLabel()+"\",";
    dot+=(decorator!=null) ? decorator.getFullNodeDecoration(this) : "shape=box";     
    dot+="];\n";

    int k=0;
    for (Entry entry : entries_) {
      if (entry.child!=null)
        dot+=entry.child.treeToDot(this);
      else
        dot+=dotLeaf("-"+k);
      ++k;
    }

    dot+="\n";

    if (parent!=null) { // edge is identified by "lower" node
      dot+=" \""+parent.dotRef()+"\" -- \""+
      dotRef()+"\" ["; // no edge labels currently
      dot+=(decorator!=null) ? decorator.getFullEdgeDecoration(this) : "";
      dot+="];\n";
    }

    return dot;
  }

  /** draw leaf */
  private String dotLeaf(String side) {
    String dummy="\""+dotRef()+"-"+side+"\"";
    String dot=" "+dummy+" [shape=point];\n";
    dot+=" \""+dotRef()+"\" -- "+dummy+" [];\n";
    return dot;
  } 

  /** get TikZ code for LaTeX export */
  public String toTikZ() {
    return "\\"+toTikZ(0)+";\n";
  }
  protected String tikzNodeStyle() { return ""; }
  protected String toTikZ(int level) {
    String spaces=Sys.indent(level);
    String tex=spaces+"node "+tikzNodeStyle()+" {"+toString()+"}\n";
    
    int k=0;
    for (Entry entry : entries_) {
      if (entry.child!=null)
        tex+=spaces+" child {\n"+entry.child.toTikZ(level+1)+" }\n";
      // else
      ++k;
    }
   
    return tex;
  }  
  
  public static void main(String[] args) {
    KTreeNode<String> node=new KTreeNode<String>("a");
    node.insert("b",1);
    node.insert("c",2);
    node.insert("d",3);
    node.insert("e",4);    

    System.out.println(node.find("b"));
    System.out.println(node.find("d"));
    System.out.println(node.find("x"));

    //String dot=node.toDot();
    //System.out.println(dot);
    aud.util.DotViewer.displayWindow(node,"KTreeNode");

    node.split(3);
    //System.out.println(dot=node.toDot());
    aud.util.DotViewer.displayWindow(node,"KTreeNode split");

    System.out.println(node.find("b"));
    System.out.println(node.find("d"));
    System.out.println(node.find("x"));

    System.out.println(node.toTikZ());

    node.mergeChild(0);
    //System.out.println(dot=node.toDot());
    aud.util.DotViewer.displayWindow(node,"KTreeNode merge 0");

    System.out.println(node.find("b"));
    System.out.println(node.find("d"));
    System.out.println(node.find("x"));

    node.mergeChild(3);
    //System.out.println(dot=node.toDot());
    aud.util.DotViewer.displayWindow(node,"KTreeNode merge 3"); // FIXME

    System.out.println(node.find("b"));
    System.out.println(node.find("d"));
    System.out.println(node.find("x"));
    
    node.checkConsistency();
  }
}