1   package adt.avltree;
2   
3   import java.util.Arrays;
4   
5   import adt.bst.BSTNode;
6   
7   public class AVLCountAndFillImpl<T extends Comparable<T>> extends
8   		AVLTreeImpl<T> implements AVLCountAndFill<T> {
9   
10  	private int LLcounter;
11  	private int LRcounter;
12  	private int RRcounter;
13  	private int RLcounter;
14  
15  	public AVLCountAndFillImpl() {
16  		
17  	}
18  
19  	@Override
20  	public int LLcount() {
21  		return LLcounter;
22  	}
23  
24  	@Override
25  	public int LRcount() {
26  		return LRcounter;
27  	}
28  
29  	@Override
30  	public int RRcount() {
31  		return RRcounter;
32  	}
33  
34  	@Override
35  	public int RLcount() {
36  		return RLcounter;
37  	}
38  
39  	@Override
40  	public void fillWithoutRebalance(T[] array) {
41  		if(array != null && array.length != 0 ) {
42  			Arrays.parallelSort(array);
43  			this.fillWithoutRebalance(0, array.length -1, array);
44  		}	
45  	}
46  	
47  	private void fillWithoutRebalance(int leftIndex, int rightIndex, T[] array) {
48  		int m = (int) Math.ceil((leftIndex-rightIndex) / 2);
49  		if(m >= array.length || m < 0)
50  			return;
51  		else {
52  			this.insert(array[m]);
53  			fillWithoutRebalance(leftIndex, m-1, array);
54  			fillWithoutRebalance(m+1, array.length -1, array);
55  		}
56  	}
57  	
58  	@Override
59  	protected void rebalance(BSTNode<T> node) {
60  		int balance = calculateBalance(node);
61  		
62  		if (balance < -1) {
63  			if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
64  				rightRotation((BSTNode<T>) node.getRight());
65  				this.RLcounter++;
66  			}else
67  				this.RRcounter++;
68  			
69  			leftRotation(node);
70  		} 
71  		
72  		else if (balance > 1) {
73  			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
74  				leftRotation((BSTNode<T>) node.getLeft());
75  				this.LRcounter++;
76  			}else
77  				this.LLcounter++;
78  			rightRotation(node);
79  		}
80  	}
81  
82  }
