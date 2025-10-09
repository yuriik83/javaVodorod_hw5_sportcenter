package org.example.service;

import org.example.entity.Visitor;
import org.example.repository.VisitorRepository;
import java.util.List;

public class VisitorService {

    private final VisitorRepository repository = new VisitorRepository();

    public void registerVisitor(Visitor visitor) {
        repository.add(visitor);
    }

    public List<Visitor> getAllVisitors() {
        return repository.getAll();
    }
}

