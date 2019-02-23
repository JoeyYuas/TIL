fun main(args: Array<String>) {
    val d1 : AbstractDisplay = CharDisplay('H')
    val d2 : AbstractDisplay = StringDisplay("Hello, World")
    val d3 : AbstractDisplay = StringDisplay("こんにちは")

    d1.display()
    d2.display()
    d3.display()

}


/*
Template MethodパターンはAbstractClass（抽象クラス）、ConcreteClass（具象クラス）
からなる。ACではテンプレート（使い回す予定のある）としてテンプレートメソッドを実装し、
そのテンプレートメソッドでは抽象メソッドを宣言する。サブクラスであるCCがその抽象メソッドを
実装する役割を持つ。そしてメイン処理でCCのインスタンスを作り、スーパークラスのACのテンプレート
メソッドを呼び出すことでアルゴリズムをなんども書かなくても平気にしてくれる。
*/

//AbstractClass
abstract class AbstractDisplay {
    abstract fun open()
    abstract fun print()
    abstract fun close()

    //Final is default in Kotlin.
    fun display() {
        open()
        for (i in 0..5){
            print()
        }
        close()
    }
}

//ConcreteClass
class CharDisplay(val ch : Char) : AbstractDisplay() {
    override fun open() {
        println("<<")
    }

    override fun print() {
        println(ch)
    }

    override fun close() {
        println(">>")
    }
}

//ConcreteClass
class StringDisplay(val string : String) : AbstractDisplay() {
    val width = string.toByteArray()

    override fun open() {
        printLine()
    }
    override fun print() {
        println("|${string}|")
    }
    override fun close() {
        printLine()
    }

    private fun printLine() {
        println("+")
        for (i in width){
            println("-")
        }
        println("+")
    }

}
