###Note
1. corner case
    N = 1

2. bachwash
   针对 isFull() 方法会有bug
   当我们建立两个virtual node 的时候，两侧的节点有可能
   是通过底部的virtual node链接起来的，实际上是没有联通的，所以可以
   使用两个uf datatype，一个只建立top的virtual node

   具体参见：http://coursera.cs.princeton.edu/algs4/checklists/percolation.html
