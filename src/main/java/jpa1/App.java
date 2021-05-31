package jpa1;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add dishes");
                    System.out.println("2: view dishes");
                    System.out.println("3: view dishes by price");
                    System.out.println("4: view dishes by discount");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addDishes(sc);
                            break;
                        case "2":
                            viewDishes();
                            break;
                        case "3":
                            viewDishesbyPrice(sc);
                            break;
                        case "4":
                            viewDishesbyDiscount();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addDishes(Scanner sc) {
        System.out.print("Enter dish name: ");
        String dishes = sc.nextLine();

        System.out.print("Enter dish cost: ");
        String sCost = sc.nextLine();
        int cost = Integer.parseInt(sCost);

        System.out.println("Enter dish weight: ");
        String sWeight = sc.nextLine();
        int weight = Integer.parseInt(sWeight);

        System.out.println("Enter dish discount - yes or not: ");
        Boolean discount;
        discount = false;
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            discount = true;
        }

        em.getTransaction().begin();

        try {
            RestaurantMenu c = new RestaurantMenu(dishes, cost, weight, discount);
            em.persist(c);
            em.getTransaction().commit();

            System.out.println(c.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewDishes() {
        Query query = em.createQuery(
                "SELECT c FROM RestaurantMenu c", RestaurantMenu.class);
        List<RestaurantMenu> list = (List<RestaurantMenu>) query.getResultList();

        for (RestaurantMenu c : list)
            System.out.println(c);
    }

    private static void viewDishesbyPrice(Scanner sc) {
        System.out.println("Enter low price:");
        String sLowprice = sc.nextLine();
        int lowprice = Integer.parseInt(sLowprice);
        System.out.println("Enter max price: ");
        String sMaxprice = sc.nextLine();
        int maxprice = Integer.parseInt(sMaxprice);
            Query query = em.createQuery(
                    "SELECT c FROM RestaurantMenu c WHERE (c.cost>=:lowprice)AND (c.cost<=:maxprice)",RestaurantMenu.class );
            query.setParameter("lowprice", lowprice);
            query.setParameter("maxprice", maxprice);
            List<RestaurantMenu> listdishes = (List<RestaurantMenu>) query.getResultList();

            for (RestaurantMenu c : listdishes)
                System.out.println(c);
    }

    private static void viewDishesbyDiscount () {
        Query query = em.createQuery("SELECT c FROM RestaurantMenu c WHERE c.discount=true ", RestaurantMenu.class);
        List<RestaurantMenu> listdishes = (List<RestaurantMenu>) query.getResultList();
        for (RestaurantMenu c : listdishes)
            System.out.println(c);
    }
}
