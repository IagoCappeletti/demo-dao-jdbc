package application;

import model.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Seller findByID ===");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);

        System.out.println("=== Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findAllDepartments(department);
        for (Seller s : list) {
            System.out.println(s);
        }
    }
}