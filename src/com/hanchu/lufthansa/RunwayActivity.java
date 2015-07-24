/**
 * 
 */
package com.hanchu.lufthansa;

import java.util.ArrayList;
import java.util.List;

import com.hanchu.lufthansa.adapter.ViewPagerAdapter;
import com.hanchu.lufthansa.scrollviewtext.AutoScrollTextView;
import com.hanchu.lufthansa.typetextview.TypeTextView;
import com.hanchu.lufthansa.viewpager.ViewPager.OnPageChangeListener;
import com.hanchu.lufthansa.viewpager.ViewPager;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
//import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import android.widget.TextView;
/**
 * @Project Lufthansa	
 * @author houxb
 * @Date 2015-7-8
 */
public class RunwayActivity extends FragmentActivity implements OnPageChangeListener{
	
	
	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;

	private List<View> views;
	private AutoScrollTextView scrollText = null;
	// 底部小点图片
	//private ImageView[] dots;

	// 记录当前选中位置
	//private int currentIndex;
	
	private TextView[] tvs = new TextView[6];
    private Handler handler = new Handler();
    
	private TypeTextView mTypeTextView = null;
	private TypeTextView mTypeTextView1 = null;
	private TypeTextView mTypeTextView2 = null;
	private TypeTextView mTypeTextView3 = null;
	private TypeTextView mTypeTextView4 = null;
	private TypeTextView mTypeTextView5 = null;
	
	private ImageView g0_next ,g1_next, g2_next, g3_next,
						g4_next, g5_next, g6_next, g7_next,
						g8_next;
	private Animation animationBottom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		
		// 初始化页面
		initViews();

