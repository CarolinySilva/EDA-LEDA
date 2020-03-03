package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

 public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {
13  
14      @Override
15      public void sort(T[] array, int leftIndex, int rightIndex) {
16  
17          if (this.canBeSorted(array, leftIndex, rightIndex)) {
18              for (int i = leftIndex; i <= rightIndex; i++) {
19                  this.insertion(array, i, leftIndex);
20              }
21          }
22  
23      }
24  
25      private void insertion(T[] array, int index, int leftIndex) {
26  
27          while (index > leftIndex && array[index].compareTo(array[index - 1]) < 0) {
28              Util.swap(array, index, index - 1);
29              index -= 1;
30          }
31  
32      }
33  
34      private boolean canBeSorted(T[] array, int leftIndex, int rightIndex) {
35          boolean result = true;
36  
37          result = !this.hasNulls(array);
38  
39          if (array == null || array.length <= 0) {
40              result = false;
41          } else if (leftIndex >= rightIndex || leftIndex < 0) {
42              result = false;
43          } else if (rightIndex > array.length) {
44              result = false;
45          }
46  
47          return result;
48      }
49  
50      private boolean hasNulls(T[] array) {
51          boolean result = false;
52  
53          int i = 0;
54  
55          while (!result && i < array.length) {
56              if (array[i] == null) {
57                  result = true;
58              }
59              i++;
60          }
61  
62          return result;
63      }
64  }
