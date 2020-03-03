package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
4      		DoubleLinkedList<T> {
5      
6      	protected DoubleLinkedListNode<T> last;
7      	private DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<T>();
8      
9      	public DoubleLinkedListImpl(){
10    		super();
11    		setHead(nil);
12    		setLast(nil);
13    	}
14    	
15    	@Override
16    	public void insertFirst(T element) {
17    		if(element != null){
18    			DoubleLinkedListNode<T> head = (DoubleLinkedListNode<T>) getHead();
19    			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, head, new DoubleLinkedListNode<>());
20    		
21    			head.setPrevious(newHead);
22    			setHead(newHead);
23    			if(this.getLast().isNIL()){
24    				this.setLast(newHead);
25    			}if(this.getLast().getPrevious()== null){
26    				this.getLast().setPrevious(newHead);
27    				
28    			}
29    		}
30    	}
31    	
32    	@Override
33    	public void insert(T element) {
34    		if (element != null) {
35    		DoubleLinkedListNode aux = new DoubleLinkedListNode(element, new DoubleLinkedListNode<>(), last);
36    		if (isEmpty()) {
37    			super.head = last = aux;
38    
39    		} else {
40    			last.next = aux;
41    			last = aux;
42    		}
43    		}
44    	}
45    	@Override
46    	public void remove(T element) {
47    		if (element != null && !isEmpty()) {
48                if (this.getHead().getData().equals(element)) {
49                    this.removeFirst();
50                } else if (this.getLast().getData().equals(element)) {
51                    this.removeLast();
52                } else {
53                    DoubleLinkedListNode<T> aux = this.getLast();
54                    DoubleLinkedListNode<T> toRemove = null;
55    
56                    while(!aux.isNIL() && aux.getPrevious() != null){
57                        if(aux.getData().equals(element)){
58                            toRemove = aux;
59                        }
60                        aux = aux.getPrevious();
61                    }
62    
63                    if(toRemove != null){
64                        toRemove.getPrevious().setNext(toRemove.getNext());
65                        ((DoubleLinkedListNode<T>) toRemove.getNext())
66                                .setPrevious(toRemove.getPrevious());
67                    }
68                }
69            }
70    	}
71    
72    	
73    	@Override
74    	public void removeFirst() {
75    		
76    		if(!isEmpty()){
77    			if(size() > 1){
78    				DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead().getNext();
79    				aux.setPrevious(new DoubleLinkedListNode<>());
80    				this.setHead(aux);
81    			}else{
82    				this.setHead(new DoubleLinkedListNode<>());
83    				this.setLast(new DoubleLinkedListNode<>());
84    				
85    			}
86    		}
87    	    
88    	}
89    
90    	@Override
91    	public void removeLast() {
92    		if(!isEmpty()){
93    			// se o tamanho for igual a 1, o head sera igual ao last, que por sua vez tbm ser� igual ao next do head, ou seja, o no Nil.
94    			if(size() == 1){
95    				super.head = this.last = (DoubleLinkedListNode<T>) super.head.getNext();
96    			}else{
97    				this.last.getPrevious().setNext(new DoubleLinkedListNode<>());
98    				// set last
99    				this.last = this.last.getPrevious();
100  			}
101  		}
102  	}
103  
104  	
105  	
106  	public DoubleLinkedListNode<T> getLast() {
107  		return last;
108  	}
109  	
110  	
111  	public void setLast(DoubleLinkedListNode<T> last) {
112  		this.last = last;
113  	}
114 
115 }
