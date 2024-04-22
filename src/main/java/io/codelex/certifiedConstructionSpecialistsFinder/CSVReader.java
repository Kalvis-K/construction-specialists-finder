package io.codelex.certifiedConstructionSpecialistsFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private final GetData getData = new GetData();

    public List<String> filterCSV() {
        downloadFile();
        List<String> filteredList = new ArrayList<>();
        String filterValue = "Aktīvs";
        int columnIndex = 4;

        Path filePath = getData.getTargetPath();

        addFilteredPositionsToList(filePath, columnIndex, filterValue, filteredList);
        return filteredList;
    }

    private void downloadFile() {
        try {
            getData.downloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFilteredPositionsToList(Path filePath, int columnIndex, String filterValue, List<String> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns[columnIndex].contains(filterValue)) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
