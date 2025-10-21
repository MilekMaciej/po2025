public class batcoding {

    public String firstHalf(String str) {

        return str.substring(0,str.length()/2);
    }

    public boolean nearHundred(int n) {
        if ((Math.abs(100-n) > 10) && (Math.abs(200-n) > 10)){
            return false;
        } else {
            return true;
        }
    }
    public int intMax(int a, int b, int c) {
        if(a>b){
            if(c>a){
                return c;
            } else{
                return a;
            }
        } else if (b>c){
            return b;
        } else {
            return c;
        }
    }

    public String helloName(String name) {
        String txt1 = "Hello ";
        String txt2 = "!";
        String result = txt1.concat(name).concat(txt2);
        return result;
    }

    public int[] makeLast(int[] nums) {
        int len = nums.length*2;
        int[] newaray = new int[len];
        for(int i=0; i<(len); i++){
            int a = nums[nums.length-1];
            if(i != len-1){
                newaray[i] = 0;
            } else {
                newaray[i] = a;
            }
        }
        return newaray;
    }
}





