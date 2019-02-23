//KotlinにおけるSingleton（インスタンスを１個しか存在しないことを保証する）は、
//とてつもなくシンプルにかけてしまう。

fun main(args: Array<String>) {
    println("Start.")

    val obj1 = Singleton
    val obj2 = Singleton

    if (obj1 == obj2) {
        println("obj1とobj2は同じインスタンスです。")
    } else {
        println("obj1とobj2は異なるインスタンスです。")
    }

    println("End.")

}

object Singleton {
    init {
        println("インスタンスを生成しました")
    }
}

//object（Singleton）はコンストラクタを持てないので、インスタンスを生成するときに()は不要
