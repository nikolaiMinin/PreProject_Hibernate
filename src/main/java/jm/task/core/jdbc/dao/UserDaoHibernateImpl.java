package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory;
    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }
    @Override
    public void createUsersTable() {

        String sql = """ 
                CREATE TABLE IF NOT EXISTS `users_database`.`users_table` (
                `user_id` BIGINT(3) NOT NULL AUTO_INCREMENT,
                `user_name` VARCHAR(45) NOT NULL,
                `user_lastname` VARCHAR(45) NOT NULL,
                `user_age` SMALLINT(3) NOT NULL,
                PRIMARY KEY (`user_id`))
                """;

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS `users_database`.`users_table`";

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.printf("User с именем – %s добавлен в базу данных",name);
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> userList = session.createQuery("from User").getResultList();

        for (User user: userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE `users_database`.`users_table`";

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
    }
}
