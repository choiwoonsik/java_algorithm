import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class set_WIFI_2110 {
	/*
	c개의 공유기를 설치
	한집당 하나의 공유기
	c개의 공유기를 n개의 집에 적당히 설치하여 가장 인접한 두 공유기 사이의 거리를 최대로 해라
	집의 개수 : 2~20만
	공유기 개수 : 2 ~ 집의 개수(20만)
	좌표 1 ~ 1000000000(10억)

	5 3 //5개의 집 3개의 공유기
	1 2 8 4 9 // 집좌표

	0     0       0
	1 2 . 4 . . . 8 9
	가장 왼쪽에 있는 위치부터 순서대로 간격이 x이상이 되도록 공유기를 설치해 보면서 C개 이상을 설치할 수 있는지 보면 됩니다.


	1 2 4 8 9
	left = 0;
	right = length-1;
	mid = (left + right) / 2;
	x의 길이를 이분탐색으로 줄여나간다

	 */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = tmp[0];
		int C = tmp[1];
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int left = 1;
		int right = arr[arr.length-1] - left;
		int distance;
		int answer = 0;
		while (left <= right) {
			int Limit = (left + right) / 2;
			int start = arr[0];
			int cnt = 1;

			for (int i = 1; i < N; i++)
			{
				distance = arr[i] - start;
				if (Limit <= distance){
					cnt++;
					start = arr[i];
				}
			}
			if (cnt < C)
				right = Limit - 1;
			else if (cnt >= C) {
				left = Limit + 1;
				answer = Limit;
			}
		}
		System.out.println(answer);
	}
}
