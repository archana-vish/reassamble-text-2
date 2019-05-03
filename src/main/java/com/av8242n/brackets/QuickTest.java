package com.av8242n.brackets;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QuickTest {

    public static void main(String[] args) {
        String equation = "[({a+b}*c)+d]";

        Map<String, String> openCloseBrackets = new HashMap<>();

        openCloseBrackets.put("(",")");
        openCloseBrackets.put("[","]");
        openCloseBrackets.put("{","}");

        ArrayDeque<String> brackets = new ArrayDeque<>(); // working as a stack -> push, poll/pop, peek

        List<String> listOfAllBracketsFromEquation = new ArrayList<>();
        Predicate<String> containsKey = (a-> openCloseBrackets.containsKey(a));
        Predicate<String> containsValue = (a-> openCloseBrackets.containsValue(a));
        Predicate<String> containsKeyOrValue = containsKey.or(containsValue);
        listOfAllBracketsFromEquation = Arrays.stream(equation.split(""))
                .filter(containsKeyOrValue)
                .collect(Collectors.toList());

        for (String bracket : listOfAllBracketsFromEquation) {
            if (openCloseBrackets.containsKey(bracket))
             brackets.push(bracket);
            else {
                // pop out
                if (openCloseBrackets.get(brackets.peek()).equals(bracket)) {
                    brackets.pop();
                } else {
                    // error
                    System.out.println("Invalid sequence for " + bracket);
                }
            }

        }

        System.out.println(brackets.size());

    }
}
