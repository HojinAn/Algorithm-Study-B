package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//17��
public class BOJ11724_�������ǰ���_���μ� {
	static int N,M;
	static int[] parents;
	
	static int findSet(int a) {
		if(parents[a]==0) return a;
		
		return parents[a] = findSet(parents[a]);
	}
	
	static void union(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		if(pa!=pb) {
			parents[pb] = pa;
		}
	}
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			union(from,to);

			
		}
		
		int count = 0;
		for(int i = 1; i <= N; i++) {//��� ��带 Ž���� �� ���� = visited�迭�� �� true�� �ɶ�
			if(parents[i] == 0) {
				count++;// �ѹ� Ž���ϰ� ���� ������ �Ѱ��� ����
			}
		}
		System.out.println(count);
		
	}
	
	
}
