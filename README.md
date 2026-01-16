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
      +getId() int
      +setId(int)
      +getNombre() String
      +setNombre(String)
      +getnivelPeligro() int
      +setNivelPeligro(int)
      +getMonstruoJefe() Monstruo
      +setMonstruoJefe(Monstruo)
      +getListaMontruos() List~Monstruo~
      +setListaMontruos(List~Monstruo~)
      +getListaDragon() List~Dragon~
      +setListaDragon(List~Dragon~)
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
      +getId() int
      +setId(int)
      +getNombre() String
      +setNombre(String)
      +getIntensidadFuego() int
      +setIntensidadFuego(int)
      +getResistencia() int
      +setResistencia(int)
      +getBosque() Bosque
      +setBosque(Bosque)
      +exhalar(Monstruo)
    }

    class Mago {
      -int id
      -String nombre
      -int vida
      -int nivelMagia
      -List~String~ conjuroNombres
      -List~Hechizo~ conjuro
      +getId() int
      +setId(int)
      +getNombre() String
      +setNombre(String)
      +getVida() int
      +setVida(int)
      +getNivelMagia() int
      +setNivelMagia(int)
      +getConjuro() List~Hechizo~
      +setConjuro(List~Hechizo~)
      +lanzarHechizo(Monstruo)
      +lanzarHechizo(Monstruo, Hechizo)
    }

    class Monstruo {
      -int id
      -String nombre
      -int vida
      -int fuerza
      -Tipo tipo
      -Bosque bosque
      +getId() int
      +setId(int)
      +getNombre() String
      +setNombre(String)
      +getVida() int
      +setVida(int)
      +getFuerza() int
      +setFuerza(int)
      +geTipo() Tipo
      +setTipo(Tipo)
      +getBosque() Bosque
      +setBosque(Bosque)
      +atacar(Mago)
    }

    class Hechizo {
      <<enumeration>>
      BOLA_DE_FUEGO
      BOLA_DE_NIEVE
      RAYO
      PUTREFACCION
      +getNombre() String
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
      -List~Mago~ magosVivos
      -List~Mago~ magosMuertos
      -List~Monstruo~ monstruosVivos
      -List~Monstruo~ monstruosMuertos
      -Dragon dragon
      -Bosque bosque
      +iniciar()
      +crearBosque()
      +crearMonstruo()
      +crearDragon()
      +crearMago()
      +asignarMonstruoJefe()
      +iniciarJuego()
    }

    class Controlador {
      -EntityManagerFactory entityManagerFactory$
      -Controlador instance$
      -ControladorMago controladorMago
      -ControladorMonstruo controladorMonstruo
      -ControladorDragon controladorDragon
      -ControladorBosque controladorBosque
      +getInstance()$ Controlador
      +getEntityManager() EntityManager
      +cerrarEntityManagerFactory()$
      +cerrarSessionFactory()$
      +getControladorMago() ControladorMago
      +getControladorMonstruo() ControladorMonstruo
      +getControladorDragon() ControladorDragon
      +getControladorBosque() ControladorBosque
      +guardarMago(Mago)
      +actualizarMago(Mago)
      +eliminarMago(Mago)
      +obtenerMago(int)
      +guardarBosque(Bosque)
      +actualizarBosque(Bosque)
      +eliminarBosque(Bosque)
      +obtenerBosque(int)
      +guardarMonstruo(Monstruo)
      +actualizarMonstruo(Monstruo)
      +eliminarMonstruo(Monstruo)
      +obtenerMonstruo(int)
      +guardarDragon(Dragon)
      +actualizarDragon(Dragon)
      +eliminarDragon(Dragon)
      +obtenerDragon(int)
    }
    
    class ControladorMago {
      -Controlador controladorPrincipal
      +guardar(Mago)
      +actualizar(Mago)
      +eliminar(Mago)
      +obtener(int) Mago
    }
    
    class ControladorMonstruo {
      -Controlador controladorPrincipal
      +guardar(Monstruo)
      +actualizar(Monstruo)
      +eliminar(Monstruo)
      +obtener(int) Monstruo
    }
    
    class ControladorDragon {
      -Controlador controladorPrincipal
      +guardar(Dragon)
      +actualizar(Dragon)
      +eliminar(Dragon)
      +obtener(int) Dragon
    }
    
    class ControladorBosque {
      -Controlador controladorPrincipal
      +guardar(Bosque)
      +actualizar(Bosque)
      +eliminar(Bosque)
      +obtener(int) Bosque
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
    
    Main --> Interfaz : inicia
    Interfaz --> Monstruo : crea
    Interfaz --> Mago : crea
    Interfaz --> Bosque : crea
    Interfaz --> Dragon : crea
    Interfaz --> Controlador : usa
    
    Controlador "1" --> "1" ControladorMago : delega
    Controlador "1" --> "1" ControladorMonstruo : delega
    Controlador "1" --> "1" ControladorDragon : delega
    Controlador "1" --> "1" ControladorBosque : delega
    
    ControladorMago --> Controlador : usa
    ControladorMonstruo --> Controlador : usa
    ControladorDragon --> Controlador : usa
    ControladorBosque --> Controlador : usa
```

## Diseño

### Diagrama entidad relación

```mermaid
erDiagram
    BOSQUES ||--o{ MONSTRUOS : "contiene"
    BOSQUES ||--o{ DRAGONES : "contiene"
    BOSQUES ||--o| MONSTRUOS : "monstruoJefe"
    MAGOS ||--o{ MAGO_HECHIZOS : "tiene"
    
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
    
    MAGO_HECHIZOS {
        int mago_id FK
        string hechizo
    }
```

## Manual de usuario

[Manual de usuario - Dragonlandia](ManualUsuario.md)

## Tablas después de una ronda
[Estado tablas despues de una ronda de juego](MarcosRey_DragolandiaHibernate.pdf)