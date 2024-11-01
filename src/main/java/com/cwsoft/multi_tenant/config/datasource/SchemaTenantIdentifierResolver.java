package com.cwsoft.multi_tenant.config.datasource;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return TenantContext.getCurrentTenant(); // Retrieve tenantId from TenantContext
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true; // Validate existing sessions if needed
    }
}
