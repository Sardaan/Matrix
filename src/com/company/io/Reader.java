package com.company.io;

public interface Reader {
    int readSize();
    float readPrecision();
    float[][] readMatrix(int size);
}
