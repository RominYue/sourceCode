##拓扑排序(TopoSort)
###一、定义：给定一组偏序关系，给出全局的偏序关系
拓扑排序的几种情况：

	(1)没有拓扑排序，说明有向图中有环
	(2)有唯一的拓扑排序
    (3)有多组拓扑排序
###解法1：基于栈的清除入度为0的点
算法步骤：

	(1) 建图 (注意：在插入(u,v)时，考虑重边，自环)
	(2) 统计入度为0点，入栈
	(3) 当栈不为空，pop栈顶，扫栈顶相邻的边，入度减一，减至0入栈, 直至栈空
    (4) 如何判断拓扑排序结果是上述3种情况的哪一种？
		a. 维护全局变量cnt，表示顶点个数，若图无环，则顶点入栈一次，出栈一次
		   每次出栈cnt--, 若最后cnt != 0, 则说明有环
		b. 考察每个状态下栈中元素个数，若有唯一的拓扑排序，则任意时刻栈中元素
           有且仅有1个。依据此判断是否是唯一

算法模板：

	    //toposort
        stack<int> s;
        int cnt = ind.size();
        for(auto iter = ind.begin(); iter != ind.end(); iter++)
        {
            int u = iter->first;
            int indegree = iter->second;
            if(indegree == 0)s.push(u);
        }
        bool flag = true; //check is unique or not
        vector<int> ans;
        while(!s.empty())
        {
            if(s.size() > 1){
                flag = false;
                break;
            }
            int u = s.top();s.pop();
            ans.push_back(u);
            cnt--;
            for(auto iter = g[u].begin(); iter != g[u].end(); iter++)
            {
                int v = *iter;
                ind[v]--;
                if(ind[v] == 0)s.push(v);
            }
        }

		if(cnt != 0) 有环
###解法2：基于dfs
算法步骤:

	算法原理：在dfs过程中，u -> v, 若v未访问过，则能表明u在v前面；若v正在被访问，
			 说明是后向边，有环；若v已访问完成，则此边是前向边或者是横向边。
	(1)如何判断是否有环？
       添加flag标记，当出现后向边的时候，即可判断有环
	(2)如何判断是否唯一？

算法模板：

	    stack<int> ans;
		int vis[n];
		//vis[i] == 0 not yet visit
        //vis[i] == 1 on stack
        //vis[i] == 2 has been over
        bool flag = false; //检测是否有环
        for(int i = 0; i < n; i++)
        {
            if(!vis[i])dfs(i, flag, vis, ans);
        }

		void dfs(int i, bool& flag, int vis[], stack<int>& ans)
    	{
        	vis[i] = 1;
        	for(v: i's adjcent node)
        	{
            	if(vis[v] == 1){
                	flag = true;
                	return;
            	}
            	if(vis[v] == 0)dfs(v, flag, vis, ans);
        	}
        	vis[i] = 2;
        	ans.push(i);
    	}

		//get toposort
		逆序stack中的数即可

		
###二、例题
####Leetcode 207, 210 	Course Schedule(I,II)
题目大意：

	给定一些选修课的依赖关系，求是否可以拓扑排序，如果可以，给出拓扑排序链

####Leetcode 444: Sequence Reconstruction
题目大意：
	
	给定orignal 数组(比如[1,2,3]), 给定一些subseqs数组(比如[1,2],[1,3])
	问这些subseqs数组能否唯一的生成original数组，且数组内序关系不变？

思路：

	将subseqs数组建图，看能否给出唯一的拓扑排序链，看这条链和original数组是否相等

####Leetcode 269: 	Alien Dictionary
题目大意:

	给定一组排序好的字符串，问能不能找出其中字符的排序顺序？

思路：

	第 ith 字符串和第 i-1th 字符串比较，将第一个不相同字符建边，看看能否拓扑排序
	第 0th 字符串特殊处理
	