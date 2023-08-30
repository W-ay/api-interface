package com.way.apiinterface.utils;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import jdk.internal.util.xml.impl.ReaderUTF8;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    @Getter
    public static ArrayList<String> poems;
    public static List<String> updatePoems(String file) throws FileNotFoundException {
        FileReader fileReader = new FileReader(file);
        CsvReader reader = CsvUtil.getReader(fileReader);
        reader.setFieldSeparator('/');
        CsvData data = reader.read();
        poems = new ArrayList<>();
        for (CsvRow row : data.getRows()) {
            poems.add(row.get(0));
        }
        return poems;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String> poems = updatePoems("assets/poem.csv");
        System.out.println(poems);
        File f1 = new File("poem.csv");
        System.out.println(f1.getAbsolutePath());
        File f2 = new File("/poem.csv");
        System.out.println(f2.getAbsolutePath());
        File f3 = new File("../poem.csv");
        System.out.println(f3.getAbsolutePath());
    }
}
