import extensions.EntityManagerResolver;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.persistence.EntityManager;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(2)
@ExtendWith(EntityManagerResolver.class)
public class JooqTest {

  @Test
  void select(EntityManager em) {
    var dsl = DSL.using(SQLDialect.POSTGRES).select(field("message"))
          .from(table("errors e"))
          .where(field("e.message").eq("error"));

      var query = em.createNativeQuery(dsl.getSQL());
      var bindValues = dsl.getBindValues();
      for (int i = 1; i <= bindValues.size(); i++) {
        query.setParameter(i, bindValues.get(0));
      }
      var errors = query.getResultList();
      assertEquals(1, errors.size());
      assertEquals("error", errors.get(0));
  }
}
