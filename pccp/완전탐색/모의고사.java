package pccp.완전탐색;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class 모의고사 {
    public static void main(String[] args) {

    }

    private static class Solution {

        static int[] patternOne = {1, 2, 3, 4, 5};
        static int[] patternTwo = {2, 1, 2, 3, 2, 4, 2, 5};
        static int[] patternThree = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        public int[] solution(int[] answers) {
            if (answers.length == 0) return new int[]{};

            Member one = new Member(1);
            Member two = new Member(2);
            Member three = new Member(3);

            for (int i = 0; i < answers.length; i++) {
                int answer = answers[i];
                if (answer == patternOne[i % 5]) one.getPoint();
                if (answer == patternTwo[i % 8]) two.getPoint();
                if (answer == patternThree[i % 10]) three.getPoint();
            }

            List<Member> members = List.of(one, two, three);
            int point = members.stream()
                    .max(Comparator.comparing(it -> it.point))
                    .get()
                    .point;
            return members.stream()
                    .filter(it -> it.point == point)
                    .mapToInt(it -> it.number)
                    .sorted()
                    .toArray();
        }

        private static class Member {
            int number;
            int point = 0;

            public Member(int number) {
                this.number = number;
            }

            public void getPoint() {
                this.point++;
            }
        }
    }
}
