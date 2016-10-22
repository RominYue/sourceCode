##动态规划
###1. 要点

	1. 可以先试着穷举，分治穷举(比如包含A的，和不包含A的), top-down, bottom-up
	2. 定义状态，发现是否有 recursive relation，发现是否有重叠子问题
	3. 状态转移方程
	4. 求值顺序，看看能否优化
	5. 求连续状态的，一般以ith元素结尾的某种状态
	
###2. 计算组合数(二项式系数) (Number Partition)
Question:
	
	从n个数里面随机选取k个数，求方法数

Answer:

	分治穷举：按照k个数是否包含最后一个数，可以将原问题分解为两个子问题：
			 (1)若包含，从前n-1个数中选取k-1个数
             (2)若不包含， 从前n-1个数中选取k个数
	状态转移方程：
		f(n,k) = f(n-1, k-1) + f(n, k-1)

	帕斯卡三角形

###3. String interleaving (String Sequence)
Question:

	Suppose you are given three strings of characters: X, Y , and Z, where |X| = n, |Y | = m, and |Z| = n + m.
	Z is said to be a shuffle of X and Y iff Z can be formed by interleaving the characters from X and Y in a way 
	that maintains the leftto-right ordering of the characters from each string. 
	You should determine whether Z is a shuffle of X and Y .

	Example:
	cchocohilaptes is a shuffle of chocolate and chips, but chocochilatspe is not.
Answer:

	1.状态： f(m,n)表示[1,m],[1,n]可以表示[1, m+n]的一个shuffle
	  考虑Z的最后一个字符，他要么和x的最后一个字符匹配，要么和y的最后一个字符匹配，要么就是false
      状态转移方程：
	  f(i,j) = false
      if (X[i] == Z[i+j]) f(i,j) |= f(i-1, j)
	  if (Y[j] == Z[i+j]) f(i,j) |= f(i, j-1)
      else                f(i,j) = false
      
      边界条件:
      f(0,0) = true
      f(0,j) 计算一遍
      f(i,0) 计算一遍
      
      确定求值顺序，可写迭代版本

###4. Edit Distance (String Sequence)
Question:

	将A串变为B串所需最少的操作数/cost。 允许的操作：
	(1) Insertion
    (2) Deletion
    (3) Substitution

Answer:

	定义状态f(m,n)为将A转化为B所需要的最小操作数
	对于如何到达最后一步操作，可以是Match, Insertion, Deletion, Substitution
	状态转移方程:
	f(i,j) = f(i-1, j-1)            if A[i] == B[j]
		   = min( f(i-1, j-1) + 1,  if A[i] != B[j] //Substitution
                  f(i-1, j) + 1,    //Deletion
                  f(i, j-1) + 1     //Insertion
             )

	struct Node{
		int cost;
		int parent; //存储路径
    }

扩展

	1.Substring Matching
    在B中查找最相似子串A

	2.最长公共子序列(LCS)
    给定字符串A,B,找出他们最长的公共子序列长度/或者打印出来
    Answer: 不做Edit Distance的substitution操作和Insertion操作，状态定义
            f(m,n)表示变成A == B所需要的最小操作数即可，这要保留下来的就是最长的公共子序列
 
 			或者直接重新定义状态：
			f(m,n)表示A串，B串最长的公共子序列长度
            f(i,j) = f(i-1, j-1) + 1   if A[i] == B[j]
                     max(f(i-1, j-1), f(i-1,j), f(i, j-1))   if A[i] != B[j]

###5. 最长上升子序列 (String Sequence, HDU 1087)
Question:

	给定一序列，求其最长上升子序列

Answer:

	1.状态：dp(n)表示以nth 元素结尾的最长上升子序列长度
      状态转移方程: dp[n] = max(dp[j]) + 1 if a[j] < a[n]
      复杂度： 时间 O(n2) 空间O(n)

    2.转化为最长公共子序列问题.
      将序列A排序后的序列记为B，求A和B的LCS
      复杂度： 时间 O(n2) 空间 O(n2)

###6. 拆分序列最小花费 (Array Partition)
Question:

	给定一组序列，同时给定一组切分点的集合，假设一段序列[i,j] 和 切分点为K，k将[i,j]切分成两个子序列所需要的
	花费是j-i+1, 问切分点的集合把这个序列切分成子序列，所需要的最小花费是多少？

Answer:

	1.状态：dp(0,n)表示切分[0,n]所需要的最小花费
      状态转移方程: dp[i,j] = min(dp[i][k] + dp[k+1][j]) + j-i+1, for avaliable k in [i,j]
	  复杂度 时间O(n3)，空间O(n2)

###7. 序列切分 (Array Partition)
Question:

	Array切分成m个subarray，尽量使得subarray中的最大值尽可能的小
