package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 피자굽기_1756 {
	// 오븐깊이 D, 반죽의 개수 N,
	// 오븐 지름 위부터 아래로 차례대로
	// 반죽이 완성되는 순서 지름
	// 30만 * 4byte = 120만 byte = 1.2mb
	static int[] diaArr;
	static int D, N, LAST;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		diaArr = new int[D+1];
		st = new StringTokenizer(br.readLine());
		int before_dia = Integer.MAX_VALUE;
		for (int oven = 1; oven <= D; oven++)
		{
			int diameter = Integer.parseInt(st.nextToken());
			before_dia = Math.min(diameter, before_dia);
			diaArr[oven] = before_dia;
		}

		LAST = D+1;
		st = new StringTokenizer(br.readLine());
		for (int pizza = 0; pizza < N; pizza++)
		{
			int pizzaDia = Integer.parseInt(st.nextToken());
			LAST = find(1, LAST, pizzaDia);
			if (LAST < 1) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(LAST);
	}
	// upper_bound 찾기 - key보다 작은 곳의 인덱스
	private static int find(int start, int end, int key)
	{

		while (start < end) {
			int mid = (start + end) / 2;
			if (diaArr[mid] >= key) {
				start = mid + 1;
			}
			else if (diaArr[mid] < key) {
				end = mid;
			}
		}
		return end-1;
	}
}
