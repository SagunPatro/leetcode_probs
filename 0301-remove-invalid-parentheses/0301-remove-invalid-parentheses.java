class Solution {
    int minremovals = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();

        backtrack(new StringBuilder(), s, 0, 0, res);

        return res;
    }

    public boolean isValid(String s) {
        int open = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                open++;
            }
            else if(ch == ')') {
                open--;
            }
            if(open < 0) {
                return false;
            }
        }
        return open == 0;
    }

    public void backtrack(StringBuilder current, String s, int i, int removed, List<String> res) {

        if(i == s.length()) {
            if(isValid(current.toString())) {
                if(removed < minremovals) {
                    minremovals = removed;
                    res.clear();
                    res.add(current.toString());
                }
                else if(removed == minremovals) {
                    if (!res.contains(current.toString())) {
                        res.add(current.toString());
                    }
                }
            }
        return;
        }
        
        current.append(s.charAt(i));
        backtrack(current, s, i+1, removed, res);
        current.deleteCharAt(current.length() - 1);

        if(s.charAt(i) == '(' || s.charAt(i) == ')') {
            backtrack(current, s, i+1, removed+1, res);
        }
    }
}