import entities.Error;
import extensions.EntityManagerResolver;
import extensions.EntityManagerResolver.Unit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(EntityManagerResolver.class)
class LobTest {

  @Test
  void insert(EntityManager em) {
    em.getTransaction().begin();

    Error error = new Error("error");
    em.persist(error);

    em.getTransaction().commit();
  }

  @Test
  void select(@Unit EntityManager em) {
    var errors = em.createQuery("select e from Error e", Error.class).getResultList();
    assertEquals(1, errors.size());
    assertEquals("error", errors.get(0).getMessage());
  }
}
