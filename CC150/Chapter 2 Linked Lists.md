##Chapter 2 Linked Lists
###1.1 Remove Dups
Question:

	Write code to remove duplicates from an unsorted linked list. 
	FOLLOW UP
	How would you solve this problem if a temporary buffer is not allowed?
Answer:

	1. sort, 然后remove duplicates
	2. 利用hashtable
###1.2 Return Kth to Last
Question:

	 Implement an algorithm to find the kth to last element of a singly linked list.
Answer:

	扫描链表，找到第k个元素的father 指针，以及tail指针，然后删除和插入即可
###1.3 Delete Middle Node
Question:

	Implement an algorithm to delete a node in the middle (i.e., any node but
	the first and last node, not necessarily the exact middle) of a singly linked
	list, given only access to that node

	EXAMPLE
	Input: the node c from the linked list a -> b-> c -> d -> e -> f
	Result: nothing is returned, but the new linked list looks like a -> b -> d -
	> e -> f
Answer:

	x 是要删除的结点
	ListNode* tmp = x->next;
	x->next = tmp->next;
	x>val = tmp->val;
	delete(tmp);
###1.4 Partition
Question:

	Write code to partition a linked list around a value x, such that all nodes 
	less than x come before all nodes greater than or equal to x. lf x is contained 
	within the list, the values of x only need to be after the elements less 
	than x (see below). The partition element x can appear anywhere in the 
	"right partition"; it does not need to appear between the left and right 
	partitions.

	EXAMPLE
	Input: 3 -> 5 -> 8 -> 5 - > 10 -> 2 -> 1 [partition = 5)
	Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8

Answer:

	leetcode
###1.5 Sum Lists
Question:

	You have two numbers represented by a linked list, where each node contains a 
	single digit. The digits are stored in reverse order, such that the 1's digit
	is at the head of the list. Write a function that adds the two numbers and 
	returns the sum as a linked list.

	EXAMPLE
	Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295.
	Output: 2 -> 1 -> 9. That is, 912.

	FOLLOW UP
	Suppose the digits are stored in forward order. Repeat the above problem 
	EXAMPLE
	Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295.
	Output: 9 -> 1 -> 2. That is, 912.

Answer:

	模拟加法即可，若不是逆序排列，先对数字做逆序
###1.6 Palindrome
Question:

	 Implement a function to check if a linked list is a palindrome
Answer:

	Two Pointer 找到中点
    翻转后一半的链表
	Two Pointer从头和从中间一一比较即可

###1.7 Intersection
Question:

	Given two (singly) linked lists, determine if the two lists intersect. Return
	the intersecting node. Note that the intersection is defined based on reference
	,not value. That is, if the kth node of the first linked list is the exact same
	node (by reference) as the jth node of the second linked list, then they are 
	intersecting.

Answer:

	leetcode.
	两个链表都无环?
	一个链表有环，一个链表无环?
	两个链表都有环?

###1.8 Loop Detection
Question:

	Given a circular linked list, implement an algorithm that returns the node at 
	the beginning of the loop. 

	DEFINITION
	Circular linked list: A (corrupt) linked list in which a node's next pointer 
	points to an earlier node, so as to make a loop in the linked list.

	EXAMPLE
	Input: A -> B -> C -> D -> E -> C[thesameCasearlierl
	Output: C
Answer:

	leetcode

###1.9 Find Last nth Element (Fifth Edition)
Question:

	Implement an algorithm to find the nth to last element of a singly linked list
Answer:

	leetcode