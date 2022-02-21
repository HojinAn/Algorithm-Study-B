import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//10��
//�ѹ� Ǯ���� ������ ������.
public class BOJ1931_ȸ�ǽǹ��� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] time = new int[n][2];//ȸ�ǽ� �����ϴ� �ð�,, ������ �ð� ���� �迭
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//������ �ð��� �������� �������� ����, ������ �ð��� ���ٸ� �����ϴ� �ð� �������� �������� ����(1,2)->(2,2)����
		Arrays.sort(time, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int result = o1[1] - o2[1];
				if(result == 0) {
					result = o1[0] - o2[0];
				}
				return result;
			}
			
		});
		
		//����Ʈ�� ���鼭 �����ϴ� �ð��� ���� ������ �ð����� ũ�ų� ������ ��밡���ϹǷ� count++
		int count = 1;
		int end = time[0][1];
		for(int i = 1; i < n; i++) {
			if(time[i][0] >= end) {
				count++;
				end = time[i][1];
			}
		}
		System.out.println(count);

	}

}
