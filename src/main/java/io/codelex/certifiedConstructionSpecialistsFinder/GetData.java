package io.codelex.certifiedConstructionSpecialistsFinder;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class GetData {
    private Path targetPath;

    public void downloadFile() throws IOException {
        String dataLocation = "https://data.gov.lv/dati/dataset/f44f8d2f-4121-4494-b009-368f48992603/resource/443d4936-2b81-40a0-9f95-932f5b480c3f/download/sertificetie-buvspecialisti-20.04.2024.csv";
        targetPath = Paths.get(setTargetDirectory(), setFileName(dataLocation));
        URL url = new URL(dataLocation);
        Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private String setFileName(String dataLocation) {
        String pattern = "-\\d{2}.\\d{2}.\\d{4}";
        String fileName = dataLocation.substring(dataLocation.lastIndexOf('/') + 1);
        fileName = fileName.replaceAll(pattern, "");
        return fileName;
    }

    private String setTargetDirectory() {
        return System.getProperty("user.home") + "\\Downloads\\";
    }

    public Path getTargetPath() {
        return targetPath;
    }
}
