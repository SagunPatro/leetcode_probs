class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();

        backtrack(nums, ans, current, 0);

        return ans;
    }

    void backtrack(int[] nums, List<List<Integer>> ans, ArrayList<Integer> current, int i) {
        if(i == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[i]);
        backtrack(nums, ans, current, i+1);
        current.remove(current.size()-1);

        backtrack(nums, ans, current, i+1);
    }
}