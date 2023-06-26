// Задание
// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами, 
// их необходимо считать, как одного человека с разными телефонами. 
// Вывод должен быть отсортирован по убыванию числа телефонов.

// Пример меню:
// 1) Добавить контакт
// 2) Вывести всех
// 3) Выход

// Иванов 123432
// Иванов 546457
// Иванов 788354
// Map<String, ArrayList> ---- {Иванов:[23145, 456745, 56787], Петров:[4325, 45674]}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * homeworkSem5
 */
public class homeworkSem5 {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> spravochnik = new HashMap<>();
        Scanner sc = new Scanner(System.in, "cp866");
        Boolean getOut = false;
        String st;
        while (!getOut) {
            System.out.println("Введите номер действия (1 - добавить абонента, 2 - показать всех, exit - выйти из программы):");
            st = sc.nextLine();
            switch (st) {
                case "1": {
                    System.out.println("Введите фамилию: ");
                    String name = sc.nextLine();
                    if (spravochnik.containsKey(name)) {
                        for (var item : spravochnik.entrySet()) {
                            item.getValue().addAll(AddList()); 
                            break;
                        }
                        break;
                    }
                    else {
                        spravochnik.put(name, AddList());
                        sortPrint(spravochnik);
                        break;
                    }
                }
                case "2":{
                    sortPrint(spravochnik);
                }
                case "exit": {
                    getOut = true;
                    break;
                }
            }
        }
    }

    public static ArrayList<Integer> AddList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер: ");
        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            y.add(sc.nextInt());
        }
        return y;
    }

    static void sortPrint(Map<String, ArrayList<Integer>> spr) {
        Set<String> keySet = spr.keySet();
        int maxCount = 0;
        int minCount = 1_000_000;
        // for (var item : spr.entrySet()) {
        //     if (maxCount < item.getValue().size())
        //         maxCount = item.getValue().size();
        //     if (minCount > item.getValue().size())
        //         minCount = item.getValue().size();
        // }
        Stack<String> st = new Stack<>();
        int num = minCount;
        while (num <= maxCount) {
            for (var item : spr.entrySet()) {
                if (item.getValue().size() == num) {
                    st.push(item.getKey());
                }
            }
            num += 1;
        }
        String name;
        for (int i = 0; i < keySet.size(); i++) {
            name = st.pop();
            for (var item : spr.entrySet()) {
                if (name == item.getKey()) {
                    System.out.printf("%8s: ", item.getKey());
                    System.out.println(item.getValue());
                }
            }
        }
        System.out.println();
    }
}