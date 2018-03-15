package com;

public final class MemoryHandling {

    public static void writeToMem(byte addyHigh, byte addyLow, byte value) {

        int chipNumber = addyHigh >>> 4;
        int address = addyHigh + addyLow;

        address = address & 0x0FFF;

        Main.RAM[chipNumber][address] = value;

        //System.out.printf("\nwritten to ram: %02X", Main.RAM[chipNumber][address]);

    }

    public static void readFromMem(byte addyHigh, byte addyLow) {

        int chipNumber = addyHigh >>> 4;
        int address = addyHigh + addyLow;

        address = address & 0x0FFF;

        Main.memRead = Main.RAM[chipNumber][address];

    }
}
