# Spring Batch Lab

Este proyecto es un "cajón de sastre" de diferentes ejemplos sencillos de Spring Batch

Este proyecto es un "cajón de sastre" de diferentes ejemplos sencillos de Spring Batch

Cada ejemplo está encapsulado en su propio package y no hay dependencias entre ellos.

Para acceder a la consola de la base de datos H2:

localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

## ejemplo 1

Objetivo:


Para disparar el job1: http://localhost:8080/trigger/job1

## ejemplo 2

Objetivo: Pasar datos del fichero de entrada materiales/entradas/ejemplo02_personas.csv a la tabla PEOPLE

El fichero de entrada tiene 3 líneas de cabecera que se deben descartar.

Se trabaja con chunks de 5

Hay un error en el fichero de entrada (en el elemento 7). Esto nos permite comprobar que los 5 primeros items van a parar a la tabla.

Se realiza un commit por chunk. El step finaliza en estado FAILED.

Se unclue un ItemProcessor<Person,Person> que realiza las siguientes tareas:

	- pierde tiempo (medio segundo por item)
	- transforma a mayúsculas el nombre y el apellido
	
Para disparar el job2: http://localhost:8080/trigger/job2

## ejemplo 3

Objetivo:

Para disparar el job3: http://localhost:8080/trigger/job3

## ejemplo 4

Objetivo: Pasar datos de la tabla PRODUCTS al fichero materiales/salidas/ejemplo04_productos.csv

Se inicluye un ItemProcessor<Product,Product> que no realiza ninguna tarea!

El reader recibe un parámetro String para que la query filtre el producto por familia.

Para disparar el job4: http://localhost:8080/trigger/job4?familia=hardware

## ejemplo 5

Objetivo: Pasar datos de la tabla PRODUCTS al fichero materiales/salidas/ejemplo05_productos.csv

Se inicluye un ItemProcessor<Product,Product> que no realiza ninguna tarea!

El reader recibe dos parámetros (min y max) para filtrar productos entre un rango de precios.

Para disparar el job5: http://localhost:8080/trigger/job5?min=100.0&max=150.76

## ejemplo 7

Objetivo: Utilizar implementaciones sencillas (dummy) de ItemReader e ItemWriter

ItemReader: lee la clase ProductDTO

ItemProcessor: mapea de ProductDTO a Product

ItemWriter: escribe Product


Para disparar el job7: http://localhost:8080/trigger/job7

## ejemplo 8

Objetivo: Leer datos de un fichero XML y escribir en fichero CSV


Para disparar el job8: http://localhost:8080/trigger/job8


## ejemplo 10

Objetivo: Pasar datos de un fichero csv a una tabla, utilizando repositorios de Spring Data (JPA)

Para disparar el job10: http://localhost:8080/trigger/job10

## ejemplo 11

Objetivo:

Para disparar el job11: http://localhost:8080/trigger/job11

## ejemplo 12

Objetivo:

Para disparar el job12: http://localhost:8080/trigger/job12

## ejemplo 13

Objetivo: Ejecutar varios steps de forma secuencial.

Se ejecutan steps de ejercicios anteriores.
La secuencia es: 

	step1, step2, step8, step2 y step2	


Para disparar el job13: http://localhost:8080/trigger/job13

## ejemplo 14

Objetivo: Ejecutar steps en paralelo


Para disparar el job14: http://localhost:8080/trigger/job14

## ejemplo 15

Objetivo: Ejecutar steps condicionales.

El job job15 recibe el parámetro fail. El step conditionalStep recoge el valor de dicho parámetro y resuelve con diferentes códigos de salida.

- fail = 1

	conditionalStep1 establece ExitStatus = FAILED
	
	Se ejecuta: conditionalStep1 -> conditionalStep3

- fail = 2
	
	conditionalStep1 establece ExitStatus = PEPITO
	
	Se ejecuta: conditionalStep1 -> conditionalStep2 -> conditionalStep4 -> conditionalStep5

- fail = [cualquier otro valor]
	
	conditionalStep1 establece ExitStatus = COMPLETED
	
	Se ejecuta: conditionalStep1 -> conditionalStep4 -> conditionalStep5


Para disparar el job15: http://localhost:8080/trigger/job15?fail=1
