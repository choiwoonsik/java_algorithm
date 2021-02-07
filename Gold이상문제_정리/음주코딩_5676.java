package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 음주코딩_5676 {
	static int N, K, height, last;
	static int[] A, tree;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder str;
	public static void main(String[] args) throws IOException {
		String firstLine;
		while((firstLine = br.readLine()) != null)
		{
			st = new StringTokenizer(firstLine);
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			str = new StringBuilder();
			A = new int[N+1];
			tree = new int[N*4];
			Arrays.fill(tree, 1);
			height = (int) Math.ceil(Math.log10(N)/Math.log10(2));
			last = (int) Math.pow(2, height);
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int n = Integer.parseInt(st.nextToken());
				A[i] = Integer.compare(n, 0);
			}
			init(1, 1, last);
			play();
			System.out.println(str);
		}
	}

	private static void play() throws IOException {
		for (int p = 0; p < K; p++) {
			st = new StringTokenizer(br.readLine());
			char type = st.nextToken().charAt(0);
			if (type == 'C')
			{
				int changed_idx = Integer.parseInt(st.nextToken());
				int after = Integer.parseInt(st.nextToken());
				after = Integer.compare(after, 0);
				update(1, 1, last, changed_idx, after);
			}
			else if (type == 'P')
			{
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				int result = multiply(1, 1, last, left, right);
				char c = result < 0 ? '-' : (result == 0 ? '0' : '+');
				str.append(c);
			}
		}
	}

	private static int init(int index, int start, int end)
	{
		if (start > N) return 1;
		else if (start == end) tree[index] = A[start];
		else {
			int mid = (start+end)/2;
			tree[index] = init(index*2, start, mid) * init(index*2+1, mid+1, end);
		}
		return tree[index];
	}
	private static int multiply(int index, int start, int end, int left, int right)
	{
		if (left > end || right < start) return 1;
		else if (left <= start && end <= right)
			return tree[index];
		else {
			int mid = (start+end)/2;
			return multiply(index*2, start, mid, left, right)
				* multiply(index*2+1, mid+1, end, left, right);
		}
	}
	private static int update(int index, int start, int end, int changed_idx, int after)
	{
		if (changed_idx < start || changed_idx > end) return tree[index];
		else if (start == end)
			tree[index] = after;
		else {
			int mid = (start + end) / 2;
			tree[index] = update(index * 2, start, mid, changed_idx, after)
						* update(index * 2 + 1, mid+1, end, changed_idx, after);
		}
		return tree[index];
	}
}
