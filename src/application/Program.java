package application;

import model.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

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

        System.out.println("=== Seller findAll ===");
        list = sellerDao.findAll();
        for (Seller s : list) {
            System.out.println(s);
        }

//        System.out.println("=== Seller insert ===");
//        Seller newSeller = new Seller(9, "Greg", "greg@hotmail.com", new Date(), 4000, department);
//        sellerDao.insert(newSeller);
//        System.out.println("Insert new id: " + newSeller.getId());

        System.out.println("=== Seller update ===");
        seller = sellerDao.findById(9);
        seller.setName("Neymar jr");
        sellerDao.update(seller);
        System.out.println("Updated Seller name: " + seller.getName());

        System.out.println("=== Seller delete ===");
        System.out.print("Digite um ID: ");
        int idDelete = sc.nextInt();
        sellerDao.deleteById(idDelete);
        System.out.println("Deleted Seller ID: " + idDelete);

        sc.close();
    }
}