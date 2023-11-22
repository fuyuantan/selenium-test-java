# selenium-java
Java + Selenium + TestNG

startup:

1.Maven projects: import by selecting "import existing Maven project";

2.Integrated with ReportNG. To use it, remove the annotations for listener: class-name="listener.RetryListener" and listener: class-name="listener.RetryOnfinishListener" in the testng.xml. NOT recommended; and which just provided a implementation method;

3.Integrated with TestNGRetry. To use it, remove the annotations for listener: class-name="listener.RetryListener" and listener: class-name="listener.RetryOnfinishListener" in the testng.xml. This method is unstable and NOT recommended, and which just provided a implementation method;

4.Included functionality for test completion notifications and capturing screenshots on test failures;

5.ReportNG test reports are in the test-output directory and are .html formal.

design philosophy:
Test cases are wrote in Excel, and using POI to operate the Excel for importing the testing operations. Meanwhile, the automated testing is conducted on the Chrome browser.

启动：

1.Maven 项目: import exist maven project 导入；

2.整合了 ReportNG，如需使用，除去 testng.xml 中 listener class-name="listener.RetryListener"
		listener class-name="listener.RetryOnfinishListener" 
			的注解，即可使用。不推荐，在这里只是提供一种实现方法；

3.整合了失败重跑(TestNGRetry),除去 testng.xml 中 listener class-name="listener.RetryListener" 
listener class-name="listener.RetryOnfinishListener" 的注解，即可使用；但不稳定，不推荐，在这里只是提供一种实现方法；

4.含测试完成、失败截图功能；

5.ReportNG 测试报告在 test-output 文件中，为 .html 文件。

设计思想：
测试用例编写在 Excel 中，对 Excel 进行 POI 操作，导入测试操作，在 Chrome 浏览器进行自动化测试。

Email: tanfy.cs@gmail.com
