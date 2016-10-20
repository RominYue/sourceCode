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
������01����
1.״̬: f(n,m)��ʾ������ǰn�ҵ꣬���ٵĽ��Ϊm��ʱ�����Ĳ���ץ�ĸ���
2.״̬ת��: �������һ�ҵ꣬��δ����, f(n,m) = f(n-1,m)
                            �����٣�  f(n,m) = f(n-1, m - a[n])*(1-prob[n])
3.�߽�����: f(0,0) = 1, f(0,i) = 0

4.���������Ż��������Ż�
*/
int main()
{
    freopen("data.in","r", stdin);
    //freopen("data.out","w", stdout);

    int t;
    while(cin >> t)
    {
        double p;
        int n;

        for(int cs = 1; cs <= t; cs++)
        {
            cin >> p >> n;

            int money[n];
            double prob[n];

            int total = 0;
            for(int i = 1; i <= n; i++)
            {
                cin >> money[i] >> prob[i];
                total += money[i];
            }
            //cout<<"total: " << total<<endl;

            double dp[2][total + 1];
            memset(dp,0,sizeof(dp));
            dp[0][0] = 1;

            for(int i = 1; i <= n; i++)
            {
                for(int j = 0; j <= total; j++)
                {
                    dp[i%2][j] = dp[(i-1)%2][j];
                    if(j >= money[i])dp[i%2][j] = max(dp[i%2][j],dp[(i-1)%2][j-money[i]]*(1 - prob[i]));
                }
            }

            double ans = 0;
            for(int j = total; j >= 0; j--)
            {
                if(dp[n%2][j] >= 1 - p)
                {
                    ans = j;
                    break;
                }
            }

            cout<<ans<<endl;
        }
    }

    return 0;
}
