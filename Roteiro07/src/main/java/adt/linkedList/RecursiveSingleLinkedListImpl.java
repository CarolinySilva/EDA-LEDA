1   package adt.linkedList;
2   
3   public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {
4   
5   	protected T data;
6   	protected RecursiveSingleLinkedListImpl<T> next;
7   
8   	public RecursiveSingleLinkedListImpl() {
9   
10  	}
11  
12  	protected boolean inputIsValid(T element) {
13  		boolean validInput = true;
14  
15  		if (element == null) {
16  			validInput = false;
17  		}
18  
19  		return validInput;
20  	}
21  
22  	@Override
23  	public boolean isEmpty() {
24  		return this.getData() == null;
25  	}
26  
27  	@Override
28  	public int size() {
29  		int size = 0;
30  
31  		if (!this.isEmpty()) {
32  			size = 1 + this.getNext().size();
33  		}
34  
35  		return size;
36  	}
37  
38  	@Override
39  	public T search(T element) {
40  		T result = null;
41  
42  		if (!this.isEmpty() && this.inputIsValid(element)) {
43  			if (this.getData().equals(element)) {
44  				result = this.getData();
45  			} else {
46  				result = this.getNext().search(element);
47  			}
48  		}
49  
50  		return result;
51  	}
52  
53  	@Override
54  	public void insert(T element) {
55  		if (this.inputIsValid(element)) {
56  			if (this.isEmpty()) {
57  				this.setData(element);
58  				this.setNext(new RecursiveSingleLinkedListImpl<>());
59  			} else {
60  				this.getNext().insert(element);
61  			}
62  		}
63  	}
64  
65  	@Override
66  	public void remove(T element) {
67  		if (this.inputIsValid(element) && !this.isEmpty()) {
68  			if (this.getData().equals(element) && !this.getNext().isEmpty()) {
69  				this.setData(this.getNext().getData());
70  				this.setNext(this.getNext().getNext());
71  			} else if (this.getData().equals(element) && this.getNext().isEmpty()){
72  				this.setData(null);
73  			} else {
74  				this.getNext().remove(element);
75  			}
76  		}
77  	}
78  
79  	@Override
80  	public T[] toArray() {
81  		T[] result = (T[]) new Object[this.size()];
82  
83  		if (!this.isEmpty()) {
84  			toArray(result, 0, this.size());
85  		}
86  
87  		return result;
88  	}
89  
90  	private void toArray(T[] array, int currentSize, int size) {
91  		if (!this.isEmpty() && this.getNext().isEmpty()) {
92  			array[currentSize] = this.getData();
93  		} else if (!this.isEmpty() && !this.getNext().isEmpty()) {
94  			array[currentSize] = this.getData();
95  			currentSize++;
96  			this.getNext().toArray(array, currentSize, size);
97  		}
98  	}
99  
100 	public T getData() {
101 		return data;
102 	}
103 
104 	public void setData(T data) {
105 		this.data = data;
106 	}
107 
108 	public RecursiveSingleLinkedListImpl<T> getNext() {
109 		return next;
110 	}
111 
112 	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
113 		this.next = next;
114 	}
115 
116 }
