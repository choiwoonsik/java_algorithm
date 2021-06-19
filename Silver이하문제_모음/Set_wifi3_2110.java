package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Set_wifi3_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] houseArr = new int[N];
        for (int i = 0; i < N; i++){
            int pos = Integer.parseInt(br.readLine());
            houseArr[i] = pos;
        }
        Arrays.sort(houseArr);

        int minDistance = houseArr[1] - houseArr[0];
        int maxDistance = houseArr[houseArr.length-1] - houseArr[0];
        int ret = 0;

        while (minDistance <= maxDistance)
        {
            int startPos = houseArr[0];
            int midDistance = (minDistance+maxDistance) /2;
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (startPos + midDistance >= houseArr[i]) {
                    count++;
                    startPos = houseArr[i];
                }
            }
            if (count >= C){
                ret = minDistance;
                minDistance = midDistance + 1;
            }
            else
                maxDistance = midDistance - 1;
        }
        System.out.println(ret);
    }
}
