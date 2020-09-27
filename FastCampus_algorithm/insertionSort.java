package FastCampus_algorithm;

import java.util.Random;

public class insertionSort {
    public static void main(String args[])
    {
        Random rand = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(1000000);
        int[] arr1 = arr;
        int[] arr2 = arr;
        int[] arr3 = arr;

        long beforeTime = System.currentTimeMillis();
        insertion_sort(arr1);
        long afterTime = System.currentTimeMillis();
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("시간차이(m) : "+secDiffTime);

        long beforeTime1 = System.currentTimeMillis();
        bubble_sort(arr2);
        long afterTime1 = System.currentTimeMillis();
        long secDiffTime1 = (afterTime1 - beforeTime1);
        System.out.println("시간차이(m) : "+secDiffTime1);

        long beforeTime2 = System.currentTimeMillis();
        selection_srot(arr3);
        long afterTime2 = System.currentTimeMillis();
        long secDiffTime2 = (afterTime2 - beforeTime2);
        System.out.println("시간차이(m) : "+secDiffTime2);
    }
    static void insertion_sort(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = i + 1; j > 0; j--){
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = tmp;
                }
                else
                    break;
            }
        }
    }
    static void bubble_sort(int[] arr)
    {
        boolean swaped = false;
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if (arr[j] > arr[j+1]){
                    swaped = true;
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
            if (!swaped)
                return;
        }
    }
    static void selection_srot(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int pivot = i;
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[j] < arr[pivot])
                    pivot = j;
            }
            int tmp = arr[pivot];
            arr[pivot] = arr[i];
            arr[i] = tmp;
        }
    }
}
