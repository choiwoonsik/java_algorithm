package 힣_코테.케이뱅크_2024_06;

import java.util.Arrays;
import java.util.Collection;

/**
 * dp[1][2] = 2
 * dp[1][3] = 6
 * dp[1][4] = 24 = dp[1][3] * 4
 * <p>
 * dp[2][3] = 6
 * dp[2][4] = 24 = dp[2][3] * 4
 * dp[2][5] = 120 = dp[2][4] * 5
 * dp[2][6] = 720 = dp[2][5] * 6
 * <p>
 * dp[3][4] = 12
 * dp[3][5] = 60 = dp[3][4] * 5
 * dp[3][6] = 360 = dp[3][5] * 6
 * <p>
 * dp[4][5] = 20
 * <p>
 * 1 1 2 3  4   5 6 7  8  9 10 11 12 13 14 15  16 17
 * 2 3 4 5 [4] 6 7 8 [5] 9 10 11 [6] 12 13 14 [7] 15
 * 1*2
 * 2*3
 * 3*4
 * 4*5
 * 2*4
 */
public class Sol3 {
    public static void main(String[] args) {
        Sol3 sol3 = new Sol3();
        System.out.println(sol3.solution(1000000));
    }

    static final long MAX = 1_000_000_000_000L;

    public long solution(int n) {
        int len = 20000;
        long[][] dp = new long[len + 1][len + 1];

        dp[1][2] = 2;

        for (int i = 3; i < len; i++) {
            if (dp[1][i - 1] > MAX) break;
            dp[1][i] = dp[1][i - 1] * i;
        }

        for (int i = 2; i < len; i++) {
            dp[i][i + 1] = (long) i * (i + 1);
            for (int j = i + 2; j < len; j++) {
                if (dp[i][j - 1] * j > MAX) break;
                dp[i][j] = dp[i][j - 1] * j;
            }
        }

        long[] array = Arrays.stream(dp)
                .flatMapToLong(Arrays::stream)
                .distinct()
                .sorted()
                .toArray();

        System.out.println(Arrays.toString(array));
        return array[n];
    }
}
/*
-- 코드를 입력하세요

with
f_dist as (
    select f.id1 as userid, gu.distance
    from friends f
    join game_users gu on f.id2 = gu.id
    union all
    select f.id2 as userid, gu.distance
    from friends f
    join game_users gu on f.id1 = gu.id
),
f_max_dist as (
    select userid, max(distance) as dist
    from f_dist
    group by userid
)

select gu.id as ID
from game_users gu
left join f_max_dist fmd on gu.id = fmd.userid
where gu.distance > fmd.dist
order by gu.id desc
;
 */