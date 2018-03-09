package com;

public abstract class mux {

    int numInputs;
    int ID;
    byte inputs[];
    byte output;
    char aOrB = '\0';

    mux(int n, int U_number) {
        this.numInputs = n;
        this.ID = U_number;
        inputs = new byte[numInputs];
    }

    mux(int n, int U_number, char aOrB) {
        this.numInputs = n;
        this.ID = U_number;
        inputs = new byte[numInputs];
        this.aOrB = aOrB;
    }

    abstract void selectInput(int n);
}
