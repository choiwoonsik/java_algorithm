import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class seven_smallPerson_2309 {
    static int[] all_key = new int[9];
    static boolean[] visited = new boolean[9];
    static int sum;
    static boolean stop;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            int tmp[] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            all_key[i] = tmp[0];
        }
        find_seven(0);
        for (int l : list)
            System.out.println(l);
    }
    static void find_seven(int count)
    {
        if (stop)
            return;
        if (sum == 100 && count == 7)
        {
            for (int i = 0; i < visited.length; i++)
                if (visited[i])
                    list.add(all_key[i]);
            stop = true;
            Collections.sort(list);
            return;
        }
        for (int i = 0; i < 9; i++)
        {
            if (!visited[i] && count < 7)
            {
                sum += all_key[i];
                visited[i] = true;
                count += 1;
                find_seven(count);
                count--;
                sum -= all_key[i];
                visited[i] = false;
            }
        }
    }
}
