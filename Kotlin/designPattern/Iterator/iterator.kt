//Iterator＝反復処理についてのパターン
//Aggregateインターフェース＝＞集合体を意味する。

//Aggregateインターフェースはiteratorメソッドのみ実装している
//集合体を数え上げたい、スキャンしたい、１つずつ調べていきたいというとき、
//iteratorメソッドを呼び出す。
interface Aggregate {
  abstract fun iterator() :Iterator
}

interface Iterator {
  abstract fun hasNext(): Boolean
  abstract fun next(): Any
}

data class Book(val name: String)

class BookShelf(maxsize: Int): Aggregate {
  val books: Array<Book?> = arrayOfNulls(maxsize)
  var last: Int = 0

  fun getBookAt(index: Int): Book?{
    return books[index]
  }

  fun appendBook(book: Book){
    this.books[last] = book
    last ++
  }

  override fun iterator(): Iterator{
    return BookShelfIterator(this)
  }
}

class BookShelfIterator(val bookShelf: BookShelf): Iterator{
  var index: Int = 0

  override fun hasNext(): Boolean{
    if(index < bookShelf.last) {
      return true
    }else{
      return false
    }
  }
  override fun next(): Any{
    val book = bookShelf.getBookAt(index)
    index ++
    return book as Any
  }
}

fun main(args: Array<String>){

  val bookShelf: BookShelf = BookShelf(4)
  bookShelf.appendBook(Book("Around the World in 80 Days"))
  bookShelf.appendBook(Book("Bible"))
  bookShelf.appendBook(Book("Cinderella"))
  bookShelf.appendBook(Book("Daddy-Long-Legs"))

  val it = bookShelf.iterator()
  while(it.hasNext()){
    val book: Book = it.next() as Book
    println(book.name)
  }
}
