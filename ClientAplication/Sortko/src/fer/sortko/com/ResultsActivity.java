package fer.sortko.com;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends Activity implements OnClickListener {
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.result);
        
        long sortingResult = getIntent().getLongExtra("fer.sortko.com.result", 0);
        
        TextView resultTextView = (TextView) findViewById(R.id.result);
        resultTextView.setText(Long.toString(sortingResult));
        
        Button selectSort = (Button) findViewById(R.id.selectsort);
        selectSort.setOnClickListener((View.OnClickListener)this);
        
        //http://www.sortko.com.hr/sortkoservice.svc/PohraniRezultat?igrac=igor&rezultat=12&idvrstesorta=2
		String result = getWebService("PohraniRezultat","igrac=igor&rezultat="+sortingResult+"&idvrstesorta=1");
        
        Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
		toast.show();
	}
	@Override
	public void onClick(View view){
		
		if (view.getId()== R.id.selectsort){
			selectSort();
		}
	}
	private void selectSort(){
		Resources resources = getResources();
    	final CharSequence[] items = resources.getStringArray(R.array.sorts);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Odaberi vrstu sortiranja!");
    	builder.setItems(items, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int item) {
  	    	  Intent sortIntent = new Intent(ResultsActivity.this,SortingActivity.class); 
	    	  sortIntent.putExtra("fer.sortko.com.sortNumber", item);
	    	  startActivity(sortIntent);
	    	  finish();
        	}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
	}

    public String getWebService(String methodName, String methodParams){
        HttpClient client = new DefaultHttpClient();
    	HttpHost targetHost = new HttpHost("www.sortko.com.hr",80,"http");
    	HttpGet httpGet = new HttpGet("/sortkoservice.svc/"+methodName+"?"+methodParams);
    	String result = null;
        HttpEntity entity = null;
        try {
            HttpResponse response = client.execute(targetHost, httpGet);
            entity = response.getEntity();
            result = EntityUtils.toString(entity);   
        }
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {
	        if (entity!=null)
	        try {
	            entity.consumeContent();
	        } catch (IOException e) {
	        	
	        }
        }
        return result;
    }
}
