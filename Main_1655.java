/*
 * 1655: 가운데를 말해요 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> max = new PriorityQueue<Integer>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return Integer.compare(o2, o1);
					}
				});
		PriorityQueue<Integer> min = new PriorityQueue<Integer>();
		
		max.add(Integer.parseInt(br.readLine()));
		System.out.println(max.peek());
		
		StringBuilder rs = new StringBuilder();
		for(int i = 1; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(min.size() == max.size()) {
				// 홀수 차례
				if(min.peek() < num) {
					max.add(min.poll());
					min.add(num);
				} else {
					max.add(num);
				}
			} else {
				// 짝수 차례
				if(max.peek() < num) {
					min.add(num);
				} else {
					min.add(max.poll());
					max.add(num);
				}
			}
			rs.append(max.peek() + "\n");
		}
		
		System.out.println(rs.toString());
	}
	
}
