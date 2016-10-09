## Chapter 8 Recursion and Dynamic Programming
###1.1 Triple Step
Question

	 A child is running up a staircase with n steps and can hop either 1 step, 2 steps, 
	or 3 steps at a time. Implement a method to count how many possible ways the  child 
	can run up the stairs.
Answer:

	dp[i]表示到达第i阶台阶时，所有的走法。 状态转移方程为
	dp[i] = sum(dp[i-1], dp[i-2], dp[i-3]);
	边界条件：dp[0] = 1, dp[1] = 1
###1.2 Robot in a Grid:
Question

	Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
	The robot can only move in two directions, right and down, but certain cells are 
	"off limits" such that the robot cannot step on them. Design an algorithm to find 
	a path for the robot from the top left to the bottom right. 
Answer:

	dp[i][j]表示到达(i,j)的位置所需要的最短距离，状态转移方程为
    dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + 1
	边界条件: dp[0][j] == "off limits" INF: dp[0][j-1] + 1;
             dp[i][0] == "off limits" INF: dp[i-1][0] + 1;
###1.3 Magic Index
Question

	A magic index in an array A [0 ... n -1] is defined to be an index such that 
	A[i] = i. Given a sorted array of distinct integers, write a method 
	to find a magic index, if one exists, in array A. 

	FOLLOW UP 
	What if the values are not distinct? 

Answer:

	To do //Binary Search?
###1.4 Power Set
Question:

	Write a method to return all subsets of a set. 
Answer:

	Combinational Search, DFS
###1.5 Recursive Multiply
Question:

	Write a recursive function to multiply two positive integers without using the 
	pruduct * operator. You can use addition, subtraction, and bit shifting, but you
	should minimize the number of those operations. 
Answer:

	To do
###1.6 Towers of Hanoi
Question:

	In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of
	different sizes which can slide onto any tower. The puzzle starts with disks sorted 
	in ascending order of size from top to bottom (Le., each disk sits on top of an even
	larger one). You have the following
	constraints:
	(1) Only one disk can be moved at a time.
	(2) A disk is slid off the top of one tower onto another tower.
	(3) A disk cannot be placed on top of a smaller disk.
	Write a program to move the disks from the first tower to the last using stacks. 
Answer:

	To do
###1.7 Permutations without Dups
Question:

	Write a method to compute all permutations of a string of unique characters.
Answer:

	Combinational Search. DFS + back tracking

###1.8 Permutations with Dups
Question:

	Write a method to compute all permutations of a string whose characters are not 
	necessarily unique. The list of permutations should not have duplicates.
Answer:
	
	make an map to count the occurance of the character. such: map['a'] = 2
	Combinational Search. DFS + back tracking when m['char'] > 0
###1.9 Parens
Question:

	Implement an algorithm to print all valid (e.g., properly opened and closed)
	combinations of n pairs of parentheses.
Answer:

	dfs(int k, int n, int left, int right)
    //放置第K个位置的符号，总共需要放置括弧的数目n，到第k个位置时，放置的左括弧的个数left
	和右括弧的个数right
	//判断当前位置是否可以放置左括弧(没限制), 右括弧(right + 1 <= left)
###1.10 Paint Fill
Question:

	Implement the "paint fill" function that one might see on many image editing programs.
	That is, given a screen (represented by a two-dimensional array of colors), a point, 
	and a new color, fill in the surrounding area until the color changes from the 
	original color. 
Answer:

	To do
###1.11 Coins
Question:

	Given an innnite number of quarters (25 cents), dimes (10 cents), nickels (5 cents), 
	and pennies (1 cent), write code to calculate the number of ways of representing 
	n cents.
Answer:

	类似于爬台阶, dp[i]表示凑齐i cents时的种数
	dp[i] = dp[i-1] + dp[i-5] + dp[i-10] + dp[i-25]
	dp[0] = 1; dp[1] = 1;
###1.12 Eight Queens
Question:

	Write an algorithm to print all ways of arranging eight queens on an 8x8 chess 
	board. so that none of them share the same row, column, or diagonal. In this case, 
	"diagonal" means all diagonals, not just the two that bisect the board
Answer:

	8皇后问题，DFS + Back Tracking
	dfs(int k, int n, ...)
	//放到第k行，总共要放n行，更新棋盘的状态，dfs，然后恢复棋盘的状态
###1.13 Stack of Boxes
Question:

	You have a stack of n boxes, with widths Wi ' heights hi ' and depths di . The boxes 
	cannot be rotated and can only be stacked on top of one another if each box in 
	the stack is strictly larger than the box above it in width, height, and depth. 
	Implement a method to compute the height of the tallest possible stack. The height 
	of a stack is the sum of the heights of each box. 
Answer:

	To do
###1.14 Boolean Evaluation
Question:

	Given a boolean expression consisting of the symbols 0 (false), 1 (true), &
	(AND), | (OR), and ^ (XOR), and a desired boolean result value result, 
	implement a function to count the number of ways of parenthesizing the expression 
	such that it evaluates to result. 

	EXAMPLE
	countEval("1^0|0|1", false) -> 2
	countEval("0&0&0&1^1|0", true) -> 10
Answer:

	To do
