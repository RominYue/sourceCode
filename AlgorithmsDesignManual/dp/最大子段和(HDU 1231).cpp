#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#include <set>
#include <map>
#include <stack>
#include <algorithm>
#include <cctype>
#include <unordered_map>
#include <unordered_set>
#include <string>
#include <cstdio>

using namespace std;
/*
1.状态: dp(i)表示以i结尾的最大字段和
*/

const int maxn = 1e6 + 10;

int main()
{
    typedef long long LL;
    freopen("data.in","r", stdin);
    //freopen("data.out","w", stdout);

    int t;
    while(scanf("%d", &t) != EOF)
    {
        if(t == 0)break;
        int a[t+1],p[t+1];
        LL dp[t+1];
        memset(a,0,sizeof(a));

        for(int i = 1; i <= t; i++)
            scanf("%d",&a[i]);

        bool allNeg = true;
        for(int i = 1; i <= t; i++)
            if(a[i] >= 0)allNeg = false;

        if(allNeg)
        {
            cout<<0<<" "<<a[1]<<" "<<a[t]<<endl;
            continue;
        }

        for(int i = 0; i <= t; i++)p[i] = i;

        LL ans;
        int pos;

        dp[1] = a[1];
        ans = dp[1];
        pos = 1;

        for(int i = 2; i <= t; i++)
        {
            if(dp[i-1] < 0)
            {
                dp[i] = a[i];
                p[i] = i;
            }else{
                dp[i] = dp[i-1] + a[i];
                p[i] = i-1;
            }

            if(dp[i] > ans){
                ans = dp[i];
                pos = i;
            }
        }

        int end = pos;
        while(p[pos] != pos) pos = p[pos];
        cout<<ans<<" "<<a[pos]<<" "<<a[end]<<endl;

    }

    return 0;
}
