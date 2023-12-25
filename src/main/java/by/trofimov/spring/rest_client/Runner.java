package by.trofimov.spring.rest_client;

import java.util.List;
import by.trofimov.spring.rest_client.configuration.MyConfig;
import by.trofimov.spring.rest_client.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static by.trofimov.spring.rest_client.entity.util.Constant.COMMUNICATION;

public class Runner {

    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(MyConfig.class)) {

            Communication communication = context.getBean(COMMUNICATION, Communication.class);

            List<Employee> allEmployees = communication.getAllEmployees();
            System.out.println(allEmployees);

            Employee employeById = communication.getEmployee(1);
            System.out.println(employeById);

            Employee employee = new Employee("Sveta", "Sokolova", "HR", 900);
            communication.saveOrUpdateEmployee(employee);

            Employee employeeForUpdate =
                    new Employee("Sveta", "Sokolova", "HR", 1200);
            employeeForUpdate.setId(7);
            communication.saveOrUpdateEmployee(employeeForUpdate);

            communication.deleteEmployee(7);

        }

    }

}
