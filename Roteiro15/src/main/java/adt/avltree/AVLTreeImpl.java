1   package adt.avltree;
2   
3   import adt.bst.BSTImpl;
4   import adt.bst.BSTNode;
5   import adt.bt.Util;
6   
7   /**
8    * 
9    * Performs consistency validations within a AVL Tree instance
10   * 
11   * @author Claudio Campelo
12   *
13   * @param <T>
14   */
15  public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {
16  	
17  
18  	@SuppressWarnings({ "unchecked", "rawtypes" })
19  	@Override
20  	protected void insert(T element, BSTNode<T> node) {
21  
22  		if (node.isEmpty()) {
23  			node.setData(element);
24  			node.setLeft(new BSTNode.Builder().parent(node).build());
25  			node.setRight(new BSTNode.Builder().parent(node).build());
26  		} 
27  		
28  		else {
29  			if (element.compareTo(node.getData()) > 0) {
30  				insert(element, (BSTNode<T>) node.getRight());
31  			}
32  				
33  			else if (element.compareTo(node.getData()) < 0) {
34  				insert(element, (BSTNode<T>) node.getLeft());
35  			}
36  			this.rebalance(node);
37  		}
38  	}
39  
40  	@Override
41  	public void remove(T element) {
42  		BSTNode<T> node = search(element);
43  
44  		if (!node.isEmpty()) {
45  			if (node.isLeaf()) {
46  				node.setData(null);
47  				rebalanceUp(node);
48  			} 
49  			
50  			else if (hasOneChild(node)) {
51  				if (node.getParent() != null) {
52  					if (!node.getParent().getLeft().equals(node)) {
53  						if (!node.getLeft().isEmpty()) {
54  							node.getParent().setRight(node.getLeft());
55  							node.getLeft().setParent(node.getParent());
56  						} 
57  						
58  						else {
59  							node.getParent().setRight(node.getRight());
60  							node.getRight().setParent(node.getParent());
61  						}
62  
63  					} 
64  					
65  					else {
66  						if (!node.getLeft().isEmpty()) {
67  							node.getParent().setLeft(node.getLeft());
68  							node.getLeft().setParent(node.getParent());
69  						} 
70  						
71  						else {
72  							node.getParent().setLeft(node.getRight());
73  							node.getRight().setParent(node.getParent());
74  						}
75  					}
76  				} 
77  				
78  				else {
79  					if (node.getLeft().isEmpty()) {
80  						this.root = (BSTNode<T>) node.getRight();
81  					} 
82  					
83  					else {
84  						this.root = (BSTNode<T>) node.getLeft();
85  					}
86  					this.root.setParent(null);
87  				}
88  
89  				rebalanceUp(node);
90  			} 
91  			
92  			else {
93  				T successor = sucessor(node.getData()).getData();
94  				remove(successor);
95  				node.setData(successor);
96  			}
97  		}
98  	}
99  	
100 	protected boolean hasOneChild(BSTNode<T> node) {
101 
102 		return ((node.getRight().isEmpty() && !node.getLeft().isEmpty()
103 				|| node.getLeft().isEmpty() && !node.getRight().isEmpty()));
104 	}
105 
106 	protected int calculateBalance(BSTNode<T> node) {
107 		int result = 0;
108 		
109 		if (!node.isEmpty()) {
110 			result = this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
111 		}
112 		return result;
113 	}
114 	
115 	
116 	protected void rebalance(BSTNode<T> node) {
117 		int balance = calculateBalance(node);
118 		
119 		if (balance < -1) {
120 			if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
121 				rightRotation((BSTNode<T>) node.getRight());
122 			}
123 			leftRotation(node);
124 		} 
125 		
126 		else if (balance > 1) {
127 			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
128 				leftRotation((BSTNode<T>) node.getLeft());
129 			}
130 			rightRotation(node);
131 		}
132 	}
133 
134 	protected void rightRotation(BSTNode<T> node) {
135 		if (node.equals(this.getRoot())) {
136 			root = Util.rightRotation(node);
137 		}
138 		
139 		else {
140 			BSTNode<T> aux = Util.rightRotation(node);
141 
142 			if (aux.getData().compareTo(aux.getParent().getData()) > 0) {
143 				aux.getParent().setRight(aux);
144 			}
145 				
146 			else {
147 				aux.getParent().setLeft(aux);
148 			}	
149 		}
150 	}
151 
152 	protected void leftRotation(BSTNode<T> node) {
153 
154 		if (node.equals(this.getRoot())) {
155 			root = Util.leftRotation(node);
156 		}
157 			
158 		else {
159 			BSTNode<T> aux = Util.leftRotation(node);
160 
161 			if (aux.getData().compareTo(aux.getParent().getData()) < 0) {
162 				aux.getParent().setLeft(aux);
163 			}
164 				
165 			else {
166 				aux.getParent().setRight(aux);
167 			}
168 		}
169 	}
170 
171 	protected void rebalanceUp(BSTNode<T> node) {
172 		BSTNode<T> parent = (BSTNode<T>) node.getParent();
173 		
174 		while (parent != null) {
175 			rebalance(parent);
176 			parent = (BSTNode<T>) parent.getParent();
177 		}
178 	}
179 
180 }
