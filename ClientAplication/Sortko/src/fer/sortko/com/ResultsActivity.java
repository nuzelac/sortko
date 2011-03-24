package fer.sortko.com;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends ListActivity implements OnClickListener{
    
	public static final String SORTKO_PREFS = "SortkoPrefsFile";
	
	private long sortingResult = 0;
	private int sortTypeNumber = 0;
	private String username = "";
	private String jmbag = "";
	private String methodName;
	private String methodParams;
    private ProgressDialog resultsProgressDialog = null;
    private ArrayList<Result> results = null;
    private ResultAdapter resultsAdapter;
    private Runnable viewResults;
	//TODO: spremanje rezultata u bazu podataka tako da ako nema veze da se može uploadati kasnije
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.result);
        
        sortingResult = getIntent().getLongExtra("fer.sortko.com.result", 0);
        sortTypeNumber = getIntent().getIntExtra("fer.sortko.com.sortTypeNumber", 0);
        
        if(sortingResult != 0){
	        SharedPreferences settings = getSharedPreferences(SORTKO_PREFS, 0);
	        username = settings.getString("username","Ivan Horvat");
	        jmbag = settings.getString("jmbag", "0000000000");
	        
	        //TODO: greška kada username tipa ime + prezime (s razmakom)
	        
	        methodName = "PohraniRezultat";
	        
			
	        try {
	        	methodParams = "igrac=" + URLEncoder.encode(username,"UTF-8") + "&jmbag=" + URLEncoder.encode(jmbag,"UTF-8") + "&rezultat=" + sortingResult + "&idvrstesorta=" + sortTypeNumber; 
	        }
	        catch (Exception e){
	        	Log.e("URLEncoder", e.getMessage());
	        }
	        Log.i("TEST", methodParams);
        }
	    TextView resultTextView = (TextView) findViewById(R.id.result);
	    resultTextView.setText(Long.toString(sortingResult));

        Button selectSort = (Button) findViewById(R.id.selectsort);
        selectSort.setOnClickListener((View.OnClickListener)this);
        
        results = new ArrayList<Result>();
        this.resultsAdapter = new ResultAdapter(this, R.layout.list_item, results);
                setListAdapter(this.resultsAdapter);
        
        viewResults = new Runnable(){
            @Override
            public void run(){
            	callWebService(methodName,methodParams);
                getResults(sortTypeNumber);
            }
        };
        
        //TODO: greška kada nema veze na net, if ima konekcije radi ovo dalje, inaèe ništa
        
        Thread thread =  new Thread(null, viewResults, "GetResults");
        thread.start();
        resultsProgressDialog = ProgressDialog.show(ResultsActivity.this,    
              "Prièekajte...", "Dohvaæanje podataka ...", true);        
	}
	
	@Override
	public void onClick(View view){
		if (view.getId()== R.id.selectsort){
			selectSort();
		}
	}
	private String callWebService(String methodName, String methodParams){
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
	        if (entity!=null){
		        try {
		            entity.consumeContent();
		        } catch (IOException e) {
		        	e.printStackTrace();
		        }
	        }
        }        
        return result;	
	}
	
	private void getResults(int idVrsteSorta){
		try{
			String result = callWebService("dohvatirezultate","idvrstesorta=" + Integer.toString(idVrsteSorta));
        	
            String[] items = result.substring(result.indexOf(">")+1,result.indexOf("</string>")).split("&#xD;",20);
            // header == <string xmlns="http://schemas.microsoft.com/2003/10/Serialization/">
            // result == MarkoMarulic§139909&#xD;
            // footer == </string>
            int resultNumber = 0;
            results = new ArrayList<Result>();
            for (String item : items) {
            	
                Result r = new Result();
                r.setResultPlace(Integer.toString(++resultNumber)+".");
            	r.setResultUser(item.substring(0, item.indexOf("§")).trim());
            	r.setResultNumber(item.substring(item.indexOf("§")+1));
            	Log.i("ITEM", r.getResultPlace()+" "+r.getResultUser()+" "+r.getResultNumber());
            	results.add(r);
            }
		}catch (Exception e) { 
			e.printStackTrace();
		}
		runOnUiThread(returnResults);
	}
	private Runnable returnResults = new Runnable() {
        @Override
        public void run() {
            if(results != null && results.size() > 0){
                resultsAdapter.notifyDataSetChanged();
                for(int i=0; i<results.size(); i++)
                	resultsAdapter.add(results.get(i));
            }
            resultsProgressDialog.dismiss();
            resultsAdapter.notifyDataSetChanged();
        }
    };
	
	private void selectSort(){
		Resources resources = getResources();
    	final CharSequence[] items = resources.getStringArray(R.array.sorts);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Odaberi vrstu sortiranja!");
    	builder.setItems(items, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int item) {
  	    	  Intent sortIntent = new Intent(ResultsActivity.this,SortingActivity.class); 
	    	  sortIntent.putExtra("fer.sortko.com.sortTypeNumber", item);
	    	  startActivity(sortIntent);
	    	  finish();
        	}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
	}
}
