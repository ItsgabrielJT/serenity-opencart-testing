package com.company.automation.tasks.db;

import com.company.automation.abilities.QueryDatabase;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Task: ExecuteQuery
 *
 * Ejecuta una consulta SQL parametrizada contra la base de datos configurada.
 * Los resultados se almacenan en el actor mediante una nota (notepad pattern).
 *
 * Principio SOLID:
 *   - Single Responsibility: solo ejecuta y almacena el resultado.
 *   - Dependency Inversion: depende de la abstracción QueryDatabase Ability.
 *
 * Uso:
 *   actor.attemptsTo(ExecuteQuery.sql("SELECT * FROM oc_order WHERE order_id = ?", orderId));
 *   List<Map<String,Object>> rows = DbQueryResult.retrievedBy(actor);
 */
@Subject("execute SQL query against the database")
public class ExecuteQuery implements Task {

    public static final String QUERY_RESULT_KEY = "DB_QUERY_RESULT";

    private final String sql;
    private final Object[] params;

    private ExecuteQuery(String sql, Object... params) {
        this.sql = sql;
        this.params = params;
    }

    public static ExecuteQuery sql(String sql, Object... params) {
        return new ExecuteQuery(sql, params);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        QueryDatabase db = QueryDatabase.as(actor);
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                int columnCount = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    results.add(row);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query: " + sql, e);
        }

        actor.remember(QUERY_RESULT_KEY, results);
    }
}
