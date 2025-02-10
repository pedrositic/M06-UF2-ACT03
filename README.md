# Projecte Tenfe

Aquest projecte és una aplicació de gestió de trens, estacions, companyies i trajectes utilitzant Hibernate com a ORM i una base de dades MariaDB.

## Estructura del Projecte

El projecte està organitzat en els següents paquets i arxius:

- `com.iticbcn.paupedros.model`: Conté les classes de model com `Companyia`, `Tren`, `Estacio` i `Trajecte`.
- `com.iticbcn.paupedros.model.dao`: Conté les classes DAO (Data Access Object) per interactuar amb la base de dades.
- `com.iticbcn.paupedros`: Conté la classe principal `Main` i la utilitat `HibernateUtil`.
---
## Configuració de Hibernate

La configuració de Hibernate es troba en l'arxiu `hibernate.cfg.xml`:

```xml
<hibernate-configuration>
  <session-factory>
    <property name="connection.driver_class">org.mariadb.jdbc.Driver</property>
    <property name="connection.url">jdbc:mariadb://localhost:3309/tenfe2</property>
    <property name="connection.username">root</property>
    <property name="connection.password">system</property>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="show_sql">true</property>
    <property name="hbm2ddl.auto">update</property>
    <mapping class="com.iticbcn.paupedros.model.Tren" />
    <mapping class="com.iticbcn.paupedros.model.Trajecte" />
    <mapping class="com.iticbcn.paupedros.model.Estacio" />
    <mapping resource="companyia.hbm.xml"/>
  </session-factory>
</hibernate-configuration>
```
---
## Sistema DAO

El sistema DAO s'utilitza per interactuar amb la base de dades de manera abstracta. Aquí hi ha un exemple de la classe `CompanyiaDAO`:

```java
public class CompanyiaDAO {
  private final SessionFactory sessionFactory;

  public CompanyiaDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void crearCompanyia(Companyia comp) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.persist(comp);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperat: " + e.getMessage());
        }
      }
    }
  }

  // Altres mètodes CRUD...
}
```
---
## Interacció amb l'Usuari

La interacció amb l'usuari es maneja a la classe `Main`, on es presenten opcions en un menú per gestionar companyies, trajectes, estacions i trens:

```java
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
                default -> System.out.println("Opció invàlida. Si us plau, torna a provar.");
            }
        }

        sessionFactory.close();
    }

    // Mètodes per gestionar entitats...
}
```
--- 
## Sentències HQL

El projecte utilitza HQL (Hibernate Query Language) per realitzar consultes a la base de dades. Aquí hi ha un exemple d'una consulta HQL a `TrajecteDAO`:

```java
public Map<String, Long> countTrajectesPerTren() {
    try (Session session = sessionFactory.openSession()) {
      String hql = "SELECT t.tren.model, COUNT(t.id) FROM Trajecte t GROUP BY t.tren.model";
      Query<Object[]> query = session.createQuery(hql, Object[].class);
      List<Object[]> results = query.list();

      Map<String, Long> resultMap = new HashMap<>();
      for (Object[] result : results) {
        String trenModel = (String) result[0];
        Long count = (Long) result[1];
        resultMap.put(trenModel, count);
      }
      return resultMap;
    } catch (Exception e) {
      System.err.println("Error en executar countTrajectesPerTren: " + e.getMessage());
      e.printStackTrace();
      return Collections.emptyMap(); // Retorna un mapa buit en cas d'error
    }
}
```
---
## Scripts de Base de Dades

El projecte inclou un script SQL per crear les taules necessàries a la base de dades:

```sql
CREATE TABLE companyia (
    id INTEGER PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE tren (
    id INTEGER PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    companyia_id INTEGER,
    FOREIGN KEY (companyia_id) REFERENCES companyia(id)
);

CREATE TABLE trajecte (
    id INTEGER PRIMARY KEY,
    origen VARCHAR(255),
    desti VARCHAR(255),
    tren_id INTEGER,
    FOREIGN KEY (tren_id) REFERENCES tren(id)
);

CREATE TABLE estacio (
    id INTEGER PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);

CREATE TABLE trajecte_estacio (
    trajecte_id INTEGER,
    estacio_id INTEGER,
    FOREIGN KEY (trajecte_id) REFERENCES trajecte(id),
    FOREIGN KEY (estacio_id) REFERENCES estacio(id)
);
```
---
## Dependències

Les dependències del projecte estan gestionades per Maven i es troben a l'arxiu `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.11</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.4.4.Final</version>
    </dependency>
    <dependency>
        <groupId>org.mariadb.jdbc</groupId>
        <artifactId>mariadb-java-client</artifactId>
        <version>3.5.1</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.24.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.24.3</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.5.16</version>
    </dependency>
</dependencies>
```
---