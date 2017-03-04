##二叉树
###1. 二叉树遍历
	前序遍历，中序遍历，后序遍历
	0. 层次遍历
	   如何输出每一层？
	1. 递归遍历
	   简单，略
	2. 非递归遍历
	   主要是参考http://zisong.me/post/suan-fa/geng-jian-dan-de-bian-li-er-cha-shu-de-fang-fa
       模拟函数调用栈，主要就是出栈的顺序符合函数调用顺序就可以
	   (1)前序遍历
	   void preOrder(TreeNode* root)
       {
           stack<pair<TreeNode* , bool> > s;
           s.push(make_pair(root, false));
           
           while(!s.empty()){
               TreeNode* cur = s.top().first;
			   bool visited = s.top().second;
               s.pop();
               //到达NULL节点
               if(cur == NULL)continue;
               //待处理节点
               if(visited){
                   cout<<cur->val<<endl;
               }else{
                   //展开此节点调用
                   s.push(make_pair(cur->right, false));
                   s.push(make_pair(cur->left, false));
                   s.push(make_pair(cur, true));
               }
           }
       }
	   (2)中序遍历
	   void InOrder(TreeNode* root)
       {
           stack<pair<TreeNode* , bool> > s;
           s.push(make_pair(root, false));
           
           while(!s.empty()){
               TreeNode* cur = s.top().first;
			   bool visited = s.top().second;
               s.pop();
               if(cur == NULL)continue;

               if(visited){
                   cout<<cur->val<<endl;
               }else{
                   s.push(make_pair(cur->right, false));
                   s.push(make_pair(cur, true));
                   s.push(make_pair(cur->left, false));
               }
           }
       }
	   (3)后序遍历
	   void postOrder(TreeNode* root)
       {
           stack<pair<TreeNode* , bool> > s;
           s.push(make_pair(root, false));
           
           while(!s.empty()){
               TreeNode* cur = s.top().first;
			   bool visited = s.top().second;
			   s.pop();
               if(cur == NULL)continue;

               if(visited){
                   cout<<cur->val<<endl;
               }else{
                   s.push(make_pair(cur, true));
                   s.push(make_pair(cur->right, false));
                   s.push(make_pair(cur->left, false));
               }
           }
       }
	3. Morris Traversal
	   类似于线索二叉树，只不过是single-threaded, 空间复杂度O(1)
       (1)前序遍历
       void preOrder(TreeNode* root){
           TreeNode* cur = root;
           TreeNode* pre = NULL;
           while(cur != NULL){
               if(cur->left == NULL){
                   cout<<cur->val<<endl;
                   cur = cur->right;
               }else{
                   pre = cur->left;
                   while(pre->right != NULL && pre->right != cur)
				      pre = pre->right;
                   if(pre->right == NULL){
                       cout<<cur->val<<endl;
                       pre->right = cur;
                       cur = cur->left;
                   }else{
                       pre->right = NULL;
                       cur = cur->right;
                   }
               }
           }
       }
       (2)中序遍历
       void InOrder(TreeNode* root){
           TreeNode* cur = root;
           TreeNode* pre = NULL;
           while(cur != NULL){
               if(cur->left == NULL){
                   cout<<cur->val<<endl;
                   cur = cur->right;
               }else{
                   pre = cur->left;
                   while(pre->right != NULL && pre->right != cur)
				      pre = pre->right;
                   if(pre->right == NULL){
                       pre->right = cur;
                       cur = cur->left;
                   }else{
                       pre->right = NULL;
                       cout<<cur->val<<endl;
                       cur = cur->right;
                   }
               }
           }
       }
       (3)后序遍历
	   pass

###2. 二叉搜索树(BST)
	1. 数据结构
	   struct TreeNode
	   {
			int val;
    		TreeNode* left;
    		TreeNode* right;
    		TreeNode(int a):val(a), left(NULL), right(NULL){}
	   };

	2. Insert
		TreeNode* insert(TreeNode* root, int target)
		{
    		if(root == NULL) return new TreeNode(target);
    		if(target < root->val){
        	root->left = insert(root->left, target);
    		}
    		else if(target == root->val){
        		root->val = target;
    		}else{
        		root->right = insert(root->right, target);
    		}
    		return root;
		}

	3. Delete
		TreeNode* delete(TreeNode* root, int target)
		{
    		if(root == NULL) return NULL;
    		if(target < root->val) root->left = delete(root->left, target);
    		else if(target > root->val) root->left = delete(root->right, target);
    		else{
        		if(root->left == NULL) return root->right;
        		if(root->right == NULL)return root->left;
        		TreeNode* x = root;
        		x = find_min(root->right);
        		x->right = delMin(root->right);
        		x->left = root->left;
        		root = x;
    	   	}
    		return root;
		}

		int find_min(TreeNode* root)
		{
    		if(root->left == NULL)return root->val;
    		return find_min(root->left);
		}

		TreeNode* delMin(TreeNode* root)
		{
    		if(root->left == NULL) return root->right;
    		root->left = delMin(root->left);
    		return root;
		}

