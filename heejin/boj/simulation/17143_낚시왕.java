import java.io.*;
import java.util.*;

// BOJ / ���ÿ� / G2 / 80��
// https://www.acmicpc.net/problem/17143
public class Main_17143 {
	static class Fish implements Comparable<Fish> {
		int r, c, s, d, z; 

		public Fish(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Fish o) { // ũ�� ������ ������ ����
			return o.z - this.z;
		}
	}

	static int R, C, M;
	static Fish map[][];
	static int dx[] = { 0, -1, 1, 0, 0 }; // 1:��, 2:�Ʒ�, 3:������, 4:����
	static int dy[] = { 0, 0, 0, 1, -1 };
	static int reverse[] = { 0, 2, 1, 4, 3 }; // �ݴ����
	static List<Fish> list;
	static int res; // ���ÿ��� ���� ����� ũ�� ��(����)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Fish[R + 1][C + 1];

		list = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Fish(r, c, s, d, z);
			list.add(new Fish(r, c, s, d, z));
		}

		simulation();
		System.out.println(res);
	}

	private static void simulation() {
		for (int t = 1; t <= C; t++) { // ���ÿ��� ��ġ
			// 1. ���� ������ ���� �� �ִ� ����� �ִ���
			fishDown(t);
			// 2. ����� �̵�
			fishMove();
			// 3. �̵��� ������ map�� ��ġ
			setMap();
		}
	}

	private static void setMap() { // map���ٰ� list�� ����� fish�� ��ġ
		mapClear(); // map ��ġ �� ����
		Collections.sort(list); // �������� ũ�� ������ ����
		for(int i=0;i<list.size();i++) {
			Fish cur = list.get(i);
			if(map[cur.r][cur.c]==null) { //������� ���� ��ġ (ũ�� �� �����̱� ����)
				map[cur.r][cur.c]=cur;
			}
		}
		
		reset(); //���� ���� ���� ���� map�� ������ list�� ����
	}

	private static void reset() { //map�� ������ list�� ����
		list.clear();
		for(int i=1;i<=R;i++) {
			for(int j=1;j<=C;j++) {
				if(map[i][j]!=null) {
					list.add(map[i][j]);
				}
			}
		}	
	}

	private static void mapClear() { //map ����
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] != null)
					map[i][j] = null;
			}
		}
	}

	private static void fishMove() { // ����� �̵�
		for (int i = 0; i < list.size(); i++) {
			Fish cur = list.get(i);
			// ���� �� �̵� �������� �ӷ¸�ŭ �̵�
			int nx = cur.r, ny = cur.c;
			int moveCnt = cur.s; // �������� �ϴ� ĭ ��(�ӷ�)
			while (moveCnt > 0) {
				if ((nx <= 1 && cur.d==1) || (nx >= R && cur.d==2)
						|| (ny <= 1 && cur.d==4) || (ny >= C && cur.d==3)) {
					cur.d = reverse[cur.d];
				}
				nx += dx[cur.d];
				ny += dy[cur.d];
				
				moveCnt--;
			}
			cur.r = nx;
			cur.c = ny;
		}
	}

	private static void fishDown(int c) { //���ÿ��� ���� ������ ���� �� �ִ� �����
		for (int i = 1; i <= R; i++) {
			if (map[i][c] != null) { // �� �ִٸ�
				res += map[i][c].z;
				map[i][c] = null; // ����
				reset();//map�� ��ȭ�� �Ͼ���� list�� ����
				break;
			}
		}

	}
}
