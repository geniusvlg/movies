package com.fact.nash.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DateResolution {

    private static final int totalQuarter = 4;

    public static List<Long> getResolvedAnnualDates(int startDate, int endDate) {
        List<Long> resolvedDates = new ArrayList<Long>();
        for (int i = startDate; i <= endDate; i++) {
            resolvedDates.add((long) i);
        }

        return resolvedDates;
    }

    public static List<Long> parsingDatesFromDhRequest(List<Integer> dates) {
        return dates.stream().mapToLong(Integer::longValue).boxed().collect(Collectors.toList());
    }

    public static List<Long> getResolvedQuarterlyDates(int startDate, int endDate) {
        int startYear = startDate / 1000;
        int endYear = endDate / 1000;
        int startMonth = startDate % 10;
        int endMonth = endDate % 10;
        int startQuarter = startMonth / 3;
        int endQuarter = endMonth / 3;
        int quarter = startQuarter;
        List<Integer> resolvedDates = new ArrayList<>();

        for (int year = startYear; year < endYear; ++year) {
            for (; quarter <= totalQuarter; quarter++) {
                int date = year * 100 + quarter * 3;
                resolvedDates.add(date);
            }
            quarter = 1;
        }

        for (; quarter <= endQuarter; quarter++) {
            int date = endYear * 100 + quarter * 3;
            resolvedDates.add(date);
        }

        return null;
    }

}
