# 04 Kick-Starting the TDD Cycle

Starting with build, deploy, and test on a nonexistent system sounds odd, but we think it's essential.

## Test a Walking Skeleton

First, work out how to build, deploy, and test a walking skeleton, then use that infrastructure to write the acceptance tests for the first meaningful feature.

A walking skeleton is an implementation of the thinnest possible slice of real functionality that we can automatically build, deploy, and test end-to-end (refers to the process).
We keep the skeleton's application functionality so simple that it's obvious and uninteresting.

Including the deployment step in the testing process is critical for two reasons:

1. this is the sort of error-prone activity that should not be done by hand;
2. this is often the moment where the development team bumps into the rest of the organization and has to learn how it operates.

## The Shape of the Walking Skeleton

The development of a walking skeleton is the moment when we start to make choices about the high-level structure of our application.

We should be able to draw the design for the walking skeleton in a few minutes on a whiteboard.
To design this initial structure, we have to have some understanding of the purpose of the system.
We need a high-level view of the client's requirements to guide our choices.

The context of the first test:

understand the problem -> Broad-Brush Design (Architecture) -> Automate: Build, Deployment, End-to-end Tests -> Deployable System

## Build Sources of Feedback

We have no guarantees that the decisions we've taken about the design of our application are right.

Our ideal situation is where the team releases regularly to a real production system. This allows the system's stakeholders to respond to how well the system meets their needs, at the same time allowing us to judge its implementation.

We use the automation of building and testing to give us feedback on qualities of the system.

The automated deployment helps us release frequently to real users, which gives us feedback.

The great benefit is that we will be able to make changes in response to whatever we learn.

## Expose Uncertainty Early

The time to implement the first few features will be unpredictable as the team discovers more about its requirements and target environment.

We don't want to spend the whole project setting up a perfect walking skeleton, but the most important thing is to have a sense of direction and a concrete implementation to test our assumptions.
