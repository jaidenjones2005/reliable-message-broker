# Reliable Message Broker Journal

## Phase 1: The Foundation

A Queue is appropriate for a message broker because it uses FIFO (First-In, First-Out). This means messages are processed in the same order they are received, which helps keep processing fair and organized. If a Stack were used instead, it would use LIFO (Last-In, First-Out), causing the newest messages to be processed first while older messages could be delayed.
## Phase 2: The Processing Loop

Adding failed messages back to the end of the queue helps keep the system fair because one failed message does not prevent the other messages from being processed. When a message fails, its retry count increases and it moves to the rear of the queue. This gives the other messages a chance to be processed before the failed message is tried again.