package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class work_2056 {
    //solution number : 2056 - 작업
    public static void main(String args[]) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int[] mark = new int[n+1];
        int[] spend = new int[n+1]; //걸리는 시간 기록
        int[] result = new int[n+1]; //결과값 저장 배열
        for(int i=0; i<=n; i++){
            list.add(new ArrayList<Integer>());
        }
        for(int i=1; i<=n; i++){
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            spend[i] = v[0];
            int nn = v[1]; //반복 횟수
            for(int j=0; j<nn; j++){
                list.get(v[j+2]).add(i);
                mark[i]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<n; i++){
            if(mark[i]==0){
                q.add(i);
                result[i] = spend[i];
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int i:list.get(cur)){
                if(result[i]<result[cur]+spend[i]) //dp 느낌으로 채워주기. 현재 값과 다음에 선택하는 값의 합이 다음 값보다 큰 경우에만 값을 넣어주기
                    result[i] = result[cur] + spend[i];
                mark[i]--;
                if(mark[i]==0) q.add(i);
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, result[i]);
        }
        System.out.println(ans);


    }
}