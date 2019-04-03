package kz.surdoserver.pojo;

import com.google.gson.annotations.SerializedName;

public class SurdoWord {

    @SerializedName("id")
    private int id;

    @SerializedName("name_ru")
    private String nameRU;

    @SerializedName("name_kz")
    private String nameKZ;

    @SerializedName("video_link")
    private String videoLink;

    public SurdoWord(int id, String nameRU, String nameKZ, String videoLink) {
        this.id = id;
        this.nameRU = nameRU;
        this.nameKZ = nameKZ;
        this.videoLink = videoLink;
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

    public String getVideoLink() {
        return videoLink;
    }
}
