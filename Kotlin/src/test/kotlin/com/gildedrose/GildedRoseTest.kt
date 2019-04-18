package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class GildedRoseTest {

    @Test fun sellinDecreases_normalItems() {
        val items = arrayOf(Item("foo", 10, 0))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(9, app.items[0].sellIn)
    }

    @Test
    fun qualityDecreases_normalItems() {
        val items = arrayOf(Item("foo", 10, 20))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(19, app.items[0].quality)
    }

    @Test
    fun qualityDecreasesTwiceAsFastAfterSellInDatePassed_normalItems() {
        val items = arrayOf(Item("foo", 0, 10))
        val app = GildedRose(items)

        app.updateQuality()

        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun qualityIsNeverNegative() {
        val items = arrayOf(Item("foo", 10, 0))
        val app = GildedRose(items)

        app.updateQuality()


        assertEquals(0, app.items[0].quality)
    }
}


