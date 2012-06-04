package jp.gr.java_conf.ringtwit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class RingTwitService extends Service {

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	public void onCreate() {

	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		// 確認用トースト
		ToastNumber("start");

		// 電話情報の受信開始
		TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		telManager.listen(phoneStateListener,
				PhoneStateListener.LISTEN_CALL_STATE
						| PhoneStateListener.LISTEN_SERVICE_STATE
						| PhoneStateListener.LISTEN_SIGNAL_STRENGTH
						| PhoneStateListener.LISTEN_CELL_LOCATION);
		return startId;

	}

	public PhoneStateListener phoneStateListener = new PhoneStateListener() {
		@Override
		public void onCallStateChanged(int state, String number) {
			if (state == TelephonyManager.CALL_STATE_RINGING)
				ToastNumber(number);
		}
	};

	// トースト
	public void ToastNumber(String number2) {
		Toast.makeText(this, number2, Toast.LENGTH_LONG).show();
	}

}
