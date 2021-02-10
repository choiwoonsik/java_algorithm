package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 암호만들기2_1759 {
	static int L, C;
	static char[] word;
	static StringBuilder ret = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		word = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++)
			word[i] = st.nextToken().charAt(0);
		Arrays.sort(word);

		backTracking(-1, 0, "");
		System.out.print(ret);
	}
	private static void backTracking(int index, int len, String str)
	{
		if (len == L)
		{
			int ja = 0;
			int mo = 0;
			//자음 모음 체크
			for (int i = 0; i < L; i++) {
				if (str.charAt(i) == 'a' || str.charAt(i) == 'e'
					|| str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u')
					mo++;
				else
					ja++;
			}
			if (mo >= 1 && ja >= 2)
				ret.append(str).append("\n");
			return;
		}

		for (int i = index + 1; i < C; i++)
			backTracking(i, len + 1, str+word[i]);
	}
}
