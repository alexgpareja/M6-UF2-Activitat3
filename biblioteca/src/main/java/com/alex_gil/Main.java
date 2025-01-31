package com.alex_gil;

import com.alex_gil.dao.*;
import com.alex_gil.model.*;
import com.alex_gil.HibernateUtil;
import org.hibernate.SessionFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
}
