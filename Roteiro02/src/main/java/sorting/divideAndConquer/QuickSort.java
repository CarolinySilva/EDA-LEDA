package sorting.divideAndConquer;

import util.*;
import sorting.AbstractSorting;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
 public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {
17  
18  	@Override
19  	public void sort(T[] array, int leftIndex, int rightIndex) {
20  		if (leftIndex >= 0 && rightIndex <= array.length-1) {
21  			if (leftIndex < rightIndex) {
22  				int posic_pivot = particionamento(array,leftIndex,rightIndex);
23  				sort(array,leftIndex,posic_pivot-1);
24  				sort(array,posic_pivot+1,rightIndex);
25  			}
26  		}
27  	}
28  
29  	private int particionamento(T[] array, int leftIndex, int rightIndex) {
30  		int i = leftIndex;
31  		T pivot = array[leftIndex];
32  
33  		for (int j = leftIndex + 1; j <= rightIndex; j++) {
34  			if (pivot.compareTo(array[j]) > 0) {
35  				i += 1;
36  				Util.swap(array,i,j);
37  			}
38  		}
39  		Util.swap(array,leftIndex,i);
40  
41  		return i;
42  	}
43  }

