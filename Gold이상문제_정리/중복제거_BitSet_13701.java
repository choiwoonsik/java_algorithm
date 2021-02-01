package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 중복제거_BitSet_13701 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder str = new StringBuilder();
		BitSet bitSet = new BitSet();

		int[] A = new int[1 << 20]; // 2^5 * 2^20= == 2^25

		while (st.hasMoreTokens())
		{
			int N = Integer.parseInt(st.nextToken());

			if(!bitSet.get(N)) {
				bitSet.set(N);
				str.append(N).append(" ");
			}
		}
		System.out.println(str);
	}
}
