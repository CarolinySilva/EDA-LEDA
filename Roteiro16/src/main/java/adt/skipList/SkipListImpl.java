1   package adt.skipList;
2   
3   public class SkipListImpl<T> implements SkipList<T> {
4      
5      	protected SkipListNode<T> root;
6      	protected SkipListNode<T> NIL;
7      
8      	protected int maxHeight;
9      
10    	protected double PROBABILITY = 0.5;
11    
12    	public SkipListImpl(int maxHeight) {
13    		this.maxHeight = maxHeight;
14    		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
15    		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
16    		connectRootToNil();
17    	}
18    
19    	/**
20    	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
21    	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
22    	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
23    	 * conectar apenas o forward[0].
24    	 */
25    	private void connectRootToNil() {
26    		for (int i = 0; i < maxHeight; i++) {
27    			root.forward[i] = NIL;
28    		}
29    	}
30    
31    	@Override
32    	public void insert(int key, T newValue, int height) {
33    		if (newValue != null) {
34    			SkipListNode<T>[] update = new SkipListNode[height];
35    			SkipListNode<T> aux = root;
36    
37    			for (int i = height - 1; i >= 0; i--) {
38    				while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
39    					aux = aux.getForward(i);
40    				}
41    				update[i] = aux;
42    			}
43    
44    			aux = aux.getForward(0);
45    
46    			if (aux.getKey() == key) {
47    				aux.setValue(newValue);
48    			} else {
49    				adjustHeight(height, update);
50    				aux = new SkipListNode<T>(key, height, newValue);
51    
52    				for (int i = 0; i < height; i++) {
53    					aux.forward[i] = update[i].forward[i];
54    					update[i].forward[i] = aux;
55    				}
56    
57    			}
58    		}
59    	}
60    
61    	private void adjustHeight(int height, SkipListNode<T>[] update) {
62    		if (height > maxHeight) {
63    			for (int i = maxHeight; i < height; i++)
64    				root.getForward()[i] = NIL;
65    			maxHeight = height;
66    		}
67    
68    	}
69    
70    	@Override
71    	public void remove(int key) {
72    		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
73    		SkipListNode<T> aux = root;
74    
75    		for (int i = maxHeight - 1; i >= 0; i--) {
76    			if (aux.forward[i] != NIL) {
77    				while (aux.forward[i].value != null && aux.forward[i].key < key)
78    					aux = aux.forward[i];
79    			}
80    			update[i] = aux;
81    		}
82    		aux = aux.getForward()[0];
83    
84    		if (aux.key == key) {
85    
86    			for (int i = 0; i < maxHeight; i++) {
87    				if (update[i].getForward()[i] != aux)
88    					break;
89    				update[i].getForward()[i] = aux.getForward()[i];
90    			}
91    		}
92    	}
93    
94    	@Override
95    	public int height() {
96    		int height = maxHeight - 1;
97    		while (height >= 0 && root.getForward(height) == NIL) {
98    			if (height == 0) {
99    				height--;
100  				break;
101  			} else {
102  				height--;
103  			}
104  		}
105  		return height + 1;
106  	}
107  
108  	@Override
109  	public SkipListNode<T> search(int key) {
110  		SkipListNode<T> aux = root;
111  
112  		for (int i = height() - 1; i >= 0; i--) {
113  			while (aux.getForward(i) != null && aux.getForward(i).getKey() < key) {
114  				aux = aux.getForward(i);
115  			}
116  		}
117  
118  		aux = aux.getForward(0);
119  
120  		return key == aux.getKey() ? aux : null;
121  	}
122  
123  	@Override
124  	public int size() {
125  		int result = 0;
126  		SkipListNode<T> aux = root.getForward(0);
127  		while (aux != NIL) {
128  			result++;
129  			aux = aux.getForward(0);
130  		}
131  		return result;
132  	}
133  
134  	@Override
135  	public SkipListNode<T>[] toArray() {
136  		int size = size() + 2;
137  		SkipListNode<T>[] array = new SkipListNode[size];
138  		SkipListNode<T> aux = root;
139  
140  		for (int i = 0; i < size; i++) {
141  			array[i] = aux;
142  			aux = aux.getForward(0);
143  		}
144  		return array;
145  	}
146  
147 }
