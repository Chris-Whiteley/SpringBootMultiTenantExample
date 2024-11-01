package com.cwsoft.multi_tenant.config.datasource;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String companyId = request.getHeader("company-id"); // Fetching company ID from header

        // Check if the URL is for admin or auth
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/admin/") || requestURI.startsWith("/auth/")) {
            TenantContext.setCurrentTenant("GLOBAL"); // Set to global tenant ID
        } else {
            if (companyId != null) {
                TenantContext.setCurrentTenant(companyId); // Set current tenant in context
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return false; // Bad request if company ID is missing
            }
        }
        return true; // Continue with the request
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear(); // Clear context after request
    }
}
