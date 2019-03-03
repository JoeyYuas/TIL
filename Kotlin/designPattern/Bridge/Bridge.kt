fun main(args: Array<String>) {
    val d1 = Display(StringDisplayImpl("Hello, Japan"))
    d1.display()
    val d2 = CountDisplay(StringDisplayImpl("Hello, World"))
    d2.display()
    val d3 = CountDisplay(StringDisplayImpl("Hello, Universe"))
    d3.display()

    d3.multiDisplay(5)
    
}

open class Display(val impl : DisplayImpl) {
    fun open() {
        impl.rawOpen()
    }

    fun print() {
        impl.rawPrint()
    }
    fun close() {
        impl.rawClose()
    }
    fun display() {
        open()
        print()
        close()
    }
}

class CountDisplay(impl : DisplayImpl) : Display(impl) {
    fun multiDisplay(times : Int) {
        open()
        for(i in 0..times) {
            print()
        }
        close()
    }
}

abstract class DisplayImpl {
    abstract fun rawOpen()
    abstract fun rawPrint()
    abstract fun rawClose()
}

class StringDisplayImpl(val string : String) : DisplayImpl() {
    private val width = string.toByteArray()

    override fun rawOpen() {
        printLine()
    }
    override fun rawPrint() {
        println("|${string}|")
    }
    override fun rawClose() {
        printLine()
    }

    private fun printLine() {
        println("+")
        for(wid in width) {
            println("-")
        }
        println("+")
    }
}
