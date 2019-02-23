package framework
import java.util.*

class Manager {
    private val showcase = HashMap<String, Product>()

    fun register(name : String, proto : Product) {
        showcase.put(name, proto)
    }

    fun create(protoname : String) : Product {
        val p : Product = showcase.get(protoname) as Product
        return p
    }
}
