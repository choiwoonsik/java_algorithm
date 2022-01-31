package SDS_알고리즘.Day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
4
1 2
2 3
3 4
4 1
 */
public class 전깃줄2_2568_2 {
	static boolean[] isSeq = new boolean[500002];
	static int[] leftLineArr = new int[500002];
	static int[] indexOrderArr = new int[500002];
	static int N;
	static int[] orders;
	static int LAST = 0;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Arrays.fill(leftLineArr, -1);
		Arrays.fill(indexOrderArr, -1);

		N = Integer.parseInt(br.readLine().trim());
		orders = new int[N + 1];
		int tmpLast = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int key = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			leftLineArr[key] = val;
			tmpLast = Math.max(tmpLast, key);
		}
		tmpLast++;

		int total = indexBinarySearch(tmpLast);
		answer.append(total).append("\n");

		traceIndex(tmpLast);

		System.out.print(answer);
	}

	private static int indexBinarySearch(int tmpLast) {
		orders[0] = leftLineArr[0];
		LAST = 0;
		indexOrderArr[0] = 0;

		for (int lKey = 0; lKey < tmpLast; lKey++) {

			if (leftLineArr[lKey] == -1) continue;

			int value = leftLineArr[lKey];

			if (orders[LAST] < value) {
				LAST++;
				orders[LAST] = value;
				indexOrderArr[lKey] = LAST;
			} else {
				int index = binarySearch(value);
				orders[index] = value;
				indexOrderArr[lKey] = index;
			}
		}

		return N - LAST;
	}

	private static int binarySearch(int val) {
		int left = 1;
		int right = LAST;
		int mid;

		while (left < right) {
			mid = (left + right) / 2;

			if (val <= orders[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return right;
	}

	private static void traceIndex(int tmpLast) {
		int last = LAST;
		for (int i = tmpLast - 1; i >= 0; i--) {
			int index = indexOrderArr[i];
			if (index == -1) {
				isSeq[i] = true;
				continue;
			}

			if (index == last) {
				last--;
				isSeq[i] = true;
			}
		}

		for (int i = 0; i < tmpLast; i++) {
			if (!isSeq[i]) answer.append(i).append("\n");
		}
	}
}