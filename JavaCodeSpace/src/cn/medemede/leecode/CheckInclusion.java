package cn.medemede.leecode;

public class CheckInclusion {

    /**
     * s2是否包含s1一个排列，只有小写字母
     * <p>滑动窗口</p>
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || "".equals(s1)) {
            return true;
        }
        if (s2.length() < s1.length()) {
            return false;
        }
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int[] count = new int[128];
        for (char a : s1Chars) {
            count[a]++;
        }
        int left = 0;
        int right = 0;
        for (; right < s1Chars.length; right++) {
            count[s2Chars[right]]--;
        }
        if (isSubStr(count)) {
            return true;
        }
        while (right < s2Chars.length) {
            count[s2Chars[right]]--;
            count[s2Chars[left]]++;
            left++;
            if (isSubStr(count)) {
                return true;
            }
            right++;
        }
        return false;
    }

    private boolean isSubStr(int[] count) {
        for (int i = 96; i < 123; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

}

