import java.util.Scanner;

public class BirthdayCakeCandles {

    public static int countTallestCandles(int[] candles) {
        int maxHeight = 0;
        int count = 0;

        for (int i = 0; i < candles.length; i++) {
            if (candles[i] > maxHeight) {
                maxHeight = candles[i];
                count = 1; // Reset the count
            } else if (candles[i] == maxHeight) {
                count++;
            }
        }

        return count;
    }

    public static int birthdayCakeCandlesRecursive(int[] candles){
        return birthdayCakeCandlesRecursive(candles, 0, candles.length).count;
    }

    public static MaxCountReturn birthdayCakeCandlesRecursive(int[] candles, int i, int l) {
        if (l == 1) {
            return new MaxCountReturn(1, candles[i]);
        }
        int new_l1 = l / 2;
        int new_l2 = l - new_l1;
        int new_i1 = i;
        int new_i2 = i + new_l1;
        MaxCountReturn b1 = birthdayCakeCandlesRecursive(candles, new_i1, new_l1);
        MaxCountReturn b2 = birthdayCakeCandlesRecursive(candles, new_i2, new_l2);
        if (b1.max > b2.max)
            return b1;
        else if (b1.max < b2.max)
            return b2;
        else
            return new MaxCountReturn(b1.count + b2.count, b1.max);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of candles: ");
        int n = scanner.nextInt();

        int[] candles = new int[n];
        System.out.println("Enter the heights of the candles:");
        for (int i = 0; i < n; i++) {
            candles[i] = scanner.nextInt();
        }
        scanner.close();

        int nonRecursiveResult = countTallestCandles(candles);
        System.out.println("Non-recursive result: " + nonRecursiveResult);

        int recursiveResult = birthdayCakeCandlesRecursive(candles);
        System.out.println("Recursive result: " + recursiveResult);
    }
}
class MaxCountReturn {
    public int count;
    public int max;

    public MaxCountReturn(int count, int max) {
        this.count = count;
        this.max = max;
    }

}