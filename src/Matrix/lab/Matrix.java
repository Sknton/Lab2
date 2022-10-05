package Matrix.lab;


import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Matrix {
    private int row, column;
    private double[][] matr;



    public Matrix(){
        row = 0;
        column = 0;
        matr = new double[0][0];
    }

    public Matrix(int row, int colum){
        this.row = row;
        this.column = colum;
        matr = new double[row][column];
    }


    public Matrix(Matrix matr2){
        this.row = matr2.row;
        this.column = matr2.column;
        matr = new double[row][column];
        for (int i = 0; i<matr2.row; i++){
            for (int j=0; j<matr2.column; j++){
                matr[i][j]=matr2.getMatr()[i][j];
            }
        }
    }


    private double[][] getMatr() {
        return matr;
    }

    public void SetMatr(){
        Scanner in = new Scanner(System.in);
        for (int i = 0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                getMatr()[i][j]=in.nextDouble();
            }
        }
    }

    public void Out_Matr(){
        for (int i=0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                System.out.print(Double.toString(getMatr()[i][j])+"     ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public void Random_fill_Matr(){
        for (int i = 0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                double start = -10, end = 10;
                double random = new Random().nextDouble();
                getMatr()[i][j]= round(start + (random * (end - start)), 2);
            }
        }
    }

    public void Get_elem(int row, int column){
        System.out.println(getMatr()[row-1][column-1]+"\n");
    }

    public void Get_row(int row){
        for (int i =0; i<getMatr()[row-1].length; i++){
            System.out.print(getMatr()[row-1][i]+"\t");
        }
        System.out.println("\n");
    }

    public void Get_Column(int column) {
        for (int i =0; i<getMatr().length; i++){
            System.out.println(getMatr()[i][column-1]);
        }
        System.out.println("");
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setMatr(double[][] matr) {
        this.matr = matr;
    }

    @Override
    public boolean equals(Object o) {
        boolean elem;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())return false;
        Matrix matrix = (Matrix) o;
        if (row != matrix.row || column != matrix.column)return false;
        for (int i=0; i<row; i++){
            if (!Arrays.equals(matr[i], matrix.matr[i]))return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(row, column);
        for (int i=0; i<row; i++)
            result = 31 * result + Arrays.hashCode(matr[i]);
        return result;
    }

    public int[] Get_Size(){
        int[] size = {row, column};
        return size;
    }

    public void set_elem(int row, int column, double val){
        getMatr()[row-1][column-1]=val;
    }

    public static Matrix column_matr(int row){
        Matrix column_mtr = new Matrix(row, 1);
        column_mtr.Random_fill_Matr();
        return column_mtr;
    }

    public static Matrix invert(Matrix Mt)
    {
        int row = Mt.row;
        Matrix invert_mt = new Matrix(row, row);
        double[][] un_matr = new double[row][row];
        int[] index = new int[row];
        for (int i=0; i<row; ++i)
            un_matr[i][i] = 1;
        gaussian(Mt.matr, index);
        for (int i=0; i<row-1; ++i)
            for (int j=i+1; j<row; ++j)
                for (int k=0; k<row; ++k)
                    un_matr[index[j]][k] -= round(Mt.matr[index[j]][i]*un_matr[index[i]][k],2);
        for (int i=0; i<row; ++i)
        {
            invert_mt.matr[row-1][i] = round(un_matr[index[row-1]][i]/Mt.matr[index[row-1]][row-1], 2);
            for (int j=row-2; j>=0; --j)
            {
                invert_mt.matr[j][i] = round(un_matr[index[j]][i], 2);
                for (int k=j+1; k<row; ++k)
                {
                    invert_mt.matr[j][i] -= round(Mt.matr[index[j]][k]*invert_mt.matr[k][i], 2);
                }
                invert_mt.matr[j][i] = round(invert_mt.matr[j][i]/Mt.matr[index[j]][j], 2);
            }
        }
        return invert_mt;
    }


    public static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double[] c = new double[n];
        for (int i=0; i<n; ++i)
            index[i] = i;
        for (int i=0; i<n; ++i)
        {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = round(c1,2);
        }
        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 = round(pi0/c[index[i]], 2);
                if (pi0 > pi1)
                {
                    pi1 = round(pi0, 2);
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = round(a[index[i]][j]/a[index[j]][j], 2);
                a[index[i]][j] = round(pj, 2);
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= round(pj*a[index[j]][l], 2);
            }
        }
    }
}
