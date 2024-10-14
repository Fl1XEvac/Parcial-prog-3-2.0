Parcial
>Este proyecto implementa un sistema de detección de mutantes basado en secuencias de ADN.

Hecho con
Java
Spring Boot
Gradle

Uso de la Api
Para verificar una secuencia de ADN, envía una solicitud POST a /mutant/ con el siguiente formato JSON:

```json

{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
   
### La API responderá con:

- 200 OK si la secuencia es de un mutante
- 403 Forbidden si la secuencia no es de un mutante

### Obtener Estadisticas

1. Para obtener las estadisticas, envia una solicitud GET a /mutant/stats
 Respuesta exitosa
- Codigo: 200 OK
- Cuerpo

   
json
   {
    "countMutantDna": 10,
    "countHumanDna": 20,
    "ratio": 0.5
}
