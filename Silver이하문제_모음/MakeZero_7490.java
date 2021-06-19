package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MakeZero_7490 {

    static List<ArrayList<String>> operations;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++)
        {
            int N = Integer.parseInt(br.readLine());
            operations = new ArrayList<>();

            find_combination(new ArrayList<>(), N-1);

            for (ArrayList<String> opers : operations)
            {
                StringBuilder str = new StringBuilder();
                for (int num = 0; num < N-1; num++)
                {
                    str.append(num+1);
                    str.append(opers.get(num));
                }
                String ret = str.append(N).toString();
                String tmp = ret.replaceAll(" ", "");
                if (calculator(tmp) == 0)
                    System.out.println(ret);
            }
            System.out.println();
        }
    }

    private static int calculator(String operStr) {
        String[] sep = operStr.split("[+\\-]");
        ArrayList<Character> opr = new ArrayList<>();

        for (char c : operStr.toCharArray())
        {
            if (c == '+' || c == '-')
                opr.add(c);
        }

        int ret = Integer.parseInt(sep[0]);
        for (int i = 1; i < sep.length; i++)
        {
            if (opr.get(i-1).equals('+'))
                ret += Integer.parseInt(sep[i]);
            else if (opr.get(i-1).equals('-'))
                ret -= Integer.parseInt(sep[i]);
        }
        return ret;
    }

    private static void find_combination(ArrayList<String> operStack, int n) {
        if (operStack.size() == n)
        {
            operations.add((ArrayList<String>) operStack.clone());
            return;
        }

        operStack.add(" ");
        find_combination(operStack, n);
        operStack.remove(operStack.size()-1);

        operStack.add("+");
        find_combination(operStack, n);
        operStack.remove(operStack.size()-1);

        operStack.add("-");
        find_combination(operStack, n);
        operStack.remove(operStack.size()-1);
    }
}
