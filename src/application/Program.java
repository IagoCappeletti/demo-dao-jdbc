package application;

import model.DaoFactory;
import model.dao.SellerDao;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

    }
}