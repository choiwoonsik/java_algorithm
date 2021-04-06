package Gold이상문제_정리.코딩테스트;

public class 카카오커머스_2 {
	private boolean[] include= new boolean[16];

	public int solve(int n, int selected, int r, int[][] needs){
		if(n == needs[0].length && selected == r){
			int count = 0;
			for(int i=0; i<needs.length; i++){
				boolean flag = true;
				for(int j=0; j<needs[i].length; j++){
					if(needs[i][j] == 1 && !include[j]){
						flag = false;
						break;
					}
				}
				if(flag) count++;
			}
			return count;
		}
		if(n == needs[0].length) return 0;

		include[n] = true;
		int res1 = solve(n+1, selected+1, r, needs);
		include[n] = false;
		int res2 = solve(n+1, selected, r, needs);
		return Math.max(res1, res2);
	}

	public int solution(int[][] needs, int r) {
		int answer = 0;
		answer = solve(0, 0, r, needs);
		return answer;
	}
}

