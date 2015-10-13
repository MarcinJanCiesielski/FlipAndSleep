package com.nosite.cmarcin.flipandsleep;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by FAN on 2015-10-13.
 */
public class FaSService extends Service {
	protected int usrRingerMode;
	protected int usrAlarmVol;
	protected int usrDtmfVol;
	protected int usrMusicVol;
	protected int usrNotifyVol;
	protected int usrRingVol;
	protected int usrSystemVol;
	protected int usrVoiceCallVol;

	protected AudioManager audioManager;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		setUserVolumeSettings();
		Toast.makeText(this, "FaS Service Destroyed", Toast.LENGTH_LONG).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Let it continue running until it is stopped.
		audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
		getUserVolumeSettings();
		silenceMode();
		Toast.makeText(this, "FaS Service Started", Toast.LENGTH_LONG).show();
		return START_STICKY;
		//return super.onStartCommand(intent, flags, startId);
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public void silenceMode() {
		audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0); // The audio stream for alarms
		audioManager.setStreamVolume(AudioManager.STREAM_DTMF, 0, 0); //The audio stream for DTMF Tones
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0); //The audio stream for music playback
		audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0); //The audio stream for notifications
		audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0); //The audio stream for the phone ring
		audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0); //The audio stream for system sounds
		audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 0, 0); //The audio stream for phone calls

	}

	public void getUserVolumeSettings(){
		usrRingerMode = audioManager.getRingerMode();
		usrAlarmVol  = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
		usrDtmfVol  = audioManager.getStreamVolume(AudioManager.STREAM_DTMF);
		usrMusicVol  = audioManager.getStreamVolume(audioManager.STREAM_MUSIC);
		usrNotifyVol  = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
		usrRingVol  = audioManager.getStreamVolume(AudioManager.STREAM_RING);
		usrSystemVol  = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		usrVoiceCallVol  = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
	}

	public void setUserVolumeSettings() {
		audioManager.setRingerMode(usrRingerMode);
		audioManager.setStreamVolume(AudioManager.STREAM_ALARM, usrAlarmVol, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_DTMF, usrDtmfVol, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, usrMusicVol, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, usrNotifyVol, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_RING, usrRingerMode, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, usrSystemVol, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, usrVoiceCallVol, 0);
	}
}
