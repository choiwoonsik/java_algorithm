import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Scanner;

public class SHA256_10930 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        MessageDigest hash = MessageDigest.getInstance("SHA-256");
        hash.update(str.getBytes());
        byte byteData[] = hash.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
        }
        System.out.println(sb.toString());
    }
}
