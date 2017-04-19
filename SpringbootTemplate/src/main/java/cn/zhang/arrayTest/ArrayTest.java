package cn.zhang.arrayTest;

public class ArrayTest {
	public static void main(String[] args) {
		testAddItem();
	}
	public static void testAddItem(){
		int []num=new int[20];
		for(int i=0;num[i++]<21;);
		for (int a:num) {
			System.out.println(a);
		}
	}
}