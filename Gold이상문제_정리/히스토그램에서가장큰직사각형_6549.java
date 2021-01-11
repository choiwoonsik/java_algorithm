package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 히스토그램에서가장큰직사각형_6549 {
	static int N;
	static int[] A;
	static int[] tree;
	static int last;
	public static void main(String[] args) throws IOException {
		/*
		전체 사각형 배열에 대해서 높이가 가장 낮은 곳의 인덱스를 찾고
		해당 인덱스를 제외한 좌,우의 넓이를 봐서 더 넓은 넓이를 갖는 직사각형을 찾는다
		*/

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder str = new StringBuilder();
		StringTokenizer st;

		while (true)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			A = new int[N+1];
			tree = new int[N*4];

			for (int i = 1; i <= N; i++)
				A[i] = Integer.parseInt(st.nextToken());

			int h = (int) Math.ceil(Math.log10(N) / Math.log10(2));
			last = (int) Math.pow(2, h);

			// 구간에서 높이가 가장 낮은곳의 인덱스를 트리에 저장
			init(1, 1, last);
			str.append(findMaxArea(1, 1, N)).append("\n");
		}
		System.out.print(str);
	}

	private static int init(int node, int start, int end) {
		if (start > N)
			return -1;
		if (start == end)
			tree[node] = start;
		else {
			int mid = (start + end) / 2;
			int leftIndex = init(node * 2, start, mid);
			int rightIndex = init(node * 2 + 1, mid + 1, end);

			if (leftIndex == -1)
				tree[node] = rightIndex;
			else if (rightIndex == -1)
				tree[node] = leftIndex;
			else
				tree[node] = A[leftIndex] < A[rightIndex] ? leftIndex : rightIndex;
		}
		return tree[node];
	}

	private static int findMindIndex(int node, int start, int end, int left, int right)
	{
		// 1~N이 left~right -> 전체 범위에 겹치지 않는다면 = 해당하지 않는 넓이 부분
		if (end < left || right < start)
			return -1;

		// 해당 범위에 해당하는 트리에 저장된 최소높이
		if (left <= start && end <= right)
			return tree[node];

		int mid = (start+end)/2;
		int leftIndex = findMindIndex(node*2, start, mid, left, right);
		int rightIndex = findMindIndex(node*2+1, mid+1, end, left, right);
		if (leftIndex == -1)
			return rightIndex;
		else if (rightIndex == -1)
			return leftIndex;
		// 높이가 더 낮다면
		else return (A[leftIndex] < A[rightIndex] ? leftIndex : rightIndex);
	}

	private static long findMaxArea(int node, int start, int end)
	{
		// 가장 낮은 높이의 인덱스
		int index = findMindIndex(1, 1, last, start, end);
		long area = (long) A[index] * (end - start + 1);

		// 왼쪽
		if (index - 1 >= start) {
			area = Math.max(area, findMaxArea(node*2, start, index-1));
		}
		// 오른쪽
		if (index + 1 <= end) {
			area = Math.max(area, findMaxArea(node*2+1, index+1, end));
		}

		return area;
	}
}
