package io.ably.lib.test.android;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import java.io.InputStream;
import java.io.IOException;
import io.ably.lib.test.common.ResourceLoader;

public class AssetResourceLoader implements ResourceLoader {
	public byte[] read(String resourceName) throws IOException {
		InputStream is = null;
		byte[] bytes = null;
		try {
			is = instrumentationCtx.getAssets().open(resourceName);
			Log.v(TAG, "Reading " + is.available() + " bytes for resource " + resourceName);
			bytes = new byte[is.available()];
			is.read(bytes);
		} catch(IOException ioe) {
			Log.e(TAG, "Unexpected exception reading asset resource", ioe);
		} finally {
			if(is != null)
				is.close();
			return bytes;
		}
	}

	private static final String TAG = AssetResourceLoader.class.getName();
	private Context instrumentationCtx = InstrumentationRegistry.getContext();
}
