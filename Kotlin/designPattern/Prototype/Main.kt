import framework.*

fun main(args: Array<String>) {
    val manager = Manager()
    val upen = UnderlinePen('~')
    val mbox = MessageBox('*')
    val sbox = MessageBox('/')
    manager.register("strong message", upen)
    manager.register("warning box", mbox)
    manager.register("slash box", sbox)

    val p1 = manager.create("strong message")
    p1.use("Hello, world.")
    val p2 = manager.create("warning box")
    p2.use("Hello, world.")
    val p3 = manager.create("slash box")
    p3.use("Hello, world.")
}

class MessageBox(val decochar : Char) : Product {
    override fun use(s : String) {
        val length = s.toByteArray().size

        for (i in 0..length + 4) {
            println(decochar)
        }

        println("")
        println("${decochar} ${s} ${decochar}")

        for (i in 0..length + 4) {
            println(decochar)
        }

        println("")
    }

    override fun clone() : Product {
        val p : Product = clone() as Product
        return p
    }
}

class UnderlinePen(val ulchar : Char) : Product {
    override fun use(s: String) {
        val length = s.toByteArray().size

        println("¥ ${s} ¥")
        println(" ")

        for (i in 0..length) {
            println(ulchar)
        }

        println("")
    }

    override fun clone() : Product {
        val p : Product = clone() as Product
        return p
    }
}
