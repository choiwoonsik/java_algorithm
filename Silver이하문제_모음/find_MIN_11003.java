package Silver이하문제_모음;

import java.io.*;
import java.util.*;

public class find_MIN_11003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException
    {
        int n[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        int L = n[1];
        int number;
        ArrayDeque<Dot> deque = new ArrayDeque<>();
        boolean isfirst;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
        {
            isfirst = true;
            number = Integer.parseInt(st.nextToken());
            if (deque.isEmpty()) {
                isfirst = false;
                deque.offer(new Dot(number, i));
            }
            //새로운 값이 더 작다면 마지막값을 지운다
            while (!deque.isEmpty() && deque.getLast().x > number){
                deque.removeLast();
            }
            //새로운 값을 넣는다
            if (isfirst)
                deque.offer(new Dot(number, i));
            //덱의 크기를 벗어나면 젤 오래된걸 지운다
            if (deque.getFirst().y <= i - L) {
                deque.removeFirst();
            }
            sb.append(deque.getFirst().x + " ");
        }
        bw.write(sb.toString());
    }
}
