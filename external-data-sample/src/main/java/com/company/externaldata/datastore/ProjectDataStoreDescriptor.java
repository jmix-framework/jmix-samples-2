package com.company.externaldata.datastore;

import io.jmix.core.metamodel.model.StoreDescriptor;
import org.springframework.stereotype.Component;

@Component("sample_ProjectDataStoreDescriptor")
public class ProjectDataStoreDescriptor implements StoreDescriptor {

    @Override
    public String getBeanName() {
        return "sample_ProjectDataStore";
    }

    @Override
    public boolean isJpa() {
        return false;
    }
}
