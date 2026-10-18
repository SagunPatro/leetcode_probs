import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = sumOfDigits(nums[i]);
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        Map<Integer, Integer> targetIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            targetIndex.put(arr[i][0], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int current = i;
            int cycleSize = 0;

            while (!visited[current]) {
                visited[current] = true;
                current = targetIndex.get(nums[current]);
                cycleSize++;
            }

            if (cycleSize > 1) {
                swaps += cycleSize - 1;
            }
        }

        return swaps;
    }

    private int sumOfDigits(int n) {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
}