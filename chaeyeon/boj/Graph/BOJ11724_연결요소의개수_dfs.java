package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//17��
public class BOJ11724_�������ǰ���_dfs {
	static int N,M;
	static Node[] list;
	static boolean[] visited;
	
	static class Node {
		int v;
		Node link;
		
		public Node(int v, Node link) {
			super();
			this.v = v;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		list = new Node[N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from] = new Node(to, list[from]);
			list[to] = new Node(from, list[to]);
		}
		
		int count = 0;
		for(int i = 1; i <= N; i++) {//��� ��带 Ž���� �� ���� = visited�迭�� �� true�� �ɶ�
			if(!visited[i]) {
				dfs(i);
				count++;// �ѹ� Ž���ϰ� ���� ������ �Ѱ��� ����
			}
			
		}
		System.out.println(count);
		
	}
	
	public static void dfs(int current) {
		visited[current] = true;
		
		for(Node tmp=list[current]; tmp != null; tmp = tmp.link) {
			if(!visited[tmp.v]) {
				dfs(tmp.v);
			}
		}
	}


}
