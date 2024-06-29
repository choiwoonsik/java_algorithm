package modernJavaInAction

import modernJavaInAction.Color.GREEN
import modernJavaInAction.Color.RED
import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.IntPredicate
import java.util.function.Predicate

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

fun main() {
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