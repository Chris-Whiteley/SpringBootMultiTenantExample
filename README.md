# Spring Boot Multi-Tenant Application Example (Schema-Based)

This project is a Spring Boot application demonstrating a multi-tenant architecture where each tenant (customer) has its own dedicated database schema. This setup provides strong data isolation and helps manage client-specific data independently.

## Overview

In this example, each tenant is represented by a company with a unique `companyId`. When a request is made, the application intercepts it to determine the `companyId` from the request headers and uses that information to set the current tenant’s context dynamically. Based on the `companyId`, the application switches to the appropriate schema, ensuring that the application only accesses data associated with that specific tenant.

## How It Works

1. **Tenant Identification**: Each request to the application includes a `companyId` in the HTTP headers. This `companyId` is a UUID that uniquely identifies the tenant.

2. **Tenant Context Management**: A `TenantInterceptor` extracts the `companyId` from the request headers. If found, it sets the current tenant context using a `TenantContext` class.

3. **Schema-Based Database Routing**: The `TenantDataSource` dynamically routes to the correct database schema based on the `companyId` present in the `TenantContext`. This isolates each tenant’s data within their own schema.

4. **Automatic Schema Switching**: For regular business logic in `@Service` classes and `@Repository` interfaces, the schema selection is transparent. When the `companyId` is set in the `TenantContext`, Spring will automatically route queries to the respective schema, so `findAll()` or other methods can be used without manually specifying tenant IDs.

5. **Global Data Tables**: Certain tables, such as `User`, are global and shared across tenants. They contain non-tenant-specific data, allowing shared access for operations like user authentication and authorization.

## Key Components

- **`TenantContext`**: Holds the tenant information (`companyId`) for the current request in a thread-safe manner.
- **`TenantInterceptor`**: Extracts `companyId` from each request’s headers and sets it in the `TenantContext`. If the request does not contain `companyId`, the response returns a `400 Bad Request`.
- **`TenantDataSource`**: Configures schema-based multi-tenancy by switching schemas dynamically based on the `companyId` set in the `TenantContext`.
- **Global `User` Table**: The `User` table is configured as a global table, accessible without a specific tenant context, to handle cross-tenant operations like login and access management.

## Advantages

- **Data Isolation**: Separate schemas provide robust isolation for each tenant’s data, improving security and compliance.
- **Ease of Scaling**: Adding new tenants is as simple as creating a new schema, allowing the application to scale efficiently.
- **Schema Customization**: Allows for custom configurations at the schema level for different tenants, if needed.

## How to Run

1. **Set Up Schemas**: Ensure that schemas for each tenant exist in the database and are accessible by the application.
2. **Run the Application**: Launch the Spring Boot application.
3. **Access Tenant-Specific Data**: Make HTTP requests with the `companyId` in the headers to fetch data for the specific tenant schema.

---

This example is designed to show the advantages of schema-based multi-tenancy in a Spring Boot application. It demonstrates a scalable approach to tenant isolation, offering flexibility for tenant-specific data management while minimizing complexity for developers working with shared resources like the global `User` table.
