package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int= {
      if(c == 0 || c == r) 1
      else pascal(c, r-1) + pascal(c-1, r-1)
  }
  
  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def checkWithCount(chars: List[Char], cnt: Int): Boolean ={
      if (chars.isEmpty) cnt == 0
      else {
        val num =
          if (chars.head == '(') cnt + 1
          else if (chars.head == ')') cnt - 1
          else cnt

        if (num >= 0) checkWithCount(chars.tail, num)
        else false
      }
    }

    checkWithCount(chars, 0)
  }
  
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
  }
}
