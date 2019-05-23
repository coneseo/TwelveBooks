package com.twelvebooks.twelvebook.util;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

@Component
public class RestProperties {
    private static Properties props = null;

    public RestProperties(){
        props = new Properties();
//        Resource resource = new ClassPathResource("rest.properties");
        InputStream is = null;

        try{
            is = getClass().getResourceAsStream("/rest.properties");
//            is = new FileInputStream(resource.getFile());
            props.load(is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(is != null) try{is.close();}catch (Exception ignore){}
        }
    }

    public String getProperty(String key){
        return props.getProperty(key, "");
    }

    public String[] getProperty(String key, String deli){
        String value = props.getProperty(key, "");
        if(isNull(deli)) return new String[]{value};
        if (value.indexOf(deli) >= 0) {
            return value.split(deli);
        }
        return new String[] { value };
    }

    public static boolean isNull(Object obj) {
        if (obj == null || (obj instanceof String && (((String)obj).length() == 0) || "null".equalsIgnoreCase((String)obj))) return true;
        return false;
    }
}
