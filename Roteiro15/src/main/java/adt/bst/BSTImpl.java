1   package adt.bst;
2   
3   public class BSTImpl<T extends Comparable<T>> implements BST<T> {
4   
5   	protected BSTNode<T> root;
6   
7   	public BSTImpl() {
8   		root = new BSTNode<T>();
9   	}
10  
11  	public BSTNode<T> getRoot() {
12  		return this.root;
13  	}
14  
15  	@Override
16  	public boolean isEmpty() {
17  		return root.isEmpty();
18  	}
19  
20  	@Override
21  	public int height() {
22  		return height(root);
23  	}
24  
25     	protected int height(BSTNode<T> node) {
26     		int result = -1;
27  		
28  		if (!node.isEmpty()) {
29  			int alturaEsquerda = height((BSTNode<T>) node.getLeft());
30  			int alturaDireita = height((BSTNode<T>) node.getRight());
31  			
32  			if (alturaEsquerda > alturaDireita)
33  				result =  1 + alturaEsquerda;
34  			else
35  				result = 1 + alturaDireita;
36  		}
37  		
38  		return result;
39     	}
40  
41  	@Override
42  	public BSTNode<T> search(T element) {
43  		return search(element,root);
44  	}
45  		    	
46  	public BSTNode<T> search(T element, BSTNode<T> node) {
47  		if (element == null || node.isEmpty() )
48  			return new BSTNode<T>();
49  		
50  		if (element.compareTo(node.getData()) == 0)
51  			return node;
52  		if (node.getData().compareTo(element)>0)
53  			return search(element,(BSTNode<T>) node.getLeft());
54  		
55  		return search(element,(BSTNode<T>) node.getRight());
56  	}
57  
58  	@Override
59  	public void insert(T element) {
60  		if (element!=null)
61  	 		insert(element,root);
62  	}
63  			    
64    	protected void insert(T element, BSTNode<T> node) {
65  		if (node.isEmpty()) {
66  			node.setData(element);
67  			node.setLeft(new BSTNode<>());
68  			node.setRight(new BSTNode<>());
69  			node.getLeft().setParent(node);
70  			node.getRight().setParent(node);
71  		} else {
72  			if (node.getData().compareTo(element) > 0)
73  				insert(element,(BSTNode<T>) node.getLeft());
74  			else if(node.getData().compareTo(element) < 0)
75  				insert(element,(BSTNode<T>) node.getRight());
76  		}
77  	}
78  
79  	@Override
80  	public BSTNode<T> maximum() {
81  		return maximum(root);
82  	}
83  		    	
84  	private BSTNode<T> maximum(BSTNode<T> node) {
85  		if (node.isEmpty())
86  			return null;
87  		
88  		if (node.getRight().isEmpty())
89  			return node;
90  		
91  		return maximum((BSTNode<T>) node.getRight());
92  	}
93  		    	
94  	@Override
95  	public BSTNode<T> minimum() {
96  		return minimum(root);
97  	}
98  		   	
99    	private BSTNode<T> minimum(BSTNode<T> node) {
100   		if (node.isEmpty())
101   			return null;
102   		
103   		if (node.getLeft().isEmpty())
104   			return node;
105   		
106   		return minimum((BSTNode<T>) node.getLeft());
107   	}
108 
109 	@Override
110 	public BSTNode<T> sucessor(T element) {
111 		BSTNode<T> node = search(element);
112 			return sucessor(node);
113 	}
114 		  	
115   	private BSTNode<T> sucessor(BSTNode<T> node) {
116   		if (node==null || node.isEmpty())
117   			return null;
118   
119   		BSTNode<T> menorDireita = minimum((BSTNode<T>) node.getRight());
120   		if (menorDireita != null)
121   			return menorDireita;
122   		
123   		BSTNode<T> parent = (BSTNode<T>) node.getParent();
124   		while(parent!=null && parent.getData().compareTo(node.getData()) < 0)
125   			parent = (BSTNode<T>) parent.getParent();
126   		
127   		return parent;
128   	}
129 
130 	@Override
131 	public BSTNode<T> predecessor(T element) {
132 		BSTNode<T> node = search(element);
133 	  		return predecessor(node);
134 	}
135 		  
136   	private BSTNode<T> predecessor(BSTNode<T> node) {
137   		if (node==null || node.isEmpty())
138   			return null;
139   
140   		BSTNode<T> maiorEsquerda = maximum((BSTNode<T>) node.getLeft());
141   		if (maiorEsquerda != null)
142   			return maiorEsquerda;
143   		
144   		BSTNode<T> parent = (BSTNode<T>) node.getParent();
145   		while(parent != null && parent.getData().compareTo(node.getData()) > 0) 
146   			parent = (BSTNode<T>) parent.getParent();
147   		
148   		return parent;
149   	}
150 
151 	@Override
152 	public void remove(T element) {
153 		BSTNode<T> node = this.search(element);
154 		if(!node.isEmpty()) 
155 			this.remove(node);				
156 	}
157 			  	
158   	protected void remove(BSTNode<T> node) {
159   		BSTNode<T> parent = (BSTNode<T>) node.getParent();
160   		boolean hasOneChild = node.getLeft().isEmpty() ^ node.getRight().isEmpty();
161   		if (node.isLeaf()){
162   			node.setData(null);
163   		} else if (hasOneChild){
164   			if (parent!=null) {
165   				if (!node.getLeft().isEmpty()) {
166   					if (isLeftChild(node, parent)) {
167   						parent.setLeft(node.getLeft());
168   						node.getLeft().setParent(parent);
169   					} else {
170   						parent.setRight(node.getLeft());
171   						node.getLeft().setParent(parent);
172   					}
173   				} else {
174   					if (isLeftChild(node, parent)) {
175   						parent.setLeft(node.getRight());
176   						node.getRight().setParent(parent);
177   					} else {
178   						parent.setRight(node.getRight());
179   						node.getRight().setParent(parent);
180   					}
181   				}
182   			} else {
183   				if (node.getLeft() == null || node.getLeft().isEmpty()) 
184   					root = (BSTNode<T>) node.getRight();
185   				else 
186   					root = (BSTNode<T>) node.getLeft();
187   				root.setParent(null);
188   			}
189   		} else {
190   			BSTNode<T> nodeAux = sucessor(node);
191   			T aux = node.getData();
192   			node.setData(nodeAux.getData());
193   			nodeAux.setData(aux);
194   			remove(nodeAux);
195   		}
196   	}
197   	
198   	private boolean isLeftChild(BSTNode<T> node, BSTNode<T> parent) {
199   		return parent.getLeft().equals(node);
200   	}
201   		  	
202 	private boolean isRightChild(BSTNode<T> node, BSTNode<T> parent) {
203 		return parent.getRight().equals(node);
204 	}
205 
206 	@Override
207 	public T[] preOrder() {
208 		T[] result = (T[]) new Comparable[this.size()];
209 		preOrder(result, 0, root);
210 		return result;
211 	}
212 	
213 	private int preOrder(T[] result, int index, BSTNode<T> node) {
214 		 if (!node.isEmpty()) {
215 			 result[index] = node.getData();
216 	         index++;
217 	         index = preOrder(result, index, (BSTNode<T>) node.getLeft());
218 	         index = preOrder(result, index, (BSTNode<T>) node.getRight());
219 	         return index;
220 	      } else {
221 	         return index;
222 	      }
223   	}
224 
225 	@Override
226 	public T[] order() {
227 		T[] array = (T[]) new Comparable[this.size()];
228   		order(array, root, 0);
229   		return array;
230 	}
231 	
232   	private int order(T[] array, BSTNode<T> node,int index) {
233   		if (!node.isEmpty()) {
234   			index = order(array, (BSTNode<T>) node.getLeft(), index);
235   			array[index++] = node.getData();
236   			index = order(array, (BSTNode<T>) node.getRight(), index);
237   		}
238   		return index;
239   	}
240 
241 	@Override
242 	public T[] postOrder() {
243 		T[] array = (T[]) new Comparable[this.size()];
244   		postOrder(array, root, 0);
245   		return array;
246 	}
247   	private int postOrder(T[] array, BSTNode<T> node,int index) {
248   		if (!node.isEmpty()) {
249   			index = postOrder(array, (BSTNode<T>) node.getLeft(), index);
250   			index = postOrder(array, (BSTNode<T>) node.getRight(), index);
251   			array[index++] = node.getData();
252   		}
253   		return index;
254   	}
255 
256 	/**
257 	 * This method is already implemented using recursion. You must understand
258 	 * how it work and use similar idea with the other methods.
259 	 */
260 	@Override
261 	public int size() {
262 		return size(root);
263 	}
264 
265 	private int size(BSTNode<T> node) {
266 		int result = 0;
267 		// base case means doing nothing (return 0)
268 		if (!node.isEmpty()) { // indusctive case
269 			result = 1 + size((BSTNode<T>) node.getLeft())
270 					+ size((BSTNode<T>) node.getRight());
271 		}
272 		return result;
273 	}
274 
275 }
