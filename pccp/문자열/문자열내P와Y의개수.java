package pccp.문자열;

public class 문자열내P와Y의개수 {
    public static void main(String[] args) {
        new Solution().solution("ppyyz");
    }

    private static class Solution {
        boolean solution(String s) {
            String lowerStr = s.toLowerCase();

            int noPLen = lowerStr.replaceAll("p", "").length();
            int noYLen = lowerStr.replaceAll("y", "").length();

            return noPLen == noYLen;
        }
    }
}
