package by.andervyd.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

// applicationContext.xml
@Configuration
@ComponentScan(basePackages = "by.andervyd") // <context:component-scan base-package="by.andervyd" />
@EnableWebMvc // <mvc:annotation-driven/>
@EnableTransactionManagement // <tx:annotation-driven transaction-manager="transactionManager"/>
public class Config {

    @Bean // <bean id="dataSource" ...
    public DataSource dataSource() {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {

            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course_db?useSSL=false&serverTimezone=UTC");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course_db?useSSL=false&serverTimezone=Europe/Kiev");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course_db?useSSL=false&amp;serverTimezone=UTC");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course_db?useLegacyDatetimeCode=false&serverTimezone=UTC");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/course_db?useUnicode=true&serverTimezone=UTC&useSSL=false");
            dataSource.setUser("root");
            dataSource.setPassword("root");

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean // <bean id="sessionFactory" ...
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("by.andervyd.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean // <bean id="transactionManager" ...
    public HibernateTransactionManager transactionManager() {

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
