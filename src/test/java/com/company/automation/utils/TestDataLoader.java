package com.company.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDataLoader {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final CsvMapper   CSV_MAPPER   = new CsvMapper();

    private TestDataLoader() {}

    
    public static <T> List<T> fromJson(String fileName, TypeReference<List<T>> type) {
        String path = "testdata/" + fileName;
        try (InputStream is = TestDataLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException("Test data file not found: " + path);
            }
            return JSON_MAPPER.readValue(is, type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON test data: " + path, e);
        }
    }

    
    public static <T> List<T> fromCsv(String fileName, Class<T> beanClass) {
        String path = "testdata/" + fileName;
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        try (InputStream is = TestDataLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException("Test data file not found: " + path);
            }
            return CSV_MAPPER.readerFor(beanClass)
                             .with(schema)
                             .<T>readValues(is)
                             .readAll();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load CSV test data: " + path, e);
        }
    }
}
