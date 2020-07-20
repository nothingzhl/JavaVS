package zhl.study.sort;

/**
 * 插入排序
 */
public class InsertionSort extends SortTemplate {
    public InsertionSort(Comparable[] target) {
        super(target);
    }

    @Override
    public boolean sort() {

        int length = target.length;

        for (int i = 0; i < length; i++) {

            //对j之前的数据进行排序
            for (int j = i; j > 0 && less(target[j], target[j - 1]); j--) {
                exchange(j, j - 1);
            }
        }

        return false;
    }
}
