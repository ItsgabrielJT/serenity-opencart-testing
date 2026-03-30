package com.company.automation.questions.db;

import com.company.automation.tasks.db.ExecuteQuery;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.Map;

/**
 * Question: TheDatabaseResult
 *
 * Recupera los resultados de la última query SQL ejecutada,
 * almacenados previamente por ExecuteQuery en el notepad del actor.
 *
 * Principio Screenplay: Desacopla la ejecución (Task) de la
 * verificación (Question), manteniendo la separación de responsabilidades.
 *
 * Uso:
 *   actor.should(seeThat(TheDatabaseResult.hasRows(1)));
 *   actor.should(seeThat(TheDatabaseResult.firstRowColumn("status"), equalTo("shipped")));
 */
@Subject("the result of the last database query")
public class TheDatabaseResult implements Question<List<Map<String, Object>>> {

    private TheDatabaseResult() {}

    public static TheDatabaseResult retrieved() {
        return new TheDatabaseResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> answeredBy(Actor actor) {
        return (List<Map<String, Object>>) actor.recall(ExecuteQuery.QUERY_RESULT_KEY);
    }

    /**
     * Question derivada: cantidad de filas devueltas.
     */
    public static Question<Integer> rowCount() {
        return actor -> {
            List<Map<String, Object>> results = new TheDatabaseResult().answeredBy(actor);
            return results == null ? 0 : results.size();
        };
    }

    /**
     * Question derivada: valor de una columna específica en la primera fila.
     */
    public static Question<Object> firstRowColumn(String columnName) {
        return actor -> {
            List<Map<String, Object>> results = new TheDatabaseResult().answeredBy(actor);
            if (results == null || results.isEmpty()) {
                return null;
            }
            return results.get(0).get(columnName);
        };
    }
}
