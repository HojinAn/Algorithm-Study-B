import java.io.*;
import java.util.*;

// SEA / Ư���� �ڼ� / ���� SW ���� / 40��
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
public class Solution_4013 {
	static int K;
	static int res; // ȸ���� ���� �� ȹ�� ����
	static int magnet[][];
	static int dir[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			res = 0;
			K = Integer.parseInt(br.readLine());
			magnet = new int[5][8]; // 1~4�������� n��, s�� ����
			dir = new int[5]; // 1~4���� ȸ�� ���� (0: ȸ�� x, -1: �ݽð�, 1: �ð�)
			for (int i = 1; i < 5; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// ��ɾ� �Է�
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()); // num �ڼ��� d�������� ȸ��
				int d = Integer.parseInt(st.nextToken());
				dir[num] = d;
				setDirLeft(num - 1); // num ���� ������ ȸ������ ����
				setDirRight(num + 1); // num ���� �������� ȸ������ ����
				moveMagnet(); // �� ��Ϲ������� ȸ�����⿡ �°� ȸ��
			}
			for (int i = 1; i < 5; i++) {
				if (magnet[i][0] == 1)
					res += Math.pow(2, i - 1);
			}
			System.out.println("#" + t + " " + res);
		}
	}

	private static void moveMagnet() {
		for (int i = 1; i < 5; i++) {
			int d = dir[i];
			if (d == 0) // ȸ�� X�̸�
				continue;
			else if (d == 1) {
				magnet = Circle(i, magnet); // i��° ��Ϲ��� �ð���� ȸ��
			} else if (d == -1) {
				magnet = ReverseCircle(i, magnet); // i��° ��Ϲ��� �ݽð���� ȸ��
			}
		}

	}

	private static int[][] ReverseCircle(int num, int[][] magnet) { // �ݽð�
		int data[] = copy(magnet[num]);
		for (int i = 0; i < 8; i++) {
			magnet[num][i] = data[(i + 1 + 8) % 8];
		}
		return magnet;

	}

	private static int[][] Circle(int num, int[][] magnet) { // �ð�
		int data[] = copy(magnet[num]);
		for (int i = 0; i < 8; i++) {
			magnet[num][i] = data[(i - 1 + 8) % 8];
		}
		return magnet;
	}

	private static int[] copy(int[] arr) { // �迭 ����
		int[] tmp = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			tmp[i] = arr[i];
		return tmp;
	}

	private static void setDirLeft(int num) {
		if (num == 0)
			return;
		// num�� 2���� num+1�� 6�� ��
		if (dir[num + 1] == 0)
			dir[num] = 0;
		else if (magnet[num + 1][6] == magnet[num][2])
			dir[num] = 0;
		else
			dir[num] = dir[num + 1] == -1 ? 1 : -1; // �ݴ� ���� ȸ��

		setDirLeft(num - 1);
	}

	private static void setDirRight(int num) {
		if (num > 4)
			return;
		// num�� 6���� num-1�� 2�� ��
		if (dir[num - 1] == 0) // ���� �ڼ��� ȸ�� X �̸� �ڽŵ� ȸ�� x
			dir[num] = 0;
		else if (magnet[num - 1][2] == magnet[num][6]) { // �ڼ��� ���� ��� ȸ�� X
			dir[num] = 0;
		} else {
			dir[num] = dir[num - 1] == -1 ? 1 : -1; // �ݴ� ���� ȸ��
		}
		setDirRight(num + 1);

	}

}
