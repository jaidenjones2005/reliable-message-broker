# Reliable Message Broker Journal

## Phase 1: The Foundation

A Queue is appropriate for a message broker because it uses FIFO (First-In, First-Out). This means messages are processed in the same order they are received, which helps keep processing fair and organized. If a Stack were used instead, it would use LIFO (Last-In, First-Out), causing the newest messages to be processed first while older messages could be delayed.
