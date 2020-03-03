package sorting.divideAndConquer.threeWayQuicksort;

import util.*;
import sorting.AbstractSorting;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot e os
	 * maiores a direita do pivot, e depois aplicamos a mesma estrategia
	 * recursivamente na particao a esquerda do pivot e na particao a direita do
	 * pivot.
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do
	 * quicksort pode ser otimizada para lidar de forma mais eficiente com isso.
	 * Essa melhoria eh conhecida como quicksort tree way e consiste da seguinte
	 * ideia: - selecione o pivot e particione o array de forma que * arr[l..i]
	 * contem elementos menores que o pivot * arr[i+1..j-1] contem elementos
	 * iguais ao pivot. * arr[j..r] contem elementos maiores do que o pivot.
	 * 
	 * Obviamente, ao final do particionamento, existe necessidade apenas de
	 * ordenar as particoes contendo elementos menores e maiores do que o pivot.
	 * Isso eh feito recursivamente.
	 **/

 	@Override
28  	public void sort(T[] array, int leftIndex, int rightIndex) {
29  		if (leftIndex >= 0 && rightIndex <= array.length - 1) {
30  			if (leftIndex < rightIndex) {
31  				int[] pos_pivot = particiona3(array, leftIndex, rightIndex);
32  				sort(array, leftIndex, pos_pivot[0] - 1);
33  				sort(array, pos_pivot[1] + 1, rightIndex);
34  			}
35  		}
36  	}
37  
38  	private int[] particiona3(T[] array, int leftIndex, int rightIndex) {
39  		T pivot = array[leftIndex];
40  		int p = leftIndex;
41  		int[] new_pivots = new int[2];
42  
43  		for (int j = leftIndex+1;j <= rightIndex;j++) {
44  			if (array[j].compareTo(pivot) < 0) {
45  				p += 1;
46  				Util.swap(array,p,j);
47  			}
48  		}
49  
50  		Util.swap(array,p,leftIndex);
51  
52  		new_pivots[0] = p;
53  
54  		for (int j = p +1; j <= rightIndex; j++) {
55  			if (array[j].compareTo(pivot) == 0) {
56  				p += 1;
57  				Util.swap(array,p,j);
58  			}
59  		}
60  		new_pivots[1] = p;
61  
62  		return new_pivots;
63  	}
64  
65  }
