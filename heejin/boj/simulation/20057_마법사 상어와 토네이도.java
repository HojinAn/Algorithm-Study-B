import java.io.*;
import java.util.*;

// BOJ / G3 / ������ ���� ����̵�
public class Main_20057 {
	static int N;
	static int map[][];
	static int dir[] = { 0, 1, 2, 3 }; // 0: ��, 1: ��, 2: ��, 3: ��
	static int nextDir[] = { 2, 3, 1, 0 }; // ���� ���⿡�� ��������
	static int dx[] = { -1, 1, 0, 0 }; // �����¿�
	static int dy[] = { 0, 0, -1, 1 };
	static int res; // ���� ������ ���� �� ��

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		simulation();
		System.out.println(res);
	}

	private static void simulation() {

		int cx = N / 2, cy = N / 2; // ������
		int curDir = 2; // ���� ������ ����(2)
		int nx = 0, ny = 0; // ���� ĭ
		int d = 1; // �̵��ؾ� �ϴ� ĭ ��
		int cnt = 0; // �̵� Ƚ��
		int check = 0; // �̵��ؾ� �ϴ� ĭ��ŭ �̵��� 2�� �ߴ���

		while (true) {
			if (cx == 0 && cy == 0) { // (1,1) �����ϸ� �Ҹ��
				break;
			}
			nx = cx + dx[curDir];
			ny = cy + dy[curDir];
			cnt++;
			move(cx, cy, nx, ny, curDir);

			if (d == cnt) {
				cnt = 0;
				curDir = nextDir[curDir];
				check++;
			}
			if (check == 2) {
				check = 0;
				d++; // �̵��ؾ� �ϴ� ĭ��ŭ 2�� �̵� ������ �̵��ؾ� �ϴ� ĭ �� �ø���
			}
			cx = nx;
			cy = ny;
		}
	}

	static int dsx[][] = { { 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 },
			{ -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 } }; // �� ������ x����
	static int dsy[][] = { { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 }, { -1, 1, -2, 2, 0, -1, 1, -1, 1, 0 },
			{ 1, 1, 0, 0, -2, 0, 0, -1, -1, -1 }, { -1, -1, 0, 0, 2, 0, 0, 1, 1, 1 } }; // �� ������ y����
	static int rate[] = { 1, 1, 2, 2, 5, 7, 7, 10, 10 };

	private static void move(int cx, int cy, int nx, int ny, int curDir) {
		map[nx][ny] += map[cx][cy];
		map[cx][cy] = 0; // x �ڸ��� �̵������Ƿ� ����
		int sand = map[nx][ny]; // �� ����
		int a = sand; // a ĭ�� �� ����
		int sx = 0, sy = 0; // �� �𳯸��� ��ǥ
		for (int i = 0; i < 9; i++) { // ������ �����ִ� 9ĭ�� ��ġ
			sx = nx + dsx[curDir][i];
			sy = ny + dsy[curDir][i];
			int amount = (int) (sand * (rate[i] * 0.01));

			check(sx, sy, amount);
			a -= amount;
		}
		int ax = nx + dsx[curDir][9]; // aĭ�� ��ġ
		int ay = ny + dsy[curDir][9];
		check(ax, ay, a);
		map[nx][ny] = 0; // y �ڸ� �� ����

	}

	private static void check(int sx, int sy, int amount) {
		if (sx < 0 || sx >= N || sy < 0 || sy >= N) // ���� ��� ��� res�� ���ϱ�
			res += amount;
		else { // ���� �� ����� map�� �ױ�
			map[sx][sy] += amount;
		}
	}

}
