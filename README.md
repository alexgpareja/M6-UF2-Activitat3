# M6-UF2-Activitat3
Aquesta activitat té com a objectiu desenvolupar una aplicació de gestió de biblioteca utilitzant Hibernate com ORM. L’aplicació permet gestionar usuaris, llibres, categories i reserves, fent ús de la persistència en una base de dades relacional.

## Tecnologies utilitzades
✔ Java 17+
✔ Hibernate (ORM) 6.x
✔ Jakarta Persistence API (JPA)
✔ Maven (Gestió de dependències)
✔ MariaDB (Base de dades)

## Estructura del projecte
```bash
└── alexgpareja-m6-uf2-activitat3/
    ├── README.md
    └── biblioteca/
        ├── pom.xml
        ├── src/
        │   └── main/
        │       ├── java/
        │       │   └── com/
        │       │       └── alex_gil/
        │       │           ├── HibernateUtil.java
        │       │           ├── Main.java
        │       │           ├── MainTest.java
        │       │           ├── dao/
        │       │           │   ├── CategoriaDAO.java
        │       │           │   ├── LlibreDAO.java
        │       │           │   ├── ReservaDAO.java
        │       │           │   └── UsuariDAO.java
        │       │           └── model/
        │       │               ├── Categoria.java
        │       │               ├── Llibre.java
        │       │               ├── Reserva.java
        │       │               └── Usuari.java
        │       └── resources/
        │           ├── hibernate.cfg.xml
        │           └── reserva.hbm.xml
        └── target/
            └── classes/
                ├── Reserva.hbm.xml
                ├── hibernate.cfg.xml
                └── com/
                    └── alex_gil/
                        ├── dao/
                        └── model/

```

## Funcionalitats principals

### Menú de navegació
<img width="692" alt="image" src="https://github.com/user-attachments/assets/8a2b445c-7b61-41eb-9e1e-8f578828e41d" />


### Gestió d’Usuaris
<img width="435" alt="image" src="https://github.com/user-attachments/assets/7ff5ae2e-a41e-4605-95c4-9a6bd9f3aa03" />

#### Afegir un nou usuari
<img width="998" alt="image" src="https://github.com/user-attachments/assets/2f19b262-045d-42b8-b145-6520c2106e8a" />

Mostrar tots els usuaris
Cercar un usuari per DNI
Eliminar un usuari

### Gestió de llibres
<img width="309" alt="image" src="https://github.com/user-attachments/assets/a28aa5bf-dc28-4f13-94b4-88ad2079d799" />


#### Afegir un nou llibre
#### Mostrar tots els llibres
#### Cercar un llibre per ID
#### Eliminar un llibre

### Gestió de Categories
#### Afegir una nova categoria
#### Mostrar totes les categories
#### Cercar una categoria per ID
#### Eliminar una categoria
#### Comptar quants llibres té cada categoria

### Gestió de Reserves
#### Afegir una nova reserva
#### Mostrar totes les reserves
#### Cercar una reserva per ID
#### Modificar una reserva (data de retorn i llibres)
#### Eliminar una reserva

