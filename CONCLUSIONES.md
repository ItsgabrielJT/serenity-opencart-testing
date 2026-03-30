# Conclusiones del Ejercicio

## Resultado General

La ejecucion mas reciente del flujo E2E de compra en OpenCart fue funcionalmente exitosa y demostro que el recorrido principal de compra como invitado puede completarse de extremo a extremo sin fallos visibles en la aplicacion bajo prueba.

- Feature ejecutada: `web/ui_purchase`
- Runner ejecutado: `com.company.automation.runners.WebUIRunner`
- Escenarios ejecutados: 1
- Escenarios exitosos: 1
- Escenarios fallidos: 0
- Tiempo total de la ejecucion: ~32 s
- Ejecucion en paralelo: no; ejecucion serial en un navegador Chrome sobre macOS

## Hallazgos Principales

### 1. El flujo critico de compra como invitado se completo de punta a punta

La evidencia del reporte Serenity confirma que el escenario `Guest customer completes a full purchase with two products` finalizo en estado `SUCCESS` y cubrio el flujo completo desde la busqueda de productos hasta la confirmacion final del pedido.

Esto permitio comprobar que:

- La navegacion inicial a la tienda responde correctamente
- La busqueda de productos funciona para `MacBook` e `iPhone`
- La adicion al carrito desde resultados de busqueda se completa sin error
- El acceso al `Shopping Cart` superior lleva al carrito real antes de entrar al checkout
- El carrito conserva simultaneamente los productos `MacBook` e `iPhone`
- El checkout como invitado permite avanzar hasta la confirmacion final de la orden
- El mensaje final contiene `Your order has been placed`

### 2. Las validaciones funcionales cubren el recorrido principal del checkout

La ejecucion no se limito a abrir paginas o validar elementos superficiales. El escenario cubrio puntos de control funcionales relevantes del negocio durante el checkout.

En la ejecucion mas reciente se valido correctamente que:

- El flujo pasa primero por la pagina de `Shopping Cart` y no salta directamente al checkout
- El carrito muestra los dos productos agregados antes de continuar
- La pagina de checkout se muestra despues de pasar por el carrito
- La seccion de billing details aparece y acepta el formulario completo del cliente
- Las secciones de delivery details, delivery method, payment method y confirm order se muestran en secuencia
- La aceptacion de terminos y condiciones permite continuar el flujo
- La pagina final de confirmacion refleja una compra completada

### 3. El flujo se estabilizo forzando navegacion por HTTP en carrito y checkout

Durante el ajuste del escenario se identifico que OpenCart puede exponer enlaces de `Shopping Cart` y `Checkout` que derivan hacia rutas `https`, pero en este ambiente de prueba esas transiciones no son consistentes porque el checkout no opera como un enlace seguro estable. Eso introducia fallas intermitentes o diferencias de carga entre la navegacion esperada y la navegacion real.

Por esa razon la prueba se implemento de esta manera:

- Se hace clic en el enlace real de `Shopping Cart` del sitio para conservar el comportamiento funcional del usuario
- Se valida que ambos productos esten presentes en el carrito antes de continuar
- Si el sitio intenta redirigir a `https` en carrito o checkout, la automatizacion corrige la ruta a `http` sobre el mismo dominio para mantener el flujo estable del ambiente bajo prueba

La decision no cambia la intencion funcional del escenario. Al contrario, evita falsos negativos causados por una configuracion insegura o inconsistente del entorno y permite validar el caso de negocio que realmente interesa: compra completa como invitado con dos productos en el carrito

### 4. Los mayores tiempos del flujo se concentran en transiciones del checkout y confirmacion

Aunque el escenario fue exitoso, el reporte Serenity permite identificar los puntos mas costosos en tiempo dentro del flujo UI. No hubo errores, pero si varios pasos con esperas visibles que conviene monitorear si el ejercicio evoluciona hacia pruebas de estabilidad o rendimiento funcional.

Los pasos con mayor duracion observada fueron:

- Apertura inicial del home: ~3.78 s
- Diligenciamiento de billing details: ~4.06 s
- Confirmacion de la orden: ~4.04 s
- Continue desde billing details: ~3.01 s
- Continue desde delivery details: ~3.02 s
- Continue desde delivery method: ~3.02 s
- Continue desde payment method: ~3.01 s

