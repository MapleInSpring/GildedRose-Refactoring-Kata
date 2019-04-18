package com.gildedrose

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be greater or equal to`
import org.junit.Test

class GildedRoseTest {

    @Test
    fun sellinDecreases_normalItems() {
        val item = Item("foo", 10, 0)

        val updatedItem = getUpdatedItem(item)

        updatedItem.sellIn `should be equal to` 9
    }

    @Test
    fun qualityDecreases_normalItems() {
        val item = Item("foo", 10, 20)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 19
    }

    @Test
    fun qualityDecreasesTwiceAsFastAfterSellInDatePassed_normalItems() {
        val item = Item("foo", 0, 10)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 8
    }

    @Test
    fun qualityIsNeverNegative() {
        val item = Item("foo", 10, 0)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be greater or equal to` 0
    }

    private fun getUpdatedItem(item: Item): Item {

        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        return app.items[0]
    }
}


