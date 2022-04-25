package util;

import java.util.HashMap;


public class MySpring {
    private static HashMap<String,Object> beanBox = new HashMap<>();
    public static <T>T getBean(String className){
        T obj = null;
        try {
            obj = (T)beanBox.get(className);
            if(obj == null){
                Class clazz = Class.forName(className);
                obj = (T)clazz.newInstance();
                beanBox.put(className,obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
