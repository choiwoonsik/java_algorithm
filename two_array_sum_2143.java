import java.io.*;
import java.util.*;

public class two_array_sum_2143 {
    static int target;
    static int A_n;
    static int B_n;
    static int[] arrA;
    static int[] arrB;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        A_n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        arrA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        B_n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()[0];
        arrB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Scanner sc = new Scanner(System.in);

        int sum;
        boolean changed = false;
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        for (int i = 0; i < A_n; i++){
            sum = 0;
            for (int j = i; j < A_n; j++) {
                sum += arrA[j];
                listA.add(sum);
            }
        }
        for (int i = 0; i < B_n; i++){
            sum = 0;
            for (int j = i; j < B_n; j++) {
                sum += arrB[j];
                listB.add(sum);
            }
        }
        Collections.sort(listA);
        Collections.sort(listB);

        int left = 0;
        int right = listB.size() - 1;

        long left_cnt;
        long right_cnt;
        long sumLR = 0;

        while(left < listA.size() && right >= 0){
            int left_val = listA.get(left);
            int right_val = listB.get(right);
            if (left_val + right_val == target) {
                changed = true;
                left_cnt = 0;
                while (left < listA.size() && listA.get(left) == left_val){
                    left_cnt++;
                    left++;
                }
                right_cnt = 0;
                while (right >= 0 && listB.get(right) == right_val) {
                    right_cnt++;
                    right--;
                }
                sumLR += left_cnt * right_cnt;
            }
            if (left_val + right_val < target)
                left++;
            if (left_val + right_val > target)
                right--;
        }
        if (!changed) {
            System.out.print(0);
            return;
        }
        System.out.print(sumLR);
    }
}
