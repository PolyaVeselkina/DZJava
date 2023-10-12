// VS Code не читает почему-то слово "выход" на русском, поэтому написала "exit", 
// и имена или фамилии контактов тоже приходилось вводить на английском, потому что при выводе справочника, 
// если писала по-русски, вместо фамилий выводил "?????"

import java.util.*;

public class PhoneBook {

    public static void main(String[] args) {
        HashMap<String, HashSet<String>> phoneBook = new HashMap<>();//Создаем HashMap для хранения контактов
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Введите имя контакта (или 'exit' для завершения):");
            String name = scanner.nextLine();
            
           
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            
            System.out.println("Введите номер телефона:");
            String phoneNumber = scanner.nextLine();
            

            //Проверяем, есть ли контакт с таким именем
            if (phoneBook.containsKey(name)) {
                HashSet<String> phoneNumbers = phoneBook.get(name);//Получаем список телефонных номеров
                
                phoneNumbers.add(phoneNumber);//Добавляем новый телефонный номер в список
                phoneBook.put(name, phoneNumbers);//Обновляем запись в HashMap
            
            } else {
                
                HashSet<String> phoneNumbers = new HashSet<>();//Создаем новый список телефонных номеров
                
                phoneNumbers.add(phoneNumber);//Добавляем ПЕРВЫЙ телефонный номер
                phoneBook.put(name, phoneNumbers);//Добавляем запись в HashMap
            }               
          
        }
        
        scanner.close();//Закрываем сканер

        //Сортировка телефонной книги по убыванию числа телефонных номеров
        List<Map.Entry<String, HashSet<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().size() - entry1.getValue().size());
        
        //Вывод отсортированной телефонной книги
        System.out.println("Телефонная книга:");
       
        for (Map.Entry<String, HashSet<String>> entry : sortedEntries) {
            String name = entry.getKey();
            HashSet<String> phoneNumbers = entry.getValue();
            
            System.out.println(name + ": " + phoneNumbers.toString().replaceAll("^\\[|\\]$", ""));//Преобразование для вывода без квадратных скобок
        }
    }
}
