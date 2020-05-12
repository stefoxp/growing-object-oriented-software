package test.endtoend.auctionsniper;

import javax.swing.*;
import com.objogate.wl.swing.driver.JFrameDriver;

public class AuctionSniperDriver extends JFrameDriver {
    public AuctionSniperDriver(int timeoutMillis) {
        super(new GesturePerformer(),
                JFrameDriver.topLevelFrame(
                        named(Main.MAIN_WINDOW_NAME),
                        showingOnScreen()),
                        new AWTEventQueueProber(timeoutMillis, 100
                ));
    }

    public void showsSniperStatus(String statusText) {
        new JLabelDriver(
                this, named(Main.SNIPER_STATUS_NAME)).hasText(equalTo(statusText));
    }
}
