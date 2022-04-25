import java.io.*;
import java.util.*;

public class Main_20056 {
	static class FireBall {
		int r, c, m, s, d;

		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static int N, M, K;
	static int res;
	static ArrayList<FireBall> map[][];
	static List<FireBall> list;
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // ���� ũ��
		M = Integer.parseInt(st.nextToken()); // ���̾ ����
		K = Integer.parseInt(st.nextToken()); // �̵� ��� Ƚ��
		map = new ArrayList[N][N]; // ���̾�� �����ϴ� ����

		for (int i = 0; i < N; i++) { // ���� �ʱ�ȭ
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		list = new ArrayList<>(); // ���̾ ����Ʈ

		// FireBall ���� �Է¹ޱ�
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // (1,1)���� �����ϴ� ���� (0,0) �������� �ٲ�
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new FireBall(r, c, m, s, d));
		}

		simulation(); // �ùķ��̼� ����

		res = getResult(); // �����ִ� ���̾ ���� �� ���ϱ�
		System.out.println(res);
	}

	private static int getResult() {
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).m;
		}
		return sum;

	}

	private static void simulation() {
		for (int time = 0; time < K; time++) { // K������ ���
			// 1. ���̾ �̵�
			for (int i = 0; i < list.size(); i++) {
				FireBall cur = list.get(i);
				// r, c �̵�
				cur.r = (cur.r + N + dx[cur.d] * (cur.s % N)) % N;
				cur.c = (cur.c + N + dy[cur.d] * (cur.s % N)) % N;

				map[cur.r][cur.c].add(cur); // �̵��� map�� ��ġ

			}

			// 2. �̵����� �� 2�� �̻��� ���̾ �ִ� ĭ üũ
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() > 1) {
						divide4(i, j);
					}
				}
			}

			// map�� ��ġ�Ѱ� list�� ���
			list.clear();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() != 0) {
						for (int k = 0; k < map[i][j].size(); k++) {
							FireBall cur = map[i][j].get(k);
							list.add(cur);
						}
					}
				}
			}
			// map ����� list�� �� �������, ���� ���� ���� map clear
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() > 0)
						map[i][j].clear();
				}
			}
		}
	}

	private static void divide4(int x, int y) { // 4�������� ��������
		int sumM = 0, sumS = 0; // ������, �ӷ���
		boolean flag = true; // ���� ���� Ȧ,¦�̸� true, �ƴϸ� false

		for (int i = 0; i < map[x][y].size(); i++) {
			FireBall cur = map[x][y].get(i);
			sumM += cur.m;
			sumS += cur.s;
			if (i != map[x][y].size() - 1) {
				FireBall next = map[x][y].get(i + 1);
				if (cur.d % 2 != next.d % 2) // Ȧ, ¦ �������� ��� flag=false
					flag = false;
			}
		}

		int newM = 0, newS = 0;
		newM = sumM / 5;
		newS = sumS / map[x][y].size();

		map[x][y].clear();
		setMap(newM, newS, flag, x, y);

	}

	private static void setMap(int newM, int newS, boolean flag, int x, int y) {
		if (newM != 0) { // ���̾ ���� 0 �ƴҶ��� ��Ƴ����Ƿ�
			if (flag) {
				for (int i = 0; i < 4; i++) {
					FireBall newFireBall = new FireBall(x, y, newM, newS, 2 * i);
					map[x][y].add(newFireBall);
				}
			} else {
				for (int i = 0; i < 4; i++) {
					FireBall newFireBall = new FireBall(x, y, newM, newS, 2 * i + 1);
					map[x][y].add(newFireBall);
				}
			}
		}
	}

}
