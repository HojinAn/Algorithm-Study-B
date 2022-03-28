package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654_�����ڸ��� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());//�������ִ� ���� ����
		int N = Integer.parseInt(st.nextToken());//�������ϴ� ���� ����
		
		long left=1;//������ ����
		long right=Integer.MIN_VALUE;
		int[] arr = new int[K];
		for(int i=0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, arr[i]);
		}

		while(left <= right) {
			long mid = (left+right)/2;
			long cnt=0;
			for(int i=0; i < K; i++) {
				cnt+=arr[i]/mid;
			}
			if(cnt >= N) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		
		System.out.println(right);
	}

}
