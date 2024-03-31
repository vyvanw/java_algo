package questions;

import doNotModify.Song;
import doNotModify.SongNode;

public class PlayQueue {
    public SongNode start; // DO NOT MODIFY
    public SongNode end;   // DO NOT MODIFY
    // You may add extra attributes here

    /**
     * Adds a Song to the end of the PlayQueue.
     * <p>
     * Note: This must be completed before moving onto any other method.
     * @param song - The Song to add
     */
    public void addSong(Song song) {
        // TODO: To be completed
    	SongNode newSong = new SongNode(song, null, end);
    	if (start == null) {
            start = newSong;
        }
    	if (end != null) {
            end.next = newSong;
        }
        end = newSong; //update cursor
    }

    /**
     * Remove the first SongNode with the parameter Song from the PlayQueue.
     * <p>
     * Return true if a SongNode was removed, false otherwise.
     * @param song
     * @return - true if a SongNode was removed, false otherwise.
     */
    public boolean removeSong(Song song) {
    	SongNode current = start;
        while (current != null) {
            if (current.song.equals(song)) {
                if (current == start) { //if remove first node
                    start = current.next;
                    if (start != null) {
                        start.previous = null;
                    }
                } else if (current == end) { // if remove last node
                    end = current.previous;
                    if (end != null) {
                        end.next = null;
                    }
                } else { // if remove middle nodes
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                return true;
            }
            current = current.next; //traverse through the list
        }
        return false;  
    }

    /**
     * Removes the SongNode at the specified index from the PlayQueue, returning
     * the Song that was removed.
     * <p>
     * Return null if `index` is invalid.
     * @param index
     */
    public Song removeSong(int index) {
    	if (index < 0) return null; // if negative, immediately return null
        SongNode current = start;
        int i = 0;
        while (current != null) {
            if (i == index) {
                Song removedSong = current.song;
                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    start = current.next; // if first node
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    end = current.previous; //if last node
                }
                return removedSong;
            }
            i++;
            current = current.next;
        }
        return null;  
    }

