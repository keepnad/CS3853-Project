package com.muxes;

public abstract class Mux {

    int numInputs;
    int ID;
    public byte inputs[];
    public byte output;
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

    public abstract void selectInput(int n);
}
