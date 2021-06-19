package algorithm_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N_Queens {
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] Ns = br.readLine().split(" ");
        int N = Integer.parseInt(Ns[0]);

        // 모든 행을 채운 결과
        result = new int[N];
        //각 행을 채운 결과를 담는곳
        List<Integer> current_candidate = new ArrayList<>();
        DFS(N, 0, current_candidate);
    }
    private static void DFS(int N, int current_row, List<Integer> current_candidate) {
        if (current_row == N){
            for (int i = 0; i < current_candidate.size(); i++){
                result[i] = current_candidate.get(i);
            }
            Arrays.stream(result).forEach(s -> System.out.print(s+" "));
            System.out.println();
        }
        else {
            for (int current_col = 0; current_col < N; current_col++){
                if (is_available(current_candidate, current_col)){
                    current_candidate.add(current_col);
                    DFS(N, current_row + 1, current_candidate);
                    //조건에 부합되지 않거나 N개를 모두 채운 경우 하나씩 빼주면서 backTracking해준다
                    current_candidate.remove(current_candidate.size() - 1);
                }
            }
        }
    }

    private static boolean is_available(List<Integer> candidate, int current_col) {

        int current_row = candidate.size();
        for (int queen_row = 0; queen_row < current_row; queen_row++){
            if (candidate.get(queen_row) == current_col
            || Math.abs(candidate.get(queen_row) - current_col) == current_row - queen_row){
                return false;
            }
        }
        return true;
    }
}
