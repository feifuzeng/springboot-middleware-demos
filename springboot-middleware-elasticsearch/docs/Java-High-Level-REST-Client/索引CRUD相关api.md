# 有关Elasticsearch 索引CRUD相关api

> @version 6.7.1

## API

### [Index Management]

#### [Analyze API]

1. [Create Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-ilm-ilm-put-lifecycle-policy.html)
> 新建一个索引                                     
* 示例-->[CreateIndexDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/indices/index/CreateIndexDemo.java)

2. [Delete Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-delete-index.html)
> 删除一个索引
* 示例-->[DeleteIndexDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/indices/index/DeleteIndexDemo.java)

3. [Indices Exists API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-indices-exists.html)
> 判断某索引是否存在
* 示例-->[IndexExistsDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/indices/index/IndexExistsDemo.java)

4. [Open Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-open-index.html)
> 基于索引名称打开索引
* 示例-->[]()

5. [Close Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-close-index.html)
> 基于索引名称关闭索引
* 示例-->[CloseIndexDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/indices/index/CloseIndexDemo.java)

6.[Shrink Index API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-shrink-index.html)
> 基于索引名称关闭索引
* 示例-->[CloseIndexDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/indices/index/CloseIndexDemo.java)

7. [Split Index API]()

8. [Refresh API]()

9. [Flush API]()

10. [Flush Synced API]()

11. [Clear Cache API]()

12. [Force Merge API]()

13. [Rollover Index API]()

14. [Update Indices Settings API](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-indices-put-settings.html)
> 更新索引配置
* 示例-->[UpdateIndicesSettingsDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/indices/index/UpdateIndicesSettingsDemo.java)
15. [Get Settings API]()

16. [Validate Query API]()

17. [Get Index API]()


### Mapping Management

1. [Put Mapping API]()
2. [Get Mappings API]()
3. [Get Field Mappings API]()

### Alias Management
1. [Index Aliases API]()
2. [Exists Alias API]()
3. [Get Alias API]()

### Template Management
1. [Get Templates API]()
2. [Templates Exist API]()
3. [Put Template API]()


## 参考

* [官方说明- Indices APIs](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/_indices_apis.html)
