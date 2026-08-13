class Solution {
    public String removeOuterParentheses(String s) {

        int count = 0;
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                count++;

                // Don't add the outermost '('
                if (count > 1) {
                    ans.append('(');
                }

            } else {
                // ')' 
                count--;

                // Don't add the outermost ')'
                if (count > 0) {
                    ans.append(')');
                }
            }
        }

        return ans.toString();
    }
}