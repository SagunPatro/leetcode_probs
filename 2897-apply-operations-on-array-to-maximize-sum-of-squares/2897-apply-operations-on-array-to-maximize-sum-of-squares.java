
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        int[] bitCount = new int[32];

        // Count set bits at each position
        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long ans = 0;

        // Build k maximum numbers greedily
        for (int i = 0; i < k; i++) {
            long x = 0;

            for (int bit = 31; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    x |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            ans = (ans + x * x) % MOD;
        }

        return (int) ans;
    }
}
