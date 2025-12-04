# 知识点列表

| 编号 | 名称              | 描述                                        | 级别 |
| ---- | ----------------- | ------------------------------------------- | ---- |
| 1    | 常用类核心概念    | JavaSE 预定义常用类的设计思想与核心作用     | **   |
| 2    | 关键常用类详解    | String、StringBuilder、包装类等核心类的特性 | **   |
| 3    | 基础 API 核心方法 | 常用类的高频方法（字符串操作、类型转换等）  | **   |
| 4    | API 使用核心原则  | 不可变 / 可变类选择、线程安全、性能优化要点 | **   |
| 5    | 常见使用陷阱      | 自动装箱拆箱、字符串拼接、日期格式化的坑    | **   |
| 6    | 实战应用场景      | 常用类在开发中的典型使用场景与最佳实践      | *    |



# 核心概念

JavaSE 的**常用类与基础 API**是 Java 语言提供的预定义工具类集合，封装了开发中最常用的功能（如字符串处理、类型转换、数学运算、日期处理等），是 Java 开发的基础基石。

- 定义：由 Java 核心类库（`java.lang`、`java.util`等包）提供的、无需手动实现即可直接使用的类，其提供的公开方法（API）构成了基础编程的核心工具。
- 核心设计思想：
  - 封装性：隐藏实现细节，仅暴露简单易用的接口（如`String`的`equals()`方法，无需关心底层字符比较逻辑）；
  - 实用性：覆盖开发中 90% 以上的通用场景，避免重复造轮子；
  - 规范性：统一方法命名和使用逻辑（如所有类的`toString()`、`equals()`方法遵循统一规范）。
- 核心作用：
  - 简化开发：直接调用 API 完成复杂功能（如字符串拼接、日期格式化）；
  - 提高效率：底层由 JVM 优化实现，性能优于手动编码；
  - 保证稳定性：经过严格测试，避免手动实现的逻辑漏洞。



# 关键常用类详解（核心重点）

JavaSE 常用类主要集中在`java.lang`（核心类，无需导入）和`java.util`（工具类）包，以下是开发中最常用的核心类，含特性、核心方法和实战示例：



## 1.String 类（字符串处理核心）

### 核心特性

- 不可变性：字符串创建后无法修改内容（底层是`final char[]`数组）；
- 常量池优化：相同字符串常量会被缓存到常量池，避免重复创建（节省内存）；
- 线程安全：不可变性天然保证线程安全。

### 核心方法与示例

```java
public class StringDemo {
    public static void main(String[] args) {
        String str1 = "Hello Java";
        String str2 = new String("Hello Java"); // 新创建对象，不优先使用

        // 1. 字符串比较（必须用equals()，不能用==）
        System.out.println(str1.equals(str2)); // 输出：true（内容相等）
        System.out.println(str1 == str2);      // 输出：false（地址不同）

        // 2. 字符串拼接（不可变类，拼接会产生新对象）
        String str3 = str1 + "!";
        System.out.println(str3); // 输出：Hello Java!

        // 3. 截取字符串（substring(起始索引, 结束索引)，左闭右开）
        String subStr = str1.substring(0, 5);
        System.out.println(subStr); // 输出：Hello

        // 4. 查找字符/字符串
        int index = str1.indexOf("Java"); // 查找子串位置
        System.out.println(index); // 输出：6（从0开始计数）

        // 5. 转换大小写
        System.out.println(str1.toUpperCase()); // 输出：HELLO JAVA
        System.out.println(str1.toLowerCase()); // 输出：hello java

        // 6. 去除首尾空格
        String str4 = "  Hello World  ";
        System.out.println(str4.trim()); // 输出：Hello World

        // 7. 字符串分割
        String[] parts = str1.split(" "); // 按空格分割
        System.out.println(parts[0]); // 输出：Hello
        System.out.println(parts[1]); // 输出：Java
    }
}
```

## 2. StringBuilder 类（高效字符串拼接）

### 核心特性

- 可变性：底层是可变的`char[]`数组，修改时不会创建新对象；
- 高效性：拼接、插入、删除等操作效率远高于`String`（无额外对象创建）；
- 非线程安全：多线程环境下需使用`StringBuffer`（线程安全但效率略低）。

