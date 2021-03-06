package cn.medemede.leecode;

import java.util.LinkedList;

public class MaxSlidingWindow {

    /**
     * 单调队列：从尾部添加，被挡住的逐个删除
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] res = new int[nums.length - k + 1];
        int i = 0;
        int j = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        while (j < k) {
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            j++;
        }
        res[i] = queue.peekFirst();
        while (j < nums.length) {
            if (nums[i] == queue.peekFirst()) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < nums[j]) {
                queue.removeLast();
            }
            queue.addLast(nums[j]);
            i++;
            res[i] = queue.peek();
            j++;
        }
        return res;
    }

}

