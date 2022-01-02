package 이분탐색;

import java.io.*;
import java.util.*;

public class 카드팩구매하기_15823 {
    static int N;
    static int M;
    static int[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int answer = findPackSize();
        System.out.print(answer);
    }

    private static int findPackSize() {
        int low = 1;
        int high = N / M;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (calcCardCount(mid) >= M) {
                // 높여야댐
                low = mid + 1;
            } else {
                // 낮춰야댐
                high = mid - 1;
            }
        }

        return high;
    }

    private static int calcCardCount(int max) {
        boolean[] visited = new boolean[500001];
        Arrays.fill(visited, false);
        Queue<Integer> set = new LinkedList<>();
        int index = 0;
        int packCount = 0;

        while (index < N) {

            int nowCard = cards[index];
            if (visited[nowCard] && !set.isEmpty()) {
                visited[set.poll()] = false;
                continue;
            }
            visited[nowCard] = true;
            set.add(nowCard);
            index++;
            if (set.size() == max) {
                packCount++;
                for (Integer card : set) {
                    visited[card] = false;
                }
                set.clear();
            }
        }
        return packCount;
    }
}