### 核心方法与示例

```java
public class StringBuilderDemo {
    public static void main(String[] args) {
        // 1. 创建对象（推荐指定初始容量，避免扩容）
        StringBuilder sb = new StringBuilder(16);

        // 2. 拼接字符串（append()，支持所有数据类型）
        sb.append("Hello");
        sb.append(" ");
        sb.append("Java");
        sb.append(8); // 支持基本类型
        System.out.println(sb.toString()); // 输出：Hello Java8

        // 3. 插入字符串（insert(索引, 内容)）
        sb.insert(6, "SE ");
        System.out.println(sb.toString()); // 输出：Hello SE Java8

        // 4. 反转字符串
        sb.reverse();
        System.out.println(sb.toString()); // 输出：8avaJ ES olleH

        // 5. 删除字符串（delete(起始索引, 结束索引)）
        sb.delete(0, 4);
        System.out.println(sb.toString()); // 输出：ES olleH

        // 6. 替换字符串
        sb.replace(0, 2, "Java");
        System.out.println(sb.toString()); // 输出：Java olleH
    }
}
```

## 3.String vs StringBuilder 对比（开发必懂）

| 特性     | String           | StringBuilder      | 适用场景                                      |
| -------- | ---------------- | ------------------ | --------------------------------------------- |
| 可变性   | 不可变           | 可变               | -                                             |
| 拼接效率 | 低（创建新对象） | 高（直接修改数组） | 少量拼接用 String，大量拼接用 StringBuilder   |
| 线程安全 | 是（不可变）     | 否                 | 多线程用 StringBuffer，单线程用 StringBuilder |
| 内存占用 | 高（重复对象）   | 低（复用数组）     | 高频拼接场景优先选 StringBuilder              |

## 4.包装类（基本类型→引用类型）

### 核心特性

- 对应关系：8 种基本类型对应 8 种包装类（如`int→Integer`、`boolean→Boolean`）；
- 自动装箱 / 拆箱：JDK5 + 支持自动转换（`int`与`Integer`无缝切换）；
- 功能扩展：提供类型转换、常量定义等额外功能（如`Integer.MAX_VALUE`）。

### 核心对应关系

| 基本类型 | 包装类    | 父类   |
| -------- | --------- | ------ |
| int      | Integer   | Number |
| long     | Long      | Number |
| double   | Double    | Number |
| boolean  | Boolean   | Object |
| char     | Character | Object |

### 核心方法与示例

```java
public class WrapperClassDemo {
    public static void main(String[] args) {
        // 1. 自动装箱（基本类型→包装类）
        Integer num1 = 100; // 等价于：Integer num1 = Integer.valueOf(100);
        Double num2 = 3.14;

        // 2. 自动拆箱（包装类→基本类型）
        int a = num1; // 等价于：int a = num1.intValue();
        double b = num2;

        // 3. 字符串→基本类型（开发常用）
        String strNum = "123";
        int num3 = Integer.parseInt(strNum); // 字符串转int
        double num4 = Double.parseDouble("3.1415"); // 字符串转double
        System.out.println(num3 + num4); // 输出：126.1415

        // 4. 基本类型→字符串
        String str1 = Integer.toString(456);
        String str2 = String.valueOf(789); // 通用方法，支持所有类型

        // 5. 常量定义（包装类特有）
        System.out.println(Integer.MAX_VALUE); // 输出：2147483647（int最大值）
        System.out.println(Integer.MIN_VALUE); // 输出：-2147483648（int最小值）

        // 注意：包装类默认值是null（基本类型是0/false等）
        Integer num5 = null;
        // int c = num5; // 运行报错：NullPointerException（拆箱时null不能转基本类型）
    }
}
```

## 5.Math 类（数学运算工具）

### 核心特性

- 所有方法都是静态方法（无需创建对象，直接`Math.方法名()`调用）；
- 提供常用数学运算（绝对值、随机数、取整、三角函数等）；
- 底层优化：运算效率高于手动实现。

### 核心方法与示例

