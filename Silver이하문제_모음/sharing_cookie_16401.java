package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
모든 조카에게 같은 길이의 막대를 줘야한다
M명의 조카와 N개의 과자가 있을때 한명에게 줄수있는 최대의 길이
3명에게 10개의 과자
1 2 3 4 5 6 7 8 9 10

과자 최대 길이 10억

L = 1;
R = 1000000000;
cookieLen = (L+R)/2;

while (L < R)
for (int i = 0; i < N; i++)
	if (arr[i] >= cookieLen)
		count++;
if count >= M
	answer = cookieLen;
	L = cookieLen+1;
else
	R = cookieLen-1;

 */
public class sharing_cookie_16401 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int left = 1;
		int right = 1000000000;
		int cookieLen;
		int answer = 0;
		while (left <= right)
		{
			int count = 0;
			cookieLen = (left+right) / 2;
			for (int i = 0; i < N; i++) {
				if (arr[i] >= cookieLen) {
					count += (arr[i] / cookieLen);
				}
			}
			if (count >= M){
				answer = cookieLen;
				left = cookieLen + 1;
			}
			else
				right = cookieLen - 1;
		}
		System.out.println(answer);
	}
}
