package Gold이상문제_정리;

import java.io.*;
import java.util.*;

interface Coffee {
	void make();
}

class Americano implements Coffee {
	@Override
	public void make() {
		System.out.println("Make a cup of Americano");
	}
}

class VanillaLatte implements Coffee {
	public void make() {
		System.out.println("Make a cup of Vanilla Latte");
	}
}

class Cappuccino implements Coffee {
	@Override
	public void make() {
		System.out.println("Make a cup of Cappuccino");
	}
}

class CoffeeMaker {
	private Americano americano;
	private VanillaLatte vanillaLatte;
	private Cappuccino cappuccino;

	public CoffeeMaker () {
		americano = new Americano();
		vanillaLatte = new VanillaLatte();
		cappuccino = new Cappuccino();
	}

	public void makeAmericano() {
		americano.make();
	}
	public void makeVanillaLatte() {
		vanillaLatte.make();
	}
	public void makeCappuccino() {
		cappuccino.make();
	}
}

class FacadePatternDemo {
	private static void main(String[] args) {
		CoffeeMaker coffeeMaker = new CoffeeMaker();

		coffeeMaker.makeAmericano();
		coffeeMaker.makeVanillaLatte();
		coffeeMaker.makeCappuccino();
	}
}

public class Main {
	public static void main(String[] args) {

	}
}