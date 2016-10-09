##Chapter 5 Bit Manipulation
###1.1 Insertion
Question:

	You are given two 32-bit numbers, Nand M, and two bit positions, i and j. 
	Write a method to insert Minto N such that M starts at bit j and ends at bit i. 
	You can assume that the bits j through i have enough space to fit all of M. 
	That is, if M = 10011, you can assume that there are at least 5 bits between j and i.
	You would not, for example, have j = 3 and i = 2, because M could not fully fit 
	between bit 3 and bit 2.

	EXAMPLE:
	Input: N = 10000000000, M = 10101, i = 2, j = 6
	Output: N = 10001010100
Answer:

	int insert(int m, int n, int i, int j){
		int ret = n;
		for(int pos = i; pos <= j; pos++){
			ret = update(ret, i, getBit(m, pos - i));
		}
		return ret;
	}

	//k == 0 or 1
	int updateBit(int num, int i, int k){
		int mask = ~(1 << i);
		return (num & mask) | (k << i);
	}

	int getBit(int num, int i){
		return num & (1 << i);
	}
###1.2 Binary to String
Question:

	Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, 
	print the binary representation. If the number cannot be represented accurately 
	in binary with at most 32 characters, print "ERROR"
Answer:

	string doubleToBinary(double x){
		//your code here
	}
###1.3 Flip Bit to Win
Question:

	You have an integer and you can flip exactly one bit from a 13 to a 1. Write code to
	find the length of the longest sequence of ls you could create. 

	EXAMPLE
	Input: 1775 (or: 1110111101111)
	Output: 8
Answer:

	从低位向高位扫面，统计连续1的个数。对于每一个0，维护pre值(pre表示在0前面的连续1的个数)
	和维护后面连续1的个数，一路扫描取最大值即可
###1.4 Next Number
Question:

	Given a positive integer, print the next smallest and the next largest number that
	have the same number of 1 bits in their binary representation.
Answer:

	To do
###1.5 Debugger
Question:

	Explain what the following code does: ((n & (n-1)) == 0)
Answer:

	1. n&(n-1) 把 n 的最后一个1清除
	2. n&(n-1) == 0 判断n是否是2的整数次幂
###1.6 Conversion
Question:

	Write a function to determine the number of bits you would need to flip to convert
	integer A to integer B.

	EXAMPLE
	Input: 29 (or: 111101), 15 (or: 101111)
	Output: 2
Answer:

	To do
###1.7 Pairwise Swap
Question:

	Write a program to swap odd and even bits in an integer with as few instructions 
	as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, etc).
Answer:

	To do
###1.8 Draw Line
Question:

	A monochrome screen is stored as a single array of bytes, allowing eight consecutive
	pixels to be stored in one byte. The screen has width w, where w is divisible by 8
	(that is, no byte will be split across rows). The height of the screen, of course, 
	can be derived from the length of the array and the width. Implement a function
	that draws a horizontal line from (xl, y) to (x2, y). The method signature should 
	look something like:
	drawLine(byte[] screen, int width, int xl, int x2, int y)
Answer:

	To do
###1.9 Find the missing number (Fifth Edition)
Question:

	An array A[1...n] contains all the integers from 0 to n except for one number 
	which is missing. In this problem, we cannot access an entire integer in A with
	a single operation. The elements of A are represented in binary, and the only 
	operation we can use to access them is “fetch the jth bit of A[i]”, which takes
	constant time. Write code to find the missing integer. Can you do it in O(n) time?
Answer:

	To do