//Client
fun main(args: Array<String>) {
    val p = PrintBanner("Hello")
    p.printWeak()
    p.printStrong()
}

//Target
open class Banner(val string: String) {
    fun showWithParen() {
        println("(${string})")
    }

    fun showWithAster() {
        println("*${string}*")
    }
}

//Adaptee
interface Print {
    abstract fun printWeak()
    abstract fun printStrong()
}

//Adapter
class PrintBanner(string: String): Banner(string), Print {

    override fun printWeak() {
        showWithParen()
    }

    override fun printStrong(){
        showWithAster()
    }
}
