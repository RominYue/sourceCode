##Interview Questions
###Union-Find
####1. Social network connectivity
Question:

	Given a social network containing n members and a log file containing m timestamps at which times pairs of members 
	formed friendships, design an algorithm to determine the earliest time at which all members are connected 
	(i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by 
	timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or 
	better and use extra space proportional to n
Hint:

	union−find
Answer:

	Union-Find with pass compression is enough.

####2. Union-find with specific canonical element
Question:

	Add a method find() to the union-find data type so that find(i) returns the largest element in the connected 
	component containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.

	Example
	if one of the connected components is {1,2,6,9}, then the find() method should return 9 for each of the four 
	elements in the connected components.

Hint:

	maintain an extra array to the weighted quick-union data structure that stores for each root i the large 
	element in the connected component containing i.

Answer:

	take an extra array called  max_val[n], when every union operation is doing, update the max_val array.

####3. Successor with delete
Question:

	Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the following form:
	(1) Remove x from S
	(2) Find the successor of x: the smallest y in S such that y≥x.
	design a data type so that all operations (except construction) should take logarithmic time or better.
Hint:

	use the modification of the union-find data discussed in the previous question.
Answer:

	This can be treated as an union-find problem.
 	1. Treat Root(x) as the successor of x
 	2. Delete(x) is equivalent to Union(x, x + 1) and specifically in Union(x, x+1) we need to change Root(x) to Root(x+1)
 	3. Successor(x) is equivalent to return Root(x)

###Analysis of Algorithms
####1. 3-SUM in quadratic time
Question:

	Design an algorithm for the 3-SUM problem that takes time proportional to n2 in the worst case. You may assume 
	that you can sort the n integers in time proportional to n2 or better
Hint:

	given an integer x and a sorted array a[] of n distinct integers, design a linear-time algorithm to determine 
	if there exists two distinct indices i and j such that a[i]+a[j]==x.
Answer:

	two pointers

####2. Search in a bitonic array
Question:

	An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a 
	decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer values, 
	determines whether a given integer is in the array

	(1)Standard version: Use ∼3lgn compares in the worst case.
	(2)Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform 
	fewer than ∼2lgn compares in the worst case).
Hint:

	Standard version. First, find the maximum integer using ∼1lgn compares—this divides the array into the increasing and decreasing pieces.

	Signing bonus. Do it without finding the maximum integer.
Answer:

	To do

####3. Egg drop
Quesiton:

	Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if it is 
	dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the 
	value of T given the following limitations on the number of eggs and tosses

	Version 0: 1 egg, ≤T tosses.
	Version 1: ∼1lgn eggs and ∼1lgn tosses.
	Version 2: ∼lgT eggs and ∼2lgT tosses.
	Version 3: 2 eggs and ∼2n−−√ tosses.
	Version 4: 2 eggs and ≤cT−−√ tosses for some fixed constant c.

Hint:

	Version 0: sequential search.
	Version 1: binary search.
	Version 2: find an interval containing T of size ≤2T, then do binary search.
	Version 3: find an interval of size sqrt(n), then do sequential search. Note: can be improved to sqrt(2)n tosses.
	Version 4: 1+2+3+…+t∼12t2. Aim for c=2sqrt(2).

Answer:

	To do

###Stack and Queue
####1. Queue with two stacks
Question:

	Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
Answer:

	leetcode

####2. Stack with max
Question:

	Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-
	maximum operation. Assume the elements are reals numbers so that you can compare them.

Answer:

	CC150

####3. Java generics
Question:

	Explain why Java prohibits generic array creation.
Answer:

	 you need to understand that Java arrays are covariant but Java generics are not: that is, String[] is a 
	subtype of Object[], but Stack<String> is not a subtype of Stack<Object>.

###Elementary Sorts
####1. Intersection of two sets
Question:

	Given two arrays a[] and b[], each containing n distinct 2D points in the plane, design a subquadratic 
	algorithm to count the number of points that are contained both in array a[] and array b[].
Hint:

	shellsort (or any other subquadratic sort).
Answer:

	hashtable

####2. Permutation
Question:

	Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a permutation 
	of the other. That is, do they contain exactly the same entries but, possibly, in a different order.

Hint:

	sort both arrays

####3. Dutch national flag
Question:

	Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

	(1) swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
	(2) color(i): color of pebble in bucket i.

	The performance requirements are as follows:
	(1) At most n calls to color()
	(2) At most n calls to swap()
	(3) Constant extra space

Answer:

	leetcode or three-way partition in quicksort

###Merge Sort
####1. Merging with smaller auxiliary array
Question:

	Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] is sorted. How can you 
	merge the two subarrays so that a[0] to a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?

Hint:

	copy only the left half into the auxiliary array.

Answer:

	use a[n] - a[2*n-1] as another length n auxiliary array
####2. Counting inversions
Question:

	An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j]. Given an array, 
	design a linearithmic algorithm to count the number of inversions.

Answer:

	mergesort count inversions

####3. Shuffling a linked list
Question:

	Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should 
	consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst 
	case.

Hint:

	design a linear-time subroutine that can take two uniformly shuffled linked lists of sizes n1 and n2 and 
	combined them into a uniformly shuffled linked lists of size n1+n2.
Answer:

	to do

