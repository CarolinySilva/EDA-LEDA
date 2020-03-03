package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
 	@Override
23  	public T getOrderStatistics(T[] array, int k) {
24  		
25  		T result = null;
26  		
27  		if(array != null && array.length > 0) {
28  		
29  			T menor = array[0];
30  	
31  			for (int j = 0; j < array.length; j++) {
32  	
33  				if(array[j].compareTo(menor) < 0) {
34  	
35  					menor = array[j];
36  	
37  				}
38  	
39  			}
40  	
41  			result = getOrderStatistics(array, menor, --k);
42  			
43  		}
44  		
45  		return result;
46  
47  	}
48  	
49  	public T getOrderStatistics(T[] array, T less, int k) {
50  		
51  		T result;
52  		
53  		if (k == 0) {
54  			
55  			result = less;
56  			
57  		} else {
58  
59  			T menor = null;
60  
61  			for (int j = 0; j < array.length; j++) {
62  
63  				if(array[j].compareTo(less) > 0) {
64  					
65  					if (menor == null) {
66  						
67  						menor = array[j];
68  						
69  					} else {
70  						
71  						if (array[j].compareTo(menor) < 0) {
72  							
73  							menor = array[j];
74  							
75  						}
76  						
77  					}
78  
79  				}
80  
81  			}
82  			
83  			result = getOrderStatistics(array, menor, --k);
84  			
85  		}
86  		
87  		return result;
88  		
89  	}
90  
91  }
