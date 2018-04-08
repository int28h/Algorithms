/*
Входные данные: В первой строке указаны два числа: число вершин и число дуг графа. 
Далее идут строки с описанием дуг. Их количество равно числу дуг. 
В каждой строке указаны 2 числа: исходящая вершина, входящая вершина. 
В последней строке указан номер вершины, с которой начинается обход.

Выходные данные: список вершин в том порядке, в котором они были посещены.

Sample Input:
4 6
0 1
0 2
1 2
2 0
2 3
3 3
2
Sample Output:
2 0 3 1

Sample Input:
4 8
1 2
1 3
1 4
2 4
3 1
3 2
3 4
4 2
3
Sample Output:
3 1 2 4
*/
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	private static List<Arc> arcs;
	private static int vertexesCount;
	private static int arcsCount;
	private static int taskFrom; //стартовая вершина
	
	private static List<Integer> wasVisited = new LinkedList<>(); //посещенные вершины
	private static Queue<Arc> queue = new LinkedList<>(); //очередь пригодных для использования ребер
	
	private static class Arc {
		int from;
		int to;
		
		private Arc(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}	
	
	private static void initialize(Scanner in) {
		vertexesCount = in.nextInt();
		arcsCount = in.nextInt();
		arcs = new LinkedList<>();
		for(int i = 0; i < arcsCount; i++) {
			arcs.add(new Arc(in.nextInt(), in.nextInt()));
		}
		taskFrom = in.nextInt();
	}
	
	private static void bfs(int v) {
		wasVisited.add(v);
		
		//удаляем из очереди все дуги, ведущие в текущую вершину
		Iterator<Arc> it = queue.iterator();
		while(it.hasNext()) {
			if(it.next().to == v) {
				it.remove();
			}
		}
		
		List<Arc> currentArcsList = new LinkedList<>();
		
		//составляем список возможных для использования дуг
		for(Arc a : arcs) {
			if (a.from == v && !wasVisited.contains(a.to)) {
				currentArcsList.add(a);
			}
		}
		
		if(currentArcsList.size() != 0) {
			//все найденные - в очередь
			queue.addAll(currentArcsList);			
			bfs(queue.poll().to);
		} else {
			//если не найдено ни одной подходящей дуги...
			if(!queue.isEmpty()) {
				//...то она берется из очереди				
				bfs(queue.poll().to);
			} else {
				return;
			}
			return;
		}
	}
	
	public static void main(final String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		initialize(in);		
		
		if(arcsCount != 0) {
			bfs(taskFrom);
			for(int i : wasVisited) {
				System.out.print(i + " ");
			}
		} else {
			System.out.println("Arcscount = 0");
		}
	}
}