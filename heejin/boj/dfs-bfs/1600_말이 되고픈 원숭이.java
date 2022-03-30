package com.boj;

import java.io.*;
import java.util.*;
// BOJ / ���� �ǰ��� ������ / G4 / 40��
// https://www.acmicpc.net/problem/1600

class Node {
	int x;
	int y;
	int horse; // ��ó�� ������ Ƚ��
	int time; // �ҿ� �ð�

	public Node(int x, int y, int horse, int time) {
		this.x = x;
		this.y = y;
		this.horse = horse;
		this.time = time;
	}

}

public class Main_1600 {
	static int dx[] = { -1, 1, 0, 0 }; // �����̵�
	static int dy[] = { 0, 0, -1, 1 };
	static int dx2[] = { -2, -1, 1, 2, 2, 1, -1, -2 }; // ��ó�� �̵�
	static int dy2[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int map[][];
	static int visited[][][];
	static int K, W, H;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new int[H][W][K + 1]; // (w,h)��ǥ���� k����ŭ ��ó�� �����Ͽ� �湮�Ͽ��� �� �ð�
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (W == 1 && H == 1)
			System.out.println(0);
		else
			System.out.println(bfs(0, 0));
	}

	private static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y, 0, 0));
		visited[x][y][0] = 0;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.x == H - 1 && cur.y == W - 1) {
				// visited[nx][ny][0~K]���� �ּڰ� ���ϱ�
				int res = Integer.MAX_VALUE;
				for (int i = 0; i <= K; i++) {
					if (visited[cur.x][cur.y][i] != 0)
						res = Math.min(res, visited[cur.x][cur.y][i]);
				}

				return res;
			}
			// 1. ��ó�� ����
			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx2[i];
				int ny = cur.y + dy2[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny] != 1 && cur.horse < K && visited[nx][ny][cur.horse + 1] == 0) { // ���� Ƚ���� K �̸��̰�, ��������
																								// ���� �̹湮�� ���
					q.offer(new Node(nx, ny, cur.horse + 1, cur.time + 1));
					visited[nx][ny][cur.horse + 1] = visited[cur.x][cur.y][cur.horse] + 1;
				}
			}
			// 2. ���� �̵�
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= H || ny < 0 || ny >= W)
					continue;
				if (map[nx][ny] != 1 && visited[nx][ny][cur.horse] == 0) {
					q.offer(new Node(nx, ny, cur.horse, cur.time + 1));
					visited[nx][ny][cur.horse] = visited[cur.x][cur.y][cur.horse] + 1;
				}

			}
		}
		return -1;
	}
}
