1   package adt.btree;
2   
3   import java.util.LinkedList;
4   import java.util.List;
5   
6   public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {
7   
8   	protected BNode<T> root;
9   	protected int order;
10  
11  	public BTreeImpl(int order) {
12  		this.order = order;
13  		this.root = new BNode<T>(order);
14  	}
15  
16  	@Override
17  	public BNode<T> getRoot() {
18  		return this.root;
19  	}
20  
21  	@Override
22  	public boolean isEmpty() {
23  		return this.root.isEmpty();
24  	}
25  
26  	@Override
27  	public int height() {
28  		return height(this.root);
29  	}
30  
31  	private int height(BNode<T> node) {
32  		int result = -1;
33  
34  		if (!node.isEmpty()) {
35  			if (node.isLeaf())
36  				return 0;
37  			else
38  				return 1 + height(node.getChildren().get(0));
39  		}
40  		return result;
41  	}
42  
43  	@Override
44  	public BNode<T>[] depthLeftOrder() {
45  		List<BNode<T>> list = new LinkedList<BNode<T>>();
46  
47  		addListDepthLeftOrder(this.root, list);
48  
49  		BNode<T>[] result = toArray(list);
50  		return result;
51  	}
52  	
53  	private BNode<T>[] toArray(List<BNode<T>> list) {
54  		BNode<T>[] array = new BNode[list.size()];
55  		for (int i = 0; i < list.size(); i++) {
56  			array[i] = list.get(i);
57  		}
58  		return array;
59  	}
60  	
61  	private void addListDepthLeftOrder(BNode<T> node, List<BNode<T>> list) {
62  		if (!node.isEmpty()) {
63  			list.add(node);
64  			for (int i = 0; i < node.getChildren().size(); i++) {
65  				addListDepthLeftOrder(node.getChildren().get(i), list);
66  			}
67  		}
68  	}
69  
70  	@Override
71  	public int size() {
72  		if (this.isEmpty())
73  			return 0;
74  		else
75  			return size(this.root);
76  	}
77  	
78  	private int size(BNode<T> node) {
79  		if (node == null || node.isEmpty())
80  			return 0;
81  
82  		else {
83  			int size = node.size();
84  			for (BNode<T> child : node.getChildren())
85  				size += size(child);
86  
87  			return size;
88  		}
89  	}
90  
91  	@Override
92  	public BNodePosition<T> search(T element) {
93  		if (element != null)
94  			return search(this.getRoot(), element);
95  		else
96  			return new BNodePosition<T>();
97  	}
98  	
99  	private BNodePosition<T> search(BNode<T> node, T element) {
100 		if (node == null || element == null)
101 			return new BNodePosition<T>();
102 
103 		else {
104 			int index = 0;
105 			
106 			while (index < node.size() && node.getElementAt(index).compareTo(element) < 0)
107 				index++;
108 			
109 			if (index < node.size() && node.getElementAt(index).equals(element))
110 				return new BNodePosition<>(node, index);
111 			else if (node.isLeaf())
112 				return new BNodePosition<T>();
113 			else
114 				return search(node.getChildren().get(index), element);
115 		}
116 	}
117 
118 	@Override
119 	public void insert(T element) {
120 		if (element != null)
121 			insert(getRoot(), element);
122 
123 	}
124 	
125 	private void insert(BNode<T> node, T element) {
126 		int index = 0;
127 
128 		while (index < node.size() && node.getElementAt(index).compareTo(element) < 0)
129 			index++;
130 
131 		if (index >= node.size() || !node.getElementAt(index).equals(element)) {
132 			if (node.isLeaf())
133 				node.addElement(element);
134 			else
135 				insert(node.getChildren().get(index), element);
136 		}
137 		
138 		if (node.size() > node.getMaxKeys())
139 			split(node);
140 	}
141 
142 	private void split(BNode<T> node) {
143 int midIndex = (node.getElements().size() / 2);
144 		
145 		BNode<T> left = new BNode<>(this.order);
146 		BNode<T> right = new BNode<>(this.order);
147 		
148 				
149 		for (int i = 0; i < node.size(); i++) {
150 			if (i < midIndex)
151 				left.getElements().add(node.getElementAt(i));
152 			else if (i > midIndex)
153 				right.getElements().add(node.getElementAt(i));
154 		}
155 		T midElement = node.getElementAt(midIndex);
156 		
157 		if (!node.isLeaf()) {
158 			if (node.getChildren().size() > 0) {
159 				for (int i = 0; i <= midIndex; i++) {
160 					left.addChild(i, node.getChildren().get(i));
161 				}
162 				int index = 0;
163 				for (int i = midIndex + 1; i < node.getChildren().size(); i++) {
164 					right.addChild(index, node.getChildren().get(i));
165 					index += 1;
166 				}
167 			}
168 		}
169 		
170 		if (node.equals(this.getRoot())) {
171 			BNode<T> newRoot = new BNode<>(this.order);
172 			newRoot.addElement(midElement);
173 			node.setParent(newRoot);
174 			this.root = newRoot;
175 			newRoot.addChild(0, left);
176 			newRoot.addChild(1, right);
177 			newRoot.getChildren().get(0).setParent(newRoot);
178 			newRoot.getChildren().get(1).setParent(newRoot);
179 		} else {
180 			node.addChild(0, left);
181 			node.addChild(1, right);
182 			promote(node);
183 		}
184 	}
185 
186 	private void promote(BNode<T> node) {
187 		int midIndex = (node.getElements().size() / 2);
188 		
189 		T midElement = node.getElementAt(midIndex);
190 		
191 		node.getElements().clear();
192 		node.addElement(midElement);
193 		
194 		BNode<T> parent = node.getParent();
195 		
196 		if (parent != null) {
197 			node.getChildren().get(0).setParent(parent);
198 			node.getChildren().get(1).setParent(parent);
199 			
200 			int index = parent.getChildren().indexOf(node);
201 			
202 			parent.addElement(midElement);
203 			parent.addChild(index, node.getChildren().get(0));
204 			parent.addChild(index + 1, node.getChildren().get(1));
205 			node.getChildren().get(0).setParent(parent);
206 			node.getChildren().get(1).setParent(parent);
207 			parent.getChildren().remove(node);
208 		}
209 	}
210 
211 	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
212 	@Override
213 	public BNode<T> maximum(BNode<T> node) {
214 		// NAO PRECISA IMPLEMENTAR
215 		throw new UnsupportedOperationException("Not Implemented yet!");
216 	}
217 
218 	@Override
219 	public BNode<T> minimum(BNode<T> node) {
220 		// NAO PRECISA IMPLEMENTAR
221 		throw new UnsupportedOperationException("Not Implemented yet!");
222 	}
223 
224 	@Override
225 	public void remove(T element) {
226 		// NAO PRECISA IMPLEMENTAR
227 		throw new UnsupportedOperationException("Not Implemented yet!");
228 	}
229 
230 }
