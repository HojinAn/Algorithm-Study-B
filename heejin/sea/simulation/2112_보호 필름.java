import java.io.*;
import java.util.*;

// BOJ / ��ȣ �ʸ� / ���� ���� / 40��
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
public class Solution_2112 {
	static int D,W,K; //D:�β�(��), W:����ũ��(��), K: �հݱ���
	static int map[][], tmp[][]; //map: input��, tmp: �þ� �־��ٰ� �ǵ����� ��
	static int res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			tmp = new int[D][W];
		
			
			for(int i=0;i<D;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<W;j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			for(int i=0;i<D;i++) {	//�迭 ����
				for(int j=0;j<W;j++)
					tmp[i][j] = map[i][j];
			}
			if(pass())
				res = 0;
			else {
				res = Integer.MAX_VALUE;
				dfs(0,0);
			}
				
			System.out.println("#"+t+" "+res);
		}
	}
	private static void dfs(int idx, int cnt) { //idx: ���� ��, cnt: �þ� ��� ȸ��
		if(idx>=D) {
			if(pass()) { //�׽�Ʈ ��� ����� ��
				res = Math.min(res, cnt);
			}
			return;
		}
		for(int i=0;i<2;i++) { //0(A), 1(B) �þ� ����
			fill(idx,i);
			dfs(idx+1, cnt+1);	//�þ� ������ ���
			recover(idx);
		}
		dfs(idx+1, cnt); //�þ� ���� �� �� ���
		
	}
	private static void recover(int idx) {	//���� ������ �ǵ�����
		for(int i=0;i<W;i++)
			tmp[idx][i] = map[idx][i];
		
	}
	private static void fill(int idx, int val) {	//�þ� ����
		for(int i=0;i<W;i++)
			tmp[idx][i] = val;
		
	}
	private static boolean pass() { //���� tmp map ���°� �հ� ���� K�� �����ϴ���

		int cnt = 1; //������ Ư���� ��
		int maxCnt = 0;
		
		for(int j=0;j<W;j++) {	//��� ���� ����
			cnt = 1;
			maxCnt = 0;
			for(int i=0;i<D-1;i++) {
				if(tmp[i][j] == tmp[i+1][j])
					cnt++;
				else
					cnt=1;
				maxCnt = Math.max(maxCnt, cnt);
			}
			
			if(maxCnt<K)
				return false;
		}

			return true;

	}
}
