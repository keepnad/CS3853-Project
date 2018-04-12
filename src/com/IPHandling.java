package com;

public final class IPHandling {

    public static int getCurrentPointer(){
        int pointer = (Main.IP.outputHigh & 0x0F) * 0x100 + Main.IP.outputLow;
        return pointer;
    }
}
