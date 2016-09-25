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

int num[3100][3100];
int cnt[3100];
int dp[3100][3100];

int main()
{
    int t,m,n,k;
    freopen("data.in","r", stdin);
    freopen("data.out","w", stdout);
    cin >> t;
    for(int cs = 1; cs <= t; cs++)
    {
        memset(num,0,sizeof(num));
        memset(cnt,0,sizeof(cnt));
        memset(dp,0,sizeof(dp));

        scanf("%d %d %d",&m,&n,&k);

        for(int i = 0; i < k; i++)
        {
            int x,y;
            scanf("%d %d",&x,&y);
            num[x][y] = 1;
        }

        for(int j = 0; j < n; j++)dp[0][j] = num[0][j] == 1 ? 0:1;

        for(int i = 0; i < m; i++)dp[i][0] = num[i][0] == 1 ? 0:1;

        for(int i = 1; i < m; i++)
        {
            for(int j = 1; j < n; j++)
            {
                if(num[i][j] == 1)dp[i][j] = 0;
                else
                {
                    dp[i][j] = min(dp[i-1][j],dp[i][j-1]);
                    dp[i][j] = min(dp[i][j], dp[i-1][j-1]) + 1;
                }
            }
        }

        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(dp[i][j])cnt[dp[i][j]]++;

        int min_val = min(m,n);
        long long ans = 0;
        for(int i = 1; i <= min_val; i++)
        {
            ans += (long long)cnt[i] * (long long)i;
            //cout<<i<< " "<<cnt[i]<<endl;
        }
        cout<<"Case #"<<cs<<": "<<ans<<endl;
    }
    return 0;
}



