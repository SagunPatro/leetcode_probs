
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;

        int[] freq = new int[100001];
        int maxDiff = 0;
        long total = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            freq[diff]++;
            maxDiff = Math.max(maxDiff, diff);
            total += diff;
        }

        // All differences can become zero
        if (k >= total) {
            return 0;
        }

        // Reduce the largest differences in batches
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] == 0) {
                continue;
            }

            long count = freq[d];

            if (k >= count) {
                freq[d] -= (int) count;
                freq[d - 1] += (int) count;
                k -= count;

                // Process the same level again if needed
                if (freq[d] > 0) {
                    d++;
                }
            } else {
                // Reduce only k of the differences by one
                freq[d] -= (int) k;
                freq[d - 1] += (int) k;
                k = 0;
            }
        }

        long ans = 0;

        for (int d = 1; d <= maxDiff; d++) {
            ans += (long) d * d * freq[d];
        }

        return ans;
    }
}
