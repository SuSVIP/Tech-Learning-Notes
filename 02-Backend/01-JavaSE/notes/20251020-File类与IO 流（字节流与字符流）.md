# 知识点列表

| 编号 | 名称 | 描述 | 级别 |
| ---- | ---- | ---- | ---- |
| 1    |      |      | **   |
| 2    |      |      | **   |
| 3    |      |      | **   |
| 4    |      |      | **   |
| 5    |      |      | **   |
| 6    |      |      | **   |

# 多态的核心概念

多态是面向对象编程（OOP）的三大特性之一（封装、继承、多态），核心思想是 **“同一行为，不同实现”**。

- 定义：同一方法调用，由于对象不同可能产生不同的行为效果。
- 本质：**父类引用指向子类对象**，通过这个引用调用方法时，实际执行的是子类的重写实现。
- 目的：
  - 提高代码的**可扩展性**（新增子类不影响原有代码）。
  - 降低耦合度（通过父类接口操作对象，无需关心具体子类类型）。

# 多态的实现条件

多态必须满足以下三个条件，缺一不可：

1. **存在继承关系**：子类继承父类（或实现接口，接口多态后续讲解）。
2. **子类重写父类方法**：子类对父类的方法进行重写，定义自己的实现逻辑。
3. **父类引用指向子类对象**：声明为父类类型的变量，实际指向子类的实例。



# 多态的基本示例

以 “动物发声” 为例，展示多态的实现：

## 1. 定义父类与子类（满足继承和重写）

```java
// 父类：动物
class Animal {
    // 父类方法：发声（被子类重写）
    public void makeSound() {
        System.out.println("动物发出声音");
    }
}

// 子类：狗（继承Animal，重写方法）
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("狗汪汪叫");
    }
}

// 子类：猫（继承Animal，重写方法）
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("猫喵喵叫");
    }
}
```

## 2. 父类引用指向子类对象（多态的核心表现）



```java
public class PolymorphismDemo {
    public static void main(String[] args) {
        // 父类引用指向子类对象（多态的关键语法）
        Animal animal1 = new Dog();  // animal1是Animal类型，但实际是Dog对象
        Animal animal2 = new Cat();  // animal2是Animal类型，但实际是Cat对象

        // 同一方法调用，因对象不同产生不同结果
        animal1.makeSound();  // 输出：狗汪汪叫（执行Dog的重写方法）
        animal2.makeSound();  // 输出：猫喵喵叫（执行Cat的重写方法）
    }
}
```

**原理**：

- 编译时，`animal1.makeSound()` 检查父类 `Animal` 是否有 `makeSound()` 方法（若没有则编译报错）。
- 运行时，JVM 识别到 `animal1` 实际指向 `Dog` 对象，因此执行 `Dog` 类的 `makeSound()` 方法（动态绑定）。



# 多态的两种形式

多态分为**编译时多态**（静态多态）和**运行时多态**（动态多态），核心区别在于 “方法调用的绑定时机”。

### 1. 编译时多态（方法重载 Overload）

- 定义：在同一类中，方法名相同但参数列表（类型、顺序、个数）不同的方法。

- 绑定时机：编译时确定调用哪个方法（根据参数匹配）。

- 示例：

  ```java
  class Calculator {
      // 重载：参数个数不同
      public int add(int a, int b) {
          return a + b;
      }
  
      // 重载：参数类型不同
      public double add(double a, double b) {
          return a + b;
      }
  
      // 重载：参数顺序不同
      public int add(int a, String b) {
          return a + Integer.parseInt(b);
      }
  }
  
  public class OverloadDemo {
      public static void main(String[] args) {
          Calculator calc = new Calculator();
          System.out.println(calc.add(1, 2));          // 调用int+int
          System.out.println(calc.add(1.5, 2.5));      // 调用double+double
          System.out.println(calc.add(3, "4"));        // 调用int+String
      }
  }
  ```

### 2. 运行时多态（方法重写 Override）

- 定义：子类重写父类的方法（方法签名相同），通过父类引用调用时，实际执行子类的实现。
- 绑定时机：运行时根据对象实际类型确定调用哪个方法（动态绑定）。
- 示例：前文 “动物发声” 案例即属于运行时多态，是多态的核心应用场景。



# 多态中的类型转换

多态中，父类引用指向子类对象时，若需调用子类**特有方法**（未在父类中定义的方法），需进行**类型转换**。

## 1. 向下转型（强制转型）

将父类类型转换为子类类型（需显式声明，且必须确保实际类型匹配，否则抛 `ClassCastException`）。

```java
public class CastDemo {
    public static void main(String[] args) {
        Animal animal = new Dog();  // 多态：父类引用指向Dog对象

        // 错误：父类引用不能直接调用子类特有方法
        // animal.wagTail();  // 编译报错：Animal类中无wagTail()方法

        // 向下转型：转为Dog类型
        if (animal instanceof Dog) {  // 先用instanceof判断，避免转型错误
            Dog dog = (Dog) animal;   // 强制转型
            dog.wagTail();            // 调用Dog的特有方法
        }

        // 错误转型示例（实际是Dog，转为Cat）
        if (animal instanceof Cat) {  // 条件为false，不执行
            Cat cat = (Cat) animal;   // 若执行则抛ClassCastException
        }
    }
}

// 给Dog添加特有方法
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("狗汪汪叫");
    }

    // 子类特有方法（父类中没有）
    public void wagTail() {
        System.out.println("狗摇尾巴");
    }
}
```



