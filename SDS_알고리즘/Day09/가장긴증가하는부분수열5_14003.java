package SDS_알고리즘.Day09;
/*
15
10 20 5 30 20 50 30 60 65 100 -1 -2 3 -5 -10
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열5_14003 {
	static int N;
	static int LAST = 0;
	static int[] origin;
	static int[] A;
	static int[] traceIndexA;
	static Stack<Integer> traceIndexStack = new Stack<>();
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		origin = new int[N];
		traceIndexA = new int[N];

		st = new StringTokenizer(br.readLine());
		// 배열중 타겟보다 크거나 같은 인덱스를 구한다.
		for (int i = 0; i < N; i++) {
			int curNum = Integer.parseInt(st.nextToken());
			origin[i] = curNum;
			if (i == 0) {
				A[0] = curNum;
				traceIndexA[0] = 0;
				LAST = 1;
			} else {
				int idx = binarySearch(curNum);
				if (idx >= LAST) {
					A[LAST] = curNum;
					traceIndexA[i] = LAST;
					LAST++;
				} else {
					A[idx] = curNum;
					traceIndexA[i] = idx;
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			System.out.print(traceIndexA[i]+" ");
//		}
//		System.out.println();
//		System.out.println(LAST - 1);

		int revseIndex = LAST - 1;
		for (int last = N - 1; last >= 0; last--) {
			if (traceIndexA[last] == revseIndex) {
				traceIndexStack.push(origin[last]);
				revseIndex--;
			}
		}

		answer.append(LAST).append("\n");
		while (!traceIndexStack.isEmpty()) {
			answer.append(traceIndexStack.pop()).append(" ");
		}
		System.out.print(answer);
	}

	private static int binarySearch(int curNum) {
		int left = 0;
		int right = LAST;
		int mid;

		while (left < right) {
			mid = (left + right) / 2;

			if (A[mid] < curNum) {
				left = mid + 1;
			} else if (A[mid] == curNum) {
				right = mid;
				break;
			} else {
				right = mid;
			}
		}

		return right;
	}
}
