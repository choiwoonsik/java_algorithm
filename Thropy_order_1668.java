import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Thropy_order_1668 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
        {
            int height = Integer.parseInt(br.readLine());
            arr[i] = height;
        }
        int front = 0;
        int back = 0;
        int count = 1;
        while (back < N)
        {
            if (arr[front] >= arr[back])
                back++;
            else {
                count++;
                front = back;
            }
        }
        System.out.println(count);
        front = N-1;
        back = N-1;
        count = 1;
        while (back >= 0)
        {
            if (arr[front] >= arr[back])
                back--;
            else
            {
                count++;
                front = back;
            }
        }
        System.out.println(count);
    }
}
