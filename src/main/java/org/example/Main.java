package org.example;

import org.example.entity.Client;
import org.example.service.ClientService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ClientService service = new ClientService();

        service.addClient(new Client("Ivan", "Ivanov", 30, "123456789", LocalDate.now(), Client.Status.ACTIVE, 100.0));
        service.addClient(new Client("Petr", "Petrov", 25, "987111222", LocalDate.now(), Client.Status.PREMIUM, 200.0));
        service.addClient(new Client("Sergey", "Sidorov", 28, "111222333", LocalDate.now(), Client.Status.ACTIVE, 150.0));
        service.addClient(new Client("Olga", "Smirnova", 32, "444555666", LocalDate.now(), Client.Status.BLOCKED, 50.0));
        service.addClient(new Client("Sergey", "Kuznetsov", 40, "777888999", LocalDate.now(), Client.Status.PREMIUM, 500.0));

        System.out.println("All clients:");
        for (Client c : service.getAllClients()) {
            System.out.println(c.getId() + " - " + c.getFirstName() + " " + c.getLastName() + " (" + c.getStatus() + ")");
        }

        System.out.println("\nUpdating client status:");
        service.updateClientStatus(1L, Client.Status.BLOCKED);

        System.out.println("\nDeleting client:");
        service.deleteClient(2L);

        System.out.println("\nAll clients after update:");
        for (Client c : service.getAllClients()) {
            System.out.println(c.getId() + " - " + c.getFirstName() + " " + c.getLastName() + " (" + c.getStatus() + ")");
        }
    }
}
