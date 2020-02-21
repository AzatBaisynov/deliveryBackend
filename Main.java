package backend;

import java.sql.Time;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();
        dbWorker.connect();
        System.out.println("Вас приветствует служба доставки еды\nВведите свое имя:");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        System.out.println("Добро пожаловать " + userName + ".\nСписок кафе:");
        dbWorker.showCafeList();
        System.out.println("Выберите кафе по ID: ");
        int choice1 = sc.nextInt();
        dbWorker.getFoodList(choice1);
        System.out.println("Какое блюдо вы хотите заказать?");
        int choice2 = sc.nextInt();
        dbWorker.addOrder(choice2);
        System.out.println("Ваш заказ был успешно совершен");

        dbWorker.returnAllOrders();

        System.out.println();
        System.out.println();
        System.out.println();

        dbWorker.getDishByPrice();
    }
}
