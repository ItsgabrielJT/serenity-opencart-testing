# Serenity BDD — OpenCart Enterprise Automation Framework

Framework de automatización QA empresarial construido con **Java + Serenity BDD + Screenplay Pattern**.

---

## Tecnologías

| Tecnología           | Versión  | Uso                                  |
|----------------------|----------|--------------------------------------|
| Java                 | 17       | Lenguaje base                        |
| Serenity BDD         | 4.1.4    | Orquestador + Reportes               |
| Screenplay Pattern   | 4.1.4    | Arquitectura de actores              |
| Cucumber             | 7.15.0   | BDD / Gherkin                        |
| Gradle               | 8.5      | Build tool                           |
| REST Assured         | 5.4.0    | Pruebas API                          |
| HikariCP + JDBC      | 5.1.0    | Validación base de datos             |
| Jackson              | 2.16.1   | Serialización JSON/CSV               |
| JavaFaker            | 1.0.2    | Generación de datos de prueba        |
| Chrome WebDriver     | auto     | Automatización web (Serenity auto-dl)|

---

## Estructura del proyecto

```
src/test/java/com/company/automation/
├── abilities/          # Capacidades de los actores (Web, API, DB)
├── config/             # Configuración de infraestructura (DB pool)
├── models/             # POJOs de datos de negocio
├── tasks/
│   ├── web/            # Tasks de interfaz web (Screenplay)
│   ├── api/            # Tasks de API REST (Screenplay REST)
│   └── db/             # Tasks de base de datos
├── questions/
│   ├── web/            # Questions UI
│   ├── api/            # Questions API
│   └── db/             # Questions DB
├── ui/
│   └── pages/          # UI Targets por página
├── stepdefinitions/    # Step Definitions Cucumber + Hooks
├── runners/            # JUnit Runners (full, smoke, ui, api)
└── utils/              # Utilidades (Faker, TestDataLoader, ConfigReader)

src/test/resources/
├── features/
│   ├── web/            # ui_purchase.feature
│   └── api/            # api_orders.feature, db_validation.feature
├── testdata/           # users.json, products.csv
├── serenity.conf       # Configuración Serenity
├── cucumber.properties
└── db.properties       # Configuración de base de datos
```

---

## Ejecución

### Todos los tests
```bash
./gradlew test 
```

### Solo smoke
```bash
./gradlew test -Dcucumber.filter.tags="@smoke" 
```

### Solo API
```bash
./gradlew test -Dcucumber.filter.tags="@api" 
```

### Solo UI
```bash
./gradlew test -Dcucumber.filter.tags="@ui" 
```

### Por runner específico
```bash
./gradlew test --tests "com.company.automation.runners.SmokeTestRunner" 
./gradlew test --tests "com.company.automation.runners.APIRunner" 
```

### Headless (CI)
```bash
./gradlew test \
  -Dcucumber.filter.tags="@smoke" \
  -Dchrome.switches="--no-sandbox,--disable-dev-shm-usage,--disable-gpu,--headless=new" \
  
```

---

## Tags disponibles

| Tag          | Descripción                              |
|--------------|------------------------------------------|
| `@smoke`     | Pruebas críticas de humo                 |
| `@regression`| Suite completa de regresión              |
| `@ui`        | Pruebas de interfaz web                  |
| `@api`       | Pruebas de API REST                      |
| `@db`        | Validaciones de base de datos            |
| `@wip`       | En desarrollo (excluidos del CI)         |

---

## Reportes

Los reportes Serenity se generan en `target/serenity-reports/index.html`.

```bash
open target/serenity-reports/index.html
```

---

## Configurar los Locators UI

Los Targets en `src/test/java/com/company/automation/ui/pages/` tienen los XPath vacíos.
Completar inspeccionando el HTML de `http://opencart.abstracta.us/`:

```java
// Ejemplo: HomePageTargets.java
public static final Target SEARCH_INPUT =
        Target.the("search input field")
              .located(By.xpath("//input[@name='search']"));
```

---

## Principios aplicados

- **Screenplay Pattern**: Actor → Task → Interaction → Question
- **Single Responsibility**: cada clase hace una sola cosa
- **Open/Closed**: extensible sin modificar código existente
- **Dependency Inversion**: Tasks dependen de Models, no de valores hard-coded
- **DRY**: FakerDataGenerator y TestDataLoader centralizan generación de datos
- **High Cohesion**: packages agrupados por responsabilidad funcional
- **Low Coupling**: Abilities, Tasks y Questions son independientes entre sí
