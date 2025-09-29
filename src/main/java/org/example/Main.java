package org.example;

import org.example.entity.Client;
import org.example.entity.Room;
import org.example.entity.RoomStatus;
import org.example.entity.Service;
import org.example.service.ClientService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // Добавление клиента
            Client client = new Client("Иван", "Иванов", 25);
            session.persist(client);
            System.out.println("Добавлен клиент: " + client);

            // Поиск клиента по id
            Client found = session.get(Client.class, client.getId());
            System.out.println("Найден клиент: " + found);

            // Добавление услуги
            Service service = new Service("Теннис", 100.0);
            session.persist(service);
            System.out.println("Добавлена услуга: " + service);

            // Добавление помещения
            Room room = new Room("Тренажёрный зал", "R001", 30, RoomStatus.ACTIVE, 500.0);
            session.persist(room);
            System.out.println("Добавлено помещение: " + room);

            // Отсоединение помещения
            session.detach(room);
            room.setIdentifier("002");
            session.persist(room);
            System.out.println("Добавлено помещение через detach: " + room);

            // Смена аренды
            Room updateRoom = session.get(Room.class, room.getId());
            updateRoom.setRentPrice(150.0);
            session.merge(updateRoom);
            System.out.println("Обновлена стоимость аренды: " + updateRoom);

            tx.commit();
        }
    }
}