package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 과제_13904 {
	static int N;
	static int Point = 0;
	static int[] subject_arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<pair> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s2.point, s1.point));

		int last_day = 0;
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int d_day = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			last_day = Math.max(last_day, d_day);
			pair subject = new pair(d_day, point);
			pq.add(subject);
		}
		subject_arr = new int[last_day+1];

		while (!pq.isEmpty())
		{
			pair now_subject = pq.poll();

			int idx = now_subject.d_day;
			while (idx > 0) {
				if (subject_arr[idx] < now_subject.point) {
					subject_arr[idx] = now_subject.point;
					break;
				}
				idx--;
			}
		}
		Point = Arrays.stream(subject_arr).sum();
		System.out.println(Point);
	}

	private static class pair
	{
		int d_day, point;

		public pair(int d_day, int point) {
			this.d_day = d_day;
			this.point = point;
		}
	}
}
