package fer.sortko.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.main);
        
        Button sortit = (Button)findViewById(R.id.sortit);
        sortit.setOnClickListener(myListener);
        
        Button results = (Button)findViewById(R.id.results);
        results.setOnClickListener(myListener);
	}  
	
	//TODO: dodati da se upisuje ime igraca i njegov JMBAG i sprema u bazu
	private OnClickListener myListener = new OnClickListener() {
	    public void onClick(View v) {
	      if (v.getId()==R.id.sortit){
	    	  selectSort();
	      } else {
	    	  Intent resultIntent = new Intent(Main.this,ResultsActivity.class);
	    	  startActivity(resultIntent);
	    	  finish();
	      }
	    }
	};
	
	private void selectSort(){
		Resources resources = getResources();
    	final CharSequence[] items = resources.getStringArray(R.array.sorts);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Odaberi vrstu sortiranja!");
    	builder.setItems(items, new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int item) {
  	    	  Intent sortIntent = new Intent(Main.this, SortingActivity.class);
	    	  sortIntent.putExtra("fer.sortko.com.sortTypeNumber", item);
	    	  startActivity(sortIntent);
	    	  finish();
        	}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
	}
	


}