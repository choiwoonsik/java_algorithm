import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class sort_num2_2751 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        quick_sort(0, arr.length - 1);
        for (int i = 0; i < N; i++)
            System.out.println(arr[i]);
    }
    private static void quick_sort(int start, int end)
    {
        if (start < end) {
            int new_pivot = part_swap(start, end);
            quick_sort(start, new_pivot - 1);
            quick_sort(new_pivot + 1, end);
        }
    }
    private static int part_swap(int start, int end)
    {
        int pivot = arr[start];
        int low = start + 1;
        int high = end;
        do {
            while (low <= end && arr[low] < pivot)
                low++;
            while (high >= start && arr[high] > pivot)
                high--;
            if (low < high)
                swap(low, high);
        } while(low < high);
        swap(start, high);
        return (high);
    }
    private static void swap(int front, int back)
    {
        int tmp = arr[front];
        arr[front] = arr[back];
        arr[back] = tmp;
    }
}
