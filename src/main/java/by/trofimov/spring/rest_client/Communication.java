package by.trofimov.spring.rest_client;

import java.util.List;
import by.trofimov.spring.rest_client.entity.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import static by.trofimov.spring.rest_client.entity.util.Constant.*;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Employee>>() {
                        });
        return responseEntity.getBody();
    }

    public Employee getEmployee(int id) {
        return restTemplate.getForObject(URL + SEPARATOR + id, Employee.class);
    }

    public void saveOrUpdateEmployee(Employee employee) {
        int id = employee.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, employee, String.class);
            System.out.println(NEW_EMPLOYEE_WAS_ADDED);
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println(EMPLOYEE_WITH_ID + id + WAS_UPDATED);
        }
    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + SEPARATOR + id);
        System.out.println(EMPLOYEE_WITH_ID + id + WAS_DELETED);
    }

}
