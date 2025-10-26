# Linked List Cycle Detection

## Problem Statement
Given the head of a singly linked list, determine whether the list contains a cycle.  
If a cycle exists, return:
- the **0-based index** of the node where the cycle begins, and
- the **length** of the cycle (number of nodes in the loop).

If no cycle exists, return `entryIndex = -1` and `cycleLength = 0`.

---

## Assumptions
- Each node in the list contains an integer value and a reference to the next node.
- The list may be empty (`head = null`).
- No extra data structures (e.g., sets or maps) may be used — only O(1) additional space.
- The list must not be modified while detecting the cycle.

---

## Conceptual Solution
The problem can be solved efficiently using **Floyd’s Tortoise and Hare algorithm**:

1. **Cycle detection:**  
   Use two pointers that move at different speeds — one moves one step at a time (`slow`), the other moves two steps (`fast`).
    - If `fast` reaches the end (`null`), no cycle exists.
    - If `slow` and `fast` ever meet, a cycle is detected.

2. **Finding cycle length:**  
   Once the two pointers meet, keep one pointer fixed and move the other forward until it returns to the same node, counting the number of steps.  
   That count equals the **cycle length**.

3. **Finding entry node:**  
   Reset one pointer to the head of the list while keeping the other at the meeting point.  
   Move both one step at a time — the node where they meet is the **entry node** of the cycle.  
   The number of steps taken from the head gives the **entry index**.

---

## Example
**Input:** `head = [3, 2, 0, -4]`, tail connects to index `1`  
**Output:** `entryIndex = 1`, `cycleLength = 3`

**Explanation:**  
The cycle begins at the node with value `2`, forming the loop `[2 → 0 → -4 → 2]`.

---

## Summary
This algorithm detects cycles in linear time `O(n)` and constant space `O(1)`.  
It leverages the relative speed of two pointers to identify both the presence and structure of the loop without extra memory.
