package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends
		AbstractSorting<T> {

18      @Override
19      public void sort(T[] array, int leftIndex, int rightIndex) {
20  
21          if (this.canBeSorted(array, leftIndex, rightIndex)) {
22  
23              boolean changed = true;
24  
25              while (changed && leftIndex < rightIndex) {
26                  changed = this.bubbleStep(array, leftIndex, rightIndex);
27                  rightIndex -= 1;
28  
29                  if (!changed) {
30                      break;
31                  }
32                  changed = this.bubbleStepBackwards(array, leftIndex, rightIndex);
33                  leftIndex += 1;
34  
35              }
36          }
37  
38      }
39  
40      private boolean bubbleStepBackwards(T[] array, int leftIndex, int rightIndex) {
41  
42          boolean changed = false;
43  
44          for (int i = rightIndex; i > leftIndex; i--) {
45  
46              if (array[i].compareTo(array[i - 1]) < 0) {
47                  Util.swap(array, i, i - 1);
48                  changed = true;
49              }
50  
51          }
52  
53          return changed;
54      }
55  
56      private boolean bubbleStep(T[] array, int leftIndex, int rightIndex) {
57  
58          boolean changed = false;
59  
60          for (int i = leftIndex; i < rightIndex; i++) {
61  
62              if (array[i].compareTo(array[i + 1]) > 0) {
63                  Util.swap(array, i, i + 1);
64                  changed = true;
65              }
66  
67          }
68  
69          return changed;
70      }
71  
72      private boolean canBeSorted(T[] array, int leftIndex, int rightIndex) {
73          boolean result = true;
74  
75          result = !this.hasNulls(array);
76  
77          if (array == null || array.length <= 0) {
78              result = false;
79          } else if (leftIndex >= rightIndex || leftIndex < 0) {
80              result = false;
81          } else if (rightIndex > array.length) {
82              result = false;
83          }
84  
85          return result;
86      }
87  
88      private boolean hasNulls(T[] array) {
89          boolean result = false;
90  
91          int i = 0;
92  
93          while (!result && i < array.length) {
94              if (array[i] == null) {
95                  result = true;
96              }
97              i++;
98          }
99  
100         return result;
101     }
102 }

