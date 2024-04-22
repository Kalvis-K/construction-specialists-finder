package io.codelex.certifiedConstructionSpecialistsFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindData {
    private final Scanner scanner;
    private final List<String> requestedInfo;
    private final List<String> foundLines;

    public FindData() {
        scanner = new Scanner(System.in);
        CSVReader csvReader = new CSVReader();
        foundLines = new ArrayList<>();
        requestedInfo = csvReader.filterCSV();
    }

    private String usersFindingCriteria() {
        System.out.println("Please select the criteria by which you will search for information.");
        System.out.println("----------------------------------------------------");
        System.out.println("To search by person's surname, enter '1'");
        System.out.println("To search by field of activity, enter '2'");
        System.out.println("----------------------------------------------------");
        System.out.print("Please enter the number of the chosen criteria: ");

        String criteria;

        while (true) {
            criteria = scanner.nextLine();

            if (criteria.equals("1") || criteria.equals("2")) {
                break;
            } else {
                System.out.println("Incorrect input!");
                System.out.println("Please enter '1' (if you want to search by persons surname) \nor '2' (if you want to search by field of activity)");
            }
        }
        return criteria;
    }

    private void findInfoBySurname() {
        filterCriteria(requestedInfo, "surname", 1);
    }

    private void findInfoByField() {
        filterCriteria(requestedInfo, "field of activity", 6);
    }

    public void getUserSelectedInfoBySearchCriteria() {
        if (usersFindingCriteria().equals("1")) {
            findInfoBySurname();
        } else {
            findInfoByField();
        }
    }

    private void handleSearchResultWithMoreThan200Lines(List<String> foundLines) {
        if (foundLines.size() > 200) {
            System.out.println("The search returned more than 200 lines. Do you want to see them?");
            System.out.println("Enter 'yes' to see the lines, or 'no' to start a new search.");

            String input = scanner.nextLine().toLowerCase();
            if (input.equals("yes")) {
                System.out.println("Showing the lines:");
                for (String line : foundLines) {
                    System.out.println(line);
                }
            } else if (input.equals("no")) {
                System.out.println("Let's start a new search!");
                foundLines.clear();
                getUserSelectedInfoBySearchCriteria();
            } else {
                System.out.println("Please enter 'yes' or 'no'!");
            }
        } else {
            System.out.println("Found " + foundLines.size() + " lines.");
            for (String line : foundLines) {
                System.out.println(line);
            }
        }
    }

    private void filterCriteria(List<String> requestedInfo, String criteriaValue, int columnIndex) {
        System.out.printf("Please enter the %s: ", criteriaValue);
        String userSearch = scanner.nextLine();
        boolean found = false;

        for (String info : requestedInfo) {
            String[] column = info.split(",");
            if (column.length >= columnIndex && column[columnIndex].contains(userSearch)) {
                foundLines.add(info);
                found = true;
            }
        }
        displaySearchResult(found, userSearch);
    }

    private void displaySearchResult(boolean found, String userSearch) {
        if (found) {
            handleSearchResultWithMoreThan200Lines(foundLines);
        } else {
            System.out.println("No lines found with the surname '" + userSearch + "'.");
        }
    }
}
