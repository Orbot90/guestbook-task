package ru.orbot90.guestbook;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Iurii Plevako orbot90@gmail.com
 **/
@Component
public class DBMigratingContextListener {

    private final Flyway flyway;

    @Autowired
    public DBMigratingContextListener(Flyway flyway) {
        this.flyway = flyway;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void migrateDatabase() {
        flyway.validate();
        flyway.migrate();
    }
}