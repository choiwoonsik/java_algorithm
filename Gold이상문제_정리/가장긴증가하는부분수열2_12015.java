package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 가장긴증가하는부분수열2_12015 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		ArrayList<Integer> findList = new ArrayList<>();
		findList.add(0, -1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			int put_index = Collections.binarySearch(findList, n);

			// 음수인경우
			if (put_index < 0)
			{
				put_index = Math.abs(put_index + 1);
				if (put_index < findList.size())
					findList.remove(put_index);
				findList.add(put_index, n);
			}
			// 0, 양수인 경우
			else {
				findList.remove(put_index);
				findList.add(put_index, n);
			}
		}

		System.out.println(findList.size() - 1);
	}
}
