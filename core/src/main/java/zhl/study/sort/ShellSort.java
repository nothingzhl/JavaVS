package zhl.study.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort extends SortTemplate {
    public ShellSort(Comparable[] target) {
        super(target);
    }

    @Override
    public boolean sort() {

        int length = target.length;
        int h = 1;

        //确定h
        while (h < length / 3) {
            h = 3 * h + 1;
        }

        //排序
        while (h >= 1) {

            //插入排序法的变形
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && less(target[j], target[j - h]); j -= h) {
                    exchange(j, j - h);
                }
            }

            h = h / 3;
        }

        return false;
    }
}
