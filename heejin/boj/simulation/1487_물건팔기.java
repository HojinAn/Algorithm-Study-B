import java.io.*;
import java.util.*;

// BOJ / ���� �ȱ� / S4 / 30��
public class Main_1487 {
	static int price[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		price = new int[N][2]; // �ִ�ݾ�, ��ۺ�

		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			price[i][0] = Integer.parseInt(st.nextToken());
			price[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(price, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		//�� ���� ���� �� ���� �� �ִ� �� ���ϱ�
		int res = Integer.MIN_VALUE; //�ִ� ������ �� �� �ִ� ����
		int maxBenefit = Integer.MIN_VALUE; // �ִ� ����
		for(int i=0;i<N;i++) {
			int std = price[i][0];  // ���� ����
			int benefit = 0; // ����
			for(int j=i;j<N;j++) {
				if(std-price[j][1]>0) {
					benefit += std-price[j][1];
				}
			}
			if(maxBenefit<benefit) {
				maxBenefit = benefit;
				res = std;
			}
		}
		if(maxBenefit<=0) // ���� ���� �� 
			System.out.println(0);
		else
			System.out.println(res);
	}
}
