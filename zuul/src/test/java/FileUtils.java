/* created by chenshi at 2018-11-20 */

import org.junit.Test;

import java.io.File;

public class FileUtils {

    @Test
    public void changeFileName() {
        File file = new File("E:\\OneDrive - VIP University\\java学习资料\\12：妙味原创js视频教程");
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                String fileName = file1.getName();
                String replaceName = fileName.replace("妙味课堂原创【JavaScript专题课程视频】", "");
                file1.renameTo(new File("E:\\OneDrive - VIP University\\java学习资料\\12：妙味原创js视频教程\\" + replaceName));
            }
//            String fileName = files[0].getName();
//            String replaceName = fileName.replace("妙味课堂原创JavaScript视频教程——", "");
//            files[0].renameTo(new File("E:\\OneDrive - VIP University\\java学习资料\\12：妙味原创js视频教程\\"+replaceName));

        }
    }
}
