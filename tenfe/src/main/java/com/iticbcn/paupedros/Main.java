package com.iticbcn.paupedros;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Companyia;
import com.iticbcn.paupedros.model.Tren;
import com.iticbcn.paupedros.model.dao.CompanyiaDAO;
import com.iticbcn.paupedros.model.dao.EstacioDAO;
import com.iticbcn.paupedros.model.dao.TrajecteDAO;
import com.iticbcn.paupedros.model.dao.TrenDAO;

public class Main {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Selecciona l'entitat amb la que vols treballar:");
            System.out.println("1. Companyia");
            System.out.println("2. Trajecte");
            System.out.println("3. Estacio");
            System.out.println("4. Tren");
            System.out.println("0. Sortir");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> gestionarCompanyia(scanner);
                case 2 -> gestionarTrajecte(scanner);
                case 3 -> gestionarEstacio(scanner);
                case 4 -> gestionarTren(scanner);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }

        sessionFactory.close();
    }

    private static void gestionarCompanyia(Scanner scanner) {
        CompanyiaDAO companyiaDAO = new CompanyiaDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de Companyies:");
            System.out.println("1. Afegir Companyia");
            System.out.println("2. Mostrar Companyia per ID");
            System.out.println("3. Actualitzar Companyia");
            System.out.println("4. Esborrar Companyia");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirCompanyia(scanner, companyiaDAO);
                case 2 -> mostrarCompanyiaPerID(scanner, companyiaDAO);
                case 3 -> actualitzarCompanyia(scanner, companyiaDAO);
                case 4 -> esborrarCompanyia(scanner, companyiaDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    private static void gestionarTrajecte(Scanner scanner) {
        TrajecteDAO trajecteDAO = new TrajecteDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de Trajectes:");
            System.out.println("1. Afegir Trajecte");
            System.out.println("2. Mostrar Trajecte per ID");
            System.out.println("3. Actualitzar Trajecte");
            System.out.println("4. Esborrar Trajecte");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirTrajecte(scanner, trajecteDAO);
                case 2 -> mostrarTrajectePerID(scanner, trajecteDAO);
                case 3 -> actualitzarTrajecte(scanner, trajecteDAO);
                case 4 -> esborrarTrajecte(scanner, trajecteDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    private static void gestionarEstacio(Scanner scanner) {
        EstacioDAO estacioDAO = new EstacioDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió d'Estacions:");
            System.out.println("1. Afegir Estació");
            System.out.println("2. Mostrar Estació per ID");
            System.out.println("3. Actualitzar Estació");
            System.out.println("4. Esborrar Estació");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirEstacio(scanner, estacioDAO);
                case 2 -> mostrarEstacioPerID(scanner, estacioDAO);
                case 3 -> actualitzarEstacio(scanner, estacioDAO);
                case 4 -> esborrarEstacio(scanner, estacioDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    private static void gestionarTren(Scanner scanner) {
        TrenDAO trenDAO = new TrenDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de Trens:");
            System.out.println("1. Afegir Tren");
            System.out.println("2. Mostrar Tren per ID");
            System.out.println("3. Actualitzar Tren");
            System.out.println("4. Esborrar Tren");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirTren(scanner, trenDAO);
                case 2 -> mostrarTrenPerID(scanner, trenDAO);
                case 3 -> actualitzarTren(scanner, trenDAO);
                case 4 -> esborrarTren(scanner, trenDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    // Implementació dels mètodes per afegir, mostrar, actualitzar i esborrar
    private static void afegirCompanyia(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introdueix el nom de la companyia:");
        String nom = scanner.nextLine();
        Companyia companyia = new Companyia(nom);
        companyiaDAO.crearCompanyia(companyia);
        System.out.println("Companyia afegida.");

        System.out.println("Vols afegir tren? (s/n)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            TrenDAO tdao = new TrenDAO(sessionFactory);
            Tren tren = afegirTrenToCompanyia(scanner, tdao, companyia.getId());
            companyia.addTren(tren);
        }
        System.err.println(companyia);
    }

    private static void mostrarCompanyiaPerID(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introduce el ID de la compañía:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salto de línea
        Companyia companyia = companyiaDAO.obtenirCompanyia(id);
        if (companyia != null) {
            System.out.println("Compañía encontrada: " + companyia.getNom());
        } else {
            System.out.println("No se ha encontrado ninguna compañía con el ID proporcionado.");
        }
    }

    private static void actualitzarCompanyia(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introdueix l'ID de la companyia a actualitzar:");
        Long id = scanner.nextLong();
        Companyia companyia = companyiaDAO.obtenirCompanyia(id);
        if (companyia != null) {
            System.out.println("Introdueix el nou nom per a la companyia:");
            scanner.nextLine();
            String nouNom = scanner.nextLine();
            companyia.setNom(nouNom);
            companyiaDAO.actualitzarCompanyia(companyia);
            System.out.println("Companyia actualitzada amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap companyia amb l'ID proporcionat.");
        }
    }

    private static void esborrarCompanyia(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introdueix l'ID de la companyia a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia
        Companyia companyia = companyiaDAO.obtenirCompanyia(id);
        if (companyia != null) {
            companyiaDAO.eliminarCompanyia(companyia);
            System.out.println("Companyia eliminada amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap companyia amb l'ID proporcionat.");
        }
    }

    private static void afegirTrajecte(Scanner scanner, TrajecteDAO trajecteDAO) {
        // Implementació de l'afegirTrajecte
    }

    private static void mostrarTrajectePerID(Scanner scanner, TrajecteDAO trajecteDAO) {
        // Implementació de la mostrarTrajectePerID
    }

    private static void actualitzarTrajecte(Scanner scanner, TrajecteDAO trajecteDAO) {
        // Implementació de l'actualitzarTrajecte
    }

    private static void esborrarTrajecte(Scanner scanner, TrajecteDAO trajecteDAO) {
        // Implementació de l'esborrarTrajecte
    }

    private static void afegirEstacio(Scanner scanner, EstacioDAO estacioDAO) {
        // Implementació de l'afegirEstacio
    }

    private static void mostrarEstacioPerID(Scanner scanner, EstacioDAO estacioDAO) {
        // Implementació de la mostrarEstacioPerID
    }

    private static void actualitzarEstacio(Scanner scanner, EstacioDAO estacioDAO) {
        // Implementació de l'actualitzarEstacio
    }

    private static void esborrarEstacio(Scanner scanner, EstacioDAO estacioDAO) {
        // Implementació de l'esborrarEstacio
    }

    private static Tren afegirTrenToCompanyia(Scanner scanner, TrenDAO trenDAO, Long id) {
        System.out.println("Introduix el model:");
        String model = scanner.nextLine();
        CompanyiaDAO cdao = new CompanyiaDAO(sessionFactory);
        Companyia comp = cdao.obtenirCompanyia(id);
        Tren tren = new Tren(model, comp);
        trenDAO.crearTren(tren);
        System.out.println("Tren añadido con éxito.");
        return tren;
    }

    private static Tren afegirTren(Scanner scanner, TrenDAO trenDAO) {
        System.out.println("Introduix el model:");
        String model = scanner.nextLine();
        CompanyiaDAO cdao = new CompanyiaDAO(sessionFactory);
        System.out.println("ID Companyia:");
        Long idComp = scanner.nextLong();
        Companyia comp = cdao.obtenirCompanyia(idComp);
        Tren tren = new Tren(model, comp);
        trenDAO.crearTren(tren);
        System.out.println("Tren añadido con éxito.");
        return tren;
    }

    private static void mostrarTrenPerID(Scanner scanner, TrenDAO trenDAO) {
        System.out.println("Introduce el ID del tren:");
        int id = scanner.nextInt();
        Tren tren = trenDAO.obtenirTren(id);
        if (tren != null) {
            System.out.printf("Tren encontrado: %s\n", tren);
        } else {
            System.out.println("No se ha encontrado ningún tren con el ID proporcionado.");
        }
    }

    private static void actualitzarTren(Scanner scanner, TrenDAO trenDAO) {
        // Implementació de l'actualitzarTren
    }

    private static void esborrarTren(Scanner scanner, TrenDAO trenDAO) {
        // Implementació de l'esborrarTren
    }
}