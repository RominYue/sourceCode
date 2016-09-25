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

char num[30][30];
int vis[30][30];
double cal[30][30];

int dir[4][2] = {{1,0},{-1,0},{0,1},{0,-1}};
double ans;
int t,m,n,s_i,e_i,s;
double p,q;

bool check(int x, int y)
{
    if(x >= 0 && x< m && y >= 0 && y < n)return true;
    return false;
}

void dfs(int x, int y, int step, double val)
{
    //cout<<x<<" "<<y<<" "<<step<<" "<< val<<endl;
    if(step == s)
    {
        ans = max(ans, val);
        //cout<<endl;
        return;
    }

    for(int i = 0; i < 4; i++)
    {
        int next_x = x + dir[i][0];
        int next_y = y + dir[i][1];
        if(check(next_x,next_y))
        {
            double tmp = 0.0;
            if(num[next_x][next_y] == '.')tmp = q;
            else tmp = p;

            vis[next_x][next_y] += 1;
            dfs(next_x, next_y, step + 1, val + pow(1-tmp,vis[next_x][next_y]-1)*tmp);
            vis[next_x][next_y] -= 1;
        }
    }
}

int main()
{
    freopen("data.in","r", stdin);
    freopen("data.out","w", stdout);
    cin >> t;
    for(int cs = 1; cs <= t; cs++)
    {
        memset(num,0,sizeof(num));
        memset(vis,0,sizeof(vis));
        for(int i = 0; i < 30; i++)
            for(int j = 0; j < 30; j++)
                cal[i][j] = 1.0;

        ans = 0;
        scanf("%d %d %d %d %d", &m, &n, &s_i, &e_i, &s);
        cin >>p >> q;

        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                cin >> num[i][j];
            }
        }
        //cout<<s_i<<" "<<e_i<<endl;

        dfs(s_i,e_i,0,0.0);
        printf("Case #%d: %.7f\n",cs,ans);
    }
    return 0;
}



