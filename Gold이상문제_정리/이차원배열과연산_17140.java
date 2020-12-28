import java.io.*;
import java.util.*;

public class 이차원배열과연산_17140 {
    static int[][] map;
    static int maxRow, maxCol;
    static int r, c, k;
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        map = new int[100][100];
        maxRow = 3;
        maxCol = 3;

        for (int y = 0; y < maxRow; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < maxCol; x++)
                map[y][x] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        while (time <= 100)
        {
            if (map[r][c] == k)
                break;
            if (maxRow >= maxCol) {
                for (int r = 0; r < maxRow; r++)
                    R_operation(r);
            }
            else {
                for (int c = 0; c < maxCol; c++)
                    C_operation(c);
            }
            time++;
        }
        if (time <= 100)
            System.out.println(time);
        else
            System.out.println("-1");
    }

    private static void R_operation(int r) {
        // 각 행에서 각각의 수가 몇번 나왔는지 확인
        // 수의 등장을 기준으로 정렬
        // 수의 등장이 같은것이 여러개라면 수가 커지는 순으로 정렬
        // 정렬한거를 배열에 다시 담을때는 수와 등장 횟수를 넣는다

        // SortedMap과 TreeMap을 사용해서 기본적으로 key값을 기준으로 정렬
        SortedMap<Integer, Integer> sortMap = new TreeMap<>();
        for (int x = 0; x < maxCol; x++) {
            if (map[r][x] == 0)
                continue;
            if(sortMap.containsKey(map[r][x]))
                sortMap.put(map[r][x], sortMap.get(map[r][x]) + 1);
            else
                sortMap.put(map[r][x], 1);
        }
        // key값으로 정렬된 값에 대해서 value값을 기준으로 다시 정렬
        List<Map.Entry<Integer, Integer>> list_entries = new ArrayList<>(sortMap.entrySet());
        list_entries.sort(Map.Entry.comparingByValue());
        int x = 0;
        for (Map.Entry<Integer, Integer> list_entry : list_entries) {
            map[r][x] = list_entry.getKey();
            map[r][x + 1] = list_entry.getValue();
            x += 2;
        }
        maxCol = Math.max(x, maxCol);
        for (int st = x; st < maxCol; st++)
            map[r][st] = 0;
    }

    private static void C_operation(int c) {
        SortedMap<Integer, Integer> sortMap = new TreeMap<>();
        for (int y = 0; y < maxRow; y++) {
            if (map[y][c] == 0)
                continue;
            if(sortMap.containsKey(map[y][c]))
                sortMap.put(map[y][c], sortMap.get(map[y][c]) + 1);
            else
                sortMap.put(map[y][c], 1);
        }

        List<Map.Entry<Integer, Integer>> list_entries = new ArrayList<>(sortMap.entrySet());
        list_entries.sort(Map.Entry.comparingByValue());
        int y = 0;
        for (Map.Entry<Integer, Integer> list_entry : list_entries) {
            map[y][c] = list_entry.getKey();
            map[y + 1][c] = list_entry.getValue();
            y += 2;
        }
        maxRow = Math.max(y, maxRow);
        for (int st = y; st < maxRow; st++)
            map[st][c] = 0;
    }
}
