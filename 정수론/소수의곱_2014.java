package 정수론;

import java.io.*;
import java.util.*;

public class 소수의곱_2014 {
	static int K;
	static int N;
	static PriorityQueue<Long> pq = new PriorityQueue<>();
	static long[] primes;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		primes = new long[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			primes[i] = Integer.parseInt(st.nextToken());
			pq.add(primes[i]);
		}

		long cur = 0;
		while (N-- > 0) {
			cur = pq.poll();

			for (int i = 0; i < K; i++) {
				long val = cur * primes[i];
				pq.add(val);

				if (cur % primes[i] == 0) {
					break;
				}
			}
		}

		System.out.println(cur);
	}
}
