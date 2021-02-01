package Gold이상문제_정리;


import java.io.*;
import java.util.*;

public class 중복제거_13701 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder str = new StringBuilder();

		int[] A = new int[1 << 20]; // 2^5 * 2^20= == 2^25

		while (st.hasMoreTokens())
		{
			int N = Integer.parseInt(st.nextToken());

			int R = N / 32;
			int C = N % 32;
			if ((A[R] & (1 << C)) != (1 << C)) { //아직 없다면
				A[R] |= (1 << C); // 체크
				str.append(N).append(" ");
			}
		}
		System.out.println(str);
	}
}
