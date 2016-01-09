package com.mygdx.game.android;


import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**ublic class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGdxGame(), config);
	}

	@Override
	public android.view.WindowManager getWindowManager() {
		return null;
	}

	@Override
	public void runOnUiThread(Runnable runnable) {

	}

	@Override
	public void startActivity(android.content.Intent intent) {

	}
}**/
