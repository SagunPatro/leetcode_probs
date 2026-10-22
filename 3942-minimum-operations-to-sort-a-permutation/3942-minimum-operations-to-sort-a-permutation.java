class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }

        // Find the index of 0
        int idx0 = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx0 = i;
                break;
            }
        }

        // Check cyclic orientation
        boolean isInc = true;
        boolean isDec = true;

        for (int i = 0; i < n; i++) {
            if (nums[(idx0 + i) % n] != i) {
                isInc = false;
            }
            if (nums[(idx0 - i + n) % n] != i) {
                isDec = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        // If cyclically increasing
        if (isInc) {
            if (idx0 == 0) return 0;
            ans = Math.min(ans, Math.min(idx0, n - idx0 + 2));
        }

        // If cyclically decreasing
        if (isDec) {
            ans = Math.min(ans, Math.min(n - idx0, ((idx0 + 1) % n) + 1));
        }

        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }
}