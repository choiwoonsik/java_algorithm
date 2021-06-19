package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MusicScale_2920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean de = false;
        boolean as = false;
        boolean mx = false;
        for (int i = 0; i < arr.length-1; i++){
            if (arr[i] < arr[i+1] && !de)
                as = true;
            else if (arr[i] > arr[i+1] && !as)
                de = true;
            else {
                mx = true;
                as = false;
                de = false;
                break;
            }
        }
        if (as)
            System.out.println("ascending");
        else if (de)
            System.out.println("descending");
        else if (mx)
            System.out.println("mixed");
    }
}
