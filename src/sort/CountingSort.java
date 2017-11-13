//падает с ошибкой java.lang.OutOfMemoryError: Requested array size exceeds VM limit
//на тесте, в котором есть максимально и минимально возможные числа.

/**
 *  В этой задаче Вам нужно будет отсортировать много неотрицательных целых чисел.
 *  
 *  Вам даны два массива, A и B, содержащие соответственно n и m элементов. 
 *  Числа, которые нужно будет отсортировать, имеют вид A[i]*B[j], где 1<=i<=n и 1<=j<=m.
 *  Иными словами, каждый элемент первого массива нужно умножить на каждый элемент второго массива.
 *  Пусть из этих чисел получится отсортированная последовательность C длиной n*m. 
 *  
 *  Выведите сумму каждого десятого элемента этой последовательности (то есть, C[1] + C[11] + C[21] + ...).
 *  
 *  Формат входного файла
 *  В первой строке содержатся числа n и m (1 <= n, m <= 6000) — размеры массивов. 
 *  Во второй строке содержится n чисел — элементы массива A. 
 *  Аналогично, в третьей строке содержится m чисел — элементы массива B. 
 *  Элементы массива неотрицательны и не превосходят 40000.
 *  
 *  Формат выходного файла
 *  Выведите одно число — сумму каждого десятого элемента последовательности, 
 *  полученной сортировкой попарных произведений элементов массивов A и B.
 *  
 *  Пример
 *  input
 *  4 4
 *  7 1 4 9
 *  2 7 8 11 
 *  output
 *  51
 *  Пояснение к примеру: 
 *  Неотсортированная последовательность С выглядит следующим образом:
 *  [14, 2, 8, 18, 49, 7, 28, 63, 56, 8, 32, 72, 77, 11, 44, 99].
 *  Отсортировав ее, получим:
 *  [<b>2</b>, 7, 8, 8, 11, 14, 18, 28, 32, 44, <b>49</b>, 56, 63, 72, 77, 99].
 *  Жирным выделены первый и одиннадцатый элементы последовательности, 
 *  при этом двадцать первого элемента в ней нет. Их сумма — 51 — и будет ответом. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	public static int[] initializeArray(Scanner in) {
		int[] a = new int[in.nextInt()]; 
        int[] b = new int[in.nextInt()];
        int n = a.length;
        int m = b.length;
        for(int i = 0; i < n; i++) {
       	 a[i] = in.nextInt();       	 
        }
        for(int i = 0; i < m; i++) {
       	 b[i] = in.nextInt();
        }
        in.close();
        int[] c = new int[n * m];
        int k = 0;
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < m; j++) {
        		c[k] = a[i]*b[j];
        		k++;
        	}
        }
        return c;
	}
	
	public static int searchMax(int[] array) {
		int k = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > k) k = array[i];
		}
		return k;
	}
	
	public static void countingSort(int[] a, int[] b, int k) {
		int[] c = new int[k + 1];
		for(int j = 0; j < a.length; j++) {
			c[(int) a[j]]++;
		}
		for(int i = 1; i < k + 1; i++) {
			c[i] += c[i - 1];	
		}
		for(int j = a.length - 1; j >= 0; j--) {
			c[(int) a[j]]--;
			b[(int) c[(int) a[j]]] = a[j];			
		}
	}
    public static void main(String[] args) throws FileNotFoundException { 
    	Scanner in = new Scanner(new File("input.txt"));
    	int[] array = initializeArray(in);
    	in.close();
    	
		int k = searchMax(array);
    	
		int[] sortArray = new int[array.length];
    	countingSort(array, sortArray, k);
    	
		int result = sortArray[0];
    	for(int i = 1; i < sortArray.length; i++) {
    		if(i % 10 == 0) {
    			result += sortArray[i];
    		}
    	}
    	FileOutputStream fos = new FileOutputStream("output.txt");
        PrintStream out = new PrintStream(fos);
        out.print(result);
        out.close();
    }
}

