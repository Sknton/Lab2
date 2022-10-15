package Matrix.lab;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Immatrix {
    private final int row, column;
    private final double[][] matr;



    public Immatrix(){
        row = 0;
        column = 0;
        matr = new double[0][0];
    }

    public Immatrix(int row, int colum){
        this.row = row;
        this.column = colum;
        matr = new double[row][column];
        for (int i = 0; i<getMatr().length; i++){
            for (int j=0; j<getMatr()[i].length; j++){
                double start = -10, end = 10;
                double random = new Random().nextDouble();
                getMatr()[i][j]= round(start + (random * (end - start)));
            }
        }
    }


    public Immatrix(Immatrix matr2){
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())return false;
        Immatrix matrix = (Immatrix) o;
        if (row != matrix.row || column != matrix.column)return false;
        for (int i=0; i<row; i++){
            if (!Arrays.equals(matr[i], matrix.matr[i]))return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(row, column);
        System.out.println(result);
        for (int i=0; i<row; i++)
            result = 31 * result + Arrays.hashCode(matr[i]);
        return result;
    }

    public int[] getSize(){
        int[] size = {row, column};
        return size;
    }

}
