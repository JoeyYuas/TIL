# Railsで１からWebサービスを作る方法

## 1.準備

1. `rails new アプリケーション名`でアプリケーションを作成する
1. `rails server`でサーバーを起動する
1. `rails g controller コントローラー名 アクション名`でコントローラーとそれに紐づくアクションを生成する。これは随時実行する

上記を一通り実施すると、routes.rbとcontrollerとhtml.erbができる。
まずメソッドを実施する前にこのあたりを整理しておくと次が楽になる。

つまり、`必要なView（HTMLとCSS）`、それに伴う`コントローラー`、そしてコントローラーとURLを結びつける`ルーティング`。以上は事前に設計する。


### 2.ページの追加方法

0. まずコントローラーを新規で作成する場合は、`1.準備`で実施した`rails g controller コントローラー名 アクション名`を実行する。
1. `routes.rb`に作成するページのルーティングを追記する。
2. `~.controller.rb`に以下のようにアクションを追記する。

```ruby
class HomeController < ApplicationController
  def top
  end

  def about #追加したいアクション
  end

end
```

※なお、アクションは処理をなにも書かない場合でもviewを生成する

3. `~.html.erb`にHTMLを記述する。

### 3.ヘッダーの追加方法

1. `view/layouts/application.html.erb`に共通の処理を書くことができる。ヘッダーやフッダーはここに記載することで、すべてのページに共通して表示させることが可能。

### 4.データベースの扱い方

0. データベースについては別途コントローラーを用意した方が良い。そのため、ターミナルで`rails g controller posts アクション名`を実行する。
1. テーブルを作成する。`rails g model テーブル名（頭大文字） カラム名:データ型`すると、`/db/migrate`の配下にマイグレーションファイルが作成される。
2. `rails db:migration`を実行するとマイグレーションファイルの中身がデータベースに書き込まれ、更新される。その際に、指定したカラム以外に`id`、`created_at`、`updated_at`の三つのカラムも自動生成される。

※ターミナル上でデータベースにアクセスしたいときは`rails console`を起動し、そこでコマンドを打っていく。`テーブル名.new(カラム名:データ)`を変数に格納してから`変数.save`でデータベースに保存される。

3. ヴァリデーションを設定しよう。`/models/posts.rb`に追記することで、データベースへの入力を制限することができる。

```ruby
validates :カラム名, {presence: true} #空のデータを制限する
validates :カラム名, {length: {maximum: 数値}} #文字数を制限する
validates :カラム名, {presence: true, length: {...}} #複数制限をまとめて書く
```

### 5.Webページからデータベースを操作する

1. SELECTする場合はコントローラーで「取得の」処理結果を変数に定義する。

1. ADDする場合、HTMLにまず`<%= form_tag("URL") do %>送信内容<% end %>`を追記し、送信内容に`nameタグ（name=名前）`を記載しておく。するとsubmitされたとき、`form_tag("URL")`のURLに飛ぶ（ルーティング）。そしてroutes.rbに`post "URL" => "コントローラー名#アクション"`と追記し、そのコントローラーでデータベースの追加処理を記述する。

ルーティング
```ruby
Rails.application.routes.draw do
  get "posts/index" => "posts#index"
  get "posts/new" => "posts#new"
  get "posts/:id" => "posts#show"

  post "posts/create" => "posts#create"
end
```

コントローラー
```ruby
class PostsController < ApplicationController
  def index #一覧を表示する
    @posts = Post.all.order(created_at: :desc)
  end

  def show #SELECTしたidから取得する
    @post = Post.find_by(id: params[:id])
  end

  def create #データベースに追加する
    @post = Post.new(content: params[:content])
    @post.save
    redirect_to("/posts/index")
  end
end
```

indexのhtml
```HTML
<div class="main posts-index">
  <div class="container">
    <% @posts.each do |post| %>
      <div class="posts-index-item">
        <%= link_to(post.content, "/posts/#{post.id}") %>
      </div>
    <% end %>
  </div>
</div>
```

showのhtml
```html
<div class="main posts-show">
  <div class="container">
    <div class="posts-show-item">
      <p>
        <%= @post.content %>
      </p>
      <div class="post-time">
        <%= @post.created_at %>
      </div>
    </div>
  </div>
</div>
```

newのhtml(=>createへのPost)
```html
<div class="main posts-new">
  <div class="container">
    <h1 class="form-heading">投稿する</h1>
    <div class="form">
      <div class="form-body">
        <%= form_tag("/posts/create") do %>
          <textarea name="content"></textarea>
          <input type="submit" value="投稿">
        <% end %>
      </div>
    </div>
  </div>
</div>
```


### Tips

- ルーティングにおけるgetとpostの違いは、get=データベースを変更しないアクション／post=データベースを変更するアクション、と考える。（そもそもフォームから情報を受け取った場合、ほぼ確実にデータベースと連携する）

- フラッシュについて: フラッシュとは、一時的にViewに表示され、すぐに削除されるものである。定義自体は共通のapplication.html.erbにしておく。そして、実装はコントローラーで行う。

```HTML
<% if flash[:notice] %>
  <div class="flash">
    <%= flash[:notice] %>
  </div>
<% end %>
```

```ruby
def create
  @post = Post.new(content: params[:content])
  if @post.save
    flash[:notice] = "投稿を作成しました"
    redirect_to("/posts/index")
  else
    render("/posts/new")
  end
end
```

- renderについて:redirect_toは一度ルーティングへ飛ばし、コントローラー／アクションを介するが、renderは直接Viewに飛ばすことができる。
