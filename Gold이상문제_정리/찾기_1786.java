package Gold이상문제_정리;
/*
ABC ABCDAB ABCDABCDABDE
ABCDABD
 */
import java.io.*;
import java.util.*;

public class 찾기_1786 {
	static int[] pi;
	static char[] T;
	static char[] P;
	static int cnt;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = br.readLine().toCharArray();
		P = br.readLine().toCharArray();
		pi = new int[P.length];

		//make pi
		int j = 0;
		for (int i = 1; i < P.length; i++) {

			while (j > 0 && P[i] != P[j])
			{
				j = pi[j - 1];
			}

			if (P[i] == P[j]) pi[i] = ++j;
		}

		// KMP
		j = 0;
		for (int i = 0; i < T.length; i++) {

			while (j > 0 && T[i] != P[j])
			{
				j = pi[j - 1];
			}

			if (T[i] == P[j]) ++j;

			if (j == P.length) {
				cnt++;
				str.append(i - (P.length - 1) + 1).append(" ");
				j = pi[j - 1];
			}
		}
		System.out.println(cnt);
		System.out.print(str);
	}
}
