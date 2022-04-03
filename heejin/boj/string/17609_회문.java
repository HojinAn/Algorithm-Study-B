import java.io.*;
import java.util.*;

// BOJ / ȸ�� / S1 / 1�ð� ��
// https://www.acmicpc.net/problem/17609
// ������ ���������ΰ� �˰���� ���� �����س´µ� ������ �������. ��α� �����Ͽ� �ۼ�
public class Main_17609 {
	static String input;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			input = br.readLine();
			int start = 0, end = input.length() - 1;
			if(check(start,end)) { //���ڸ� �������� �ʰ� ���� ���
				System.out.println(0);
				continue;
			}else {
				if(check2(start,end)) { //���ڸ� �ϳ� �����ؾ� ���� ���
					System.out.println(1);
				}else { //���� �ϳ� �����ص� ���� ���� ���
					System.out.println(2);
				}
			}
			
		}
	}

	private static boolean check2(int start, int end) {
		while(start<=end) {
			if(input.charAt(start)!=input.charAt(end)) { //�ٸ���
				boolean a = check(start+1, end); //���� ���� �������� �� ���
				boolean b = check(start, end-1); //������ ���� �������� �� ���
				if(a==false&& b==false) //����, ������ �����ص� �� �� ���
					return false;	
				else
					return true;
			}
			start++;
			end--;
		}
		return false;
	}

	private static boolean check(int start, int end) {
		while(start<=end) {
			if(input.charAt(start)!=input.charAt(end)) //�ٸ���
				return false;
			start++;
			end--;
		}
		return true; //��� ������ true ��ȯ
	}
}
