package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 로봇조립_18116 {
    static int N;
    static int[] parents = new int[(int) Math.pow(10, 6) + 1];
    static int[] counts = new int[(int) Math.pow(10, 6) + 1];
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            counts[i] = 1;
        }

        while (N-- > 0) {
            String[] input = br.readLine().split(" ");
            int partA;
            int partB;
            if (input.length == 3) {
                partA = Integer.parseInt(input[1]);
                partB = Integer.parseInt(input[2]);
                setFamily(partA, partB);
            } else if (input.length == 2) {
                partA = Integer.parseInt(input[1]);
                question(partA);
            }
        }
        System.out.print(answer);
    }

    private static void question(int part) {
        part = find(part);
        answer.append(counts[part]).append("\n");
    }

    private static void setFamily(int partA, int partB) {
        union(partA, partB);
    }

    private static void union(int partA, int partB) {
        partA = find(partA);
        partB = find(partB);
        if (partA == partB) return;
        counts[partA] += counts[partB];
        parents[parents[partB]] = partA;
    }

    private static int find(int p) {
        if (parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }
}
