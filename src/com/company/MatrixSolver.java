package com.company;
import com.company.io.Reader;

import java.io.PrintStream;

public class MatrixSolver {

    PrintStream out = System.out;

    public void solve(Reader reader){
        int size = reader.readSize();
        float precision = reader.readPrecision();
        float[][] matrix = reader.readMatrix(size);

        double det = findDet(matrix);
        if(det != 0){
            float[][] matrixWithRightDiag = getMatrixWithRightDiagonal(matrix);
            float[][] equalMatrix = getEqualMatrix(matrixWithRightDiag);
            if (equalMatrix!=null && checkNorm(equalMatrix))
                findAnswer(equalMatrix, precision);
        }else
        out.println("Нет решения");
    }

    public double findDet(float[][] matrix){
        double det = 0;
        int size = matrix.length;
        for (int i = 0; i < size; i++){
            double first = 1;
            double second = 1;
            int k = i;
            int t = size -1;
            for (int j = 0; j < size; j++){
                first *= matrix[k][j];
                second *= matrix[t][j];
                t--;
                k++;
                if(t < 0){
                    t = size - 1;
                }
                if (k > size-1){
                    k = 0;
                }
            }
            det = det + first - second;
        }
        return det;
    }

    public float[][] getMatrixWithRightDiagonal(float[][] matrix){
        int size = matrix.length;

        float[] sum = new float[size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (Math.abs(matrix[i][j])>=Math.abs(matrix[i][i])){
                    replace(matrix, j, i);
                }
            }
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                sum[i]+=Math.abs(matrix[i][j]);
            }
            if (matrix[i][i] == 0) {
                return null;
            }else if (Math.abs(matrix[i][i]) <= (sum[i]-Math.abs(matrix[i][i]))) {
                out.println("Невозможно получить преобладающую диагональ");
                break;
            }
        }
        return matrix;
    }

    public float[][] replace(float[][] matrix, int str1, int str2){

        float[] first = matrix[str1];
        float[] second = matrix[str2];
        matrix[str1] = second;
        matrix[str2] = first;
        return matrix;
    }

    public float[][] getEqualMatrix(float[][] matrix){
        if (matrix!=null){
            int size = matrix.length;
            float[][] equalMatrix = new float[size][size + 1];
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size; j++){
                    if (i == j)
                        equalMatrix[i][j] = 0;
                    else
                        equalMatrix[i][j] = - matrix[i][j]/matrix[i][i];
                }
                equalMatrix[i][size] = matrix[i][size]/matrix[i][i];
            }
            return equalMatrix;
        }
        else
            return null;

    }

    public boolean checkNorm(float[][] matrix){
        int size = matrix.length;
        float maxNorm = 0;
        float[] sum = new float[size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                sum[i] += Math.abs(matrix[i][j]);
            }
            if (sum[i]>maxNorm){
                maxNorm = sum[i];
            }
        }
        if (maxNorm>=1){
            return false;
        }
        return true;

    }


    public void findAnswer(float[][] matrix, float precision){

        int size = matrix.length;
        float[] diff = new float[size];
        float[] freeNumber = new float[size];
        float[] constant = new float[size];
        float[] X = new float[size];
        float[][] coef = new float[size][size];

        for(int i = 0; i < size; i++){
            freeNumber[i] =  matrix[i][size];
            constant[i] =  matrix[i][size];

            for(int j = 0; j < size; j++){
                coef[i][j] = matrix[i][j];
            }

        }

        float maxDiff = precision;

        while (maxDiff>=precision){
            float localMax = 0;

            for(int i = 0; i < size; i++){
                X[i]=0;
                for(int j = 0; j < size; j++){
                    X[i] += coef[i][j]*freeNumber[j];
                }
                X[i] += constant[i];
                float previousX = freeNumber[i];
                freeNumber[i] = X[i];


                diff[i] = Math.abs(X[i] - previousX);
                if (diff[i] > localMax){
                    localMax = diff[i];
                }
            }


            maxDiff = localMax;
        }
        for (int i=0; i<size; i++){
            out.println("x"+(i+1)+" "+ X[i] +": " + diff[i]);
        }
    }

}

//O(n + l*n)