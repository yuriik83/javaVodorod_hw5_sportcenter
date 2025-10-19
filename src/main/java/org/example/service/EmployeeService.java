package org.example.service;

import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    public Employee getHighestPaidEmployee() {
        return repository.getHighestPaidEmployee();
    }

    public Employee getLowestPaidEmployee() {
        return repository.getLowestPaidEmployee();
    }

    public BigDecimal calculateTotalSalaryExpenses(LocalDate start, LocalDate end) {
        return repository.calculateTotalSalaryExpenses(start, end);
    }
}
