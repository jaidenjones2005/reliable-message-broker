# Reliable Message Broker Journal

## Phase 1: The Foundation

A Queue is appropriate for a message broker because it uses FIFO (First-In, First-Out). This means messages are processed in the same order they are received, which helps keep processing fair and organized. If a Stack were used instead, it would use LIFO (Last-In, First-Out), causing the newest messages to be processed first while older messages could be delayed.
## Phase 2: The Processing Loop

Adding failed messages back to the end of the queue helps keep the system fair because one failed message does not prevent the other messages from being processed. When a message fails, its retry count increases and it moves to the rear of the queue. This gives the other messages a chance to be processed before the failed message is tried again.
## Phase 3: The Dead-Letter Queue

A poison message starts in the main processing queue like any other message. When it is processed and fails, its retry count increases and it is added back to the rear of the main queue. This continues until the retry count reaches the maximum of three attempts. Once it reaches MAX_RETRIES, the message is no longer added back to the main queue. Instead, it is moved to the Dead-Letter Queue. Its retry count stays at three, which shows how many times processing failed. The message remains in the DLQ until the user chooses to view and clear the DLQ, which dequeues and removes it.