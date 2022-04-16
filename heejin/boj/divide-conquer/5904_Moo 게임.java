import java.io.*;
import java.util.*;

// BOJ / Moo ���� / S1 / 70��
// https://www.acmicpc.net/problem/5904
public class Main_5904 {
	static int N;
	static int dp[]; // Moo ������ ����
	static char res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[50];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 3;
		int k = 0;
		while (dp[k] <= N) { // Moo ���� ���� ���ϱ�
			k++;
			dp[k] = dp[k - 1] * 2 + (k + 2) + 1;
		}
		Moo(N);
		System.out.println(res);

	}

	private static void Moo(int n) {
		if (n == 1) {
			res = 'm';
			return;
		}
		if (n == 2 || n == 3) {
			res = 'o';
			return;
		}

		int k = 0;

		while (dp[k] < n)
			k++;

		int mid = dp[k - 1] + k + 3; // S(k)= S(k-1)+ moo.. + S(k-1)���� �߰� moo... ���� o�ڸ�
		if (mid < n) // dp[k-1]���� ó��
			Moo(n - mid);
		else if (n == dp[k - 1] + 1)
			res = 'm';
		else
			res = 'o';

	}
}
