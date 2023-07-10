package com.saha.rest.resources.impl;

import com.saha.model.EmployeeEntity;
import com.saha.persistence.PersistenceManager;
import com.saha.rest.model.Employee;
import com.saha.rest.resources.ApiResponseMessage;
import com.saha.rest.resources.EmployeesApiService;
import com.saha.rest.resources.NotFoundException;
import com.saha.service.CrudService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2023-07-09T23:14:58.597+05:30[Asia/Kolkata]")
public class EmployeesApiServiceImpl extends EmployeesApiService {
    private static EntityManager em = PersistenceManager.getEntityManager();
    private static EntityTransaction tx = em.getTransaction();
    private static CrudService<EmployeeEntity> service = new CrudService<>(EmployeeEntity.class, em);

    private static final String SCHEMA_NAME = "openapi";

    @Override
    public Response createEmployee(Long applicationId, Employee employee, SecurityContext securityContext) throws NotFoundException {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.fromDTO(employee);
        try {
            tx.begin();
            service.create(employeeEntity);
            long empId = service.getNextSequenceId(EmployeeEntity.class, SCHEMA_NAME, em);
            if (empId !=0) {
                employee.setId(empId);
            }
            tx.commit();
            return Response.ok().entity(employee).build();
        } catch (Exception e) {
            tx.rollback();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Override
    public Response deleteEmployee(Long applicationId, Long employeeId, SecurityContext securityContext) throws NotFoundException {
        try {
            tx.begin();
            service.removeById(employeeId);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Deletion successful!")).build();
    }

    @Override
    public Response getEmployee(Long applicationId, Long employeeId, SecurityContext securityContext) throws NotFoundException {
        EmployeeEntity employee = null;
        try {
            tx.begin();
            employee = service.findById(employeeId);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        if (employee != null)
            return Response.ok(employee).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getEmployees(Long applicationId, SecurityContext securityContext) throws NotFoundException {
        List<EmployeeEntity> employeeEntites = new ArrayList<>();
        try {
            tx.begin();
            employeeEntites = service.findAll();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        List<Employee> employees = employeeEntites.stream().map(entity -> entity.toDTO()).collect(Collectors.toList());
        return Response.ok().entity(employees).build();
    }

    @Override
    public Response updateEmployee(Long applicationId, Long employeeId, Employee employee, SecurityContext securityContext) throws NotFoundException {
        EmployeeEntity employeeEntity = null;
        try {
            tx.begin();
            employeeEntity = service.findById(employeeId);
            if (employeeEntity == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            employeeEntity.fromDTO(employee);
            service.update(employeeEntity);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        if (employeeEntity != null) {
            Employee updatedEmployee = employeeEntity.toDTO();
            return Response.ok().entity(updatedEmployee).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
