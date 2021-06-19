package algorithm_정리;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
	public static void main(String[] args) {
		/*
		분할 정복 알고리즘
		divide and conquer
		- 재귀를 주로 사용

		이진 탐색 (기본적으로 정렬된 자료에 대해서 시행)
		- 리스트를 두개의 서브 리스트로 나눈다

		 */

		/*
		구현

		binary_search(list, find_data)
		find_data : 원하는 값
		list : 데이터 리스트
		- list의 중간값을 find_data와 비교
		- 작으면 맨앞부터 중간값에서 다시 중간값을 비교
		- 크면 중간값부터 맨뒤값에서 다시 중간값 비교
		- 반복...
		 */
		System.out.println("binary Search\n");
		int[] array = new int[10];
		Random rand = new Random();
		for (int i = 0; i < array.length; i++)
			array[i] = rand.nextInt(10);

		Arrays.sort(array);
		System.out.println(binary_search(array, 1));
	}
	private static boolean binary_search(int[] list, int data){
		int mid;
		Arrays.stream(list).forEach(a -> System.out.print(a+" "));
		System.out.println();
		if (list.length > 1) {
			mid = list.length / 2;

			if (list[mid] == data)
				return true;
			else if (list[mid] < data)
				return binary_search(Arrays.copyOfRange(list, mid, list.length), data);
			else
				return binary_search(Arrays.copyOfRange(list, 0, mid), data);
		}
		else if (list.length == 1 && list[0] == data)
			return true;
		else if (list.length == 1 && list[0] != data)
			return false;
		else
			return false;
	}
}
