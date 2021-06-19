package algorithm_정리;

import java.util.*;

public class graph {
	public static void main(String[] args) {
		Dictionary<Character, List<Character>> graph = new Hashtable<>();
		graph.put('A', List.of('B', 'C'));
		graph.put('B', List.of('A', 'D'));
		graph.put('C', List.of('A', 'G', 'H', 'I'));
		graph.put('D', List.of('B', 'E', 'F'));
		graph.put('E', List.of('D'));
		graph.put('F', List.of('D'));
		graph.put('G', List.of('C'));
		graph.put('H', List.of('C'));
		graph.put('I', List.of('C', 'J'));
		graph.put('J', List.of('I'));

		System.out.println("BFS");
		BFS(graph, 'A').forEach(s -> System.out.print(s+" "));
		System.out.println("\nDFS");
		DFS(graph, 'A').forEach(s -> System.out.print(s+" "));
	}
	private static Queue<Character> DFS(Dictionary<Character, List<Character>> graph, Character start)
	{
		Stack<Character> needVisit = new Stack<>();
		Queue<Character> visited = new LinkedList<>();

		needVisit.push(start);
		int count = 0;
		while (!needVisit.isEmpty())
		{
			count += 1;
			Character now = needVisit.pop();
			if (!visited.contains(now))
			{
				visited.add(now);
				graph.get(now).forEach(needVisit::push);
			}
		}
		System.out.println(count);
		return visited;
	}

	private static Queue<Character> BFS(Dictionary<Character, List<Character>> graph, Character start)
	{
		Queue<Character> needVisit = new LinkedList<>();
		Queue<Character> visited = new LinkedList<>();

		needVisit.add(start);
		int count = 0;
		while (!needVisit.isEmpty())
		{
			count += 1;
			Character now = needVisit.poll();
			if (!visited.contains(now)){
				visited.add(now);
				needVisit.addAll(graph.get(now));
			}
		}
		System.out.println(count);
		return visited;
	}
}
