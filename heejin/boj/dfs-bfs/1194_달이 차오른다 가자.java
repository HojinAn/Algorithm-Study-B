package com.boj;

import java.io.*;
import java.util.*;

// BOJ / ���� ��������, ����. / G1 /
// https://www.acmicpc.net/problem/1194

class Node {
	int x;
	int y;
	int move;
	int key;

	public Node(int x, int y, int move, int key) {
		super();
		this.x = x;
		this.y = y;
		this.move = move;
		this.key = key;
	}

}

public class Main_1194 {
	static int N, M;
	static char map[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean visited[][][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][64]; // a~f���� Ű ���� ����

		int start_x = 0, start_y = 0;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '0') {
					start_x = i;
					start_y = j;
				}
			}

		}

		bfs(start_x, start_y);

	}

	private static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y, 0, 0));
		visited[x][y][0] = true;
		map[x][y] = '.';

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (map[cur.x][cur.y] == '1') {
				System.out.println(cur.move);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == '#' || visited[nx][ny][cur.key])
					continue;

				if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') { // ������ ���
					int key = cur.key | (1 << (map[nx][ny] - 'a'));
					q.offer(new Node(nx, ny, cur.move + 1, key));
					visited[nx][ny][key] = true;
				} else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'Z') { // ���� ���
					boolean flag = (cur.key & (1 << (map[nx][ny] - 'A'))) != 0;
					if (flag) { // Ű ������ ���
						q.offer(new Node(nx, ny, cur.move + 1, cur.key));
						visited[nx][ny][cur.key] = true;
					}

				} else { // .�̳� �������� ���
					q.offer(new Node(nx, ny, cur.move + 1, cur.key));
					visited[nx][ny][cur.key] = true;

				}
			}
		}
		System.out.println(-1);
	}

}
