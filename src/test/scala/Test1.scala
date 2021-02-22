
import Main.{Expression, divAddResult, divSubResult, multiAddResult, multiSubResult}
import org.junit.Test
import org.junit.Assert._

class Test1 {
  // Compile Test
  @Test def t1(): Unit = {
    assertEquals("I was compiled by dotty :)", Main.msg)
  }
  // Multiplication + Addition Test
  @Test def t2(): Unit = {
    var expected : Int  = 252
    var actual : Int  = Main.eval(multiAddResult)
    assertEquals(expected, actual)
  }
  // Division + Subtraction Test
  @Test def t3(): Unit = {
    var expected : Int  = 0
    var actual : Int  = Main.eval(divSubResult)
    assertEquals(expected, actual)
  } // Multiplication + Subtraction Test
  @Test def t4(): Unit = {
    var expected : Int  = 140
    var actual : Int  = Main.eval(multiSubResult)
    assertEquals(expected, actual)
  }
  // Division Test + Addition Test
  @Test def t5(): Unit = {
    var expected : Int  = 0
    var actual : Int  = Main.eval(divAddResult)
    assertEquals(expected, actual)
  }
  
}