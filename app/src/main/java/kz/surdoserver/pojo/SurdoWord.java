package kz.surdoserver.pojo;

import com.google.gson.annotations.SerializedName;

public class SurdoWord {

    @SerializedName("id")
    private int id;

    @SerializedName("name_ru")
    private String nameRU;

    @SerializedName("name_kz")
    private String nameKZ;

    @SerializedName("video_link_ru")
    private String videoLinkRU;

    @SerializedName("video_link_kz")
    private String videoLinkKZ;

    public SurdoWord(int id, String nameRU, String nameKZ, String videoLinkRU, String videoLinkKZ) {
        this.id = id;
        this.nameRU = nameRU;
        this.nameKZ = nameKZ;
        this.videoLinkRU = videoLinkRU;
        this.videoLinkKZ = videoLinkKZ;
    }

    public int getId() {
        return id;
    }

    public String getNameRU() {
        return nameRU;
    }

    public String getNameKZ() {
        return nameKZ;
    }

    public String getVideoLinkRU() {
        return videoLinkRU;
    }

    public String getVideoLinkKZ() {
        return videoLinkKZ;
    }
}
