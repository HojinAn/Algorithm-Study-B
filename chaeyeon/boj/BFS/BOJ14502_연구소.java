import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_������ {
	static int N,M;
	static int[][] map;
	static int[][] d = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static Queue<int[]> q = new LinkedList<>();
	static ArrayList<int[]> virusList;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());//��
		M = Integer.parseInt(st.nextToken());//��
		map = new int[N][M];
		virusList = new ArrayList<>();//���̷��� ��ġ ���� ����Ʈ
		
		for(int i=0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusList.add(new int[] {i,j});//���̷����϶� ��ġ ����
				}
			}
		}
		
		calc(0,0);
		System.out.println(max);

	}
	
	//�� �����
	//ó���� j�� �Ѱ�����µ� �׷��� �Ǹ� ���� ��Ϳ��� �� �� �� �� j�� 0���� �����ؾ��ϴµ� �Ѱ���j���� �����ϰ� �ż� �ȵƴ�.
	public static void calc(int sx, int cnt) {//dfs��
		if(cnt == 3) {
			bfs();
			return;
		}
		
		for(int i = sx; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
						if(j == M-1)
							calc(i+1, cnt+1);//������ ���� �� ��ġ�� �Ѱ���	
						else				
							calc(i, cnt+1);//������ ���� �� ��ġ�� �Ѱ���	
					map[i][j] = 0;
				}
			}
		}
	}
	
	//���̷��� ��Ʈ����
	public static void bfs() {
		for(int[] vi: virusList) {//���̷��� ��Ƴ��� ������ ť�� ����
			q.add(vi);
		}
		int[][] temp = new int[N][M];
		for(int i=0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		while(!q.isEmpty()) {//ť���� ������ �̾� �ش� ��ġ�� �����¿�� ����������
			int[] virus = q.poll();
			for(int i = 0; i < 4; i++) {
				int dx = virus[0]+d[i][0];
				int dy = virus[1]+d[i][1];
				if(dx >= 0 && dy >= 0 && dx < N && dy < M && temp[dx][dy]==0) {
					q.add(new int[] {dx,dy});
					temp[dx][dy] = 2;
				}
			}
			
		}
		safeArea(temp);
	}
	
	public static void safeArea(int[][] temp) {//��ĭ�� �κ� üũ
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< M; j++) {
				if(temp[i][j] == 0)
					count++;
			}
		}
		
		max = Math.max(max, count);
	}

}
