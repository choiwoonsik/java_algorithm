package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 가운데를말해요_1655 {
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int n = Integer.parseInt(br.readLine());
            findMid(n);
        }
        System.out.println(answer);
    }

    private static void findMid(int val) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(val);
        } else {
            if (maxHeap.size() == minHeap.size()) {
                if (val <= maxHeap.peek()) maxHeap.add(val);
                else {
                    minHeap.add(val);
                    maxHeap.add(minHeap.poll());
                }
            } else if (maxHeap.size() > minHeap.size()){
                if (val <= maxHeap.peek()) {
                    maxHeap.add(val);
                    minHeap.add(maxHeap.poll());
                } else {
                    minHeap.add(val);
                }
            }
        }
        answer.append(maxHeap.peek()).append("\n");
    }
}
