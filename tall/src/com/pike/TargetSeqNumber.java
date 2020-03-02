package com.pike;

import java.util.LinkedList;

public class TargetSeqNumber {

	LinkedList<Integer> findTarget(LinkedList<Integer> numbers, int target)
	{
		int sum = 0;
		LinkedList<Integer> answer = new LinkedList<Integer>();
		int i = 0;
		
		while(i<numbers.size()){
			sum = sum + numbers.get(i);
			answer.add(numbers.get(i));
			if (sum>target){
				answer.clear();
				numbers.removeFirst();
				sum = 0;
				i = 0;
			} else if (sum==target) {
				break;
			} else {
				i++;
			}
		}
		
		return answer;
	}
	
	
	public static void main(String[] args) {
		int[] list = {1, 3, 5, 6, 7, 8, 14, 21};
		int target = 18;
		LinkedList<Integer> numbers = new LinkedList<Integer>();
		for (int i=0; i<list.length; i++){
			numbers.add(list[i]);
		}
		
		TargetSeqNumber call = new TargetSeqNumber();
		LinkedList<Integer> answer = call.findTarget(numbers, target);
		
		System.out.println(answer);
		
	}
}
