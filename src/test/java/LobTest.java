import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class LobTest {

  private static EntityManagerFactory sessionFactory;

  @BeforeAll
  static void setUp() {
    sessionFactory = Persistence.createEntityManagerFactory("ru.andrey.experiments.jpa");
  }

  @Test
  void insert() {
    EntityManager em = sessionFactory.createEntityManager();
  }

}
