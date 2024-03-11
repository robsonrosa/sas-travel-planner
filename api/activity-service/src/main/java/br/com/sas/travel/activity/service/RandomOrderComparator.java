package br.com.sas.travel.activity.service;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;

public class RandomOrderComparator<T> implements Comparator<T> {

	private static final Random random = new Random();

	@Override
	public int compare(T o1, T o2) {
		return Integer.compare(random.nextInt(), random.nextInt());
	}

	public static <T> RandomOrderComparator<T> comparator() {
		return new RandomOrderComparator<>();
	}

	public static <T> Predicate<T> predicate() {
		return value -> random.nextBoolean();
	}
}