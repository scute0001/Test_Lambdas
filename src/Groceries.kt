package com.scute.higherOrder

data class Grocery(val name: String,
                    val category: String,
                    val unit: String,
                    val unitPrice: Double,
                    val quantity: Int)

data class Pizza(val name: String,
                 val PricePerSlice: Double,
                 val quantity: Int)

fun main(args: Array<String>){
    val groceries = listOf(
        Grocery("Tomatoes", "Vegetable", "lb", 3.0, 3),
        Grocery("Mushrooms", "Vegetable", "lb", 4.0, 1),
        Grocery("Bagels", "Bakery", "Pack", 1.5, 2),
        Grocery("Olive oil", "Pantry", "Bottle", 6.0, 1),
        Grocery("Ice cream", "Frozen", "Pack", 3.0, 2))

//  higher-order of maxBy / filter / filterNot / map / sumByDouble / minBy
    val  highestUnitPrice = groceries.maxBy { item:Grocery -> item.unitPrice}
//    val  highestUnitPrice = groceries.maxBy { it.unitPrice * 5 }
    println("highestUnitPrice: $highestUnitPrice")
    val  lowestQuantity = groceries.minBy { it.quantity }
    println("lowestQuantity: $lowestQuantity")

    val sumQuantity = groceries.sumBy { it.quantity }
    println("sumQuantity: $sumQuantity")
    val totalPrice = groceries.sumByDouble { it.unitPrice * it.quantity }
    println("totalPrice: $totalPrice")

    val vegetables = groceries.filter { it.category == "Vegetable" }
    println("vegetables: $vegetables")
    val notFrozen = groceries.filterNot { it.category == "Frozen" }
    println("notFrozen: $notFrozen")

    val groceryNames = groceries.map { it.name }
    println("groceryNames: $groceryNames")
    val halfUnitPrice = groceries.map { it.unitPrice * 0.5 }
    println("halfUnitPrice: $halfUnitPrice")

    val newPrices = groceries.filter { it.unitPrice > 3.0 }
        .map { it.unitPrice * 2 }
    println("newPrice: $newPrices")

    println("Grocery names: ")
    groceries.forEach { println(it.name) }

    println("Grocery with unitPrice > 3.0: ")
    groceries.filter { it.unitPrice > 3.0 }
        .forEach { println(it.name) }

    var itemNames = ""
    groceries.forEach ({ itemNames += "${it.name} " })
    println("itemNames: $itemNames")


    //  higher-order of forEach & groupBy & fold
    groceries.groupBy { it.category }.forEach {
        println(it.key)
        it.value.forEach { println("    ${it.name}") }
    }

    val ints = listOf(1, 2, 3)
    val sumOfInts = ints.fold(0) {runningSum, item -> runningSum + item}
    println("sumOfInts: $sumOfInts")

    val productOfInts = ints.fold(1, {runningProduct, item -> runningProduct * item})
    println("productOfInts: $productOfInts")

    val names = groceries.fold("") {string, item -> string + " ${item.name}"}
    println("names: $names")

    val changeFrom50 = groceries.fold(50.0) {change, item -> change - item.unitPrice * item.quantity}
    println("changeFrom50: $changeFrom50")
}

fun pizzaHot(){
    val ints = listOf(1, 2, 3, 4, 5)
    val pizzas = listOf(
        Pizza("Sunny Chicken", 4.5, 4),
        Pizza("Goat and Nut", 4.0, 1),
        Pizza("Tropical", 3.0, 2),
        Pizza("The Garden", 3.5, 3)
    )

    val minInt = ints.minBy { it }
    val minInt2 = ints.minBy ({ int: Int -> int })
    val sumInts = ints.sum()
    val sumInts2 = ints.sumBy { it }
    val sumInts3 = ints.sumByDouble { it.toDouble() }
    val sumInts4 = ints.sumByDouble { int: Int -> int.toDouble() }

//    val lowPrice = pizzas.min()
    val lowPrice2 = pizzas.minBy { it.PricePerSlice }
    val highQuantity = pizzas.maxBy { p: Pizza -> p.quantity }
    val highQuantity2 = pizzas.maxBy { it.quantity }
//    val totalPrice = pizzas.sumBy { it.PricePerSlice * it.quantity }
    val totalPrice2 = pizzas.sumByDouble { it.PricePerSlice * it.quantity }
}
