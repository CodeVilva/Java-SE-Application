package com.converter;

public class Main {

    public static void main(String[] args) {

        String inputFolder =
                "D:\\Certificates\\DOCX";

        String outputFolder =
                "D:\\Certificates\\PDF";

        WordToPdfService.convertFolder(
                inputFolder,
                outputFolder);
    }
}