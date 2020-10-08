import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

public class recursivCall {
    static long[] arr = new long[100000000];
    public static void main(String[] args) {

        //팩토리얼을 구하는 알고리즘을 Recusive Call을 활용해서 작성
        /**
         * 2! = 1 * 2
         * 3! = 1 * 2 * 3
         * 4! = 4 * 3!
         * 5! = 5! * 4!
         * ...
         */
        System.out.println(factorial(15));

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i ++) {
            list.add(i);
        }
        System.out.println(list_sum(list));

        List<String> str_list = new ArrayList<>();
        str_list.add("dnstlr");
        str_list.add("abcba");
        str_list.add("dad");
        str_list.add("A");
        str_list.add("");

        str_list.stream().forEach(s -> {
            System.out.println(s+" -> "+check_circle_str(s));
        });

        makeOne(3);

        System.out.println(by123(5));
    }

    // 재귀함수는 전형적인 스택의 구조로 움직인다
    // fac(5) -> fac(4) -> fac(3) -> fac(2) -> fac(1)
    // fac(1) return -> fac(2) return -> fac(3) -> return -> fac(4) return -> fac(5) return;
    private static long factorial(long num){
        if (num > 1) {
            if (arr[(int)num] == 0)
                arr[(int)num] = num * factorial(num - 1);
            else
                return arr[(int)num];
            return arr[(int)num];
        }
        else
            arr[(int)num] = num;
            return num;
    }

    // 주어진 리스트의 합을 구하는 재귀함수
    private static int list_sum(List<Integer> list){
        if (list.size() == 1)
            return list.get(0);
        else if (list.size() > 1)
        {
            int last = list.get(list.indexOf(list.size() - 1));
            list.remove(list.indexOf(list.size() - 1));
            return last + list_sum(list);
        }
        else
            return 0;
    }

    // 회문 : 순서를 거꾸로 읽어도 제대로 읽은 것과 같은 단어와 문장을 의미
    // 회문을 판별하는 리스트 슬라이싱 구현
    private static boolean check_circle_str(String str){
        if (str.length() <= 1)
            return true;

        if (str.charAt(0) == str.charAt(str.length()- 1) )
            return check_circle_str(str.substring(1, str.length() - 1));
        else
            return false;
    }

    /* 정수 n에 대해서
    홀수이면 3n + 1
    짝수이면 n / 2
    n이 1 이될때까지 진행
     */
    private static void makeOne(int n)
    {
        System.out.println(">"+n);
        if (n == 1)
            return;
        else if (n % 2 == 0)
            makeOne(n/2);
        else
            makeOne(3*n + 1);
    }

    // 정수 n에 대해서 1, 2, 3의 조합으로 나타낼수 있는 방법의 수를 구하라
    private static int by123(int n)
    {
        // f(n) = f(n - 1) + f(n -2) + f(n - 3)
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        if (n == 3)
            return 4;
        else if (n >= 4)
            return by123(n-3) + by123(n-2) + by123(n-1);
        else
            return 0;
    }

}
