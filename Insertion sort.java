/**
 * Формат входного файла
 * В первой строке входного файла содержится число n (1<= n <= 1000) - число элементов в массиве.
 * Во второй строке находятся n различных целых чисел, по модулю не превосходящих 10^9.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int[] array = new int[in.nextInt()];        
        for(int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        in.close();        
        for(int j = 0; j < array.length; j++){            
            int i = j - 1;
            while(i >= 0 && array[i] > array[i+1]) {
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
                i--;                 
            }
        }        
        FileOutputStream fos = new FileOutputStream("output.txt");
        PrintStream out = new PrintStream(fos);
        for(int i = 0; i < array.length; i++) {
            //System.out.print(array[i] + " ");
            out.print(array[i] + " ");
        }
        out.close();
    }
}
