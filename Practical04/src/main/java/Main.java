import java.util.List;

public class Main {
    public static void main(String[] args) {
        Employees employee001 = new Employees(1, "123451111", "Алексей", 5);
        Employees employee002 = new Employees(2, "123452222", "Борис", 1);
        Employees employee003 = new Employees(3, "123453333", "Вадим", 6);
        Employees employee004 = new Employees(4, "123454444", "Гриша", 12);
        Employees employee005 = new Employees(5, "123455555", "Елена", 4);
        Employees employee006 = new Employees(6, "123456666", "Женя", 16);
        Employees employee007 = new Employees(7, "123457777", "Зинаида", 24);
        Employees employee008 = new Employees(8, "123458888", "Иннокентий", 40);
        Employees employee009 = new Employees(9, "123459999", "Кирилл", 10);
        Employees employee010 = new Employees(9, "123450000", "Кирилл", 11);

        EmployeeDirectory directory = new EmployeeDirectory();
        directory.addEmployee(employee001);
        directory.addEmployee(employee002);
        directory.addEmployee(employee003);
        directory.addEmployee(employee004);
        directory.addEmployee(employee005);
        directory.addEmployee(employee006);
        directory.addEmployee(employee007);
        directory.addEmployee(employee008);
        directory.addEmployee(employee009);
        directory.addEmployee(employee010);

        List<Employees> allEmployees = directory.getAllEmployees();
        for (Employees employee : allEmployees) {
            System.out.println("Табельный номер: " + employee.getEmployeeId());
            System.out.println("Номер телефона: " + employee.getPhoneNumber());
            System.out.println("Имя: " + employee.getName());
            System.out.println("Стаж: " + employee.getExperience() + " лет");
            System.out.println("------------------------");
        }

        directory.getEmployeesByExperience(24);
        directory.getEmployeesByName("Кирилл");
        directory.getEmployeesById(5);

        directory.setEmployeeList(allEmployees); // Добавляем сотрудника с консоли;

        for (Employees employee : allEmployees) {
            System.out.println("Табельный номер: " + employee.getEmployeeId());
            System.out.println("Номер телефона: " + employee.getPhoneNumber());
            System.out.println("Имя: " + employee.getName());
            System.out.println("Стаж: " + employee.getExperience() + " лет");
            System.out.println("------------------------");
        }
    }
}
