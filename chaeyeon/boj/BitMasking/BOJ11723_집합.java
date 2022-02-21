import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//40��
//Ư�� ��Ʈ�� 0���� ����� remove�� Ư����Ʈ�� 0�� 1 1->0���� �ٲٴ� toggle�� ��� �ؾ����� ����ϴ��� ���� �ɷȴ�.
public class BOJ11723_���� {

	public static void main(String[] args) throws IOException {
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int list = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
				case "add":
					int x = Integer.parseInt(st.nextToken());
					list = list | 1<<x;
					break;
				case "check":
					if((list & 1<<Integer.parseInt(st.nextToken())) != 0) 
						sb.append(1 + "\n");
					else
						sb.append(0 + "\n");
					break;
				case "remove":
					list = list & ~(1<<Integer.parseInt(st.nextToken()));
					break;
				case "toggle":
					list = list ^ (1<<Integer.parseInt(st.nextToken()));
					break;
				case "empty":
					list = list & 0;
					break;
				case "all":
					list = list | ~0;
					break;

			}
		}
		System.out.println(sb);
	}

}
