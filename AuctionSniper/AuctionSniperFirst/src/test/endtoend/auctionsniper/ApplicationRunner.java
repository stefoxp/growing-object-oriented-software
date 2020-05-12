package test.endtoend.auctionsniper;

import sun.applet.Main;

public class ApplicationRunner {
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PASSWORD = "sniper";
    private AuctionSniperDriver driver;

    public void startBiddingIn(final FakeAuctionServer auction) {
        Thread thread = new Thread("Test Application") {
            // we call the application through its main() function to make sure we're assembled the pieces correctly
            @Override
            public void run() {
                try {
                    // to keep simple, we'll assume that we're only binding for one item
                    // and pass the identifier to main()
                    Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PASSWORD, auction.getItemId());
                } catch (Exception e) {
                    // we just print exception. Later we'll handle exceptions properly
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        // we turn down the timeout period for finding frames and components
        driver = new AuctionSniperDriver(1000);
        // we wait the status to change to Joining so we know that the application has attempted to connect
        diver.showsSniperStatus(STATUS_JOINING);
    }
    public void showsSniperHasLostAuction() {
        // we expect the Sniper to show a Lost status
        driver.showSniperStatus(STATUS_LOST);
    }
    public void stop() {
        if (driver != null) {
            // we tell the driver to dispose of the window to make sure
            // it won't be picked up in another test before being garbage-collected
            driver.dispose();
        }
    }
}
