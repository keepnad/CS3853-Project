package com;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * MemoryHandling class:
 * * Non-instanced, static methods for use with Memory
 */
public final class MemoryHandling {

    public static void writeToMem(byte addyHigh, byte addyLow, byte value) {

        int chipNumber = addyHigh >>> 4;
        chipNumber = chipNumber & 0xF;
        int offset = (((addyHigh & 0xFF) * 0x100) + (addyLow & 0xFF)) % 0x1000;
        offset = offset & 0xFFF;

        if (chipNumber > 13 || offset > 4095) {
            System.err.println("Bad memory value in writeToMem");
            return;
        }

        Main.Memory[chipNumber][offset] = value;

        //System.out.printf("\nwritten to ram: %02X", Main.Memory[chipNumber][offset]);

    }

    public static void readFromMem(byte addyHigh, byte addyLow) {

        int chipNumber = addyHigh >>> 4;
        chipNumber = chipNumber & 0xF;
        int offset = (((addyHigh & 0xFF) * 0x100) + (addyLow & 0xFF)) % 0x1000;
        offset = offset & 0xFFF;

        if (chipNumber > 13 || offset > 4095) {
            System.err.println("Bad memory offset in readFromMem");
            return;
        }

        Main.memRead = Main.Memory[chipNumber][offset];

    }

    public static void memDump() {
        PrintWriter writer;
        int zeroCount = 0;

        try {
            writer = new PrintWriter("memDump.txt");
        } catch (IOException e1) {
            System.out.println("\nError creating dump file.\n");
            return;
        }

        writer.printf("-----RAM STARTS HERE-----\n\n");
        writer.println("Address\t\tVal");

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 4096; j++) {
                if(Main.Memory[i][j] == (byte) 0x0){
                    zeroCount++;
                }else{
                    zeroCount = 0;
                }
                if(zeroCount < 4) {
                    writer.printf("0x%X%03X\t\t%02X\n", i, j, Main.Memory[i][j]);
                }else if(zeroCount == 4){
                    writer.println("");
                }
            }
        }

        int i = 15;
        writer.printf("-----EPROM STARTS HERE-----\n\n");
        writer.println("Address\t\tVal");
        for (int j = 0; j < 4096; j++) {
            if(Main.Memory[i][j] == (byte) 0x0){
                zeroCount++;
            }else{
                zeroCount = 0;
            }
            if(zeroCount < 4) {
                writer.printf("0x%X%03X\t\t%02X\n", i, j, Main.Memory[i][j]);
            }else if(zeroCount == 4){
                writer.println("");
            }
        }

        writer.flush();
        writer.close();
    }
}
