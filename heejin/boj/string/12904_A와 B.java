import java.io.*;
import java.util.*;
//BOJ / A�� B / G5 / 40��
//https://www.acmicpc.net/problem/12904
public class Main_12904_2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		StringBuilder ss = new StringBuilder(S);
		StringBuilder st = new StringBuilder(T);
		
		while(ss.length()<st.length()){
			if(st.charAt(st.length()-1)=='A') {	//A�̸� ������ ���� ����
				st.delete(st.length()-1, st.length()); 
			}else {								//B�̸� ������ ���� ���� �� ������
				st.delete(st.length()-1, st.length()); 
				st.reverse();
			}
		}
		if(ss.toString().equals(st.toString()))
			System.out.println(1);
		else
			System.out.println(0);
	}
}
