package modernJavaInAction

import modernJavaInAction.Color.GREEN
import modernJavaInAction.Color.RED
import java.io.BufferedReader
import java.io.FileReader
import java.security.PrivilegedAction
import java.util.Arrays
import java.util.Collections
import java.util.Comparator.comparing
import java.util.EnumSet
import java.util.Optional
import java.util.OptionalInt
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.IntPredicate
import java.util.function.Predicate
import java.util.function.Supplier
import java.util.stream.Collector
import java.util.stream.Collectors
import java.util.stream.Collectors.counting
import java.util.stream.Collectors.groupingBy
import java.util.stream.Collectors.joining
import java.util.stream.Collectors.maxBy
import java.util.stream.Collectors.partitioningBy
import java.util.stream.Collectors.toList
import java.util.stream.IntStream
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.sqrt

fun filterGreenApples(inventory: List<Apple>): List<Apple> {
    val result = mutableListOf<Apple>()

    for (apple in inventory) {
        if (GREEN == apple.color) {
            result.add(apple)
        }
    }

    return result
}

enum class Color {
    RED, GREEN
}

open class Fruit {
    open val color: Color = RED
    open val weight: Int = 100
}

class Apple(
    override val color: Color = RED,
    override val weight: Int = 100
) : Fruit()

class Orange(
    override val color: Color = GREEN,
    override val weight: Int = 150
) : Fruit()

fun interface FruitPredicate<T : Fruit> {
    fun test(t: T): Boolean
}

class FruitHeavyWeightPredicate<T : Fruit> : FruitPredicate<T> {
    override fun test(t: T): Boolean {
        return t.weight > 150
    }
}

class FruitGreenColorPredicate<T : Fruit> : FruitPredicate<T> {
    override fun test(t: T): Boolean {
        return GREEN == t.color
    }
}

fun <T : Fruit> filterFruit(inventory: List<T>, predicate: FruitPredicate<T>): List<T> {
    val result = mutableListOf<T>()

    for (apple in inventory) {
        if (predicate.test(apple)) {
            result.add(apple)
        }
    }

    return result
}

fun mainn() {
    val inventory = listOf(Apple(RED, 100), Apple(GREEN, 90), Apple(RED, 150))
    val greenApples = filterFruit(inventory, FruitGreenColorPredicate())
    val heavyApples = filterFruit(inventory, FruitHeavyWeightPredicate())
    val redApples = filterFruit(inventory) { apple -> RED == apple.color }

    filterFruit(inventory) { apple -> RED == apple.color }

    println(greenApples)
    println(heavyApples)
    println(redApples)

    val orangeInventory = listOf(Orange(RED, 50), Orange(GREEN, 100), Orange(RED, 150))
    val redOranges = filterFruit(orangeInventory) { apple -> RED == apple.color }
    val heavyWeightOranges = filterFruit(orangeInventory) { apple -> apple.weight > 150 }

    println(redOranges)
    println(heavyWeightOranges)

    inventory.sortedWith { o1, o2 -> o1.weight - o2.weight }

    val thread = Thread(object : Runnable {
        override fun run() {
            println("Hello World")
        }
    })

    val thread2 = Thread { println("Hello World") }

    val executorService = Executors.newCachedThreadPool()
    executorService.submit(
        object : Callable<String> {
            override fun call(): String {
                return "Hello World"
            }
        }
    )

    executorService.submit { Callable { "Hello World" } }
}

