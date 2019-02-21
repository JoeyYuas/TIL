fun main(args: Array<String>) {
    val p = PrintBanner("Hello")
    p.printWeak()
    p.printStrong()
}

open class Banner(val string: String) {
    fun showWithParen() {
        println("(${string})")
    }

    fun showWithAster() {
        println("*${string}*")
    }
}

abstract class Print {
    abstract fun printWeak()
    abstract fun printStrong()
}

class PrintBanner(val string: String): Print() {
    val banner = Banner(string)

    override fun printWeak() {
        banner.showWithParen()
    }

    override fun printStrong() {
        banner.showWithAster()
    }

}