###QuickSort
####1. Nuts and bolts
Question:

	A disorganized carpenter has a mixed pile of n nuts and n bolts. The goal is to find the corresponding pairs of 
	nuts and bolts. Each nut fits exactly one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt 
	together, the carpenter can see which one is bigger (but the carpenter cannot compare two nuts or two bolts 
	directly). Design an algorithm for the problem that uses nlogn compares (probabilistically)

Hint:

	modify the quicksort partitioning part of quicksort.

	Remark: This research paper gives an algorithm that runs in nlog4n time in the worst case.
	http://web.cs.ucla.edu/~rafail/PUBLIC/17.pdf

####2. Selection in two sorted arrays
Question:

	Given two sorted arrays a[] and b[], of sizes n1 and n2, respectively, design an algorithm to find the kth
	largest key. The order of growth of the worst case running time of your algorithm should be logn, where n=n1+n2

	Version 1: n1=n2 and k=n/2
	Version 2: k=n/2
	Version 3: no restrictions

Hint:

	there are two basic approaches.

	Approach A: Compute the median in a[] and the median in b[]. Recur in a subproblem of roughly half the size.
	Approach B: Design a constant-time algorithm to determine whether a[i] is the kth largest key. Use this 
	subroutine and binary search.
	
	Dealing with corner cases can be tricky.

####3. Decimal dominants
Question:

	Given an array with n keys, design an algorithm to find all values that occur more than n/10 times. The 
	expected running time of your algorithm should be linear.

Hint:

	determine the (n/10)th largest key using quickselect and check if it occurs more than n/10 times.

	Alternate solution hint: use 9 counters.

Answer:

	kth element?  moore

### Priority Queue
####1. Dynamic median
Question:

	Design a data type that supports insert in logarithmic time, find-the-median in constant time, and remove-the-
	median in logarithmic time.

Hint:

	maintain two binary heaps, one that is max-oriented and one that is min-oriented.

####2. Randomized priority queue
Question:

	Describe how to add the methods sample() and delRandom() to our binary heap implementation. The two methods 
	return a key that is chosen uniformly at random among the remaining keys, with the latter method also removing 
	that key. The sample() method should take constant time; the delRandom() method should take logarithmic time. 
	Do not worry about resizing the underlying array.

Hint:

	No

####3. Taxicab numbers
Question:

	A taxicab number is an integer that can be expressed as the sum of two cubes of integers in two different ways: a3+b3=c3+d3. For example, 1729=93+103=13+123. Design an algorithm to find all taxicab numbers with a, b, c, and d less than n.

	Version 1: Use time proportional to n2logn and space proportional to n2.
	Version 2: Use time proportional to n2logn and space proportional to n.

Hint:

	Version 1: Form the sums a3+b3 and sort.
	Version 2: Use a min-oriented priority queue with n items.

###Elementary Symbol Tables
####1. Java autoboxing and equals()
Question:

	Consider two double values a and b and their corresponding <tt>Double</tt> values x and y.
	Find values such that (a==b) is true but x.equals(y) is false.
	Find values such that (a==b) is false but x.equals(y) is true.

Hint:

	IEEE floating point arithmetic has some peculiar rules for 0.0, −0.0, and NaN. Java requires that equals() 
	implements an equivalence relation.

####2. Check if a binary tree is a BST
Question:

	Given a binary tree where each Node contains a key, determine whether it is a binary search tree. Use extra 
	space proportional to the height of the tree.

Hint:

	design a recursive function isBST(Nodex,Keymin,Keymax) that determines whether x is the root of a binary search 
	tree with all keys between min and max.

Answer:

	中序遍历看是否有序

####3. Inorder traversal with constant extra space
Question:

	Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space

Hint:

	you may modify the BST during the traversal provided you restore it upon completion.

####4. Web tracking
Question:

	Suppose that you are tracking n web sites and m users and you want to support the following API:
	(1) User visits a website.
	(2) How many times has a given user visited a given site?
	What data structure or data structures would you use?

Answer:

	map< user, map<site,int>>

###Balanced Search Trees
####1. Red–black BST with no extra memory
Question:

	Describe how to save the memory for storing the color information when implementing a red–black BST.

Hint:

	 modify the structure of the BST to encode the color information.

####2. Document search
Question:

	Design an algorithm that takes a sequence of n document words and a sequence of m query words and find the 
	shortest interval in which the m query words appear in the document in the order given. The length of an 
	interval is the number of words in that interval.

Hint:

	for each word, maintain a sorted list of the indices in the document in which that word appears. Scan through 
	the sorted lists of the query words in a judicious manner.

####3. Generalized queue
Question:

	Design a generalized queue data type that supports all of the following operations in logarithmic time (or 
	better) in the worst case.

	Create an empty data structure.
	Append an item to the end of the queue.
	Remove an item from the front of the queue.
	Return the ith item in the queue.
	Remove the ith item from the queue.

Hint:

	create a red–black BST where the keys are integers and the values are the items such that the ith largest 
	integer key in the red–black BST corresponds to the ith item in the queue.

###Hash Tables
####1. 4-SUM
Question:

	leetcode

####2. Hashing with wrong hashCode() or equals()
Question:

	Suppose that you implement a data type OlympicAthlete for use in a java.util.HashMap.

	Describe what happens if you override hashCode() but not equals().
	Describe what happens if you override equals() but not hashCode().
	Describe what happens if you override hashCode() but implement public boolean equals(OlympicAthlete that) 
	instead of public boolean equals(Object that)