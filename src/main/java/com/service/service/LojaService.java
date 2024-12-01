package com.service.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LojaService {

    public static double getTotalAmount(List<Product> products, double discountPercentage) {
        if (products == null || discountPercentage < 0) {
            throw new IllegalArgumentException("Lista de produtos ou desconto inválido.");
        }

        double total = products.stream()
                .mapToDouble(product -> product.getPrice() * product.getStock())
                .sum();

        if (discountPercentage > 0) {
            total = total - (total * discountPercentage / 100);
        }

        System.out.println("Valor total calculado com desconto de " + discountPercentage + "%: " + total);
        return total;
    }

    public static List<Product> getProductsInStock(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("Lista de produtos não pode ser nula.");
        }

        List<Product> productsInStock = products.stream()
                .filter(product -> product.getStock() > 0)
                .collect(Collectors.toList());

        System.out.println("Produtos em estoque: " + productsInStock);
        return productsInStock;
    }

    public static boolean couponValidation(String coupon) {
        if (coupon == null || coupon.isEmpty() || !"CUPOM-1".equals(coupon)) {
            throw new IllegalArgumentException("Cupom inválido.");
        }

        System.out.println("Cupom válido: " + coupon);
        return true;
    }

    public static double[] orderPrices(List<Product> products) {
        if (products == null) {
            throw new IllegalArgumentException("Lista de produtos não pode ser nula.");
        }

        double[] orderedPrices = products.stream()
                .mapToDouble(Product::getPrice)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToDouble(Double::doubleValue)
                .toArray();

        System.out.println("Preços ordenados: " + java.util.Arrays.toString(orderedPrices));
        return orderedPrices;
    }

    public static String[] checkStock(List<Product> products, int minLimit) {
        if (products == null || minLimit <= 0) {
            throw new IllegalArgumentException("Argumentos inválidos.");
        }

        String[] productNames = products.stream()
                .filter(product -> product.getStock() < minLimit)
                .map(Product::getName)
                .toArray(String[]::new);

        System.out.println("Produtos abaixo do limite de estoque " + minLimit + ": " + java.util.Arrays.toString(productNames));
        return productNames;
    }
}