Esto sugiere que el flujo principal es estable desde el punto de vista funcional, pero que la experiencia del checkout depende de varias transiciones con tiempos no triviales.

## Evidencia de la Ultima Ejecucion

Resumen observado en el reporte Serenity:

- `Number of scenarios = 1`
- `Number of test cases = 1`
- `Passed = 1`
- `Failed = 0`
- `Compromised = 0`
- `Pending = 0`
- `Ignored = 0`
- `Skipped = 0`

Datos relevantes del escenario ejecutado:

- `feature = web/ui_purchase`
- `scenario = Guest customer completes a full purchase with two products`
- `tag = @ui`
- `driver = chrome:customer`
- `context = Chrome, Mac`
- `startTime = 2026-03-30 15:09:27 -05:00`
- `endTime = 2026-03-30 15:09:59 -05:00`
- `resultado final = SUCCESS`

Validaciones finales confirmadas:

- `the shopping cart should contain "MacBook"`
- `the shopping cart should contain "iPhone"`
- `checkout page should display`
- `billing details section should display`
- `delivery details section should display`
- `delivery method section should display`
- `payment method section should display`
- `confirm order section should display`
- `order confirmation page should display`
- `order confirmation message should contain "Your order has been placed"`

## Conclusiones

La implementacion actual demuestra una automatizacion E2E funcional y consistente para el flujo de compra como invitado en OpenCart. En esta ultima ejecucion, el sistema bajo prueba permitio completar la busqueda de productos, el carrito, el checkout y la confirmacion del pedido sin errores funcionales.

La prueba se dejo construida con paso explicito por `Shopping Cart` porque eso representa mejor el recorrido real del usuario y agrega una validacion de negocio importante: comprobar que los dos productos realmente quedaron en el carrito antes de pagar. Adicionalmente, se forzo la continuidad por `http` en carrito y checkout para adaptarse al comportamiento actual del ambiente, donde esas rutas no se comportan de forma confiable bajo `https`.

### Conclusion de calidad

Desde una perspectiva funcional, el ejercicio cumple su objetivo principal: automatizar y validar el flujo critico de compra con evidencia trazable en Serenity. No se observaron fallos, interrupciones del flujo ni inconsistencias en las validaciones finales del escenario. La inclusion del paso por carrito fortalece la cobertura porque evita asumir que la adicion de productos fue correcta sin comprobarla visual y funcionalmente.

### Conclusion practica

La suite actual deja una base valida para ampliar cobertura sobre otros caminos del negocio, como usuarios autenticados, validaciones negativas del checkout, cupones, medios de envio o combinaciones adicionales de productos. Tambien deja identificado que los tiempos mas sensibles del flujo estan en los pasos intermedios de checkout y en la confirmacion de la orden, y que el ambiente debe seguir tratandose como `http` para evitar fallas ajenas al objetivo funcional de la prueba.

## Recomendaciones

1. Mantener este escenario como smoke E2E principal del flujo de compra por su valor funcional sobre el negocio.
2. Agregar escenarios negativos para checkout invitado, especialmente datos incompletos o combinaciones invalidas en direccion, region y terminos.
3. Separar la cobertura por capas, conservando este flujo como validacion E2E y agregando pruebas API o unitarias para validaciones mas puntuales.
4. Monitorear los pasos mas lentos del checkout para detectar degradaciones futuras en la experiencia de compra.
5. Incorporar mas evidencia funcional en Serenity si se requiere auditoria adicional, por ejemplo screenshots en pasos clave o trazabilidad complementaria del pedido generado.
6. Mantener documentado que `Shopping Cart` y `Checkout` deben validarse sobre `http` en este entorno hasta que el sitio tenga una configuracion segura consistente en `https`.

# Anexos

### Reporte consolidado de Serenity

- `target/serenity-reports/index.html`

### Resumen textual generado por Serenity

- `target/serenity-reports/summary.txt`

### Evidencia estructurada del escenario ejecutado

- `target/serenity-reports/786efde285a9a6153374dc7d37e45a271f315f96ff0104c3e87529f7fee26033.json`