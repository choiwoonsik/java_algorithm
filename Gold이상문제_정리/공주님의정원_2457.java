package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
4
1 1 5 31
1 1 6 30
5 15 8 31
6 10 12 10

공주가 가장 좋아하는 계절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어 있도록 한다.
정원이 넓지 않으므로 정원에 심는 꽃들의 수를 가능한 적게 한다.
 */
public class 공주님의정원_2457 {
	static int N;
	static int START_DATE = 301;
	static int END_DATE = 1201;
	static int answer = 0;
	static HashMap<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sm = Integer.parseInt(st.nextToken()) * 100;
			int sd = Integer.parseInt(st.nextToken());
			int dm = Integer.parseInt(st.nextToken()) * 100;
			int dd = Integer.parseInt(st.nextToken());

			int start = sm + sd;
			int end = dm + dd;

			//시작 날이 같은 꽃이 이미 있다면 죽는 날이 더 늦은 경우에만 넣어줌
			if (map.get(start) == null || map.get(start) < end)
				map.put(start, end);
		}

		boolean flag = false;
		int now = START_DATE;

		// 현재 날짜를 마지막날을 넘으면 종료
		while (now < END_DATE)
		{
			// 꽃이 지는 마지막 날 LAST
			int LAST = now;
			for (int start : map.keySet())
			{
				// 이어서 피어야 되므로 <= && 마지막 날보다 더 늦게 지는 경우에만
				if (start <= now && LAST < map.get(start))
				{
					LAST = map.get(start);
					flag = true;
				}
			}

			// 그런 꽃이 있다면
			if (flag) {
				// 시작은 꽃이 진 마지막날
				now = LAST;
				flag = false;
				answer++;
			}
			// 그런 꽃이 없는 경우 조건 실패
			else {
				answer = 0;
				break;
			}
		}

		System.out.println(answer);
	}

}
