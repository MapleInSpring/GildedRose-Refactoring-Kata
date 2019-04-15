import { expect } from 'chai';
import { TypedItem, GildedRose, ItemFactory } from '../app/gilded-rose';

const getUpdatedItem = (item: TypedItem) => {
    const gildedRose = new GildedRose([item]);

    const updatedItems = gildedRose.updateQuality();
    return updatedItems[0];
};

const shouldIncreaseInQuality = (itemName: string) => {
    it('should increase in quality as it gets older', () => {
        const item = ItemFactory.createItem(itemName, 20, 25);

        const updatedItem = getUpdatedItem(item);

        expect(updatedItem.quality).to.be.greaterThan(25);
    });

    it('should never be more than 50', () => {
        const item = ItemFactory.createItem(itemName, 0, 50);

        const updatedItem = getUpdatedItem(item);

        expect(updatedItem.quality).to.be.lessThan(51);
    });

    it('should increase by 2 when sellIn in between 10 to 6', () => {
        const sellInRange = [10, 9, 8, 7, 6];
        for(let sellIn of sellInRange) {
            const item = ItemFactory.createItem(itemName, sellIn, 10);

            const updatedItem = getUpdatedItem(item);

            expect(updatedItem.quality).to.equal(12, 'sellIn is ' + sellIn);
        }
    });

    it('should increase by 3 when sellIn in between 5 to 1', () => {
        const sellInRange = [5, 4, 3, 2, 1];
        for(let sellIn of sellInRange) {
            const item = ItemFactory.createItem(itemName, sellIn, 10);

            const updatedItem = getUpdatedItem(item);

            expect(updatedItem.quality).to.equal(13, 'sellIn is ' + sellIn);
        }
    });

    it('should be 0 after concert', () => {
        const concertedItem = ItemFactory.createItem(itemName, 0, 20);

        const updatedItem = getUpdatedItem(concertedItem);

        expect(updatedItem.quality).to.equal(0);
    })
};

describe('Gilded Rose', function () {

    it('should foo', function () {
        const gildedRose = new GildedRose([ItemFactory.createItem('foo', 0, 0)]);
        const items = gildedRose.updateQuality();
        expect(items[0].name).to.equal('foo');
    });

    describe('normal items', () => {
        describe('quality', () => {
            it('should never be negative', () => {
                const item = ItemFactory.createItem('foo', 0, 0);
                
                const updatedItem = getUpdatedItem(item);

                expect(updatedItem.quality).to.be.above(-1);
            });

            it('should degrade twice as fast once passed sell by date', () => {
                const item = ItemFactory.createItem('foo', 0, 5);

                const updatedItem = getUpdatedItem(item);

                expect(updatedItem.quality).to.equal(3);
            })
        });

        describe('sellIn', () => {
            it('should decrease for every update', () => {
                const item = ItemFactory.createItem('foo', 20, 20);

                const updatedItem = getUpdatedItem(item);
    
                expect(updatedItem.sellIn).to.equal(19);
            })
        })
    });

    describe('special items', () => {
        describe('Aged Brie', () => {
            const agedBrieName = 'Aged Brie';
            
            shouldIncreaseInQuality(agedBrieName);
        });

        describe('Backstage passes', () => {
            const backstageName = 'Backstage passes to a TAFKAL80ETC concert';

            shouldIncreaseInQuality(backstageName);
        })

        describe('Sulfuras', () => {
            const sulfurasName = 'Sulfuras, Hand of Ragnaros';

            it('should never decrease in quality', () => {
                const originalQuality = 25;
                const item = ItemFactory.createItem(sulfurasName, 20, originalQuality);

                const updatedItem = getUpdatedItem(item);

                expect(updatedItem.quality).to.equal(originalQuality);
            });

            it('should never be sold', () => {
                const originalSellIn = 20;
                const item = ItemFactory.createItem(sulfurasName, originalSellIn, 25);

                const updatedItem = getUpdatedItem(item);

                expect(updatedItem.sellIn).to.equal(originalSellIn);
            })
        })
    })
});
