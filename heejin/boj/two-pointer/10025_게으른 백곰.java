package com.boj;

import java.io.*;
import java.util.*;

// BOJ / ������ ��� / S4 / 25��
// https://www.acmicpc.net/problem/10025
public class Main_10025 {
	static int N, K;
	static int map[];
	static int res; // �������� ���� �ִ�

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // ���� �絿�� ����
		K = Integer.parseInt(st.nextToken()); // �̵� ���� �Ÿ�

		map = new int[1000001];
		int max_x = 0; // �ִ� x ��ǥ

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken()); // ������ ��
			int x = Integer.parseInt(st.nextToken()); // �絿���� ��ǥ
			map[x] = g;
			max_x = Math.max(max_x, x);
		}

		// �����̵� ������ -> 2*K+1 ũ��
		if(K>=max_x/2+1) {	//�̵� ������ ��ü�� Ŀ���� ���
			for(int i=0;i<=max_x;i++)
				res +=map[i];
		}else {
			int sum = 0;
			for (int i = 0; i < 2 * K + 1; i++) { // �ʱ� ����
				sum += map[i];
			}
			res = sum;
			for (int i = 0; i < max_x - 2 * K; i++) {
				// �տ��� ����
				sum -= map[i];
				// �ڿ� �� ���ϱ�
				sum += map[i + 2 * K + 1];
				res = Math.max(res, sum);
			}
		}
		
		System.out.println(res);

	}
}
