import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class sort_num2_2_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++)
            list.add(Integer.parseInt(br.readLine()));
        Collections.sort(list);
        for (int i = 0; i < N; i++)
            System.out.println(list.get(i));
    }
}
