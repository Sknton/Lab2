package Matrix.lab;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Matrix Mt = new Matrix(3,3);
        Mt.setMatr();
        /*
        Mt.Out_Matr();
        Matrix Mt2 = new Matrix(Mt);
        Matrix Mt3 = new Matrix();
        Mt2.Out_Matr();
        Mt.Get_elem(1,2);
        Mt.Get_row(1);
        Mt.Get_Column(1);
        System.out.println(Mt3.Get_Size()[0] + "X" + Mt3.Get_Size()[1]+"\n");
        System.out.println(Mt.equals(Mt2));
        System.out.println(Mt.hashCode()==Mt2.hashCode());
        Mt2.set_elem(1 ,2 , 3);
        Mt.Out_Matr();
        Mt2.Out_Matr();
        System.out.println(Mt.equals(Mt2));
        System.out.println(Mt.hashCode()==Mt2.hashCode());
        */
        Mt.printMatr();
        Matrix inv = Matrix.inverted(Mt);
        inv.printMatr();

    }
}
