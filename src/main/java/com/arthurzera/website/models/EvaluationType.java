package com.arthurzera.website.models;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum EvaluationType {
	UP(1), NONE(0), DOWN(-1);

	private int value;
	private static Map<Integer, EvaluationType> map = new HashMap<>();
	static {
		for(EvaluationType type:EvaluationType.values()) {
			map.put(type.value, type);
		}
	}
	private EvaluationType(int value) {
		this.value = value;
	}
	@Enumerated(EnumType.STRING)
	public static String valueOf(int i) {
		return map.get(i).name();
	}
	@Enumerated(EnumType.ORDINAL)
	public int getValue() {
		return value;
	}
}
