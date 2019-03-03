fun main(args: Array<String>) {

    val textbuilder = TextBuilder()
    val director = Director(textbuilder)

    println(textbuilder.getResult())

}

abstract class Builder {
    abstract fun makeTitle(title : String)
    abstract fun makeString(str : String)
    abstract fun makeItems(vararg items : String)
    abstract fun close()
}


class Director(val builder : Builder) {
    init {
        builder.makeTitle("Greeting")
        builder.makeString("朝から昼にかけて")
        builder.makeItems("おはようございます", "こんにちは")
        builder.makeString("夜に")
        builder.makeItems("こんばんは", "おやすみなさい", "さようなら")
        builder.close()
    }
}

class TextBuilder : Builder() {
    private val buffer = StringBuilder()

    override fun makeTitle(title : String) {
        buffer.append("===========================\n")
        buffer.append("『${title}』\n")
        buffer.append("\n")
    }

    override fun makeString(str : String) {
        buffer.append("■ ${str} \n")
        buffer.append("\n")
    }

    override fun makeItems(vararg items : String){
        for (item in items){
            buffer.append(" ・ ${item} \n")
        }
        buffer.append("\n")
    }

    override fun close() {
        buffer.append("===========================\n")
    }

    fun getResult() : String {
        return buffer.toString()
    }

}
