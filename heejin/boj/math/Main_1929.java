import java.io.*;
import java.util.*;

// BOJ / �Ҽ� ���ϱ� / S3 / 15��
// https://www.acmicpc.net/problem/1929
public class Main_1929 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		boolean isPrime[] = new boolean[N + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false; // 0,1�� �Ҽ� X

		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				if (i * j > N) // ���� ��� ���
					break;
				if (!isPrime[i * j]) // �Ҽ� �ƴ� ���(������ ó��)
					continue;
				isPrime[i * j] = false;
			}
		}

		for (int i = M; i <= N; i++) {
			if (isPrime[i]) // �Ҽ��� ���
				System.out.println(i);
		}
	}

}
