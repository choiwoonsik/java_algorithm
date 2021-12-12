package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 싸지방에간준하_12764 {
    static int[] arr = new int[100001];
    static int N;
    static PriorityQueue<Computer> usingCom = new PriorityQueue<>(Comparator.comparing(c -> c.end));
    static PriorityQueue<Computer> user = new PriorityQueue<>(Comparator.comparing(c -> c.start));
    static PriorityQueue<Integer> remainSeat = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int seat = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            user.add(new Computer(start, end));
        }

        while (!user.isEmpty()) {
            Computer userTime = user.poll();
            int start = userTime.start;
            int end = userTime.end;

            // no computer
            if (usingCom.isEmpty()) {
                usingCom.add(new Computer(++seat, start, end));
                arr[seat]++;
            }
            // find usable computer
            else {
                while (!usingCom.isEmpty()) {
                    Computer com = usingCom.peek();

                    // 사용가능
                    if (com.end < start) {
                        remainSeat.add(com.number);
                        usingCom.poll();
                    } else if (com.end > start) {
                        break;
                    }
                }

                // 사용가능한 컴퓨터 없음
                if (remainSeat.isEmpty()) {
                    usingCom.add(new Computer(++seat, start, end));
                    arr[seat]++;
                }
                // 사용가능한 컴퓨터 있음
                else {
                    int seatNum = remainSeat.poll();
                    arr[seatNum]++;
                    usingCom.add(new Computer(seatNum, start, end));
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(seat).append("\n");
        for (int i = 1; i <= seat; i++) {
            answer.append(arr[i]).append(" ");
        }
        System.out.println(answer);
    }

    private static class Computer {
        int number;
        int start;
        int end;

        public Computer(int number, int start, int end) {
            this.number = number;
            this.start = start;
            this.end = end;
        }

        public Computer(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
