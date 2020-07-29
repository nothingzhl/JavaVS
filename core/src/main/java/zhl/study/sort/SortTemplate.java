package zhl.study.sort;

import com.google.common.base.Preconditions;

import java.util.Arrays;

/**
 * 排序模板
 */
public abstract class SortTemplate implements Sort {

    protected Comparable[] target;

    public SortTemplate(Comparable[] target) {
        Preconditions.checkNotNull(target, "目标不能为空");
        this.target = target;
    }

    /**
     * 是否小于
     *
     * @param v
     * @param w
     * @return
     */
    protected boolean less(Comparable v, Comparable w) {
        Preconditions.checkNotNull(v, "不能为空");
        Preconditions.checkNotNull(w, "不能为空");
        return v.compareTo(w) < 0;
    }

    /**
     * 交换位置
     *
     * @param a
     * @param b
     * @return
     */
    protected boolean exchange(int a, int b) {
        Comparable t = target[a];
        target[a] = target[b];
        target[b] = t;
        return true;
    }


    /***
     * 展示
     */
    public void show() {
        System.out.println(getClass().getSimpleName() + ":" + Arrays.toString(target));
    }

    /**
     * 是否有序
     *
     * @return
     */
    protected boolean isSorted() {
        for (int i = 1; i < target.length; i++) {
            if (less(target[i], target[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
