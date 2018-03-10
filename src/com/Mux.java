package com;

public abstract class Mux {

    int numInputs;
    int ID;
    byte inputs[];
    byte output;
    char aOrB = '\0';

    Mux(int n, int U_number) {
        this.numInputs = n;
        this.ID = U_number;
        inputs = new byte[numInputs];
    }

    Mux(int n, int U_number, char aOrB) {
        this.numInputs = n;
        this.ID = U_number;
        inputs = new byte[numInputs];
        this.aOrB = aOrB;
    }

    abstract void selectInput(int n);
}
