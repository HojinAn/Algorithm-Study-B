package com.boj;

import java.io.*;
import java.util.*;


// BOJ / ������ / G4 / 30��
// https://www.acmicpc.net/problem/2239
public class Main_2239 {
	static int[][] map;
	static List<int[]> list; //0�� ���� ��ǥ
	static int cnt;	//ó���� ����ϱ� ���� ����
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		list = new ArrayList<>();
		for(int i=0;i<9;i++) {
			String input = br.readLine();
			for(int j=0;j<9;j++) {
				map[i][j] = input.charAt(j)-'0';
				if(map[i][j]==0)
					list.add(new int[] {i,j});
			}
		}
		dfs(0,map,list);
	
	}

	private static void dfs(int idx, int[][] map, List<int[]> list) {
		if(cnt>0) return;	//�̹� �� �� ��������� �� �̻� dfs ���� X
		if(idx==list.size()) {
			//���
			if(cnt==0) {	//�ʹ� ����� ���� ��
				for(int i=0;i<9;i++) {
					for(int j=0;j<9;j++)
						System.out.print(map[i][j]);
					System.out.println();
				}
			}
			cnt++;
			return;
		}
		for(int i=1;i<=9;i++) { //0�� ���� 1~9���� ���� �־��
			int[] cur = list.get(idx);
			int x = cur[0], y = cur[1];
			map[x][y] = i;
			if(isPromising(x,y,map))	//������ ���
				dfs(idx+1,map,list);
			map[x][y] = 0; 	
		}
	}
	private static boolean isPromising(int x, int y, int[][] map) {
		int cur = map[x][y]; //���� ������ ����
		// �࿡ ���� ���� ����
		for(int c=0;c<9;c++) {
			if(c!=y && map[x][c]==cur)	//���� �࿡ ���� �� �����ϴ� ���
				return false;
		}
		// ���� ���� ���� ����
		for(int r=0;r<9;r++) {
			if(r!=x && map[r][y]==cur) //���� ���� ���� �� �����ϴ� ���
				return false;
		}
		// 3x3�� ���� ���� ����
		int start_x = x - (x%3);
		int start_y = y - (y%3);
		for(int i=start_x;i<start_x+3;i++) {
			for(int j=start_y;j<start_y+3;j++) {
				if(i==x && y==j) continue;
				if(map[i][j]==cur)
					return false;
			}
		}
		return true;
	}
}
