package com;

public class ClockTimer implements Runnable {

    public void run() {

        //long timer = 0;

        while (true) {

            try {
                Thread.sleep(25);
            } catch (InterruptedException e1) {
            }
            //timer++;
            //System.out.println("timer = " + timer);
            Main.CLK = !Main.CLK;
            if (Main.CLK) {
                Main.R0.clockIn();
                Main.R1.clockIn();
                Main.R2.clockIn();
                Main.R3.clockIn();
                Main.SP.clockIn();
                Main.IP.clockIn();
                Main.FLAGS.clockIn();
            }
            //System.out.println(Main.CLK);

        }
    }

    public static void waitForTick(){
        try {
            while (Main.CLK) {
                Thread.sleep(2);
            }
            while (!Main.CLK) {
                Thread.sleep(2);
            }
        } catch (InterruptedException e) {
        }
    }
}
