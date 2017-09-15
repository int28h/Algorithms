//https://youtu.be/XaqR3G_NVoo

//Время работы O(nlog(n)).

/**
 * Дан массив целых чисел. Ваша задача — отсортировать его в порядке неубывания с помощью сортировки слиянием.
 *
 * Формат входного файла
 * В первой строке входного файла содержится число n (1<= n <= 10^5) - число элементов в массиве.
 * Во второй строке находятся n различных целых чисел, по модулю не превосходящих 10^9.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	public static int[] merge(int [] a, int[] b) {
		int i = 0, j = 0, k = 0;
		int n = a.length;
		int m = b.length;
		int[] c = new int[n + m];
		while(i < n || j < m) {
			if (j == m || (i < n && a[i] <= b[j])) {
				c[k] = a[i];
				k++;
				i++;
			} else {
				c[k] = b[j];
				k++;
				j++;
			}
		}
		
		return c;
	}
	
	public static int[] mergeSort(int[] array) {
		int n = array.length;
		if(n == 1) return array;
		int k = 0;
		int[] l = new int[n / 2]; 
		for(int i = 0; i < l.length; i++) {
			l[i] = array[k];
			k++;
		}
		int[] r = new int[n - l.length];
		for(int i = 0; i < r.length; i++) {
			r[i] = array[k];
			k++;
		}
		l = mergeSort(l);
		r = mergeSort(r);
		return merge(l,r);		
	}

    public static void main(String[] args) throws FileNotFoundException {    	    	
    	Scanner in = new Scanner(new File("input.txt"));
        int[] array = new int[in.nextInt()];
        for(int i = 0; i < array.length; i++) {
        	array[i] = in.nextInt();
        }
        FileOutputStream fos = new FileOutputStream("output.txt");
        PrintStream out = new PrintStream(fos);
        int [] result = mergeSort(array);
        for(int i = 0; i < result.length; i++) {
        	if (i == 0) {
        		out.print(result[i]);
        	} else {
        		out.print(" " + result[i]);
        	}
        }
        out.close();
    }
}
