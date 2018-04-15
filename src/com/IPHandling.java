package com;

public final class IPHandling {

    public static int getCurrentPointer(){
        int pointer = (Main.IP.outputHigh << 8) | Main.IP.outputLow;
        return pointer;
    }
}
