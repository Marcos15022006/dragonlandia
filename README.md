# Dragonlandia

## Descripcion
En este proyecto vamos a implementar una aplicación que va a gestionar combates entre diferentes clases que representan a personjes fantasticos con persistencia de datos en una base de datos. En la rama main no estan implementados los dragones

## Análisis

### Diagrama clases

 ```mermaid
    classDiagram
       
        class Bosque {
          -int id
          -Monstruo monstruoJefe
          -String nombre
          -int peligro
          +mostrarJefe()
          +cambiarJefe()
        }
        class Mago {
          -int id
          -String nombre
          -int vida
          -int nivelmagia
          +lanzarhechizo()

        }
        class Monstruo {
          -int id
          -String Nombre
          -int fuerza
          -int vida
          -Tipo tipo
          -Bosque bosque
          -List<Tipo> tipo
        }


        class Interfaz {
        -Scanner scanner
        -Controlador controlador
        -List~Monstruo~ monstruos
        +crearMonstruo(boolean) Monstruo
        +iniciar()
        }

        class Controlador {
        -SessionFactory sessionFactory
        -getSessionFactory() SessionFactory
        +cerrarSessionFactory()
        +guardarMago(Mago)
        +guardarBosque(Bosque)
        +guardarMonstruo(Monstruo)
        +combate(Monstruo, Mago) String
        }
        
        class Main {
        +main(String[])
        }

        Main --> Interfaz : Inicia
        Interfaz  "1" --> "1"  Monstruo : Crea
        Interfaz  "1" --> "1"  Mago : Crea
        Interfaz  "1" --> "1"  Bosque : Crea
        Interfaz  "1" --> "1"  Controlador : Crea 
    ```
## Diseño

### Diagrama entidad relación