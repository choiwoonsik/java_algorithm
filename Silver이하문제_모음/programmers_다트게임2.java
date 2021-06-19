package Silver이하문제_모음;

import java.util.Arrays;

public class programmers_다트게임2 {
    public static void main(String[] args)
    {

    }
    public static int solution(String dartResult){
        Dart[] dartGame = new Dart[3];
        for (int i = 0; i < dartGame.length; i++)
            dartGame[i] = new Dart(0, ' ', ' ');

        for (int dart = 0; dart < 3; dart++)
        {
            int idx = 0;
            int point = Integer.parseInt((dartResult.charAt(idx++) + ""));
            if (dartResult.charAt(idx) == '0') {
                point = 10;
                idx++;
            }
            char bonus = dartResult.charAt(idx++);
            char option = ' ';
            if (idx < dartResult.length()) {
                if (Character.isDigit(dartResult.charAt(idx)))
                    dartResult = dartResult.substring(idx);
                else {
                    option = dartResult.charAt(idx);
                    dartResult = dartResult.substring(++idx);
                }
            }
            dartGame[dart] = new Dart(point, bonus, option);
        }

        int sum = 0;
        for (int i = 0; i < 3; i++)
        {
            if (dartGame[i].bonus == 'D')
                dartGame[i].point = (int) Math.pow(dartGame[i].point, 2);
            else if (dartGame[i].bonus == 'T')
                dartGame[i].point = (int) Math.pow(dartGame[i].point, 3);
            if (dartGame[i].option == '*') {
                dartGame[i].point *= 2;
                if (i > 0)
                    dartGame[i - 1].point *= 2;
            }
            else if (dartGame[i].option == '#')
                dartGame[i].point *= -1;
        }
        for (Dart dart : dartGame)
            sum += dart.point;
        return sum;
    }
    private static class Dart
    {
        int point;
        char bonus;
        char option;
        public Dart(int point, char bonus, char option) {
            this.point = point;
            this.bonus = bonus;
            this.option = option;
        }
    }
}