```java
public class MathDemo {
    public static void main(String[] args) {
        // 1. 绝对值
        System.out.println(Math.abs(-10)); // 输出：10
        System.out.println(Math.abs(-3.14)); // 输出：3.14

        // 2. 取整操作
        System.out.println(Math.ceil(3.2)); // 输出：4.0（向上取整）
        System.out.println(Math.floor(3.8)); // 输出：3.0（向下取整）
        System.out.println(Math.round(3.5)); // 输出：4（四舍五入）

        // 3. 随机数（0.0 ≤ r < 1.0）
        double random = Math.random();
        System.out.println(random); // 输出：0.xxx（随机小数）

        // 生成[1-100]的随机整数
        int randomInt = (int) (Math.random() * 100 + 1);
        System.out.println(randomInt); // 输出：1-100之间的随机数

        // 4. 最值计算
        System.out.println(Math.max(10, 20)); // 输出：20
        System.out.println(Math.min(3.14, 2.99)); // 输出：2.99

        // 5. 幂运算与开方
        System.out.println(Math.pow(2, 3)); // 输出：8.0（2^3）
        System.out.println(Math.sqrt(16)); // 输出：4.0（平方根）
    }
}
```

## 6.日期时间类（JDK8 前常用）

### 核心类

- `Date`：表示特定时间点（精确到毫秒），功能简单；
- `SimpleDateFormat`：日期格式化（Date→String 或 String→Date）；
- `Calendar`：日期时间计算（如加减年、月、日）。

### 核心方法与示例

```java
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeDemo {
    public static void main(String[] args) throws Exception {
        // 1. Date类（获取当前时间）
        Date now = new Date();
        System.out.println(now); // 输出：Wed Oct 18 15:30:00 CST 2023（默认格式）

        // 2. SimpleDateFormat（日期格式化，开发常用）
        // 格式符：yyyy(年)、MM(月)、dd(日)、HH(时)、mm(分)、ss(秒)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Date→String（格式化）
        String dateStr = sdf.format(now);
        System.out.println(dateStr); // 输出：2023-10-18 15:30:00

        // String→Date（解析，需处理ParseException异常）
        String str = "2023-01-01 12:00:00";
        Date date = sdf.parse(str);
        System.out.println(date); // 输出：Sun Jan 01 12:00:00 CST 2023

        // 3. Calendar（日期计算）
        Calendar cal = Calendar.getInstance(); // 获取当前日历
        cal.setTime(now); // 设置日历时间

        // 获取年月日时分秒
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1; // 月份从0开始，需+1
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + month + "月" + day + "日");

        // 日期计算：加1年、减2天
        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DAY_OF_MONTH, -2);
        Date newDate = cal.getTime();
        System.out.println(sdf.format(newDate)); // 输出：2024-10-16 15:30:00
    }
}
```

### 注意事项

- `SimpleDateFormat` 是非线程安全的，多线程环境下需避免共享对象（或使用 JDK8 的`DateTimeFormatter`替代）；
- `Calendar` 的月份从 0 开始（0=1 月，11=12 月），容易踩坑，需额外注意。

# 基础 API 使用核心原则

## 1. 不可变类与可变类选择原则

- 无需修改的字符串（如常量、配置项）用`String`；
- 需要频繁拼接、修改的字符串（如日志拼接、动态 SQL 构建）用`StringBuilder`；
- 多线程环境下的字符串修改用`StringBuffer`（线程安全）。

## 2. 包装类使用注意事项

- 避免`null`包装类直接拆箱（会抛`NullPointerException`）；

- 字符串转基本类型时，需处理`NumberFormatException`（如`Integer.parseInt("abc")`会报错）；

- 缓存机制：`Integer`对`[-128, 127]`范围内的数值有缓存，超出范围会创建新对象：

  ```java
  Integer a = 100;
  Integer b = 100;
  System.out.println(a == b); // 输出：true（缓存命中）
  
  Integer c = 200;
  Integer d = 200;
  System.out.println(c == d); // 输出：false（超出缓存，新对象）
  ```

## 3. 日期时间处理原则

- JDK8 及以上推荐使用`java.time`包（`LocalDate`、`LocalTime`、`DateTimeFormatter`），线程安全且 API 更友好；
- 若使用 JDK8 前的类，`SimpleDateFormat`需在方法内创建（避免共享），或使用`ThreadLocal`封装。



