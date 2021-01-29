## IDaoSupport使用文档
### 一. 添加
通过Map插入
```
Map map = new HashMap();
map.put("name", "value");
this.goodsDaoSupport.insert("table_name", new HashMap());
```
通过PO插入
```
JdbcTestPo po = new JdbcTestPo();
this.goodsDaoSupport.insert("table_name", po);
```
通过T插入
```
JdbcTestPo testPo = new JdbcTestPo();
testPo.setName("aaaa");
testPo.setNum(18);
testPo.setTime(12345678901l);
testPo.setTotalPrice(22.2);
this.goodsDaoSupport.insert(testPo);
```
### 二. 修改
通过Map和条件数据Map修改数据
```
Map map = new HashMap();
map.put("name", "value");
Map whereMap = new HashMap();
whereMap.put("id", "1");
this.goodsDaoSupport.update("table_name", map, whereMap);
```
通过Map和条件String修改数据
```
Map map = new HashMap();
map.put("name", "value");
String where = " id=1 and pid=2 ";
this.goodsDaoSupport.update("table_name", map, where);
```
通过PO和条件数据Map修改数据
```
JdbcTestPo po = new JdbcTestPo();
Map whereMap = new HashMap();
whereMap.put("id", "1");
this.goodsDaoSupport.update("table_name", po, where);
```
通过PO和条件String修改数据
```
JdbcTestPo po = new JdbcTestPo();
String where = " id=1 and pid=2 ";
this.goodsDaoSupport.update("table_name", po, id);
```
通过T和条件主键id修改数据
```
JdbcTestPo testPo = new JdbcTestPo();
testPo.setName("bbb");
testPo.setNum(18);
testPo.setTime(12345678901l);
testPo.setTotalPrice(22.2);
testPo.setTest_id(1);
this.goodsDaoSupport.update(testPo, testPo.getTest_id());
```
### 三. 删除
通过class和id删除数据
```
this.goodsDaoSupport.delete(JdbcTestPo.class, 1);
```
### 四. 查询
读取单一结果集，返回Double类型数据
```
String sql="select column from table_name where id=? and pid=?";
Object[] value = new Object[2];
value[0] = 1;
value[1] = 2;
Double d = this.goodsDaoSupport.queryForDouble(sql, value);
```
读取单一结果集，返回Integer类型数据
```
String sql="select column from table_name where id=? and pid=?";
Object[] value = new Object[2];
value[0] = 1;
value[1] = 2;
Float d = this.goodsDaoSupport.queryForFloat(sql, value);
```
读取单一结果集，返回String类型数据
```
String sql="select column from table_name where id=? and pid=?";
Object[] value = new Object[2];
value[0] = 1;
value[1] = 2;
String s = this.goodsDaoSupport.queryForString(sql, value);
```
读取单一结果集，返回Float类型数据
```
String sql="select column from table_name where id=? and pid=?";
Object[] value = new Object[2];
value[0] = 1;
value[1] = 2;
Integer i =this.goodsDaoSupport.queryForInt(sql, value);
```
读取单一结果集，返回Long类型数据
```
String sql="select column from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
Long l =this.goodsDaoSupport.queryForLong(sql, args);
```
读取单一结果集，返回Map类型数据
```
String sql="select column from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
Map map =this.goodsDaoSupport.queryForMap(sql, args);
```
读取单一结果集，返回Object类型数据
```
Object obj =this.goodsDaoSupport.queryForObject(JdbcTestPo.class, 1);
```
读取单一结果集，返回List<泛型>和传递的class类型相同
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
JdbcTestPo clazz =this.goodsDaoSupport.queryForObject(sql, JdbcTestPo.class, args);
```
查询多行结果集，返回List
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
List list = this.goodsDaoSupport.queryForList(sql, args);
```
查询多行结果集，返回List<泛型>
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
List<JdbcTestPo> list = this.goodsDaoSupport.queryForList(sql, JdbcTestPo.class, args);
```
查询多行结果集，返回List根据分页
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
Integer pageNo = 1; //页数
Integer pageSize = 10; //条数
List list = this.goodsDaoSupport.queryForListPage(sql, pageNo, pageSize, args);
```
分页查询，返回Page
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
Integer pageNo = 1; //页数
Integer pageSize = 10; //条数
Page page = this.goodsDaoSupport.queryForPage(sql, pageNo, pageSize, args);
```
分页查询，返回Page<泛型>，可指定返回的泛型
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
Integer pageNo = 1; //页数
Integer pageSize = 10; //条数
Page<JdbcTestPo> page = this.goodsDaoSupport.queryForPage(sql, pageNo, pageSize, JdbcTestPo.class, args);
```
分页查询，返回Page
```
String countSql="select count(0) from table_name";    //总条数sql
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
Integer pageNo = 1; //页数
Integer pageSize = 10; //条数
Page page = this.goodsDaoSupport.queryForPage(sql, countSql, pageNo, pageSize, args);
```
### 五. 读取最后插入的行的id
读取最后插入的行的id。（务必和添加数据在同一是屋内）
```
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void test(String cacheKey) {
        Map map = new HashMap();
        map.put("name", "value");
        this.goodsDaoSupport.insert("table_name", new HashMap()); //添加
        Integer id =this.goodsDaoSupport.getLastId("table_name"); //读取id
    }
```
### 六. 自定义查询sql
自定义查询sql，此处的sql语句可任意。
```
String sql="select * from table_name where id=? and pid=?";
Object[] args = new Object[2];
args[0] = 1;
args[1] = 2;
this.goodsDaoSupport.execute(sql, args);
```