Answer:

	1.状态： dp(n,m)表示序列[0,n]切分成m个部分是，min(max(subarray[k] for k in [1,m]))的值最小
      状态转移方程: dp[i,j] = min(max(dp[k,j-1], sum[k+1,j]), dp[i,j]) for k in [0, i)
	  复杂度：时间 O(n2*m), 空间 O(n*m)
	  优化：dp[n,k]当n fixed的时候，是随着K单调递增的，sum[k+1,j]是单调递减的，到达拐点即可break，剪枝

 	2.二分
      每个subarray和的下界是sum[0,n]/m, 上界是sum[0,n]，二分搜索上下界，
      判断一个结果是否能够将数组切分，需要的时间是O(n),总的时间复杂度为O(n*log(sum[0,n]))

###8. 智趣题
Question:

	给定n层楼，m个鸡蛋，最少投掷多少次鸡蛋，即可确定在某一层向下投掷鸡蛋，鸡蛋会碎？

Answer:

	pass

###9. Robberies (HDU 2955, HDU 1864, HDU 2602)
Question:

	有n家银行，对于ith 银行，存有Mi million的钱，同时如果抢劫这家银行，那么被抓的概率为Pi.
    现在Bob想要抢劫这N家银行，只要他被抓的概率 <= P ,则认为他是安全的。现在给定P，求问最多能抢的钱是多少？
Answer:

	类似于01背包
	1.状态: f(n,m)表示抢劫完前n家店，抢劫的金额为m的时候最大的不被抓的概率
	2.状态转移: 考虑最后一家店，若未抢劫, f(n,m) = f(n-1,m)
                            若抢劫，  f(n,m) = f(n-1, m - a[n])*(1-prob[n])
	3.边界条件: f(0,0) = 1, f(0,i) = 0
	4.滚动数组优化，背包优化
	5.倒叙遍历m,找到第一个大于等于(1-P)的值即可

###10. 最大连续子段和
Question:

	最大连续子段和，输出第一个区间
Answer:

	1.状态： dp(i)表示以ith 元素结尾的最大连续子段和
    2.状态转移： 考虑dp(i+1)的状态，如果 dp(i) + a[i+1] < a[i+1], dp(i+1) = a[i]
								 否则 dp(i+1) = dp(i) + a[i+1]
    3.边界条件：dp(1) = a[1]
    4.遍历取最大值即可

###11. To the Max (二维最大连续子段和) (HDU 1801)
Question:

	给定二维数组，求和最大的子矩形
Answer:

	1.分析：对于第i排数字，可能的子矩阵，来自于 jth - ith rows (j < i)组成的矩形。这样就变成了
		   将 j-ith row压缩成一维，然后求其最大连续子段和，这样，扫完每一排，就可以得到以这一排为底
		   的最大值，然后取大者即可。
	2.时间复杂度：O(n3) 

###12. Largest Rectangle in a Histogram (最长连续递增or递减子段) (HDU 1506)
Question:

	给定一维数组，代表等宽(宽度为1)的矩形的高，求可以围成的最大的矩形面积
Answer:

	1.分析：对于特定的第i个柱子，统计左边比他高的连续柱子个数，统计右边比他高的连续柱子的个数即可
	2.状态: l[i] = l[i-1] + 1 if a[i] < a[i-1] else 1
          	r[i] = r[i+1] + 1 if a[i] < a[i+1] else 1
    3.边界条件: l[1] = 1, r[1] = 1 

###13. City Game (矩阵中全为1的矩形的最大面积 HDU 1505)
Question:

	给定一矩形，矩形元素为0或1，求全为1的最大矩形的面积
Answer:

	1.分析：考察第(i,j)个元素，如果统计以它为底，向上连续的1作为矩形的高，那么就变成了和上一题一样
		   的情况了，只不过需要预先统计up[i][j],然后统计left[i][j], right[i][j]

###14. 命运 (HDU 2571，类似数塔)
Question:
	
	给定一矩形m*n,矩形的每一格有权值，可正可负。
	走路规则如下：
	(1)可向下或者向右走
	(2)向下只能走1格，向右可以走一格，或者走到k的倍数格，k为当前列数	
	现在小明从(0,0)出发，走到(m,n)，求可以获得的最大权值？

Answer:

	1.状态：f(i,j)表示小明走到(i,j)的位置时，可以获得的最大权值
	2.状态转移：考虑第(i,j)个格子，可以从(i-1,j) => (i,j), 那么 f(i,j) = max(f(i,j), f(i-1,j))
			   可以从(i,k) => (i,j) if k == j-1 or k能整除j for k in [1..j-1]
			   f(i,j) = max(f(i,j-1),f(i,k))
               再取两者的max值
	3.边界条件:第一行特殊处理，第一列特殊处理

###15. Fate (HDU 2159, 背包)
Question:

	to do

Answer:

	to do
	
	