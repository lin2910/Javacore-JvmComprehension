
public class JvmComprehension {
    // Загрузка нового встреченного класса JvmComprehension с помощью ClassLoader.
    // JVM проверяет валидность кода.
    // Происходит связывание (подготовка класса к выполнению) по этапам: Verify -> Prepare -> Resolve.
    // ClassLoader подтверждает загрузку класса. Происходит инициализация класса.
    // JvmComprehension загружается в Metaspace.
    // JVM ищет точку входа main и начинается выполнение программы.
    // В стек помещается фрейм main.


    public static void main(String[] args) {
        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }
}

/*
1. В стек в фрейм main загружается значение переменной i = 1
2. В куче выделяется память под объект о
    Создается ссылка на объект о
    Ссылка добавляется в стек в фрейм main
3. В куче выделяется память под Integer ii
    В выделенную память заносится значение 2
    Создается ссылка на ii
    Ссылка добавляется в стек в фрейм main
4. В стеке создается фрейм printAll
    В фрейм добавляются аргументы метода
        o - ссылка на объект o в куче
        i - 1
        ii - ссылка на Integer в куче
   После выполнения метода фрейм printAll отчищается в стеке // стек возвращается в исходное состояние до вызова метода
5. В куче выделяется память под Integer uselessVar
    В выделенную память заносится значение 700
    Создается ссылка на uselessVar
    Ссылка добавляется в стек в фрейм printAll
6. В стеке создается фрейм println
    В фрейм добавляются аргументы
    Происходит выполнение метода
    После выполнения метода фрейм println отчищается в стеке
После выполнения метода printAll отчищается в стеке
Ссылка на uselessVar стирается, переменная становится недостижимой
7. В стеке создается фрейм println
    В фрейм добавляются аргументы
    Происходит выполнение метода
    После выполнения метода фрейм println отчищается в стеке

После выполнения метода main отчищается в стеке
Ссылки на o, ii стирается, переменные становятся недостижимыми

*/