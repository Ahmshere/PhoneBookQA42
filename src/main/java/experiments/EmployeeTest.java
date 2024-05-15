package experiments;

public class EmployeeTest {

    public void testMethod(){
    Employee employee = Employee.builder()
            .name("MyName")
            .age(55)
            .build();
    employee.setAge(45);
    }


}
