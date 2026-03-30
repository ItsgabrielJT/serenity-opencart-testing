package com.company.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TestDataLoader
 *
 * Carga datos de prueba desde archivos JSON y CSV ubicados en el classpath
 * bajo el directorio testdata/.
 *
 * Principio SOLID:
 *   - Single Responsibility: solo carga y deserializa archivos de test data.
 *   - Open/Closed: se puede extender para soportar Excel/YAML sin modificar
 *     los métodos existentes.
 */
public class TestDataLoader {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final CsvMapper   CSV_MAPPER   = new CsvMapper();

    private TestDataLoader() {}

    /**
     * Carga una lista de objetos desde un archivo JSON en testdata/.
     *
     * @param fileName nombre del archivo (ej. "users.json")
     * @param type     TypeReference del tipo destino
     */
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

    /**
     * Carga una lista de objetos desde un archivo CSV en testdata/.
     *
     * @param fileName  nombre del archivo (ej. "products.csv")
     * @param beanClass clase destino de cada fila
     */
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
