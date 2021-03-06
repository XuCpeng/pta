package cn.medemede.leecode;

import java.util.LinkedList;

public class RemoveDuplicateLetters {

    /**
     * 去除重复字母，且保持其他字母顺序不变，且字典序最小。
     * <p>基本思想：从前到后找到s[i]>s[i+1]，将s[i]删除；
     * <p>1. 若栈中不存在当前元素，则将比自己大且后面还有的元素弹出，入栈；
     * <p>2. 若栈中存在此元素，则什么也不做
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] flag = new int[128];
        boolean[] inStack = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            flag[s.charAt(i)]++;
        }
        LinkedList<Character> s1 = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            while (!s1.isEmpty() && s1.peek() > x && flag[s1.peek()] > 1) {
                inStack[s1.peek()] = false;
                s1.pop();
            }
            if (!inStack[x]) {
                s1.push(x);
                inStack[x] = true;
            }

        }

        while (!s1.isEmpty()) {
            sb.append(s1.pop());
        }
        return sb.reverse().toString();
    }

}