# 常见使用陷阱（新手必避）

## 1. String 的`==`与`equals()`混用

- 错误：用`==`比较字符串内容（`==`比较地址，`equals()`比较内容）；
- 正确：字符串内容比较用`equals()`，地址比较用`==`。

## 2. 自动装箱拆箱的空指针异常

- 错误：`Integer num = null; int a = num;`（null 拆箱报错）；
- 正确：使用包装类前先判空，或用`Optional`包装。

## 3. 字符串拼接的性能问题

- 错误：循环中用`String`拼接（如`for`循环中`str += i`），创建大量临时对象；
- 正确：循环中用`StringBuilder`的`append()`方法。

## 4. SimpleDateFormat 的线程安全问题

- 错误：定义为静态变量供多线程共享；
- 正确：方法内创建局部变量，或使用 JDK8 的`DateTimeFormatter`。



#  实践

## 1. 字符串处理场景（用户输入验证）

```java
// 验证手机号格式（11位数字，以13/14/15/17/18开头）
public static boolean validatePhone(String phone) {
    if (phone == null || phone.trim().length() != 11) {
        return false;
    }
    // 正则表达式配合String的matches()方法
    return phone.matches("^1[34578]\\d{9}$");
}

public static void main(String[] args) {
    System.out.println(validatePhone("13800138000")); // 输出：true
    System.out.println(validatePhone("12345678901")); // 输出：false
}
```



## 2. 日期格式化场景（日志输出）

```java
// 生成带时间戳的日志（使用SimpleDateFormat）
public static String generateLog(String content) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = sdf.format(new Date());
    return "[" + time + "] " + content;
}

public static void main(String[] args) {
    System.out.println(generateLog("用户登录成功"));
    // 输出：[2023-10-18 16:00:00] 用户登录成功
}
```



## 3. 数据类型转换场景（HTTP 参数解析）

```java
// 解析HTTP请求参数（String→int/double）
public static void parseParams(String ageStr, String scoreStr) {
    try {
        int age = Integer.parseInt(ageStr);
        double score = Double.parseDouble(scoreStr);
        System.out.println("年龄：" + age + "，分数：" + score);
    } catch (NumberFormatException e) {
        System.out.println("参数格式错误");
    }
}

public static void main(String[] args) {
    parseParams("20", "95.5"); // 输出：年龄：20，分数：95.5
    parseParams("abc", "95.5"); // 输出：参数格式错误
}
```



# 常用类与基础 API 的优缺点

## 优点

1. 开箱即用：无需手动实现复杂功能，直接调用 API，开发效率高；
2. 性能优化：底层由 JVM 优化（如 String 常量池、Math 的硬件加速），性能优于手动编码；
3. 稳定性强：经过 Java 官方严格测试，无逻辑漏洞，可靠性高；
4. 兼容性好：API 跨版本兼容，升级 JDK 时无需大幅修改代码。

## 缺点

1. 功能固定：API 是标准化的，无法满足特殊场景（需自定义扩展）；
2. 部分类非线程安全：如`StringBuilder`、`SimpleDateFormat`，多线程使用需额外处理；
3. 包装类开销：相比基本类型，包装类有额外的对象开销（频繁使用可能影响性能）；
4. 日期类设计缺陷：JDK8 前的日期类（如`Date`、`Calendar`）API 不直观，容易踩坑。



# 核心总结

1. **核心常用类**：String（不可变字符串）、StringBuilder（高效拼接）、包装类（类型转换）、Math（数学运算）、日期类（时间处理）是开发必备；
2. **关键原则**：不可变 vs 可变类按需选择，包装类避免 null 拆箱，日期格式化注意线程安全；
3. **高频 API**：String 的`equals()`/`substring()`/`split()`、StringBuilder 的`append()`、包装类的`parseXxx()`、Math 的`random()`/`abs()`；
4. **避坑重点**：String 的`==`与`equals()`区分、自动装箱拆箱的空指针、字符串拼接的性能问题；
5. **最佳实践**：JDK8 + 优先使用`java.time`日期类、多线程用`StringBuffer`/`DateTimeFormatter`、循环拼接用`StringBuilder`。
