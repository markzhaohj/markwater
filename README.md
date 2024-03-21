# 一、需求及分析
  
    PDF（Portable Document Format） 是工作及生活中常用的文档格式,
    有时基于安全及保护版权考虑需要对PDF 文档文件进行添加水印，
    本DEMO即提供为指定PDF文档每一页的页中心加印水印的功能。

# 二、方案

    1、一般有几类开源库可以实现该功能，比如 PDFBox库、iText库、Free Spire.PDF for Java 及 Aspose.PDF for Java，也有Ghostscript 的命令行工具实现该需求，本 Demo 采用使用较为便捷的 Aspose.PDF 进行实现。
   
    2、技术栈： 
     Spring， SpringBoot，aspose 第三方开源
   
    3、提供功能包含：
       1：提供文本水印
       2：提供图形水印
       3：在每页中心打印
       4：使用开源代码编写

# 三、设计

     1、设计为 Web Controler方式
     2、主要实现类为PdfController
     3、分别提供俩个RESTful API接口实现： 
        addWatermarkText 
        addWatermarkImage
    
# 四、POSTMAN 测试方法

     1、Open Postman 选择 POST 请求方法。
     2、URL 地址栏输入 http://localhost:8081/api/ncs/addWatermarkText
     3、Postman Header 标签中 Set ：Content-Type 为 multipart/form-data
     4：Body 标签页中选 form-data 类型设置 key 为 file，value 选择本地的 PDF 文件。

     图形水印类似 URL 改为http://localhost:8081/api/ncs/addWatermarkImage

# 五、文件说明

    1： PDFController ---- 提供文本水印和图形水印功能
    2： resources/doc 基础知识之 JDK，JAVA， OS.doc --- 为基础知识文档参考
    3： resources/doc 关于办理 2023 年度个税汇算清缴常见问题.PDF  --- 可作为测试图形水印的测试文件
    4： markwater/watermark.png ---- 图形水印文件，可作为测试图形水印的测试文件
    5： resources/lib 下为Aspose.PDF第三方包
