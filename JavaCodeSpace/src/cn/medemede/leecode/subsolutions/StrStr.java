package cn.medemede.leecode.subsolutions;

public class StrStr {

    /**
     * 实现 strStr()
     * <p>KMP算法</p>
     * <p>https://wiki.jikexueyuan.com/project/kmp-algorithm/define.html
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        int[] next = getNextVal(needleChars);

        int i = 0;
        int j = 0;

        while (i < haystackChars.length && j < needleChars.length) {
            if (j == -1 || haystackChars[i] == needleChars[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == needleChars.length) {
            return i - needleChars.length;
        } else {
            return -1;
        }
    }

    private int[] getNextVal(char[] needleChars) {
        int[] next = new int[needleChars.length];
        next[0] = -1;
        int k = -1;
        int i = 0;
        while (i < needleChars.length - 1) {
            if (k == -1 || needleChars[i] == needleChars[k]) {
                ++i;
                ++k;
                if (needleChars[i] != needleChars[k]) {
                    next[i] = k;
                } else {
                    next[i] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

}

