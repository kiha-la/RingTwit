package jp.gr.java_conf.ringtwit;



import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RingTwitActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	public static RequestToken _req = null;
	public static OAuthAuthorization _oauth = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ボタン設定
		Button startServiceButton = (Button) findViewById(R.id.button1);
		Button authorizeButton = (Button) findViewById(R.id.button2);
		startServiceButton.setOnClickListener(this);
		authorizeButton.setOnClickListener(this);

	}

	protected void onResume() {
		super.onRestart();

		if ( _req !=null) {
			Log.d("ringtwit", "onresume");

		}

	}

	public void onClick(View v) {

		switch (v.getId()) {

		// 通話監視サービス開始ボタンの場合
		case R.id.button1:
			Intent startServiceIntent = new Intent(RingTwitActivity.this,
					RingTwitService.class);
			startService(startServiceIntent);
			break;

		// twitter認証ボタンの場合
		case R.id.button2:
			executeOauth();
			break;

		}

	}

	// 認証ページをブラウザで開く
	private void executeOauth() {
		Log.d("ringtwit", "executeOauth");

		// Twitetr4jの設定を読み込む
		Configuration conf = ConfigurationContext.getInstance();

		// Oauth認証オブジェクト作成
		OAuthAuthorization oauth = new OAuthAuthorization(conf);
		// Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
		oauth.setOAuthConsumer("ztK1xRFV31ma6O0a3jiQ",
				"HFiekYjyOzMdjm1B7XjuWo2tiFf9ZBo7CZujPrrw4");

		// アプリの認証オブジェクト作成
		RequestToken _req = null;
		try {
			_req = oauth.getOAuthRequestToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		String _uri;
		_uri = _req.getAuthorizationURL();
		// _uriのログ
		Log.d("ringtwit", _uri);
		startActivityForResult(new Intent(Intent.ACTION_VIEW, Uri.parse(_uri)),
				0);

	}

}