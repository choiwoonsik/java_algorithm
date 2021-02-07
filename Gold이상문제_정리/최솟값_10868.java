package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 최솟값_10868 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder str = new StringBuilder();
	static int N, M, height, last;
	static int[] tree, A;
	public static void main(String[] args) throws IOException {
		start();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int min = find(1,1, last, left, right);
			str.append(min).append("\n");
		}
		System.out.print(str);
	}

	private static void start() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N+1];
		tree = new int[N*4];
		Arrays.fill(tree, Integer.MAX_VALUE);
		height = (int)Math.ceil(Math.log10(N) / Math.log10(2));
		System.out.println(height);
		last = (int) Math.pow(2, height);
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		init(1, 1, last);
	}
	private static int init(int index, int start, int end)
	{
		if (start > N) return Integer.MAX_VALUE;
		else if (start == end)
			tree[index] = A[start];
		else {
			int mid = (start+end)/2;
			tree[index] = Math.min(init(index*2, start, mid), init(index*2+1, mid+1, end));
		}
		return tree[index];
	}
	private static int find(int index, int start, int end, int left, int right)
	{
		if (left > end || right < start) return Integer.MAX_VALUE;
		else if (left <= start && end <= right) return tree[index];
		else {
			int mid = (start + end)/2;
			return Math.min(find(index*2, start, mid, left, right),
					find(index*2+1, mid+1, end, left, right));
		}
	}
}
