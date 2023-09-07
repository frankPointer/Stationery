package pointer.util;

import java.awt.*;

public class ScreenUtil {

    /**
     *
     * @return 电脑屏幕的宽度
     */
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }


    /**
     *
     * @return 电脑屏幕高度
     */
    public static int getScreenHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
