package com.example.mediaplayerpreferences;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * CPRE 388 - Labs
 * <p/>
 * Copyright 2013
 */
public class MyMediaPlayerActivity extends Activity {

    /**
     * Other view elements
     */
    private TextView songTitleLabel;

    /**
     * media player:
     * http://developer.android.com/reference/android/media/MediaPlayer.html
     */
    private MediaPlayer mp;

    /**
     * Index of the current song being played
     */
    private int currentSongIndex = 0;
    private int songRequested = -1;

    /**
     * Variables for preferences
     */
    private boolean shuffle;
    private boolean loop;

    /**
     * List of Sounds that can be played in the form of SongObjects
     */
    private static ArrayList<SongObject> songsList = new ArrayList<SongObject>();

    private static final int PICK_SONG_REQUEST = 1;

    Button back, play, forward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_main);

        songTitleLabel = (TextView) findViewById(R.id.songTitle);

        // Initialize the media player
        mp = new MediaPlayer();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (loop) {
                    playSong(currentSongIndex);
                } else {
                    play.setBackgroundResource(R.drawable.btn_play);
                    onForwardBtClick();
                }

            }
        });


        // Getting all songs in a list
        populateSongsList();

        // By default play first song if there is one in the list
        playSong(0);

        back = (Button) findViewById(R.id.backbutton);
        play = (Button) findViewById(R.id.playpausebutton);
        forward = (Button) findViewById(R.id.forwardbutton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackBtClick();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlayBtClick();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onForwardBtClick();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.media_player_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_choose_song:
                // Open SongList to display a list of audio files to play
                //TODO
                Intent i = new Intent(this, SongList.class);
                startActivityForResult(i, PICK_SONG_REQUEST);
                return true;
            case R.id.menu_preferences:
                // Display Settings page
                //TODO
                Intent j = new Intent(this, MediaPreference.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Helper function to play a song at a specific index of songsList
     *
     * @param songIndex - index of song to be played
     */
    public void playSong(int songIndex) {
        // Play song if index is within the songsList
        if (songIndex < songsList.size() && songIndex >= 0) {
            try {
                mp.stop();
                mp.reset();
                mp.setDataSource(songsList.get(songIndex).getFilePath());
                mp.prepare();
                mp.start();
                // Displaying Song title
                String songTitle = songsList.get(songIndex).getTitle();
                songTitleLabel.setText(songTitle);

                // Changing Button Image to pause image
                ((Button) findViewById(R.id.playpausebutton)).setBackgroundResource(R.drawable.btn_pause);

                // Update song index
                currentSongIndex = songIndex;

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (songsList.size() > 0) {
            playSong(0);
        }
    }


    /**
     * Get list of info for all sounds to be played
     */
    public void populateSongsList() {
        //TODO add all songs from audio content URI to this.songsList
        songsList = new ArrayList<SongObject>();

        //set preferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Resources res = getResources();
        shuffle = prefs.getBoolean(res.getString(R.string.mp_shuffle_pref), false);
        loop = prefs.getBoolean(res.getString(R.string.mp_loop_pref), false);
        String songInit = prefs.getString(res.getString(R.string.mp_songInit_pref), "A-Z");

        // Get a Cursor object from the content URI
        String[] mProjection = {MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA};
        String mSelectionClause = MediaStore.Audio.Media.IS_MUSIC + " = 1";
        String[] mSelectionArgs = null;
        String mSortOrder = null;

        Cursor mCursor = getContentResolver().query(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                mSortOrder
        );
        // Use the cursor to loop through the results and add them to
        //		the songsList as SongObjects
        while (mCursor.moveToNext()) {

            String songTitle = mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String songPath = mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.DATA));

            SongObject s = new SongObject(songTitle, songPath);
            if (songInit.equals("A-Z")) {
                songsList.add(s);
            } else {
                if (songInit.equalsIgnoreCase(songTitle.charAt(0) + "")) {
                    songsList.add(s);
                }
            }

        }

        mCursor.close();


        //shuffle the songsList if in shuffle mode
        if (shuffle) {
            System.out.println("In Shuffle Mode!!!!!!!!!!!!!!!!!!!!!!!!!");
            Collections.shuffle(songsList);
        }
    }

    /**
     * Get song list for display in ListView
     *
     * @return list of Songs
     */
    public static ArrayList<SongObject> getSongsList() {
        return songsList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateSongsList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check which request we're responding to
        if (requestCode == PICK_SONG_REQUEST) {
            //Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //Get the index that points to the selected song
                songRequested = data.getIntExtra("songIndex", -1);
                if (songRequested != -1) {
                    currentSongIndex = songRequested;
                    playSong(songRequested);
                }
            }//End inner if
        }//End outer if
    }


    private void onPlayBtClick() {
        try {
            if (mp.isPlaying()) {
                mp.pause();
                play.setBackgroundResource(R.drawable.btn_play);
            } else {
                mp.start();
                play.setBackgroundResource(R.drawable.btn_pause);
            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void onBackBtClick() {
        if (songsList.size() > 0) {
            currentSongIndex = currentSongIndex - 1;
            if (currentSongIndex < 0) currentSongIndex += songsList.size();
            playSong(currentSongIndex);
        }

    }

    private void onForwardBtClick() {
        if (songsList.size() > 1) {
            currentSongIndex = currentSongIndex + 1;
            currentSongIndex = currentSongIndex % (songsList.size() - 1);
            playSong(currentSongIndex);
        }

    }
}


