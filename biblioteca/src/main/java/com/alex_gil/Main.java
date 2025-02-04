package com.alex_gil;

import com.alex_gil.dao.*;
import com.alex_gil.model.*;
import com.alex_gil.HibernateUtil;
import org.hibernate.SessionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            // Inicialitzar els DAO
            UsuariDAO usuariDAO = new UsuariDAO(sessionFactory);
            LlibreDAO llibreDAO = new LlibreDAO(sessionFactory);
            CategoriaDAO categoriaDAO = new CategoriaDAO(sessionFactory);
            ReservaDAO reservaDAO = new ReservaDAO(sessionFactory);

            boolean sortirApp = false;

            // Bucle del menú principal
            while (!sortirApp) {
                mostrarMenuPrincipal();
                int opcio = Integer.parseInt(br.readLine());

                switch (opcio) {
                    case 1 -> gestionarUsuaris(br, usuariDAO);
                    case 2 -> gestionarLlibres(br, llibreDAO, categoriaDAO);
                    case 3 -> gestionarCategories(br, categoriaDAO);
                    case 4 -> gestionarReserves(br, reservaDAO, usuariDAO, llibreDAO);
                    case 0 -> {
                        System.out.println("👋 Sortint del programa...");
                        sortirApp = true;
                    }
                    default -> System.out.println("⚠ Opció incorrecta! Torna-ho a intentar.");
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error de lectura: " + e.getMessage());
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n📚 MENÚ PRINCIPAL");
        System.out.println("1️⃣ Gestionar Usuaris");
        System.out.println("2️⃣ Gestionar Llibres");
        System.out.println("3️⃣ Gestionar Categories");
        System.out.println("4️⃣ Gestionar Reserves");
        System.out.println("0️⃣ Sortir");
        System.out.print("👉 Tria una opció: ");
    }

    private static void gestionarUsuaris(BufferedReader br, UsuariDAO usuariDAO) throws IOException {
        int opcio;
        do {
            System.out.println("\n👤 GESTIÓ D'USUARIS");
            System.out.println("1️⃣ Afegir Usuari");
            System.out.println("2️⃣ Mostrar Tots els Usuaris");
            System.out.println("3️⃣ Cercar Usuari per ID");
            System.out.println("4️⃣ Eliminar Usuari");
            System.out.println("0️⃣ Tornar al menú principal");
            System.out.print("👉 Tria una opció: ");

            opcio = Integer.parseInt(br.readLine());

            switch (opcio) {
                case 1 -> {
                    System.out.print("📝 Introdueix el nom de l'usuari: ");
                    String nom = br.readLine();

                    System.out.print("📝 Introdueix el dni de l'usuari: ");
                    String dni = br.readLine();

                    System.out.print("📝 Introdueix el email de l'usuari: ");
                    String email = br.readLine();

                    System.out.print("📝 Introdueix el telèfon de l'usuari: ");
                    String telefon = br.readLine();

                    Usuari usuari = new Usuari(dni, nom, telefon, email);

                    usuariDAO.create(usuari);
                    System.out.println("✅ Usuari afegit correctament!");
                }
                case 2 -> {
                    System.out.println("📋 Llistat d'Usuaris:");
                    usuariDAO.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("🔍 Introdueix l'ID de l'usuari: ");
                    int id = Integer.parseInt(br.readLine());
                    Usuari usuari = usuariDAO.read(id);
                    if (usuari != null) {
                        System.out.println("🆔 Usuari trobat: " + usuari);
                    } else {
                        System.out.println("❌ No s'ha trobat cap usuari amb aquest ID.");
                    }
                }
                case 4 -> {
                    System.out.print("🗑 Introdueix l'ID de l'usuari a eliminar: ");
                    int id = Integer.parseInt(br.readLine());
                    Usuari usuari = usuariDAO.read(id);
                    if (usuari != null) {
                        usuariDAO.delete(usuari);
                        System.out.println("✅ Usuari eliminat correctament!");
                    } else {
                        System.out.println("❌ No s'ha trobat cap usuari amb aquest ID.");
                    }
                }
                case 0 -> System.out.println("🔙 Tornant al menú principal...");
                default -> System.out.println("⚠ Opció incorrecta!");
            }
        } while (opcio != 0);
    }

    private static void gestionarLlibres(BufferedReader br, LlibreDAO llibreDAO, CategoriaDAO categoriaDAO)
            throws IOException {
        int opcio;
        do {
            System.out.println("\n📖 GESTIÓ DE LLIBRES");
            System.out.println("1️⃣ Afegir Llibre");
            System.out.println("2️⃣ Mostrar Tots els Llibres");
            System.out.println("3️⃣ Cercar Llibre per ID");
            System.out.println("4️⃣ Eliminar Llibre");
            System.out.println("0️⃣ Tornar al menú principal");
            System.out.print("👉 Tria una opció: ");

            opcio = Integer.parseInt(br.readLine());

            switch (opcio) {
                case 1 -> {
                    System.out.print("📘 Introdueix el títol del llibre: ");
                    String titol = br.readLine();
                    System.out.print("✍ Introdueix l'autor: ");
                    String autor = br.readLine();
                    System.out.print("📅 Introdueix l'any de publicació: ");
                    int any = Integer.parseInt(br.readLine());

                    System.out.println("📚 Categories disponibles:");
                    categoriaDAO.findAll().forEach(System.out::println);

                    System.out.print("📂 Introdueix l'ID de la categoria: ");
                    int idCategoria = Integer.parseInt(br.readLine());

                    Categoria categoria = categoriaDAO.read(idCategoria);
                    if (categoria != null) {
                        Llibre llibre = new Llibre(0, titol, autor, any, categoria);
                        llibreDAO.create(llibre);
                        System.out.println("✅ Llibre afegit correctament!");
                    } else {
                        System.out.println("❌ No s'ha trobat la categoria.");
                    }
                }
                case 2 -> {
                    System.out.println("📖 Llistat de Llibres:");
                    llibreDAO.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("🔍 Introdueix l'ID del llibre: ");
                    int id = Integer.parseInt(br.readLine());
                    Llibre llibre = llibreDAO.read(id);
                    if (llibre != null) {
                        System.out.println("📘 Llibre trobat: " + llibre);
                    } else {
                        System.out.println("❌ No s'ha trobat cap llibre amb aquest ID.");
                    }
                }
                case 4 -> {
                    System.out.print("🗑 Introdueix l'ID del llibre a eliminar: ");
                    int id = Integer.parseInt(br.readLine());
                    Llibre llibre = llibreDAO.read(id);
                    if (llibre != null) {
                        llibreDAO.delete(llibre);
                        System.out.println("✅ Llibre eliminat correctament!");
                    } else {
                        System.out.println("❌ No s'ha trobat cap llibre amb aquest ID.");
                    }
                }
                case 0 -> System.out.println("🔙 Tornant al menú principal...");
                default -> System.out.println("⚠ Opció incorrecta!");
            }
        } while (opcio != 0);
    }

    private static void gestionarCategories(BufferedReader br, CategoriaDAO categoriaDAO) throws IOException {
        int opcio;
        do {
            System.out.println("\n📂 GESTIÓ DE CATEGORIES");
            System.out.println("1️⃣ Afegir Categoria");
            System.out.println("2️⃣ Mostrar Totes les Categories");
            System.out.println("3️⃣ Cercar Categoria per ID");
            System.out.println("4️⃣ Eliminar Categoria");
            System.out.println("5️⃣ Comptar Llibres per Categoria");
            System.out.println("0️⃣ Tornar al menú principal");
            System.out.print("👉 Tria una opció: ");

            opcio = Integer.parseInt(br.readLine());

            switch (opcio) {
                case 1 -> {
                    System.out.print("📂 Introdueix el nom de la categoria: ");
                    String nom = br.readLine();
                    Categoria categoria = new Categoria(nom);
                    categoriaDAO.create(categoria);
                    System.out.println("✅ Categoria afegida correctament!");
                }
                case 2 -> {
                    System.out.println("📂 Llistat de Categories:");
                    categoriaDAO.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("🔍 Introdueix l'ID de la categoria: ");
                    int id = Integer.parseInt(br.readLine());
                    Categoria categoria = categoriaDAO.read(id);
                    if (categoria != null) {
                        System.out.println("📂 Categoria trobada: " + categoria);
                    } else {
                        System.out.println("❌ No s'ha trobat cap categoria amb aquest ID.");
                    }
                }
                case 4 -> {
                    System.out.print("🗑 Introdueix l'ID de la categoria a eliminar: ");
                    int id = Integer.parseInt(br.readLine());
                    Categoria categoria = categoriaDAO.read(id);
                    if (categoria != null) {
                        categoriaDAO.delete(categoria);
                        System.out.println("✅ Categoria eliminada correctament!");
                    } else {
                        System.out.println("❌ No s'ha trobat cap categoria amb aquest ID.");
                    }
                }
                case 5 -> {
                    System.out.println("\n📊 Nombre de llibres per categoria:");
                    List<Object[]> resultats = categoriaDAO.comptarLlibresPerCategoria();
                    if (resultats != null) {
                        for (Object[] fila : resultats) {
                            System.out.println("📚 Categoria: " + fila[0] + " | 📖 Nombre de llibres: " + fila[1]);
                        }
                    } else {
                        System.out.println("⚠ No s'han pogut obtenir els resultats.");
                    }
                }
                case 0 -> System.out.println("🔙 Tornant al menú principal...");
                default -> System.out.println("⚠ Opció incorrecta!");
            }
        } while (opcio != 0);
    }

    private static void gestionarReserves(BufferedReader br, ReservaDAO reservaDAO, UsuariDAO usuariDAO,
            LlibreDAO llibreDAO) throws IOException {
        int opcio;
        do {
            System.out.println("\n📜 GESTIÓ DE RESERVES");
            System.out.println("1️⃣ Afegir Reserva");
            System.out.println("2️⃣ Mostrar Totes les Reserves");
            System.out.println("3️⃣ Cercar Reserva per ID");
            System.out.println("4️⃣ Eliminar Reserva");
            System.out.println("0️⃣ Tornar al menú principal");
            System.out.print("👉 Tria una opció: ");

            opcio = Integer.parseInt(br.readLine());

            switch (opcio) {
                case 1 -> {
                    System.out.println("📜 Creant una nova reserva...");

                    // Seleccionar Usuari
                    System.out.println("👤 Usuaris disponibles:");
                    usuariDAO.findAll().forEach(System.out::println);
                    System.out.print("🔍 Introdueix l'ID de l'usuari que fa la reserva: ");
                    int usuariId = Integer.parseInt(br.readLine());

                    Usuari usuari = usuariDAO.read(usuariId);
                    if (usuari == null) {
                        System.out.println("❌ No s'ha trobat cap usuari amb aquest ID.");
                        break;
                    }

                    // Seleccionar llibres disponibles
                    System.out.println("📖 Llibres disponibles:");
                    List<Llibre> llibresDisponibles = llibreDAO.obtenirLlibresDisponibles();
                    if (llibresDisponibles.isEmpty()) {
                        System.out.println("⚠ No hi ha llibres disponibles per reservar.");
                        break;
                    }
                    llibresDisponibles.forEach(System.out::println);

                    System.out.print("📘 Introdueix l'ID del llibre a reservar: ");
                    int llibreId = Integer.parseInt(br.readLine());
                    Llibre llibre = llibreDAO.read(llibreId);

                    if (llibre == null || llibre.getReserva() != null) {
                        System.out.println("❌ Aquest llibre no està disponible.");
                        break;
                    }

                    // Crear reserva
                    Reserva reserva = new Reserva();
                    reserva.setUsuari(usuari);
                    reservaDAO.create(reserva);

                    // Assignar el llibre a la reserva
                    llibre.setReserva(reserva);
                    llibreDAO.update(llibre);

                    System.out.println("✅ Reserva creada correctament!");
                }
                case 2 -> {
                    System.out.println("📜 Llistat de Reserves:");
                    reservaDAO.findAll().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("🔍 Introdueix l'ID de la reserva: ");
                    int id = Integer.parseInt(br.readLine());
                    Reserva reserva = reservaDAO.read(id);
                    if (reserva != null) {
                        System.out.println("📜 Reserva trobada: " + reserva);
                    } else {
                        System.out.println("❌ No s'ha trobat cap reserva amb aquest ID.");
                    }
                }
                case 4 -> {
                    System.out.print("🗑 Introdueix l'ID de la reserva a eliminar: ");
                    int id = Integer.parseInt(br.readLine());
                    Reserva reserva = reservaDAO.read(id);
                    if (reserva != null) {
                        reservaDAO.delete(reserva);
                        System.out.println("✅ Reserva eliminada correctament!");
                    } else {
                        System.out.println("❌ No s'ha trobat cap reserva amb aquest ID.");
                    }
                }
                case 0 -> System.out.println("🔙 Tornant al menú principal...");
                default -> System.out.println("⚠ Opció incorrecta!");
            }
        } while (opcio != 0);
    }

}
