package 힣_코테.야놀자_2024_02;

import java.io.*;

import static java.util.stream.Collectors.joining;


class Test0 {
    static class Result {

        /*
         * Complete the 'fizzBuzz' function below.
         *
         * The function accepts INTEGER n as parameter.
         */

        public static void fizzBuzz(int n) {
            // Write your code here
            Car car = new Car("woonsik car", 100);

            car.go();
        }

        static class Car {
            private String name;
            private int fuel;
            private int moveTotalKilometer;
            private int distance = 0;

            public Car(String name, int fuel) {
                this.name = name;
                this.fuel = fuel;
            }

            public void getCarName() {
                System.out.println(name);
            }

            public void go() {
                if (fuel <= 0) {
                    System.out.println("연료가 부족합니다.");
                } else {
                    System.out.println("1 block go!");
                    distance++;
                    moveTotalKilometer++;
                    fuel--;
                }
            }

            public void addFuel() {
                fuel++;
            }

            public void getCurrentFuel() {
                System.out.println(fuel);
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        Result.fizzBuzz(n);

        bufferedReader.close();
    }
}

