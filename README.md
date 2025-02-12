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

* Si l'usuari ja existeix (es comprova per dni duplicat):
  <img width="992" alt="image" src="https://github.com/user-attachments/assets/62a56a9d-6045-4db1-b971-7e1c36945334" />

#### Mostrar tots els usuaris

<img width="637" alt="image" src="https://github.com/user-attachments/assets/6eb08f79-1928-4c06-b659-4b27426ff239" />

* Si no hi ha cap usuari enregistrat:
  <img width="703" alt="image" src="https://github.com/user-attachments/assets/990fea64-be1b-4388-bab0-ab7a9bf196bd" />

#### Cercar un usuari per DNI

<img width="728" alt="image" src="https://github.com/user-attachments/assets/073b8827-52b2-4fc1-a63f-8a4b5b6019d6" />

* Si l'usuari no existeix o no es troba:
  <img width="742" alt="image" src="https://github.com/user-attachments/assets/40647bd7-cecb-47a3-9228-2933419e9b5a" />

#### Eliminar un usuari

<img width="729" alt="image" src="https://github.com/user-attachments/assets/c74ddbcb-c3af-4937-9b64-52db3964d9d7" />

* Si no troba l'usuari:
  <img width="745" alt="image" src="https://github.com/user-attachments/assets/e51921c8-9a38-4cbc-93da-42a8de9bda66" />

### Gestió de llibres

<img width="309" alt="image" src="https://github.com/user-attachments/assets/a28aa5bf-dc28-4f13-94b4-88ad2079d799" />

#### Afegir un nou llibre

<img width="995" alt="image" src="https://github.com/user-attachments/assets/bceb7266-7f10-446b-952d-ec7c27534f76" />

* Si la categoría del llibre no existeix:
  <img width="992" alt="image" src="https://github.com/user-attachments/assets/7e6f4c7c-25d2-401d-8e3b-82c3601ffe59" />

#### Mostrar tots els llibres

#### Cercar un llibre per ID

#### Eliminar un llibre

### Gestió de Categories

#### Afegir una nova categoria

<img width="982" alt="image" src="https://github.com/user-attachments/assets/9e9dd4c9-19e9-48c2-9e6d-c071816d5b13" />

#### Mostrar totes les categories

<img width="1000" alt="image" src="https://github.com/user-attachments/assets/e68c57e0-2e00-4b0e-a301-0b3673ba9617" />

#### Cercar una categoria per ID

<img width="1005" alt="image" src="https://github.com/user-attachments/assets/1e02e6a8-8807-422b-8f74-9a68c5e95cbd" />

* Si no troba la categoria?
  <img width="997" alt="image" src="https://github.com/user-attachments/assets/bd64580c-c771-4a89-ac3f-8428d939e41d" />

#### Eliminar una categoria

<img width="994" alt="image" src="https://github.com/user-attachments/assets/92929f7a-d3bd-4610-80af-7487035d4aed" />

* Si no troba la categoria?
<img width="991" alt="image" src="https://github.com/user-attachments/assets/0e7e6acb-7cac-4762-86b6-63e1b4317815" />

#### Comptar quants llibres té cada categoria

<img width="995" alt="image" src="https://github.com/user-attachments/assets/7cb88d6e-b260-48ab-bf62-7371fa57286e" />

### Gestió de Reserves

#### Afegir una nova reserva
<img width="995" alt="image" src="https://github.com/user-attachments/assets/e02db28e-f654-4bb9-a3f1-c8c695cada60" />
 - En la reserva es mostren els usuaris existents.
 - Es mostren els llibres disponibles.

#### Mostrar totes les reserves

* Si no hi ha reserves:
  <img width="951" alt="image" src="https://github.com/user-attachments/assets/3c3f7a58-ac5a-4cc1-b800-25ca74f43ac8" />


#### Cercar una reserva per ID

* Si no troba la reserva?
<img width="994" alt="image" src="https://github.com/user-attachments/assets/cafd732a-7511-482a-afc1-833966a4fd8e" />

#### Modificar una reserva (data de retorn)

#### Eliminar una reserva
<img width="1001" alt="image" src="https://github.com/user-attachments/assets/2c5380d5-8899-4274-9ccd-b9465262b2cb" />


* Si no troba la reserva?
<img width="992" alt="image" src="https://github.com/user-attachments/assets/1e4097d0-7e63-4230-84ea-faacad297bda" />
