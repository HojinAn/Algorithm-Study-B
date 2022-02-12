package com.ssasfy;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ1966 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for(int t=0; t<tc; t++) {
			int n = sc.nextInt(); //������ ����
			int m = sc.nextInt(); //�ñ��� ���� ���°
			int cnt = 0; //��
			
			LinkedList<int[]> q = new LinkedList<>();
			for(int i=0;i<n;i++) {
				q.offer(new int[] {i, sc.nextInt()}); //�ʱ���ġ, �߿䵵 
			}
			
			while(true) {
				int[] item = q.poll();
				boolean isMax = true;
				
				for(int i=0;i<q.size(); i++) {
					if(item[1] < q.get(i)[1]) {
						q.offer(item);
						
						//ū �� �տ��Ÿ� ���� �ڷ� ������
						for(int j=0; j<i; j++) {
							q.offer(q.poll());
						}
						isMax = false;
						break;
					}
				}
				
				if(isMax) {
					cnt++;
					if(item[0] == m) break;
				}
			}
			System.out.println(cnt);
		}
	}

}
