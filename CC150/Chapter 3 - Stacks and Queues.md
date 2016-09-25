##Chapter 3 | Stacks and Queues
###3.1  Three in One:Describe how you could use a single array to implement three stacks.
	1. 将数组劈成3段，每段维护一个栈顶指针
	2. 直接维护3个栈顶指针，同时维护当前元素指向上一个元素的指针

###3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in 0(1) time.
	1.让每一个入栈元素加一个域，维护当前栈底至栈顶的最小值。这样，min() = s.top().min, 缺点：消耗空间多一倍。
	2.让另外一个栈来维护当前栈顶到栈底的最小值。
伪代码

```c++

	void push(int val)
	{
		if s_min为空：
		    将当前值入栈，将当前值入s_min栈
		else：
		     if 当前值 <= s_min.top():
		          将当前值入栈，将当前值入s_min栈
		     else:
		          将当前值入栈
    }

	void pop()
	{
		if s.top() > s_min.top():
			s.pop()
		else:
		    s.pop(); 
		    s_min.pop();
	}
	
	int min()
	{
		return s_min.top();
	}

```

###3.3 Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single stack (that is, pop ( ) should return the same values as it would if there were just a single stack).

###FOLLOW UP

###Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.

To be continued

###3.4 Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.

	1. leetcode 原题
	2. 执行 enqueue，直接入栈即可
	3. 执行 dequeue，将栈内元素pop然后入另外一个栈，然后dequeue即可

###3.5 Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.

	1.如果可以使用priority_queue, 出栈入队列然后在入栈即可
	2.只允许用一个辅助的stack。模拟插入排序, 一个变量保存当前值，在辅助stack中找到插入位置（不符合一直pop元素出栈入原来的栈），然后push

###3.6 In the classic problem of the Towers of Hanoi, you have 3 rods and N disks of different sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom (e.g., each disk sits on top of an even larger one). You
###have the following constraints:
###(A) Only one disk can be moved at a time.
###(B) A disk is slid off the top of one rod onto the next rod.
###(C) A disk can only be placed on top of a larger disk.
###Write a program to move the disks from the first rod to the last using Stacks.
汉诺塔问题

###3.7 Animal Shelter: An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis. People must adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific animal they would like. Create the data structures to maintain this system and implement operations such as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in Linked List data structure.

	1.维护两个queue，分别是dog和cat，这样dog和cat分别满足要求
	2.dequeueAny，比较两queue的头部元素，pop时间戳小的元素