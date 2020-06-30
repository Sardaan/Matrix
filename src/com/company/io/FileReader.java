package com.company.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;


public class FileReader implements  Reader{
    String fileName = "/Users/a111/IdeaProjects/VMath/src/matrix.txt";
    PrintStream out = System.out;

    public static float[] readCoefficient(String line, int size){
        String[] args = line.split("\\s+");
        float[] coefficients = new float[size + 1];

        for (int i = 0; i< size+1; i++ ){
            float number = Float.parseFloat(args[i].replace(',','.'));
            coefficients[i] = number;
        }
        return coefficients;

    }

    @Override
    public int readSize() {
        int size = 0;
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            size = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public float readPrecision() {
        Scanner sc = new Scanner(System.in);
        out.println("Введите точность решения: ");
        return Float.parseFloat(sc.next().replace(',','.'));
    }

    @Override
    public float[][] readMatrix(int size) {
        float[][] matrix = new float[size][size+1];
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            br.readLine();
            for (int i = 0; i < size; i++) {
                matrix[i] = readCoefficient(br.readLine(), size);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }
}
