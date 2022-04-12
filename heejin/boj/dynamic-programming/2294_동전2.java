package com.boj;

import java.io.*;
import java.util.*;
// BOJ / ����2 / S1 / 25��
// https://www.acmicpc.net/problem/2294
public class Main_2294 {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // ���� ����
		K = Integer.parseInt(st.nextToken()); // ���� ��ġ ��

		int coin[] = new int[N]; // ���� ����
		for (int i = 0; i < N; i++)
			coin[i] = Integer.parseInt(br.readLine());
		Arrays.sort(coin); //���� ������ ������������ ����

		int dp[] = new int[K + 1]; // i�� ����� ���� ���� �ּ� ����
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i < K + 1; i++) { // i�� ����� ���� ��
			for(int j=0;j<N;j++) {
				if(i-coin[j]>=0) {
					if(dp[i-coin[j]]!=Integer.MAX_VALUE)
						dp[i] = Math.min(dp[i-coin[j]]+1, dp[i]); //���� ���� ������� ���� ��� �� ���� �� �� �ּҰ� ����
				}
				else	//���� ���� ������ ������� �ϴ� ������ ũ�� Ż��
					break;
			}
		}
		System.out.println(dp[K]==Integer.MAX_VALUE?-1:dp[K]);
	}
}
