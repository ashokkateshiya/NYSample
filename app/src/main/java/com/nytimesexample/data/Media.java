package com.nytimesexample.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Eval-Ranjitha on 04-08-2018.
 */

public class Media implements Serializable{

    @SerializedName("media-metadata")
    private ArrayList<MediaItem> mediaItems;

    public ArrayList<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public void setMediaItems(ArrayList<MediaItem> mediaItems) {
        this.mediaItems = mediaItems;
    }
}
