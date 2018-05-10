# seleniumTest
Java+Selenium+TestNG

1.Maven项目，import exist maven project导入

2.整合了ReportNG，如需使用，除去testng.xml中listener class-name="listener.RetryListener"
		listener class-name="listener.RetryOnfinishListener" 
			的注解，即可使用，不推荐，在这里只是提供一种实现方法

3.整合了失败重跑，TestNGRetry,除去testng.xml中listener class-name="listener.RetryListener" 
listener class-name="listener.RetryOnfinishListener" 的注解，即可使用，但不稳定，不推荐，在这里只是提供一种实现方法

4.含测试完成、失败截图功能

5.ReportNG测试报告在test-output文件中，为.html文件

设计思想：
测试用例编写在Excel中，对Excel进行POI操作，导入测试操作，对Chrome浏览器进行自动化测试。
