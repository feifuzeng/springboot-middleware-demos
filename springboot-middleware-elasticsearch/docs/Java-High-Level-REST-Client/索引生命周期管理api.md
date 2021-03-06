# 有关Elasticsearch 索引生命周期管理相关api

> @version 6.7.1

## API

1. [Put Lifecycle Policy API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-put-lifecycle-policy.html)
> 新建一个索引生命周期管理策略
* 示例-->[PutLifecyclePoliceTests](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/PutLifecyclePolicyDemo.java)

2. [Delete Lifecycle Policy API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-delete-lifecycle-policy.html)
> 删除一个索引生命周期管理策略
* 示例-->[DeleteLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/DeleteLifecyclePolicyDemo.java)

3. [Get Lifecycle Policy API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-get-lifecycle-policy.html)
> 查询一个索引生命周期管理策略
* 示例-->[GetLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/GetLifecyclePolicyDemo.java)

4. [Explain Lifecycle API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-explain-lifecycle.html)
> 检索一个索引生命周期管理策略有关一个或多个索引的生命周期策略执行的信息
* 示例-->[ExplainLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/ExplainLifecyclePolicyDemo.java)

5. [Start Index Lifecycle Management API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-explain-lifecycle.html)
> 启动一个索引生命周期管理策略
* 示例-->[ExplainLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/ExplainLifecyclePolicyDemo.java)

6. [Stop Index Lifecycle Management API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-start-ilm.html)
> 关闭一个索引生命周期管理策略
* 示例-->[StopLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/StopLifecyclePolicyDemo.java)

7. [Index Lifecycle Management Status API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-status.html)
> 索引生命周期管理策略查询
* 示例-->[IndexLifecycleManagementStatusDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/IndexLifecycleManagementStatusDemo.java)

8. [Retry Lifecycle Policy API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-retry-lifecycle-policy.html)
> 对某索引生命周期策略进行重试
* 示例-->[RetryLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/RetryLifecyclePolicyDemo.java)

9. [Remove Policy from Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-remove-lifecycle-policy-from-index.html)
> 启动一个索引生命周期管理策略
* 示例-->[RemoveLifecyclePolicyDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/ilm/RemoveLifecyclePolicyDemo.java)


## 参考

* [官方说明- Index lifecycle management API](https://www.elastic.co/guide/en/elasticsearch/reference/6.7/ilm-put-lifecycle.html)

* [基于High-level-rest-Api](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/_index_lifecycle_management_apis.html)