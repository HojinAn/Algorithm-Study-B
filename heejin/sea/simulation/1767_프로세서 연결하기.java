import java.io.*;
import java.util.*;

// BOJ / ���μ��� �����ϱ� / SW ���� ���� / 2�ð� +
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
class Core {
	int x;
	int y;

	public Core(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_1767 {
	static int N, maxCore, minWire; // maxCore: �ִ� �ھ� ��, minWire: �ּ� ���� ��
	static int map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static List<Core> list; // �ھ� ��ġ ���� ����Ʈ
	static int res; //���� ��(����)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<Core>();
			minWire = Integer.MAX_VALUE;
			maxCore = Integer.MIN_VALUE;
			res = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) { // ���� �� ���� �ھ�
						list.add(new Core(i, j));
					}
				}
			}

			dfs(0, 0, 0); // idx: ���� Ž���ϰ� �ִ� �ھ�, coreCnt: ����� �ھ� ��, len: ���� ��
			System.out.println("#" + t + " " + res);

		}
	}

	private static void dfs(int idx, int coreCnt, int len) {
		if (idx == list.size()) { // ��������: ��� �ھ� �� Ž�� ��
			if (maxCore < coreCnt) { // �� ���� �ھ� ���� ��
				maxCore = coreCnt;
				res = len;
			} else if (maxCore == coreCnt) { // �ھ� �� ������ ���� �� �� �����ŷ�
				res = Math.min(res, len);
			}
			return;
		}

		for (int i = 0; i < 4; i++) { // 4�� Ž��
			if (isConnect(list.get(idx), i)) { // �ش� �������� �̵� �������� Ȯ��
				fill(list.get(idx), i, -1);
				dfs(idx + 1, coreCnt + 1, len + minWire);
				fill(list.get(idx), i, 0);
			}
		}
		dfs(idx + 1, coreCnt, len); // �ھ� ���� �� ��

	}

	private static void fill(Core core, int dir, int num) {
		minWire = 0; // ���� ��

		int nx = core.x;
		int ny = core.y;
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				break;
			map[nx][ny] = num;
			minWire++;
		}

	}

	private static boolean isConnect(Core core, int dir) { // ���� �������� ����
		int nx = core.x;
		int ny = core.y;
		while (true) {
			nx += dx[dir];
			ny += dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) // �� ������ ����� ���� ����
				return true;
			if (map[nx][ny] != 0) // �߰��� �����̳� �ھ� ���� ���
				return false;
		}
	}

}
