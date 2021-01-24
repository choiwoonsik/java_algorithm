package Gold이상문제_정리;

import java.io.*;

public class 좋은수열_2661 {
	static int N;
	static boolean done;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String str = "1";
		DFS(1, str);
	}

	private static void DFS(int len, String s) {
		if (done)
			return;
		if (len == N)
		{
			System.out.println(s);
			done = true;
		}

		for (int i = 1; i <= 3; i++) {
			if (isGood(s + i)) {
				DFS(len + 1, s + i);
			}
		}
	}

	private static boolean isGood(String str) {
		int len = str.length() / 2;
		int start = str.length() - 1;
		int end = str.length();

		for (int comp_size = 1; comp_size <= len; comp_size++)
		{
			// 추가되는 뒤쪽부터 검사해야 된다 , end는 고정 start를 앞으로 땡긴다, 비교 크기를 1씩 증가
			String origin = str.substring(start, end);
			String other = str.substring(start - comp_size, end - comp_size);
			if (origin.equals(other))
				return false;
			start -= 1;
		}
		return true;
	}
}
