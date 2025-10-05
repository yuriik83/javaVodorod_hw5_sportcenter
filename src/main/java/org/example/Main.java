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

            // Клиенты
            Address address1 = new Address("Minsk", "Brylya", "10", "220000");
            Client client1 = new Client();
            client1.setFirstName("Ivan");
            client1.setLastName("Ivanov");
            client1.setPremium(true);
            client1.setAddress(address1);

            Address address2 = new Address("Bobruisk", "Lenina", "1", "230000");
            Client client2 = new Client();
            client2.setFirstName("Andrey");
            client1.setLastName("Ivanov");
            client2.setPremium(false);
            client2.setAddress(address2);

            session.persist(client1);
            session.persist(client2);

            // Комнаты
            Room room1 = new Room(
                    "Бассейн",
                    "POOL",
                    4,
                    RoomStatus.ACTIVE,
                    50.0
            );

            Room room2 = new Room(
                    "Теннис",
                    "TENNIS",
                    22,
                    RoomStatus.REPAIR,
                    120.0
            );

            Room room3 = new Room(
                    "Тренажёрный зал",
                    "GYM01",
                    15,
                    RoomStatus.ACTIVE,
                    70.0
            );

            session.persist(room1);
            session.persist(room2);
            session.persist(room3);

            tx.commit();

            // Отображение Premium клиентов
            System.out.println("\nPremium Clients:");
            List<PremiumClient> premiumClients = session.createQuery("from PremiumClient", PremiumClient.class).list();
            for (PremiumClient client : premiumClients) {
                System.out.printf("ID: %d, Name: %s%n", client.getId(), client.getFirstName());
            }

            // Отображение малых комнат
            System.out.println("\nSmall Rooms:");
            List<SmallRoom> smallRooms = session.createQuery("from SmallRoom", SmallRoom.class).list();
            for (SmallRoom smallRoom : smallRooms) {
                System.out.printf(
                        "ID: %d, Name: %s, Capacity: %d, Price: %.2f%n",
                        smallRoom.getId(),
                        smallRoom.getName(),
                        smallRoom.getCapacity(),
                        smallRoom.getPriceByHour()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}