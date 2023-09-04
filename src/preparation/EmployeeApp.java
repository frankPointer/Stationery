package preparation;

import preparation.emp.Employee;
import preparation.emp.utils.EmployeeUtils;

public class EmployeeApp {
    public static void main(String[] args) {
        Employee employee = new Employee("zhangsan",20,3000);
        EmployeeUtils.showAllEmp();
    }
}
