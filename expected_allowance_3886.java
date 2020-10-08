import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class expected_allowance_3886 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException
    {
        int[] arr;
        while (true) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (arr[0] == arr[1] && arr[1] == arr[2]  && arr[0] == 0)
                return;
            int N_dic = arr[0];
            int M_side = arr[1];
            int k = arr[2];
            int[] dice = new int[M_side];

            for (int i = 1; i <= M_side; i++)
                dice[i -1] = i;


        }
    }
}

/*
히데유키는 아버지 우지 사토에게 용돈으로 매달 1000 엔 정도의 지폐를 허용한다. 매월 1 일 청구서의 수는 다음과 같이 결정됩니다.
Ujisato는 m면 주사위 n 개를 준비하고 삭감 k를 선언합니다. 히데유키가이 주사위를 굴립니다.
주어진 지폐의 수는 삭감에의해 감소된 굴린 주사위의 점의 합계입니다.
다행스럽게도 히데유키에게 우지 사토는 스팟의 합이 삭감을 초과하지 않더라도 적어도 하나의 청구서를 주겠다고 약속합니다.
각 주사위는 각면에 1부터 m까지의 스팟이 있으며 각면의 확률은 동일합니다.

이 문제에서는 주어진 청구서 수의 예상 가치를 찾는 프로그램을 작성해야합니다.

삭감된 점의 합이 삭감 k보다 작아도 1개는 준
6면 주사위 2개 준비, 삭감은 3일때
예를 들어 n = 2, m = 6 및 k = 3 일 때 지폐 수가 1, 2, 3, 4, 5, 6, 7, 8 및 9가 될 확률은,,,,,,, 및 , 각각.
6 + 6 - 3 = 9

6 + 5 - 3= 8
5 + 6 - 3= 8

1 + 1 - 3= 1
1 + 2 - 3= 1
2 + 1 - 3= 1
2 + 2 - 3= 1
3 + 1 - 3= 1
1 + 3 - 3= 1

1 + 4 - 3= 2
4 + 1 - 3= 2

즉, 합이 k + 1 이하인 경우 -> 지폐 1개
합이 k + 2인경우 ... <= M면 * n개 - k
따라서 예상 값은

,

약 4.11111111입니다.


입력은 각각이 순서대로 n, m 및 k 세 개의 정수를 포함하는 일련의 행입니다.

다음 조건을 만족합니다.

1 ≤ n
2 ≤ m
0 ≤ k <nm
nm × mn <100000000 (10^8)
입력의 끝은 세 개의 0이 포함 된 행으로 표시됩니다.

출력은 각각 하나의 소수를 포함하는 행으로 구성되어야합니다. 예상 지폐 수이며 10-7 미만의 오류가있을 수 있습니다. 출력에 다른 문자가 없어야합니다.

 */
