## sbt project compiled with Dotty

### Usage

This is a normal sbt project, you can compile code with `sbt compile` and run it
with `sbt run`, `sbt console` will start a Dotty REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).


The Function Definitions for Subtract, Divide, Macro, Assign, Let, and Scope were added.

According to the example Macro definition, it take two paramerters, a string name and an expression and creates
a "Macro" for the expression. For the Assign definition, it takes two expressions and assigns a variable name to an expression.  
According to the Let example definition it takes an Assigned variable expression and gives it a variable type.
For Scope definition, it takes two parameters a string name and expression, This assigns the scope of the expression.


Run the junit tests for testing the different expressions. Otherwise run it like normal Scala project.

