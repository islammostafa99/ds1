package eg.edu.alexu.csd.filestructure.sort;
import java.util.Scanner;
import java.util.Vector;
public class Controller{
    Scanner input=new Scanner(System.in);
    public static Vector<Object> arr=new Vector<Object>();
    private int n;
    private int i;
    public Controller(){
        i=0;
        n=input.nextInt();
        while(n!=-1){
            arr.add(i++,n);
            n=input.nextInt();
        }
    }
}