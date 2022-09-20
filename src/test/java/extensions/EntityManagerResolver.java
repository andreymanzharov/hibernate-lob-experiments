package extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class EntityManagerResolver implements ParameterResolver {

  private static final ExtensionContext.Namespace NAMESPACE =
      ExtensionContext.Namespace.create(EntityManagerFactory.class);

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.PARAMETER)
  public @interface Unit {
    String value() default "experiments";
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getType() == EntityManager.class;
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    var persistenceUnitName = parameterContext.findAnnotation(Unit.class)
        .map(Unit::value)
        .orElse("experiments");
    var entityManagerFactory = extensionContext.getRoot().getStore(NAMESPACE)
        .getOrComputeIfAbsent(persistenceUnitName, Persistence::createEntityManagerFactory, EntityManagerFactory.class);
    return entityManagerFactory.createEntityManager();
  }
}
