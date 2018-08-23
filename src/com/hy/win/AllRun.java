package com.hy.win;

public class AllRun {
    public static void main(String[] args) {
        KeyboardHook kbhook = new KeyboardHook();
        new Thread(kbhook).start();
    }
}
