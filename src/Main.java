import java.util.*;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input a four or three digit number, using at least two different digits: ");
        int num = scan.nextInt();

        if (countDigits(num) == 3) num = num * 10;      // adding an extra zero for 3-digit numbers
        if (Integer.toString(num).length() != 4) {
            System.out.println("Error: number is the wrong length.");
            System.exit(0);
        }
        if (checkSameDigits(num)) {
            System.out.println("Error: number has all the same digits.");
            System.exit(0);
        }

        System.out.println("\n" + kaprekarsConstant(num));
    }

    private static int kaprekarsConstant(int num) {
        int[] digits = new int[4];
        int cnt = 0;
        int ascending, descending;

        while (num != 6174) {
            cnt++;

            // splitting num into digits
            String[] temp = Integer.toString(num).split("");
            for (int i = 0; i < 4; i++) digits[i] = Integer.parseInt(temp[i]);

            // sorting the two numbers in ascending and descending order
            ascending = sortAscending(digits);
            descending = sortDescending(digits);

            // subtracting based on which is the bigger number
            if (ascending > descending) num = ascending - descending;
            else num = descending - ascending;

            // if the subtracted number is less than 1000, an extra zero is added
            if (num < 1000) num = num * 10;
        }

        return cnt;
    }

    private static boolean checkSameDigits(int num) {
        String temp = Integer.toString(num);

        for (int i = 1; i < temp.length(); i++) {
            if (temp.charAt(i) != temp.charAt(0)) return false;
        }

        return true;
    }

    private static int countDigits(int num) {
        String snum = Integer.toString(num);
        return snum.length();
    }

    private static int arrToInt(int[] arr) {
        String s = "";

        for (int i = 0; i < arr.length; i++) s += arr[i] + "";

        return Integer.parseInt(s);
    }

    private static int sortAscending(int[] arr) {
        Arrays.sort(arr);

        return arrToInt(arr);
    }

    private static int sortDescending(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if (arr[j] < arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        return arrToInt(arr);
    }
}