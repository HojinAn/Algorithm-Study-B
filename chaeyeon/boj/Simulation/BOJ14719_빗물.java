import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//h=1���� H ���� ���پ� Ž��. 500*500 = 250000�̴ϱ� �ð��� ����ϴٰ� ����
//�� �࿡�� ä�����ִ� ���� ������ ��ĭ�� ������ ����
public class BOJ14719_���� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] block = new int[W+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i <= W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		for(int i=1; i <= H; i++) {
			boolean full = false, empty = false;
			int count= 0;
			for(int j=1; j <= W; j++) {
				if(block[j]>=i) {
					if(!full) {
						full=true;
					}
					if(empty) {
						sum += count;
						count = 0;
						empty = false;
						}
				}
				else {
					if(full) {
						empty = true;
						count++;
					}
				}
			}
		}
		
		System.out.println(sum);
		
		
	}

}
