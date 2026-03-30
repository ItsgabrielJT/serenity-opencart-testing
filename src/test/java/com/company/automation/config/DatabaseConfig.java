package com.company.automation.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * DatabaseConfig
 *
 * Singleton que gestiona el pool de conexiones HikariCP.
 * Lee la configuración desde db.properties ubicado en resources.
 *
 * Principio SOLID: Single Responsibility – solo gestiona el ciclo
 * de vida del DataSource.
 */
public class DatabaseConfig {

    private static volatile DatabaseConfig instance;
    private final HikariDataSource dataSource;

    private DatabaseConfig() {
        Properties props = loadProperties();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(props.getProperty("db.url", "jdbc:postgresql://localhost:5432/opencart"));
        config.setUsername(props.getProperty("db.username", "opencart"));
        config.setPassword(props.getProperty("db.password", "opencart"));
        config.setDriverClassName(props.getProperty("db.driver", "org.postgresql.Driver"));
        config.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.pool.size", "10")));
        config.setConnectionTimeout(30_000);
        config.setIdleTimeout(600_000);
        config.setMaxLifetime(1_800_000);
        config.setPoolName("OpenCartTestPool");
        this.dataSource = new HikariDataSource(config);
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            // Si no existe el archivo, se usan los defaults declarados arriba
        }
        return props;
    }
}
