package fr.robin.android.surtain_com.util;

import android.util.Log;

import java.util.HashMap;

import fr.robin.android.surtain_com.data.SiteClient;

public class Data {

        // <p>[REGION]=Normandie (Basse Normandie)</p>\n\n\n
        public static HashMap getData(String corpsArticle){
            HashMap data = new HashMap();
            try{
                // <p>[REGION]=Normandie (Basse Normandie)</p>\n\n\n
                String[] split = corpsArticle.split("<p>");
                //[REGION]=Normandie (Basse Normandie)</p>\n\n\n
                String s1,s2,s3 = "";
                for(int i=0;i<split.length;i++){
                    //[REGION]=Normandie (Basse Normandie)
                    int j = split[i].indexOf("</p>");
                    if(j < 0){continue;}
                    s1 = split[i].substring(0,j);
                    //REGION
                    int c1 = s1.indexOf("[")+1;if(c1 < 0){continue;}
                    int c2 = s1.indexOf("]");if(c2 < 0){continue;}
                    s2 = s1.substring(c1,c2);
                    //Normandie (Basse Normandie)
                    int egal = s1.indexOf("=")+1;if(egal < 0){continue;}
                    s3 = s1.substring(egal);
                    //
                    data.put(s2,s3);
                }
            }catch(Exception e){
                e.printStackTrace();
                Log.e("MAIRIE Data Exception", e.getMessage());
            }
            return data;
    }

    // <p>[SURTAINVILLE] = COM:1111;http://www.lemaitre-robin.fr</p>\n\n\n
    public static HashMap<String,SiteClient> getDataAuthorisation(String corpsArticle){
        HashMap<String,SiteClient> data = new HashMap();
        try{
            // <p>[REGION]=Normandie (Basse Normandie)</p>\n\n\n
            String[] split = corpsArticle.split("<p>");
            //[REGION]=Normandie (Basse Normandie)</p>\n\n\n
            String s1,s2,s3,s4,s5 = "";
            for(int i=0;i<split.length;i++){
                //[REGION]=Normandie (Basse Normandie)
                int j = split[i].indexOf("</p>");
                if(j < 0){continue;}
                s1 = split[i].substring(0,j);
                //REGION
                int c1 = s1.indexOf("[")+1;if(c1 < 0){continue;}
                int c2 = s1.indexOf("]");if(c2 < 0){continue;}
                s2 = s1.substring(c1,c2);
                //Normandie (Basse Normandie)
                int egal = s1.indexOf("=")+1;if(egal < 0){continue;}
                s3 = s1.substring(egal);
                //Site client
                int k = s3.indexOf(";");if(k < 0){continue;}
                s4 = s3.substring(0,k);
                s5 = s3.substring(k+1);
                SiteClient client = new SiteClient(s4,s5);
                //
                data.put(s2,client);
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE Data Exception", e.getMessage());
        }
        return data;
    }

}
