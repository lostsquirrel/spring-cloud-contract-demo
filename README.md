# spring-cloud-contract-demo
demos leanning spring cloud contract

## 生产者 hello-contract-demo-server
contract 定义放在 `src/test/resourses/contract` 目录
执行 `mvn pacage` 会根据 contract 生成单元测试，并对生产者进行测试，只有测试通过，打包才能成功

## 消费者 hello-contract-demo-client
在单元测试时，可以用于启动 mock server, 
`@AutoConfigureStubRunner(ids = {"demo.spring.cloud:hello-contract-demo-server:+:stubs:8080"}, workOffline = true)`
详见 `demo.spring.cloud.contract.test.LoanApplicationServiceTests`

