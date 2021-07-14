package Gold이상문제_정리;

import java.io.*;
import java.util.*;
/*
5
1 2 3 4 5
1 5 2 4 3
 */
public class 전구_2550 {
	static int N;
	static int[] btn;
	static int[] btnPos;
	static Pair[] trace;
	static HashMap<Integer, Integer> numPosMap = new HashMap<>();
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		btn = new int[N];
		btnPos = new int[N];
		trace = new Pair[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			btn[i] = Integer.parseInt(st.nextToken());
			numPosMap.put(btn[i], i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			btnPos[i] = numPosMap.get(Integer.parseInt(st.nextToken()));
		}

		search();
	}

	private static void search() {
		ArrayList<Integer> LIS = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (LIS.size() == 0 || LIS.get(LIS.size() - 1) < btnPos[i]) {
				LIS.add(btnPos[i]);
				trace[i] = new Pair(btnPos[i], LIS.size() - 1);
			} else {
				int idx = binaryFind(LIS, btnPos[i]);
				LIS.set(idx, btnPos[i]);
				trace[i] = new Pair(btnPos[i], idx);
			}
		}

		int lastIdx = LIS.size() - 1;

		for (int idx = N - 1; idx >= 0; idx--) {
			if (trace[idx].idx == lastIdx) {
				LIS.set(lastIdx, btn[trace[idx].num]);
				lastIdx--;
			}
		}

		LIS.sort(null);
		answer.append(LIS.size()).append("\n");
		for (int n : LIS)
			answer.append(n).append(" ");
		System.out.print(answer);
	}

	private static int binaryFind(ArrayList<Integer> list, int idx) {
		int l = 0;
		int r = list.size() - 1;
		int mid;

		while (l <= r) {
			mid = (l + r) / 2;

			if (list.get(mid) > idx)
				r = mid - 1;
			else if (list.get(mid) < idx)
				l = mid + 1;
			else return mid;
		}
		return l;
	}

	private static class Pair
	{
		int num, idx;

		public Pair(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}
}
