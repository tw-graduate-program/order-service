## 1. 代码提交注释规范
```shell
git commit –m "[#<card number>] <type>: <subject>"

type类型
1. feat：新功能（feature）
2. fix：修补bug
3. docs：文档（documentation）
4. style： 格式（不影响代码运行的变动）
5. refactor：重构（即不是新增功能，也不是修改bug的代码变动）
6. test：增加测试
7. chore：构建过程或辅助工具的变动
```


## 2. 启动

```yaml
docker-compose up database
docker-compose up backend

## 后端的服务在数据库启动之后启动，depends_on 是根据容器是否启动判断，不是容器中的应用
```


## 3. 接口 API

```yaml
1. 获取所有订单
GET
http://localhost:4321/orders

2. 根据 id 获取某个订单
GET
http://localhost:4321/orders/{id}

3. 添加订单
POST
http://localhost:4321/orders
Body
  {
    "contactId": "77777",
    "orderType": 77,
    "orderStatus": 9,
    "orderFee": 99988877.00,
    "deliveryFee": 9988877.00
  }

4. 修改订单
POST
http://localhost:4321/orders/{id}
Body
  {
    "contactId": "contact id",
    "orderType": 99,
    "orderStatus": 99,
    "orderFee": 999888777.00,
    "deliveryFee": 99888777.00
  }

5. 根据 id 删除订单
POST
http://localhost:4321/orders/{id}/deletion



```

