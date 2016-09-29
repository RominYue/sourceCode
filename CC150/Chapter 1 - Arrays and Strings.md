##Chapter 1 | Arrays and Strings
###1.1 Is Unique
Quesiton:

	Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
Answer:

	1. use hashtable, 时间复杂度O(n)
	2. 不借助其他数据结构的话，先排序，再比较, 时间复杂度O(nlgn)
###1.2 Check Permutation
Question:

	 Given two strings, write a method to decide if one is a permutation of the other.
Answer:

	对两者排序，看是否相等，时间复杂度O(nlgn)
###1.3 URLify
Question:

	Write a method to replace all spaces in a string with '%20: You may assume that the string has sufficient space at the end 
	to hold the additional characters, and that you are given the "true" length of the string. (Note: If implementing in Java, 
	please use a character array so that you can perform this operation in place.)

	EXAMPLE
	Input:  "Mr John Smith     " 13
	Output: "Mr%20J ohn%20Smith"
Answer:

	从后向前扫描字符串
		if(char is not space) assign it at the end
        if(char is space) replace with %20, and assign it at the end
    Note: 如果后面没有留足够大的空间，我们可以从前向后存储字符，最后做一个reverse操作
###1.4 Palindrome Permutation
Question:

	Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or phrase 
	that is the same forwards and backwards. A permutation is a rearrangement of letters.The palindrome 
	does not need to be limited to just dictionary words.
	
	EXAMPLE
	Input: Tact Coa
	Output: True (permutations: "taco cat". "atco cta". etc.)

Answer:

	只需判断string是否能构造出一个Palindrome即可。利用Hashtable判断字符出现的次数
	要么都为双数，要么只有一个是奇数且为1
###1.5 One Away
Question:

	There are three types of edits that can be performed on strings: insert a character, remove a character, or replace 
	a character. Given two strings, write a function to check if they are one edit (or zero edits) away.

	pale, pIe -> true
	pales. pale -> true
	pale. bale -> true
	pale. bake -> false

Answer:

	leetcode 161
###1.6 String Compression
Question:

	Implement a method to perform basic string compression using the counts of repeated characters. For example, 
	the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original string, 
	your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).
Answer:

	模拟压缩字符串，比较压缩后的字符串和之前的长短，取短者
###1.7 Rotate Matrix
Question:

	Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, 
	write a method to rotate the image by 90 degrees. can you do this in place?
Answer:

	leetcode 48
###1.8 Zero Matrix
Question:

	Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to O.
Answer:

	两个int型变量，位表示行和列是否为0，two-pass置为0即可
###1.9 String Rotation
Question:

	Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2, 
	write code to check if s2 is a rotation of s1 using only one call to isSubstring 
	(e.g.,"waterbottle" is a rotation of"erbottlewat").
Answer:

	s1 + s1 "waterbottlewaterbottle" 必定包含所有s1的旋转字符串，所以只需调用一次isSubstring即可
###1.10 Revserse String (Fifth Edition)
Question:

	Write code to reverse a C-Style String (C-String means that “abcd” is represented as	 five characters, 
	including the null character )
Answer:

	纯模拟即可
###1.11 Remove Duplicate Letters (Fifth Edition)
Question:

	Design an algorithm and write code to remove the duplicate characters in a string without using any additional buffer. 
	NOTE: One or two additional variables are fine. An extra copy of the array is not allowed
	FOLLOW UP
	Write the test cases for this method
Answer:

	1.不允许使用额外空间的话，对于当前未处理字符，查看之前有无出现，出现，remove并且指针后移，时间复杂度O(n^2)
	2.允许使用小数组 or int变量可以位表示，hashtable的思想，时间复杂度O(n)
