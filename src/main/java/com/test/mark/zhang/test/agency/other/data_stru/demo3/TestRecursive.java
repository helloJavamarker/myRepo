package com.test.mark.zhang.test.agency.other.data_stru.demo3;

public class TestRecursive {

	public static void main(String[] args) {
		print(3);
	}
	
	//递归
	public static void print(int i) {
		if(i>0) {
			System.out.println(i);
			print(i-1);
		}
	}

}
