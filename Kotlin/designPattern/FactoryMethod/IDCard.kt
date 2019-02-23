package idcard
import framework.*
import java.util.*

class IDCard(val owner : String) : Product() {
    init {
	    println("${owner}のカードを作ります")
    }

    override fun use() {
        println("${owner}のカードを使います")
    }

    fun getOwner() : String {
        return owner
    }
}
