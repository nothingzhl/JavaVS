package zhl.study.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Tuple;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import zhl.study.entity.UserEntity;

/**
 * Dao测试类
 */
class UserDaoTest {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Test
    void testSave() {
        Session session = ourSessionFactory.openSession();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("zhanghanlin");
        userEntity.setPassword("13956668172");
        Object save = session.save(userEntity);
        UserEntity userEntity1 = session.get(UserEntity.class, save);
        System.out.println(userEntity1);
    }

    @Test
    void testPersist() {
        EntityManager entityManager =
                ourSessionFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("zhanghanlin");
        userEntity.setPassword("1395668172");
        entityManager.persist(userEntity);
        transaction.commit();
    }

    @Test
    void testValidation() {
        // 验证器工厂
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        //验证器
        Validator validator = validatorFactory.getValidator();

        UserEntity userEntity = new UserEntity();

        Set<ConstraintViolation<UserEntity>> validate = validator.validate(userEntity);

        validate.stream()
                .map(item->item.getPropertyPath().iterator().next().getName())
                .forEach(System.out::println);
    }

    @Test
    void testMetaModel() {
        EntityManager entityManager = ourSessionFactory.createEntityManager();
        Metamodel metamodel = entityManager.getMetamodel();
        Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();
        managedTypes.stream()
                .map(item->item.getPersistenceType())
                .forEach(System.out::println);
        managedTypes.stream()
                .flatMap(item->item.getSingularAttributes().stream())
                .map(it->new Pair(it.getName(),it.getJavaType().getSimpleName()))
                .forEach(System.out::println);

    }
}