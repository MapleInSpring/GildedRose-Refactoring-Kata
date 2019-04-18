package com.gildedrose

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be greater or equal to`
import org.junit.Test

class GildedRoseTest {

    @Test
    fun sellinDecreases_normalItems() {
        val items = arrayOf(Item("foo", 10, 0))
        val app = GildedRose(items)

        app.updateQuality()

        app.items[0].sellIn `should be equal to` 9
    }

    @Test
    fun qualityDecreases_normalItems() {
        val items = arrayOf(Item("foo", 10, 20))
        val app = GildedRose(items)

        app.updateQuality()

        app.items[0].quality `should be equal to` 19
    }

    @Test
    fun qualityDecreasesTwiceAsFastAfterSellInDatePassed_normalItems() {
        val items = arrayOf(Item("foo", 0, 10))
        val app = GildedRose(items)

        app.updateQuality()

        app.items[0].quality `should be equal to` 8
    }

    @Test
    fun qualityIsNeverNegative() {
        val items = arrayOf(Item("foo", 10, 0))
        val app = GildedRose(items)

        app.updateQuality()


        app.items[0].quality `should be greater or equal to` 0
    }
}


