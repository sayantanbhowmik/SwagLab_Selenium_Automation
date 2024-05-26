package com.sauceDemo.swaglabs;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String d = null;
		String s="Item total: $29.99";
		String x[] = s.split("$",2);
		for(int i=0;i<x.length;i++) {
			System.out.println(x[i]);
			
			System.out.println("\n");
//			if(!x[i].contains("$"))
//			{
//				d=x[i];
//			}
		}
		System.out.println(" "+x.length);

	}

}
