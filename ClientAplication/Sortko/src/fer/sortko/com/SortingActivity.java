package fer.sortko.com;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class SortingActivity extends Activity implements OnClickListener {
	private long points = 0;
	private int NO_NUMBERS = 8;
	private int selectedButton = -1;
	private int sortTypeNumber = 0;
	private List<Button> list;
	private Button helpVariable;
	private TextView sortPoints, sortName;
	Algorithm sort;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
				WindowManager.LayoutParams.FLAG_FULLSCREEN);  

		setContentView(R.layout.sort);


		sortPoints = (TextView) findViewById(R.id.sortpoints);
		sortName = (TextView) findViewById(R.id.sortname);

		sortTypeNumber = getIntent().getIntExtra("fer.sortko.com.sortTypeNumber", 0);
		startSort();

		helpVariable = (Button) findViewById(R.id.helpvariable);
		if (sort.NEEDS_HELP_VARIABLE){
			helpVariable.setId(2008);
			helpVariable.setText("X");
			helpVariable.setVisibility(View.VISIBLE);
			helpVariable.setOnClickListener((OnClickListener) this);
		}

		ImageView changeSort = (ImageView) findViewById(R.id.changeSort);
		changeSort.setId(3000);
		changeSort.setOnClickListener((OnClickListener) this);

		list = new ArrayList<Button>();
		final LinearLayout buttonList = (LinearLayout) findViewById(R.id.buttonlist);

		for (int i = 0; i < NO_NUMBERS; i++){

			Button btn = new Button(this);
			btn.setId(2000+i);
			Integer randomNumber = sort.getNumbersCopy()[i];
			btn.setText(randomNumber.toString());
			btn.setBackgroundResource(R.drawable.normal_button);
			btn.setTextSize((float)26.0);
			btn.setTextColor(Color.WHITE);
			btn.setTypeface(Typeface.DEFAULT_BOLD);
			MarginLayoutParams margin = new MarginLayoutParams(53,53);
			margin.setMargins(6,0,0,0);
			LayoutParams params = new LayoutParams(margin);
			btn.setLayoutParams(params);
			btn.setOnClickListener((OnClickListener) this);
			buttonList.addView(btn);
			list.add(btn);
		}
		list.add(helpVariable);

		if(sort.isFinished()){
			displayMessage("Algoritam završen");
			showResultActivity();
		}
	}
	private void startSort(){
		Resources res = getResources();
		final CharSequence[] items = res.getStringArray(R.array.sorts);
		sortName.setText(items[sortTypeNumber]);

		switch (sortTypeNumber){
		case 0: sort = new BubbleSort(NO_NUMBERS);
		break;
		case 1: sort = new InsertionSort(NO_NUMBERS);
		break;
		case 2: sort = new QuickSort(NO_NUMBERS);
		break;
		case 3: sort = new ShellSort(NO_NUMBERS);
		break;
		case 4: sort = new SelectionSort(NO_NUMBERS);
		break;
		}
	}
	private void showResultActivity(){
		Intent resultIntent = new Intent(SortingActivity.this, ResultsActivity.class);
		resultIntent.putExtra("fer.sortko.com.result", points);
		resultIntent.putExtra("fer.sortko.com.sortTypeNumber", sortTypeNumber);
		startActivity(resultIntent);
		finish();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case 2000: selectButton(0);
		break;
		case 2001: selectButton(1);
		break;
		case 2002: selectButton(2);
		break;
		case 2003: selectButton(3);
		break;
		case 2004: selectButton(4);
		break;
		case 2005: selectButton(5);
		break;
		case 2006: selectButton(6);
		break;
		case 2007: selectButton(7);
		break;
		case 2008: selectButton(8);
		break;
		case 3000: changeSort();
		break;
		}
	}
	//TODO: izmjeniti ikonu za izmjenu sorta, dodati novu sliku
	private void changeSort() {
		Resources resources = getResources();
		final CharSequence[] items = resources.getStringArray(R.array.sorts);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Odaberi vrstu sortiranja!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Intent sortIntent = new Intent(SortingActivity.this,SortingActivity.class); 
				sortIntent.putExtra("fer.sortko.com.sortTypeNumber", item);
				startActivity(sortIntent);
				finish();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	private void selectButton(int buttonNumber){
		if (selectedButton == -1){
			selectedButton = buttonNumber;
			list.get(buttonNumber).setBackgroundResource(R.drawable.selected_button);

		}
		else if (selectedButton == buttonNumber){
			selectedButton = -1;
			list.get(buttonNumber).setBackgroundResource(R.drawable.normal_button);
		}
		else {
			// pokušaj zamjeniti brojeve, ako uspije vraæa bodove
			long newPoints = sort.goToNextPosition(new AlgorithmPosition(selectedButton, buttonNumber, null));

			if(newPoints > 0){
				points += newPoints;
			}
			else {
				points -= sort.NEGATIVE_POINTS;
				displayMessage("Niste odabrali dobar korak!");
			}

			refreshButtons();

			list.get(buttonNumber).setBackgroundResource(R.drawable.normal_button);
			list.get(selectedButton).setBackgroundResource(R.drawable.normal_button);
			selectedButton = -1;

			if(sort.isFinished()){
				displayMessage("Algoritam završen!");
				showResultActivity();
			}
		}
	}
	private void refreshButtons() {
		AlgorithmPosition ap = sort.findSwitch();
		int[] newNumbers = ap.getCurrentNumbersList();
		for (int i = 0; i<8; i++){
			list.get(i).setText(Integer.toString(newNumbers[i]));
		}
		if (sort.NEEDS_HELP_VARIABLE){
			helpVariable.setText(Integer.toString(ap.getHelpVariable()));
		}
		sortPoints.setText(Long.toString(this.points));
	}
	private void displayMessage(String string) {
		Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP, 0, 50);
		toast.show();
	}
	@Override
	public void onConfigurationChanged(final Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
	}
}
