package org.example;

import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Client client1 = new Client();
            client1.setName("Ivan");
            client1.setPremium(true);
            Address address = new Address();
            address.setCity("Minsk");
            address.setStreet("Brylya");
            address.setHouseNumber("10");
            address.setPostalCode("220000");
            client1.setAddress(address);

            Client client2 = new Client();
            client2.setName("Andrey");
            client2.setPremium(false);
            Address addr2 = new Address();
            addr2.setCity("Bobryisk");
            addr2.setStreet("Lenina");
            addr2.setHouseNumber("1");
            addr2.setPostalCode("230000");
            client2.setAddress(addr2);

            session.persist(client1);
            session.persist(client2);

            Room room1 = new Room();
            room1.setName("Бассейн");
            room1.setCapacity(4);
            room1.setPricePerHour(50);

            Room room2 = new Room();
            room2.setName("Теннис");
            room2.setCapacity(22);
            room2.setPricePerHour(120);

            Room room3 = new Room();
            room3.setName("Тренажерный зал");
            room3.setCapacity(15);
            room3.setPricePerHour(70);

            session.persist(room1);
            session.persist(room2);
            session.persist(room3);

            tx.commit();

            System.out.println("\n Premium Clients");
            List<PremiumClient> premiumClients = session.createQuery("from PremiumClient", PremiumClient.class).list();
            for (PremiumClient client : premiumClients) {
                System.out.printf("ID: %d, Name: %s%n", client.getId(), client.getFirstName());
            }

            System.out.println("\n Small Rooms");
            List<SmallRoom> smallRooms = session.createQuery("from SmallRoom", SmallRoom.class).list();
            for (SmallRoom smallRoom : smallRooms) {
                System.out.printf("ID: %d, Name: %s, Capacity: %d, Price: %.2f%n",
                        smallRoom.getId(), smallRoom.getName(), smallRoom.getCapacity(), smallRoom.getPriceByHour());
            }
        }
    }
}