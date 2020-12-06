public class programmers_다트게임 {
    public static void main(String[] args) {
        solution("1D#2S*3S");
    }
    private static int solution(String dartResult) {
        // 3번의 기회 , 각 기회마다 0 ~ 10점, Single(1제곱) , D (2제곱) , T (3제곱)
        // 스타상 * -> 해당 점수 , 직전의 점수를 2배로, (처음의 경우 해당 점수만 2배로)
        // 아차상 # -> 해당 점수 마이너스로
        // 중복된 스타상 ** -> 4배로
        // 중복된 스타상 아차상 *# -> 두배로 마이너스
        // 점수|보너스(SDT)|옵션(*,#,없음)
        // 1S2D*#T
        Dart[] dart = new Dart[3];
        for (int i = 0; i < dart.length; i++)
            dart[i] = new Dart(0, ' ', ' ');

        for (int turn = 0; turn < 3; turn++) {
            int point;
            char bonus = 0;
            char option = 0;
            int idx = 1;
            if (dartResult.charAt(1) - '0' == 0) {
                point = 10;
                idx = 2;
            } else
                point = dartResult.charAt(0) - '0';
            while (idx < dartResult.length() && (dartResult.charAt(idx) - '0' > 9 || dartResult.charAt(idx) - '0' < 0)) {
                if (dartResult.charAt(idx) == 'S' || dartResult.charAt(idx) == 'D' || dartResult.charAt(idx) == 'T')
                    bonus = dartResult.charAt(idx);
                else
                    option = dartResult.charAt(idx);
                idx++;
            }
            dart[turn] = new Dart(point, bonus, option);
            dartResult = dartResult.substring(idx, dartResult.length());
        }

        for (int i = 0; i < dart.length; i++) {
            if (dart[i].bonus == 'D')
                dart[i].point = (int)Math.pow(dart[i].point, 2);
            else if (dart[i].bonus == 'T')
                dart[i].point = (int)Math.pow(dart[i].point, 3);

            if (dart[i].option == '#')
                dart[i].point *= -1;
            else if (dart[i].option == '*') {
                dart[i].point *= 2;
                if (i >= 1)
                    dart[i - 1].point *= 2;
            }
        }
        int sum = 0;
        for (int i = 0; i < dart.length; i++) {
            System.out.println(dart[i]);
            sum += dart[i].point;
        }

        System.out.println(sum);
        return sum;
    }
    private static class Dart
    {
        int point;
        char bonus;
        char option = ' ';

        public Dart(int point, char bonus, char option) {
            this.point = point;
            this.bonus = bonus;
            this.option = option;
        }

        @Override
        public String toString() {
            return "Dart{" +
                    "point=" + point +
                    ", bonus=" + bonus +
                    ", option=" + option +
                    '}';
        }
    }
}
