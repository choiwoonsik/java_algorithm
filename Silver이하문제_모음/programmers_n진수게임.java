package Silver이하문제_모음;

public class programmers_n진수게임 {
    public static void main(String[] args)
    {
        /*
        숫자를 0부터 9까지 10명이 순서대로 말한다
        10이상의 수부터는 한자리씩 끊어서 말한다
        1, 0, / 1, 1 / 1, 2/ ...

         */
        System.out.println(solution(2, 4, 2, 1));
    }
    private static String solution(int n, int t, int m, int p)
    {
        String jinsu = "0123456789ABCDEF";

        int needNumCnt = 0;
        int turn = 1;

        StringBuilder tubeStr = new StringBuilder();

        int startNum = 0;
        while (true)
        {
            int num = startNum;
            StringBuilder number = new StringBuilder();
            if (num == 0)
                number.append("0");
            else {
                while (num > 0) {
                    number.append(jinsu.charAt(num % n));
                    num /= n;
                }
            }
            for (int inN = number.length() - 1; inN >= 0; inN--) {

                if (needNumCnt == t) {
                    System.out.println(tubeStr);
                    return tubeStr.toString();
                }
                if (turn == m)
                    turn = 0;
                turn %= m;
                if (turn == p % m) {
                    //System.out.println("inN > "+inN);
                    tubeStr.append(number.charAt(inN));
                    needNumCnt++;
                }
                turn++;
            }
            startNum++;
        }
    }
}
