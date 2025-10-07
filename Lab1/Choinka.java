import java.util.Scanner; 

public class Choinka{
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);
        
        int height = myObj.nextInt();
        
        for(int i=1; i<height; i++){

            for(int a=0; a<height-i; a++){
                System.out.print(".");
            }
            for(int m=height/2; m<height/2+i; m++){
            System.out.print("*");
            System.out.print("*");
            }
            for(int b=height+i; b<2*height; b++){
                System.out.print(".");
            }

        System.out.println();
        }
    }
}