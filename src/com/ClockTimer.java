package com;

/**
 * ClockTimer class:
 * Runs the clock for the chips
 * Only ties to registers at the moment
 */
public class ClockTimer implements Runnable {
    /**
     * When the clock goes high, it calls the clockIn method for each register
     */
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

    /**
     * This method makes the unclocked portions of the program wait for a clock-in
     * Used when waiting for a register to save a value
     */
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
