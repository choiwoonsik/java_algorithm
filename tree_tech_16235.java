import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class tree_tech_16235 {
    static int[][] feedMap;
    static int[][] feedInfo;
    static int[] d_y = {-1, -1, -1, 1, 1, 1, 0, 0};
    static int[] d_x = {0, 1, -1, 0, 1, -1, 1, -1};
    static PriorityQueue<Tree> Trees = new PriorityQueue<>();
    static int N;
    static int M;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        feedMap = new int[N+1][N+1];
        feedInfo = new int[N+1][N+1];
        // 겨울에 나눠줄 먹이 정보
        for (int y = 1; y <= N; y++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 1; x <= N; x++) {
                int feed = Integer.parseInt(st.nextToken());
                feedInfo[y][x] = feed;
                feedMap[y][x] = 5;
            }
        }
        // 나무 심기
        for (int m = 0; m < M; m++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            Trees.add(new Tree(y, x, age));
        }

        //K년 보내기
        year();
        System.out.println(Trees.size());
    }

    private static void year() {
        ArrayList<Tree> dieTrees = new ArrayList<>();
        ArrayList<Tree> liveTrees = new ArrayList<>();

        for (int year = 0; year < K; year++) {

            int tsize = Trees.size();
            PriorityQueue<Tree> newPQ = new PriorityQueue<>();

            for (int i = 0; i < tsize; i++) {
                Tree tree = Trees.poll();
                int treeY = tree.y;
                int treeX = tree.x;

                if (feedMap[treeY][treeX] < tree.age) {
                    dieTrees.add(tree);
                    continue;
                }

                feedMap[treeY][treeX] -= tree.age;
                newPQ.add(new Tree(treeY, treeX, tree.age + 1));
                if ((tree.age + 1) % 5 == 0)
                    liveTrees.add(new Tree(treeY, treeX, tree.age + 1));
            }
            Trees = new PriorityQueue<>(newPQ);

            // 여름
            for (Tree dieTree : dieTrees)
                feedMap[dieTree.y][dieTree.x] += dieTree.age / 2;
            dieTrees.clear();

            // 가을
            for (Tree tree : liveTrees) {
                for (int dir = 0; dir < 8; dir++) {
                    int dy = tree.y + d_y[dir];
                    int dx = tree.x + d_x[dir];
                    if (dy <= 0 || dy > N || dx <= 0 || dx > N)
                        continue;
                    Trees.add(new Tree(dy, dx, 1));
                }
            }
            liveTrees.clear();

            //겨울
            for (int y = 1; y <= N; y++) {
                for (int x = 1; x <= N; x++) {
                    feedMap[y][x] += feedInfo[y][x];
                }
            }
        }
    }

    private static class Tree implements Comparable<Tree>{
        int y;
        int x;
        int age;

        public Tree(int y, int x, int age) {
            this.y = y;
            this.x = x;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}
