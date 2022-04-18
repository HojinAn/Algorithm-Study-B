import java.io.*;
import java.util.*;

// BOJ / ȣ���� �θ��� ġŲ / G5 /
// https://www.acmicpc.net/problem/21278
public class Main_21278 {
	static int N, M;
	static int graph[][];
	static int selected[]; // ���õ� ġŲ��
	static int resSum; // ��� ���ÿ����� �պ� �ð� ��
	static int res[]; // �ǹ� 2���� ������ �ǹ� ��ȣ

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N + 1][N + 1];
		selected = new int[2];
		res = new int[2];
		resSum = Integer.MAX_VALUE;

		// �ִܰŸ� ���̺� ��� �������� �ʱ�ȭ
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(graph[i], (int) 1e9);
		}

		// �ڱ� �ڽſ��� �ڱ� �ڽ����� ���� ����� 0���� �ʱ�ȭ
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < N + 1; j++) {
				if (i == j)
					graph[i][j] = 0;
			}
		}

		// ���� ���� �Է¹ޱ�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}

		// �÷��̵� ����
		for (int k = 1; k < N + 1; k++) {
			for (int a = 1; a < N + 1; a++) {
				for (int b = 1; b < N + 1; b++) {
					graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
				}
			}
		}
		// 1~N���� ġŲ�� 2�� ����
		selectChicken(0, 1);
		System.out.println(res[0] + " " + res[1] + " " + resSum);

	}

	private static void selectChicken(int idx, int start) { // 1~N���� ġŲ�� 2�� ����
		if (idx == 2) {
			// ���� ���õ� ġŲ������ �� �ҿ� �ð� ���ϱ�
			getTime();
			return;
		}

		for (int i = start; i < N + 1; i++) {
			selected[idx] = i;
			selectChicken(idx + 1, i + 1);
		}

	}

	private static void getTime() { //�պ� �ҿ�ð� ���ϱ�
		int sum = 0;
		int s1 = selected[0];
		int s2 = selected[1];
		for (int i = 1; i < N + 1; i++) {
			sum += Math.min(graph[i][s1] * 2, graph[i][s2] * 2); // ġŲ�� 1�� ġŲ�� 2 �� �� ���� �պ��ð�
		}

		if (sum < resSum) { //��ü �պ��ð� �� ���� �� ����
			resSum = sum;
			res[0] = s1;
			res[1] = s2;
		}

	}

}
