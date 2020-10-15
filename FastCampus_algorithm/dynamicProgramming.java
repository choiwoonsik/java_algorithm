package FastCampus_algorithm;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class dynamicProgramming {
    public static void main(String[] args) {
        /**
         * 동적 계획법 - DP
         *
         * 입력크기가 작은 부분 문제들을 해결한 후, 해당 부분 문제의 해를 활용해서
         * 보다 큰 크기의 부분 문제를 해결, 최종적으로 전체 문제를 해결하는 알고리즘
         *
         * Memoization 기법을 사용한다
         * 상향식 접근법이다
         *
         * 프로그램 실행시 이전에 계산한 값을 지정하며, 다시 계산하지 않도록
         * 전체 실행 속도를 빠르게 하는 기술
         *
         * ex) 피보나치 수열
         */

        /**
         * 분할 정복
         *
         * 문제를 나눌 수 없을 때까지 나누어서 각각을 풀면서
         * 다시 합병하여 문제의 답을 얻는 알고리즘
         *
         * 하양식 접근법이다
         * 일반적으로 재귀함수로 구현을 많이한다
         * 문제를 잘게 쪼갤때, 부분문제는 중복되지 않는다
         * Memoization 기법을 사용하지 않는다
         *
         * ex) 병합 정렬, 퀵 정렬 (merge sort, quick sort)
         */

        /**
         *  공통점
         *
         *  문제를 잘게 쪼개서 가장 작은 단위로 분할
         */

        //0 1 1 2 3 5 8 13 ...
        // DP 문제 - 피보나치 수열
        System.out.println(fibonacci(11));
        System.out.println(fibo(11));


        Random rand = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(100);
        quickSort(arr, 0, arr.length -1);

//        for (int l : arr)
//            System.out.print(l+" ");

		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);
        mergesort(arr);

//        for (int l : arr)
//            System.out.print(l+" ");

		for (int i = 0; i < arr.length; i++)
			arr[i] = rand.nextInt(100);
		for (int k : mergeSort(arr))
			System.out.print(k + " ");

    }

    static int[] arr = new int[10000];
    //재귀 형태
    private static int fibonacci(int n){
        if (n >= 2) {
            if (arr[n] == 0) {
                arr[n] = fibonacci(n - 1) + fibonacci(n - 2);
                return arr[n];
            }
            else
                return arr[n];
        }
        else if (n <= 1) {
            if (n == 1)
                arr[n] = 1;
            return (arr[n]);
        }
        return 0;
    }

    //반복문 형태
    private static int fibo(int n)
    {
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < n + 1; i++)
            arr[i] = arr[i-1] + arr[i-2];
        return arr[n];
    }

    //퀵 소트

    /**
     * pivot 설정 (보통 첫번째 요소로 설정)
     *
     * 피봇을 기준으로 작은값을 왼쪽, 큰값을 오른쪽으로 정렬
     *
     * 재귀로 다시 왼쪽, 오른쪽에 있는 값을에 대해 요소가 하나가 될때까지 진행
     * 모두 정렬이 끝난 후 정렬된 배열을 리턴 -> 모든 요소들이 합쳐진다
     *
     */

	public static void quickSort(int[] arr, int left, int right) {
		int i, j, pivot, tmp;
		if (left < right) {
			i = left;   j = right;
			pivot = arr[(left+right)/2];
			//분할 과정
			while (i < j) {
				while (arr[j] > pivot)
					j--;
				while (arr[i] < pivot)
					i++;
				if (i < j) {
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					i++;
					j--;
				}
			}
			//정렬 과정
			quickSort(arr, left, i - 1);
			quickSort(arr, i + 1, right);
		}
	}

	public static List<Integer> quicksort_stream(List<Integer> list){
		if (list.size() <= 1)
			return list;
		int pivot = list.get(list.size()/2);
		List<Integer> Left = new LinkedList<>();
		List<Integer> Right = new LinkedList<>();
		List<Integer> Same = new LinkedList<>();

		for (int val : list){
			if (val < pivot)
				Left.add(val);
			else if (val > pivot)
				Right.add(val);
			else
				Same.add(val);
		}
		return Stream.of(quicksort_stream(Left), Same ,quicksort_stream(Right))
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

    // 병합 정렬

    // mergeSplit함수 만들기

    /*
    만약 리스트 개수가 한개이면 해당 값 리턴
    그렇지 않으면, 리스트를 앞뒤, 두개로 나누기
    left = mergeSplit(앞)
    right = mergeSplit(뒤)
    merget(left, right)
     */

    // merge 함수 만들기
	/*
	리스트 변수 하나 만들기 (정렬된 요소 저장)
	left_idx, right_idx = 0
	while left_idx < left.length or right_idx < right.length
		만약 left_idx나 right_idx가 이미 left, right를 다 순회했다면, 반대쪽 데이터를 전부다 넣는다
		왼쪽이 작으면 왼쪽걸 넣고 left_idx++
		오른쪽이 작으면 오른쪽걸 넣고 right_idx++

	 */

	private static void mergesort(int[] arr)
	{
		split(arr, 0, arr.length-1);
	}

	private static void split(int[] arr, int start, int end){
		if (start < end) {
			int mid = (start + end) / 2;
			split(arr, start, mid);
			split(arr, mid + 1, end);
			merge(arr, start, mid, end);
		}
	}

	private static void merge(int[] arr, int start, int mid, int end){
		int[] temp = new int[arr.length];
		int left = start;
		int right = mid+1;
		int idx = start;

		while (left <= mid && right <= end){
			if (arr[left] < arr[right])
				temp[idx++] = arr[left++];
			else
				temp[idx++] = arr[right++];
		}
		while (left <= mid)
			temp[idx++] = arr[left++];
		while (right <= end)
			temp[idx++] = arr[right++];

		for (int i = start; i < idx; i++)
			arr[i] = temp[i];
	}

	private static int[] mergeSort(int[] arr)
	{
		if (arr.length <= 1)
			return arr;
		int mid = arr.length / 2;
		//mid 전까지 취하는 Arrays.copyOfRange이다 따라서 mid번째는 포함 x
		int[] left_arr = mergeSort(Arrays.copyOfRange(arr, 0, mid));
		int[] right_arr =  mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
		int[] sorted_arr = new int[arr.length];

		int left = 0; int idx = 0; int right = 0;
		while (left < left_arr.length && right < right_arr.length) {
			if (left_arr[left] < right_arr[right])
				sorted_arr[idx++] = left_arr[left++];
			else
				sorted_arr[idx++] = right_arr[right++];
		}
		while (left < left_arr.length)
			sorted_arr[idx++] = left_arr[left++];

		while (right < right_arr.length)
			sorted_arr[idx++] = right_arr[right++];

		return sorted_arr;

	}
}
