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
        long time = System.currentTimeMillis();
        System.out.println("成功读入" + csvDao.readData() + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据导入失败！");
        System.out.println("耗时：" + (System.currentTimeMillis() - time) + "ms");
        Logger.reset();
        //2.通过sqlite数据源导入数据到MySQL
        DBDaoImpl dbDao = (DBDaoImpl) context.getBean("dbDaoImpl");
        long time2 = System.currentTimeMillis();
        System.out.println("成功读入" + dbDao.readData() + "条数据！");
        System.out.println(Logger.getDupCount() + "条重复数据导入失败！");
        System.out.println("耗时：" + (System.currentTimeMillis() - time2) + "ms");
        Logger.close();
    }
}
