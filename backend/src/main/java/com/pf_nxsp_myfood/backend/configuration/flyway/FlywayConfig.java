package com.pf_nxsp_myfood.backend.configuration.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        flyway.migrate();
    }
}