## 2. 向上转型（自动转型）

将子类类型转换为父类类型（无需显式声明，多态本身就是向上转型的过程）。

```java
Dog dog = new Dog();
Animal animal = dog;  // 向上转型（自动完成），等价于 Animal animal = new Dog();
```



## 3. `instanceof` 关键字

用于判断 “对象实际类型是否为某个类（或其子类）”，返回 `boolean`，是安全转型的前提。

语法：`对象 instanceof 类名`

```java
Animal animal = new Dog();
System.out.println(animal instanceof Animal);  // true（Dog是Animal的子类）
System.out.println(animal instanceof Dog);     // true（实际类型是Dog）
System.out.println(animal instanceof Cat);     // false（实际类型不是Cat）
```



# 多态的应用场景

多态在实际开发中应用广泛，典型场景包括：

## 1. 方法参数多态

方法参数声明为父类类型，调用时可传入任意子类对象，提高方法通用性。

```java
// 工具类：统一处理所有动物
class AnimalTool {
    // 参数为父类类型，可接收Dog、Cat等所有子类对象
    public static void letAnimalSound(Animal animal) {
        animal.makeSound();  // 多态调用：实际执行子类方法
    }
}

public class PolymorphismParam {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        // 同一方法，传入不同子类对象，表现不同行为
        AnimalTool.letAnimalSound(dog);  // 狗汪汪叫
        AnimalTool.letAnimalSound(cat);  // 猫喵喵叫
    }
}
```



## 2. 集合存储多态对象

集合声明为父类类型，可存储所有子类对象，遍历时有统一的处理方式。

```java
import java.util.ArrayList;
import java.util.List;

public class PolymorphismCollection {
    public static void main(String[] args) {
        // 集合存储Animal类型（实际是Dog、Cat等子类对象）
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());

        // 遍历集合，统一调用makeSound()，表现多态
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }
}
```

**输出**：

```txt
狗汪汪叫
猫喵喵叫
```



# 多态的优缺点

### 优点

1. **提高代码可扩展性**：新增子类（如 `Pig`）时，无需修改原有方法（如 `letAnimalSound`），直接传入新对象即可。
2. **降低耦合度**：通过父类接口操作对象，代码不依赖具体子类，符合 “开闭原则”（对扩展开放，对修改关闭）。
3. **简化代码逻辑**：统一处理不同子类对象，避免大量 `if-else` 判断（如无需判断 “是狗还是猫”，直接调用 `makeSound()`）。

### 缺点

1. **不能直接调用子类特有方法**：需先转型，增加代码复杂度。

2. **属性不具备多态性**：多态仅针对方法，父类引用访问的属性始终是父类的属性（不被子类覆盖）。

   ```java
   class Parent {
       String name = "Parent";
   }
   
   class Child extends Parent {
       String name = "Child";  // 子类同名属性（不是重写，是隐藏）
   }
   
   public class FieldPolymorphism {
       public static void main(String[] args) {
           Parent p = new Child();
           System.out.println(p.name);  // 输出：Parent（访问的是父类属性）
       }
   }
   ```




# 多态与接口

接口是多态的重要载体（接口多态），一个类可实现多个接口，弥补 Java 单继承的限制。

```java
// 接口：可飞翔
interface Flyable {
    void fly();
}

// 接口：可游泳
interface Swimmable {
    void swim();
}

// 鸭子：实现两个接口
class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("鸭子低空飞");
    }

    @Override
    public void swim() {
        System.out.println("鸭子划水游");
    }
}

// 鱼：只实现游泳接口
class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("鱼摆尾游");
    }
}

public class InterfacePolymorphism {
    public static void main(String[] args) {
        // 接口引用指向实现类对象（多态）
        Flyable flyable = new Duck();
        Swimmable swimmable1 = new Duck();
        Swimmable swimmable2 = new Fish();

        flyable.fly();       // 鸭子低空飞
        swimmable1.swim();   // 鸭子划水游
        swimmable2.swim();   // 鱼摆尾游
    }
}
```



# 多态的核心总结

1. **多态三要素**：继承、重写、父类引用指向子类对象。
2. **两种形式**：编译时多态（重载）、运行时多态（重写，核心）。
3. **关键操作**：
   - 向上转型：自动完成（`子类对象 → 父类引用`）。
   - 向下转型：需显式声明，依赖 `instanceof` 判断安全性。
4. **核心价值**：通过统一接口实现不同行为，提高代码扩展性和灵活性。

多态是 Java 面向对象编程的精髓，理解多态能帮助你设计出更灵活、可维护的系统。
