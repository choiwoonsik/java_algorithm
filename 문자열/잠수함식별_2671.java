package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잠수함식별_2671 {
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        boolean result = false;
        if (s.length() != 0) {
            if (s.charAt(0) == '1') {
                result = discript(0, 0);
            }
            else {
                result = discript(0, 4);
            }
        }
        if (result) System.out.println("SUBMARINE");
        else System.out.println("NOISE");
    }

    private static boolean discript(int idx, int state) {
        if (idx >= s.length() - 1) {
            return state == 5 || state == 3;
        }
        switch (state) {
            case 0: {
                if (s.charAt(idx + 1) == '0') return discript(idx + 1, 1);
                else return false;
            }
            case 1: {
                if (s.charAt(idx + 1) == '0') return discript(idx + 1, 2);
                else return false;
            }
            case 2: {
                if (s.charAt(idx + 1) == '0') return discript(idx + 1, 2);
                else return discript(idx + 1, 3);
            }
            case 3: {
                if (s.charAt(idx + 1) == '1') {
                    if (idx + 3 < s.length() && s.charAt(idx + 2) == '0' && s.charAt(idx + 3) == '0') {
                        return discript(idx + 1, 0);
                    }
                    else return discript(idx + 1, 3);
                }
                else return discript(idx + 1, 4);
            }
            case 4: {
                if (s.charAt(idx + 1) == '1') return discript(idx + 1, 5);
                return false;
            }
            case 5: {
                if (s.charAt(idx + 1) == '1') return discript(idx + 1, 0);
                else return discript(idx + 1, 4);
            }
        }
        return true;
    }
}
