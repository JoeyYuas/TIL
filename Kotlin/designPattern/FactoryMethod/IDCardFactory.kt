package idcard
import framework.*
import java.util.*


class IDCardFactory : Factory() {
    val owners = ArrayList<String>()

    override fun createProduct(owner : String) : Product {
        val idCard = IDCard(owner)
        return idCard
    }

    override fun registerProduct(product : Product) {
        if(product is IDCard){
	        owners.add(product.getOwner())
        }
    }

    fun getOwners() : ArrayList<String> {
        return owners
    }
}
