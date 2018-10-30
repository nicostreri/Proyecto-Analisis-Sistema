# Análisis de Proyecto Prode

## Planificación

Se decidió agregar dos nuevas funcionalidades al proyecto:

* **Top 10 de Usuario:** Mostrar la lista de los 10 primeros usuarios del sistema con mayor puntaje obtenido en Apuestas.
* **Estadísticas de Apuesta a un Partido:** Mostrar las Estadísticas sobre cuántos usuarios Apostaron Gana_Local/Empate/Gana_Visitante en un Partido, el objetivo es que el usuario pueda tener encuentra que apostó la comunidad al momento de realizar su apuesta.

### Diagrama Gantt

Se adjunta un Diagrama de Gantt con la Planificación de tareas para las próximas semanas, que incluye la realización de las funcionalidades descritas arriba y la realización de mejoras propuestas por Code Climate.

## Resultado Métricas Estáticas

Se analizó cada una de las clases mediante Code Climate obteniendo en resumen los siguientes resultados:

* Exceso de lineas de codigo por métodos.
* Alta Complejidad cognitiva.
* Declaraciones de flujo de control profundamente anidadas.
* Bloques de códigos idénticos o similares en distintas ubicaciones.

  

  **Nota:** Los Resultados anteriores se vieron presente en las clases relacionadas a los controladores de las rutas principales del proyecto.

## Aplicación Modelo Vista Controlador

El proyecto se realizó pensando en un MVC de manera informal. Analizando la estructura actual del mismo podemos encontrar los 3(tres) componentes principales del Modelo.

**Modelo:** Este es representado por las Clases que se generaron al implementar ActiveJDBC.
**Controlador:** Representado por App.java que define todas las rutas HTTP escuchadas por el programa, y las clases en el paquete __prode.controladores.*__ .
**View:** Representado por las Plantillas HTML definidas para el Template Engine Mustache.
