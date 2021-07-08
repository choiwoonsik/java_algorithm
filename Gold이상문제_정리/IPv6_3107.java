package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class IPv6_3107 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String ipv6 = br.readLine();

		String[] ipv6TwoColon = ipv6.split("::");

		int oneColonCnt = 0;
		for (int i = 0; i < ipv6.length() - 1; i++) {
			if (ipv6.charAt(i) == ':' && ipv6.charAt(i+1) == ':') i++;
			else if (ipv6.charAt(i) == ':') oneColonCnt++;
		}

		if (ipv6TwoColon.length == 2 || (ipv6TwoColon.length == 1 && ipv6.indexOf("::") > 0))
		{
			int twoColonIdx = ipv6.indexOf("::");
			int partCnt;
			if (ipv6TwoColon.length == 1)
				partCnt = oneColonCnt + 1;
			else if (twoColonIdx == 0)
				partCnt = oneColonCnt + 1;
			else
				partCnt = oneColonCnt + 2;

			StringBuilder zero = new StringBuilder();
			if (twoColonIdx == 0)
				zero.append("0000:".repeat(8 - partCnt));
			else if (twoColonIdx == ipv6.length() - 2)
				zero.append(":0000".repeat(8 - partCnt));
			else
				zero.append(":").append("0000:".repeat(8 - partCnt));
			ipv6 = ipv6.replace("::", zero);
		}

		String[] parts = ipv6.split(":");

		StringBuilder answer = new StringBuilder();

		for (String part : parts) {
			String newPart = "0".repeat(4 - part.length()) + part;
			answer.append(newPart).append(":");
		}
		answer.replace(answer.length() - 1, answer.length(), "");
		System.out.println(answer);
	}
}
