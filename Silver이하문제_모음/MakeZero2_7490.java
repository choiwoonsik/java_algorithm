package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MakeZero2_7490 {
    static ArrayList<ArrayList<Character>> calculators;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++)
        {
            int N = Integer.parseInt(br.readLine());

            calculators = new ArrayList<>();
            ArrayList<Character> list = new ArrayList<>();
            StringBuilder str;

            make_calculators(list, N-1);

            for (ArrayList<Character> calculator : calculators) {
                str = new StringBuilder();
                int num;
                for (num = 0; num < N-1; num++) {
                    str.append(num + 1).append(calculator.get(num));
                }
                str.append(num+1);

                String calcToNum = str.toString();
                calcToNum = calcToNum.replaceAll(" ", "");
                if (calcIsZero(calcToNum) == 0) {
                    System.out.println(str);
                }
            }
            System.out.println();
        }
    }

    private static int calcIsZero(String s) {
        String[] onlyNum = s.split("[+\\-]");
        List<Character> list = new ArrayList<>();

        for (Character c : s.toCharArray()) {
            if (c == '+' || c == '-')
                list.add(c);
        }

        int sum = Integer.parseInt(onlyNum[0]);
        for (int i = 1; i < onlyNum.length; i++)
        {
            if(list.get(i-1).equals('+'))
                sum += Integer.parseInt(onlyNum[i]);
            if(list.get(i-1).equals('-'))
                sum -= Integer.parseInt(onlyNum[i]);
        }

        return sum;
    }

    private static void make_calculators(ArrayList<Character> list, int n)
    {
        if (list.size() == n) {
            calculators.add((ArrayList<Character>) list.clone());
            return;
        }

        //공백 넣기
        list.add(' ');
        make_calculators(list, n);
        list.remove(list.size()-1);

        //더하기 넣기
        list.add('+');
        make_calculators(list, n);
        list.remove(list.size()-1);

        //빼기넣기
        list.add('-');
        make_calculators(list, n);
        list.remove(list.size()-1);
    }
}
