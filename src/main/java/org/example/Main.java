package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final List<Trainer> trainers = new ArrayList<>();
    private static final List<Client> clients = new ArrayList<>();
    private static final List<TrainingSession> sessions = new ArrayList<>();

    public static void main(String[] args) {
        seedData();
        runConsole();
    }

    public static List<TrainingSession> findSessionsByTrainer(List<TrainingSession> sessions, String trainerName) {
        if (trainerName == null || trainerName.trim().isEmpty()) {
            return new ArrayList<>();
        }
        final String lowerCaseName = trainerName.trim().toLowerCase();
        return sessions.stream()
                .filter(s -> s.getTrainer().getName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());
    }

    public static List<Client> filterClientsByMembership(List<Client> clients, Client.MembershipType type) {
        return clients.stream()
                .filter(c -> c.getMembershipType() == type)
                .collect(Collectors.toList());
    }

    private static void seedData() {
        Trainer trainer1 = new Trainer("Алексей Петров", "Силовые тренировки");
        Trainer trainer2 = new Trainer("Анна Сидорова", "Йога");
        trainers.addAll(Arrays.asList(trainer1, trainer2));

        clients.add(new Client("Алексей Смирнов", Client.MembershipType.FULL));
        clients.add(new Client("Ольга Кузнецова", Client.MembershipType.DAY));
        clients.add(new Client("Дмитрий Васильев", Client.MembershipType.MORNING));
        clients.add(new Client("Елена Михайлова", Client.MembershipType.FULL));

        sessions.add(new TrainingSession("Тяжелая атлетика", trainer1, LocalDateTime.now().plusHours(2)));
        sessions.add(new TrainingSession("Хатха-йога", trainer2, LocalDateTime.now().plusHours(3)));
        sessions.add(new TrainingSession("Функциональный тренинг", trainer1, LocalDateTime.now().plusDays(1)));
    }
    
    private static void runConsole() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            String cmd = sc.nextLine().trim();
            if ("exit".equalsIgnoreCase(cmd)) break;
            switch (cmd) {
                case "1": findSessionsByTrainerUI(sc); break;
                case "2": filterClientsByMembershipUI(sc); break;
                case "3": System.out.println(sessions); break;
                case "4": System.out.println(clients); break;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Меню Фитнес-клуба ===");
        System.out.println("1 - Поиск занятий по тренеру");
        System.out.println("2 - Фильтрация клиентов по абонементу");
        System.out.println("3 - Показать все занятия");
        System.out.println("4 - Показать всех клиентов");
        System.out.println("exit - Выход");
        System.out.print("Команда: ");
    }
    
    private static void findSessionsByTrainerUI(Scanner sc) {
        System.out.print("Введите имя тренера: ");
        String name = sc.nextLine();
        List<TrainingSession> result = findSessionsByTrainer(sessions, name);
        if (result.isEmpty()) {
            System.out.println("Занятия не найдены.");
        } else {
            result.forEach(System.out::println);
        }
    }
    
    private static void filterClientsByMembershipUI(Scanner sc) {
        System.out.print("Введите тип абонемента (FULL, DAY, MORNING): ");
        String typeStr = sc.nextLine().trim().toUpperCase();
        try {
            Client.MembershipType type = Client.MembershipType.valueOf(typeStr);
            List<Client> result = filterClientsByMembership(clients, type);
            result.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный тип абонемента.");
        }
    }
}