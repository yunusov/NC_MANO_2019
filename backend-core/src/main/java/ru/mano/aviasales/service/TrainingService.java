package ru.mano.aviasales.service;

import org.springframework.stereotype.Service;
import ru.mano.aviasales.model.Curator;
import ru.mano.aviasales.model.Student;

import javax.annotation.PostConstruct;

@Service
public class TrainingService {
    @PostConstruct
    public void startTraining () { ///
        Curator curator = new Curator();
        Student student = new Student();

        System.out.println(student.sayINeedHelp());
        if (curator.hasEars())
            System.out.println(curator.helpStudent());
    }
}
