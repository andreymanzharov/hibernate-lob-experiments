import entities.Error;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

class LobTest {

  private static EntityManagerFactory sessionFactory;

  @BeforeAll
  static void setUp() {
    sessionFactory = Persistence.createEntityManagerFactory("experiments");
  }

  @Test
  void insert() {
    EntityManager em = sessionFactory.createEntityManager();
    em.getTransaction().begin();

    Error error = new Error(LocalDateTime.now() + ": error");
    em.persist(error);

    em.getTransaction().commit();
  }

}
