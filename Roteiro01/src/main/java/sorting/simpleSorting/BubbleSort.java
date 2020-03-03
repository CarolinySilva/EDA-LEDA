package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {
12  
13      @Override
14      public void sort(T[] array, int leftIndex, int rightIndex) {
15  
16          if (this.canBeSorted(array, leftIndex, rightIndex)) {
17              boolean changed = true;
18  
19              while (changed && leftIndex < rightIndex) {
20                  changed = this.bubbleStep(array, leftIndex, rightIndex);
21                  rightIndex -= 1;
22              }
23          }
24  
25      }
26  
27      private boolean bubbleStep(T[] array, int leftIndex, int rightIndex) {
28  
29          boolean changed = false;
30  
31          for (int i = leftIndex; i < rightIndex; i++) {
32  
33              if (array[i].compareTo(array[i + 1]) > 0) {
34                  Util.swap(array, i, i + 1);
35                  changed = true;
36              }
37  
38          }
39  
40          return changed;
41      }
42  
43      private boolean canBeSorted(T[] array, int leftIndex, int rightIndex) {
44          boolean result = true;
45  
46          result = !this.hasNulls(array);
47  
48          if (array == null || array.length <= 0) {
49              result = false;
50          } else if (leftIndex >= rightIndex || leftIndex < 0) {
51              result = false;
52          } else if (rightIndex > array.length) {
53              result = false;
54          }
55  
56          return result;
57      }
58  
59      private boolean hasNulls(T[] array) {
60          boolean result = false;
61  
62          int i = 0;
63  
64          while (!result && i < array.length) {
65              if (array[i] == null) {
66                  result = true;
67              }
68              i++;
69          }
70  
71          return result;
72      }
}
