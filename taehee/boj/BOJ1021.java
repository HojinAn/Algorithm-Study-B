package com.study;

import java.util.LinkedList;
import java.util.Scanner;

public class BOJ1021 {

	//1�� poll
	//2�� left
	//3�� right
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //q.size
		int m = sc.nextInt(); //���� ����
		int[] arr = new int[m]; //���� �ε���
		for (int i = 0; i < m; i++) {
			arr[i] = sc.nextInt();
		}
		
		LinkedList<Integer> deq = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			deq.offer(i);
		}
		
		int cnt = 0;
		for(int num : arr) {
			while(true) {
				if(deq.peek() == num) { //1
					deq.poll();
					break;
				}else {
					int l = deq.indexOf(num);
					if(l > deq.size() / 2) { //3
						deq.offerFirst(deq.pollLast());
					}else { //2
						deq.offer(deq.poll());
					}
					cnt++;
				}				
			}
		}
		System.out.println(cnt);
	}

}
