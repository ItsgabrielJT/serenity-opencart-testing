package com.company.automation.abilities;

import com.company.automation.config.DatabaseConfig;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.HasTeardown;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Ability: QueryDatabase
 *
 * Permite a un Actor ejecutar consultas SQL sobre la base de datos
 * configurada en el framework.
 *
 * Principio SOLID aplicado:
 *   - Single Responsibility: solo gestiona la conexión y liberación de recursos DB.
 *   - Dependency Inversion: depende de la abstracción DataSource, no de un driver concreto.
 *
 * Implementa HasTeardown para garantizar el cierre del DataSource
 * al finalizar el escenario (previene leaks de conexión).
 */
public class QueryDatabase implements Ability, HasTeardown {

    private final DataSource dataSource;

    private QueryDatabase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ─── Factory Methods ──────────────────────────────────────────────────────

    public static QueryDatabase usingDefaultConfiguration() {
        return new QueryDatabase(DatabaseConfig.getInstance().getDataSource());
    }

    public static QueryDatabase using(DataSource dataSource) {
        return new QueryDatabase(dataSource);
    }

    // ─── API ──────────────────────────────────────────────────────────────────

    /**
     * Obtiene una conexión del pool.
     * El llamador es responsable de cerrarla (try-with-resources).
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Recupera la ability desde el actor.
     */
    public static QueryDatabase as(Actor actor) {
        return actor.abilityTo(QueryDatabase.class);
    }

    // ─── Teardown ─────────────────────────────────────────────────────────────

    @Override
    public void tearDown() {
        // El pool HikariCP se cierra a través de DatabaseConfig al finalizar la suite.
        // Aquí podría añadirse lógica adicional de limpieza si fuera necesaria.
    }
}
