package com.two.football.model;



public class VideoLq {
    private String  id, title, tournaments, urlThumbnail, urlVideo;

    public VideoLq() {
    }

    public VideoLq(String id, String title, String tournaments, String urlThumbnail, String urlVideo) {
        this.id = id;
        this.title = title;
        this.tournaments = tournaments;
        this.urlThumbnail = urlThumbnail;
        this.urlVideo = urlVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTournaments() {
        return tournaments;
    }

    public void setTournaments(String tournaments) {
        this.tournaments = tournaments;
    }

    public String getUrlThumbnail() {
        return urlThumbnail;
    }

    public void setUrlThumbnail(String urlThumbnail) {
        this.urlThumbnail = urlThumbnail;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
