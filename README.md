# knowledgepoints_searching
三个文件，mc_search是前端文件，get_course_list_info是后端文件，sql后缀文件是数据库文件
### 使用方法：
前端文件在编译器打开后在终端运行：npm run dev

后端文件在编译器中直接F5运行



# Backend part

## 1. Interface

### Interface with database

The name of the table used to connect the backend to the MySQL database

```java
@Entity
@Table(name= "knowledge_point_info_chart")//Database table name
```

Back-end mapping database column name. The primary key of the database must be declared first, and then each column of the database is mapped to the backend. Only one column is shown here, and the other columns are written in the same way.

The mapping database column name consists of two parts, one part is to assign the database column name to the back-end variable (preferably use the same name), and the other part is get and set. Neither part can be missing.

```JAVA
@Id//Define the primary key, the attribute below the annotation is the primary key
@GeneratedValue(strategy = GenerationType.IDENTITY)

@Column(name= "knowledge_id")
private int knowledge_id;

public int getKnowledge_id() {
    return knowledge_id;
}
public void setKnowledge_id(int knowledge_id) {
    this.knowledge_id = knowledge_id;
}

//Define each column in the table, that is, each attribute
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

### Interface with Frontend

Create a new function in pojo to receive the output from the front end

```java
String searchContent;
public String getSearchContent() {
    return searchContent;
}
```

## 2. Back-end query database statement

> Define an Interface JAVA class in DAO, and create two new query statements in the class:
>
> 1. Query all data, related to real-time update of search popularity help
>
> 2. Query the knowledge point entries where the keywords input from the front end to the back end are located

The code for querying all data is relatively simple, as long as it returns the column mapped from the previous database.

```java
List<GetKnowledgePointInfoDataBase> findAll();
```

According to the keyword, query the row containing the keyword in the knowledge point column of the database, and the SQL query statement is built into the back-end to process:

```java
// knowledge_point_info_chart is the table knowledge_point_info_chart under the database course_list_info, which stores web search results
@Query(name="findByKnowledge_point",nativeQuery = true,value = "select * from knowledge_point_info_chart where knowledge_point LIKE %:knowledge_point%")  

List<GetKnowledgePointInfoDataBase> findByKnowledge_point(@Param("knowledge_point") String knowledge_point);
```

All of the above defines the function, and the function includes the formal parameters of the keyword. These functions are used in the controller.

## 3. The back-end interface transmits data to the front-end

### 1. Pass keyword search results

```java
@Autowired GetKnowledgePointInfoDAO GetKnowledgePointInfoDAO;

@CrossOrigin  
@PostMapping(value = "api/index")   // Process POST request
@ResponseBody  // Make the returned result parsed as json data
public List<GetKnowledgePointInfoDataBase> findByKnowledge_point(@RequestBody GetCourseListInfoFront requestSearch){
    String searchContent = requestSearch.getSearchContent();
    List<GetKnowledgePointInfoDataBase> tp = GetKnowledgePointInfoDAO.findByKnowledge_point(searchContent);
    // Return the searched matching JSON array
    return tp;
}
```

Description: 

1. In order to overcome the cross-domain problem, add **@CrossOrigin** statement when processing POST request

 2. The function model findByKnowledge_point is introduced from DAO, and the keyword variables from the front end are passed as actual parameters.

### 2. Pass the entire database

```java
@Autowired GetKnowledgePointInfoDAO GetKnowledgePointInfoDAO;

@CrossOrigin
// Search all arrays
@RequestMapping(value = "api/searchindex")   //Handle the initial interface GET request and get all the database information
@ResponseBody  // Make the returned result parsed as json data
public List<GetKnowledgePointInfoDataBase> findAll()
{
    List<GetKnowledgePointInfoDataBase> tp = GetKnowledgePointInfoDAO.findAll();
    // Return a JSON array of all data
    return tp;
}
```

# Frontend part

## 1. Page design and function

### 1. Initialize the page
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/Initial.png)

> At the top of the page is the search bar.
>
> In the middle is the entry status management column, which can display the number of entries, and can filter the entries that users need according to the course status, teacher style and whether it is a national quality class.
>
> On the right is the hot search list, which can be updated in real time according to the search popularity value of the term recorded in the database.

Description: After the user enters the key words of the knowledge points that they want to search in the search bar, the front-end page will be passed to the back-end through axios, and the back-end uses SQL statements to search in the mapped database, and uses the JSON array of records that meet the conditions The way is returned to the front end.

### 2. Search success page
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/Search%20successful.png)

Search keywords: database, get entries, and knowledge points have "database" in their names.

> Entry structure (take the first entry as an example)
>
> Basic knowledge of database: knowledge naming
>
> Central South University: The school to which the knowledge point belongs
>
> Yan Hui: The teacher who taught this knowledge point (Stern: Teacher's teaching style)
>
> Affiliated course: If there is an excellent course label at the back, the course belongs to an excellent course
>
> Course introduction: introduction to the course
>
> Upper right corner: Course status, divided into about to start (yellow), finished (gray), and in progress (green)

### 3. Search failed page
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/Search%20failed.png)

> Triggered when there is no match for the entered keyword

### 4. filter

Course status:
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/Course%20status%20screening.png)

Teacher style:
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/Teacher%20style%20selection.png)

National Boutique:
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/National%20Boutique%20Screening.png)

Note: After you click to check the filter conditions, the name of the filter column will be changed to the filter conditions
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/Filter.png)

### 5. Technical realization

> The front-end part uses the vue framework to build scaffolding, multi-component development, and uses vuex to comprehensively manage the data used by each component

#### (a) Component
![image](https://github.com/Calypso52/knowledgepoints_searching/blob/main/pictures/components.png)

> Index: parent component
>
> Indexheader: the component where the search bar is located
>
> Indexmain: Search results and hot search list display
>
> Indexfooter: the component where the page is located

#### (b) Front-end keyword interface

```javascript
// Communicate with the background
axios
    .post('/index', {
        searchContent: indexSearchContent
    })
    .then((successResponse) => {
        let tempList = successResponse.data
        commit(SET_RESULT_ARRAY, {tempList})

        // Error message!
        if (successResponse.data.length === 0) {
             alert('There is no such result! ! ! ')
        }
    })
    .catch((failResponse) => { })
