package SDS_알고리즘.Day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

3
ACMA
APCA
TOGI
NEST

PCMM
RXAI
ORCN
GPCG

ICPC
GCPC
ICPC
GCPC
 */
public class Boggle_9202 {
    static int totalPoint = 0;
    static int wordCount = 0;
    static char[][] grid = new char[4][4];
    static boolean[][] visited;
    static final TrieNode root = new TrieNode();
    static StringBuilder sb = new StringBuilder();
    static String longestWord = "";
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            makeDictionary(word);
        }

        br.readLine();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            for (int i = 0; i < 4; i++) {
                String line = br.readLine();
                for (int j = 0; j < 4; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }

            visited = new boolean[4][4];
            sb = new StringBuilder();
            longestWord = "";
            totalPoint = 0;
            wordCount = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (root.hasChild(grid[i][j])) {
                        search(i, j, 1, root.getChild(grid[i][j]));
                    }
                }
            }
            answer
                    .append(totalPoint)
                    .append(" ")
                    .append(longestWord)
                    .append(" ")
                    .append(wordCount)
                    .append("\n");
            root.clearHit();
            br.readLine();
        }
        System.out.print(answer);
    }

    private static void search(int y, int x, int len, TrieNode node) {
        int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dx = {0, 0, -1, 1, -1, 1, -1, 1};

        // 1. 체크인
        visited[y][x] = true;
        sb.append(grid[y][x]);

        // 2 목적지 도달하였는가? -> isEnd, isHit
        if (node.isEnd && !node.isHit) {
            node.isHit = true;
            totalPoint += getPoint(len);
            wordCount++;
            String foundWord = sb.toString();
            if (compare(longestWord, foundWord) > 0) {
                longestWord = foundWord;
            }
        }

        // 3. 연결된 곳을 순회
        for (int d = 0; d < 8; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            // 4. 가능한가?
            if (ny < 0 || nx < 0 || ny >= 4 || nx >= 4) continue;
            if (!node.hasChild(grid[ny][nx])) continue;
            if (visited[ny][nx]) continue;

            // 5. 간다
            search(ny, nx, len + 1, node.getChild(grid[ny][nx]));
        }

        // 6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(len - 1);
    }

    private static int compare(String s0, String s1) {
        int comp = Integer.compare(s1.length(), s0.length());
        if (comp == 0) {
            // 사전순
            return s0.compareTo(s1);
        }
        else {
            // 긴거 먼저
            return comp;
        }
    }

    private static void makeDictionary(String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.hasChild(c)) {
                cur.child[c - 'A'] = new TrieNode();
            }
            cur = cur.getChild(c);
        }
        cur.isEnd = true;
    }

    private static int getPoint(int word) {
        if (word == 1 || word == 2) {
            return 0;
        } else if (word == 3 || word == 4) {
            return 1;
        } else if (word == 5) {
            return 2;
        } else if (word == 6) {
            return 3;
        } else if (word == 7) {
            return 5;
        } else if (word == 8) {
            return 11;
        }
        return 0;
    }

    private static class TrieNode {
        boolean isEnd;
        boolean isHit;
        TrieNode[] child = new TrieNode[26];

        public boolean hasChild(char c) {
            return child[c - 'A'] != null;
        }

        public TrieNode getChild(char c) {
            return child[c - 'A'];
        }

        void clearHit() {
            isHit = false;
            for (TrieNode trieNode : child) {
                if (trieNode != null) {
                    trieNode.clearHit();
                }
            }
        }
    }
}
