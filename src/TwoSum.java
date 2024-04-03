import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {


        //int[] nums = {2,7,11,15};
        //int target = 9;

        int[] nums = {3,4,2};
        int target = 6;

        //int[] nums = {3,3};
        //int target = 6;
        int resultado[] = twoSum(nums, target);
        System.out.println(resultado[1]);
        System.out.println(resultado[2]);
    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> somas = new HashMap<>();

        for(int i=0; i < nums.length; i++){
            int diferenca = target - nums[i];

            if(somas.containsKey(diferenca)){
                return new int[] {somas.get(diferenca), i};
            }
            somas.put(nums[i], i);
        }
        return null;
    }
}

