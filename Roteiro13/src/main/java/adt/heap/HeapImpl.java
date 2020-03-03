1   package adt.heap;
2   
3   import java.util.ArrayList;
4   import java.util.Arrays;
5   import java.util.Comparator;
6   
7   import util.Util;
8   
9   /**
10   * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
11   * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
12   * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
13   * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
14   * diretamente com os elementos armazenados, mas sim usando um comparator. 
15   * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
16   * ou min-heap.
17   */
18  public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
19  
20  	protected T[] heap;
21  	protected int index = -1;
22  	/**
23  	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
24  	 * mudar apenas o comparator e mandar reordenar a heap usando esse
25  	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
26  	 * como max-heap ou min-heap.
27  	 */
28  	protected Comparator<T> comparator;
29  
30  	private static final int INITIAL_SIZE = 20;
31  	private static final int INCREASING_FACTOR = 10;
32  
33  	/**
34  	 * Construtor da classe. Note que de inicio a heap funciona como uma
35  	 * min-heap.
36  	 */
37  	@SuppressWarnings("unchecked")
38  	public HeapImpl(Comparator<T> comparator) {
39  		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
40  		this.comparator = comparator;
41  	}
42  
43  	// /////////////////// METODOS IMPLEMENTADOS
44  	private int parent(int i) {
45  		return (i - 1) / 2;
46  	}
47  
48  	/**
49  	 * Deve retornar o indice que representa o filho a esquerda do elemento
50  	 * indexado pela posicao i no vetor
51  	 */
52  	private int left(int i) {
53  		return (i * 2 + 1);
54  	}
55  
56  	/**
57  	 * Deve retornar o indice que representa o filho a direita do elemento
58  	 * indexado pela posicao i no vetor
59  	 */
60  	private int right(int i) {
61  		return (i * 2 + 1) + 1;
62  	}
63  
64  	@Override
65  	public boolean isEmpty() {
66  		return (index == -1);
67  	}
68  
69  	@Override
70  	public T[] toArray() {
71  		ArrayList<T> resp = new ArrayList<T>();
72  		for (int i = 0; i <= this.index; i++) {
73  			resp.add(this.heap[i]);
74  		}
75  		return (T[])resp.toArray(new Comparable[0]);
76  	}
77  
78  	// ///////////// METODOS A IMPLEMENTAR
79  	/**
80  	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
81  	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
82  	 * (comparados usando o comparator) elementos na parte de cima da heap.
83  	 */
84  	private void heapify(int position) {
85  		if (position < this.size()) {
86  			int leftChildPosition = left(position);
87  			int rightChildPosition = right(position);
88  			int largest = position;
89  
90  			if (leftChildPosition < this.size() &&
91  					this.comparator.compare(heap[leftChildPosition], heap[position]) > 0) {
92  				largest = leftChildPosition;
93  			}
94  
95  			if (rightChildPosition < this.size() &&
96  					this.comparator.compare(heap[rightChildPosition], heap[largest]) > 0) {
97  				largest = rightChildPosition;
98  			}
99  
100 			if (largest != position) {
101 				Util.swap(heap, position, largest);
102 				heapify(largest);
103 			}
104 		}
105 	}
106 
107 	@Override
108 	public void insert(T element) {
109 		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
110 		if (index == heap.length - 1) {
111 			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
112 		}
113 		// /////////////////////////////////////////////////////////////////
114 		if (element != null) {
115 			this.index++;
116 
117 			int i = this.index;
118 			heap[i] = element;
119 
120 			while (i > 0 && this.comparator.compare(heap[i], heap[parent(i)]) > 0) {
121 				Util.swap(heap, i, parent(i));
122 				i = parent(i);
123 			}
124 		}
125 	}
126 
127 	@Override
128 	public void buildHeap(T[] array) {
129 		if (array != null) {
130 			this.heap = array;
131 			this.index = array.length - 1;
132 
133 			for (int i = array.length / 2; i >= 0; i--) {
134 				heapify(i);
135 			}
136 		}
137 	}
138 
139 	@Override
140 	public T extractRootElement() {
141 		T result = null;
142 
143 		if (!this.isEmpty()) {
144 			result = this.heap[0];
145 
146 			this.heap[0] = this.heap[this.index];
147 			this.index--;
148 
149 			heapify(0);
150 		}
151 
152 		return result;
153 	}
154 
155 	@Override
156 	public T rootElement() {
157 		T result = null;
158 
159 		if (!this.isEmpty()) {
160 			result = heap[0];
161 		}
162 
163 		return result;
164 	}
165 
166 	@Override
167 	public T[] heapsort(T[] array) {
168 		T[] result = null;
169 
170 		if (array != null) {
171 			Comparator<T> currentComparator = this.comparator;
172 			this.setComparator((o1, o2) -> o2.compareTo(o1));
173 
174 			this.buildHeap(array);
175 
176 			result = (T[]) new Comparable[this.size()];
177 			for (int i = 0; i < result.length; i++) {
178 				result[i] = this.extractRootElement();
179 			}
180 
181 			this.setComparator(currentComparator);
182 		}
183 
184 		return result;
185 	}
186 
187 	@Override
188 	public int size() {
189 		return this.index + 1;
190 	}
191 
192 	public Comparator<T> getComparator() {
193 		return comparator;
194 	}
195 
196 	public void setComparator(Comparator<T> comparator) {
197 		this.comparator = comparator;
198 	}
199 
200 	public T[] getHeap() {
201 		return heap;
202 	}
203 
204 }
