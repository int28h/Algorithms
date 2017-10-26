/**
 * Дан массив из n элементов, упорядоченный в порядке неубывания, и m запросов: 
 * найти первое и последнее вхождение некоторого числа в массив. 
 * Требуется ответить на эти запросы.
 *
 * Формат входного файла
 * В первой строке входного файла содержится одно число n — размер массива (1 <= n <= 100000). 
 * Во второй строке находятся n чисел в порядке неубывания — элементы массива. 
 * В третьей строке находится число m — число запросов (1 <= m <= 100000). 
 * В следующей строке находятся m чисел — запросы. 
 * Элементы массива и запросы являются целыми числами, неотрицательны и не превышают 10^9.
 *
 * Формат выходного файла
 * Для каждого запроса выведите в отдельной строке номер (индекс) первого и последнего вхождения 
 * этого числа в массив. Ecли числа в массиве нет, выведите два раза -1.
 *
 * Пример
 * input.txt
 * 5
 * 1 1 2 2 2
 * 3
 * 1 2 3
 * 
 * output.txt
 * 1 2
 * 3 5
 * -1 -1
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {	
	public static void binarySearch(int arraySize, int[] array, int key, PrintStream out) {
		int[] output = new int[2];
		int l = -1, r = arraySize;
		boolean isContains = false;
		
		while(r > l + 1) {
			int m = (l + r)/2;
			if(array[m] < key) {
				l = m;
			} else {
				r = m;
			}
			
			if((r < arraySize) && (array[r] == key)){
				isContains = true;
				output[0] = r;
				output[1] = r;
				
				for(int i = r; i > -1; i--) {
					if(array[i] == key) {
						output[0] = i;
					}
				}
				
				for(int i = r; i < arraySize; i++) {
					if(array[i] == key) {
						output[1] = i;
					}
				}
			}	
		}

		if(isContains) {
			out.print((output[0] + 1) + " " + (output[1] + 1));
			out.println();
		} else {
			out.println("-1 -1");
		}
	}
	
	public static void main(String[] args) throws Exception { 
    	 Scanner in = new Scanner(new File("input.txt"));
    	 FileOutputStream fos = new FileOutputStream("output.txt");
    	 PrintStream out = new PrintStream(fos);
    	 
    	 int arraySize = in.nextInt();
    	 int[] array = new int[arraySize];    	 
    	 for (int i = 0; i < arraySize; i++) {
    		 array[i] = in.nextInt();
    	 }
    	 
    	 int requestsCount = in.nextInt();
    	 int[] requests = new int[requestsCount];
    	 for (int i = 0; i < requestsCount; i++) {
    		 requests[i] = in.nextInt();
    		 binarySearch(arraySize, array, requests[i], out);
    	 }
    	 
    	 in.close();
    	 out.close();
    }
}

