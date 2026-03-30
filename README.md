# Serenity BDD — OpenCart Enterprise Automation Framework

Framework de automatizacion QA enfocado en el flujo web de compra de OpenCart, construido con **Java + Serenity BDD + Screenplay Pattern**.

---

## Tecnologías

| Tecnología           | Versión  | Uso                                  |
|----------------------|----------|--------------------------------------|
| Java                 | 17       | Lenguaje base                        |
| Serenity BDD         | 4.2.34   | Orquestador + Reportes               |
| Screenplay Pattern   | 4.2.34   | Arquitectura de actores              |
| Cucumber             | 7.15.0   | BDD / Gherkin                        |
| Gradle               | 8.5      | Build tool                           |
| Chrome WebDriver     | auto     | Automatización web (Serenity auto-dl)|

---

## Estructura del proyecto

```text
src/test/java/com/company/automation/
├── models/             # POJOs de datos de negocio
├── tasks/
│   └── web/            # Tasks de interfaz web (Screenplay)
├── questions/
│   └── web/            # Questions UI
├── ui/
│   └── pages/          # UI Targets por página
├── stepdefinitions/    # Step Definitions Cucumber + Hooks
└── runners/            # JUnit Runner web (WebUIRunner)

src/test/resources/
├── features/
│   └── web/            # ui_purchase.feature
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
```

Runner disponible actualmente:

- `WebUIRunner`: ejecuta los escenarios etiquetados con `@ui`.

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

Actualmente el proyecto fue depurado para conservar unicamente el flujo web activo.

---

## Reportes

El proyecto genera unicamente reportes Serenity. Los reportes HTML de Cucumber fueron eliminados para evitar salidas duplicadas.

Despues de ejecutar `./gradlew test`, el reporte que debes abrir es:

`target/serenity-reports/index.html`

Ese archivo es la vista final consolidada de Serenity.

```bash
open target/serenity-reports/index.html
```

Nota:

- `target/serenity-reports/` contiene el reporte HTML final y los artefactos de Serenity en un solo lugar.

---


## Principios aplicados

- **Screenplay Pattern**: Actor → Task → Interaction → Question
- **Single Responsibility**: cada clase hace una sola cosa
- **Open/Closed**: extensible sin modificar código existente
- **Dependency Inversion**: Tasks dependen de Models, no de valores hard-coded
- **DRY**: el proyecto conserva solo las clases necesarias para la suite web actual
- **High Cohesion**: packages agrupados por responsabilidad funcional
- **Low Coupling**: Abilities, Tasks y Questions son independientes entre sí
