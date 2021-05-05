package math;

/**
 * @author 胖虎
 * @date 2021/3/11 下午 6:44
 */
public class Demo37 {
    public static boolean compaerNum(int[] ints,int i){
        if(i == 0){
            return false;
        }
        if(i==ints.length){
            return false;
        }
        if(ints[i]>ints[i-1]){
            return false;
        }
        if(ints[i]>ints[i+1]){
            return false;
        }
        if(ints[i]==ints[i+1]){
            return compaerNum(ints,i+1);
        }
        return true;
    }
    public static int demo(int[]ints){
        int lengthMax = 0;
        int rightMax = 0;
        int lengthIndes = 0 ;
        int rightInds = 0 ;
        int sum = 0;
        for(int i=0,j=ints.length;i<j;i++){
            if(compaerNum(ints,i)){
                rightMax = ints[i];
                for (int x = i+1;x<j;x++){
                    if(rightMax == ints[x]||rightMax<ints[x]){
                        rightMax = ints[x];
                        rightInds = x;
                    }else{
                        break;
                    }
                }
                //木桶边板子最低位
                int max = lengthMax>rightMax?rightMax:lengthMax;
                for(int length = lengthIndes+1;length<rightInds;length++){
                    if(max-ints[length] >0) {
                        sum = sum + max - ints[length];
                    }
                }
                 lengthMax = 0;
                 rightMax = 0;
                 lengthIndes = 0 ;
                 rightInds = 0 ;
            }else{
                if(lengthMax<=ints[i]) {
                    lengthMax = ints[i];
                    lengthIndes = i;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int [] inds = {9,2,0,4,3,4,9,1,1,5};
        System.out.println(demo(inds));
    }
}
