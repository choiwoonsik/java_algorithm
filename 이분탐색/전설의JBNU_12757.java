package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class 전설의JBNU_12757 {
    static int N;
    static int Q;
    static int K;
    static SortedMap<Integer, Integer> db;
    static List<Integer> keys;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ret = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        db = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            db.put(k, v);
        }
        keys = new ArrayList<>(db.keySet());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1 || type == 2) {
                int dataKey = Integer.parseInt(st.nextToken());
                int dataValue = Integer.parseInt(st.nextToken());

                if (type == 1) { // add
                    db.put(dataKey, dataValue);
                    keys.add(lowerBound(dataKey), dataKey);
                } else { // update
                    int lowerPosition = lowerBound(Math.max(dataKey - K, 0));
                    int upperPostion = upperBound(dataKey + K);
                    int key = findMinDiff(dataKey, lowerPosition, upperPostion);

                    if (key >= 0) db.replace(key, dataValue);
                }
            } else { // output
                int dataKey = Integer.parseInt(st.nextToken());
                int lowerPosition = lowerBound(Math.max(dataKey - K, 0));
                int upperPostion = upperBound(dataKey + K);
                int key = findMinDiff(dataKey, lowerPosition, upperPostion);

                if (key == -1)
                    ret.append(-1).append("\n"); // no data
                else if (key == -2)
                    ret.append("?").append("\n"); // dup data
                else
                    ret.append(db.get(key)).append("\n");
            }
        }
        System.out.print(ret);
    }

    private static int findMinDiff(int key, int l, int r) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int minDiff = 10001;
        int midDiffPos = 0;

        for (int i = l; i < r; i++) {
            int diff = Math.abs(keys.get(i) - key);

            if (diff <= minDiff) {
                minDiff = diff;
                midDiffPos = i;
                if (!map.containsKey(minDiff)) map.put(minDiff, 1);
                else map.put(minDiff, map.get(minDiff) + 1);
            }
        }

        if (minDiff == 10001) return -1;
        if (map.get(minDiff) == 1) return keys.get(midDiffPos);
        else if (map.get(minDiff) > 1) return -2;
        return 0;
    }

    private static int lowerBound(int key) {
        int left = 0;
        int right = keys.size();
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (keys.get(mid) < key)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }

    private static int upperBound(int key) {
        int left = 0;
        int right = keys.size();
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (keys.get(mid) <= key)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }
}