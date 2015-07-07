package com.hanchu.lufthansa;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hanchu.lufthansa.adapter.ViewPagerAdapter;
import com.hanchu.lufthansa.scrollviewtext.AutoScrollTextView;
import com.hanchu.lufthansa.typetextview.TypeTextView;
import com.hanchu.lufthansa.R;

/**
 * 
 * @{# GuideActivity.java Create on 2013-5-2 下午10:59:08
 * 
 *     class desc: 引导界面
 * 
 *     <p>
 *     Copyright: Copyright(c) 2013
 *     </p>
 * @Version 1.0
 * @Author <a href="mailto:gaolei_xj@163.com">Leo</a>
 * 
 * 
 */
public class GuideActivity extends Activity implements OnPageChangeListener {

	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private AutoScrollTextView scrollText = null;
	// 底部小点图片
	private ImageView[] dots;

	// 记录当前选中位置
	private int currentIndex;
	
	private TextView[] tvs = new TextView[6];
    private Handler handler = new Handler();
    
	private TypeTextView mTypeTextView = null;
	private TypeTextView mTypeTextView1 = null;
	private static final String DATA_1 = "时间2012年暑假，又是一个平常的日子，寡人照例批阅奏章（帖子）。这时一个头像吸引了我，以神界（飞行）特有的直觉，这十有八九是个同类。果不其然。就这么在浩瀚的人群中，发现了你。关键的是，这还是个妹子呀。";
	private static final String DATA_2 = "后来对你了解越多，我发现所谓理想中的那个人就是这样的吧。不过回想自己的状况，我感觉这是一个遥不可及的距离，我配不上她。立刻把自己的这点念想彻底熄灭了。所以当做什么也没想过，我们时常还是相谈甚欢的。";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		
		// 初始化页面
		initViews();

		// 初始化底部小点
		initDots();
		BackgroundMusic.getInstance(this).playBackgroundMusic("Butterfly Kiss.mp3", true);
		
	}
	
	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);

		views = new ArrayList<View>();
		// 初始化引导图片列表
		views.add(0,inflater.inflate(R.layout.guide_one, null));
		mTypeTextView = (TypeTextView) views.get(0).findViewById(R.id.typeTx_1);
		views.add(1,inflater.inflate(R.layout.guide_two, null));
		textView2();
		views.add(2,inflater.inflate(R.layout.guide_three, null));
		mTypeTextView1 = (TypeTextView) views.get(2).findViewById(R.id.typeTx_2);
		views.add(3,inflater.inflate(R.layout.guide_four, null));
		views.add(4,inflater.inflate(R.layout.guide_five, null));
		scrollText = (AutoScrollTextView) views.get(4).findViewById(R.id.scrolltext);
		views.add(5,inflater.inflate(R.layout.guide_six, null));
		//mTypeTextView = new TypeTextView(this);
		
		
		// 初始化Adapter
		vpAdapter = new ViewPagerAdapter(views, this);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		// 绑定回调
		vp.setOnPageChangeListener(this);
	}

	private void textView2(){
		tvs[0] = (TextView) views.get(1).findViewById(R.id.text_1);
        tvs[1] = (TextView) views.get(1).findViewById(R.id.text_2);
        tvs[2] = (TextView) views.get(1).findViewById(R.id.text_3);
        tvs[3] = (TextView) views.get(1).findViewById(R.id.text_4);
        tvs[4] = (TextView) views.get(1).findViewById(R.id.text_5);
        tvs[5] = (TextView) views.get(1).findViewById(R.id.text_6);
	}
	
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
		mTypeTextView.start(DATA_1);		
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
	                "欢迎使用远程点播"); 
			scrollText.starScroll();
			break;
		default:
			break;
		}
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int arg0) {
		// 设置底部小点选中状态
		setCurrentDot(arg0);
	}
	


	
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

}
