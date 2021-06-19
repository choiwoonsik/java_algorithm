package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class FindNumber_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            int key = Integer.parseInt(st.nextToken());
            hashtable.put(key, i);
        }

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            int key = Integer.parseInt(st.nextToken());
            if (hashtable.containsKey(key))
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
