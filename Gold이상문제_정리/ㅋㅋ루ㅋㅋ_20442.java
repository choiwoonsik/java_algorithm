package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

/*
비어있지않은 R로만 이뤄진 문자열 : ㅋㅋ루ㅋㅋ
ㅋㅋ루ㅋㅋ 양옆에 K가 붙어도 ㅋㅋ루ㅋㅋ
KKRKK
5
RRKRR
4

부분 수열이므로 R - (중간 상관 없음) - R 의 구간을 일단 구하고, 양옆에 붙일 수 있는 K개수를 구하면 된다.
 */
public class ㅋㅋ루ㅋㅋ_20442 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int answer = twoPointer(str);
        System.out.print(answer);
    }

    private static int twoPointer(String s) {
        ArrayList<Integer> lK = new ArrayList<>();
        ArrayList<Integer> rK = new ArrayList<>();

        int kCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'K') kCnt++;
            else if (s.charAt(i) == 'R') lK.add(kCnt);
        }
        kCnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'K') kCnt++;
            else if (s.charAt(i) == 'R') rK.add(kCnt);
        }
        rK.sort(Comparator.reverseOrder());

        int left = 0;
        int right = rK.size() - 1;
        int max = 0;

        while (left <= right) {
            // 부분 수열이므로 R의 양끝 구간을 일단 구하고, 바깥 K의 개수를 맞추기 위해 최소값 * 2를 더해준다.
            max = Math.max(max, (right - left + 1) + (2 * Math.min(lK.get(left), rK.get(right))));

            if (lK.get(left) < rK.get(right)) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
