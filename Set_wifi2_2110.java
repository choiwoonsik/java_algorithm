import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Set_wifi2_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int distance = 100000000;

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            int pos = Integer.parseInt(br.readLine());
            list.add(pos);
        }
        Collections.sort(list);

        int min = list.get(1) - list.get(0);
        int max = list.get(list.size() - 1) - list.get(0);
        int result = 0;

        while (min <= max)
        {
            int mid = (max + min) / 2;
            int now_pos = list.get(0);
            int count = 1;
            for (int i = 1; i < N; i++){
                if (list.get(i) >= now_pos+mid) {
                    now_pos = list.get(i);
                    count++;
                }
            }
            if (count >= C) {
                min = mid + 1;
                result = mid;
            }
            else
                max = mid - 1;
        }
        System.out.println(result);
    }
}
