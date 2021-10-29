#  后端部分

##  一、接口

###  1.与数据库的接口

后端对接MySQL数据库的表名

```java
@Entity
@Table(name= "knowledge_point_info_chart")//数据库表名
```

后端映射数据库列名。数据库主键要先声明，之后就是映射数据库的每一列到后端，这里只展示一个列，其他列都是相同的写法。

映射数据库列名包含两个部分，一个部分是把数据库的列名赋值到后端的变量中（最好使用同样的名字），另个部分是get和set。两个部分都不能缺失。

```java
@Id//定义主键，该注解下面的属性即为主键
@GeneratedValue(strategy = GenerationType.IDENTITY)

@Column(name= "knowledge_id")
private int knowledge_id;

public int getKnowledge_id() {
    return knowledge_id;
}
public void setKnowledge_id(int knowledge_id) {
    this.knowledge_id = knowledge_id;
}

//定义表中的每一列，即每一个属性
@Column(name="knowledge_point")
private String knowledge_point;

public String getKnowledge_point() {
    return knowledge_point;
}
public void setKnowledge_point(String knowledge_point) {
    this.knowledge_point = knowledge_point;
}

...
```

###  2.与前端的接口

在pojo里新建一个函数，接收来自前端的输出

```java
String searchContent;
public String getSearchContent() {
    return searchContent;
}
```

##  二、后端查询数据库语句

>在DAO里面定义一个 Interface 的 JAVA 类，类里面新建两个查询语句：
>
>1.查询所有的数据，与实时更新搜索热度帮相关
>
>2.查询前端向后端输入的关键词所在的知识点词条

查询所有数据的代码比较简单，只要返回之前数据库映射过来的列就可以了。

```java
List<GetKnowledgePointInfoDataBase> findAll();
```

根据关键词，查询数据库知识点栏包含该关键词的行，使用了把SQL查询语句内置到后端的办法处理：

```java
// knowledge_point_info_chart是数据库course_list_info下的表knowledge_point_info_chart，存网页搜索结果
@Query(name="findByKnowledge_point",nativeQuery = true,value = "select * from knowledge_point_info_chart where knowledge_point LIKE %:knowledge_point%")  

List<GetKnowledgePointInfoDataBase> findByKnowledge_point(@Param("knowledge_point") String knowledge_point);
```

以上都是定义了函数，函数包括了关键词的形参。使用这些函数是在controller里面。

##  三、后端接口传数据给前端

###  1.传递关键词搜索结果

```java
@Autowired GetKnowledgePointInfoDAO GetKnowledgePointInfoDAO;

@CrossOrigin  // 跨域
@PostMapping(value = "api/index")   // 处理POST请求
@ResponseBody  // 使返回结果被解析为json数据
public List<GetKnowledgePointInfoDataBase> findByKnowledge_point(@RequestBody GetCourseListInfoFront requestSearch){
    String searchContent = requestSearch.getSearchContent();
    List<GetKnowledgePointInfoDataBase> tp = GetKnowledgePointInfoDAO.findByKnowledge_point(searchContent);
    // 返回搜索到的匹配的JSON数组
    return tp;
}
```

说明：1.为了克服跨域问题，在处理POST请求时要添加 **@CrossOrigin** 语句

​			2.从DAO引入了函数模型findByKnowledge_point，将前端传来的关键词变量作为实参传递进去

###  2.传递整个数据库

```java
@Autowired GetKnowledgePointInfoDAO GetKnowledgePointInfoDAO;

@CrossOrigin
// 搜索全部的数组
@RequestMapping(value = "api/searchindex")   //处理初始界面GET请求，获取所有的数据库信息
@ResponseBody  // 使返回结果被解析为json数据
public List<GetKnowledgePointInfoDataBase> findAll()
{
    List<GetKnowledgePointInfoDataBase> tp = GetKnowledgePointInfoDAO.findAll();
    // 返回所有数据的JSON数组
    return tp;
}
```

#  前端部分

##  一、页面设计及功能

###  1.初始化页面

![初始](C:\Users\calypso\Desktop\总结\图片\初始.png)

> 页面上部是搜索栏。
>
> 中部是词条状态管理栏，可以显示词条个数，能根据课程状态、教师风格和是否为国家精品课多项筛选用户需要的词条。
>
> 右部是热搜榜，可以根据数据库中记载的词条搜索热度值实时更新。

说明：用户在搜索栏输入想要搜索的知识点关键词后，前端页面会通过 axios 传递给后端，后端在映射好的数据库中使用SQL语句搜索，并将符合条件的记录用 JSON 数组的方式返回给前端。

###  2.搜索成功页面

![搜索成功](C:\Users\calypso\Desktop\总结\图片\搜索成功.png)

搜索关键词：数据库 ，获得词条，知识点名字都带有“数据库”。

> 词条结构（以第一个词条为例）
>
> 数据库基础知识：知识点名
>
> 中南大学：该知识点所属课程所属学校
>
> 严晖：教授该知识点的老师    （严厉：教师教学风格）
>
> 所属课程：若后面有精品课程标签，则该课程属于精品课程
>
> 课程简介：所属课程简介
>
> 右上角：课程状态，分为即将开始（黄色）、已结束（灰色）、正在进行（绿色）

