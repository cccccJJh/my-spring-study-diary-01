package stream;

import java.util.*;

public class StreamSorted {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3);

        List<Integer> sorted = numbers.stream()
                .sorted()           // 정렬
                .toList();

        System.out.println(sorted);  // [1, 2, 3, 5, 8, 9]

        //숫자 정렬
        List<Integer> numbers1 = Arrays.asList(5, 2, 8, 1, 9);

// 오름차순 (기본)
        List<Integer> asc = numbers.stream()
                .sorted()
                .toList();
        System.out.println("오름차순: " + asc);  // [1, 2, 5, 8, 9]

// 내림차순
        List<Integer> desc = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println("내림차순: " + desc);  // [9, 8, 5, 2, 1]


// 문자열 정렬
        List<String> names = Arrays.asList("Charlie", "Alice", "Bob");

// 알파벳순 (오름차순)
        List<String> asc1 = names.stream()
                .sorted()
                .toList();
        System.out.println(asc);  // [Alice, Bob, Charlie]

// 알파벳 역순 (내림차순)
        List<String> desc1 = names.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(desc);  // [Charlie, Bob, Alice]



        //문자열 길이로 정렬
        List<String> words = Arrays.asList("apple", "pie", "banana", "kiwi");

// 길이 짧은 순
        List<String> byLength = words.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();
        System.out.println(byLength);  // [pie, kiwi, apple, banana]

// 길이 긴 순
        List<String> byLengthDesc = words.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toList();
        System.out.println(byLengthDesc);  // [banana, apple, kiwi, pie]


    }
}
