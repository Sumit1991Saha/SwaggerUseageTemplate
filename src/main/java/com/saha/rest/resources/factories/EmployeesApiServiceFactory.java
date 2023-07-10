package com.saha.rest.resources.factories;

import com.saha.rest.resources.EmployeesApiService;
import com.saha.rest.resources.impl.EmployeesApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-09T23:14:58.597+05:30[Asia/Kolkata]")
public class EmployeesApiServiceFactory {
    private final static EmployeesApiService service = new EmployeesApiServiceImpl();

    public static EmployeesApiService getEmployeesApi() {
        return service;
    }
}
