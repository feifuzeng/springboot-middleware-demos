# 有关Elasticsearch 文档操作相关api

> @version 6.7.1

## API

### Document APIs

### Single document APIs

1. [Index API]()
> 新建一篇文档                                    
* 示例-->[CreateIndexDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/document/singal/DocIndexDemo.java)

2. [Get API]()
> 获取一篇文档
* 示例-->[DocGetDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/document/singal/DocGetDemo.java)

3. [Exists API]()
> 判断一篇文档是否存在
* 示例-->[DocExistDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/document/singal/DocExistDemo.java)

4. [Delete API]()
> 删除一篇文档
* 示例-->[DocDeleteDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/document/singal/DocDeleteDemo.java)

5. [Update API]()
> 更新一篇文档
* 示例-->[DocUpdateDemo](../../src/test/java/com/github/feifuzeng/middleware/elasticsearch/document/singal/DocUpdateDemo.java)

6. [Term Vectors API]()

### Multi-document APIs
1. [Bulk API]()
2. [Multi-Get API]()
3. [Reindex API]()
4. [Update By Query API]()
5. [Delete By Query API]()
6. [Rethrottle API]()
7. [Multi Term Vectors API]()


## 参考

* [官方说明- Document APIs](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.7/java-rest-high-supported-apis.html)
