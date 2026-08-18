class Solution {
    public int f(int[] nums, int goal) {

        if(goal < 0) {
            return 0;
        }
        int n = nums.length;
        int left = 0;
        int sum = 0;
        int count = 0;
        for(int right = 0; right < n; right++) {
            sum += nums[right];

            while(goal < sum) {
                sum -= nums[left];
                left++;
            }

            count +=  (right-left+1);
        }
        return count;
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        return f(nums, goal) - f(nums, goal-1);
    }
}