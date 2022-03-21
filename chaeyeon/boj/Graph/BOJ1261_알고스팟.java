package �ִܰ��;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//��������� �ְ� ������ ������ ���� Ǯ�� Ǯ������ ���� ������ �Ȱ��Ƽ� ���� Ǯ����.
//���ͽ�Ʈ��
public class BOJ1261_�˰��� {
	
	public static class Pos implements Comparable<Pos> {
		int x,y,wall;

		public Pos(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}

		@Override
		public int compareTo(Pos o) {
			return this.wall - o.wall;
		}
	}

	static int[][] d = {{-1,0},{1,0},{0,-1},{0,1}};
	static int M,N;
	static int[][] distance, map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		distance = new int[M][N];
		for(int i = 0; i < M; i++) {
			String s = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j)-'0';
				distance[i][j] = Integer.MAX_VALUE;
			}
		}

		System.out.println(dijkstra());

	}
	
	public static int dijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		boolean visited[][] = new boolean[M][N];
		distance[0][0] = 0;
		pq.offer(new Pos(0,0,0));
		
		while(!pq.isEmpty()) {
			Pos current = pq.poll();
			
			if(visited[current.x][current.y]) continue;
			visited[current.x][current.y] = true;
			
			if(current.x == M-1 && current.y == N-1) {
				return current.wall;
			}
			
			for(int i=0; i < 4; i++) {
				int dx = current.x + d[i][0];
				int dy = current.y + d[i][1];

				if(dx >= 0 && dy >= 0 && dx < M && dy < N && !visited[dx][dy]
						&& distance[dx][dy] > distance[current.x][current.y]+map[dx][dy]) {
					distance[dx][dy] = distance[current.x][current.y]+map[dx][dy];
					pq.add(new Pos(dx, dy, distance[dx][dy]));
					

				}
			}
		}
		return 0;
	}

}
	//visited�迭 ���� ���ͽ�Ʈ�� ���
	/*public static int dijkstra() {
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		distance[0][0] = 0;
		pq.offer(new Pos(0,0,0));
		
		while(!pq.isEmpty()) {
			Pos current = pq.poll();
			
			if(distance[current.x][current.y] == -1) continue;
			distance[current.x][current.y] = -1;
			
			if(current.x == M-1 && current.y == N-1) {
				return current.wall;
			}
			
			for(int i=0; i < 4; i++) {
				int dx = current.x + d[i][0];
				int dy = current.y + d[i][1];

				if(dx >= 0 && dy >= 0 && dx < M && dy < N && distance[dx][dy] != -1
						&& distance[dx][dy] > current.wall+map[dx][dy]) {
					distance[dx][dy] = current.wall+map[dx][dy];
					pq.add(new Pos(dx, dy, distance[dx][dy]));
				}
			}
		}
		return 0;
	}*/


