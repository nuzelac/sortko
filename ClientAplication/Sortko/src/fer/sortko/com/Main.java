package fer.sortko.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity{
	
	public static final String SORTKO_PREFS = "SortkoPrefsFile";
	
	private String username = "";
	private String jmbag = "";
	private EditText usernameedit;
	private EditText jmbagedit;
	
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
        
        usernameedit = (EditText)findViewById(R.id.editusername);
        jmbagedit = (EditText)findViewById(R.id.editjmbag);
        
        SharedPreferences settings = getSharedPreferences(SORTKO_PREFS, 0);

        username = settings.getString("username",getResources().getString(R.string.username_Default));
        jmbag = settings.getString("jmbag",getResources().getString(R.string.uniquestudentid_Default));
        
        if (username != getResources().getString(R.string.username_Default)){
        	usernameedit.setText(username);
        }
        if (jmbag != getResources().getString(R.string.uniquestudentid_Default)){
        	jmbagedit.setText(jmbag);
        }
	}  
	
	private OnClickListener myListener = new OnClickListener() {
	    public void onClick(View v) {
	    	savePreferences();
	    	if (v.getId()==R.id.sortit){
	    		selectSort();
	    	} else {
	    		Intent resultIntent = new Intent(Main.this,ResultsActivity.class);
	    		startActivity(resultIntent);
	    		finish();
	      }
	    }

		private void savePreferences() {
		      SharedPreferences settings = getSharedPreferences(SORTKO_PREFS, 0);
		      SharedPreferences.Editor editor = settings.edit();
		      editor.putString("username", usernameedit.getText().toString());
		      editor.putString("jmbag", jmbagedit.getText().toString());
		      editor.commit();
		}
	};
	
	private void selectSort(){
		Resources resources = getResources();
    	final CharSequence[] items = resources.getStringArray(R.array.sorts);
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle(resources.getString(R.string.select_sort));
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