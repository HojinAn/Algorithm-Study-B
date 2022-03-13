package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//25��
//���� ������ ����Ͽ� ����ص״ٰ� �ڿ� ������ ����� �� ����ϸ� �ǰڴٰ� ����
//�׸��� ���� �м��غ��ٰ� [i][j] ���� �ִ��� [i^0][j-1]�� [i^0][j-2]�� �ִ񰪿� �ڱ��ڽ��� ���Ѱ� ���� �˰Ե�
//��ó�� j==1�� ��츦 ���� ���������� �ʰ� ��ƼĿ �Է��� 1������ ������ j-2�϶� �ε��� ó���� �ڿ������� �ذ�Ǵ� ���� �ٸ� �ڵ带 ���� �˰Ե�
public class BOJ9465_��ƼĿ {
	static int N;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int  t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int[][] stickers = new int[2][N];
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			

			for (int j = 1; j < N; j++) {
				for(int i = 0; i < 2; i++) {
					//j==1�̸� ���� �밢���� ����
					if(j==1) stickers[i][j] = stickers[i][j] + stickers[i^1][j-1];
					else {
						stickers[i][j] = stickers[i][j] + Math.max(stickers[i^1][j-1], stickers[i^1][j-2]);
					}
					
				}
			}
			
			int result = Math.max(stickers[0][N-1], stickers[1][N-1]);
			System.out.println(result);
		}
		
		
	}

}
