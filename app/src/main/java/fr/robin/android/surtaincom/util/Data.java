package fr.robin.android.surtaincom.util;

import android.util.Log;

import java.util.HashMap;

import fr.robin.android.surtaincom.data.SiteClient;

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

    // <p>[SURTAINVILLE] = COM;1111;http://www.lemaitre-robin.fr;9</p>\n\n\n
    // [SITE]=login;motPasse;URL;categorie ANDROID
    public static HashMap<String,SiteClient> getDataAuthorisation(String corpsArticle){
        HashMap<String,SiteClient> data = new HashMap();
        try{
            //
            String[] split = corpsArticle.split("<p>");
            //
            String s1,site,s3,s4,s5 = "";
            for(int i=0;i<split.length;i++){
                //
                int j = split[i].indexOf("</p>");
                if(j < 0){continue;}
                s1 = split[i].substring(0,j);
                //[SURTAINVILLE]
                int c1 = s1.indexOf("[")+1;if(c1 < 0){continue;}
                int c2 = s1.indexOf("]");if(c2 < 0){continue;}
                site = s1.substring(c1,c2);
                //
                int egal = s1.indexOf("=")+1;if(egal < 0){continue;}
                s3 = s1.substring(egal);
                //liste des éléments
                String[] elements = s3.split(";");
                String login = elements[0];
                String password = elements[1];
                //Traitement UTL
                String url = elements[2];
                int c3 = url.indexOf(">");
                if(c3 > 0) {
                    int c4 = url.indexOf("<",1);
                    url = url.substring(c3+1,c4);
                }
                //Catégorie
                String categorie = elements[3];
                //Login et password, URL, id categorie ANDROID
                SiteClient client = new SiteClient(site,login,password,url,Integer.parseInt(categorie));
                data.put(site,client);
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE COM - Data Exception", e.getMessage());
        }
        return data;
    }

    // <pre class></pre>\n\n\n
    public static String getDataCorp(String corpsArticle){
        String data = new String("");
        try{
            //<pre class="wp-block-preformatted"
            //String d1 = corpsArticle.replace("'","");
            String d2 = corpsArticle.substring(37);
            String d3 = d2.replace("</pre>","");
            String d4 = d3.replace("<p></p>","");
            data = d4.substring(0,d4.length()-1);
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE COM - Data Exception", e.getMessage());
        }
        return data;
    }
}
