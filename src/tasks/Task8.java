package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {

    /*Удаление первого элемента из List (если это ArrayList) по сложности O(n) + проверка, что List не пуст.
            При использовании skip(1) мы ничего не удаляем, просто пропускаем первый элемент - O(1)*/
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {    // List to Set проще без стрима
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream()
            .collect(Collectors.toMap(
                    Person::getId,
                    this::convertPersonToString,
                    (a, b) -> a
            ));
  }

  // есть ли совпадающие в двух коллекциях персоны?

 /* В методе Collections.disjoint одна из коллекций используется в качестве contains, лучше всего для нее
  использовать Set, т. к. для Set метод contains() имеет O(1), вторая коллекция используется как iterator,
  для нее лучше использовать (при прочих равных) более короткую коллекцию, т. к. итерирование по любой коллекции - O(n)
  В нашем случае лучше использовать Set для коллекции с большим size(), но я буду считать, что они примерно равны
  по количеству элементов, чтобы не делать дополнительных проверок.  */
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    Set<Person> persons1Set = new HashSet<>(persons1);
    return !Collections.disjoint(persons1Set, persons2);   // проверка на наличие совпадений в 2 коллекциях
  }

  //...
  public long countEven(Stream<Integer> numbers) {

   /* Глобальные переменные не стоит использовать без веской причины,
    это может приводить к ошибкам. В данном случае в ней не было необходимости.*/
    return numbers
            .filter(num -> num % 2 == 0)
            .count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = false;
    boolean reviewerDrunk = true;
    return codeSmellsGood || reviewerDrunk;
  }
}
