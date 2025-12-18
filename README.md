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
      -int nivelPeligro
      -List~Monstruo~ listaMontruos
      -List~Dragon~ listaDragon
      +mostrarJefe()
      +cambiarJefe(Monstruo)
      +addMonstruo(Monstruo)
      +addDragon(Dragon)
    }

    class Dragon {
      -int id
      -String nombre
      -int intensidadFuego
      -int resistencia
      -Bosque bosque
      +exhalar(Monstruo)
    }

    class Mago {
      -int id
      -String nombre
      -int vida
      -int nivelMagia
      -List~Hechizo~ conjuro
      +lanzarHechizo(Monstruo)
      +lanzarHechizo(Monstruo, Hechizo)
    }

    class Monstruo {
      -int id
      -String nombre
      -int fuerza
      -int vida
      -Tipo tipo
      -Bosque bosque
      +atacar(Mago)
    }

    class Hechizo {
      <<enumeration>>
      BOLA_DE_FUEGO
      BOLA_DE_NIEVE
      RAYO
      PUTREFACCION
    }

    class Tipo {
      <<enumeration>>
      OGRO
      TROLL
      SPECTRO
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

    Bosque "1" --> "0..1" Monstruo : monstruoJefe
    Bosque "1" --> "*" Monstruo : listaMonstruos
    Bosque "1" --> "*" Dragon : listaDragon
    Monstruo "*" --> "1" Tipo : tipo
    Monstruo "*" --> "0..1" Bosque : bosque
    Dragon "*" --> "0..1" Bosque : bosque
    Dragon --> Monstruo : exhalar
    Mago "*" --> "*" Hechizo : conjuro
    Mago --> Monstruo : lanzarHechizo
    Monstruo --> Mago : atacar
    
    Main --> Interfaz : Inicia
    Interfaz --> Monstruo : Crea
    Interfaz --> Mago : Crea
    Interfaz --> Bosque : Crea
    Interfaz --> Controlador : Usa
```

## Diseño

### Diagrama entidad relación

```mermaid
erDiagram
    BOSQUES ||--o{ MONSTRUOS : "contiene"
    BOSQUES ||--o{ DRAGONES : "contiene"
    BOSQUES ||--o| MONSTRUOS : "monstruoJefe"
    
    BOSQUES {
        int id PK
        int monstruo_jefe_id FK
        string nombre
        int nivelPeligro
    }
    
    MONSTRUOS {
        int id PK
        string nombre
        int vida
        int fuerza
        string tipo
        int bosque_id FK
    }
    
    DRAGONES {
        int id PK
        string nombre
        int intensidadFuego
        int resistencia
        int bosque_id FK
    }
    
    MAGOS {
        int id PK
        string nombre
        int vida
        int nivelMagia
    }
```