###  3.搜索失败页面

> 当输入的关键词没有任何匹配时触发

![搜索失败](C:\Users\calypso\Desktop\总结\图片\搜索失败.png)

###  4.筛选

课程状态：

![课程状态筛选](C:\Users\calypso\Desktop\总结\图片\课程状态筛选.png)

教师风格：

![教师风格筛选](C:\Users\calypso\Desktop\总结\图片\教师风格筛选.png)

国家精品：

![国家精品筛选](C:\Users\calypso\Desktop\总结\图片\国家精品筛选.png)

说明：点击勾选筛选条件后，筛选栏的名字会改为筛选的条件

![image-20210418165049107](C:\Users\calypso\AppData\Roaming\Typora\typora-user-images\image-20210418165049107.png)

###  5.技术实现

> 前端部分使用vue框架搭建脚手架，多组件开发，使用vuex综合管理各个组件都用到的数据

####  1）组件

![image-20210418170410446](C:\Users\calypso\AppData\Roaming\Typora\typora-user-images\image-20210418170410446.png)

index：父组件

indexheader：搜索栏所在组件

indexmain：搜索结果及热搜榜展示

indexfooter：分页所在组件

####  2）前端关键词接口

```javascript
// 与后台通信
axios
    .post('/index', {
        searchContent: indexSearchContent
    })
    .then((successResponse) => {
        let tempList = successResponse.data
        commit(SET_RESULT_ARRAY, {tempList})

        // 错误提示！
        if (successResponse.data.length === 0) {
             alert('【没有这样的结果！！！】')
        }
    })
    .catch((failResponse) => { })
```

> 说明：
>
> indexSearchContent：关键词储存在该变量中
>
> successResponse.data：后端返回的 JSON 数组

####  3）前端热搜接口

> 热搜在网页每次被刷新时发生变化

```javascript
axios
    .get('/searchindex')
    .then(response => {
        // 第一次处理数据，筛出只保留包含知识点名和热度的数组
        let tempList1 = []
                
        for (let i = 0; i < response.data.length; i++) {
            const tempObj = {
            	knowledge_point: '',   //知识点
            	hot_knowledge: ''   //热度
            }
            tempObj.knowledge_point = response.data[i].knowledge_point
            tempObj.hot_knowledge = response.data[i].hot_knowledge
            tempList1.push(tempObj)
        }
    	// console.log(tempList1)  //数组每一项对象只有 knowledge_point 和 hot_knowledge 两个元素

        // 第二次处理数据，将数据按照热度从高到低排列
        let hot_search = []  // 存前十名
        for (let i = 0; i < 10; i++) {
            for (let j = 0; j < tempList1.length - 1; j++) {
                if (tempList1[j].hot_knowledge > tempList1[j + 1].hot_knowledge) {
                    let temp = tempList1[j].hot_knowledge
                    tempList1[j].hot_knowledge = tempList1[j + 1].hot_knowledge
                    tempList1[j + 1].hot_knowledge = temp
                }
            }
            const tempObj = {
                 knowledge_point: '',   //知识点
                 hot_knowledge: ''   //热度
            }
            tempObj.knowledge_point = tempList1[tempList1.length - 1 - i].knowledge_point
            tempObj.hot_knowledge = tempList1[tempList1.length - 1 - i].hot_knowledge
            hot_search.push(tempObj)
         }
         // console.log(hot_search)  //按照热度从高到低排列的十个知识点

         commit(SET_ALL_ARRAY, {hot_search})

         // 错误提示！
         if (successResponse.data.length === 0) {
             	alert('【没有这样的结果！！！】')
         	}
         })
     .catch((failResponse) => { })
```

解释：第一次处理数据的目的是只保留数据库中的 “知识点” 和 “热度” ，因为只有这两个与后续的筛选相关。

​			第二次处理数据是将热度按照由大到小的顺序截取前十名，将前十名的“热度”和“知识点”放在一个新的数组里。

####  4）前端多条件筛选词条代码

```javascript
// 筛选函数
filter (condition, indexSearchResults) {
    return indexSearchResults.filter( item => {  // item 是 indexSearchResult 即返回数组的每一项
    	return Object.keys(condition).every( key => {  // key 是 上述每一项键值对的键名，如
    	    // item[key]是后端传来的每一项，condition是筛选数组的每一项，因为共用 key 键名，所以要求两个数组键名相同
            return item[key].includes(condition[key])
        })
    })
}
```

> 代码解释：
>
> 1.函数传入两个参数，condition是个对象，传递多个筛选条件，形式如下：
>
> ```javascript
> conditions: {
>     status: '',
>     teacher_style: '',
>     isquality: ''
> }
> ```
>
> indexSearchResults是所有词条数组
>
> 2.整个函数返回一个新词条数组，该数组符合多项筛选的条件
>
> 3.第一层return的作用：对词条数组进行过滤（filter函数），每个词条item应该满足某个条件
>
> 4.第二层return的作用：对每个词条item来说，里面包括筛选条件对象condition的所有键名的键值应该满足某个条件
>
> 5.第三层return的作用：从后端传来的每一个数组的，被拿来筛选的键名的每一个键值，都应该同时满足筛选条件condition，即包括对应筛选条件键名的键值