fun lambda() {
    val byWeight = Comparator<Apple> { o1, o2 -> o1.weight - o2.weight }

    val number = { 42 }
    number()

    val add = { x: Int, y: Int -> x + y }
    add(1, 2)

    val length = { s: String -> s.length }
    length("Hello")

    val compareWight = { o1: Apple, o2: Apple -> o1.weight - o2.weight }
    compareWight(Apple(RED, 100), Apple(GREEN, 150))

    val filterOdd = { n: List<Int> -> n.filter { it % 2 == 1 } }
    filterOdd(listOf(5))

    val none = { {} }
    none()

    val booleanExpression = { list: List<String> -> list.isEmpty() }
    val createObject = { weight: Int, color: Color -> Apple(color, weight) }
    val userObject = { apple: Apple -> println(apple) }
    val extractInObject = { apple: Apple -> apple.color }
    val combinationObjects = { a: Int, b: Int -> a * b }
    val compareObjects = { a: Apple, b: Apple, c: Apple -> a.color == b.color && b.color == c.color }
}

fun interface MyPredicate<T> {
    fun filter(t: T): Boolean
}

fun interface Test {
    fun filter(t: String): Boolean
}

val dd = Test { it == "String" }
val filter = MyPredicate<String> { string -> string == "String" }
val void = { -> Unit }
val appleFunctionalInterface = { apple1: Apple, apple2: Apple -> apple1.weight + apple2.weight }

