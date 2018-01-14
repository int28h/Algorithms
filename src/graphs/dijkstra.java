/*
Реализуйте алгоритм Дейкстры поиска кратчайшего пути в графе.

Входные данные: В первой строке указаны два числа: число вершин и число ребер графа. 
Далее идут строки с описанием ребер. Их количество равно числу ребер. 
В каждой строке указаны 3 числа: исходящая вершина, входящая вершина, вес ребра. 
В последней строке указаны 2 номера вершины: начальная и конечная вершина, кратчайший путь между которыми нужно найти.

Выходные данные: минимальное расстояние между заданными вершинами. Если пути нет, то нужно вернуть -1.

Sample Input:

4 8
1 2 6
1 3 2
1 4 10
2 4 4
3 1 5
3 2 3
3 4 8
4 2 1
1 4

Sample Output:

9
*/
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static List<Rib> ribs;
	private static int peaksCount, ribsCount;
	private static int taskFrom, taskTo;
	
	private static class Rib {
		int from;
		int to;
		int weight;
		
		private Rib(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}	
	
	private static void initialize(Scanner in) {
		peaksCount = in.nextInt();
		ribsCount = in.nextInt();
		ribs = new LinkedList<>();
		for(int i = 0; i < ribsCount; i++) {
			ribs.add(new Rib(in.nextInt(), in.nextInt(), in.nextInt()));
		}
		taskFrom = in.nextInt();
		taskTo = in.nextInt();
	}
	
	public static void main(final String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		initialize(in);
		
		if(ribsCount != 0) {
			List<Integer> usedPeaks = new LinkedList<>();
			usedPeaks.add(taskFrom);
			int currentPeak = taskFrom;
			int result = 0;	
			
			//костыль на случай, если из заданного старта есть прямой
			//путь в заданный финиш, т.к. итоговый путь может оказаться
			//больше него
			int directLineWeight = 0; 
			for(Rib r : ribs) {
				if(r.from == taskFrom && r.to == taskTo) {
					directLineWeight = r.weight;
				}
			}
			
			while(currentPeak != taskTo && usedPeaks.size() != peaksCount) {
				List<Rib> currentRibsList = new LinkedList<>();
				
				//составляем список возможных для использования ребер
				for(Rib r : ribs) {
					if (r.from == currentPeak && !usedPeaks.contains(r.to)) {
						currentRibsList.add(r);
					}
				}
				
				//если не найдено ни одного подходящего ребра
				if(currentRibsList.size() == 0) {
					System.out.println(-1);
					return;
				}
				ribs.removeAll(currentRibsList);
				
				//ищем в этом списке ребро, с использованием которого 
				//удастся достичь наименьшего НА ДАННЫЙ МОМЕНТ веса
				int minWeight = Integer.MAX_VALUE;
				Rib ribToUse = null;
				for(Rib r : currentRibsList) {
					if(r.weight + result < minWeight) {
						minWeight = r.weight;
						ribToUse = r;
					}
				}
				currentRibsList.remove(ribToUse);
				ribs.addAll(currentRibsList);
				
				//меняем текущие значения для переменных-показателей статуса
				currentPeak = ribToUse.to;
				usedPeaks.add(ribToUse.to);
				result += ribToUse.weight;
				
				//выводит список вершин, обойденных на данный момент
				/*for(int r : usedPeaks) {
					System.out.print(r);
				}
				System.out.println();*/
			}
			
			//если нужного пути не существует, выводится -1
			if(currentPeak != taskTo &&  usedPeaks.size() == peaksCount){
				System.out.println(-1);		
			} else {
				if(directLineWeight != 0 && result > directLineWeight) {
					System.out.println(directLineWeight);
				} else {
					System.out.println(result);
				}
			}
		} else {
			System.out.println(-1);
		}
	}
}