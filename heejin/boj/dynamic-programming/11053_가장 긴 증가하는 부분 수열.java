import java.io.*;
import java.util.*;
// BOJ / ���� �� �����ϴ� �κ� ���� / S2 / 10��
// https://www.acmicpc.net/problem/11053
public class Main_11053 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int nums[] = new int[N];	//�Է°�
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
			nums[i] = Integer.parseInt(st.nextToken());
		
		int LIS[] = new int[N]; //i���� ����� LIS
		int res = 0;
		for(int i=0;i<N;i++) {	//0��°���� N-1���� LIS ���ϱ�
			LIS[i] = 1;
			for(int j=0;j<i;j++) {
				if(nums[i]>nums[j] && LIS[i] < LIS[j]+1 )
					LIS[i]=LIS[j]+1;
			}
			res = Math.max(res, LIS[i]);
		}
		System.out.println(res);
	}
}
