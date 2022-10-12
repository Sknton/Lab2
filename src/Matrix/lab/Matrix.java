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


    public Matrix(Matrix matr){
        this.row = matr.row;
        this.column = matr.column;
        this.matr = new double[row][column];
        for (int i = 0; i<matr.row; i++){
            for (int j=0; j<matr.column; j++){
                this.matr[i][j]=matr.getMatr()[i][j];
            }
        }
    }


    private double[][] getMatr() {
        return matr;
    }

    public void setMatr(){
        Scanner in = new Scanner(System.in);
        for (int i = 0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                getMatr()[i][j]=in.nextDouble();
            }
        }
    }

    public void printMatr(){
        for (int i=0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                System.out.print(Double.toString(getMatr()[i][j])+"     ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    public static double round(double value) {
        double scale = Math.pow(10, 2);
        double result = Math.ceil(value * scale) / scale;
        return result;
    }

    public void randomFillMatr(){
        for (int i = 0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                double start = -10, end = 10;
                double random = new Random().nextDouble();
                getMatr()[i][j]= round(start + (random * (end - start)));
            }
        }
    }

    public void getElem(int row, int column){
        System.out.println(getMatr()[row-1][column-1]+"\n");
    }

    public void getRow(int row){
        for (int i =0; i<getMatr()[row-1].length; i++){
            System.out.print(getMatr()[row-1][i]+"\t");
        }
        System.out.println("\n");
    }

    public void getColumn(int column) {
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

    public int[] getSize(){
        int[] size = {row, column};
        return size;
    }

    public void setElem(int row, int column, double val){
        getMatr()[row-1][column-1]=val;
    }

    public static Matrix getColumnMatr(int row){
        Matrix column_mtr = new Matrix(row, 1);
        column_mtr.randomFillMatr();
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
                    un_matr[index[j]][k] -= round(Mt.matr[index[j]][i]*un_matr[index[i]][k]);
        for (int i=0; i<row; ++i)
        {
            invert_mt.matr[row-1][i] = round(un_matr[index[row-1]][i]/Mt.matr[index[row-1]][row-1]);
            for (int j=row-2; j>=0; --j)
            {
                invert_mt.matr[j][i] = round(un_matr[index[j]][i]);
                for (int k=j+1; k<row; ++k)
                {
                    invert_mt.matr[j][i] -= round(Mt.matr[index[j]][k]*invert_mt.matr[k][i]);
                }
                invert_mt.matr[j][i] = round(invert_mt.matr[j][i]/Mt.matr[index[j]][j]);
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
            c[i] = round(c1);
        }
        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 = round(pi0/c[index[i]]);
                if (pi0 > pi1)
                {
                    pi1 = round(pi0);
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = round(a[index[i]][j]/a[index[j]][j]);
                a[index[i]][j] = round(pj);
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= round(pj*a[index[j]][l]);
            }
        }
    }


    public static Matrix inverted(Matrix matrix){
        int size = matrix.row;
        Matrix invertMatrix = new Matrix(size, size);
        Matrix copyMatrix = new Matrix(matrix);
        for (int i = 0; i<size; i++){
            invertMatrix.matr[i][i]=1;
        }
        for (int k =0; k<size;k++) {
            double divider = copyMatrix.matr[k][k];
            System.out.println(divider);
            for (int i = 0; i < size; i++) {
                copyMatrix.matr[k][i] = round(copyMatrix.matr[k][i] / divider);
                invertMatrix.matr[k][i] = round(invertMatrix.matr[k][i] / divider);
            }
            invertMatrix.printMatr();
            for (int i = 0; i < size; i++) {
                if (i == k){
                    continue;
                }else {
                    double multiplier = copyMatrix.matr[i][k];
                    for (int j = 0; j < size; j++) {
                        copyMatrix.matr[i][j] = copyMatrix.matr[i][j] - round(copyMatrix.matr[k][j] * multiplier);
                        invertMatrix.matr[i][j] = invertMatrix.matr[i][j] - round(invertMatrix.matr[k][j] * multiplier);
                    }
                }
            }
            invertMatrix.printMatr();
        }
        return invertMatrix;
    }
}
