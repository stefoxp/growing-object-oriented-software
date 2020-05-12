# 11 Passing the first test

## Building the test rig

At the start of every test run, our test script starts up the Openfire server, creates accounts for the Sniper and the auction, and then runs the tests.
Each test will start instances of the application and the fake auction, and then test their communication through the server. At first, we'll run everything on the same host.

This leave us with two components to write for the test infrastructure:

- ApplicationRunner
- FakeAuctionServer

### Setting up the Openfire server

>version 3.6
[downloads and installs an Openfire server as a docker container](https://github.com/thilomack/goos-infra)

For these tests, we set up our local server with three user accounts and passwords:

- sniper
- auction-item-54321
- auction-item-65432

For desktop development, we usually started the server by hand and left it running.
We set it up to not store offline messages (no persistent state).
In the System Manager, we edited the System Name property to be *localhost*.
We set the resource policy to Never kik.

### The application runner

>ApplicationRunner class

It is an object that wraps up all management and communicating with the Swing application we're building.

We can rely on WindowLicker to do the hard work.
WindowLicker has the concept of a ComponentDriver: an object that can manipulate a feature in a Swing user interface.
If a ComponentDriver can't find the Swing component it refers to, it will time out with an error.

- Implementation Code [Auction Sniper](AuctionSniper)

The AuctionSniperDriver is simply an extension of a WindowLicker [build from https://github.com/petercoulton/windowlicker](https://github.com/petercoulton/windowlicker) JFrameDriver specialized for our tests.

>AuctionSniperDriver class

### The Fake Auction

A FakeAuctionServer is a substitute server that allows the test to check how the Auction Sniper interacts with an auction using XMPP messages. It has three responsibilities:

1. it must connect to the XMPP broker and accept a request to join the chat from the Sniper
2. it must receive chat messages from the Sniper or fail if no message arrives within some timeout
3. it must allow the test to send messages back to the Sniper as specified by Southabee 's On-Line

**Smack** (the XMPP client library) is event-driven.
There are two levels of events:

- events about a chat
- events within a chat

>FakeAuctionServer class

### The Message Broker

We set up an instance of **OpenFire** on our local host.

We want all the developers to have their own environments so they don't interfere with each other when running their tests.

## Failing and Passing the Test

We have enough infrastructure in place to run the test and watch it fail.

We start by writing a build script for *ant*.

We'll skip over the details, the important point is that we always have a single command that reliably compiles, builds, deploys, and tests the application, and that we run it repeatedly.
We only start coding once we have an automated build and test working.

>build.xml

### First user interface

The test can't find a user interface component with the name "Auction Sniper Main".

We need a top-level window for our application.

>Main class and MainWindow class
