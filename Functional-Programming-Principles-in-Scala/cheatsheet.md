##Scala Cheatsheet
###Chapter 1: Define Variables
	val a: Int = 2
	var b = 1  //不推荐，可变量
###Define Functions
	def max(x:Int, y:Int): Int = {
		if (x > y)
		   x
		else 
		   y
    }

	1.一般形式
	def func_name(param1:type, param2:type....): return type = {
		expressions/functions
        //返回最后一个表达式的值
	}

	2.几条规则
	(1)在某些情况下，函数返回值可以省略。(什么情况下)
    (2)function body只有一条语句的时候，可以省略大括号
    (3)函数无参数的时候，一对空括号即可
###Loop
	1.while //不推荐
      var i = 0
      while(i < args.length){
	  	i += 1
	  }

    2.for loop || foreach
	  args.foreach(function literal)
      for(arg <- args){
          do something
      }

###Chapter 2: Array (Mutable Seq with same type)
	val greet: Array[String] = new Array[String](3);
    greet(0) = "hello" // greet.update(0, "hello")
	printIn(greet(0)) // greet.apply(0)
    数组也是对象，赋值和打印其实都是调用对象的方法

####List (Immutable Seq with same type)
	val a: List[Int] = List(1,2,3)
	val b = List(3,4)
	val c = a ::: b (concat) (1,2,3,3,4)
	val d = 1 :: a  (cons) (1,1,2,3)
	val e = 1 :: 2 :: 3 :: Nil  //Nil is a empty list
	::相当于调用list的方法，只不过操作数在左边

####Turple (Immutable Seq with diff types)

	val a = Turple(1, "hello") //type inference is Tuple2[Int, String]
	a._1   //1
    a._2   //"hello"

####Set and Map
	Set和Map分为immutable 和 mutable两种，分别在scala.collection.mutable 和 scala.collection.immutable中
    val a = Set(1,2) // 默认immutable
	val b = HashSet(1,2)  //需显示import scala.collection.immutable.HashSet
    
	import scala.collection.mutable
	val treasureMap = mutable.Map[Int, String]()
	treasureMap += (1 -> "Go to island.")

###Chapter 3: Class And Objects
	1.class keyword
      class almost the same as Java, but recmond no mutable states
	2.Singleton Object (object Keyword)
	  (1)每一个class都有与之对应的companion object， 此种情况下object对象的作用类似于将java里一个类中的所有静态方法抽出来组成
      一个新的对象，此对象即为object。
	  (2)单独的object类似于静态类，比如java.Math的作用，或者加上一个main方法，即可成为Application的入口(也可以直接extends
	  App 这个trait，这样就不用写main方法了)

###Chapter 4: Basic Types and Operations
	Byte, Short, Int, Long, Char(16bit), Float, Double, Boolean (Package scala)
	String (Package java.lang)
	(1)String literals: """hello """ //可避免转义字符的转化
	(2)Symbol literals: 'ident, scala.Symbol类去包装，感觉不知所云
    (3)String Interpolation:(s,raw,f)
	   example: val name = "reader"; s"Hello, $name!"  => "Hello, reader!"
       example: raw"No\\\\escape!" => "No\\\\escape!"
       example: val pi="Pi"; f"$pi is approximately ${math.Pi}%.2f" => "Pi is approximately 3.14"

	(4)operator是member methods, methoda也可以看做是operator, 感觉像是语法糖
       example: 1 +　2 => 1.+(2)
	   example: s indexOf 'o' => s.indexOf('o')
	   
	   Note: infix, prefix and postfix operator (中缀，前缀，后缀)
       example: -2.0 => (2.0).unary_-

	   Note: scala有算术右移和逻辑右移之分

    (5)Object Equality
       Scala使用 == 和 != operator来判等。实际上是调用左操作数的equals方法来判等.
       和java的不同:
	   Java's == compares value equality, as in Scala. On reference types, however
       Java's == compares reference equality, which means the two variables point to the same object on the
	   JVM's heap

	(6)operator的优先级
       因为operator也是方法，因此也定义了方法的优先级？

