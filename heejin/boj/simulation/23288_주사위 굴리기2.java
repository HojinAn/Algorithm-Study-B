import java.io.*;
import java.util.*;

// BOJ / �ֻ��� ������ 2
public class Main_23288 {
	static int N, M, K;
	static int map[][];
	static int dice[] = { 1, 2, 3, 4, 5, 6 }; // ��, �Ʒ�, ��, ��, ��, ��
	static int dx[] = { -1, 0, 1, 0 }; // �ϵ�����
	static int dy[] = { 0, 1, 0, -1 };
	static int res; // ���� ����(���)

	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // ����
		M = Integer.parseInt(st.nextToken()); // ����
		K = Integer.parseInt(st.nextToken()); // Ƚ��

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		simulation();
		System.out.println(res);
	}

	static int dir = 1;// ���� ������ ����

	private static void simulation() {
		int cx = 0, cy = 0, nx = 0, ny = 0;
		for (int time = 0; time < K; time++) { // �ֻ��� K�� ������
			// �ֻ��� �̵� (x, y ��ǥ ����)
			nx = cx + dx[dir];
			ny = cy + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) { // ���� �����
				dir = (dir + 2) % 4; // �ݴ����
				nx = cx + dx[dir];
				ny = cy + dy[dir];
			}
			cx = nx;
			cy = ny;
			diceRoll(dir); // �̵��� ���⿡ ���� �ֻ��� �迭 ����
			
			// ���� x,y��ǥ���� ���� �� �ִ� ���� ���ϱ�
			visited = new boolean[N][M];
			dfs(cx, cy);
			// �������� A, B �� �� ���� ��ǥ�� �̵�
			if (dice[5] > map[cx][cy]) // A>B
				dir = (dir + 1) % 4; // �ð� 90��
			else if (dice[5] < map[cx][cy]) // A<B
				dir = (dir - 1 + 4) % 4; // �ݽð� 90��

		}

	}

	private static void diceRoll(int dir) { // �̵� ���⿡ ���� �ֻ��� �迭 ����
		int[] copy = copy(dice);
		if (dir == 0) { // �������� ������
			dice[0] = copy[4];
			dice[1] = copy[0];
			dice[4] = copy[5];
			dice[5] = copy[1];

		} else if (dir == 1) { // ����
			dice[0] = copy[3];
			dice[2] = copy[0];
			dice[3] = copy[5];
			dice[5] = copy[2];
		} else if (dir == 2) { // ����
			dice[0] = copy[1];
			dice[1] = copy[5];
			dice[4] = copy[0];
			dice[5] = copy[4];
		} else { // ����
			dice[0] = copy[2];
			dice[2] = copy[5];
			dice[3] = copy[0];
			dice[5] = copy[3];
		}
	}

	private static void dfs(int cx, int cy) { // ���� ��ǥ���� dir�� �������� �����̸� ���� ����
		visited[cx][cy]=true;
		res += map[cx][cy];
		for(int i=0;i<4;i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			if(nx<0 || nx>=N || ny<0|| ny>=M) 
				continue;
			if(!visited[nx][ny] && map[cx][cy]==map[nx][ny])
				dfs(nx, ny);
		}
	}

	private static int[] copy(int[] dice) {
		int[] tmp = new int[6];
		for (int i = 0; i < 6; i++)
			tmp[i] = dice[i];
		return tmp;
	}
}
