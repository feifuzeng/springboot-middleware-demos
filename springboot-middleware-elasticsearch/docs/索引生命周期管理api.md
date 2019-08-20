# 有关Elasticsearch 索引生命周期管理相关api

> @version 6.7.1

## API

### [Put Lifecycle Policy API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-put-lifecycle-policy.html)
> 新建一个索引生命周期管理策略

* 示例-->[PutLifecyclePoliceTests](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/PutLifecyclePoliceTests.java)

### [Delete Lifecycle Policy API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-delete-lifecycle-policy.html)
> 删除一个索引生命周期管理策略

* 示例-->[DeleteLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/DeleteLifecyclePolicyDemo.java)

### [Get Lifecycle Policy API]
> 查询一个索引生命周期管理策略

* 示例-->[GetLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/GetLifecyclePolicyDemo.java)

### [Explain Lifecycle API]
> 检索一个索引生命周期管理策略有关一个或多个索引的生命周期策略执行的信息

* 示例-->[ExplainLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/ExplainLifecyclePolicyDemo.java)

### [Start Index Lifecycle Management API]
> 启动一个索引生命周期管理策略

* 示例-->[ExplainLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/ExplainLifecyclePolicyDemo.java)

* [Stop Index Lifecycle Management API]
> 关闭一个索引生命周期管理策略

* 示例-->[StopLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/StopLifecyclePolicyDemo.java)

* [Index Lifecycle Management Status API]
> 索引生命周期管理策略查询

* 示例-->[IndexLifecycleManagementStatusDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/IndexLifecycleManagementStatusDemo.java)

* [Retry Lifecycle Policy API]
> 对某索引生命周期策略进行重试

* 示例-->[RetryLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/RetryLifecyclePolicyDemo.java)

* [Remove Policy from Index API]
> 启动一个索引生命周期管理策略

* 示例-->[RemoveLifecyclePolicyDemo](../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/RemoveLifecyclePolicyDemo.java)


## 参考

* [官方说明- Index lifecycle management API](https://www.elastic.co/guide/en/elasticsearch/reference/6.7/ilm-put-lifecycle.html)

* [基于High-level-rest-Api](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/_index_lifecycle_management_apis.html)