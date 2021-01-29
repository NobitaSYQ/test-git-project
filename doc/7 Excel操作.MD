# Excel操作工具使用说明
### 写入excel
只有一个sheet:
```
//创建excel对象,传入的mySheetName为excel的sheet名字
Excel excel =new Excel("mySheetName");

excel.row("a",12,13.23) //写入一行   
     .row("b",14,12.44) //再写入一行
     //..可以写入任意多行    

     .write(outputStream);//将结果输出到流

//row方法接受Object...参数，可以传递任意多个，任意类型的参数
如果有多个sheet:

//也可以像这样new多个sheet:
Excel excel =new Excel()
excel.sheet("test1");
excel.row("a",12,13.23)

excel.sheet("test2");
excel.row("a",12,13.23)
```
### Controller示例
```
//返回值必须是byte[]
@GetMapping(value =    "/order/{sn}/goods/excel")
public  byte[] exportExcel(){
   try {
       Excel excel = new Excel("ceshi");
       for (int i = 0; i < 10; i++) {
          excel.row("a","b",i);
       }
       HttpServletResponse response = ThreadContextHolder.getHttpResponse();
       String fileName = "导出excel测试.xlsx";
       //设置头信息，Content-Disposition 表示为下载文件
       response.setHeader("Content-disposition", "attachment; filename="+new String( fileName.getBytes("gb2312"), "ISO8859-1" ));
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       excel.write(outputStream);
       return outputStream.toByteArray();
       } catch (IOException e) {
           throw new ServiceException(AftersaleErrorCode.E608.name(),"导出数据失败");
       }
   }
```
