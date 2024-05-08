package co.com.randomexec.arrays;

public class IntArray1 {

    public static int maximumDistance(int[] array) {
        int max = -99;
        int lastMaxIndex = 0, firstMaxIndex = 0;
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] >= max) {
                    max = array[i];
                    lastMaxIndex = i; //4
                }
            }

            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] == max) {
                    firstMaxIndex = i; //1
                    break;
                }
            }
        }
        return lastMaxIndex - firstMaxIndex;
    }

    public static void main(String[] args) {
        {
            int[] array = null;
            System.out.println("result = " + maximumDistance(array));
        }
        {
            int[] array = new int[] {};
            System.out.println("result = " + maximumDistance(array));
        }
        {
            int[] array = new int[] { 4, 100, 3, 4 };
            System.out.println("result = " + maximumDistance(array));
        }
        {
            int[] array = new int[] { 5, 50, 50, 4, 5 };
            System.out.println("result = " + maximumDistance(array));
        }
        {
            int[] array = new int[] { 5, 350, 350, 4, 350 };
            System.out.println("result = " + maximumDistance(array));
        }
        {
            int[] array = new int[] { 10, 10, 10, 10, 10 };
            System.out.println("result = " + maximumDistance(array));
        }
    }

}
