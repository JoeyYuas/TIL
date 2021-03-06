## デザインパターン入門をやっていく

#### Kotlinのアクセス修飾子
javaと違ってデフォルトで`public`だから、書く必要はまずない
でもってjavaだと必要なゲッター／セッターも不要、クラス名＋ドットで呼び出せる。これは内部で、以下の処理をしているから
>プロパティを宣言すると、Kotlinコンパイラはフィールドと2つのアクセサ（getterとsetter）を内部的に作成します。しかし通常、フィールドに直接アクセスすることはできず、アクセサを経由することになります。上記のコードではデフォルトのアクセサを使用して width の値を読み書きしていたのです。

#### Kotlinのデータクラス
Kotlinはデータだけを保持するクラスを作れるらしい。
例えば、javaだと

```java
public class Book {
  private String name;
  public Book(String name){
    this.name = name;
  }
  public String getName(){
    return name;
  }
}
```
ってクソ冗長（ただデータをとるだけなのに、カプセル化してやる必要があるから）になるし、取得するときもいちいちgetterを呼び出さなくちゃいけない。
でも、Kotlinなら以下の通りスマートにかける。

```Kotlin
data class Book(val name: String)
```
超絶みじけえ。比較するとぱねえ。これ、なにをしているのかというと

>これだけでコンパイラが勝手に以下のものを作ってくれる。ただしクラス内または継承元に明示的に定義されていれば勝手に生成したりしない。

>equals()/hashCode()ペア

>"User(name=John, age=42)"って表示するtoString()

>宣言順で内容を取り出すcomponentN()関数

>copy()

取ってくるときも`Book.name`でOK。らくちん！

#### KotlinとJavaの違い（写経していて）
わりと違う。

- JavaだとObjectがルートクラス（すべての型の元）だったけど、KotlinではAnyになっている。（というより、AnyがObjectのスーパークラスになっている）

- あとゲッターセッターがいらない。

- overrideはアノテーションではなく明示的に行う必要がある

```Kotlin

override fun test(){ println("test") }

```

#### Kotlinって実は・・・
デフォルトでclassはjavaでいうところの`final`であるからして、継承はできない。
継承するためにはクラス名の前に`open`と明示的につけてやる必要があるのである。

#### コンストラクタ
コンストラクタっていうのは、インスタンス生成（javaでいう`new`）時に実行されるメソッドを指す。

```Java

public class ConstractorTest {
  private int con;
  private String concon;

  public ConstractorTest(int con, String concon) {
    this.con = con;
    this.concon = concon;
  }
}

```

だけど、Kotlinだとコンストラクタはクラス名の後ろにつけとけば、勝手に作ってくれる。

```Kotlin

class ConstractorTest(private val con: Int, private val concon: String) {}

```

ちなみにコンストラクタを持ったクラスを継承する場合、

```Kotlin

// スーパークラス
open class ConstractorTest(val concon: String)
// サブクラス
class ConstractorTest(concon: String) : Human(concon)

```

ちなちなみにKotlinでコンストラクタの引数を用いた初期化処理（メソッド）を行いたい場合、

```Kotlin

class IDCard(val owner : String) : Product() {
    init {
	    println("${owner}のカードを作ります")
    }

```

とのように`init{処理}`で書ける

#### JavaのダウンキャストはKotlinだと以下の通り

```Kotlin

override fun registerProduct(product : Product) {
    if(product is IDCard){
      owners.add(product.getOwner())
    }
}

```

上記のような形をスマートキャストと呼ぶ。
（実質ダウンキャスト）

また、`product as IDCard`とも書ける。

#### KotlinのSingleton

```Kotlin

object Singleton {
  init {
    初期化処理
  }
}

val obj1 = Singleton

```

object（Singleton）はコンストラクタを持てないので、インスタンスを生成するときに()は不要

#### 可変長の配列を引数にとる場合

```Kotlin

fun makeItems(vararg items : String){
  for (item in items){
    buffer.append(" ・ ${item} \n")
  }
  buffer.append("\n")
}

```

`vararg`と書くことで、可変長であることを示している。

#### 文字列の結合

Javaも同様だが、文字列を結合するにはいくつか方法がある。
最も簡単なのは `”String” += ”String2”` であり、これを出力すると`StringString2`となる。

だが、このようにして文字列を結合した場合、見た目上は結合されただけのように見えるがそもそもStringはimmutableなオブジェクトである。よって内部の挙動としては新規にオブジェクトを生成してそこにポインタを入れ替えているだけであり、結果メモリ的に非効率なものになっていく

よって、`StringBuider`を使う。
[リファレンス](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html)
