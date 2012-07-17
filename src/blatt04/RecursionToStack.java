package blatt04;
import aud.Stack;


public class RecursionToStack {
	public static int whatStack(int n) {
		Stack<Integer> s = new Stack<Integer>();
		Integer result;
		
		while (n > 10) {
			s.push(n % 10);
			n /= 10;
		}
		
		result = n;
		while (!s.is_empty()) {
			result += s.pop();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(whatStack(-92));
	}
}
