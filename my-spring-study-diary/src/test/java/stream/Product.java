package stream;

import lombok.*;
import java.util.*;

@Getter
public class Product {
    private String name;
    private int price;
    private double rating;

    public Product(String name, int price, double rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    // 이름 출력을 위해 생성해두면 좋은 toString
    @Override
    public String toString() {
        return "("+rating + ")"+ name + "(" + price + "원)";
    }

    public static void main(String[] args) {

        List<Product> products = Arrays.asList(
                new Product("노트북", 150000, 4.5),
                new Product("마우스", 30000, 4.8),
                new Product("키보드", 80000, 4.2),
                new Product("모니터", 300000, 4.5)
        );

        //가격 낮은 순
        System.out.println("\n가격 낮은 순");
        products.stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .forEach(System.out::println);


        //평점 높은 순
        System.out.println("\n평점 높은 순");
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getRating).reversed())
                .forEach(System.out::println);

        //평점 높은순 -> 가격 낮은순
        System.out.println("\n평점 높은순 -> 가격 낮은순");
        products.stream()
                .sorted(Comparator
                        .comparingDouble(Product::getRating).reversed()
                        .thenComparingInt(Product::getPrice))
                .forEach(System.out::println);

        // 상위 3개 뽑기
        System.out.println("\n상위 3개 뽑기");
        List<Product> top3 = products.stream()
                .sorted(Comparator.comparingInt(Product::getPrice)) // 가격 낮은순...
                .limit(3)
                .toList();
        System.out.println(top3.toString());

        List<Product> top33 = products.stream()
                // 1. 가격 높은 순(내림차순)으로 정렬 변경!
                .sorted(Comparator.comparingInt(Product::getPrice).reversed())
                .limit(3)
                .toList();
        System.out.println(">>"+top33.toString());

        //조건 필터 후 정렬
        // 평점 4.5 이상만 정렬
        System.out.println("\n평점 4.5 이상만 정렬");
        List<Product> filtered = products.stream()
                .filter(s -> s.getRating() >= 4.5)
                .sorted(Comparator
                        .comparingInt(Product::getPrice)
                        .thenComparing(Product::getName))
                .toList();
        System.out.println(filtered.toString());

        //원본은 변경되지 않음 (주의)
        System.out.println("\n원본은 변경되지 않음 (주의)");
        List<Integer> original = Arrays.asList(5, 2, 8, 1);

        List<Integer> sorted = original.stream()
                .sorted()
                .toList();

        System.out.println(original);  // [5, 2, 8, 1] ← 원본 유지
        System.out.println(sorted);    // [1, 2, 5, 8] ← 새로운 리스트


    }




}