###3. 例子

	1. leetcode 530 Minimum Absolute Difference in BST
	   问题：在BST里，找出节点对最小的差值，输出差值
	2. leetcode 515. Find Largest Value in Each Tree Row
	   问题：在二叉树中，bfs遍历时候，保留最大值即可
	3. leetcode 513. Find Bottom Left Tree Value
	   问题：在二叉树中，bfs遍历，保留第一个元素
	4. leetcode 508. Most Frequent Subtree Sum
	   问题：给定tree的root，找出频次最多的字数和的数值
       思路：用map对sum计数
	5. leetcode 501. Find Mode in Binary Search Tree
	   问题：给定BST，求出频次最高的节点值
	   思路：inorder，O(n)的话用Morris Traversal
	6. leetcode 450. Delete Node in a BST
	   To do问题：删除BST的一个节点
	7. leetcode 449. Serialize and Deserialize BST
	   To do简单方案：#做间隔
	8. leetcode 437. Path Sum III
	   问题：给定一个二叉树，找出和为target的path的数目。path必须是从parent->child，
	   不一定是从root or leaf 出发
	   思路：在tree上做dfs
	9. leetcode 404. Sum of Left Leaves
	   问题：给定二叉树，计算左叶子的和
	10.leetcode 366. Find Leaves of Binary Tree
	   To do 问题：递归的删除叶子节点，直到树空，返回每一轮的节点
	11.leetcode: 333. Largest BST Subtree
	   问题：在BST中，找出节点数最多的subtree
	12.leetcode: 331. Verify Preorder Serialization of a Binary Tree
	   问题：给定先序的序列化字符串，verify是否是合法的序列化
	13.leetcode 314. Binary Tree Vertical Order Traversal
	   问题：vertical order 遍历，from top to bottom, column by column
	14.leetcode 310. Minimum Height Trees
	   问题：给定一个无向图，选择其中一个点做root，使得tree的高度最小，返回所有这样的root
	15.leetcode 298. Binary Tree Longest Consecutive Sequence
	   问题：给定二叉树，找出连续长度最长的路径
	16.leetcode 297. Serialize and Deserialize Binary Tree
	   序列化和反序列化
	17.leetcode 285. Inorder Successor in BST
	   问题：给定一个node，找出他的中序遍历的后序节点
	18.leetcdoe 270. Closest Binary Search Tree Value
	   问题：给定一个target，在bst中找出值和他最近的那个节点并返回
	19.leetcode 261. Graph Valid Tree
	   问题：判断一个图是否是tree
	20.leetcode 257. Binary Tree Paths
	   问题：return all root-to-leaf path
	21.leetcode 255. Verify Preorder Sequence in Binary Search Tree 
	   问题：给定一组数，verify是否是bst的先序遍历的序列
	22.leetcode 236. Lowest Common Ancestor of a Binary Tree
	   问题：给定二叉树，找出两个节点的LCA
	23.leetcode 235. Lowest Common Ancestor of a Binary Search Tree
	   问题：给定bst，找出两个节点的LCA
	24.leetcode 230. Kth Smallest Element in a BST
	   问题：find kth smallest in bst
	25.leetcode 199. Binary Tree Right Side View
	   问题：给定二叉树，返回从右边能看到的值
	26.leetcode 173. Binary Search Tree Iterator
	   问题：实现一个bst的iterator
	27.leetcode 156. Binary Tree Upside Down
	   问题：Given a binary tree where all the right nodes are either leaf nodes 
	   with a sibling (a left node that shares the same parent node) or empty,
	   flip it upside down and turn it into a tree where the original right
	   nodes turned into left leaf nodes. Return the new root.
	28.leetcode 145. Binary Tree Postorder Traversal
	29.leetcode 144. Binary Tree Preorder Traversal
	30.leetcode 129. Sum Root to Leaf Numbers
	   问题：二叉树每个节点值为0-9，find sum of all root-to-leaf number
	31.leetcode 124. Binary Tree Maximum Path Sum
	   问题：路径必须是parent-child关系，可以不是root为起点
	32.leetcode 117. Populating Next Right Pointers in Each Node II
	33.leetcode 116. Populating Next Right Pointers in Each Node
	34.leetcode 114. Flatten Binary Tree to Linked List
	   按照一定顺序拍成链表
	35.leetcode 112. Path Sum，leetcode 113. Path Sum II
	   给定二叉树和target，找出所有root-to-leaf sum 等于target
	36.leetcode 111. Minimum Depth of Binary Tree
	   给定二叉树，找出最小深度的节点所在的深度
	37.leetcode 110. Balanced Binary Tree
	   verify 二叉树是否是height-balanced
	38.leetcode 109. Convert Sorted List to Binary Search Tree
	   有序链表转换成高度平衡的bst
	39.leetcode 108. Convert Sorted Array to Binary Search Tree
	   有序数组装换成高度平衡的bst
	40.leetcode 107. Binary Tree Level Order Traversal II
	   层次遍历，从底向上输出
	41.leetcode 106. Construct Binary Tree from Inorder and Postorder Traversal
	42.leetcode 105. Construct Binary Tree from Preorder and Inorder Traversal
	43.leetcode 104. Maximum Depth of Binary Tree
	   最大深度
	44.leetcode 103. Binary Tree Zigzag Level Order Traversal
	45.leetcode 102. Binary Tree Level Order Traversal
	46.leetcode 101. Symmetric Tree
	   verify tree 是否是对称的
	47.leetcode 100. Same Tree
	   给定两个二叉树，判定是否是同一颗树
	48.leetcode 99. Recover Binary Search Tree
	   两个节点交换了位置，不动树的结构的情况下还原
	49.leetcode 98. Validate Binary Search Tree
	   给定一个二叉树，判断是不是bst
	50.leetcode 96. Unique Binary Search Trees
	   给定n，判断由1-n组成的bst有多少个？
	51.leetcode 95. Unique Binary Search Trees II
	   给定n，返回由1-n组成的bst的root节点
	52.leetcode 94. Binary Tree Inorder Traversal