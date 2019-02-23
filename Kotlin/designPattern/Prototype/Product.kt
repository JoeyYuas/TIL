package framework

interface Product : Cloneable {
    abstract fun use(s : String)
    override abstract fun clone() : Product
}
