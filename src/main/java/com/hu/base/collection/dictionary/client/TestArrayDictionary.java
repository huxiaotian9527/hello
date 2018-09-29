package com.hu.base.collection.dictionary.client;


import com.hu.base.collection.dictionary.ArrayDictionary;

public class TestArrayDictionary {
	public static void main(String[] args) {
		ArrayDictionary<Integer, String> arrayDict = new ArrayDictionary<Integer, String>();
		arrayDict.add(1, "1");
		arrayDict.add(2, "2");
	}
}
