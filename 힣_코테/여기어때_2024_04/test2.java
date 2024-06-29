package 힣_코테.여기어때_2024_04;

/**
 * 다이얼을 돌리면 오른쪽 모든 다이얼도 함께 돌아간다.
 * 0 1234 5 6789
 * 1 2345 6 7890
 * 2
 * 2
 * 3
 * 4
 * 5 6789 0 1234
 * 6 7890 1 2345
 * 7 8901 2 3456
 * 8 9012 3 4567
 * 9 0123 4 5678
 */

public class test2 {
    public static void main(String[] args) {
        Solution_test2 solution = new Solution_test2();
        int[] dials = {9, 0, 0, 0, 0};
        int[] password = {9, 9, 1, 9, 1};
        System.out.println(solution.solution(dials, password));
    }
}

class Solution_test2 {
    public int solution(int[] dials, int[] password) {
        Lock lock = new Lock(dials);
        lock.rotate(password);
        return lock.totalRotateCount;
    }

    private static class Lock {
        private final int[] dials;
        private int totalRotateCount;

        public Lock(int[] dials) {
            this.dials = dials;
        }

        public void rotate(int[] password) {
            for (int pos = 0; pos < dials.length; pos++) {
                int rotate = rotate(dials[pos], password[pos]);
                totalRotateCount += Math.abs(rotate);
                moveRightDials(pos, rotate, dials);
            }
        }

        private int rotate(int currentDial, int targetDial) {
            if (currentDial == targetDial) return 0;

            if (currentDial == 0) {
                if (targetDial > 5) return targetDial - 10;
                else return targetDial;
            }
            if (targetDial == 0) {
                if (currentDial > 5) return 10 - currentDial;
                else return currentDial;
            }

            if (currentDial < 5) {
                if (targetDial < currentDial + 5) return targetDial - currentDial;
                else if (targetDial > currentDial + 5) return targetDial - 10 - 1;
                else return 5;
            } else {
                if (targetDial < currentDial - 5) return targetDial + 10 - currentDial;
                else if (targetDial > currentDial - 5) return targetDial - currentDial;
                else return 5;
            }
        }

        private void moveRightDials(int pos, int rotate, int[] dials) {
            if (pos == dials.length - 1) return;
            if (rotate < 0) {
                for (int rightDial = pos; rightDial < dials.length; rightDial++) {
                    int nextDial = dials[rightDial] + rotate;
                    if (nextDial < 0) dials[rightDial] = 10 + nextDial;
                    else dials[rightDial] = nextDial;
                }
            } else {
                for (int rightDial = pos; rightDial < dials.length; rightDial++) {
                    int nextDial = dials[rightDial] + rotate;
                    if (nextDial > 9) dials[rightDial] = nextDial - 10;
                    else dials[rightDial] = nextDial;
                }
            }
        }
    }
}
