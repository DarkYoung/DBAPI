import controller.CSVDaoImpl;
import controller.DBDaoImpl;
import controller.PreRead;
import logger.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        PreRead preRead = (PreRead) context.getBean("preRead");
        preRead.create();

        //1. 通过csv文件导入数据MySQL
        CSVDaoImpl csvDao = (CSVDaoImpl) context.getBean("csvDaoImpl");
        System.out.println("成功读入" + csvDao.readData(context) + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据导入失败！");

        //2.通过sqlite数据源导入数据到MySQL
        DBDaoImpl dbDao = (DBDaoImpl) context.getBean("dbDaoImpl");
        System.out.println("成功读入" + dbDao.readData(context) + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据导入失败！");

    }
}
