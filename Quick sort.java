//Время работы в среднем O(nlog(n)).
//Использует О(1) доппамяти.

/**
 * Дан массив целых чисел. Ваша задача — отсортировать его в порядке неубывания с помощью быстрой сортировки.
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
	private static int split(int[] array, int l, int r) {
		int i = l, j = r;
        int pivot = array[(l + r) / 2];
        while (i <= j) {
            while (array[i] < pivot)
                i++;
            while (array[j] > pivot)
                j--;
            if (i <= j) {
            	int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        return i;
	}
	
	public static void quickSort(int array[], int l, int r) {
		int index = split(array, l, r);
        if (l < index - 1)
            quickSort(array, l, index - 1);
        if (index < r)
            quickSort(array, index, r);
	}
    
    public static void main(String[] args) throws FileNotFoundException {    	
    	Scanner in = new Scanner(new File("input.txt"));
        int[] array = new int[in.nextInt()];
        for(int i = 0; i < array.length; i++) {
        	array[i] = in.nextInt();
        }
        quickSort(array, 0, array.length - 1);
        FileOutputStream fos = new FileOutputStream("output.txt");
        PrintStream out = new PrintStream(fos);
        for(int i = 0; i < array.length; i++) {
        	if (i == 0) {
        		out.print(array[i]);
        	} else {
        		out.print(" " + array[i]);
        	}
        }
        out.close();
    }
}
