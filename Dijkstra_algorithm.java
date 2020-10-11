import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra_algorithm {
	public static void main(String[] args) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
		Random rand = new Random();
		for (int i = 0; i < 10; i++)
			priorityQueue.add(rand.nextInt(10));

		//내부적으로 Heap모양의 트리로 구성되어있으므로 완전히 정렬된 오름차순으로 나오지는 않는다 (단 맨앞이 최소값임은 보장)
		priorityQueue.forEach(s-> System.out.print(s+" "));
		System.out.println();
		for (int i = 0; i < 10; i++)
			System.out.print(priorityQueue.poll()+" ");

		Graph g = new Graph(8);
		g.input(1, 2, 3);
		g.input(1, 5, 4);
		g.input(1, 4, 4);
		g.input(2, 3, 2);
		g.input(3, 4, 1);
		g.input(4, 5, 2);
		g.input(5, 6, 4);
		g.input(4, 7, 6);
		g.input(7, 6, 3);
		g.input(3, 8, 3);
		g.input(6, 8, 2);
		g.dijkstra(1);
	}
	static class Edge implements Comparable<Dijkstra.Edge>{
		int v, weight;

		public Edge(int v, int weight){
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Dijkstra.Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return weight+" ";
		}
	}
}

class Dijkstra
{
	static class Edge implements Comparable<Edge>{
		int v, weight;

		public Edge(int v, int weight){
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return weight+" ";
		}
	}
	public static void Graph() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<Edge>[] adj = new ArrayList[V];
		for (int i = 0; i  < V; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < E; i++)
			adj[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] check = new boolean[V];
		Edge[] D = new Edge[V];

		for (int i = 0; i < V; i++){
			if (i == 0)
				D[i] = new Edge(i, 0);
			else
				D[i] = new Edge(i, Integer.MAX_VALUE);
			pq.add(D[i]);
		}

		while (!pq.isEmpty())
		{
			Edge edge = pq.poll();

			for (Edge nextE : adj[edge.v]){
				if (!check[nextE.v] && D[nextE.v].weight < D[edge.v].weight + nextE.weight){
					D[nextE.v].weight = D[edge.v].weight + nextE.weight;
					pq.remove(D[nextE.v]);
					pq.add(D[nextE.v]);
				}
			}
			check[edge.v] = true;
		}
		System.out.println(Arrays.toString(D));
	}
}

class Graph
{
	private int N;
	private int[][] maps;

	public Graph(int n)
	{
		this.N = n;
		maps = new int[N+1][N+1];
	}

	public void input (int i, int j, int w)
	{
		maps[i][j] = w;
		maps[j][i] = w;
	}

	public void dijkstra(int v)
	{
		int[] distances = new int[N+1];
		boolean[] visited = new boolean[N+1];

		for (int i = 1; i < N+1; i++)
			distances[i] = Integer.MAX_VALUE;

		distances[v] = 0;
		visited[v] = true;

		for (int i = 1; i < N+1; i++)
		{
			if (!visited[i] && maps[v][i] != 0) //방문했고 0이 아니라면 (방문했으면 무한값은 아니다)
				distances[i] = maps[v][i];
		}

		for (int all = 0; all < N-1; all++)
		{
			int min = Integer.MAX_VALUE;
			int min_idx = -1;

			for (int i = 1; i < N+1; i++) {
				if (!visited[i] && distances[i] != Integer.MAX_VALUE) {
					if (distances[i] < min){
						min = distances[i];
						min_idx = i;
					}
				}
			}
			visited[min_idx] = true;

			for (int i = 1; i < N+1; i++) {
				if (!visited[i] && maps[min_idx][i] != 0){
					if (distances[i] > distances[min_idx] + maps[min_idx][i])
					{
						distances[i] = distances[min_idx] + maps[min_idx][i];
					}
				}
			}
			for (int i = 1; i < N+1; i++)
				System.out.print(distances[i] + " ");
			System.out.println();
		}
	}
}
