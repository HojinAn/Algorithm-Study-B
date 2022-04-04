import java.io.*;
import java.util.*;

// BOJ / ������ �� / S1 / 30��
// https://www.acmicpc.net/problem/11057
// �� �������� res�� ���� ���� %10007 ����� ��
public class Main_11057 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long dp[][] = new long[N+1][10]; //n�ڸ����� ������ 0~9�� ���
		

		for(int i=0;i<10;i++) {
			Arrays.fill(dp[0], 0);
			Arrays.fill(dp[1], 1);
		}
			
		
		for(int i=2;i<=N;i++) {
			for(int j=0;j<10;j++) {
				for(int k=0;k<=j;k++)
					dp[i][j] += dp[i-1][k]%10007;
				dp[i][j] %=10007;
			}
		}
		int res=0;
		for(int i=0;i<10;i++)
			res += dp[N][i]%10007;
		System.out.println(res%10007);
		
	}
}
