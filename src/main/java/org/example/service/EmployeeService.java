package org.example.service;

import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {

    private final EmployeeRepository repository = new EmployeeRepository();

    public void registerEmployee(Employee employee) {
        repository.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.getAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.getById(id);
    }

    public void removeEmployee(Long id) {
        repository.delete(id);
    }
}
