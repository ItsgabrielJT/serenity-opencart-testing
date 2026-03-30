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

```text
src/test/java/com/company/automation/
├── abilities/          # Capacidades de los actores (Web, API, DB)
├── config/             # Configuración de infraestructura (DB/Web)
├── models/             # POJOs de datos de negocio
├── tasks/
│   └── web/            # Tasks de interfaz web (Screenplay)
├── questions/
│   └── web/            # Questions UI
├── ui/
│   └── pages/          # UI Targets por página
├── stepdefinitions/    # Step Definitions Cucumber + Hooks
├── runners/            # JUnit Runners (TestSuiteRunner, WebUIRunner)
└── utils/              # Utilidades (Faker, TestDataLoader, ConfigReader)

src/test/resources/
├── features/
│   └── web/            # ui_purchase.feature
├── testdata/           # users.json, products.csv
├── serenity.conf       # Configuración Serenity
└── cucumber.properties # Configuración Cucumber
```

---

## Ejecución

### Todos los tests
```bash
./gradlew test 
```

Este comando ejecuta la suite configurada por defecto en cada invocación. La tarea `test` no reutiliza el estado `UP-TO-DATE`, por lo que siempre lanza los runners JUnit/Serenity detectados.

### Solo UI (Web)
```bash
./gradlew test -Dcucumber.filter.tags="@ui" 
```

### Por runner específico
```bash
./gradlew test --tests "com.company.automation.runners.WebUIRunner" 
./gradlew test --tests "com.company.automation.runners.TestSuiteRunner" 
```

Runners disponibles actualmente:

- `WebUIRunner`: ejecuta los escenarios etiquetados con `@ui`.
- `TestSuiteRunner`: ejecuta la suite general con exclusión de `@ui` y `@wip`.

### Headless (CI)
```bash
./gradlew test \
  -Dcucumber.filter.tags="@ui" \
  -Dchrome.switches="--no-sandbox,--disable-dev-shm-usage,--disable-gpu,--headless=new" \
  
```

---

## Tags disponibles

| Tag          | Descripción                              |
|--------------|------------------------------------------|
| `@ui`        | Pruebas de interfaz web (E2E)            |

*(Nota: los tags de api, db, smoke, regression se añadirán conforme se expandan las pruebas de esos bloques).*

---

## Reportes

El proyecto genera unicamente reportes Serenity. Los reportes HTML de Cucumber fueron eliminados para evitar salidas duplicadas.

Despues de ejecutar `./gradlew test`, el reporte que debes abrir es:

`target/site/serenity/index.html`

Ese archivo es la vista final consolidada de Serenity.

```bash
open target/site/serenity/index.html
```

Nota:

- `target/site/serenity/` contiene el HTML final que debes revisar.
- `target/serenity-reports/` es una carpeta interna de trabajo de Serenity y no es el punto de entrada recomendado para revisar resultados.

---


## Principios aplicados

- **Screenplay Pattern**: Actor → Task → Interaction → Question
- **Single Responsibility**: cada clase hace una sola cosa
- **Open/Closed**: extensible sin modificar código existente
- **Dependency Inversion**: Tasks dependen de Models, no de valores hard-coded
- **DRY**: FakerDataGenerator y TestDataLoader centralizan generación de datos
- **High Cohesion**: packages agrupados por responsabilidad funcional
- **Low Coupling**: Abilities, Tasks y Questions son independientes entre sí
