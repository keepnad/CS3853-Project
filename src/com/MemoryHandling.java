package com;

public final class MemoryHandling {

    public static void writeToMem(byte addyHigh, byte addyLow, byte value) {

        int chipNumber = addyHigh >>> 4;
        int address = addyHigh + addyLow;

        if(chipNumber > 13 || address > 4095){
            System.err.println("Bad memory address in writeToMem, fix in instruction parser so it becomes invalid input");
            return;
        }

        address = address & 0x0FFF;

        Main.RAM[chipNumber][address] = value;

        //System.out.printf("\nwritten to ram: %02X", Main.RAM[chipNumber][address]);

    }

    public static void readFromMem(byte addyHigh, byte addyLow) {

        int chipNumber = addyHigh >>> 4;
        int address = addyHigh + addyLow;

        if(chipNumber > 13 || address > 4095){
            System.err.println("Bad memory address in readFromMem, fix in instruction parser so it becomes invalid input");
            return;
        }


        address = address & 0x0FFF;

        Main.memRead = Main.RAM[chipNumber][address];

    }
}
