##二分搜索
###1. One-side Binary Search
Question:

	给定以递增序列，但是不知道其长度，查找特定target
Answer:

	以A[1], A[2], A[4], A[8]...的顺序找，找到第一个大于target的数，这样得到一个包含target的区间
	然后在该区间里做顺序或者二分查找