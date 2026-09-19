class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if (nums.size() == 0) {
            return 0;
        }
        int count = 1;
        int i =0; 
        int j = 1;
        while(j<nums.size()) {
            if(nums[j] == nums[j-1]) {
                j++;
            }
            else {
                nums[i+1] = nums[j];
                i++;
                j++;
                count++;
            }
        }
        return count;
    }
};