package com.ll;

import java.util.Arrays;

public class Calc {
    public static int run(String expr) {
        expr = removeUnnecessaryBrackets(expr);

        if (expr.contains(" * ") && expr.contains(" + ")) {
            String[] exprBits = expr.split(" \\+ ");

            int sum = Arrays.stream(exprBits)
                    .map(Calc::run)
                    .reduce((a, b) -> a + b)
                    .orElse(0);

            return sum;
        }

        if (expr.contains(" * ")) {
            String[] exprBits = expr.split(" \\* ");

            int product = Arrays.stream(exprBits)
                    .map(Integer::parseInt)
                    .reduce((a, b) -> a * b)
                    .orElse(0);

            return product;
        }

        expr = expr.replaceAll(" - ", " + -");

        String[] exprBits = expr.split(" \\+ ");

        int sum = Arrays.stream(exprBits)
                .map(Integer::parseInt)
                .reduce((a, b) -> a + b)
                .orElse(0);

        return sum;
    }

    private static String removeUnnecessaryBrackets(String expr) {
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return expr.substring(1, expr.length() - 1);
        }

        return expr;
    }
}
