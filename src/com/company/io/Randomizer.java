package com.company.io;

import java.io.PrintStream;
import java.util.Scanner;

public class Randomizer implements Reader{
    Scanner sc = new Scanner(System.in);
    PrintStream out = System.out;

    @Override
    public int readSize(){
        out.println("Введите размерность матрицы: ");
        return sc.nextInt();
    }
    @Override
    public float readPrecision(){
        out.println("Введите точность решения: ");
        return Float.parseFloat(sc.next().replace(',','.'));
    }

    @Override
    public float[][] readMatrix(int size) {

        float[][] matrix = new float[size][size+1];
        out.println("Матрица: ");
        if(size > 1) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size + 1; j++) {
                    if (i == j)
                        matrix[i][j] = (float) Math.round((Math.random() + 1) * 200);
                    else
                        matrix[i][j] = (float) Math.round(Math.random() * 10);
                    out.format("%-8.0f", matrix[i][j]);
                }
                out.println();
            }
        }

        return matrix;
    }
}
