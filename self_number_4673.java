public class self_number_4673 {
	static int			max = 10001;
	static int			start = 1;
	static int			sum = 0;
	static boolean[]	check = new boolean[max];
	public static void main(String[] args)
	{
		while (start < 10001) {
			sum += start;
			bf(start);
			start++;
		}
		print_answer(check);
	}
	static void bf(int n) {
		if (n <= 0)
		{
			if (sum < 10001)
				check[sum] = true;
			sum = 0;
		}
		else
		{
			if (n < 10)
				sum += n;
			else if (n >= 10)
				sum += n % 10;
			bf(n / 10);
		}
	}
	static void print_answer(boolean[] arr)
	{
		for (int i = 1; i < arr.length; i++)
		{
			if (arr[i] == false)
				System.out.println(i);
		}
	}
}
