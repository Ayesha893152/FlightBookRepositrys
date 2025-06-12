package util;

import VendorPortal.model.VendorPortaltestdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

//this is used to convert input stream into java objects

public class JsonUtil {
    private static final Logger log= LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper=new ObjectMapper();

    public static VendorPortaltestdata getTestdata(String path){
       try(InputStream stream =ResourceLoader.getresources(path))
       {
           return mapper.readValue(stream, VendorPortaltestdata.class);
       }catch (Exception e)
       {
           log.error("unable to read test data file {}",path,e);
       }
       return null;


    }

}
