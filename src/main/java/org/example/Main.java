package org.example;

import org.example.entity.*;
import org.example.entity.Record;
import org.example.service.RecordService;
import org.example.service.RoomService;
import org.example.service.VisitorService;
import org.example.service.EmployeeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        VisitorService visitorService = new VisitorService();
        EmployeeService employeeService = new EmployeeService();

        Address address1 = new Address(null, "Minsk", "Brylya", "10", "220000");
        Visitor visitor = new Visitor();
        visitor.setFirstName("Ivan");
        visitor.setLastName("Ivanov");
        visitor.setBirthYear(1990);
        visitor.setAddress(address1);
        visitor.setStatus(Visitor.Status.VIP);
        visitor.setFirstVisitDate(LocalDateTime.now().minusMonths(6));
        visitor.setLastVisitDate(LocalDateTime.now());
        visitor.setTotalSpent(250.50);

        visitorService.registerVisitor(visitor);

        Address address2 = new Address(null, "Bobruisk", "Lenina", "1", "230000");
        Employee employee = new Employee();
        employee.setFirstName("Andrey");
        employee.setLastName("Petrov");
        employee.setBirthYear(1985);
        employee.setAddress(address2);
        employee.setPosition("Trainer");
        employee.setHireDate(LocalDate.of(2023, 3, 1));
        employee.setMonthlySalary(1800.0);

        employeeService.registerEmployee(employee);

        System.out.println("\nVisitors:");
        visitorService.getAllVisitors().forEach(v ->
                System.out.println(v.getFirstName() + " " + v.getStatus())
        );

        System.out.println("\nEmployees:");
        employeeService.getAllEmployees().forEach(e ->
                System.out.println(e.getFirstName() + " - " + e.getPosition())
        );

        RoomService roomService = new RoomService();
        RecordService recordService = new RecordService();

        Room room = new Room();
        room.setName("Тренажёрный зал");
        roomService.add(room);


        List<Room> allRooms = roomService.getAll();
        allRooms.forEach(r -> System.out.println(r.getName()));

        // Каскадное удаление
        roomService.delete(1L);
        System.out.println("Room deleted");

        Record record = new Record();
        record.setClient(visitor);
        record.setRoom(room);
        record.setDate(LocalDate.now());
        record.setTime(LocalTime.of(10, 30));
        recordService.add(record);

        Room room1 = roomService.getById(1L);
        roomService.delete(room1.getId());

        System.out.println("Room and all records deleted success");
    }
}
