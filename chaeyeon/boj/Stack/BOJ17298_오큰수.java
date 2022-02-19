import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//20220217
//30��

//�� result�� ���ø��� �迭�� ����� ���� �� �ð��� ����Ǵ��� �𸣰���
public class BOJ17298_��ū�� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());//����ũ��
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];//����
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();//�������� ���� ����(ū�Ŵ����)
		Stack<Integer> result = new Stack<>();//������� ����
		//int[] result = new result[N];// result�迭�� ������� �� �ð��� �ξ� �����
		
		for(int i=arr.length-1; i >= 0; i--) {//�ڿ������� ������ ����
			while(!stack.isEmpty() && arr[i] >= stack.peek())
				stack.pop();
			
			if(stack.isEmpty()) {
				stack.push(arr[i]);
				result.push(-1);//�ڽź��� �������� ū�� ���ٴ� ��
			}
			else {
				result.push(stack.peek());
				stack.push(arr[i]);
			}
		}
		
		while(!result.isEmpty()) {
			sb.append(result.pop()).append(" ");
		}
		
		System.out.println(sb);
		
	}

}
