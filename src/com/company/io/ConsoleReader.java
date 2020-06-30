package com.company.io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleReader implements Reader{

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
    public float[][] readMatrix(int size){
        float[][] matrix = new float[size][size+1];
        out.println("Введите данные");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < size; i++) {
                matrix[i] = FileReader.readCoefficient(br.readLine(), size);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }



}
