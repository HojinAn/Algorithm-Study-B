import java.io.*;
import java.util.*;

// SEA / ���� ���� �κ� ���� / D3 / 10��
public class Solution_3307 {
	static int N; //������ ����
	static int nums[]; //�Է� ����
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			int res=0;
			N = Integer.parseInt(br.readLine());
			nums = new int[N];
			//���� �Է¹ޱ�
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			//lis ���̺� ����
			int lis[] = new int[N];
			Arrays.fill(lis, 1);
			
			for(int i=1;i<N;i++) {
				int plus=0;
				for(int j=0;j<i;j++) {
					if(nums[i]>=nums[j])
						plus = Math.max(plus, lis[j]);
				}
				lis[i] += plus;
			}
			
			for(int i=0;i<N;i++)
				res = Math.max(res, lis[i]);
			System.out.println("#"+t+" "+res);
		}
	}
}
