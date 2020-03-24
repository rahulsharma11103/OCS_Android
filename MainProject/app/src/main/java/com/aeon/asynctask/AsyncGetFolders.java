package com.aeon.asynctask;

import java.util.ArrayList;
import com.aeon.adapters.FoldeListAdapter;
import com.aeon.constants.ImageFolders;
import com.aeon.myoncall.ImageSelector;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.widget.Toast;


public class AsyncGetFolders extends AsyncTask<String, Integer, String> {

	 ArrayList<ImageFolders> arrayy=new ArrayList<ImageFolders>();
	
	 private StringBuffer readbuffer = new StringBuffer();
    Context context;
    public AsyncGetFolders(Context context2) {
    	// TODO Auto-generated constructor stub
    	this.context=context2;
	}

	@Override
    protected String doInBackground(String... inputObj) {
        try {
        	
        	 Uri uri;
        	 Cursor cursor;

  		    int column_index_data, column_index_folder_name;
  		    ArrayList<String> listOfAllImages = new ArrayList<String>();
  		    String absolutePathOfImage = null;
  		    uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

  		    String[] projection = { MediaColumns.DATA,
  		            MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

  		    cursor = context.getContentResolver().query(uri, projection, null,
  		            null, null);

  		    column_index_data = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
  		    column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
  		  String temp_path="";
  		  int temp=0;
  		  String tem_path="",tem_imgpath="",temp_foldername="";
  		  int x=0;
  		  
  		    while (cursor.moveToNext()) {
  		    	  		
  		    	int sss=cursor.getCount();
  		    	int ddd=cursor.getPosition();
  		    	int xxx=sss-1;
  		    	System.out.println("hererer---->"+sss+"sdasdasdas----"+ddd);
  		    	
  		        absolutePathOfImage = cursor.getString(column_index_data);
  		        System.out.println("strig img path---->"+absolutePathOfImage);
  		        listOfAllImages.add(absolutePathOfImage);
  		        String indexOF ="";
  		        int tempIntex=1;
 
  		      //  int idnex=absolutePathOfImage
  		        String pathhss=absolutePathOfImage;
  		        String splitttext=absolutePathOfImage;
  		        String pathhss22=absolutePathOfImage;
  		        	
  		        		while(absolutePathOfImage.indexOf("/") != -1)
  		        	    {
  		        	        int index = absolutePathOfImage.indexOf("/");
  		        	        indexOF +=(index+tempIntex)+" ";
  		        	        tempIntex+=(index+1);
  		        	        absolutePathOfImage = absolutePathOfImage.substring(index + 1);
  		        	    }
  		        		//System.out.println("indexx off "+tempIntex);
  		        		
  		        		String hell[]=splitttext.split("/");
  		        		int countts=hell.length;
  		        		String folder_name=hell[countts-2];
  		        		String here_one=pathhss.substring(0, tempIntex-1);
  		        		
  		        		ImageFolders imggg=new ImageFolders();
  		        		
  		        		//System.out.println("here folder path -->"+here_one);
  		        		//System.out.println("folder name--->>"+folder_name);
  		        		if(temp_path.equalsIgnoreCase(folder_name)){
  		        			
  		        			tem_path=here_one;
  		        			tem_imgpath=pathhss;
  		        			temp_foldername=folder_name;
  		        			temp++;
  		        			if(ddd==xxx){
  		        				imggg.setFolder_img_path(tem_imgpath);
  	   		        			imggg.setFolder_path(tem_path);
  	   		        			imggg.setFolder_name(temp_foldername);
  	   		        			String coou=String.valueOf(temp);
  	   		        			imggg.setImagescounr(coou);
  	   		        			arrayy.add(imggg);
  		        			}
  		        			
  		        		}else{
  		        			if(x==0){
  		        				tem_path=here_one;
  	  		        			tem_imgpath=pathhss;
  	  		        			temp_foldername=folder_name;
  	  		        			temp_path=folder_name;
  	  		        			temp++;
  	   		        			x=2;
  		        			}else{
  		        				
  	  		        			//temp_path=folder_name;
  	   		        			imggg.setFolder_img_path(tem_imgpath);
  	   		        			imggg.setFolder_path(tem_path);
  	   		        			imggg.setFolder_name(temp_foldername);
  	   		        			String coou=String.valueOf(temp);
  	   		        			imggg.setImagescounr(coou);
  	   		        			temp=0;
  	   		        			arrayy.add(imggg);
  	   		        			
  	   		        			tem_path=here_one;
	  		        			tem_imgpath=pathhss;
	  		        			temp_foldername=folder_name;
	  		        			temp_path=folder_name;
	  		        			temp++;
  		        			}
  		        		}
  		    }
  		  cursor.close();
            
        } catch (Exception e) {
            Log.d("", e.toString());
        }
        return readbuffer.toString();
    }

    @Override
    protected void onPostExecute(String result) {
		
    	try {

		    ImageSelector.list_folders.setAdapter(new FoldeListAdapter(arrayy,context));	
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast.makeText(context, "No Places Returns", Toast.LENGTH_SHORT).show();
		}
    }
}
