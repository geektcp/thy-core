package com.geektcp.common.core.sort;

/**
 * @author geektcp on 2019/9/23.
 */
public class MergeSortUp<T extends Comparable<T>> extends MergeSort<T> {

    @Override
    public void sort(T[] numberArray) {

        int N = numberArray.length;
        aux = (T[]) new Comparable[N];

        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(numberArray, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }
}
