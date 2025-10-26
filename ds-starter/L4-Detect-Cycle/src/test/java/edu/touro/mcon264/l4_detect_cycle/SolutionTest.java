package edu.touro.mcon264.l4_detect_cycle;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.touro.mcon264.l4_detect_cycle.Solution.LLNode;

public class SolutionTest {

 /**
  * Helper to build a linked list from an array of values.
  * If connectToIndex >= 0, the last node's next will point to the node at connectToIndex
  * (0-based), creating a cycle. If connectToIndex == -1, no cycle is created.
  *
  * Returns the head of the list.
  */
 private Solution.LLNode buildListWithCycle(int[] values, int connectToIndex) {
  if (values.length == 0) {
   return null;
  }

  LLNode head = new LLNode(values[0]);
  LLNode curr = head;

  // Keep references to each node by index so we can hook the cycle
  LLNode[] nodes = new LLNode[values.length];
  nodes[0] = head;

  for (int i = 1; i < values.length; i++) {
   LLNode next = new LLNode(values[i]);
   curr.next = next;
   curr = next;
   nodes[i] = next;
  }

  // create cycle if requested
  if (connectToIndex >= 0) {
   curr.next = nodes[connectToIndex];
  }

  return head;
 }

 @Test
 public void example1_cycleStartsAtIndex1_length3() {
  // head = [3,2,0,-4], tail connects to index 1
  // cycle is [2,0,-4] -> length 3
  // entryIndex = 1
  int[] values = {3, 2, 0, -4};
  LLNode head = buildListWithCycle(values, 1);

  Solution.CycleInfo info =
          Solution.detectCycleInfo(head);

  assertEquals(1, info.entryIndex,
          "Entry index should be 1 (node with value 2)");
  assertEquals(3, info.cycleLength,
          "Cycle length should be 3 ([2,0,-4])");
 }

 @Test
 public void example2_cycleStartsAtIndex0_length2() {
  // head = [1,2], tail connects to index 0
  // This is a 2-node cycle [1,2]
  // entryIndex = 0
  // cycleLength = 2
  int[] values = {1, 2};
  LLNode head = buildListWithCycle(values, 0);

  Solution.CycleInfo info =
          Solution.detectCycleInfo(head);

  assertEquals(0, info.entryIndex,
          "Entry index should be 0 (node with value 1)");
  assertEquals(2, info.cycleLength,
          "Cycle length should be 2 ([1,2])");
 }

 @Test
 public void example3_noCycle_entryNeg1_length0() {
  // head = [1], no cycle
  // entryIndex = -1
  // cycleLength = 0
  int[] values = {1};
  LLNode head = buildListWithCycle(values, -1);

  Solution.CycleInfo info =
          Solution.detectCycleInfo(head);

  assertEquals(-1, info.entryIndex,
          "No cycle -> entryIndex should be -1");
  assertEquals(0, info.cycleLength,
          "No cycle -> cycleLength should be 0");
 }

 // (Optional bonus: null / empty list case)
 @Test
 public void emptyList_noCycle() {
  LLNode head = buildListWithCycle(new int[]{}, -1);

  Solution.CycleInfo info =
          Solution.detectCycleInfo(head);

  assertEquals(-1, info.entryIndex,
          "Empty list should have no cycle");
  assertEquals(0, info.cycleLength,
          "Empty list should have no cycle");
 }
}


