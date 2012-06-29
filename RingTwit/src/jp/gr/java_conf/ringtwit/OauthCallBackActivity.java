package jp.gr.java_conf.ringtwit;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.style.SuperscriptSpan;
import android.widget.TextView;

public class OauthCallBackActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		//setContentView(layoutResID);
		
		Intent data=getIntent();
		long userId = data.getLongExtra(OAuthActivity.USER_ID, 0);
		String screenName = data.getStringExtra(OAuthActivity.SCREEN_NAME);
		String token = data.getStringExtra(OAuthActivity.TOKEN);
		String tokenSecret = data.getStringExtra(OAuthActivity.TOKEN_SECRET);  

		Twitter tw =new TwitterFactory().getInstance();
	}

}
