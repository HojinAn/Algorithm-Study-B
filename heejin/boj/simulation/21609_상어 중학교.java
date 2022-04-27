import java.io.*;
import java.util.*;

// BOJ / ��� ���б� / G2 / 2�ð� 50��
// https://www.acmicpc.net/problem/21609
public class Main_21609 {

	static class Blocks implements Comparable<Blocks> {
		int x, y; // ���� ����� ��ǥ
		int cnt; // ��� �׷��� �� ��� ����
		int rainbowCnt; // ������ ��� ����
		List<int[]> xy;
		

		public Blocks(int x, int y, int cnt, int rainbowCnt, List<int[]> blocks) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.rainbowCnt = rainbowCnt;
			this.xy = blocks;
		}

		@Override
		public int compareTo(Blocks o) {

			if (this.cnt == o.cnt) {
				if (this.rainbowCnt == o.rainbowCnt) {
					if (this.x == o.x) {
						return o.y - this.y; // �� ū ��
					} else
						return o.x - this.x; // �� ū ��
				} else
					return o.rainbowCnt - this.rainbowCnt;

			} else
				return o.cnt - this.cnt; // ��� �� ���� ���� ��
		}
	}

	static final int SPACE = 6; // �� ĭ
	static int N, M;
	static int map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean finish; // ���� ���� ���� ����
	static List<Blocks> list; // ��� �׷��
	static int res;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // ���� ũ��
		M = Integer.parseInt(st.nextToken()); // ���� ����
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// �ùķ��̼� ����
		while (true) {
			finish = false;
			makeBlockGroup();
			if (finish)
				break;
			removeBigBlocks();
			gravity();
			move90();
			gravity();
		}
		System.out.println(res);
	}


	private static void makeBlockGroup() { // ��� �׷� �����
		list = new ArrayList<>();
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				clear();
				if (!visited[i][j] && map[i][j] != -1 && map[i][j] != SPACE) {
					bfs(i, j);
				}		
			}
		}
		if (list.size() == 0) // ��ϱ׷� ������ ������� ����
			finish = true;
	}

	private static void clear() { // ������ ����� �ٸ� �׷쿡���� ��� �����ϴ� visited �ʱ�ȭ
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]==0)
					visited[i][j] = false;
			}
		}
	}

	private static void bfs(int x, int y) { // ��� �׷� ã��
		List<int[]> blocks = new ArrayList<int[]>(); // ��� ����Ʈ�� ����

		Queue<int[]> q = new LinkedList<>();
		int color = map[x][y]; // ���� ��
		visited[x][y] = true;
		q.offer(new int[] { x, y });
		blocks.add(new int[] { x, y });

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (color == 0 && map[nx][ny]>0) {  //���ػ��� ���� �������� ���, ������ ���� �Ϲ� ����� ���ػ����� ���
					color = map[nx][ny];
				}
				if (!visited[nx][ny] && map[nx][ny] != -1 && map[nx][ny] != SPACE
						&& (color == map[nx][ny] || map[nx][ny] == 0)) {
					q.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					blocks.add(new int[] { nx, ny });
				}
			}
		}

		Collections.sort(blocks, new Comparator<int[]>() { // ���� ��� ã�� ���� ����
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1]; // �� ������, �� ��������
				} else
					return o1[0] - o2[0]; // �� ��������
			}
		});

		int cnt = blocks.size(); // ��ϱ׷� �� ����
		int rainbowCnt = 0; // ������ ��� ����
		for (int i = 0; i < blocks.size(); i++) {
			int[] cur = blocks.get(i);
			int c = map[cur[0]][cur[1]];
			if (c == 0) { // ������ ����� ���
				rainbowCnt++;
			}
		}
		int bx = 0, by = 0; // ��� �׷� ���� ��ǥ
		for (int i = 0; i < blocks.size(); i++) {
			int cur[] = blocks.get(i);
			if (map[cur[0]][cur[1]] != 0) {
				bx = cur[0];
				by = cur[1];
				break;
			}
		}
		if (cnt >= 2 && cnt - rainbowCnt >= 1) // ��� 2�� �̻�, �Ϲ� ��� �ϳ��� �־����
			list.add(new Blocks(bx, by, cnt, rainbowCnt, blocks));
	}

	private static void removeBigBlocks() { // 2. ���� ū ��� �׷� ����
		Collections.sort(list); // ��� �׷�� ���ؿ� �°� ����

		Blocks blocks = list.get(0); // ���� ��� �׷�

		List<int[]> xy = blocks.xy; 
		for (int i = 0; i < xy.size(); i++) { 		// �ش� �׷쿡 �ش��ϴ� ��� ��ϵ� ����
			int[] cur = xy.get(i);
			map[cur[0]][cur[1]] = SPACE; 
		}
		res += blocks.cnt * blocks.cnt;
	}

	private static void gravity() { // �߷� �ۿ�
		for(int j=0;j<N;j++) {
			for(int i=N-1;i>=0;i--) {
				if(map[i][j]==-1 || map[i][j]==SPACE) continue; 
				int bottom = i+1; // �� ĭ ��
				while(true) { // ��ĭ ã��
					if(bottom==N) break;
					if(map[bottom][j]==SPACE) bottom++; // ��ĭ ã��
					else
						break; 
				}
				if(bottom==i+1) continue; 
				map[bottom-1][j] = map[i][j];
				map[i][j] = SPACE; 
			}
		}
	}


	private static void move90() { // 90�� �ݽð� ȸ��
		int[][] tmp = copy(map);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[N - 1 - j][i] = tmp[i][j];
			}
		}
	}

	private static int[][] copy(int[][] map) { // �迭 ����
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				tmp[i][j] = map[i][j];
		}
		return tmp;
	}
}
