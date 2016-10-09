##Chapter 10 Sorting and Searching
###1.1 Sorted Merge
Question:

	You are given two sorted arrays, A and B, where A has a large enough buffer at the
	end to hold B. Write a method to merge B into A in sorted order
Answer:

	Merge B and A 从后向前
###1.2 Group Anagrams
Question:

	Write a method to sort an array of strings so that all the anagrams are next to each 
	other.
Answer:

	bool cmp(string a, string b){
		string c = a;
		string d = b;
		sort(c.begin(), c.end())
		sort(d.begin(), d.end())
		return c < d;
	}
###1.3 Search in Rotated Array
Question:

	Given a sorted array of n integers that has been rotated an unknown number of times, 
	write code to find an element in the array. You may assume that the array was
	originally sorted in increasing order

	EXAMPLE
	Input:find 5 in {lS, 16, 19, 20, 25, 1, 3, 4, 5, 7, l0, 14}
	Output: 8 (the index of 5 in the array)
Answer:

	leetcode, Binary Search 的变形
###1.4 Sorted Search, No Size
Question:

	You are given an array-like data structure Listy which lacks a size	method. It does, 
	however, have an elementAt(i) method that returns the element at index i in
	0(1) time. If i is beyond the bounds of the data structure, it returns -1. 
	(For this reason, the data structure only supports positive integers.) Given a 
	Listy which contains sorted, positive integers, find the index at which an 
	element x occurs. If x occurs multiple times, you may return any index
Answer:

	Bianry Search 倍增寻找?
###1.5 Sparse Search
Question:

	Given a sorted array of strings that is interspersed with empty strings, write a
	method to find the location of a given string.

	Example: 
    find “ball” in [“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”, “”, “dad”, “”, “”] 
	will return 4

	find “ballcar” in [“at”, “”, “”, “”, “”, “ball”, “car”, “”, “”, “dad”, “”, “”] 
	will return -1
Answer:
	
	Binary Search, 当遇着""时，向左或向右扫描至第一个非""字符
###1.6 Sort Big File
Question:

	Imagine you have a 20 GB file with one string per line. Explain how you would 
	sort the file.
Answer:
	
	多趟排序，sorted Array List Merge
###1.7 Missing Int
Question:

	Given an input file with four billion non-negative integers, provide an algorithm to
	generate an integer that is not contained in the file. Assume you have 1 GB of
	memory available for this task

	FOLLOW UP
	What if you have only 10MB of memory? Assume that all the values are distinct and 
	we now have no more than one billion non-negative integers.

Answer:

	To do
###1.8 Find Duplicates
Question:

	You have an array with all the numbers from 1 to N, where N is at most 32,000. The
	array may have duplicate entries and you do not know what N is. With only 4 
	kilobytes of memory available, how would you print all duplicate elements in 
	the array?
Answer:

	To do
###1.9 Sorted Matrix Search
Question:

	Given an M x N matrix in which each row and each column is sorted in 
	ascending order, write a method to find an element. 

Answer:

	Binary Search，先对行做二分搜索，再对列做二分搜索
###1.10 Rank from Stream
Question:

	Imagine you are reading in a stream of integers. Periodically, you wish to be able
	to look up the rank of a number x (the number of values less than or equal to x). 
	Implement the data structures and algorithms to support these operations. That is, 
	implement the method track (int x), which is called when each number is generated, 
	and the method getRankOfNumber(int x), which returns the number of values less than 
	or equal to X (not including x itself). 

	EXAMPLE
	Stream(in order of appearance):5, 1, 4, 4, 5, 9, 7, 13, 3
	getRankOfNumber(l) 0
	getRankOfNumber(3) 1
	getRankOfNumber(4) 3 

Answer:

	Binary Search Tree?
	track(int x) -> tree.insert(x)  O(lgn)
	getRankOfNumber(int x) -> tree.rank(x)  O(lgn)

###1.11 Peaks and Valleys
Question:

	In an array of integers, a "peak" is an element which is greater than or equal to
	the adjacent integers and a "valley" is an element which is less than or equal to 
	the adjacent integers. For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} 
	are peaks and {S, 2} are valleys. Given an array of integers, sort the array into 
	an alternating sequence of peaks and valleys. 

	EXAMPLE
	Input: {S, 3, 1, 2, 3}
	Output: {S, 1, 3, 2, 3}
Answer:

	leetcode Wiggle Sort I and II

###1.12 Design A Tower (Fifth Edition)
Question:

	A circus is designing a tower routine consisting of people standing atop one 
	another’s shoulders. For practical and aesthetic reasons, each person must be both 
	shorter and lighter than the person below him or her. Given the heights and weights 
	of each person in the circus, write a method to compute the largest possible number 
	of people in such a tower.

	EXAMPLE:
	Input (ht, wt): (65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)
	Output: The longest tower is length 6 and includes from top to bottom:
	(56, 90) (60,95) (65,100) (68,110) (70,150) (75,190)
Answer:

	To do
	