# Spring におけるテストの違い

Spring においては、テストコードを組む上で、依存関係をアプリケーションコンテキストにどこまで組み込むかがテストの基準をわけることが一般的です。

![](img/894c1659.png)

# Spring の RabbitMQ のテスト方法について

マニュアルを必ずご参照ください。

https://docs.spring.io/spring-amqp/reference/testing.html

# 簡単な動作

- まったくの依存関係の呼び出しをしないテスト: [RabbitSimpleTest](testing/src/test/java/com/example/testing/RabbitSimpleTest.java)
- RabbitMQ が存在しなかったとしてもListnerをテストする方法: [RabbitSimpleIntTest](testing/src/test/java/com/example/testing/RabbitSimpleIntTest.java)
- TestContainerを使った統合テスト:[RabbitSimpleIntBTest](testing/src/test/java/com/example/testing/RabbitSimpleIntBTest.java)
