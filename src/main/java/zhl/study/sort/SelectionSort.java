package zhl.study.sort;

/**
 * 选择排序
 */
public class SelectionSort extends SortTemplate {

    public SelectionSort(Comparable[] target) {
        super(target);
    }

    @Override
    public boolean sort() {

        int length = target.length;

        for (int i = 0; i < length; i++) {
            int min = i;
            //寻找最小量
            for (int j = i + 1; j < length; j++) {
                if (less(target[j], target[min])) {
                    min = j;
                }
            }

            //交换 最小和当前数据
            exchange(i, min);
        }
        return false;
    }

}
