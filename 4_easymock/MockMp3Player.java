// Philosophy of mockobjects:
// // this file is a Mock Implementation to replace a real "Mp3Player" class for testing

/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import java.util.ArrayList;

public class MockMp3Player implements Mp3Player {

  // not playing by default
  private boolean isPlaying = false;
  // starts at beginning
  private double currentPosition = 0.0;
  // TODO: set this somewhere
  private String currentSong = "";
  // store songs
  private ArrayList trackList = new ArrayList();

  /** 
   * Begin playing the filename at the top of the
   * play list, or do nothing if playlist 
   * is empty. 
   */
  public void play(){
    if(this.trackList.size() > 0){
      // testPlay expects song to be at 0.1 s
      this.currentPosition = 0.1;
      this.isPlaying = true;
    }
  }


  /** 
   * Pause playing. Play will resume at this spot. 
   */
  public void pause(){
    this.isPlaying = false;
  }


  /** 
   * Stop playing. The current song remains at the
   * top of the playlist, but rewinds to the 
   * beginning of the song.
   */
  public void stop(){
    this.isPlaying = false;
    this.currentPosition = 0.0;
  }

  
  /** Returns the number of seconds into 
   * the current song.
   */
  public double currentPosition(){
    return currentPosition;
  }



  /**
   * Returns the currently playing file name.
   */
  public String currentSong(){
    return currentSong;
  }


  /** 
   * Advance to the next song in the playlist 
   * and begin playing it.
   */
  public void next(){
  }


  /**
   * Go back to the previous song in the playlist
   * and begin playing it.
   */
  public void prev(){
  }


  /** 
   * Returns true if a song is currently 
   * being played.
   */
  public boolean isPlaying(){
    return this.isPlaying;
  }


  /**
   * Load filenames into the playlist.
   */
  public void loadSongs(ArrayList names){
    this.trackList = names;
  }

}
