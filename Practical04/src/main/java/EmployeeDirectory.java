import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EmployeeDirectory {
    private List<Employees> employeeList;

    // Конструктор класса EmployeeDirectory:
    public EmployeeDirectory() {
        employeeList = new ArrayList<>();
    }

    /**
     * Проверка уникальности табельного номера.
     * @param employeeId
     * @return
     */
    private boolean isEmployeeIdUnique(int employeeId) {
        for (Employees employee : employeeList) {
            if (employee.getEmployeeId() == employeeId) {
                return false;
            }
        }
        return true;
    }

    // Метод для добавления нового сотрудника в справочник:
    public void addEmployee(Employees employee) {
        if (isEmployeeIdUnique(employee.getEmployeeId())) {
            employeeList.add(employee);
            System.out.println("Новый сотрудник успешно добавлен в справочник!");
        } else {
            System.out.println("Сотрудник с таким табельным номером уже существует!");
        }
    }

    // Метод для получения списка всех сотрудников:
    public List<Employees> getAllEmployees() {
        return employeeList;
    }

    // Метод для поиска сотрудников по стажу:
    public void getEmployeesByExperience(int experience) {
        List<Employees> result = new ArrayList<>();

        for (Employees employee : employeeList) {
            if (employee.getExperience() == experience) {
                result.add(employee);
                System.out.println("Искомый сотрудник с указанным стажем: ");
                System.out.println("Табельный номер: " + employee.getEmployeeId());
                System.out.println("Номер телефона: " + employee.getPhoneNumber());
                System.out.println("Имя: " + employee.getName());
                System.out.println("Стаж: " + employee.getExperience() + " лет");
            } else {
                System.out.println("Сотрудника со стажем: " + experience + " нет в списке сотрудников.");
            }
        }
    }

    //  Метод, который выводит номер телефона сотрудника по имени:
    public void getEmployeesByName(String name) {
        List<Employees> result = new ArrayList<>();

        for (Employees employee : employeeList) {
            if (Objects.equals(employee.getName(), name)) {
                result.add(employee);
                System.out.println("Искомый сотрудник с именем: " + name);
                System.out.println("Его номер телефона: " + employee.getPhoneNumber());
            } else {
                System.out.println("Сотрудника с именем: " + name + " нет в списке сотрудников.");
            }
        }
    }

    // Метод, который ищет сотрудника по табельному номеру:

    public void getEmployeesById (int id) {
        List<Employees> result = new ArrayList<>();

        for (Employees employee : employeeList) {
            if (employee.getEmployeeId() == id) {
                result.add(employee);
                System.out.println("Сотрудник с табельным номером: " + id);
                System.out.println("Табельный номер: " + employee.getEmployeeId());
                System.out.println("Номер телефона: " + employee.getPhoneNumber());
                System.out.println("Имя: " + employee.getName());
                System.out.println("Стаж: " + employee.getExperience() + " лет");
            } else {
                System.out.println("Сотрудника с табельным номером: " + id + " нет в списке сотрудников.");
            }
        }
    }

    // Метод добавления сотрудника с консоли:
    public void setEmployeeList (List<Employees> employeeList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные нового сотрудника:");

        System.out.print("Табельный номер: ");
        int employeeId = scanner.nextInt();

        System.out.print("Номер телефона: ");
        String phoneNumber = scanner.next();

        System.out.print("Имя: ");
        String name = scanner.next();

        System.out.print("Стаж: ");
        int experience = scanner.nextInt();

        Employees newEmployee = new Employees(employeeId, phoneNumber, name, experience);
        if (isEmployeeIdUnique(newEmployee.getEmployeeId())) {
            employeeList.add(newEmployee);
            System.out.println("Новый сотрудник успешно добавлен в справочник!");
        } else {
            System.out.println("Сотрудник с таким табельным номером уже существует!");
        }
    }
}
