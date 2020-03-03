package problems;

import java.util.Arrays;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
14  public class FloorCeilBinarySearch implements FloorCeil {
	@Override
17  	public Integer floor(Integer[] array, Integer x) {
18  		
19  		Integer floor = null;
20  		
21  		if (array != null && x != null) {
22  		
23  			int left = 0;
24  			int rigth = array.length - 1;
25  			
26  	
27  			while (left <= rigth) {
28  	
29  				int meio = (left + rigth)/2;
30  	
31  				if (array[meio].compareTo(x) == 0) {
32  					
33  					rigth = meio - 1;
34  					floor = array[meio];
35  					
36  				} else if (array[meio].compareTo(x) < 0) {
37  					
38  					left = meio + 1;
39  					
40  					if (floor == null) {
41  						
42  						floor = array[meio];
43  						
44  					} else {
45  						
46  						floor = Integer.max(floor, array[meio]);
47  						
48  					}
49  					
50  				} else {
51  					
52  					rigth = meio - 1;
53  					
54  				}
55  	
56  			}
57  			
58  		}
59  
60  		return floor;
61  
62  	}
63  
64  	@Override
65  	public Integer ceil(Integer[] array, Integer x) {
66  		
67  		Integer ceil = null;
68  		
69  		if(array != null && x != null) {
70  			
71  			int left = 0;
72  			int rigth = array.length - 1;
73  			
74  			while (left <= rigth) {
75  	
76  				int meio = (left + rigth)/2;
77  				
78  				if (array[meio].compareTo(x) == 0) {
79  					
80  					rigth = meio - 1;
81  					ceil = array[meio];
82  					
83  				} else if (array[meio].compareTo(x) < 0) {
84  					
85  					left = meio + 1;
86  					
87  				} else {
88  					
89  					rigth = meio - 1;
90  					
91  					if(ceil == null) {
92  						
93  						ceil = array[meio];
94  						
95  					} else {
96  						
97  						ceil = Integer.min(ceil, array[meio]);
98  						
99  					}
100 					
101 				}
102 	
103 			}
104 			
105 		}
106 
107 		return ceil;
108 
109 	}
110 
111 }
				} else {
					res = floor(array, x, middle + 1, rightIndex);
				}

			}

		}
		return res;

	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		Arrays.sort(array);
		return ceil(array, x, 0, array.length - 1);
	}

	public static Integer ceil(Integer[] array, Integer x, int leftIndex,
			int rightIndex) {
		Integer res = null;

		if (leftIndex <= rightIndex) {

			int middle = (leftIndex + rightIndex) / 2;

			if (x.equals(array[middle])) {
				res = x;

			} else if (x < array[middle]) {

				if (leftIndex == rightIndex) {
					res = array[leftIndex];

				} else {
					res = ceil(array, x, leftIndex, middle - 1);
				}

			} else {

				if (middle < array.length - 1) {
					if (array[middle + 1] > x) {
						res = array[middle + 1];
					} else {
						res = ceil(array, x, middle + 1, rightIndex);
					}
				}

			}

		}
		return res;

	}

}
