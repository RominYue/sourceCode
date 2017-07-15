##Sorting cheat sheet

###基础知识

	1.selection sort
	  每次选出最小值，放在当前位置
	  
      void selection_sort(vector<int>& a, int n){
          //确定i位置的min
          for(int i = 0; i < n; i++){
              int min_val = a[i];
              int min_pos = i;
              for(int j = i+1; j < n; j++){
                  if(a[j] < min_val)
                  {
                       min_val = a[j];
                       min_pos = j;
                  }
              }
              swap(a[i], a[min_pos]);
          }
      }
	2.insertion sort
	  对于当前元素i，查找[0~i-1]的插入位置，插入，然后相应元素后移
      
      void insert_sort(vector<int>& a, int n){
          for(int i = 1; i < n; i++){
              int tmp = a[i];
              int pos = 0;
              for(int j=i-1; j >= 0; j--)
              {
                  if(a[j] > tmp){
                      a[j+1] = a[j];
                  }else{
                      pos = j;
                      break;
                  }
              }
              a[pos] = tmp;
          }
      }
    3.bubble sort
    将大元素逐一浮到数组的最后
    void bubble_sort(vector<int>& a, int n){
        //n-1轮
        for(int i = 1; i < n; i++)
        {
            for(int j = 1; j < n; j++)
            {
                  if(a[j-1] > a[j])
                      swap(a[j-1], a[j]]);
            }
        }
    }
	4.merge sort
	  (1) array -> 2 subarrayy
	  (2) sort subarray
	  (3) merge 2 sorted subarray
      void merge_sort(vector<int>& a, int n, int l, int r){
          if(l >= r) return;
          int mid = (l + r) / 2;
          merge_sort(a, n, l, mid);
          merge_sort(a, n, mid+1, r);
          merge_sorted_array(a, l, r);
      }
      void merge_sorted_array(vector<int>& a, int l, inr r){
	      vector<int> tmp = a;
          int mid = (l + r)/2;
          int i = l, j = mid + 1, k = l;
          while(i <= mid || j <= r){
              if(i <= mid && j <= r){
                  if(tmp[i] < tmp[j]){
                      a[k++] = tmp[i++];
                  }else{
                      a[k++] = tmp[j++];
                  }
              }else if(i <= mid){
                  a[k++] = tmp[i++];
              }else{
                  a[k++] = tmp[j++];
              }
          }
      }
      //non-recursive
      void merge_sort(vector<int>& a, int n){
          for(int sz = 1; sz < n; sz += sz * 2)
          {
              for(int l = 0; l < n; l += sz * 2)
              {
                  merge_sorted_array(a, l, min(l + size*2-1, n-1));
              }
          }
      }
	5.quick sort
	  (1) partition
          可以采用辅助数组将小于pivot的元素移到左边，大于pivot的元素移到右边
          或者，采用两个指针，同时扫描，遇到不符合的互相交换
	  (2) sort 2 subarray
      void quick_sort(vector<int>& a, int n, int l, int r)
      {
	      if(l >= r) return;
          int pos = partition(a, l, r);
          quick_sort(a, n, l, pos-1);
          quick_sort(a, n, pos+1, r);
      }

      int partition(vector<int>& a, int l, int r)
      {
           int i = l+1, j = r;
           int v = a[l];
           while(i < j){
                while(a[i] <= v){
                    if(i == r)break;
                    i++;
                }
                while(a[j] > v){
                    if(j == l)break;
                    j--;
                }
                swap(a[i], a[j]);
          }
          swap(a[l], a[j]);
          return j;
      }

	6.heap sort
      heap: 对于任意一个节点，都比他的左节点和右节点大
      用数组，完全二叉树实现
      heap的操作
      (1)建堆
      (2)上浮
      (3)下沉
      (4)插入
      (5)删除
      

      heap sort
	  (1) make heap
	  (2) get heap top item -> delete -> adjust -> get...

    class Heap{
	//大顶堆
	public:

    Heap(){}
    Heap(int a): n(a){}
    Heap(int *a, int n){
        this->n = n;
        for(int i = 1; i <= n; i++)
        {
            this->pq[i] = a[i];
        }
    }

    //插入到最后，然后上浮
    void insert(int x){
        pq[++n] = x;
        swim(n);
    }

    //将最后一个元素放到第一个位置，下沉
    int delMax(){
        int val = pq[1];
        pq[1] = pq[n--];
        sink(1);
        return val;
    }

    //上浮操作，上浮第k个元素，层层比较k, k/2的大小关系
    void swim(int k){
        while(k > 1 && pq[k/2] < pq[k]){
            swap(pq[k], pq[k/2]);
            k = k/2;
        }
    }
    //下沉操作，下沉第k个元素
    //大顶堆，与两个孩子节点中较大的那个比较，是否交换
    //小顶堆，与两个孩子中较小的比较，是否交换
    void sink(int k){
        while(k*2 <= n){
            int j = 2*k;
            if(j+1 <= n && pq[j] < pq[j+1]) j = j+1;
            if(pq[j] < pq[k])break;
            swap(pq[j], pq[k]);
            k = j;
        }
    }

    //建堆，可以一直执行插入操作
    //或者，对于非叶子的每个节点，执行下沉操作
    void build_heap(){
        for(int k = n/2; k >= 1; k--){
            sink(k);
        }
    }

    //堆排序，先建堆
    //然后每次讲堆顶放入末尾，末尾元素放到堆顶，堆的size-1，调整堆
    //调整堆，下沉堆顶
    void heap_sort(){
        build_heap();
        while(n > 1){
            swap(pq[1], pq[n]);
            n--;
            sink(1);
        }
    }
	private:
    	const static int maxn = 10;
    	//n is the heap size
    	int n;
    	//a[0] is not used
    	int pq[maxn];
	};



	7.radix sort

###分析各算法的时间复杂度，空间复杂度， 排序的稳定性

	1. 每个sorting算法的 worst case, best case, 比较次数？
	2. 数据的分布对算法有什么影响？
	3. 重复的key值对算法有什么影响？
	4. 各排序的稳定性如何？
	   选择排序、快速排序、希尔排序、堆排序不是稳定的排序算法，而冒泡排序、插入排序、归并排序和基数排序是稳定的排序算法

###Imporvments

	1.MergeSort 的递归非递归写法？
	2.MergeSort 有哪一些可以改进的地方？改进的原因是啥？
    3.QuickSort 的递归非递归写法？
    4.QuickSort 有哪一些可以改进的地方？



###Bonus
	1.Knuth Shuffle
	  均匀随机生成数组的一个排列
	  (1)如何对array做 knuth shuffle
	  (2)如何对linked list 做 knuth shuffle
	2.partition (quick sort)
	  (1)求数组的第k大数(O(n))