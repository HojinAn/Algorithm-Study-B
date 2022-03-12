import java.io.*;
import java.util.*;

class pair implements Comparable<pair>{
	int x,y,z;
	public pair(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public int compareTo(pair o) {
		return this.z - o.z;
	}
}

// 크루스칼 연습문제 
public class Main {
	static int n, m;
	static int arr[] = new int[100007];
	
	static int find(int i)
	{
		if (i == arr[i])
			return i;
		return arr[i] = find(arr[i]);
	}
	static void union_set(int a, int b)
	{
		int root1 = find(a);
		int root2 = find(b);
		if (root1 == root2)
			return;
		arr[root1] = root2;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a,b,c,sum, ans;
		
		String s[];
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		ans = 0;
		ArrayList<pair> edge = new ArrayList<pair>();
		for (int i=0; i<=n; i++)
			arr[i] = i;
		for (int i=0; i<m; i++)
		{
			s = br.readLine().split(" ");
			a = Integer.parseInt(s[0]);
			b = Integer.parseInt(s[1]);
			c = Integer.parseInt(s[2]);
			edge.add(new pair(a,b,c));
		}
		Collections.sort(edge);
		for (int i=0; i<edge.size(); i++)
		{
			a = edge.get(i).x;
			b = edge.get(i).y;
			c = edge.get(i).z;
			if (find(a) == find(b))
				continue;
			else
			{
				union_set(a,b);
				ans += c;
			}
		}
		System.out.println(ans);
	}	
}