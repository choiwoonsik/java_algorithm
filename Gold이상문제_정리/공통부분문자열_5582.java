package Gold이상문제_정리;

import java.io.*;
//부분적으로 연속적인 문자열의 최대 길이를 구하는 것이다

public class 공통부분문자열_5582 {
	static int [][]DP; // dp[i][j]는 i,j번째 단어를 비교할때의 최대 공통 문자열의 값을 갖는다
	static int max_len;
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String first = br.readLine();
		String second = br.readLine();

		DP = new int[first.length()+1][second.length()+1];
		for (int i = 1; i <= first.length(); i++) {
			for (int j = 1; j <= second.length(); j++) {
//				** 두 문자열의 공통부분 문자열의 최대길이를 구하는 경우에는 아래 코드만 필요, 불연속적인 부분에대해서는 끊어줘야된다
				if (first.charAt(i-1) == second.charAt(j-1))
					DP[i][j] = DP[i-1][j-1] + 1;

//				 ** 만약 두 문자열에 대해 비연속적 공통 문자열의 최대 길이를 구한다면 아래 코드가 추가적으로 필요
//				 ABZQABC 와 AQBQAB의 최장 공통 문자열은 (비연속) => ABQAB 이다
//				else
//					DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
				max_len = Math.max(DP[i][j], max_len);
			}
		}
//		for (int i = 1; i <= first.length(); i++) {
//			for (int j = 1; j <= second.length(); j++) {
//				System.out.print(DP[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(max_len);
	}
}
