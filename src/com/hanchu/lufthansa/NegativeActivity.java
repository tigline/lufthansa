/**
 * 
 */
package com.hanchu.lufthansa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * @Project Lufthansa	
 * @author houxb
 * @Date 2015-7-8
 */
public class NegativeActivity extends Activity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_negative);
        


    }
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */

}
