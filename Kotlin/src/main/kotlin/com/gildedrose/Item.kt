package com.gildedrose

data class Item(var name: String, var sellIn: Int, var quality: Int, var type: ItemType) {
    fun isIncreasing(): Boolean {
        return type === ItemType.Increasing
    }

    fun isConstant(): Boolean {
        return type === ItemType.Constant
    }

    fun isDecreasing(): Boolean {
        return type === ItemType.Decreasing
    }
}

enum class ItemType {
    Increasing,
    Decreasing,
    Constant
}