###Chapter 5: Functional Objects
	(1)class constructor
       scala有个primary constructor，除了fields 和 methods，其他的code都算primary constructor的code
       scala可以定义auxiliary constructors, 但是必须显示调用primary constructor
	(2)类构造参数检查 
	   class Rational(param1:type, param2: type){
           require(d != 0)
	   }
       throwing an IllegalArgumentException
    (3)scala中合法的identifier (标识符)
       <1> alphanumeric identifier, 以字母开头
	   <2> operator identifier，以operator字符开头
	   <3> mixed identifier, 包含alphanumeric identifier， 再加上<2>或者'_'
	   <4> literal identifier, 用``包裹的量
    (4)implict conversions
       implicit def intToRational(x: Int) = new Rational(x)
       //隐式转化 int -> Rational

###Chapter 6: Built-in Control Structures
	(1) if expressions
        返回值为if表达式的结果
        if(predicate) A else B
	(2)While Loop (不推荐)
	(3)For expressions
	   感觉for 表达式的结果是一个生成器，每次返回迭代的元素？
       <1> Iteration through collections
           example: for (x <- coll){do something}
       <2> Range Type
           1 to 5    -> [1,5]
		   1 until 5 -> [1,5)
       <3> Filtering
           example: for (file <- filesHere if file.getName.endsWith(".scala"))
	   	   example: for (file <- filesHere
						 if file.isFile
						 if file.getName.endsWith(".scala")
					) 
	   <4> Nested Iteration
           for (
				file <- filesHere
				if file.getName.endsWith(".scala");
				line <- fileLines(file)
				if line.trim.matches(pattern)
		   )
	  <5> Producing a new collection
		  for clauses yield body
          Note: 返回的是Array

	(4)Exceptions expressions
	   <1> threw 
		   example: throw new IllegalArgumentException
           返回值是Nothing类型
       <2> try..catch
		   也有返回值，返回值是？
		   try{
			 do something
           }catch{
			 case ex: FileNotFoundException => 
             case ex: Exception => 
           }finally{}
	(5)Match expressions
	   返回值就是match到的result type
       val firstArg = "eggs"
	   val friend =
		 firstArg match {
			case "salt" => "pepper"
			case "chips" => "salsa"
			case "eggs" => "bacon"
			case _ => "huh?"
		 }   //friend = bacon
	(6)scala中没有continue和break，可以用Boolean型的变量来控制，或者用递归来控制

	(7)变量的作用域(variable scope)
	   Note: 和java差不多
	   Note: One difference between Java and Scala is that Scala allows you to define variables of 
	   the same name in nested scopes
 	   Question: 为什么在REPL中可以连续定义 val a = 1?
	   A: You can do this because conceptually the interpreter creates a new nested scope for each
		  new statement you type in.

###Chapter 7 Fuctions and Closure
	Note: 函数加括号其实是语法糖，是Function.apply()方法在实际调用
	(1)Local functions
	   scala可以在函数(方法)内部定义多个函数，做helper function，可以共享最外层函数的参数
	(2)First Class Functions
	   <1> function literal,可以看成java里面的匿名类，继承自Function[T1, T2....]，runtime的阶段called function values
	       synax： (x: Int, y: Int) => {function body}
	   <2> function literal 在某些情况下可以省略一些东西，比如在集合里，就可以省略类型参数等等
	(3)placeholder synax
       Note: use '_' 来当做占位符,一个占位符就代表一个参数
	   example: coll.filter(x => x > 0)  === coll.filter(_ > 0)
                val f = (x: Int, y: Int) => x + y ==  (_: Int) + (_: Int)

    (4)partially applied function（偏函数）
	   Note: 这里的占位符'_'可以代表整个/部分函数参数
	   example: 
		  def sum(a: Int, b: Int, c: Int) = a + b + c
		  val a = sum _   -->  a(1,2,3) = 6
          val b = sum(1, _: Int, 3) --> b(2) = 6, b(5) = 9
    (5)Closure
	   函数body中包含了free variable，那么这个free variable不在stack里，将会在heap中一直保存
	(6)Special Function Calls Forms
	   <1>Repeated parameters
          变长参数
		  def echo(args: String*) = {for (arg <- args) println(arg)} //可接受任意长度的参数
		  Note: 将Array[String]传进去的时候, echo(arr: _*)
       <2>Named arguments
		  speed(100, 10)
          speed(distance = 100, time = 10)
	   <3>Default parameter values
		  def printTime(out: java.io.PrintStream = Console.out) = {}
	(7)Tail Recursion
       尾递归优化
       