package test;

/**
 * Created by Lumpanda on 2016/11/16.
 */
public class Main {

    public static void main(String[] args){

        String url = "http://www.offershow.online:8000/offerdetail/17";
        System.out.println( url.indexOf("offerdetail") );
        System.out.println( url.substring( url.lastIndexOf('l')+2, url.length() )  );

        String result = url.substring( url.lastIndexOf('l')+2, url.length() );
        System.out.println( "aaa"+result+"aaa" );

        System.out.println( Integer.parseInt(result) );

    }

}
