package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {
12  
13     @Override
14     public void sort(Integer[] array, int leftIndex, int rightIndex) {
15        if (leftIndex >= 0 && rightIndex < array.length && array.length != 0) {
16           int max = valorMax(array, leftIndex, rightIndex);
17           CountingSortOficial(array, leftIndex, rightIndex, max);
18        }
19     }
20  
21     private void CountingSortOficial(Integer[] array, int leftIndex, int rightIndex, int max) {
22        int[] aux = new int[max];
23  
24        for (int i = leftIndex; i <= rightIndex; i++) {
25  		  aux[array[i] - 1] += 1;
26        }
27  
28        for (int i = 1; i < aux.length; i++) {
29  		  aux[i] += aux[i - 1];
30        }
31  
32        Integer[] b = new Integer[array.length];
33  
34        for (int i = 0; i < b.length; i++) {
35           b[i] = array[i];
36        }
37  
38        for (int i = rightIndex; i >= leftIndex; i--) {
39           b[aux[array[i] - 1] - 1 + leftIndex] = array[i];
40  		  aux[array[i] - 1] -= 1;
41        }
42  
43        for (int i = 0; i < array.length; i++) {
44           array[i] = b[i];
45        }
46     }
47  
48     private int valorMax(Integer[] array, int leftIndex, int rightIndex) {
49        int valorMaximo = array[leftIndex];
50  
51        for (int i = leftIndex + 1; i <= rightIndex; i++) {
52           if (valorMaximo < array[i]) {
53              valorMaximo = array[i];
54           }
55        }
56  
57        return valorMaximo;
58     }
59  
60  }
