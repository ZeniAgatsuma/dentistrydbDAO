package com.zeni.presentationlayer;

import com.zeni.DAO.DatabaseConnection;
import com.zeni.DAO.PatientDAO;
import com.zeni.model.Patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PatientDAOInterface {
    public static void main(String[] args) {
        try {
            // Підключення до бази даних
            Connection connection = DatabaseConnection.getConnection();
            PatientDAO patientDAO = PatientDAO.getInstance(connection); // Отримання екземпляру через метод getInstance
            
            // Створення об'єкту Scanner для зчитування введених користувачем даних з консолі
            Scanner scanner = new Scanner(System.in);
            
            // Головне меню
            while (true) {
                System.out.println("1. Створити нового пацієнта");
                System.out.println("2. Оновити інформацію про пацієнта");
                System.out.println("3. Видалити пацієнта");
                System.out.println("4. Показати інформацію про пацієнта за ID");
                System.out.println("5. Показати всіх пацієнтів");
                System.out.println("6. Вийти");
                System.out.print("Виберіть опцію: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера вводу
                
                switch (option) {
                    case 1:
                        // Створення нового пацієнта
                        System.out.print("Введіть ім'я пацієнта: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Введіть прізвище пацієнта: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Введіть адресу пацієнта: ");
                        String address = scanner.nextLine();
                        System.out.print("Введіть телефон пацієнта: ");
                        String phone = scanner.nextLine();
                        System.out.print("Введіть електронну пошту пацієнта: ");
                        String email = scanner.nextLine();
                        
                        Patient newPatient = new Patient.Builder()
                                .firstName(firstName)
                                .lastName(lastName)
                                .address(address)
                                .phone(phone)
                                .email(email)
                                .build();
                        patientDAO.createPatient(newPatient);
                        System.out.println("Новий пацієнт створений!");
                        break;
                    case 2:
                        // Оновлення інформації про пацієнта
                        System.out.print("Введіть ID пацієнта, якого потрібно оновити: ");
                        int updatePatientId = scanner.nextInt();
                        scanner.nextLine(); // Очистка буфера вводу

                        // Запитати користувача про нові дані пацієнта
                        System.out.print("Введіть нове ім'я пацієнта: ");
                        String newFirstName = scanner.nextLine();
                        
                        System.out.print("Введіть нове прізвище пацієнта: ");
                        String newLastName = scanner.nextLine();
                        
                        System.out.print("Введіть нову адресу пацієнта: ");
                        String newAddress = scanner.nextLine();
                        
                        System.out.print("Введіть новий телефон пацієнта: ");
                        String newPhone = scanner.nextLine();
                        
                        System.out.print("Введіть нову електронну пошту пацієнта: ");
                        String newEmail = scanner.nextLine();

                        // Створити об'єкт Patient з новими даними
                        Patient updatedPatient = new Patient.Builder()
                                .patientID(updatePatientId)  // Встановити введений користувачем PatientID
                                .firstName(newFirstName)
                                .lastName(newLastName)
                                .address(newAddress)
                                .phone(newPhone)
                                .email(newEmail)
                                .build();

                        patientDAO.updatePatient(updatedPatient);
                        System.out.println("Інформація про пацієнта оновлена!");
                        
                        break;
                    
                    case 3:
                        // Видалення пацієнта
                        System.out.print("Введіть ID пацієнта, якого потрібно видалити: ");
                        int deletePatientId = scanner.nextInt();
                        patientDAO.deletePatient(deletePatientId);
                        System.out.println("Пацієнт видалений!");
                        break;
                    case 4:
                        // Показати інформацію про пацієнта за ID
                        System.out.print("Введіть ID пацієнта: ");
                        int patientId = scanner.nextInt();
                        Patient patientById = patientDAO.getPatientById(patientId);
                        if (patientById != null) {
                            System.out.println("Інформація про пацієнта:");
                            System.out.println(patientById);
                        } else {
                            System.out.println("Пацієнта з таким ID не знайдено.");
                        }
                        break;
                    case 5:
                        // Показати всіх пацієнтів
                        List<Patient> allPatients = patientDAO.getAllPatients();
                        if (!allPatients.isEmpty()) {
                            System.out.println("Всі пацієнти:");
                            for (Patient patient : allPatients) {
                                System.out.println(patient);
                            }
                        } else {
                            System.out.println("Немає жодного пацієнта.");
                        }
                        break;
                    case 6:
                        // Вихід з програми
                        System.out.println("Програма завершена.");
                        return;
                    default:
                        System.out.println("Невірний вибір опції. Спробуйте ще раз.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
