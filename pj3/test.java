public class test {
	public int[] a;
	
	//
	
	public test() {	
		a = new int[10];
		for(int i = 0; i < 10 ; i++){
			a[i] = i;
			System.out.println(a[i]);
		}
	}
public static void main(String[] args) {
	test a =new test();
}
	
}

	
