class Item {
    name: string;
    sellIn: number;
    quality: number;

    constructor(name, sellIn, quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }
}

enum ItemType {
    IncreasingItem,
    NormalItem,
    NoChangeItem
}

export class TypedItem extends Item {
    type: ItemType

    constructor(name, sellIn, quality, type) {
        super(name, sellIn, quality);
        this.type = type;
    }
}

export class ItemFactory {
    static createItem(name, sellIn, quality): TypedItem {
        if (name == 'Aged Brie' || name == 'Backstage passes to a TAFKAL80ETC concert') {
            return new TypedItem(name, sellIn, quality, ItemType.IncreasingItem);
        }

        if (name == 'Sulfuras, Hand of Ragnaros') {
            return new TypedItem(name, sellIn, quality, ItemType.NoChangeItem);
        }

        return new TypedItem(name, sellIn, quality, ItemType.NormalItem);
    }
}

export class GildedRose {
    items: Array<TypedItem>;

    constructor(items = [] as Array<TypedItem>) {
        this.items = items;
    }

    updateQuality() {
        for (let i = 0; i < this.items.length; i++) {
            let currentItem = this.items[i];

            switch (currentItem.type) {

                case ItemType.IncreasingItem:
                    currentItem.sellIn = currentItem.sellIn - 1;
                    const increaseDelta = this._getIncreaseDelta(currentItem.sellIn);

                    currentItem = this._increaseQuality(currentItem, increaseDelta);

                    if (currentItem.sellIn < 0) {
                        currentItem.quality = 0;
                    }
                    break;
                case ItemType.NormalItem:
                    currentItem.sellIn = currentItem.sellIn - 1;
                    const decreaseDelta = this._getDecreaseDelta(currentItem.sellIn);
                    currentItem = this._decreaseQuality(currentItem, decreaseDelta);
                    break;
                default:
                    continue;

            }
        }

        return this.items;
    }

    _increaseQuality(item: TypedItem, delta: number = 1) {
        if (item.quality + delta <= 50) {
            item.quality += delta;
        }

        return item;
    }

    _getIncreaseDelta(sellIn: number): number {
        if (sellIn < 5) {
            return 3;
        }

        if (sellIn < 10) {
            return 2;
        }

        return 1;
    }

    _decreaseQuality(item: TypedItem, delta: number = 1) {
        if (item.quality - delta >= 0) {
            item.quality -= delta;
        }

        return item;
    }

    _getDecreaseDelta(sellIn: number): number {
        if (sellIn < 0) {
            return 2;
        } else {
            return 1;
        }
    }
}
