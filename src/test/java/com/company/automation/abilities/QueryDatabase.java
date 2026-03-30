package com.company.automation.abilities;

import com.company.automation.config.DatabaseConfig;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.HasTeardown;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class QueryDatabase implements Ability, HasTeardown {

    private final DataSource dataSource;

    private QueryDatabase(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public static QueryDatabase usingDefaultConfiguration() {
        return new QueryDatabase(DatabaseConfig.getInstance().getDataSource());
    }

    public static QueryDatabase using(DataSource dataSource) {
        return new QueryDatabase(dataSource);
    }

    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    
    public static QueryDatabase as(Actor actor) {
        return actor.abilityTo(QueryDatabase.class);
    }

    @Override
    public void tearDown() {
        // El pool HikariCP se cierra a través de DatabaseConfig al finalizar la suite.
        // Aquí podría añadirse lógica adicional de limpieza si fuera necesaria.
    }
}