```

illustrate:

indexSearchContent: Keywords are stored in this variable

successResponse.data: JSON array returned by the backend

#### (c) Front-end hot search interface

> Hot search changes every time the page is refreshed

```javascript
axios
    .get('/searchindex')
    .then(response => {
        // Process the data for the first time, and filter out only the array that contains the name and popularity of the knowledge
        let tempList1 = []
                
        for (let i = 0; i < response.data.length; i++) {
            const tempObj = {
            	knowledge_point: '',   //Knowledge points
            	hot_knowledge: ''   
            }
            tempObj.knowledge_point = response.data[i].knowledge_point
            tempObj.hot_knowledge = response.data[i].hot_knowledge
            tempList1.push(tempObj)
        }
    	// console.log(tempList1)  //Each object in the array has only two elements: knowledge_point and hot_knowledge

        // Process the data for the second time and arrange the data in order of popularity from high to low
        let hot_search = []  // Save the top ten
        for (let i = 0; i < 10; i++) {
            for (let j = 0; j < tempList1.length - 1; j++) {
                if (tempList1[j].hot_knowledge > tempList1[j + 1].hot_knowledge) {
                    let temp = tempList1[j].hot_knowledge
                    tempList1[j].hot_knowledge = tempList1[j + 1].hot_knowledge
                    tempList1[j + 1].hot_knowledge = temp
                }
            }
            const tempObj = {
                 knowledge_point: '',   //Knowledge points
                 hot_knowledge: ''   
            }
            tempObj.knowledge_point = tempList1[tempList1.length - 1 - i].knowledge_point
            tempObj.hot_knowledge = tempList1[tempList1.length - 1 - i].hot_knowledge
            hot_search.push(tempObj)
         }
         // console.log(hot_search)  //Ten knowledge points arranged in descending order of popularity

         commit(SET_ALL_ARRAY, {hot_search})

         // Error message!
         if (successResponse.data.length === 0) {
             	alert('There is no such result! ! ! ')
         	}
         })
     .catch((failResponse) => { })
```

Explanation: The purpose of processing the data for the first time is to keep only the "knowledge points" and "hots" in the database, because only these two are related to subsequent screening.

 The second time to process the data is to intercept the top ten in descending order of popularity, and place the top ten "hots" and "knowledge points" in a new array.

#### (d) Front-end multi-condition filtering entry code

```javascript
// Filter function
filter (condition, indexSearchResults) {
    return indexSearchResults.filter( item => {  // item is indexSearchResult, that is, each item of the array is returned
    	return Object.keys(condition).every( key => {  // key is the key name of each of the above key-value pairs
    	    // item[key] is each item from the backend, and condition is to filter each item of the array. Because the key name is shared, the two array keys are required to be the same
            return item[key].includes(condition[key])
        })
    })
}
```

> Code explanation:
>
> 1. The function passes in two parameters, condition is an object, passing multiple filter conditions, in the form as follows:
>
>    ```javascript
>    conditions: {
>     status: '',
>     teacher_style: '',
>     isquality: ''
>    }
>    ```
>
>    indexSearchResults is an array of all entries
>
> 2. The entire function returns an array of new entries, which meets multiple filtering conditions
>
> 3. The role of the first layer return: filter the entry array (filter function), each entry item should meet a certain condition
>
> 4. The role of the second layer return: for each entry item, the key value of all keys including the filter condition object condition should satisfy a certain condition
>
> 5. The role of the third layer return: every key value of the key name used for filtering in every array passed from the backend should also meet the filter condition condition, that is, the key that corresponds to the filter condition key name

