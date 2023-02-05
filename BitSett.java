package org.example;
import org.testng.Assert;

import java.util.*;

public class BitSett {
    private int arrayBitSetSize;
    private int bitCount;

    private int arrayIndex;
    private int mask;
    int[]arrayBitSet;
    private List<Integer> index;

    BitSett(int bitCount) {
        this.bitCount = bitCount;
        if (bitCount % 32 == 0) {
            arrayBitSetSize = bitCount / 32;
        } else {
            arrayBitSetSize = (bitCount / 32) + 1;
        }
        arrayBitSet = new int[arrayBitSetSize];
        for (int i = 0; i < arrayBitSetSize; ++i) {
            arrayBitSet[i] = 0;
        }
        index = new ArrayList<Integer>();
        for(int i = 0; i < bitCount; ++i) {
            index.add(i, 0);
        }
    }


    public void set(int index) {
        mask = 1;
        arrayIndex = index / 32;
        Assert.assertTrue( index != 31 + (32 * (index / 32)));
        mask <<= index;
        arrayBitSet[arrayIndex] |= mask;
        this.index.set(index, 1);
    }
    public int getBit(int index) {
        return this.index.get(index);
    }
    public void getAllBits() {
        System.out.println(this.index);
    }

    public void reset(int index) {
        Assert.assertTrue( index != 31 + (32 * (arrayIndex / 32)));
        mask = 1;
        arrayIndex = index / 32;
        mask <<= index;
        arrayBitSet[arrayIndex] &= ~mask;
        this.index.set(index, 0);
    }
    public void resetAll() {
        for (int i = 0; i < arrayBitSetSize; ++i) {
            arrayBitSet[i] = 0;
        }
        for (int i = 0; i < bitCount; ++i) {
            this.index.set(i, 0);
        }
    }
    public void getArray() {
        for(int i = 0; i < arrayBitSetSize; ++i) {
            System.out.println(arrayBitSet[i]);
        }
    }
    public void setFromTo(int fromIndex, int toIndex) {
        mask = 1;
        assert fromIndex < toIndex;
        assert toIndex <= bitCount;
        for(int i = fromIndex; i < toIndex; ++i) {
            if(i == 31 + (32 * (i / 32))) {}
            else {
                arrayIndex = i / 32;
                mask <<= i;
                arrayBitSet[arrayIndex] |= mask;
                this.index.set(i, 1);
                mask = 1;
            }
        }
    }
    public void resetFromTo(int fromIndex, int toIndex) {
        mask = 1;
        assert fromIndex < toIndex;
        assert toIndex <= bitCount;
        for(int i = fromIndex; i < toIndex; ++i) {
            if(i == 31 + (32 * (i / 32))) {}
            else {
                arrayIndex = i / 32;
                mask <<= i;
                arrayBitSet[arrayIndex] |= mask;
                this.index.set(i, 0);
                mask = 1;
            }
        }
    }

    public static void main(String[] args) {
        BitSett bitSett = new BitSett(100);
        bitSett.setFromTo(0, 31);
        bitSett.set(31);
        bitSett.getArray();
    }
}
