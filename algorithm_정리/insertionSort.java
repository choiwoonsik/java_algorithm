package algorithm_정리;

import java.util.Random;

public class insertionSort {
    public static void main(String args[]) {
        Random rand = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(100);
        insertion_sort(arr);
        for (int k : arr)
            System.out.print(k + " ");
    }

    static void insertion_sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int pivot = arr[i];
            int idx = i-1;
            while (idx >= 0 && pivot < arr[idx]) {
                arr[idx + 1] = arr[idx];
                idx--;
            }
            arr[idx + 1] = pivot;
        }
    }
}