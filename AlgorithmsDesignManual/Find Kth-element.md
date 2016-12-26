##Find K-th Element In An Array
###1. 找出最大元素
	线性扫描一遍维护最大值即可
###2. 找出第二大元素
	维护两个变量 first, second. first表示第一大元素， second表示第二大元素
	
	init: first = second = INT_MIN
	对于当前第i-th元素:
	if nums[i] > first:
		//更新第一大元素，第二大元素
		second = first
		first = second
	else if nums[i] == first:
		//此处考虑相等参与排序
		continue
	else if nums[i] > second:
		//更新第二大元素
		second = nums[i]
###3. 找出第三大元素
	同#2差不多，需要维护三个变量: first, second, third
	
	判断条件变多，先判断当前元素与first的关系，再判断与second的关系
	再判断与third的关系, 更新变量的值

###4. 找出第K大元素
	(1) 排序
	    时间复杂度O(nlgn), 空间复杂度O(nlgn)

	(2) 堆
		priority_queue<int> pq; //大顶堆
		priority_queue<int, vector<int>, greater<int> > pq;

		第K大元素，建立size为K的大顶堆即可
		时间复杂度O(nlgk), 空间复杂度O(n)

	(3) QuickSelect
		算法描述：
		挑选pivot，将小于pivot的数放在左边，大于pivot的数放在右边
		判断左边的个数是否小于K，依次递归select
		<1> 平均O(n), worstest case O(n2)
			int partition(int a[], int l, int r)
			{
				int x = a[l];
				int i = l;
				for(int j = l; j <= r; j++)
				{
					if(a[j] <= x){
						swap(a[i], a[j]);
						i++;
					}
				}
				i--;
				swap(a[l], a[i]);
				return i;
			}

			int findKth(int a[], int l, int r, int k){
				if(k > 0 && k <= r - l + 1){
					int pos = partition(a, l, r);
					if(pos - l + 1== k) return a[pos];
					else if(pos - l + 1 > k) return findKth(a, l, pos-1, k);
					else return findKth(a, pos+1, r, k-(pos-l+1));
				}
				return INT_MAX;
			}

		<2> 期望时间复杂度O(n)
			a. random 选择数组中的一个数，放在开头或者末尾作为pivot
			b. 选取开头，中间，尾部三者median作为pivot

		<3> Worstest Case O(n)
			a. 将array按照5个元素分组
			b. 每组求median，多组再求个median
			c. 将这个median作为pivot进行partition

###5. 找出median也可以看做是查找第K大数

###6. 习题:
	leetcode 215, 347, 414
						
		