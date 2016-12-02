###数论
####素数筛法［1，1e6］
时间复杂度分析参考[这里](https://www.careercup.com/question?id=8044017)

	//先认为所有的数都是素数
	//用每个素数的倍数筛一遍
	//时间复杂度是O(n*logn*logn)
	void Prime(){
    	cnt = 0;
    	memset(flag, true, sizeof(flag));
    	flag[0] = flag[1] = false;
    	for(int i = 2; i <= maxn; i++){
        	if(!flag[i])continue;
        	prime[cnt++]=i;
        	for(int j = 2; j*i <= maxn; j++)
            	flag[i*j]=false;
    	}
	}
####区间筛素数［1，1e9］
	//有时候范围给的比较大，但是会询问区间内大信息，区间不大
	//这个时候需要做二次筛法
	//判断一个数是否是素数，判断小于根号n的素数能否整除它
	//用这一些素数的倍数筛掉区间的合数，剩下的就是素数了
	//时间复杂度O(n*logn*logn), n是区间长度
	void getIntervalPrime(int l, int r)
	{
    	memset(isprime, true, sizeof(isprime));
    	//当区间左端点是1，可以变成2
    	if(l == 1) l = 2;
    	for(int i = 0; i < cnt && (LL)prime[i]*prime[i] <= r; i++)
    	{
        	int s = l / prime[i];
        	if(s * prime[i] < l)s++;
        	//找到prime的最小倍数，但是必须>=2才是合数
        	if(s == 1)s = 2;

        	for(int j = s; (LL)j*prime[i] <= r; j++)
        	{
            	if((LL)j*prime[i] >= l)
                	isprime[j*prime[i]-l] = false;
        	}
    	}
	}