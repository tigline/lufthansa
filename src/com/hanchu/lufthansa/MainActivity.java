package com.hanchu.lufthansa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private Button startButtom;
	private ImageView t1_icon2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        startButtom = (Button) findViewById(R.id.bt_start);
        t1_icon2 = (ImageView) findViewById(R.id.t1_icon2);
        Animation animation1 = AnimationUtils.loadAnimation(
				MainActivity.this, R.anim.tutorail_rotate);
		LinearInterpolator lin = new LinearInterpolator();
		animation1.setInterpolator(lin);


		t1_icon2.startAnimation(animation1);
        startButtom.setOnClickListener(this);
    }
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_start:
			Intent intent = new Intent(MainActivity.this, RunwayActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
