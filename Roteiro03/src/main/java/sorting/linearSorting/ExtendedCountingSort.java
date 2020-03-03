package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {
12  
13  	@Override
14  	public void sort(Integer[] array, int leftIndex, int rightIndex) {
15  		if (leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
16  			int max = valorMax(array,leftIndex,rightIndex);
17  			int min = valorMin(array,leftIndex,rightIndex);
18  			CountingSortNeg(array,leftIndex,rightIndex,max,min);
19  		}
20  	}
21  
22  	private void CountingSortNeg(Integer[] array, int leftIndex, int rightIndex, int max, int min) {
23  		int[] aux = new int[max-min+1];
24  
25  		for (int i = leftIndex; i <= rightIndex; i++) {
26  			aux[array[i] - min] += 1;
27  		}
28  
29  		for (int i = 1; i < aux.length; i++) {
30  			aux[i] += aux[i-1];
31  		}
32  
33  		Integer[] result = new Integer[array.length];
34  
35  		for (int i = 0; i < result.length; i++) {
36  			result[i] = array[i];
37  		}
38  
39  		for (int i = rightIndex; i >= leftIndex; i--) {
40  			result[aux[array[i] - min] - 1 + leftIndex] = array[i];
41  			aux[array[i] - min] -= 1;
42  		}
43  
44  		for (int i = 0; i < array.length; i++) {
45  			array[i] = result[i];
46  		}
47  	}
48  
49  	private int valorMax(Integer[] array, int leftIndex, int rightIndex) {
50  		int valorMaximo = array[leftIndex];
51  
52  		for (int i = leftIndex+1; i <= rightIndex; i++) {
53  			if (valorMaximo < array[i]) {
54  				valorMaximo = array[i];
55  			}
56  		}
57  
58  		return valorMaximo;
59  	}
60  
61  	private int valorMin(Integer[] array, int leftIndex, int rightIndex) {
62  		int valorMinimo = array[leftIndex];
63  
64  		for (int i = leftIndex + 1; i <= rightIndex; i++) {
65  			if (valorMinimo > array[i]) {
66  				valorMinimo = array[i];
67  			}
68  		}
69  
70  		return valorMinimo;
71  	}
72  
73  }