    /**
     * Return the size (number of SongNodes) in the PlayQueue.
     * @return the size of the PlayQueue
     */
    public int size() {
        int count = 0;
        SongNode current = start;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Reverse the calling object PlayQueues Song ordering.
     */
    public void reverseQueue() {
        // TODO: To be completed
    	SongNode current = start;
        SongNode temp = null;
        while (current != null) {
            temp = current.previous; 
            current.previous = current.next;
            current.next = temp;
            current = current.previous; 
        }
        temp = start; 
        start = end;
        end = temp;
    }

    /**
     * Move the SongNode from the `fromIndex` index the specified `amount`.
     * 
     * Let the queue be:
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * Let fromIndex be 1.
     * The expected queue should be as follows for:
     * amount := 0
     *       start              end
     *         |                 |
     * null <- a <-> b <-> c <-> d -> null
     * 
     * amount := 1
     *       start              end
     *         |                 |
     * null <- a <-> c <-> b <-> d -> null
     * 
     * amount := -1
     *       start              end
     *         |                 |
     * null <- b <-> a <-> c <-> d -> null
     * 
     * amount := 2
     *       start              end
     *         |                 |
     * null <- a <-> c <-> d <-> b -> null
     * <p>
     * Do nothing if either `fromIndex` is invalid, or `amount` is invalid for
     * the given `fromIndex`.
     * <p>
     * Do not create any new SongNode instances.
     * @param fromIndex
     * @param amount
     */
    public void moveSong(int fromIndex, int amount) {
        return;
    }





    /**
     * Swap the SongNodes at parameter indices.
     * Do nothing if either parameters are invalid.
     * @param firstIndex
     * @param secondIndex
     */
    public void swapSongs(int firstIndex, int secondIndex) {
        // TODO: To be completed
    	if (firstIndex < 0 || firstIndex >= size() || secondIndex < 0 || secondIndex >= size() || firstIndex == secondIndex) {
            return;
        }

        // Ensure firstIndex is less than secondIndex to simplify logic
        if (firstIndex > secondIndex) {
            int temp = firstIndex;
            firstIndex = secondIndex;
            secondIndex = temp;
        }

        // Find the nodes at the given indices
        SongNode firstNode = getNodeAtIndex(firstIndex);
        SongNode secondNode = getNodeAtIndex(secondIndex);

        // Edge case: adjacent nodes
        if (secondIndex - firstIndex == 1) {
            swapAdjacent(firstNode, secondNode);
            return;
        }

        // Disconnect the first node
        if (firstNode.previous != null) firstNode.previous.next = firstNode.next;
        else start = firstNode.next; // firstNode was the start
        firstNode.next.previous = firstNode.previous;

        // Disconnect the second node
        if (secondNode.next != null) secondNode.next.previous = secondNode.previous;
        else end = secondNode.previous; // secondNode was the end
        secondNode.previous.next = secondNode.next;

        // Swap
        SongNode tempPrev = firstNode.previous;
        SongNode tempNext = firstNode.next;
        firstNode.previous = secondNode.previous;
        firstNode.next = secondNode.next;
        secondNode.previous = tempPrev;
        secondNode.next = tempNext;

        // Reconnect nodes
        if (firstNode.previous != null) firstNode.previous.next = firstNode;
        else start = firstNode; // firstNode is now the start
        if (firstNode.next != null) firstNode.next.previous = firstNode;
        else end = firstNode; // firstNode is now the end

        if (secondNode.previous != null) secondNode.previous.next = secondNode;
        else start = secondNode; // secondNode is now the start
        if (secondNode.next != null) secondNode.next.previous = secondNode;
        else end = secondNode; // secondNode is now the end
    }

    private void swapAdjacent(SongNode firstNode, SongNode secondNode) {
        if (firstNode.previous != null) firstNode.previous.next = secondNode;
        else start = secondNode;
        if (secondNode.next != null) secondNode.next.previous = firstNode;
        else end = firstNode;

        firstNode.next = secondNode.next;
        secondNode.previous = firstNode.previous;

        firstNode.previous = secondNode;
        secondNode.next = firstNode;
    }

    private SongNode getNodeAtIndex(int index) {
        SongNode current = start;
        for (int i = 0; i < index && current != null; i++) {
            current = current.next;
        }
        return current;
    
    }

    /**
     * Check the PlayQueue for cycles.
     * <p>
     * There is at most one cycle in the PlayQueue. This may be bi-directional.
     * @return - true if a cycle is detected, false otherwise.
     */
    public boolean hasCycle() {
    	if (isCycleForward()) return true;
        // Check cycle using previous pointers
        return isCycleBackward();  // TODO: To be completed
    }
    
    private boolean isCycleForward() {
        SongNode slow = start;
        SongNode fast = start;

        while (fast != null && fast.next != null) {
            slow = slow.next; // moves by one
            fast = fast.next.next; // moves by two

            if (slow == fast) {
                return true; // Cycle detected in the forward direction
            }
        }
        return false; // No cycle detected in the forward direction
    }

    private boolean isCycleBackward() {
        SongNode slow = end;
        SongNode fast = end;

        while (fast != null && fast.previous != null) {
            slow = slow.previous; // moves by one backward
            fast = fast.previous;
            if (fast.previous != null) {
                fast = fast.previous; // moves by two backward
            }

            if (slow == fast) {
                return true; // Cycle detected in the backward direction
            }
        }
        return false; // No cycle detected in the backward direction
    }

    /**
     * Create and return a (semi) randomly shuffled PlayQueue from the calling object.
     * <p>
     * A shuffled PlayQueue begins with the same Song as the calling object.
     * For all other Songs in the resulting PlayQueue the following formula is used:
     * <p>
     * (x^2 + 1) % p * s % n
     * <p>
     * where x is the index previously taken from,
     * <p>
     * where p is a prime number,
     * <p>
     * where s is seed number.
     * <p>
     * and n is the length of the PlayQueue
     * <p>
     * You must ensure that you do not go out of bounds, and that when the provided formula
     * creates a cycle that it is no longer used. Then the Songs in all uncovered SongNodes
     * are added in their original order to the resulting PlayQueue.
     * 
     * @param p - prime number
     * @param s - seed number
     * @return the shuffled queue
     */
    public PlayQueue shuffledQueue(int p, int s) {
        return null;  // TODO: To be completed
    }


    @Override
    public String toString() {
        if (start == null) {
            return "null";
        }
        String forward = " forwards :         ";
        SongNode temp = start;
        while (temp.next != null) {
            forward += temp.song.title + " -> ";
            temp = temp.next;
        }
        forward += temp.song.title + " -> null";

        temp = end;
        String backward = "";
        while (temp.previous != null) {
            backward = " <- " + temp.song.title + backward;
            temp = temp.previous;
        }
        backward = "backwards : null <- " + temp.song.title + backward;
        return forward + "\n" + backward;
    }
}
