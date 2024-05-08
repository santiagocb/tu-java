package co.com.randomexec.arrays;

import java.util.Arrays;

public class IntArray2 {

    public static void swapEven(int[] array) {
        if (array != null) {
            int descIndex = array.length - 1;
            for (int index = 0; index < array.length / 2; index++) {
                if (array[index] % 2 == 0 && array[descIndex] % 2 == 0) {
                    int temp = array[index];
                    array[index] = array[descIndex];
                    array[descIndex] = temp;
                }
                descIndex--;
            }
        }
    }

    public static void main(String[] args) {
        {
            int[] array = null;
            swapEven(array);
            System.out.println(Arrays.toString(array));
        }
        {
            int[] array = new int[] {};
            swapEven(array);
            System.out.println(Arrays.toString(array));
        }
        {
            int[] array = new int[] { 10, 5, 3, 4 };
            swapEven(array);
            System.out.println(Arrays.toString(array));
        }
        {
            int[] array = new int[] { 100, 2, 3, 4, 5 };
            swapEven(array);
            System.out.println(Arrays.toString(array));
        }
        {
            int[] array = new int[] { 100, 2, 3, 45, 33, 8, 4, 54 };
            swapEven(array);
            System.out.println(Arrays.toString(array));
        }
    }

}
