package jp.gr.java_conf.ringtwit;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import android.app.Activity;
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



		AccessToken token = null;

        //Twitterの認証画面から発行されるIntentからUriを取得
        Uri uri = getIntent().getData();

        if(uri != null && uri.toString().startsWith("Callback://CallBackActivity")){
            //oauth_verifierを取得する
            String verifier = uri.getQueryParameter("oauth_verifier");
            try {
                //AccessTokenオブジェクトを取得
                token = OAuthActivity.mTwitter.getOAuthAccessToken(OAuthActivity.requestToken, verifier);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }

        TextView tv = (TextView)findViewById(R.id.button1);
        CharSequence cs = "token：" + token.getToken() + "\r\n" + "token secret：" + token.getTokenSecret();
        tv.setText(cs);
	}

}
