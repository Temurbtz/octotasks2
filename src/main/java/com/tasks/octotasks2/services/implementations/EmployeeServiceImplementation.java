package com.tasks.octotasks2.services.implementations;

import com.tasks.octotasks2.entities.Employee;
import com.tasks.octotasks2.repositories.EmployeeRepository;
import com.tasks.octotasks2.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Scheduled(fixedRate = 5000)
    @Override
    public void addEmployee() {
        Employee employee=Employee.builder()
                .firstName(randomStringGenerator())
                .lastName(randomStringGenerator())
                .salary(randomDoubleGenerator())
                .build();
        repository.save(employee);
    }

    @Scheduled(fixedRate = 5000)
    @Override
    public void getEmployees() {
       List<Employee> employees=repository.findAll();
       System.out.println("Number of employees in database:"+employees.size());
    }

    public String randomStringGenerator(){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public Double randomDoubleGenerator(){
        Random r = new Random();
        return 1234.5678 + (3456677.56 - 1234.5678) * r.nextDouble();
    }
}
