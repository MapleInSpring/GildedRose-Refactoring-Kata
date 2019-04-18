package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {

            val item = items[i]

            if (item.isDecreasing()) {
                decreaseQuanlity(item)
            } else if(item.isIncreasing()) {
                handleIncreasingItems(i)
            }

            if (item.sellIn < 0) {
                handleExpiringItems(item)
            }
        }
    }

    private fun decreaseQuanlity(item: Item) {
        if (item.quality > 0) {
            if (!item.isConstant()) {
                item.quality = item.quality - 1
            }
        }
        item.sellIn = item.sellIn - 1
    }

    private fun handleExpiringItems(item: Item) {
        if (item.isDecreasing()) {
            decreaseQuanlity(item)
        } else {
            item.quality = 0
        }
    }

    private fun handleIncreasingItems(i: Int) {

        if (items[i].quality < 50) {

            items[i].quality = items[i].quality + 1

            handleLessThan10Days(i)

            handleLessThan6Days(i)
        }

        items[i].sellIn = items[i].sellIn - 1
    }

    private fun handleLessThan6Days(i: Int) {
        if (items[i].sellIn < 6) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1
            }
        }
    }

    private fun handleLessThan10Days(i: Int) {
        if (items[i].sellIn < 11) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1
            }
        }
    }


}

