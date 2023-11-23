package com.example.lab9.recycler;

import com.example.lab9.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class DownloadFile {

    public static final int STATUS_COMPLETE = 101;
    public static final int STATUS_WAITING = -1;
    public static final int STATUS_FAIL = -2;

    private static final int[] ICONS = {
            R.drawable.icon_archive,
            R.drawable.icon_image,
            R.drawable.icon_movie,
            R.drawable.icon_music,
            R.drawable.icon_office,
            R.drawable.icon_text,
            R.drawable.icon_other
    };

    private static final Random random = new Random();

    private String id;
    private String name;
    private String localPath;
    private long size;
    private String downloadUrl;
    private int progress; // 101: success, -1: failed
    private int icon;

    public DownloadFile() {
        setId(UUID.randomUUID().toString());
        setProgress(STATUS_WAITING);
    }

    public void markAsComplete() {
        setProgress(STATUS_COMPLETE);
    }

    public void markAsFail() {
        setProgress(STATUS_FAIL);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        String ext = name.substring(name.lastIndexOf('.') + 1).toLowerCase();
        switch (ext) {
            case "jpg":
            case "png":
            case "gif":
            case "bmp":
                setIcon(R.drawable.icon_image);
                break;
            case "zip":
            case "rar":
            case "gz":
            case "7z":
                setIcon(R.drawable.icon_archive);
                break;
            case "mp4":
            case "mkv":
            case "mov":
            case "mpg":
            case "webm":
            case "avi":
            case "m4v":
                setIcon(R.drawable.icon_movie);
                break;
            case "mp3":
            case "wav":
            case "mpa":
            case "m4a":
            case "flac":
            case "wma":
            case "aac":
                setIcon(R.drawable.icon_music);
                break;
            case "doc":
            case "docx":
            case "xls":
            case "xlsx":
            case "ppt":
            case "pptx":
                setIcon(R.drawable.icon_office);
                break;
            case "txt":
            case "html":
            case "php":
            case "py":
            case "js":
            case "css":
                setIcon(R.drawable.icon_text);
                break;
            default:
                setIcon(R.drawable.icon_office);
                break;
        }
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
        setName(localPath.substring(localPath.lastIndexOf('/') + 1));
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isCompleted()  {
        return progress == STATUS_COMPLETE;
    }

    public boolean isFailed() {
        return progress == STATUS_FAIL;
    }

    public boolean isWaiting() {
        return progress == STATUS_WAITING;
    }
    public String getDisplaySize() {
        if (size < 1024) {
            return size + " bytes";
        }

        double size = this.size * 1.0 / 1024;
        if (size < 1024) {
            return round(size) + " KB";
        }

        size = size / 1024;
        if (size < 1024) {
            return round(size) + " MB";
        }

        size = size / 1024;
        return round(size) + " GB";
    }

    private double round(double input) {
        return Math.round(input * 100.0) / 100.0;
    }
}
