# Sudoku 6x6

Juego de Sudoku desarrollado en Java con JavaFX como proyecto universitario para el curso de Fundamentos de Programación Orientada a Eventos - Universidad del Valle 2026-1.

---

## Descripción

El objetivo del juego es completar una cuadrícula de 6x6 con números del 1 al 6, sin repetir en cada fila, columna y bloque de 2x3.

---

## Tecnologías

| Herramienta | Versión |
|---|---|
| Java | 21 |
| JavaFX | 21 |
| Scene Builder | - |
| IntelliJ IDEA | - |
| Git / GitHub | - |

---

## Funcionalidades

- Generación automática de tableros válidos
- Validación en tiempo real de los números ingresados
- Resaltado de celdas con errores
- Opción de ayuda con sugerencias válidas
- Detección automática de tablero completo

---

## Estructura del proyecto

~~~
src/main/java/com/example/sudokubs/
├── Applications/
│   └── SudokuApplication.java
├── Controllers/
│   ├── StartController.java
│   └── SudokuController.java
├── Model/
│   ├── datastructures/
│   │   ├── IDequeue.java
│   │   └── Dequeue.java
│   ├── SudokuBoard.java
│   ├── SudokuBoardValidator.java
│   └── SudokuValidator.java
└── Utils/
    └── Paths.java
~~~

---

## Cómo ejecutar

1. Clona el repositorio
2. Abre el proyecto en IntelliJ IDEA
3. Ejecuta la clase `Launcher.java`

---

## Autores

- **Benjamín Lopera** - 2515144
- **Sebastián Martínez** - 2519817
