1   package adt.bst;
2   
3   import java.util.ArrayList;
4   import java.util.List;
5   
6   public class BSTImpl<T extends Comparable<T>> implements BST<T> {
7   
8   	protected BSTNode<T> root;
9   
10  	public BSTImpl() {
11  		root = new BSTNode<T>();
12  	}
13  
14  	public BSTNode<T> getRoot() {
15  		return this.root;
16  	}
17  
18  	@Override
19  	public boolean isEmpty() {
20  		return root.isEmpty();
21  	}
22  
23  	@Override
24  	public int height() {
25  		if (this.root.isEmpty()) {
26  			return -1;
27  		}
28  		return height(this.getRoot());
29  	}
30  
31  	protected int height(BSTNode<T> node) {
32  		if (node.isEmpty()) {
33  			return -1;
34  		}
35  		return Math.max(this.height((BSTNode<T>) node.getLeft()), this.height((BSTNode<T>) node.getRight())) + 1;
36  	}
37  
38  	@SuppressWarnings("unchecked")
39  	@Override
40  	public BSTNode<T> search(T element) {
41  		BSTNode<T> saida = new BSTNode<T>();
42  		if (element != null) {
43  			saida = this.search(element, this.getRoot());
44  		}
45  		return saida;
46  	}
47  
48  	private BSTNode<T> search(T element, BSTNode<T> node) {
49  		if (node.isEmpty() || element.equals(node.getData())) {
50  			return node;
51  		} else if (element.compareTo(node.getData()) < 0) {
52  			return this.search(element, (BSTNode<T>) node.getLeft());
53  		} else {
54  			return this.search(element, (BSTNode<T>) node.getRight());
55  		}
56  	}
57  
58  	@Override
59  	public void insert(T element) {
60  		if (element != null) {
61  			this.insert(element, this.getRoot());
62  		}
63  	}
64  
65  	@SuppressWarnings("unchecked")
66  	protected BSTNode<T> insert(T element, BSTNode<T> node) {
67  		if (node.isEmpty()) {
68  			node.setData(element);
69  			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
70  			node.setRight(new BSTNode.Builder<T>().parent(node).build());
71  		} else if (element.compareTo(node.getData()) < 0) {
72  			node = this.insert(element, (BSTNode<T>) node.getLeft());
73  		} else {
74  			node = this.insert(element, (BSTNode<T>) node.getRight());
75  		}
76  		return node;
77  	}
78  
79  	@Override
80  	public BSTNode<T> maximum() {
81  		if (this.root.isEmpty()) {
82  			return null;
83  		}
84  		return this.maximum(this.getRoot());
85  	}
86  
87  	private BSTNode<T> maximum(BSTNode<T> node) {
88  		while (!node.getRight().isEmpty()) {
89  			node = (BSTNode<T>) node.getRight();
90  		}
91  		return node;
92  	}
93  
94  	@Override
95  	public BSTNode<T> minimum() {
96  		if (this.root.isEmpty()) {
97  			return null;
98  		}
99  		return this.minimum(this.getRoot());
100 	}
101 
102 	private BSTNode<T> minimum(BSTNode<T> node) {
103 		while (!node.getLeft().isEmpty()) {
104 			node = (BSTNode<T>) node.getLeft();
105 		}
106 		return node;
107 	}
108 
109 	@Override
110 	public BSTNode<T> sucessor(T element) {
111 		BSTNode<T> node = this.search(element);
112 		if (node.isEmpty()) {
113 			return null;
114 		} else if (!node.getRight().isEmpty()) {
115 			return this.minimum((BSTNode<T>) node.getRight());
116 		} else if (node.getParent().getLeft().equals(node)) {
117 			return (BSTNode<T>) node.getParent();
118 		}
119 		while (!node.isEmpty() && this.verifyRight(node)) {
120 			node = (BSTNode<T>) node.getParent();
121 		}
122 		return (BSTNode<T>) node.getParent();
123 	}
124 
125 	private boolean verifyRight(BSTNode<T> node) {
126 		if (node.getParent() == null || node.isEmpty()) {
127 			return false;
128 		}
129 		return node.getParent().getRight().equals(node);
130 	}
131 
132 	@Override
133 	public BSTNode<T> predecessor(T element) {
134 		BSTNode<T> node = this.search(element);
135 		if (node.isEmpty()) {
136 			return null;
137 		} else if (!node.getLeft().isEmpty()) {
138 			return this.maximum((BSTNode<T>) node.getLeft());
139 		} else if (node.getParent().getRight().equals(element)) {
140 			return (BSTNode<T>) node.getParent();
141 		}
142 		while (!node.isEmpty() && this.verifyLeft(node)) {
143 			node = (BSTNode<T>) node.getParent();
144 		}
145 		return (BSTNode<T>) node.getParent();
146 	}
147 
148 	private boolean verifyLeft(BSTNode<T> node) {
149 		if (node.getParent() == null || node.isEmpty()) {
150 			return false;
151 		}
152 		return node.getParent().getLeft().equals(node);
153 	}
154 
155 	@SuppressWarnings("unchecked")
156 	@Override
157 	public void remove(T element) {
158 		BSTNode<T> node = this.search(element);
159 		remove(node);
160 	}
161 
162 	protected BSTNode<T> remove(BSTNode<T> node) {
163 		if (!node.isEmpty()) {
164 			if (node.isLeaf()) {
165 				node.setData(null);
166 				node.setLeft(null);
167 				node.setRight(null);
168 			} else if (oneChild(node)) {
169 				if (node != root) {
170 					if (node.getParent().getLeft() == node) {
171 						if (node.getLeft().isEmpty()) {
172 							node.getParent().setLeft(node.getRight());
173 							node.getRight().setParent(node.getParent());
174 						} else {
175 							node.getParent().setLeft(node.getLeft());
176 							node.getLeft().setParent(node.getParent());
177 						}
178 					} else {
179 						if (node.getLeft().isEmpty()) {
180 							node.getParent().setRight(node.getRight());
181 							node.getRight().setParent(node.getParent());
182 						} else {
183 							node.getParent().setRight(node.getLeft());
184 							node.getLeft().setParent(node.getParent());
185 						}
186 					}
187 				} else {
188 					if (root.getLeft().isEmpty())
189 						root = (BSTNode<T>) root.getRight();
190 					else {
191 						root = (BSTNode<T>) root.getLeft();
192 					}
193 				}
194 			} else {
195 				BSTNode<T> sucessor = sucessor(node.getData());
196 				node.setData(sucessor.getData());
197 				remove(sucessor);
198 			}
199 		}
200 		return node;
201 	}
202 
203 	protected boolean oneChild(BSTNode<T> node) {
204 		return ((!node.getLeft().isEmpty() && node.getRight().isEmpty())
205 				|| (node.getLeft().isEmpty() && !node.getRight().isEmpty()));
206 	}
207 
208 	@Override
209 	public T[] preOrder() {
210 		List<T> list = new ArrayList<T>();
211 		preOrder(this.getRoot(), list);
212 		return list.toArray((T[]) new Comparable[this.size()]);
213 	}
214 
215 	private void preOrder(BSTNode<T> node, List<T> list) {
216 		if (!node.isEmpty()) {
217 			list.add(node.getData());
218 			preOrder((BSTNode<T>) node.getLeft(), list);
219 			preOrder((BSTNode<T>) node.getRight(), list);
220 		}
221 	}
222 
223 	@Override
224 	public T[] order() {
225 		List<T> list = new ArrayList<T>();
226 		order(this.getRoot(), list);
227 		return list.toArray((T[]) new Comparable[this.size()]);
228 	}
229 
230 	private void order(BSTNode<T> node, List<T> list) {
231 		if (!node.isEmpty()) {
232 			order((BSTNode<T>) node.getLeft(), list);
233 			list.add(node.getData());
234 			order((BSTNode<T>) node.getRight(), list);
235 		}
236 	}
237 
238 	@Override
239 	public T[] postOrder() {
240 		List<T> list = new ArrayList<T>();
241 		postOrder(this.getRoot(), list);
242 		return list.toArray((T[]) new Comparable[this.size()]);
243 	}
244 
245 	private void postOrder(BSTNode<T> node, List<T> list) {
246 		if (!node.isEmpty()) {
247 			postOrder((BSTNode<T>) node.getLeft(), list);
248 			postOrder((BSTNode<T>) node.getRight(), list);
249 			list.add(node.getData());
250 		}
251 	}
252 
253 	/**
254 	 * This method is already implemented using recursion. You must understand how
255 	 * it work and use similar idea with the other methods.
256 	 */
257 	@Override
258 	public int size() {
259 		return size(root);
260 	}
261 
262 	private int size(BSTNode<T> node) {
263 		int result = 0;
264 		// base case means doing nothing (return 0)
265 		if (!node.isEmpty()) { // indusctive case
266 			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
267 		}
268 		return result;
269 	}
270 }
