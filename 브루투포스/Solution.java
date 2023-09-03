package 브루투포스;

import java.util.*;

class 프라임테스트_test {
    public int solution(String s) {
        int size = s.length();

        ArrayList<Integer> primes;
        primes = new ArrayList<>(getPrimes(size));
        Collections.sort(primes);

        System.out.println(primes);
        for (int prime : primes) {
            boolean result = findLen(s, prime);
            if (result) return prime;
        }

        return size;
    }

    public static HashSet<Integer> getPrimes(int number) {
        HashSet<Integer> primeSet = new HashSet<>();

        primeSet.add(1);
        if (number > 1) primeSet.add(number);

        for (int div = 2; div * div <= number; div++) {
            if (number % div == 0) {
                primeSet.add(div);
                primeSet.add(number / div);
            }
        }

        return primeSet;
    }

    private static boolean findLen(String s, int len) {
        String subLine = s.substring(0, len);
        boolean isSub = true;
        String replaced = s;

        if (replaced.replaceAll(subLine, "").isBlank()) return true;
        while (true) {
            if (replaced.startsWith(subLine)) replaced = replaced.replaceFirst(subLine, "");
            else {
                isSub = false;
                break;
            }
            if (replaced.isBlank()) break;
        }

        return isSub;
    }
}

class RandomStringGenerator {
    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static String generateString(int length) {
        String randomString = generateRandomString(length);
        StringBuilder sb = new StringBuilder();

        while (sb.length() < 1000000) {
            sb.append(randomString);
        }

        return sb.toString();
    }
}


class Solution222 {
    public static void main(String[] args) {
        프라임테스트_test d = new 프라임테스트_test();

//        System.out.println(d.solution(RandomStringGenerator.generateRandomString(1000000)));
        System.out.println(d.solution(RandomStringGenerator.generateString(50000)));
    }
}
