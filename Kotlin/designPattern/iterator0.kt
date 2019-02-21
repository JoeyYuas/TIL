/* interface Aggregate {
  abstract fun iterator() :Iterator
}

interface Iterator {
  abstract fun hasNext(): Boolean
  abstract fun next(): Any
}
*/

data class Book(val name: String)


class BookShelf(maxsize: Int){
  val books: Array<Book?> = arrayOfNulls(maxsize)
  var last: Int = 0

  fun getBookAt(index: Int): Book?{
    return books[index]
  }

  fun appendBook(book: Book){
    this.books[last] = book
    last ++
  }
/*
  override fun iterator(): Iterator{
    return BookShelfIterator(this)
  }
*/

}

/*
class BookShelfIterator(bookShelf: BookShelf): Iterator{
  private var index: Int = 0
  private val bookShelf: BookShelf = bookShelf

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
*/

fun main(args: Array<String>){
  val bookShelf: BookShelf = BookShelf(4)
  bookShelf.appendBook(Book("Around the World in 80 Days"))
  bookShelf.appendBook(Book("Bible"))
  bookShelf.appendBook(Book("Cinderella"))
  bookShelf.appendBook(Book("Daddy-Long-Legs"))

  val books = bookShelf.books
  val it = books.iterator()

  while(it.hasNext()){
    val book: Book = it.next() as Book
    println(book.name)
  }
}
