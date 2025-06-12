package util;

import VendorPortal.model.VendorPortaltestdata;

public class demo {
  /*  public static void main(String[]args) throws Exception
    {
        InputStream stream =ResourceLoader.getresources("test-suites/a/dummy.txt");
       String content= IOUtils.toString(stream, StandardCharsets.UTF_8);
       System.out.println(content);


    }*/

    public static void main(String[]args){
        VendorPortaltestdata testdata= null;
        try {
            testdata = JsonUtil.getTestdata("test-data/Vendor-portal/sam.json");
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(testdata.monthlyearning());
    }





}
