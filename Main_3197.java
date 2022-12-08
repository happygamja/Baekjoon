/*
 * 3197: 백조의 호수 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[r][c];
		Queue<Pair> s = new LinkedList<Pair>();
		Queue<Pair> w = new LinkedList<Pair>();
		for(int i = 0; i < r; i++) {
			String input = br.readLine();
			for(int j = 0; j < c; j++) {
				char ch = input.charAt(j);
				map[i][j] = ch;
				if(s.isEmpty() && ch == 'L') { // 백조를 처음 발견했을 경우
					s.add(new Pair(i, j));
					map[i][j] = 'V';
				} else if(ch != 'X') {
					w.add(new Pair(i, j));
				}
			}
		}
		
		int[] x = {-1, 1, 0, 0};
		int[] y = {0, 0, -1, 1};
		int day = 0;
		
		loop:
		while(true) {
			Queue<Pair> nextS = new LinkedList<Pair>();
			// 백조 이동
			while(!s.isEmpty()) {
				Pair now = s.poll();
				
				for(int i = 0; i < 4; i++) {
					int nr = now.r + x[i];
					int nc = now.c + y[i];
					
					if(nr < 0 || nr >= r || nc < 0 || nc >= c || map[nr][nc] == 'V')
						continue;
					
					if(map[nr][nc] == '.') {
						s.add(new Pair(nr, nc));
					} else if(map[nr][nc] == 'X') {
						nextS.add(new Pair(nr, nc));
					} else if(map[nr][nc] == 'L') {
						break loop;
					}
					
					map[nr][nc] = 'V';
				}
			}
			
			int size = w.size();
			for(int i = 0; i < size; i++) {
				Pair now = w.poll();
				for(int j = 0; j < 4; j++) {
					int nr = now.r + x[j];
					int nc = now.c + y[j];
					
					if(nr < 0 || nr >= r || nc < 0 || nc >= c || map[nr][nc] == 'V')
						continue;
					
					if(map[nr][nc] == 'X') {
						map[nr][nc] = '.';
						w.add(new Pair(nr, nc));
					}
				}
			}
			
			s = nextS;
			day++;
		}
		
		System.out.println(day);
	}
	
	public static class Pair{
		int r;
		int c;
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
