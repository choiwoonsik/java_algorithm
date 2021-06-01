package Gold이상문제_정리;

import FastCampus_algorithm.BinarySearch;

import java.io.*;
import java.util.*;

/*
N명의 아이들이 한 줄로 줄을 서서 놀이공원에서 1인승 놀이기구를 기다리고 있다.
이 놀이공원에는 총 M종류의 1인승 놀이기구가 있으며, 1번부터 M번까지 번호가 매겨져 있다.

모든 놀이기구는 각각 운행 시간이 정해져 있어서, 운행 시간이 지나면 탑승하고 있던 아이는 내리게 된다.
놀이 기구가 비어 있으면 현재 줄에서 가장 앞에 서 있는 아이가 빈 놀이기구에 탑승한다.
만일 여러 개의 놀이기구가 동시에 비어 있으면, 더 작은 번호가 적혀 있는 놀이기구를 먼저 탑승한다고 한다.

놀이기구가 모두 비어 있는 상태에서 첫 번째 아이가 놀이기구에 탑승한다고 할 때, 줄의 마지막 아이가 타게 되는 놀이기구의 번호를 구하는 프로그램을 작성하시오.
첫째 줄에 N(1 ≤ N ≤ 2,000,000,000)과 M(1 ≤ M ≤ 10,000)이 빈칸을 사이에 두고 주어진다.
둘째 줄에는 각 놀이기구의 운행 시간을 나타내는 M개의 자연수가 순서대로 주어진다.
운행 시간은 1 이상 30 이하의 자연수이며, 단위는 분이다.

22 5
1 2 3 4 5

Q. 마지막 학생이 탄 놀이기구 번호는?
1. 놀이기구가 학생수보다 적다면 -> 학생수 출력
2. 놀이기구가 더 많다면 이분탐색으로 마지막에 탄 학생의 x분을 구한다
3. x-1분까지 탄 학생의 수를 구한다
4. x분에 탄 학생 수를 구한다. 이때 N번 학생이 있으면 해당 기구 출력
 */

public class 놀이공원_1561 {
	static int N, M;
	static long left = 1;
	static long right = 2000000000L * 30;
	static int[] time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			time[i] = Integer.parseInt(st.nextToken());

		if (N <= M)
			System.out.println(N);
		else
			System.out.println(binarySearch());
	}

	private static int binarySearch() {
		while (left <= right)
		{
			long mid = (left + right) / 2;

			long rided = 0;
			rided += M;
			for (int i = 0; i < M; i++)
				rided += mid / time[i];

			long endN = rided;
			for (int i = 0; i < M; i++) {
				if (mid % time[i] == 0)
					rided--;
			}
			long beginN = rided + 1;

			if (N < beginN)
				right = mid - 1;
			else if (N > endN)
				left = mid + 1;
			else {
				for (int i = 0; i < M; i++) {
					if (mid % time[i] == 0) {
						if (beginN == N)
							return i + 1;
						beginN++;
					}
				}
			}
		}
		return -1;
	}
}
