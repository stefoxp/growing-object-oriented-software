package test.endtoend.auctionsniper;

import org.junit.After;
import org.junit.Test;

public class AuctionSniperEndToEndTest {
    private final FakeAuctionServer auction = new FakeAuctionServer("item-54321");
    private final ApplicationRunner application = new ApplicationRunner();

    @Test
    public void sniperJoinsAuctionUntilAuctionCloses() throws Exception {
        auction.startSellingItem();                 // step 1
        application.startBiddingIn(auction);        // step 2
        auction.hasReceivedJoinRequestFromSniper(); // step 3
        auction.announceClosed();                   // step 4
        application.showsSniperHasLostAuction();    // step 5
    }

    // additional cleanup
    @After
    public void stopAuction() {
        auction.stop();
    }

    @After
    public void stopApplication() {
        application.stop();
    }
}
