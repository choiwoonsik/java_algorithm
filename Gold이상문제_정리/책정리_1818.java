package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 책정리_1818 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<>();
		list.add(-1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			int pos = Collections.binarySearch(list, n);
			if (pos < 0)
			{
				pos = Math.abs(pos + 1);
				if (pos < list.size())
					list.remove(pos);
				list.add(pos, n);
			}
		}
		System.out.println(N - (list.size() - 1));
	}
}
