import db.DataBase;
import exception.StackOverflowException;
import generator.GeneratorId;
import model.Contact;
import model.Phone;
import service.ContactService;
import service.PhoneService;
import service.serviceImpl.ContactServiceImpl;
import service.serviceImpl.PhoneServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

// Stream API
// 28-03-2024
public class Main {
    public static void main(String[] args) {
        Scanner scannerNum = new Scanner(System.in);
        Scanner scannerLn = new Scanner(System.in);

        PhoneService phoneService = new PhoneServiceImpl();
        ContactService contactService = new ContactServiceImpl();

        while (true) {
            System.out.println("""
                    ======================================= Выберите действие =======================================
                    1) Добавить телефон                         7) Добавить контакт в телефон
                    2) Получить телефон по id                   8) Найти контакт по имени
                    3) Переименовать телефон по id              9) Найти контакт по номеру телефона
                    4) Все телефоны                             10) Отсортировать контакты телефона по имени
                    5) Получить все телефоны по бренду          11) Удалить контакт
                    6) Удалить телефон                          12) Выйти
                    """);

            int command = scannerNum.nextInt();
            try {
                switch (command) {
                    case 1 -> {
                        System.out.println("Введите название телефона: ");
                        String name = scannerLn.nextLine();

                        System.out.println("Введите бренд телефона: ");
                        String brand = scannerLn.nextLine();

                        Phone phone = new Phone(GeneratorId.genPhoneId(), name, brand, new ArrayList<>());
                        System.out.println(phoneService.addPhone(phone));
                    }
                    case 2 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона: ");
                            System.out.println(phoneService.getPhoneById(scannerNum.nextInt()));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 3 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона которого хотите переименовать: ");
                            int phoneId = scannerNum.nextInt();

                            System.out.println("Введите новое название телефона: ");
                            String newName = scannerLn.nextLine();

                            System.out.println(phoneService.updatePhoneNameById(phoneId, newName));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 4 -> {
                        System.out.println(phoneService.getAllPhones());
                    }
                    case 5 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите бренд телефона: ");
                            System.out.println(phoneService.getAllPhonesByBrand(scannerLn.nextLine()));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 6 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона которого хотите удалить: ");
                            phoneService.deletePhoneById(scannerNum.nextInt());
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 7 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона куда вы хотите добавить контакт: ");
                            int phoneId = scannerNum.nextInt();

                            System.out.println("Введите имя контакта: ");
                            String name = scannerLn.nextLine();

                            System.out.println("Введите номер телефона: ");
                            String phoneNumber = scannerLn.nextLine();

                            Contact contact = new Contact(name, phoneNumber);
                            System.out.println(contactService.addContactToPhone(phoneId, contact));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 8 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона с которого вы хотите найти контакт: ");
                            int phoneId = scannerNum.nextInt();

                            System.out.println("Введите имя контакта: ");
                            String contactName = scannerLn.nextLine();

                            System.out.println(contactService.findContactByName(phoneId, contactName));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 9 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона с которого вы хотите найти контакт: ");
                            int phoneId = scannerNum.nextInt();

                            System.out.println("Введите номер телефона: ");
                            String phoneNumber = scannerLn.nextLine();

                            System.out.println(contactService.findContactByPhoneNumber(phoneId, phoneNumber));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 10 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона контакты которого хотите отсортировать: ");
                            System.out.println(contactService.sortContactsByName(scannerNum.nextInt()));
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 11 -> {
                        if (!DataBase.phones.isEmpty()) {
                            System.out.println("Введите id телефона контакт с которого хотите удалить: ");
                            int phoneId = scannerNum.nextInt();

                            System.out.println("Введите имя контакта: ");
                            String contactName = scannerLn.nextLine();

                            contactService.deleteContactByNameFromPhone(phoneId, contactName);
                        } else {
                            throw new StackOverflowException("У вас пока нету ни одного телефона!");
                        }
                    }
                    case 12 -> {
                        System.out.println("Выход успешно выполнен!");
                    }
                }
                if (command == 12) {
                    break;
                }
            } catch (StackOverflowException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}