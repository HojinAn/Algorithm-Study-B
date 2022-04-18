import java.io.*;
import java.util.*;

// BOJ / ��� / S1 / 50�� (���̵�� �� ���ø�)...
// https://www.acmicpc.net/problem/1080
public class Main_1080 {
	static int N, M;
	static int arrA[][], arrB[][];
	static boolean isSame[][];
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arrA = new int[N][M]; // ��� A
		arrB = new int[N][M]; // ��� B

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				arrA[i][j] = input.charAt(j) - '0';
		}
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++)
				arrB[i][j] = input.charAt(j) - '0';
		}

		if (N < 3 || M < 3) { // ��, �� ũ�Ⱑ 3���� ������
			if (isSame())
				res = 0;
			else
				res = -1;
		} else {
			for (int i = 0; i < N - 2; i++) {
				for (int j = 0; j < M - 2; j++) {
					if (arrA[i][j] != arrB[i][j]) {
						flip(i, j); // 3X3 �κй迭 ������
						res++;
					}
				}
			}
		}

		if (isSame())
			System.out.println(res);
		else
			System.out.println(-1);

	}

	private static void flip(int r, int c) { //�κй迭 ������
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				arrA[i][j] = arrA[i][j] ^ 1;
			}
		}

	}

	private static boolean isSame() { //��� A�� ��� B�� ������
		boolean flag = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arrA[i][j] != arrB[i][j]) {
					flag = false;
					return flag;
				}
			}
		}
		return flag;
	}

}
