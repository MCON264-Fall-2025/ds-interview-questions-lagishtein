package edu.touro.mcon264.l4_detect_cycle;



public class Solution {

    // Provided value object
   static final class CycleInfo {
        public final int entryIndex;   // 0-based index from head to entry node; -1 if no cycle
        public final int cycleLength;  // number of nodes in the cycle; 0 if no cycle

        public CycleInfo(int entryIndex, int cycleLength) {
            this.entryIndex = entryIndex;
            this.cycleLength = cycleLength;
        }
    }
    static class LLNode {
        public int val;        // or whatever data field
        public LLNode next;
        public LLNode(int val) { this.val = val; }
    }



    /**
     * Detects whether the linked list starting at head contains a cycle.
     * If no cycle: returns new CycleInfo(-1, 0)
     * If cycle:
     *   - entryIndex = index (0-based) of the first node in the cycle
     *   - cycleLength = number of unique nodes in the loop
     *
     * Runs in O(n) time, O(1) extra space.
     */
    public static CycleInfo detectCycleInfo(LLNode head) {
        if (head == null) {
            return new CycleInfo(-1, 0);
        }

        // 1. Use Floyd's algorithm to detect a meeting point (if any)
        LLNode slow = head;
        LLNode fast = head;
        boolean hasCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // moves 1 step
            fast = fast.next.next;      // moves 2 steps

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // No cycle found
        if (!hasCycle) {
            return new CycleInfo(-1, 0);
        }

        // 2. Determine the cycle length
        int cycleLength = 1;
        LLNode cursor = slow.next;
        while (cursor != slow) {
            cursor = cursor.next;
            cycleLength += 1;
        }

        // 3. Find the entry node of the cycle
        // Move one pointer to head, keep the other at meeting point.
        // Advance both 1 step at a time; where they meet is the cycle entry node.
        LLNode ptr1 = head;
        LLNode ptr2 = slow;
        int entryIndex = 0;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            entryIndex += 1;
        }

        // ptr1 == ptr2 is the entry node of the cycle.
        return new CycleInfo(entryIndex, cycleLength);
    }
}


