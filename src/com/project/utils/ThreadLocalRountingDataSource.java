package com.project.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {
    @Override
	protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }
}
