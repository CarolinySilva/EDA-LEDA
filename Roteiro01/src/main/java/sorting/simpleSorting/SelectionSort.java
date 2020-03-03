package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {


13      @Override
14      public void sort(T[] array, int leftIndex, int rightIndex) {
15  
16          if (this.canBeSorted(array, leftIndex, rightIndex)) {
17              for (int i = leftIndex; i < rightIndex; i++) {
18                  int elementIndex = getSmallerElementIndex(array, i, rightIndex);
19                  Util.swap(array, i, elementIndex);
20              }
21          }
22  
23      }
24  
25      private int getSmallerElementIndex(T[] array, int leftIndex, int rightIndex) {
26          int result = leftIndex;
27  
28          for (int i = leftIndex; i <= rightIndex; i++) {
29              if (array[i].compareTo(array[result]) < 0) {
30                  result = i;
31              }
32          }
33  
34          return result;
35  
36      }
37  
38      private boolean canBeSorted(T[] array, int leftIndex, int rightIndex) {
39          boolean result = true;
40  
41          result = !this.hasNulls(array);
42  
43          if (array == null || array.length <= 0) {
44              result = false;
45          } else if (leftIndex >= rightIndex || leftIndex < 0) {
46              result = false;
47          } else if (rightIndex > array.length) {
48              result = false;
49          }
50  
51          return result;
52      }
53  
54      private boolean hasNulls(T[] array) {
55          boolean result = false;
56  
57          int i = 0;
58  
59          while (!result && i < array.length) {
60              if (array[i] == null) {
61                  result = true;
62              }
63              i++;
64          }
65  
66          return result;
67      }
68  }
