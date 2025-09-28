package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Client;
import java.util.List;

public class ClientService {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sportcenterPU");

    public void addClient(Client client) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.close();
    }

    public List<Client> getAllClients() {
        EntityManager em = emf.createEntityManager();
        List<Client> clients = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        em.close();
        return clients;
    }

    public void deleteClient(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        if (client != null) {
            em.remove(client);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void updateClientStatus(Long id, Client.Status newStatus) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Client client = em.find(Client.class, id);
        if (client != null) {
            client.setStatus(newStatus);
        }
        em.getTransaction().commit();
        em.close();
    }
}
