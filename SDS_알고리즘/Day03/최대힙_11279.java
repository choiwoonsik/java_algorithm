package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 최대힙_11279 {
    static Queue pq;
    static int N;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new Queue(new int[N * 2 + 2], 0);
        Arrays.fill(pq.items, -1);

        while (N-- > 0) {
            int val = Integer.parseInt(br.readLine());
            if (val == 0) {
                int tmp = poll();
                answer.append(tmp).append("\n");
            } else {
                add(val);
            }
        }
        System.out.print(answer);
    }

    private static void add(int node) {
        pq.items[++pq.size] = node;
        makeMaxHeapADD(pq.size);
    }

    private static void makeMaxHeapADD(int cur) {
        if (cur == 1) return;
        int parent = cur / 2;
        if (pq.items[parent] < pq.items[cur]) {
            swap(parent, cur);
            makeMaxHeapADD(cur / 2);
        }
    }

    private static int poll() {
        int root;
        if (pq.size == 0) return 0;
        else {
            root = pq.items[1];
            int last = pq.items[pq.size];
            pq.items[pq.size] = -1;
            pq.size--;
            pq.items[1] = last;
            makeMaxHeapPOLL(1);
        }
        return root;
    }

    private static void makeMaxHeapPOLL(int node) {
        // 둘다 없는 경우 : 리프 노드
        if (pq.items[node * 2] == -1 && pq.items[node * 2 + 1] == -1)
            return;
        // 둘다 있는 경우
        if (pq.items[node * 2] != -1 && pq.items[node * 2 + 1] != -1) {
            if (pq.items[node * 2] >= pq.items[node * 2 + 1] && pq.items[node * 2] > pq.items[node]) {
                // left more small
                swap(node * 2, node);
                makeMaxHeapPOLL(node * 2);
            } else if (pq.items[node * 2+1] > pq.items[node * 2] && pq.items[node * 2+1] > pq.items[node]) {
                // right more small
                swap(node * 2 + 1, node);
                makeMaxHeapPOLL(node * 2 + 1);
            }
        } else {
            // 왼쪽 하나만 있는 경우
            if (pq.items[node * 2] > pq.items[node]) {
                swap(node * 2, node);
                makeMaxHeapPOLL(node * 2);
            }
        }
    }

    private static void swap(int val, int node) {
        int tmp;
        tmp = pq.items[val];
        pq.items[val] = pq.items[node];
        pq.items[node] = tmp;
    }

    private static class Queue {
        int[] items;
        int size;

        public Queue(int[] items, int size) {
            this.items = items;
            this.size = size;
        }
    }
}
