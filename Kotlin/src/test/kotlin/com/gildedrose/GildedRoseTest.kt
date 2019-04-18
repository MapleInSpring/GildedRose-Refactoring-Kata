package com.gildedrose

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be greater or equal to`
import org.amshove.kluent.`should be less or equal to`
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GildedRoseTest {

    @Test
    fun sellinDecreases_normalItems() {
        val item = Item("foo", 10, 0, ItemType.Decreasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.sellIn `should be equal to` 9
    }

    @Test
    fun qualityDecreases_normalItems() {
        val item = Item("foo", 10, 20, ItemType.Decreasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 19
    }

    @Test
    fun qualityDecreasesTwiceAsFastAfterSellInDatePassed_normalItems() {
        val item = Item("foo", 0, 10, ItemType.Decreasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 8
    }

    @Test
    fun qualityIsNeverNegative() {
        val item = Item("foo", 10, 0, ItemType.Decreasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be greater or equal to` 0
    }

    @Test
    fun qualityIncreases_AgedBrie() {
        val item = Item("Aged Brie", 20, 10, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 11
    }

    @Test
    fun qualityIncreases_BackstagePasses() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 20, 10, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 11
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 9, 8, 7, 6])
    fun qualityIncreasesBy2_when10DaysTo6Days(sellIn: Int) {
        val item = Item("Aged Brie", sellIn, 10, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 12
    }

    @ParameterizedTest
    @ValueSource(ints = [5, 4, 3, 2, 1])
    fun qualityIncreasesBy3_when5DaysTo1Days(sellIn: Int) {
        val item = Item("Aged Brie", sellIn, 10, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 13
    }

    @Test
    fun qualityDropsTo0_when0DaysLeft_AgedBrie() {
        val item = Item("Aged Brie", 0, 10, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 0
    }

    @Test
    fun qualityDropsTo0_when0DaysLeft_BackstagePasses() {
        val item = Item("Backstage passes to a TAFKAL80ETC concert", 0, 10, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 0
    }

    @Test
    fun qualityIsNeverMoreThan50() {
        val item = Item("Aged Brie", 10, 50, ItemType.Increasing)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be less or equal to` 50
    }

    @Test
    fun qualityRemains_Sulfuras() {
        val item = Item("Sulfuras, Hand of Ragnaros", 20, 20, ItemType.Constant)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 20
    }

    @Test
    fun qualityRemainsAfterExpired_Sulfuras() {
        val item = Item("Sulfuras, Hand of Ragnaros", 0, 20, ItemType.Constant)

        val updatedItem = getUpdatedItem(item)

        updatedItem.quality `should be equal to` 20
    }

    @Test
    fun sellInRemains_Sulfuras() {
        val item = Item("Sulfuras, Hand of Ragnaros", 20, 20, ItemType.Constant)

        val updatedItem = getUpdatedItem(item)

        updatedItem.sellIn `should be equal to` 20
    }

    private fun getUpdatedItem(item: Item): Item {

        val app = GildedRose(arrayOf(item))
        app.updateQuality()

        return app.items[0]
    }
}