		// 初始化底部小点
		//initDots();
		mTypeTextView.start(TextData.DATA_1);
		BackgroundMusic.getInstance(this).playBackgroundMusic("Butterfly Kiss.mp3", true);
		
	}
	
	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);

		views = new ArrayList<View>();
		// 初始化引导图片列表
		views.add(0,inflater.inflate(R.layout.guide_one, null));
		mTypeTextView = (TypeTextView) views.get(0).findViewById(R.id.typeTx_1);
		g0_next = (ImageView) views.get(0).findViewById(R.id.g0_next);
		views.add(1,inflater.inflate(R.layout.guide_two, null));
		g1_next = (ImageView) views.get(1).findViewById(R.id.g1_next);
		textView1();
		
		views.add(2,inflater.inflate(R.layout.guide_three, null));
		g2_next = (ImageView) views.get(2).findViewById(R.id.g2_next);
		mTypeTextView1 = (TypeTextView) views.get(2).findViewById(R.id.typeTx_2);
		
		views.add(3,inflater.inflate(R.layout.guide_four, null));
		g3_next = (ImageView) views.get(3).findViewById(R.id.g3_next);
		
		views.add(4,inflater.inflate(R.layout.guide_five, null));
		g4_next = (ImageView) views.get(4).findViewById(R.id.g4_next);
		
		views.add(5,inflater.inflate(R.layout.guide_six, null));
		g5_next = (ImageView) views.get(5).findViewById(R.id.g5_next);		
		scrollText = (AutoScrollTextView) views.get(5).findViewById(R.id.scrolltext);
		
		views.add(6,inflater.inflate(R.layout.guide_seven, null));
		g6_next = (ImageView) views.get(6).findViewById(R.id.g6_next);
		mTypeTextView2 = (TypeTextView) views.get(6).findViewById(R.id.typeTx_7);
		
		views.add(7,inflater.inflate(R.layout.guide_eight, null));
		g7_next = (ImageView) views.get(7).findViewById(R.id.g7_next);
		mTypeTextView3 = (TypeTextView) views.get(7).findViewById(R.id.typeTx_8);
		
		views.add(8,inflater.inflate(R.layout.guide_nine, null));
		g8_next = (ImageView) views.get(8).findViewById(R.id.g8_next);
		mTypeTextView4 = (TypeTextView) views.get(8).findViewById(R.id.typeTx_9);
		
		views.add(9,inflater.inflate(R.layout.guide_ten, null));
		mTypeTextView5 = (TypeTextView) views.get(9).findViewById(R.id.typeTx_10);
		//mTypeTextView = new TypeTextView(this);
				
		// 初始化Adapter
		vpAdapter = new ViewPagerAdapter(views, this);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		// 绑定回调
		vp.setOnPageChangeListener(this);
		animal(0);
	}

	private void textView1(){
		tvs[0] = (TextView) views.get(1).findViewById(R.id.text_1);
        tvs[1] = (TextView) views.get(1).findViewById(R.id.text_2);
        tvs[2] = (TextView) views.get(1).findViewById(R.id.text_3);
        tvs[3] = (TextView) views.get(1).findViewById(R.id.text_4);
        tvs[4] = (TextView) views.get(1).findViewById(R.id.text_5);
        tvs[5] = (TextView) views.get(1).findViewById(R.id.text_6);
	}
	/*
	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

		dots = new ImageView[views.size()];

		// 循环取得小点图片
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);// 都设为灰色
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
				
	}
	
	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = position;
		switch (currentIndex) {
		case 0:
			mTypeTextView.start(DATA_1);	
			break;
		case 1:
			loadContent();
			break;
		case 2:
			mTypeTextView1.start(DATA_2);
		case 4:
			scrollText.initScrollTextView(this.getWindowManager(), 
					DATA_5); 
			scrollText.starScroll();
			break;
		default:
			break;
		}
	}
	*/

		
	private void loadContent() {
	        setInvisible();
	        for (int j = 0; j < tvs.length; j++) {
	            final TextView tv = tvs[j];
	            Runnable r = new Runnable() {
	                @Override
	                public void run() {
	                    setAnimation(tv);
	                }
	            };
	// 设置动画延时
	        handler.postDelayed(r, j * 1500);
	    }
	}
	
	// 设置动画
	private void setAnimation(final TextView tv) {
	    AlphaAnimation aa = new AlphaAnimation(0, 1.0f);
	    aa.setDuration(1000);
	    aa.setAnimationListener(new AnimationListener() {
	
	        @Override
	        public void onAnimationEnd(Animation animation) {
	            tv.setVisibility(View.VISIBLE);	
	        }
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	
	        }
	        @Override
	        public void onAnimationStart(Animation animation) {
	
	        }
	    });
	    tv.startAnimation(aa);
	
	}
	private void setInvisible() {
	    for (int i = 0; i < tvs.length; i++) {
	        tvs[i].setVisibility(View.INVISIBLE);
	    }
	
	}
	
	
	protected void onStop() {
		super.onStop();
		BackgroundMusic.getInstance(this).stopBackgroundMusic();
	}
	/* (non-Javadoc)
	 * @see com.hanchu.lufthansa.viewpager.ViewPager.OnPageChangeListener#onPageScrolled(int, float, int)
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.hanchu.lufthansa.viewpager.ViewPager.OnPageChangeListener#onPageSelected(int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		animal(position);
		
	}

	/* (non-Javadoc)
	 * @see com.hanchu.lufthansa.viewpager.ViewPager.OnPageChangeListener#onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		
	}
	
	//动画
	private void animal(int position) {
		animationBottom = AnimationUtils.loadAnimation(RunwayActivity.this,
				R.anim.tutorail_bottom);
		switch (position) {
		case 0:
			mTypeTextView.start(TextData.DATA_1);			
			g0_next.startAnimation(animationBottom);
			break;
		case 1:
			loadContent();
			g1_next.startAnimation(animationBottom);
			break;
		case 2:
			mTypeTextView1.start(TextData.DATA_2);
			g2_next.startAnimation(animationBottom);
		case 3:
			g3_next.startAnimation(animationBottom);
		case 4:
			scrollText.initScrollTextView(this.getWindowManager(), TextData.DATA_5); 
			scrollText.starScroll();
			g4_next.startAnimation(animationBottom);
			break;
		case 5:
			g5_next.startAnimation(animationBottom);
		case 6:
			mTypeTextView2.start(TextData.DATA_7);
			g6_next.startAnimation(animationBottom);
			break;
		case 7:
			mTypeTextView3.start(TextData.DATA_8);
			g7_next.startAnimation(animationBottom);
			break;
		case 8:
			mTypeTextView4.start(TextData.DATA_9);
			g8_next.startAnimation(animationBottom);
			break;
		case 9:
			mTypeTextView5.start(TextData.DATA_10);
			break;
		default:
			break;
		}
	}
	
}
