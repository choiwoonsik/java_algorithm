package Silver이하문제_모음;//온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 이때, 회원들을 나이가 증가하는 순으로,
// 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class sort_age_10814 {
	static int all;
	static ArrayList<info> list = new ArrayList<>();

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		all = sc.nextInt();

		for (int i = 0; i < all; i++)
		{
			int age = sc.nextInt();
			String name = sc.next();
			list.add(new info(age, name));
		}
		list = sort(list);
		for (info l : list)
		{
			System.out.print(l.age + " ");
			System.out.println(l.name);
		}
	}

	static ArrayList sort(ArrayList list)
	{
		info_Comparator comp = new info_Comparator();
		Collections.sort(list, comp);
		return (list);
	}
}

class info
{
	int age;
	String name;
	info(int age, String name)
	{
		this.age = age;
		this.name = name;
	}
}

class info_Comparator implements Comparator<info>
{
	@Override
	public int compare(info first, info second)
	{
		int age1 = first.age;
		int age2 = second.age;
		//나이가 더 많으면 바꾼다 true
		if (age1 > age2)
			return 1;
		//나이가 더 적으면 바꾸지 않는다 false
		else if (age1 < age2)
			return -1;
		else
			return 0;
	}
}
