package websystique001.dao;
 
import java.util.List;
 
import websystique001.model.Employee;
 
public interface EmployeeDao {
   
      Employee findById(int id);
       
      void saveEmployee(Employee employee);
       
      void deleteEmployeeBySsn(String ssn);
       
      List<Employee> findAllEmployees();
   
      Employee findEmployeeBySsn(String ssn);
       
}
