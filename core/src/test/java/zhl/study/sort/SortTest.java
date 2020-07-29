package zhl.study.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SortTest {

    private Integer a[] = new Integer[]{3, 5, 1, 6, 3, 7, 8, 10};



    private void sort(SortTemplate sortTemplate) {
        sortTemplate.sort();
        sortTemplate.show();
        assertTrue(sortTemplate.isSorted());
    }

    @Test
    void testSelectionSort() {
        sort(new SelectionSort(a));
    }

    @Test
    void testInsertionSort() {
        sort(new InsertionSort(a));
    }

    @Test
    void testShellSort() {
        sort(new ShellSort(a));
    }
}