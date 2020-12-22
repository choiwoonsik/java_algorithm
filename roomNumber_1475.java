import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class roomNumber_1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();

        int[] numbers = new int[10];
        Arrays.fill(numbers, 0);

        for (int i = 0; i < N.length(); i++) {
            int num = Integer.parseInt(String.valueOf(N.charAt(i)));
                numbers[num]++;
        }
        double max = 0;
        int number = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            if (numbers[i] > max) {
                max = numbers[i];
                number = i;
            }
        }
        int count = 0;
        if (number == 6 || number == 9) {
            max = numbers[6] + numbers[9];
            count = (int) Math.ceil(max / 2.0);
            System.out.println(count);
        }
        else
            System.out.println((int) max);
    }
}
