package application;

import model.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: findById =======");
        Department dep = departmentDao.findById(1);
        System.out.println(dep);

        System.out.println("\n=== TEST 2: findAll =======");
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }

//        System.out.println("=== Department Insert ===");
//        Department department = new Department(6, "vendas");
//        departmentDao.insert(department);
//        System.out.println("Department Inserted");

        System.out.println("=== Department Update ===");
        Department dep2 = departmentDao.findById(5);
        dep2.setName("Infra");
        departmentDao.update(dep2);
        System.out.println("Department Updated " + dep2.getName());

        System.out.println("\n=== Departement delete =======");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Delete completed");
    }
}
