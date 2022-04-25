import java.io.*;
import java.util.*;

public class Main_1946 {
	static class Jiwonja implements Comparable<Jiwonja> {
		int rankA; // ���� ���� ����
		int rankB; // ���� ���� ����

		public Jiwonja(int rankA, int rankB) {
			this.rankA = rankA;
			this.rankB = rankB;
		}

		@Override
		public int compareTo(Jiwonja o) { // ���� ���� ��������
			return this.rankA - o.rankA;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine()); // ������ ��
			List<Jiwonja> list = new ArrayList<>(); // ������ ����Ʈ
			int res = 0; // ������ �� �ִ� ���Ի�� �� (����)

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new Jiwonja(a, b));
			}
			Collections.sort(list);

			int minA = Integer.MAX_VALUE; // ���� ���� ���� ����
			int minB = Integer.MAX_VALUE; // ���� ���� ���� ����

			for (int i = 0; i < N; i++) {
				Jiwonja cur = list.get(i);
				if (i == 0) { // ���� ���� ���� ������ �������� �ʱ�ȭ
					minA = cur.rankA;
					minB = cur.rankB;
					res++;
				}
				if (cur.rankB < minB) { 
					minB = cur.rankB;
					res++;
				}
			}
			System.out.println(res);
		}
	}

}
