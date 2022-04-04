import java.io.*;

// BOJ / Ÿ�� ä��� / G5 / 1�ð�
// https://www.acmicpc.net/problem/2133
// ����: https://yabmoons.tistory.com/536
public class Main_2133 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int dp[] = new int[N + 1];

		for (int i = 1; i < N + 1; i = i + 2) // Ȧ���� �� �Ұ���
			dp[i] = 0;

		dp[0] = 1; // �ƹ��͵� ���� ��� 1����

		if (N >= 2)
			dp[2] = 3;

		for (int i = 4; i < N + 1; i = i + 2) {
			dp[i] = dp[i - 2] * dp[2];
			for (int j = 4; j <= i; j = j + 2) {
				dp[i] += dp[i - j] * 2; // Ư���� ��� 2����
			}
		}

		System.out.println(dp[N]);
	}
}
