package sorting.divideAndConquer;

import util.*;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
11  public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
12  
13  	@Override
14  	public void sort(T[] array, int leftIndex, int rightIndex) {
15  		if (leftIndex >= 0 && rightIndex <= array.length-1) {
16  			if (leftIndex < rightIndex) {
17  				int meio = (leftIndex + rightIndex) / 2;
18  				sort(array,leftIndex,meio);
19  				sort(array,meio+1,rightIndex);
20  				merge(array,leftIndex,meio,rightIndex);
21  			}
22  		}
23  	}
24  
25  	public void merge(T[] array, int leftIndex, int meioIndex, int rightIndex) {
26  
27  		T[] aux = (T[]) new Comparable[array.length];
28  
29  		for (int i = 0; i < array.length; i++) {
30  			aux[i] = array[i];
31  		}
32  
33  		int i = leftIndex;
34  		int j = meioIndex +1;
35  		int k = leftIndex;
36  
37  		while (i <= meioIndex && j <= rightIndex) {
38  			if (aux[i].compareTo(aux[j]) < 0) {
39  				array[k] = aux[i];
40  				i++;
41  			}
42  			else {
43  				array[k] = aux[j];
44  				j++;
45  			}
46  			k++;
47  		}
48  
49  		while (i <= meioIndex) {
50  			array[k] = aux[i];
51  			i++;
52  			k++;
53  		}
54  
55  		while (j <= rightIndex) {
56  			array[k] = aux[j];
57  			j++;
58  			k++;
59  		}
60  	}
61  }
