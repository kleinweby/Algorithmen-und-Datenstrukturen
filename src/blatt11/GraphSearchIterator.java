package blatt11;

import java.util.HashSet;
import java.util.Set;

import aud.Stack;
import aud.example.graph.*;

public class GraphSearchIterator extends MyGraph implements Iterable<MyNode> {

	GraphSearchIterator(boolean directed) {
		super(directed);
	}

	public class DFSIterator implements java.util.Iterator<MyNode> {
		private Stack<MyNode> st = new Stack<MyNode>();
		private Set<MyNode> visitedNodes = new HashSet<MyNode>();

		public DFSIterator(MyNode start) {
			this.st.push(start);
		}

		public boolean hasNext() {
			return !this.st.is_empty();
		}

		private boolean isNodeMarkedVisited(MyNode node) {
			return this.visitedNodes.contains(node);
		}
		
		private void markNodeVisited(MyNode node) {
			this.visitedNodes.add(node);
		}
		
		public MyNode next() {
			MyNode node = this.st.pop();
			
			assert this.isNodeMarkedVisited(node) == false;
			
			this.markNodeVisited(node);
			
			// Now get all adjacent nodes
			for (MyEdge edge: getOutEdges(node)) {
				MyNode otherNode = (MyNode) edge.destination();
				
				if (!this.isNodeMarkedVisited(otherNode))
					this.st.push(otherNode);
			}
			
			// Remove marked nodes ontop of the stack
			while (!this.st.is_empty() && this.isNodeMarkedVisited(this.st.top()))
				this.st.pop();
			
			return node;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public DFSIterator iterator() {
		return new DFSIterator(getSomeNode());
	}

	public static void main(String args[]) {
		// This is really messy
		// when you specify that the graph is undirected
		// all edges are normalized to a->b where the index of a is lesser than the index of b
		// this means when you add an edge (a,b) getOutEdges(b) will not
		// give you back a, altough this would be an out edge in a undirected graph
		// to work around I declare the graph directed and alway add the
		// inverse edge explicitly
		GraphSearchIterator graph = new GraphSearchIterator(true);
		
		// Create nodes
		MyNode node1 = graph.addNode();
		MyNode node2 = graph.addNode();
		MyNode node3 = graph.addNode();
		MyNode node4 = graph.addNode();
		MyNode node5 = graph.addNode();
		MyNode node6 = graph.addNode();
		MyNode node7 = graph.addNode();
		MyNode node8 = graph.addNode();
		
		// Connect them
		graph.addEdge(node1, node2);
		graph.addEdge(node1, node3);
		graph.addEdge(node1, node6);
		graph.addEdge(node1, node7);
		graph.addEdge(node1, node8);
		
		graph.addEdge(node2, node8);
		graph.addEdge(node8, node2);
		
		graph.addEdge(node3, node8);
		graph.addEdge(node8, node3);
		
		graph.addEdge(node4, node5);
		graph.addEdge(node5, node4);
		graph.addEdge(node4, node6);
		graph.addEdge(node6, node4);
		
		graph.addEdge(node5, node6);
		graph.addEdge(node6, node5);
		graph.addEdge(node5, node7);
		graph.addEdge(node7, node5);
		graph.addEdge(node5, node8);
		graph.addEdge(node8, node5);
		
		for (MyNode n: graph) {
			System.out.println(n.index() + 1);
		}
		
		// 1, 8, 5, 7, 6, 3, 2
		
		// Solution for an not connected graph would be to
		// put initially all nodes onto the stack
	}
}
