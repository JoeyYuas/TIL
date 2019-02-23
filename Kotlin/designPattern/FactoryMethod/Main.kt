import framework.*
import idcard.*


fun main(args: Array<String>) {
    val factory : Factory = IDCardFactory()
    val card1 = factory.create("湯浅")
    card1.use()
}
