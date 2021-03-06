package cn.medemede.leecode;

public class MoveZeroes {

    /**
     * 将0移动到末尾
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int i = removeElement(nums, 0) - 1;
        while (i < nums.length) {
            nums[i] = 0;
            i++;
        }
    }

    /**
     * 原地删除指定元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == val) {
                break;
            }
            i++;
        }
        int j = i + 1;
        while (j < nums.length) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }

}

