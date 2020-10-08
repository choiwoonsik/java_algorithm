package FastCampus_algorithm;

import java.util.Random;

public class bubbleSort {
    public static void main(String args[])
    {
        Random rand = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(100);
        bubble_sort(arr);
        for (int k : arr)
            System.out.print(k+" ");
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
}
