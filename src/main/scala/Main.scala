
object Main extends App {
  //  (3+2)*(4*7)
  // Just for junit to test if it compiles
  def msg = "I was compiled by dotty :)"
  
  type Environment = Map[String, Int]
  type EnvironmentFunction = Environment ?=> Int
  given env as Environment = Map("peter"->3, "zach"->7)
  enum Expression:
    case Value(v:Int)
    case Variable(name:String)
    case Add(o1:Expression, o2:Expression)
    case Multiply(o1:Expression, o2:Expression)
    // The Function Definitions for Subtract, Divide, Macro, Assign, Let, and Scope
    case Subtract(o1:Expression, o2:Expression) // My implementation of Subtract
    case Divide(o1:Expression, o2:Expression)   // My implementation of Divide
    case Macro(name:String, o1:Expression)      // According to the example Macro definition, it take two
                                                // paramerters, a string name and an expression and creates
                                                // a "Macro" for the expression
    case Assign(name:String, o1:Expression)   // According to the example Assign definition, it takes two
                                                // expressions and assigns a variable name to an expression     
    case Let(o1:Expression)                     // According to the Let example definition it takes an Assigned
                                                // variable expression and gives it a variable type
    case Scope(name:String, o1:Expression)      // According to the example Scope definition, it takes two parameters
                                                // a string name and expression, This assigns the scope of the expression

  
    //Macro(MacroName("someName"), Multiply(Add(Variable("var"), Value(1)), Value(3)))
  //Assign(Variable("somevar"), Add(Variable("var"), Macro("someName")))
  //Let(Assign(Variable("var2"), Add(Variable("var"), Macro("someName")))) In Add(Variable("var2"), Value(1))
  //Scope("scopename", Scope("othername", Assign(Variable("somevar"), Add(Variable("var"), Macro("someName")))))
  import Main.Expression._

  // Definition Expressions
  val multiAddResult:Expression = Multiply(Add(Variable("zach"),Value(2)), Multiply(Value(4),Value(7)))
  val divSubResult:Expression = Divide(Subtract(Variable("peter"),Value(0)), Divide(Value(10),Value(2)))
  val multiSubResult:Expression = Multiply(Subtract(Variable("zach"),Value(2)), Multiply(Value(4),Value(7)))
  val divAddResult:Expression = Divide(Add(Variable("peter"),Value(0)), Divide(Value(10),Value(2)))
  val resultScope:Expression = Multiply(Add(Variable("zach"),Value(2)), Multiply(Value(4),Value(7)))

  //Macro(MacroName("multiAddR"), Multiply(Add(Variable("zach"),Value(2)), Multiply(Value(4),Value(7)))
  //examples of a variable assignment with scope
  //Assign(Variable("somevar"), Add(Variable("var"), Macro("someName")))
  //Let(Assign(Variable("var2"), Add(Variable("var"), Macro("someName")))) In Add(Variable("var2"), Value(1))
  //examples of scope definition and use
  //Scope("scopename", Scope("othername", Assign(Variable("somevar"), Add(Variable("var"), Macro("someName")))))

  def eval(expression: Expression):Int = expression match {
    case Value(v) => v
    case Variable(someName) => summon[Environment].getOrElse(someName,throw Exception(s"undefined variable $someName"))
    case Add(o1, o2) => eval(o1) + eval(o2)
    case Multiply(o1, o2) => eval(o1) * eval(o2)
    // Evaluating my function definitions   
    case Subtract(o1, o2) => eval(o1) - eval(o2)
    case Divide(o1, o2) => eval(o1) / eval(o2)

      
  }
  // function expressions for Macro, Assign, Let, Scope
  def Macro(expression: Expression): (String, Int) = expression match{
      
    case Macro(name, o1) => (name, eval(o1))

  }

  def Assign(expression: Expression): (String, Int) = expression match {
    case Assign(name, o1) =>  (name, eval(o1))
  }
  
  def Let(expression: Expression): Any = expression match{
    case Let(o1) => Let(o1)
  }

  def Scope(expression: Expression): Any = expression match {
    case Scope(name, o1) =>  (name, eval(o1))
  }

  //Macro(Multiply(Add(Variable("var"), Value(1)), Value(3)))
  //Assign(Add(Variable("var"), Variable("someName")))
  //Let(Assign(Add(Variable("var"), Variable("someName")))) In Add(Variable("var2"), Value(1))
  //Scope("scopename", Scope(Assign(Add(Variable("var"), Variable("someName")))))
  
  // Print evaluation and result of a mutiplication + addition expression
  println(multiAddResult)
  println(eval(multiAddResult))

  // Print evaluation and result of a division + subtraction expression
  println(divSubResult)
  println(eval(divSubResult))

  // Print evaluation and result of a mutiplication + subtration expression
  println(multiSubResult)
  println(eval(multiSubResult))

  // Print evaluation and result of a division + addition expression
  println(divAddResult)
  println(eval(divAddResult))
  
  
}