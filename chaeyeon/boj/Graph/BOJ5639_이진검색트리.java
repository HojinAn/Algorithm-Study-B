package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639_�����˻�Ʈ�� {
	static StringBuilder sb = new StringBuilder();
	public static class Node {
		Node prev, next;
		int val;
		public Node(Node prev, int val, Node next) {
			super();
			this.prev = prev;
			this.next = next;
			this.val = val;
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node tree = null;

		String s="";
		while((s = br.readLine()) != null) {
			if(s.trim() == "")
				break;
			int num = Integer.parseInt(s);
			if(tree == null) {//Ʈ���� ����ִٸ� Ʈ�� ������ �־���
				tree = new Node(null, num, null);
			}
			else //Ʈ���� ���� �ִٸ� �ڱ� �ڸ� ã�Ƽ� �־���
				make(num, tree);
		}
		
		postOrder(tree);
		System.out.println(sb);
	}
	
	//dfs�� ������ȸ
	private static void postOrder(Node cur) {
		if(cur == null) return;
		
		postOrder(cur.prev);
		postOrder(cur.next);
		sb.append(cur.val).append("\n");

	}
	
	private static void make(int num, Node point) {
		if(point.val < num) {//���� ����Ű�� ��庸�� ���� Ŭ ��
			if(point.next == null)//�������ڽ��� ��������� �־��ֱ�
				point.next = new Node(null, num, null);
			else//�������ڽ��� �ִٸ� �������ڽ� �������� �ٽ� Ž��
				make(num, point.next);
		}
		else if(point.val > num) {//���� ����Ű�� ��庸�� ���� ���� ��
			if(point.prev == null)//�����ڽ��� ��������� �־��ֱ�
				point.prev = new Node(null, num, null);
			else//�����ڽ��� �ִٸ� �����ڽ� �������� �ٽ� Ž��
				make(num, point.prev);
		}

	}

}
