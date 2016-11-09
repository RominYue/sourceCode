##Sorting cheat sheet
###基础知识
	1.selection sort
	  每次选出最小值，放在当前位置
	2.insertion sort
	  对于当前元素i，查找[0~i-1]的插入位置，插入，然后相应元素后移
	3.merge sort
	  (1) array -> 2 subarrayy
	  (2) sort subarray
	  (3) merge 2 sorted subarray
	4.quick sort
	  (1) partition
	  (2) sort 2 subarray
	5.heap sort
	  (1) make heap
	  (2) get heap top item -> delete -> adjust -> get...
	6.radix sort

###分析各算法的时间复杂度，空间复杂度， 排序的稳定性
	1. 每个sorting算法的 worst case, best case, 比较次数？
	2. 数据的分布对算法有什么影响？
	3. 重复的key值对算法有什么影响？
	4. 各排序的稳定性如何？

###Imporvments
	1.MergeSort 的递归非递归写法？
	2.MergeSort 有哪一些可以改进的地方？改进的原因是啥？
    3.QuickSort 的递归非递归写法？
    4.QuickSort 有哪一些



###Bonus
	1.Knuth Shuffle
	  均匀随机生成数组的一个排列
	  (1)如何对array做 knuth shuffle
	  (2)如何对linked list 做 knuth shuffle
	2.partition (quick sort)
	  (1)求数组的第k大数(O(n))