class DD {
    val filter = MyPredicate<String> { string -> string == "String" }
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<Int> {
    return this.customFilter(predicate)
}

fun filteringNumberList() {
    val oddNumberFilter = { number: Int -> number % 2 == 1 }
    val largeNumberFilter = { number: Int -> number > 10 }

    listOf(1, 2, 3)
        .customFilter { oddNumberFilter(it) }
        .customFilter { largeNumberFilter(it) }
}

class Read {
    fun processFile(p: BufferedReaderProcessor): String {
        try {
            val br = BufferedReader(FileReader("data.txt"))
            return p.process(br)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun interface BufferedReaderProcessor {
        fun process(bufferedReader: BufferedReader): String
    }

    val readOneLine = { br: BufferedReader -> br.readLine() }
    val readTwoLine = { br: BufferedReader -> br.readLine() + br.readLine() }
    val findWordMostUsed =
        { br: BufferedReader ->
            val map = mutableMapOf<String, Int>()
            br.readLines()
                .map { line ->
                    line.split(" ").forEach { word ->
                        map[word] = map.getOrDefault(word, 0) + 1
                    }
                }

            map.maxByOrNull { it.value }?.key ?: ""
        }

    fun useProcessFileLambda() {
        processFile(readOneLine)
        processFile(readTwoLine)
        processFile(findWordMostUsed)
    }
}

fun interface MyPredicate2<T> {
    fun test(t: T): Boolean
}

class PredicateTest {
    val s = Predicate<String> { it == "java" }
        .or { it == "kotlin" }
        .and { it == "hello" }
        .and { it == "world" }

    fun <T> filter1(list: List<T>, predicate: Predicate<T>): List<T> {
        return list.filter { predicate.test(it) }
    }

    fun <T> filter2(list: List<T>, test: (T) -> Boolean): List<T> {
        return list.filter { test(it) }
    }

    val onlyNoneEmptyString = Predicate<String> { it.isNotEmpty() }

    val result1 = filter1(listOf("java", "kotlin", "hello", "world"), onlyNoneEmptyString)
    val result2 = filter2(listOf("java", "kotlin", "hello", "world")) { it.length >= 2 }
}

fun interface MyConsumer<T> {
    fun accept(t: T)
}

class ConsumerTest {
    val consumer1 = Consumer<String> { println(it) }
    val consumer2 = object : Consumer<String> {
        override fun accept(t: String) {
            println(t)
        }
    }
    val consumer3 = { s: String -> println(s) }

    fun <T> filter1(list: List<T>, consumer: Consumer<T>) {
        return list.forEach { consumer.accept(it) }
    }

    fun <T> filter2(list: List<T>, consumer: (T) -> Unit) {
        return list.forEach { consumer(it) }
    }

    val result1 = filter1(listOf("hello", "world"), consumer1)
    val result2 = filter1(listOf("hello", "world"), consumer2)
    val result3 = filter2(listOf("hello", "world"), consumer3)
    val result4 = filter2(listOf("hello", "world")) { println(it) }

    val list = mutableListOf<String>()
    val result5 = filter2(mutableListOf("hello", "world")) { s -> list.add(s) }
}

fun interface MyFunction<T, R> {
    fun apply(t: T): R
}

class FunctionTest {
    val function1 = Function<List<String>, Int> { it.sumOf { it.length } }
    val function2 = object : Function<List<String>, Int> {
        override fun apply(t: List<String>): Int {
            return t.sumOf { it.length }
        }
    }
    val function3 =
        { list: List<String> ->
            try {
                list.sumOf { it.length }
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

    fun <T> filter1(list: List<T>, function: Function<List<T>, Int>): Int {
        return function.apply(list)
    }

    fun <T> filter2(list: List<T>, function: (List<T>) -> Int): Int {
        return function(list)
    }

    val result1 = filter1(listOf("hello", "world"), function1)
    val result2 = filter1(listOf("hello", "world"), function2)
    val result3 = filter2(listOf("hello", "world"), function3)
    val result4 = filter2(listOf("hello", "world")) { it.sumOf { it.length } }
}

class PrimitiveTypeTest {
    fun <T> filter1(list: List<T>, predicate: Predicate<T>): List<T> {
        return list.filter { predicate.test(it) }
    }

    fun filter2(list: List<Int>, intPredicate: IntPredicate): List<Int> {
        return list.filter { intPredicate.test(it) }
    }

    val result1 = filter1(listOf(1, 2, 3), Predicate { it % 2 == 1 })
    val result2 = filter2(listOf(1, 2, 3), IntPredicate { it % 2 == 1 })
    val result3 = filter1(listOf(1, 2, 3)) { it % 2 == 1 }
    val result4 = filter2(listOf(1, 2, 3)) { it % 2 == 1 }
}

class TargetType {
    private val inventory = listOf(Apple(RED, 100), Apple(GREEN, 90), Apple(RED, 150))

    val heavierThan150g1 = filter(inventory) { apple: Apple -> apple.weight > 150 }
    val heavierThan150g3 = filter(inventory) { apple -> apple.weight > 150 }
    val heavierThan150g2: List<Apple> = filter(inventory, { apple: Apple -> apple.weight > 150 })

    private fun filter(inventory: List<Apple>, p: Predicate<Apple>): List<Apple> {
        return inventory.filter { p.test(it) }
    }

    var localVariable = 42
    fun test() {
//        var localVariable = 42
        val intLambda = { -> localVariable + 10 }
        val c: Callable<Int> = Callable { intLambda() }
        val p: PrivilegedAction<Int> = PrivilegedAction { intLambda() }
        localVariable = 44
    }

    fun tt() {
        localVariable = 33
        val t1: () -> Unit = {
            localVariable *= 11
            println("Tricky example $localVariable")
        }
        localVariable = 22
    }
}

class MethodReference {
    fun methodReference() {
        val inventory = listOf(Apple(RED, 100), Apple(GREEN, 90), Apple(RED, 150))
        inventory.sortedWith { a1, a2 -> a1.weight - a2.weight }
        inventory.sortedWith(comparing(Apple::weight))
        inventory.sortedWith(compareBy(Apple::weight))
        inventory.sortedBy { it.weight }
    }
}

class Dish(
    val name: String = "",
    val calories: Int = 0
) {
    fun isVegetarian(): Boolean {
        return false
    }
}


class Part2 {
    companion object {
        val menu = listOf(
            Dish("Java", 100),
            Dish("Kotlin", 200),
            Dish("C++", 300),
            Dish("C#", 400),
            Dish("Python", 500),
            Dish("Go", 600),
            Dish("JavaScript", 700),
            Dish("TypeScript", 800),
            Dish("Ruby", 900),
            Dish("Rust", 1000),
        )
    }

    class StreamPart {
        fun asIs() {
            val lowCaloricDishes = mutableListOf<Dish>()

            for (dish in menu) {
                if (dish.calories < 400) {
                    lowCaloricDishes.add(dish)
                }
            }
            Collections.sort(lowCaloricDishes, object : Comparator<Dish> {
                override fun compare(o1: Dish, o2: Dish): Int {
                    return o1.calories - o2.calories
                }
            })
            val lowCaloricDishesName = mutableListOf<String>()
            for (dish in lowCaloricDishes) {
                lowCaloricDishesName.add(dish.name)
            }
        }

        fun toBe() {
            val lowCaloricDishesName1: List<String> = menu
                .parallelStream()
                .filter { dish -> dish.calories < 400 }
                .sorted(comparing(Dish::calories))
                .map(Dish::name)
                .collect(toList())

            val lowCaloricDishesName2 = menu
                .filter { dish -> dish.calories < 400 }
                .sortedBy { it.calories }
                .map { it.name }
        }

        fun innerLoop() {
            val threeHighCaloricDishNameList = menu
                .stream()
                .filter { it.calories > 300 }
                .map { it.name }
                .limit(3)
                .collect(toList())
        }

        fun streamUsed() {
            val s = listOf(1, 2, 3, 4, 5).stream()
            s.forEach { println(it) }
            s.forEach { println(it) }
        }

        fun useStream() {
            val vegetarianDishes1 = mutableListOf<Dish>()
            for (dish in menu) {
                if (dish.isVegetarian()) {
                    vegetarianDishes1.add(dish)
                }
            }

            val vegetarianDishes2 = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList())

            val numbers = listOf(1, 2, 1, 3, 3, 2, 4)
            val evenNumbers = numbers.stream()
                .filter { i -> i % 2 == 0 }
                .distinct()
                .collect(toList())

            val lowCaloricDishes = menu.stream()
                .takeWhile { dish -> dish.calories < 320 }
                .collect(toList())

            val lowCaloricDishes2 = menu.stream()
                .dropWhile { dish -> dish.calories < 320 }
                .collect(toList())

            val limited = menu.stream()
                .limit(3)
                .collect(toList())

            menu.stream()
                .map { it.name }
                .collect(toList())

        }

        fun streamTest1() {
            val stream = Arrays.stream(arrayOf("1", "2"))
            val stream1: Stream<Dish> = menu.stream()

            val result1: MutableList<Stream<String>> = menu.stream()
                .map { it.name.split("").toTypedArray() }
                .map { it: Array<String> -> Arrays.stream(it) }
                .collect(toList())

            val result2: MutableList<String>? = menu.stream()
                .map { it.name.split("").toTypedArray() }
                .flatMap { it: Array<String> -> Arrays.stream(it) }
                .collect(toList())

            val collect: MutableList<Array<String>> = menu
                .stream()
                .map { it.name.split("").toTypedArray() }
                .collect(toList())
        }

        fun findfind() {
            if (menu.stream().anyMatch { it.isVegetarian() }) {
                println("The menu is (somewhat) vegetarian friendly!!")
            }

            menu.stream()
                .allMatch { dish -> dish.calories < 1000 }

            menu.stream()
                .filter { it.isVegetarian() }
                .findAny()
                .ifPresent { dish -> println(dish.name) }
        }

        fun reduceTest() {
            val numbers = listOf(1L, 2L, 3L, 4L, 5L)
            var sum1 = 0L
            for (x: Long in numbers) {
                sum1 += x
            }

            val sum2 = numbers.stream().reduce(0L) { a, b -> max(a, b) }
//            val sum3 = numbers.stream().reduce(0L, Long::sum)
            val sum4 = numbers.stream().reduce { a, b -> a + b }

            val max1 = numbers.stream().reduce(0L) { a, b -> max(a, b) }
            numbers.stream().map { 1 }.reduce { a, b -> a + b }
            numbers.stream().count()
            numbers.parallelStream().map { 1 }.reduce { a, b -> a + b }
        }

        fun reduceRealTest() {
            class Trader(
                val name: String,
                val city: String
            )

            class Transaction(
                val trader: Trader,
                val year: Int,
                val value: Int
            )

            val minsoo = Trader("Minsoo", "Seoul")
            val jinwoo = Trader("Jinwoo", "Busan")
            val gildong = Trader("Gildong", "Anyang")
            val younghee = Trader("Younghee", "Pangyo")
            val woonsik = Trader("Woonsik", "Gawchen")
            val donghyun = Trader("Donghyun", "Pyeongchon")
            val jongjin = Trader("Jongjin", "Indeogwon")

            val transactions = listOf(
                Transaction(minsoo, 2011, 300),
                Transaction(jinwoo, 2012, 1000),
                Transaction(gildong, 2011, 400),
                Transaction(younghee, 2012, 710),
                Transaction(woonsik, 2012, 700),
                Transaction(donghyun, 2012, 950),
                Transaction(jongjin, 2011, 950)
            )

            fun ex1() {
                transactions.stream()
                    .filter { it.year == 2011 }
                    .sorted(Comparator.comparingInt(Transaction::value))
                    .collect(toList())
            }

            fun ex2() {
                transactions.stream()
                    .map { it.trader.city }
                    .distinct()
                    .collect(toList())
            }

            fun ex3() {
                transactions.stream()
                    .map { it.trader }
                    .filter { it.city == "Pangyo" }
                    .sorted(Comparator.comparing(Trader::name))
                    .collect(toList())
            }

            fun ex4() {
                transactions.stream()
                    .map { it.trader.name }
                    .distinct()
                    .sorted()
                    .collect(toList())
            }

            fun ex5() {
                transactions.stream()
                    .anyMatch { it.trader.city == "Pyeongchon" }
            }

            fun ex6() {
                transactions.stream()
                    .filter { it.trader.city == "Pyeongchon" }
                    .map { it.trader }
                    .forEach { println(it) }
            }

            fun ex7() {
                val reduce: Int? = transactions.stream()
                    .map { it.value }
                    .reduce { t1, t2 -> max(t1, t2) }
                    .orElse(null)
            }

            fun ex9() {
                transactions.stream()
                    .max(compareBy(Transaction::value))
            }
        }

        fun numberStream() {
            menu
                .stream()
                .map { it.calories }
                .reduce(0) { a, b -> a + b }

            menu
                .stream()
                .mapToInt { it.calories }
                .sum()

            val intStream: IntStream = menu
                .stream()
                .mapToInt { it.calories }

            val streamInteger: Stream<Int> = intStream.boxed()
        }

        fun numberOptional() {
            val max: OptionalInt = menu
                .stream()
                .mapToInt { it.calories }
                .max()
            val d: Int? = max.orElseGet(null)
        }

        fun numberRange() {
            val eventNumbers = IntStream.rangeClosed(1, 100)
                .filter { n -> n % 2 == 0 }
            eventNumbers.count()
        }

        fun testStream1() {
            val pythagoreanTripleList: List<Array<Double>> = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap { a ->
                    IntStream.range(a, 100)
                        .boxed()
                        .map { b: Int ->
                            arrayOf(a.toDouble(), b.toDouble(), sqrt(a.toDouble() * a + b * b))
                        }
                        .filter { it[2] % 1.0 == 0.0 }
                }
                .collect(toList())
                .orEmpty()

            pythagoreanTripleList
                .stream()
                .limit(10)
                .forEach { println("${it[0]}, ${it[1]}, ${it[2]}") }

            val notNullStream: Stream<String> = Stream.of("hello", "world", "java", "kotlin")
            val nullableStream: Stream<String?> = Stream.of("hello", "world", "java", "kotlin", null)
                .flatMap { Stream.ofNullable(it) }

            val numberArray = arrayOf("hello", "world", "java", "kotlin")
            val sum = Arrays.stream(numberArray)
                .mapToInt() { it.length }
                .sum()
        }

        fun test() {
            Stream.iterate(0) { n -> n + 2 }
                .limit(10)
                .forEach { println(it) }
        }

        fun iterate2() {
            IntStream.iterate(0, { n -> n < 100 }, { n -> n + 4 })
                .forEach { println(it) }

            IntStream.iterate(0) { n -> n + 4 }
                .filter { n -> n < 100 }
                .forEach { println(it) }
        }

        fun generate1() {
            Stream.generate(void)
                .limit(10)
                .forEach { println(it) }
        }

        fun generate2() {
            Stream.generate(Math::random)
                .limit(10)
                .forEach { println(it) }
        }

        fun generate3() {
            var i = 0
            Stream.generate { i++ }
                .limit(10)
                .forEach { println(it) }
        }
    }

    data class Transaction(
        val currency: Currency,
    )

    data class Currency(
        val name: String
    )

    fun streamData1() {
        val transactionsByCurrencies: MutableMap<Currency, MutableList<Transaction>> =
            mutableMapOf()
        val transactions = listOf(
            Transaction(Currency("USD")), Transaction(Currency("KRW")), Transaction(Currency("USD"))
        )

        for (transaction: Transaction in transactions) {
            val currency = transaction.currency
            var transactionsForCurrency = transactionsByCurrencies[currency]

            if (transactionsForCurrency == null) {
                transactionsForCurrency = mutableListOf()
            }

            transactionsForCurrency.add(transaction)
            transactionsByCurrencies[currency] = transactionsForCurrency
        }

        println("transactionsByCurrencies: $transactionsByCurrencies")
    }

    fun streamData2() {
        val transactions = listOf(
            Transaction(Currency("USD")), Transaction(Currency("KRW")), Transaction(Currency("USD"))
        )

        val transactionsByCurrencies1: MutableMap<Currency, MutableList<Transaction>> =
            transactions.stream().collect(Collectors.groupingBy { it.currency })
        val transactionsByCurrencies2: MutableMap<Currency, MutableList<Transaction>> =
            transactions.stream().collect(Collectors.groupingBy(Transaction::currency))

        println("transactionsByCurrencies1: $transactionsByCurrencies1")
        println("transactionsByCurrencies2: $transactionsByCurrencies2")
    }

    fun streamData3() {
        val count1 = menu.stream().collect(counting())
        val count2 = menu.stream().count()
        val count3 = menu.count()
    }

    fun streamData4() {
        val max1: Optional<Dish> = menu.stream().collect(maxBy(comparing { it.calories }))
        val max2: Optional<Dish> = menu.stream().max(comparing { it.calories })
        val max3: Dish = menu.maxBy { it.calories }
        val max4: Dish? = menu.maxByOrNull { it.calories }

        val joining1 = menu.stream().map { it.name }.collect(joining(","))
        val joining2 = menu.joinToString { "${it.name}," }
    }

    fun stream7() {
        val vegetarianMap: MutableMap<Boolean, MutableList<Dish>> =
            menu.stream().collect(partitioningBy(Dish::isVegetarian))

        menu.stream()
            .collect(
                partitioningBy(
                    Dish::isVegetarian,
                    groupingBy(Dish::name)
                )
            )

        fun <T> supplier(): Supplier<List<T>> {
            return Supplier { mutableListOf<T>() }
        }

        fun <T> accumulator(): BiConsumer<MutableList<T>, T> {
            return BiConsumer { list, item -> list.add(item) }
        }

        fun <T> finisher(): Function<MutableList<T>, MutableList<T>>? {
            return Function.identity()
        }

        fun <T> combiner(): BinaryOperator<MutableList<T>> {
            return BinaryOperator { list1, list2 -> list1.apply { addAll(list2) } }
        }

        fun characteristics(): Set<Collector.Characteristics> {
            return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH))
        }
    }
}

fun main() {
    Part2().streamData1()
    Part2().streamData2()
}
