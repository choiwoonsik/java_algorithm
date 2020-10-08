package FastCampus_algorithm;

import java.util.Random;

public class selectionSort {
    public static void main(String args[])
    {
        Random rand = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(10000);
        selection_srot(arr);
        for (int k : arr)
            System.out.print(k+" ");
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
