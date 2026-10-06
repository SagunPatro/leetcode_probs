class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i<n; i++) {
            sum += nums[i];
        }
        if(sum % 2 != 0) {
            return false;
        } 
        int target = sum/2;
        return (subsetSumToK(n, target, nums));
    }
    public boolean subsetSumToK(int n, int k, int[] arr) {

    boolean[] prev = new boolean[k + 1];
    boolean[] cur = new boolean[k + 1];

    prev[0] = true;
    cur[0] = true;

    if(arr[0] <= k) {
        prev[arr[0]] = true;
    }

    for (int ind = 1; ind < n; ind++) {

        for (int target = 1; target <= k; target++) {

            boolean notTake = prev[target];

            boolean take = false;

            if (arr[ind] <= target) {
                take = prev[target - arr[ind]];
            }

            cur[target] = take || notTake;
        }

        // Copy cur into prev
        prev = cur.clone();
    }

    return prev[k];
}
}