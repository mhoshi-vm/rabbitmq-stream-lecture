このレクチャは簡単な Spring のアプリが皆さんの用意している端末で実行可能であることを確認するためのものです。

# start.spring.io からコードを入手

作業用のディレクトリーを作成します。

```
mkdir test
cd test
```

そして、`web` および `actuator` だけを指定し、コードの雛形をダウンロードします。

```
curl -s https://start.spring.io/starter.tgz \
       -d dependencies=web,actuator \
       -d type=maven-project | tar -xzvf -
```

# コードの起動

以下のコマンドで起動します。

```
./mvnw spring-boot:run &
```

起動後以下のコマンドを入力します。

```
curl localhost:8080/actuator/health
```

以下が出力されること確認してください。

```
{"status":"UP"}
```

想定と異なる場合はJDKなどがインストールされているかなどをご確認ください。

すべてが完了したら、以下のコマンドで停止します。

```
kill %1
```