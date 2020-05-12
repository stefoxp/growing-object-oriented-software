package auctionsniper;

import javax.swing.*;

public class Main {
    private MainWindow ui;

    // missing in book's code sample
    public static final String SNIPER_STATUS_NAME = "sniper status name";
    public static final String MAIN_WINDOW_NAME = "main window name";

    public Main() throws Exception {
        startUserInterface();
    }

    public static void main(String ... args) throws Exception {
        Main main = new Main();
    }

    private void startUserInterface() throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                ui = new MainWindow();
            }
        });
    }
}
