package FastCampus_algorithm;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class SequentialSearch {
	public static void main(String[] args) {
		/*
		탐색은 여러 데이터 중에서 원하는 데이터를 찾아내는 것
		데이터가 담겨있는 리스트를 앞에서부터 하나씩 비교해서 원하는 데이터를 찾는 방법

		 */

		int[] arr = new int[10];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(10);
		System.out.println(find_pos(arr, 5));
	}
	private static int find_pos(int[] arr, int data){
		//임이의 리스트에서 원하는 데이터의 위치를 리턴하는 알고리즘 작성

		Arrays.stream(arr).forEach(s -> System.out.print(s+" "));
		System.out.println();
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == data)
				return i;

		return -1;
	}
}
